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

import cr.go.ice.interrupciones.BO.CorreoBO;
import cr.go.ice.interrupciones.servlets.ServletReporte;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.ConsultaCorreoController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web/p>
 * <p>Descricion de <code>ConsultaCorreoController.java</code> Implementa la consulta de tipos de correos.</p>
 * <p>Fecha creación: 20/04/2009</p>
 * <p>Ultima actualización: 20/04/2009</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class ConsultaCorreoController extends AbstractFacesController {

    private DataTable dataTableCorreos;
    private CorreoBO correoBO;
    
    /**
     * Constructor por defecto 
     */
    public ConsultaCorreoController(){

    }
    
    
    public String getInit(){
    	return "success";
    }
    /**
     * Obtiene una lista de tipos de correo
     * @return Lista de tipos de correo
     */
    public List getCorreos(){
        List correos = this.correoBO.getCorreos();
        return correos;
    }
    
    /**
     * Genera el reporte de tipos de correo
     * @return success o error
     */
    public String aceptar(){
        try{                    
            FacesContext context = FacesContext.getCurrentInstance();        
            context.getExternalContext().getSessionMap().put("tipoReporte",new Integer(ServletReporte.reporteTiposCorreo));
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
     * Cancela la opción de tipos de correo
     * @return success
     */
    public String cancelar(){
        return "success";
    }

    /**
     * Metodo accesor de dataTableCorreos.
     * @return Retorna el dataTableCorreos.
     */
    public DataTable getDataTableCorreos() {
        return this.dataTableCorreos;
    }

    /**
     * Metodo modificador de dataTableCorreos.
     * @param dataTableCorreos El dataTableCorreos a modificar.
     */
    public void setDataTableCorreos(DataTable dataTableCorreos) {
        this.dataTableCorreos = dataTableCorreos;
    }

    /**
     * Metodo modificador de correoBO.
     * @param correoBO El correoBO a modificar.
     */
    public void setCorreoBO(CorreoBO correoBO) {
        this.correoBO = correoBO;
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
