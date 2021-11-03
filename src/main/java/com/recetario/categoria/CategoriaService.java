package com.recetario.categoria;

import com.recetario.producto.Producto;
import com.recetario.producto.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public List<Categoria> getAll() {
        List<Categoria> categoria = categoriaRepository.findAll();
        return categoria;
    }
}
