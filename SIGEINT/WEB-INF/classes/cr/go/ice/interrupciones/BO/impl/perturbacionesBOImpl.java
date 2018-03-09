package cr.go.ice.interrupciones.BO.impl;

import java.util.List;

import cr.go.ice.interrupciones.domain.Perturbaciones;



public class perturbacionesBOImpl {

	private Object perturbacionesDAO;

	public List<Perturbaciones> buscar(Integer anno, Integer mes){
        return ((Perturbaciones) this.perturbacionesDAO).buscar(anno, mes);
    }
	
}
