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

import cr.go.ice.interrupciones.BO.OficinaBO;
import cr.go.ice.interrupciones.servlets.ServletReporte;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.ConsultaOficinaController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web/p>
 * <p>Descricion de <code>ConsultaOficinaController.java</code> Implementa la consulta de oficinas.</p>
 * <p>Fecha creación: 16/04/2009</p>
 * <p>Ultima actualización: 16/04/2009</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class ConsultaOficinaController extends AbstractFacesController{

    private DataTable dataTableOficinas;
    private OficinaBO oficinaBO;
    
    /**
     * Constructor por defecto 
     */
    public ConsultaOficinaController(){

    }
    
    /**
     * Obtiene una lista de tipos de oficina
     * @return Lista de tipos de oficina
     */
    public List getOficinas(){
        List oficinas = this.oficinaBO.getOficinas();
        return oficinas;
    }
    
    /**
     * Genera el reporte de tipos de oficina
     * @return success o error
     */
    public String aceptar(){
        try{                    
            FacesContext context = FacesContext.getCurrentInstance();        
            context.getExternalContext().getSessionMap().put("tipoReporte",new Integer(ServletReporte.reporteTiposOficina));
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
     * Cancela la opción de tipos de oficina
     * @return success
     */
    public String cancelar(){
        return "success";
    }

    /**
     * Metodo accesor de dataTableOficinas.
     * @return Retorna el dataTableOficinas.
     */
    public DataTable getDataTableOficinas() {
        return this.dataTableOficinas;
    }

    /**
     * Metodo modificador de dataTableOficinas.
     * @param dataTableOficinas El dataTableOficinas a modificar.
     */
    public void setDataTableOficinas(DataTable dataTableOficinas) {
        this.dataTableOficinas = dataTableOficinas;
    }

    /**
     * Metodo modificador de oficinaBO.
     * @param oficinaBO El oficinaBO a modificar.
     */
    public void setOficinaBO(OficinaBO oficinaBO) {
        this.oficinaBO = oficinaBO;
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
