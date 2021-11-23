package com.recetario.favoritos;

import com.recetario.errores.ErrorServicio;
import com.recetario.proveedores.Proveedor;
import com.recetario.proveedores.ProveedorRepository;
import com.recetario.receta.Receta;
import com.recetario.receta.RecetaRepository;
import com.recetario.usuario.domain.Usuario;
import com.recetario.usuario.service.UsuarioService;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

@Service
public class FavoritoService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private RecetaRepository recetaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Transactional
    public void setFavorito(Usuario usuario, String id, String tipo) throws ErrorServicio {
        Object fav = null;
        if (tipo.equals("proveedor")) {
            Optional<Proveedor> aux = proveedorRepository.findById(id);
            if (aux.isPresent()) {
                Proveedor aux2 = aux.get();
                aux2.setFavorito(!aux2.getFavorito());
                fav = aux2;
            }
        } else if (tipo.equals("receta")) {
            Optional<Receta> aux = recetaRepository.findById(id);
            if (aux.isPresent()) {
                Receta aux2 = aux.get();
                aux2.setFavorito(!aux2.getFavorito());
                fav = aux2;
            }
        }
        if (fav != null) {
            usuarioService.agregarFavorito(fav, usuario, tipo);
        }

    }

}
