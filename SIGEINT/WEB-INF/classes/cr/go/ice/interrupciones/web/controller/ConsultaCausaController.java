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

import cr.go.ice.interrupciones.BO.CausaBO;
import cr.go.ice.interrupciones.servlets.ServletReporte;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.ConsultaCausaController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web/p>
 * <p>Descricion de <code>ConsultaCausaController.java</code> Implementa la consulta de tipos de causa.</p>
 * <p>Fecha creación: 24/03/2009</p>
 * <p>Ultima actualización: 24/03/2009</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class ConsultaCausaController extends AbstractFacesController {

    private DataTable dataTableCausas;
    private CausaBO causaBO;
    
    /**
     * Constructor por defecto 
     */
    public ConsultaCausaController(){

    }
    
    public String getInit(){
    	return "success";
    }
    
    /**
     * Obtiene una lista de causas
     * @return Lista de causas
     */
    public List getCausas(){
        List causas = this.causaBO.getCausas();
        return causas;
    }
    
    /**
     * Genera el reporte de tipos de causa
     * @return success o error
     */
    public String aceptar(){
        try{                    
            FacesContext context = FacesContext.getCurrentInstance();        
            context.getExternalContext().getSessionMap().put("tipoReporte",new Integer(ServletReporte.reporteTiposCausa));
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
     * Cancela la opción de tipos de causa
     * @return success
     */
    public String cancelar(){
        return "success";
    }

    /**
     * Metodo accesor de dataTableCausas.
     * @return Retorna el dataTableCausas.
     */
    public DataTable getDataTableCausas() {
        return this.dataTableCausas;
    }

    /**
     * Metodo modificador de dataTableCausas.
     * @param dataTableCausas El dataTableCausas a modificar.
     */
    public void setDataTableCausas(DataTable dataTableCausas) {
        this.dataTableCausas = dataTableCausas;
    }

    /**
     * Metodo modificador de causaBO.
     * @param causaBO El causaBO a modificar.
     */
    public void setCausaBO(CausaBO causaBO) {
        this.causaBO = causaBO;
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
