package com.recetario.usuario.service;

import com.recetario.usuario.repository.PreferenciasRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PreferenciasService {
    @Autowired
    PreferenciasRepo preferenciasRepo;


}
