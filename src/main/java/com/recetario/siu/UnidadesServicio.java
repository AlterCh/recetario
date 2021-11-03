package com.recetario.siu;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UnidadesServicio {

    public Collection<UnidadesFundamentales> getUnidadesByMagnitud(Magnitud magnitud){
        Collection<UnidadesFundamentales> listaDeUnidades = new ArrayList<>();
        for (UnidadesFundamentales value : UnidadesFundamentales.values()) {
            if(value.getMagnitud().equals(magnitud)){
                listaDeUnidades.add(value);
            }
        }
        return listaDeUnidades;
    }
    
    public List<String> getSimbolosOfUnidades(List<UnidadesFundamentales> unidadesFundamentalesList){
        List<String> listaDeSimbolos = new ArrayList<>();
        for (UnidadesFundamentales value: unidadesFundamentalesList) {
            listaDeSimbolos.add(value.getSym());
        }
        return listaDeSimbolos;
    }
}
