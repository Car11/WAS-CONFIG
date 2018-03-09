package cr.go.ice.interrupciones.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.SeccionBO;
import cr.go.ice.interrupciones.domain.*;

/**
 * <p>Clase cr.go.ice.sige.sigecon.web.controller.SeccionController.java</p>
 * <p>Modulo (subsistema): Sigecon</p>
 * <p>La clase <code>SeccionController</code> se utiliza para gestionar las acciones de la capa de presentacion 
 * del jsp SigeSigeconConsultaSubSeccion.jsp.</p>
 * <p>Fecha creación: 25/01/2008</p>
 * <p>Ultima actualización: 25/01/2008</p>
 * @author Vista Verde Tecnologia (Diego Hernández Pérez)
 * @version 1.0
 */
public class ConsultaSeccionController extends AbstractFacesController{

    // -------------------- Atributos -------------------- //
    
    //  BO's
    /** atributo <code>seccionBO</code>*/
    private SeccionBO seccionBO;
    
    
    //  atributos
    /** atributo <code>circuito</code>*/
    private SubRegion subRegion;
    /** atributo <code>subRegion</code>*/
    private SubEstacion subEstacion;
    /** atributo <code>circuito</code>*/
    private Circuito circuito;
    /** atributo <code>seccion</code>*/
    private Seccion seccion;
    /** atributo <code>secciones</code>*/
    private List secciones;
    /** atributo <code>dataTableSubRegiones</code>*/
    private DataTable dataTableSecciones;
    
    private Integer buscar;
    
    
    /**
     * Constructor
     * 
     */
    public ConsultaSeccionController(){
        this.circuito = null;
        this.subRegion = new SubRegion();
        this.subEstacion = new SubEstacion();
        this.circuito = new Circuito();
        this.secciones = new ArrayList();
        this.dataTableSecciones = new DataTable();
        this.buscar = null;
    }
    
    
    public String getInit(){
     return "success";
    }
    
    // -------------------- Setters and Getters -------------------- //
    
    
    /**
     * Metodo accesor de circuito.
     * @return Retorna el circuito.
     */
    public Circuito getCircuito() {
        return this.circuito;
    }
    /**
     * Metodo modificador de circuito.
     * @param circuito El circuito a modificar.
     */
    public void setCircuito(Circuito circuito) {
        this.circuito = circuito;
    }
    /**
     * Metodo accesor de dataTableSecciones.
     * @return Retorna el dataTableSecciones.
     */
    public DataTable getDataTableSecciones() {
        return this.dataTableSecciones;
    }
    /**
     * Metodo modificador de dataTableSecciones.
     * @param dataTableSecciones El dataTableSecciones a modificar.
     */
    public void setDataTableSecciones(DataTable dataTableSecciones) {
        this.dataTableSecciones = dataTableSecciones;
    }
    /**
     * Metodo accesor de seccion.
     * @return Retorna el seccion.
     */
    public Seccion getSeccion() {
        return this.seccion;
    }
    /**
     * Metodo modificador de seccion.
     * @param seccion El seccion a modificar.
     */
    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }
    /**
     * Metodo accesor de seccionBO.
     * @return Retorna el seccionBO.
     */
    public SeccionBO getSeccionBO() {
        return this.seccionBO;
    }
    /**
     * Metodo modificador de seccionBO.
     * @param seccionBO El seccionBO a modificar.
     */
    public void setSeccionBO(SeccionBO seccionBO) {
        this.seccionBO = seccionBO;
    }
    /**
     * Metodo accesor de secciones.
     * @return Retorna el secciones.
     */
    public List getSecciones() {
        return this.secciones;
    }
    /**
     * Metodo modificador de secciones.
     * @param secciones El secciones a modificar.
     */
    public void setSecciones(List secciones) {
        this.secciones = secciones;
    }
    /**
     * Metodo accesor de subEstacion.
     * @return Retorna el subEstacion.
     */
    public SubEstacion getSubEstacion() {
        return this.subEstacion;
    }
    /**
     * Metodo modificador de subEstacion.
     * @param subEstacion El subEstacion a modificar.
     */
    public void setSubEstacion(SubEstacion subEstacion) {
        this.subEstacion = subEstacion;
    }
    /**
     * Metodo accesor de subRegion.
     * @return Retorna el subRegion.
     */
    public SubRegion getSubRegion() {
        return this.subRegion;
    }
    /**
     * Metodo modificador de subRegion.
     * @param subRegion El subRegion a modificar.
     */
    public void setSubRegion(SubRegion subRegion) {
        this.subRegion = subRegion;
    }
    
    
    // -------------------- Métodos de la clase -------------------- //
    
    /**
     * Metodo para iniciar el módulo
     * @param context El contexto faces de la aplicación
     */
    public void init(FacesContext context){
        this.buscarTodos();
    }
    
    /**
     * Método buscarTodos
     * Realiza un listado de todas los pueblos
     */
    private void buscarTodos(){
        
        FacesContext ctx = FacesContext.getCurrentInstance();
        this.buscar = (Integer) ctx.getApplication().createValueBinding("#{consultaCircuitoController.buscar}").getValue(ctx);
        this.subRegion = (SubRegion) ctx.getApplication().createValueBinding("#{consultaSubRegionController.subRegion}").getValue(ctx);
        this.subEstacion = (SubEstacion) ctx.getApplication().createValueBinding("#{consultaSubEstacionController.subEstacion}").getValue(ctx);
        this.circuito = (Circuito) ctx.getApplication().createValueBinding("#{consultaCircuitoController.circuito}").getValue(ctx); 
        
        if((this.circuito == null) || (this.circuito.getCircuitoID().getCircuito() == null)){
          
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No hay curcuitos registrados."));
        }
        else{
            if(this.buscar.equals(Integer.valueOf(1))){
                this.secciones = this.seccionBO.getSecciones(this.subEstacion.getCodigoSubEstacion(),this.circuito.getCircuitoID().getCircuito(), Seccion.ORDEN_NOMBRE);
                if(this.secciones.isEmpty()){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No hay secciones registrados para el circuito seleccionado."));
                }
            }else{
                this.secciones = this.seccionBO.getSeccionesFiltro(this.subRegion.getSubRegionID().getRegion().getRegion(), this.subRegion.getSubRegionID().getSubRegion(), this.subEstacion.getCodigoSubEstacion(), this.circuito.getCircuitoID().getCircuito());
                if(this.secciones.isEmpty()){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No hay secciones registrados para el circuito seleccionado."));
                }
            }
        }
    }
    
    /**
     * Envía a la página anterior
     * @return String "success"
     */
    public String regresar(){
        return "success";
    }
    
    public String regresarCir(){
        return "success";
    }
    
//  -------------------- Métodos específicos del controller -------------------- //
    
    /**
     * Método addError
     * Agrega un mensaje de error a una determinada propiedad
     */
   

    /**
     * Método accesor del atributo buscar.
     * @return Retorna el atributo buscar.
     */
    public Integer getBuscar() {
        return this.buscar;
    }

    /**
     * Método modificador del atributo buscar.
     * @param buscar El dato para modificar el atributo buscar.
     */
    public void setBuscar(Integer buscar) {
        this.buscar = buscar;
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
