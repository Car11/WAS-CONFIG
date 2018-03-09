package cr.go.ice.interrupciones.BO;

import java.util.List;

import cr.go.ice.interrupciones.domain.IndiceCalidadNacional;

public interface IndiceCalidadNacionalBO {
	
	public List<IndiceCalidadNacional> buscar(Integer anno, Integer mes);
    
}
