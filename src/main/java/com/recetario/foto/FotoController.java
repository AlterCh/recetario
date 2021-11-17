package com.recetario.foto;

import com.recetario.usuario.domain.Usuario;
import com.recetario.usuario.repository.UsuarioRepository;
import com.recetario.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

@Controller
@RequestMapping("/foto")
public class FotoController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Foto del usuario actual
     *
     * @param httpSession
     * @return
     */
    @GetMapping("/usuario")
    public ResponseEntity<byte[]> fotoUsuario(HttpSession httpSession) {
        try {
            Usuario usuario = usuarioRepository.getById(((Usuario) httpSession.getAttribute("usuariosession")).getId());
            byte[] foto = usuario.getFoto().getContenido();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(foto, headers, HttpStatus.OK);
        } catch (Exception exception) {
            Logger.getLogger(FotoController.class.getName()).info("Error jpeg");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Foto de un usuario particular
     *
     * @param id
     * @return
     */
    @GetMapping("/usuarios")
    public ResponseEntity<byte[]> fotoUsuarios(@RequestParam String id) {
        try {
            Usuario usuario = usuarioRepository.getById(id);
            byte[] foto = usuario.getFoto().getContenido();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(foto, headers, HttpStatus.OK);
        } catch (Exception exception) {
            Logger.getLogger(FotoController.class.getName()).info("Error jpeg");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
