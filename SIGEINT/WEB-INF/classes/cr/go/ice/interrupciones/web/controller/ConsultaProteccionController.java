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

import cr.go.ice.interrupciones.BO.ProteccionBO;
import cr.go.ice.interrupciones.servlets.ServletReporte;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.ConsultaProteccionController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web/p>
 * <p>Descricion de <code>ConsultaProteccionController.java</code> Implementa la consulta de tipos de protección.</p>
 * <p>Fecha creación: 14/04/2009</p>
 * <p>Ultima actualización: 14/04/2009</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class ConsultaProteccionController extends AbstractFacesController {

    private DataTable dataTableProtecciones;
    private ProteccionBO proteccionBO;
    
    /**
     * Constructor por defecto 
     */
    public ConsultaProteccionController(){

    }
    
    public String getInit(){
    	return "success";
    }
    
    /**
     * Obtiene una lista de protecciones
     * @return Lista de protecciones
     */
    public List getProtecciones(){
        List protecciones = this.proteccionBO.getProtecciones();
        return protecciones;
    }
    
    /**
     * Genera el reporte de tipos de protecciones
     * @return success o error
     */
    public String aceptar(){
        try{                    
            FacesContext context = FacesContext.getCurrentInstance();        
            context.getExternalContext().getSessionMap().put("tipoReporte",new Integer(ServletReporte.reporteTiposProteccion));
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
     * Cancela la opción de tipos de protección
     * @return success
     */
    public String cancelar(){
        return "success";
    }

    /**
     * Metodo accesor de dataTableProtecciones.
     * @return Retorna el dataTableProtecciones.
     */
    public DataTable getDataTableProtecciones() {
        return this.dataTableProtecciones;
    }

    /**
     * Metodo modificador de dataTableProtecciones.
     * @param dataTableProtecciones El dataTableProtecciones a modificar.
     */
    public void setDataTableProtecciones(DataTable dataTableProtecciones) {
        this.dataTableProtecciones = dataTableProtecciones;
    }

    /**
     * Metodo modificador de proteccionBO.
     * @param proteccionBO El proteccionBO a modificar.
     */
    public void setProteccionBO(ProteccionBO proteccionBO) {
        this.proteccionBO = proteccionBO;
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
