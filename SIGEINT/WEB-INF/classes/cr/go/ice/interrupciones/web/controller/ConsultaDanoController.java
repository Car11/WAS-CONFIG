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

import cr.go.ice.interrupciones.BO.DanoBO;
import cr.go.ice.interrupciones.servlets.ServletReporte;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.ConsultaDanoController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web/p>
 * <p>Descricion de <code>ConsultaDanoController.java</code> Implementa la consulta de tipos de daño.</p>
 * <p>Fecha creación: 13/04/2009</p>
 * <p>Ultima actualización: 13/04/2009</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class ConsultaDanoController extends AbstractFacesController {

    private DataTable dataTableDanos;
    private DanoBO danoBO;
    
    /**
     * Constructor por defecto 
     */
    public ConsultaDanoController(){

    }
    
    /**
     * Obtiene una lista de daños
     * @return Lista de daños
     */
    public List getDanos(){
        List danos = this.danoBO.getDanos();
        return danos;
    }
    
    /**
     * Genera el reporte de tipos de daño
     * @return success o error
     */
    public String aceptar(){
        try{                    
            FacesContext context = FacesContext.getCurrentInstance();        
            context.getExternalContext().getSessionMap().put("tipoReporte",new Integer(ServletReporte.reporteTiposDano));
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
     * Cancela la opción de tipos de daño
     * @return success
     */
    public String cancelar(){
        return "success";
    }

    /**
     * Metodo accesor de dataTableDanos.
     * @return Retorna el dataTableDanos.
     */
    public DataTable getDataTableDanos() {
        return this.dataTableDanos;
    }

    /**
     * Metodo modificador de dataTableDanos.
     * @param dataTableDanos El dataTableDanos a modificar.
     */
    public void setDataTableDanos(DataTable dataTableDanos) {
        this.dataTableDanos = dataTableDanos;
    }

    /**
     * Metodo modificador de danoBO.
     * @param danoBO El danoBO a modificar.
     */
    public void setDanoBO(DanoBO danoBO) {
        this.danoBO = danoBO;
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
