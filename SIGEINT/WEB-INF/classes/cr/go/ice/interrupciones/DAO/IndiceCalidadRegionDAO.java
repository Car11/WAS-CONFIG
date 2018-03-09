package cr.go.ice.interrupciones.DAO;

import java.util.List;

import cr.go.ice.interrupciones.domain.IndiceCalidadRegion;

public interface IndiceCalidadRegionDAO {
	public List<IndiceCalidadRegion> buscar(Integer anno, Integer mes);
}
