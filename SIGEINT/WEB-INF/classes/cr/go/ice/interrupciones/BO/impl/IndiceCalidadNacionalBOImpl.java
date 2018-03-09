package cr.go.ice.interrupciones.BO.impl;

import java.util.List;

import com.vvs.bussiness.BussinessObject;

import cr.go.ice.interrupciones.BO.IndiceCalidadNacionalBO;
import cr.go.ice.interrupciones.DAO.IndiceCalidadNacionalDAO;
import cr.go.ice.interrupciones.domain.IndiceCalidadNacional;

public class IndiceCalidadNacionalBOImpl extends BussinessObject implements IndiceCalidadNacionalBO{
	
	private IndiceCalidadNacionalDAO indiceCalidadNacionalDAO;
	
	public List<IndiceCalidadNacional> buscar(Integer anno, Integer mes){
        return this.indiceCalidadNacionalDAO.buscar(anno, mes);
    }

	public IndiceCalidadNacionalDAO getIndiceCalidadNacionalDAO() {
		return indiceCalidadNacionalDAO;
	}

	public void setIndiceCalidadNacionalDAO(IndiceCalidadNacionalDAO indiceCalidadNacionalDAO) {
		this.indiceCalidadNacionalDAO = indiceCalidadNacionalDAO;
	}

}
