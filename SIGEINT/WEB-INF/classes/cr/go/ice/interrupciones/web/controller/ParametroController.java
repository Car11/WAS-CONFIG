package cr.go.ice.interrupciones.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.cia.dominio.UsuarioCia;
import cr.go.ice.interrupciones.BO.ParametroBO;
import cr.go.ice.interrupciones.domain.Parametro;
import cr.go.ice.interrupciones.domain.ParametroID;
import cr.go.ice.interrupciones.domain.UsuarioOficina;
import cr.go.ice.interrupciones.utils.Usuario;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.AnimalController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ParametroController.java</code> Establese la relacion entre capa de presentacion y la logica del negocio.</p>
 * <p>Fecha creación: 25/01/2007</p>
 * <p>Ultima actualización: 25/01/2007</p>
 * @author Vista Verde Soft (Juan Manuel y Doc Cristian)
 * @version 1.1
 */

public class ParametroController extends AbstractFacesController {

	/**
	 * <code>id</code> Identificacion
	 */
	private Integer id;
	/**
	 * <code>generacionIndices</code> Generacion de Indeces
	 */
	private String generacionIndices;	
	/**
	 * <code>parametroBO</code> Parametros Bo
	 */
	private ParametroBO parametroBO;
	/**
	 * <code>indiceActual</code> Indice actual en la base de datos
	 */
	private String indiceActual;
	/**
	 * <code>p</code> Parametro actual en la base de datos
	 */
	private Parametro p;
	
	/**
	 * Constructor
	 */
	public ParametroController() {
		
		this.id = new Integer(1);
		
	}
	 public String getInit(){
	    		         
			return "success";
	    
	    }
	    
	

	/**
	 * Comment for getGeneracionIndices
	 * @return generacion de Indices
	 */
	public String getGeneracionIndices() {
		return generacionIndices;
	}


	/**
	 * Comment for setGeneracionIndices
	 * @param generacionIndices
	 */
	public void setGeneracionIndices(String generacionIndices) {
		this.generacionIndices = generacionIndices;
	}


	/**
	 * Comment for getId
	 * @return Identificacion
	 */
	public Integer getId() {
		return id;
	}


	/**
	 * Comment for setId
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	/**
	 * Comment for getParametroBO
	 * @return parametro de BO
	 */
	public ParametroBO getParametroBO() {
		return parametroBO;
	}


	/**
	 * Comment for setParametroBO
	 * @param parametroBO
	 */
	public void setParametroBO(ParametroBO parametroBO) {
		this.parametroBO = parametroBO;
	}
	
	
	/**
	 * Comment for getIndices
	 * @return Vector con una Lista de Indices
	 */
	public List getIndices(){			
		Vector rtn = new Vector();		  				   
		rtn.add(new SelectItem("S", "Si"));
		rtn.add(new SelectItem("N", "No"));
		return rtn;		  		
	}
	
	
	/***************************************************************/

	/**
	 * Comment for cambioParametro
	 * @return "success" al cambiar parametros
	 */
	public String cambioParametro() {
		
		Parametro parametro = new Parametro();
		
		ParametroID parametroID = new ParametroID();
		parametroID.setGeneracionIndices(this.generacionIndices);
		parametroID.setCongelarSeccion(p.getParametroID().getCongelarSeccion());
		parametro.setParametroID(parametroID);
		
		this.parametroBO.modificar(parametro,this.p);
		
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!","Actualización exitosa."));
	
        return "success";
		
	}

	/**
	 * Metodo accesor de indiceActual.
	 * @return Retorna el indiceActual.
	 */
	public String getIndiceActual() {		
		if(this.parametroBO.getParametros() != null && this.parametroBO.getParametros().size() > 0){
			p = (Parametro)this.parametroBO.getParametros().get(0);
			indiceActual = p.getParametroID().getGeneracionIndices();
		}
		else
			indiceActual = "";
		return indiceActual;
	}
    
    /**
     * Cancela
     * @return success
     */
    public String cancelar(){
        return "success";
    }
    
	/**
	 * Metodo modificador de indiceActual.
	 * @param indiceActual El indiceActual a modificar.
	 */
	public void setIndiceActual(String indiceActual) {
		this.indiceActual = indiceActual;
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
