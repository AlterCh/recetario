package com.recetario.foto;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class FotoService {

    @Autowired
    private FotoRepository fotoRepository;

    public Foto guardar(@NonNull MultipartFile archivo) throws Exception {
        if (archivo.getSize() == 0) {
            return null;
        }
        if (archivo.getContentType() == "application/octet-stream") {
            throw new Exception("Error formato de archivo");
        }
        try {
            Foto foto = new Foto();
            foto.setMime(archivo.getContentType());
            foto.setNombre(archivo.getName());
            foto.setContenido(archivo.getBytes());
            return fotoRepository.save(foto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public Foto actualizar(Foto mFoto, MultipartFile archivo) throws Exception {
        if (archivo.getSize() == 0) {
            return mFoto;
        }
        if (archivo.getContentType() == "application/octet-stream") {
            throw new Exception("Error de formato de archivo");
        }
        try {
            Foto foto = new Foto();
            if (mFoto != null) {
                Optional<Foto> respuesta = fotoRepository.findById(mFoto.getId());
                if (respuesta.isPresent()) {
                    foto = respuesta.get();
                }
            }
            foto.setMime(archivo.getContentType());
            foto.setNombre(archivo.getName());
            foto.setContenido(archivo.getBytes());
            return fotoRepository.save(foto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }


    }
}
