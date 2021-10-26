package com.recetario.Usuario;

import com.recetario.Foto.FotoService;
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

    @Transactional
    public void registrar(
            @Nullable MultipartFile archivo,
            @NonNull Usuario u,
            @NonNull String clave2
    ) throws Exception {
        try {
            u.setAlta(new Date());
            try {
                u.setFoto(fotoService.guardar(archivo));
            } catch (Exception e) {
                e.printStackTrace();
                u.setFoto(null);
            }
            encriptarClave(u);
            repo.save(u);
        } catch (Exception e) {
            throw new Exception("Error al registrar usuario");
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

}
