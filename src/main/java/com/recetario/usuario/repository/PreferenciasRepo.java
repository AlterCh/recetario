package com.recetario.usuario.repository;

import com.recetario.usuario.domain.PreferenciasUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenciasRepo extends JpaRepository<PreferenciasUsuario,String> {

}
