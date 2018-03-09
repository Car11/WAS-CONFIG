package cr.go.ice.interrupciones.BO.impl;

import java.util.List;

import com.vvs.bussiness.BussinessObject;

import cr.go.ice.interrupciones.BO.IndiceCalidadRegionBO;
import cr.go.ice.interrupciones.DAO.IndiceCalidadRegionDAO;
import cr.go.ice.interrupciones.domain.IndiceCalidadRegion;

public class IndiceCalidadRegionBOImpl extends BussinessObject implements IndiceCalidadRegionBO{
	
	private IndiceCalidadRegionDAO indiceCalidadRegionDAO;
	
	public List<IndiceCalidadRegion> buscar(Integer anno, Integer mes){
        return this.indiceCalidadRegionDAO.buscar(anno, mes);
    }

	public IndiceCalidadRegionDAO getIndiceCalidadRegionDAO() {
		return indiceCalidadRegionDAO;
	}

	public void setIndiceCalidadRegionDAO(IndiceCalidadRegionDAO indiceCalidadRegionDAO) {
		this.indiceCalidadRegionDAO = indiceCalidadRegionDAO;
	}

}
