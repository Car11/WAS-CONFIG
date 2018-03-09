package cr.go.ice.interrupciones.DAO;

import java.util.List;

import cr.go.ice.interrupciones.domain.IndiceCalidadNacional;

public interface IndiceCalidadNacionalDAO {
	
	public List<IndiceCalidadNacional> buscar(Integer anno, Integer mes);
}
