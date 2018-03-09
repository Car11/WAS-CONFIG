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

import cr.go.ice.interrupciones.BO.AnimalBO;
import cr.go.ice.interrupciones.servlets.ServletReporte;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.ConsultaAnimalController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web/p>
 * <p>Descricion de <code>ConsultaAnimalController.java</code> Implementa la consulta de tipos de animales.</p>
 * <p>Fecha creación: 16/04/2009</p>
 * <p>Ultima actualización: 16/04/2009</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class ConsultaAnimalController extends AbstractFacesController{

    private DataTable dataTableAnimales;
    private AnimalBO animalBO;
    
    /**
     * Constructor por defecto 
     */
    public ConsultaAnimalController(){

    }
    
    public String getInit(){
    	return "success";
    }
    
    /**
     * Obtiene una lista de tipos de animal
     * @return Lista de tipos de animal
     */
    public List getAnimales(){
        List animales = this.animalBO.getAnimales();
        return animales;
    }
    
    /**
     * Genera el reporte de tipos de animal
     * @return success o error
     */
    public String aceptar(){
        try{                    
            FacesContext context = FacesContext.getCurrentInstance();        
            context.getExternalContext().getSessionMap().put("tipoReporte",new Integer(ServletReporte.reporteTiposAnimal));
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
     * Cancela la opción de tipos de animal
     * @return success
     */
    public String cancelar(){
        return "success";
    }

    /**
     * Metodo accesor de dataTableAnimales.
     * @return Retorna el dataTableAnimales.
     */
    public DataTable getDataTableAnimales() {
        return this.dataTableAnimales;
    }

    /**
     * Metodo modificador de dataTableAnimales.
     * @param dataTableAnimales El dataTableAnimales a modificar.
     */
    public void setDataTableAnimales(DataTable dataTableAnimales) {
        this.dataTableAnimales = dataTableAnimales;
    }

    /**
     * Metodo modificador de animalBO.
     * @param animalBO El animalBO a modificar.
     */
    public void setAnimalBO(AnimalBO animalBO) {
        this.animalBO = animalBO;
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
