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

import cr.go.ice.interrupciones.BO.TipoVoltajeBO;
import cr.go.ice.interrupciones.servlets.ServletReporte;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.ConsultaTipoVoltajeController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web/p>
 * <p>Descricion de <code>ConsultaTipoVoltajeController.java</code> Implementa la consulta de tipos de voltaje.</p>
 * <p>Fecha creación: 28/04/2009</p>
 * <p>Ultima actualización: 28/04/2009</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class ConsultaTipoVoltajeController extends AbstractFacesController {

    private DataTable dataTableTipoVoltajes;
    private TipoVoltajeBO tipoVoltajeBO;
    
    /**
     * Constructor por defecto 
     */
    public ConsultaTipoVoltajeController(){

    }
    
    public String getInit(){
    	return "success";
    }
    /**
     * Obtiene una lista de tipos de voltaje
     * @return Lista de tipos de voltaje
     */
    public List getTipoVoltajes(){
        List voltajes = this.tipoVoltajeBO.getTiposVoltajeOrdenPorTipoVoltaje();
        return voltajes;
    }
    
    /**
     * Genera el reporte de tipos de voltajes
     * @return success o error
     */
    public String aceptar(){
        try{                    
            FacesContext context = FacesContext.getCurrentInstance();        
            context.getExternalContext().getSessionMap().put("tipoReporte",new Integer(ServletReporte.reporteTiposVoltaje));
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
     * Cancela la opción de tipos de voltaje
     * @return success
     */
    public String cancelar(){
        return "success";
    }

    /**
     * Metodo accesor de dataTableTipoVoltajes.
     * @return Retorna el dataTableTipoVoltajes.
     */
    public DataTable getDataTableTipoVoltajes() {
        return this.dataTableTipoVoltajes;
    }

    /**
     * Metodo modificador de dataTableTipoVoltajes.
     * @param dataTableTipoVoltajes El dataTableTipoVoltajes a modificar.
     */
    public void setDataTableTipoVoltajes(DataTable dataTableTipoVoltajes) {
        this.dataTableTipoVoltajes = dataTableTipoVoltajes;
    }

    /**
     * Metodo modificador de tipoVoltajeBO.
     * @param tipoVoltajeBO El tipoVoltajeBO a modificar.
     */
    public void setTipoVoltajeBO(TipoVoltajeBO tipoVoltajeBO) {
        this.tipoVoltajeBO = tipoVoltajeBO;
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
