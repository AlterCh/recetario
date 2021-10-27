package com.recetario.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;


    public List<Producto> getAll() {
        List<Producto> productos = productoRepository.findAll();
        return productos;
    }
}
