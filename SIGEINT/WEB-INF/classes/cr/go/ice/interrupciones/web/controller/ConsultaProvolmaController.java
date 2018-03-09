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

import cr.go.ice.interrupciones.BO.ProvolmaBO;
import cr.go.ice.interrupciones.servlets.ServletReporte;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.ConsultaProvolmaController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web/p>
 * <p>Descricion de <code>ConsultaProvolmaController.java</code> Implementa la consulta de tipos de provolma.</p>
 * <p>Fecha creación: 28/04/2009</p>
 * <p>Ultima actualización: 28/04/2009</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class ConsultaProvolmaController extends AbstractFacesController {

    private DataTable dataTableProvolmas;
    private ProvolmaBO provolmaBO;
    
    /**
     * Constructor por defecto 
     */
    public ConsultaProvolmaController(){

    }
    public String getInit(){
    	return "success";
    }
    
    /**
     * Obtiene una lista de tipos de provolma
     * @return Lista de tipos de provolma
     */
    public List getProvolmas(){
        List provolmas = this.provolmaBO.getProvolmas();
        return provolmas;
    }
    
    /**
     * Genera el reporte de tipos de provolma
     * @return success o error
     */
    public String aceptar(){
        try{                    
            FacesContext context = FacesContext.getCurrentInstance();        
            context.getExternalContext().getSessionMap().put("tipoReporte",new Integer(ServletReporte.reporteTiposProvolma));
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
     * Cancela la opción de tipos de provolma
     * @return success
     */
    public String cancelar(){
        return "success";
    }

    /**
     * Metodo accesor de dataTableProvolmas.
     * @return Retorna el dataTableProvolmas.
     */
    public DataTable getDataTableProvolmas() {
        return this.dataTableProvolmas;
    }

    /**
     * Metodo modificador de dataTableProvolmas.
     * @param dataTableProvolmas El dataTableProvolmas a modificar.
     */
    public void setDataTableProvolmas(DataTable dataTableProvolmas) {
        this.dataTableProvolmas = dataTableProvolmas;
    }

    /**
     * Metodo modificador de provolmaBO.
     * @param provolmaBO El provolmaBO a modificar.
     */
    public void setProvolmaBO(ProvolmaBO provolmaBO) {
        this.provolmaBO = provolmaBO;
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
