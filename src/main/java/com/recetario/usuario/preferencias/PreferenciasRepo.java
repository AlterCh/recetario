package com.recetario.usuario.preferencias;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenciasRepo extends JpaRepository<PreferenciasUsuario,String> {

}
