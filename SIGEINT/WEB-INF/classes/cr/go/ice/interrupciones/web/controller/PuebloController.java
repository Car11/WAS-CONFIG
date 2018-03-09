package cr.go.ice.interrupciones.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.PuebloBO;
import cr.go.ice.interrupciones.domain.Pueblo;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.PuebloController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>PuebloController.java</code>.</p>
 * <p>Fecha creación: 16/04/2007</p>
 * <p>Ultima actualización: 16/04/2007</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class PuebloController extends AbstractFacesController {

	private PuebloBO puebloBO;
	
    /**
     * Constructor  
     */
	public PuebloController(){
		
	}
	
	
	public String getInit(){
		return "success";
	}
	/**
	 * Metodo accesor de puebloBO.
	 * @return Retorna el puebloBO.
	 */
	public PuebloBO getPuebloBO() {
		return puebloBO;
	}
	/**
	 * Metodo modificador de puebloBO.
	 * @param puebloBO El puebloBO a modificar.
	 */
	public void setPuebloBO(PuebloBO puebloBO) {
		this.puebloBO = puebloBO;
	}
	
	/**
	 * Retorna una lista de pueblos
	 * @return Lista de pueblos
	 */
	public List getListaPueblos(){
		List pueblos = puebloBO.getPueblos();
		List resultado = new ArrayList();
		if(pueblos != null){
			for(int i = 0; i < resultado.size(); i++){
				Pueblo pueblo = (Pueblo) resultado.get(i);
				SelectItem itm  = new SelectItem(pueblo.getCodigoPueblo(),pueblo.getCodigoPueblo() + " - " + pueblo.getNombrePueblo());
				resultado.add(itm);
			}
		}
		return resultado;
	}

	@Override
	protected String getPropertyFieldName(String property) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void resetController() {
		// TODO Auto-generated method stub
		
	}

}
