package com.recetario.usuario.service;

import com.recetario.errores.ErrorServicio;
import com.recetario.foto.FotoService;
import com.recetario.producto.Producto;
import com.recetario.proveedores.Proveedor;
import com.recetario.receta.Receta;
import com.recetario.usuario.domain.Usuario;
import com.recetario.usuario.repository.UsuarioRepository;
import com.recetario.usuario.domain.PreferenciasUsuario;
import java.util.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {
    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private UsuarioRepository repo;
    @Autowired
    private FotoService fotoService;


    /**
     * TODO Falta realizar la verificacion de claves
     *
     * @param archivo: Imagen
     * @param u        : Usuario
     * @throws Exception
     */
    @Transactional
    public void registrar(
            @Nullable MultipartFile archivo,
            @NonNull Usuario u
    ) throws Exception {
        Usuario aux1 = repo.findByMail(u.getMail());
        if (aux1 != null) {
            throw new ErrorServicio("Usuario ya existe");
        }
        u.setAlta(new Date());
        try {
            u.setFoto(fotoService.guardar(archivo));
        } catch (Exception e) {
            u.setFoto(null);
            throw new ErrorServicio("No se pudo guardar la imagen");
        }
        encriptarClave(u);
        try {
            repo.save(u);
        } catch (Exception e){
            throw e;
        }
    }

    public void modificar(MultipartFile archivo, Usuario usuario) throws Exception {
        try {
            Optional<Usuario> respuesta = repo.findById(usuario.getId());
            if (respuesta.isPresent()) {
                Usuario aux = respuesta.get();
                if (!aux.getClave().equals(usuario.getClave())) {
                    encriptarClave(usuario);
                }
                aux = usuario;
                if (aux.getFoto() != null) {
                    aux.setFoto(fotoService.actualizar(aux.getFoto(), archivo));
                }
                repo.save(aux);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deshabilitar(String id) throws Exception {
        Optional<Usuario> aux = repo.findById(id);
        if (aux.isPresent()) {
            Usuario usuario = aux.get();
            usuario.setBaja(new Date());
            repo.save(usuario);
        } else {
            throw new Exception("Error al deshabilitar usuario");
        }
    }

    public void habilitar(String id) throws Exception {
        Optional<Usuario> aux = repo.findById(id);
        if (aux.isPresent()) {
            Usuario usuario = aux.get();
            usuario.setAlta(new Date());
            usuario.setBaja(null);
            repo.save(usuario);
        } else {
            throw new Exception("Error al habilitar usuario");
        }
    }

    public Usuario modificarPreferencias(Usuario usuario, PreferenciasUsuario preferenciasUsuario) throws ErrorServicio {
//        try{
//            Optional<Usuario> aux = repo.findById(usuario.getId());
//            if(!aux.isPresent()){
//                throw new ErrorServicio("Usuario no encontrado");
//            }
//            if(!aux.get().getPreferenciasUsuario().equals(preferenciasUsuario)){
//                aux.get().setPreferenciasUsuario(preferenciasUsuario);
//            }
//
//        }
//
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        try {
            Usuario usuario = repo.findByMail(mail);
            List<GrantedAuthority> authorities = new ArrayList<>();
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            // TODO Se guarda usuario en la cache
            //  y no me deja redireccionar,
            //  y por mas que la contraseña sea erronea
            //  el usuario se almacena igual en caché
            if (usuario != null) {
                //Lista de Permisos
                //Se le asigna un rol al usuario
                authorities.add(new SimpleGrantedAuthority("ROLE_USUARIO_REGISTRADO"));
                //authorities.add(new SimpleGrantedAuthority("MODULO_MASCOTAS"));
                //Se guardan los datos del usuario en la cache
                session.setAttribute("usuariosession", usuario);
                return new User(usuario.getMail(), usuario.getClave(), authorities);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void encriptarClave(Usuario u) {
        u.setClave(new BCryptPasswordEncoder()
                .encode(u.getClave()));
    }

    public Usuario getUsuario(Usuario usuario) throws ErrorServicio {
        Optional<Usuario> u = repo.findById(usuario.getId());
        if (!u.isPresent()) {
            throw new ErrorServicio(this.getClass().getName() + ": El usuario no se ha podido encontrar");
        }
        return u.get();
    }

    public void modificar(Usuario usuario) throws ErrorServicio {
        try {
            Optional<Usuario> respuesta = repo.findById(usuario.getId());
            if (respuesta.isPresent()) {
                Usuario aux = respuesta.get();
                aux = usuario;
                repo.save(aux);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ErrorServicio(this.getClass().getName() + ": No se ha podido modificar el usuario");
        }
    }

    public void agregarFavorito(Object favorito, Usuario usuario, String tipo) throws ErrorServicio {

        try {
            Optional<Usuario> respuesta = repo.findById(usuario.getId());
            if (respuesta.isPresent()) {
                Usuario aux = respuesta.get();
                if(tipo == "proveedor"){
                    Iterator<Proveedor> proveedores = aux.getListaProveedores().iterator();
                    if(proveedores.hasNext()){
                        Proveedor p = proveedores.next();
                        if(p.getId().equals(((Proveedor) favorito).getId())){
                            aux.getListaRecetas().remove(p);
                            aux.getListaProveedores().add((Proveedor) favorito);
                        }
                    }
                }else if (tipo == "receta"){
                    Iterator<Receta> recetas = aux.getListaRecetas().iterator();
                    if(recetas.hasNext()){
                        Receta r = recetas.next();
                        if(r.getId().equals(((Receta) favorito).getId())){
                            aux.getListaRecetas().remove(r);
                            aux.getListaRecetas().add((Receta) favorito);
                        }
                    }
                }
                modificar(aux);
            }
        } catch (Exception e) {
            throw new ErrorServicio("No se pudo completar esta acción.");
        }
    }

    public void agregarProveedor(Proveedor proveedor, Usuario usuario) throws ErrorServicio {
        try {

            Optional<Usuario> usuarioOptional = repo.findById(usuario.getId());
            if (usuarioOptional.isPresent()) {
                Usuario aux = usuarioOptional.get();
                aux.getListaProveedores().add(proveedor);
                repo.save(aux);
            }
        } catch (Exception ex) {
            throw new ErrorServicio("No se ha podido agregar el proveedor");
        }
    }



    public void actualizarHttpSession(HttpSession httpSession, Usuario usuario) {
        httpSession.removeAttribute("usuariosession");
        httpSession.setAttribute("usuariosession", repo.getById(usuario.getId()));
    }

    public void borrarReceta(Usuario usuario, Receta receta) throws ErrorServicio {
        usuario.getListaRecetas().remove(receta);
        modificar(usuario);
    }

    public void borrarProducto(Producto producto, Usuario usuario) throws ErrorServicio {
        usuario.getListaProductos().remove(producto);
        modificar(usuario);
    }
}
