package cr.go.ice.interrupciones.BO;

import java.util.List;

import cr.go.ice.interrupciones.domain.IndiceCalidadRegion;

public interface IndiceCalidadRegionBO {
	
	public List<IndiceCalidadRegion> buscar(Integer anno, Integer mes);

}
