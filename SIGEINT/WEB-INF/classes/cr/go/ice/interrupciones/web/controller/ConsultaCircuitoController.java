package cr.go.ice.interrupciones.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.CircuitoBO;
import cr.go.ice.interrupciones.domain.Circuito;
import cr.go.ice.interrupciones.domain.SubEstacion;
import cr.go.ice.interrupciones.domain.SubRegion;

/**
 * <p>Clase cr.go.ice.sige.sigecon.web.controller.CircuitoController.java</p>
 * <p>Modulo (subsistema): Sigecon</p>
 * <p>La clase <code>CircuitoController</code> se utiliza para gestionar las acciones de la capa de presentacion 
 * del jsp SigeSigeconConsultaCircuitos.jsp.</p>
 * <p>Fecha creación: 28/01/2008</p>
 * <p>Ultima actualización: 28/01/2008</p>
 * @author Vista Verde Tecnologia (Diego Hernández Pérez)
 * @version 1.0
 */
public class ConsultaCircuitoController extends AbstractFacesController {

    // -------------------- Atributos -------------------- //
    
    //  BO's
    /** atributo <code>subEstacionBO</code>*/
    private CircuitoBO circuitoBO;
    
    
    //  atributos
    /** atributo <code>circuito</code>*/
    private SubRegion subRegion;
    /** atributo <code>subRegion</code>*/
    private SubEstacion subEstacion;
    /** atributo <code>circuito</code>*/
    private Circuito circuito;
    /** atributo <code>circuitos</code>*/
    private List circuitos;
    /** atributo <code>dataTableSubRegiones</code>*/
    private DataTable dataTableCircuitos;
    private Integer buscar;
    
    
    
    /**
     * Constructor
     * 
     */
    public ConsultaCircuitoController(){
        this.circuito = null;
        this.subRegion = new SubRegion();
        this.subEstacion = new SubEstacion();
        this.circuito = new Circuito();
        this.circuitos = new ArrayList();
        this.dataTableCircuitos = new DataTable();
        this.buscar = null;
    }
    
    
    public String getInit(){
    	FacesContext context = FacesContext.getCurrentInstance();
        Object init = context.getExternalContext().getRequestParameterMap().get("init");
        if(init != null)
        {
            this.buscarTodos();
        }
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
     * Metodo accesor de circuitoBO.
     * @return Retorna el circuitoBO.
     */
    public CircuitoBO getCircuitoBO() {
        return this.circuitoBO;
    }
    /**
     * Metodo modificador de circuitoBO.
     * @param circuitoBO El circuitoBO a modificar.
     */
    public void setCircuitoBO(CircuitoBO circuitoBO) {
        this.circuitoBO = circuitoBO;
    }
    /**
     * Metodo accesor de circuitos.
     * @return Retorna el circuitos.
     */
    public List getCircuitos() {
        return this.circuitos;
    }
    /**
     * Metodo modificador de circuitos.
     * @param circuitos El circuitos a modificar.
     */
    public void setCircuitos(List circuitos) {
        this.circuitos = circuitos;
    }
    /**
     * Metodo accesor de dataTableCircuitos.
     * @return Retorna el dataTableCircuitos.
     */
    public DataTable getDataTableCircuitos() {
        return this.dataTableCircuitos;
    }
    /**
     * Metodo modificador de dataTableCircuitos.
     * @param dataTableCircuitos El dataTableCircuitos a modificar.
     */
    public void setDataTableCircuitos(DataTable dataTableCircuitos) {
        this.dataTableCircuitos = dataTableCircuitos;
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
        this.subRegion = (SubRegion) ctx.getApplication().createValueBinding("#{consultaSubRegionController.subRegion}").getValue(ctx);
        this.subEstacion = (SubEstacion) ctx.getApplication().createValueBinding("#{consultaSubEstacionController.subEstacion}").getValue(ctx);
        this.buscar = (Integer) ctx.getApplication().createValueBinding("#{consultaSubEstacionController.buscar}").getValue(ctx);
        
        
        if((this.subEstacion == null) || (this.subEstacion.getCodigoSubEstacion() == null)){
            this.addError(null,"No hay Subestación registrada");
        }
        else{
            this.circuitos = this.circuitoBO.buscar(this.subEstacion.getCodigoSubEstacion());
            if(this.circuitos.isEmpty()){
                this.addError(null,"No hay Circuitos registrados para la Subestación seleccionada");
            }
        }
    }
    
    /**
     * Muestra un nuevo JSP con los Circuitos correspondientes
     * a la sub estación seleccionada
     * @return String "success"
     */
    public String verSecciones(){
        this.circuito = (Circuito) this.dataTableCircuitos.getRowData();
        return "success";
    }
    
    /**
     * Muestra un nuevo JSP con los Circuitos correspondientes
     * a la sub estación seleccionada
     * @return String "success"
     */
    public String abrirSecciones(){
        this.circuito = (Circuito) this.dataTableCircuitos.getRowData();
        return "success";
    }
    
    
    /**
     * Envía a la página anterior
     * @return String "success"
     */
    public String regresar(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        SubRegion s = (SubRegion) ctx.getApplication().createValueBinding("#{consultaSubRegionController.subRegion}").getValue(ctx);
        if(s==null){
            ctx.getApplication().createValueBinding("#{consultaSubEstacionController.inicioBusqueda}").setValue(ctx,Boolean.valueOf(false));
        }
        return "success";
    }
    
    /**
     * Envía a la página anterior
     * @return String "success"
     */
    public String regresarSub(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        SubRegion s = (SubRegion) ctx.getApplication().createValueBinding("#{consultaSubRegionController.subRegion}").getValue(ctx);
        if(s==null){
            ctx.getApplication().createValueBinding("#{consultaSubEstacionController.inicioBusqueda}").setValue(ctx,Boolean.valueOf(false));
        }
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
