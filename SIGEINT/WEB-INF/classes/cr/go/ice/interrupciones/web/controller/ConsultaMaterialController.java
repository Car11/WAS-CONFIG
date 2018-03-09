package cr.go.ice.interrupciones.web.controller;

import java.io.IOException;
import java.util.List;

import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.component.datatable.DataTable;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.MaterialBO;
import cr.go.ice.interrupciones.servlets.ServletReporte;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.ConsultaMaterialController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web/p>
 * <p>Descricion de <code>ConsultaMaterialController.java</code> Implementa la consulta de tipos de material.</p>
 * <p>Fecha creación: 28/04/2009</p>
 * <p>Ultima actualización: 28/04/2009</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class ConsultaMaterialController extends AbstractFacesController {

    private DataTable dataTableMateriales;
    private MaterialBO materialBO;
    
    /**
     * Constructor por defecto 
     */
    public ConsultaMaterialController(){

    }
    
    public String getInit(){
    	return "success";
    }
    
    /**
     * Obtiene una lista de tipos de materiales
     * @return Lista de tipos de materiales
     */
    public List getMateriales(){
        List materiales = this.materialBO.getMateriales();
        return materiales;
    }
    
    /**
     * Genera el reporte de tipos de materiales
     * @return success o error
     */
    public String aceptar(){
        try{                    
            FacesContext context = FacesContext.getCurrentInstance();        
            context.getExternalContext().getSessionMap().put("tipoReporte",new Integer(ServletReporte.reporteTiposMaterial));
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ServletReporte");
            dispatcher.forward(request, response);
	    response.getOutputStream().flush();

                if(!FacesContext.getCurrentInstance().getResponseComplete() ) {
                    FacesContext.getCurrentInstance().responseComplete();
                }                                   
            return "success";
        } catch(ServletException se){
            se.printStackTrace();
            return "error";
        } catch(IOException ioe){
            ioe.printStackTrace();
            return "error";
        }  
    }
    
    /**
     * Cancela la opción de tipos de material
     * @return success
     */
    public String cancelar(){
        return "success";
    }

    /**
     * Metodo accesor de dataTableMateriales.
     * @return Retorna el dataTableMateriales.
     */
    public DataTable getDataTableMateriales() {
        return this.dataTableMateriales;
    }

    /**
     * Metodo modificador de dataTableMateriales.
     * @param dataTableMateriales El dataTableMateriales a modificar.
     */
    public void setDataTableMateriales(DataTable dataTableMateriales) {
        this.dataTableMateriales = dataTableMateriales;
    }

    /**
     * Metodo modificador de materialBO.
     * @param materialBO El materialBO a modificar.
     */
    public void setMaterialBO(MaterialBO materialBO) {
        this.materialBO = materialBO;
    }

	@Override
	protected java.lang.String getPropertyFieldName(java.lang.String property) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void resetController() {
		// TODO Auto-generated method stub
		
	}


    
    
}
