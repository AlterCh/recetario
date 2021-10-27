package com.recetario.ingrediente;

import java.util.List;
import javax.transaction.Transactional;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class IngredienteService {
    
    private IngredienteRepository repo;
    
    //Debo hacer el CRUD
    
    @Transactional
    public void registrar(@NonNull Ingrediente i) throws Exception { //C
        
        try {
            validar(i);
            repo.save(i);
        } catch (Exception e) {
            throw new Exception("No se pudo registrar el ingrediente, intente de nuevo.");
        }
        
    }  
    
    @Transactional
    public List<Ingrediente> listarIngredientes() { //R
        return repo.findAll();
    }
    
    public void modificar(Ingrediente i) throws Exception { //U
        
        try {
            
            Optional<Ingrediente> aux = repo.findById(i.getId());
            
        } catch (Exception e) {
            
        }
        
    }
    
    public void validar(Ingrediente i) throws Exception {
        
        if (i == null) {
            throw new Exception("Intente nuevamente.");
        }
        
    }
}
