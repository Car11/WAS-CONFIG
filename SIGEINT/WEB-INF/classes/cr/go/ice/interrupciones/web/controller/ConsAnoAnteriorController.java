package cr.go.ice.interrupciones.web.controller;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.ConsecutivoClorBO;
import cr.go.ice.interrupciones.BO.OficinaBO;
import cr.go.ice.interrupciones.domain.ConsecutivoClor;
import cr.go.ice.interrupciones.domain.ConsecutivoClorID;
import cr.go.ice.interrupciones.domain.Oficina;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.ConsAnoAnteriorController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ConsAnoAnteriorController.java</code> Establece la relacion entre capa de presentacion y la logica del negocio.</p>
 * <p>Fecha creación: 17/03/2008</p>
 * <p>Ultima actualización: 17/03/2008</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class ConsAnoAnteriorController  extends AbstractFacesController{
        
    private String habilitar;
    private ConsecutivoClorBO consecutivoClorBO;
    private OficinaBO oficinaBO;    
    
    private ConsecutivoClor consecutivo;

    
    /**
     * Constructor por defecto
     */
    public ConsAnoAnteriorController(){
        reiniciarCampos();
    }
    
    public String getInit(){
    	return "success";
    }
    private void reiniciarCampos(){
        this.habilitar = ConsecutivoClor.ABRIR_ANO_SI;
        
        consecutivo = new ConsecutivoClor();
        ConsecutivoClorID consecutivoID = new ConsecutivoClorID();
        consecutivoID.setCodigoOficina(new Integer(0));
        GregorianCalendar gc = new GregorianCalendar(); 
        Integer anoAnterior = new Integer(gc.get(GregorianCalendar.YEAR) - 1);   
        consecutivoID.setAno(anoAnterior);
        consecutivo.setConsecutivoClorID(consecutivoID);
    }
    
    /**
     * Lista de oficinas
     * @return Lista Oficinas
     */
    public List getOficinasSelectItems(){
        List selectItems = new ArrayList();
        List oficinas = this.oficinaBO.getOficinas();
        
        selectItems.add(new SelectItem(new Integer(0), "Seleccione una oficina"));
        
        for(int i=0; i<oficinas.size();i++){
            Oficina oficina = (Oficina) oficinas.get(i);
            selectItems.add(new SelectItem(oficina.getCodigoOficina(), oficina.getCodigoOficina() +" - "+ oficina.getNombreOficina()));
        }
        return selectItems;
    }
    
    /**
     * Lista de opciones para habilitar
     * @return lista Habilitar SelectItems
     */
    public List getHabilitarSelectItems(){
        List items = new ArrayList();
        items.add(new SelectItem(ConsecutivoClor.ABRIR_ANO_SI, "Si"));
        items.add(new SelectItem(ConsecutivoClor.ABRIR_ANO_NO, "No"));
        return items;
    }
    
    /**
     * Limpia la página al cargarse
     * @param context
     */
    public void load(FacesContext context){
        Object limpiar = context.getExternalContext().getRequestParameterMap().get("limpiar");
        if(limpiar != null)
            reiniciarCampos();        
    }
    
    /**
     * Comment for listenerOficina
     * @param v
     * @return "listener"
     */
    public String listenerOficina(){
        Integer oficina = new Integer(0);
        
        if(oficina.intValue() <= 0){
            this.consecutivo.setAbrirAno("");
        }else{
            GregorianCalendar gc = new GregorianCalendar(); 
            Integer anoAnterior = new Integer(gc.get(GregorianCalendar.YEAR) - 1);              
            ConsecutivoClor consecutivo = new ConsecutivoClor();
            ConsecutivoClorID consecutivoID = new ConsecutivoClorID();
            consecutivoID.setCodigoOficina(oficina);   
            consecutivoID.setAno(anoAnterior);
            consecutivo.setConsecutivoClorID(consecutivoID); 

            ConsecutivoClor consecutivoResultado = this.consecutivoClorBO.buscar(consecutivo);
            if(consecutivoResultado != null)
                this.consecutivo = consecutivoResultado;
            else{
                consecutivo.setAbrirAno(ConsecutivoClor.ABRIR_ANO_NO);
                this.consecutivoClorBO.agregar(consecutivo);
                this.consecutivo = consecutivo;
            }
        }
        
        return "listener";
    }
    
    private boolean validarDatos(){
       
        GregorianCalendar gc = new GregorianCalendar();  
        int mesActual = gc.get(GregorianCalendar.MONTH);
        if(mesActual != 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!","La modificación solo es posible en el mes de enero."));
            return false;
        }
        
        if(this.consecutivo.getConsecutivoClorID().getCodigoOficina() == null || this.consecutivo.getConsecutivoClorID().getCodigoOficina().intValue() <= 0){           
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!","La oficina es requerida."));

            return false;
        }

        return true;
    }
    
    /**
     * Realiza la modificación del parametro
     * @return success o error
     */
    public String cambioParametro(){
        if(validarDatos()){     
            this.consecutivo.setAbrirAno(this.habilitar);
            this.consecutivoClorBO.modificar(this.consecutivo);            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!","Actualización exitosa."));
            return "success";
        }else{
            return "error";
        }
    }
    
    
    /**
     * Cancela
     * @return success
     */
    public String cancelar(){
        return "success";
    }
    
    /**
     * Metodo accesor de habilitar.
     * @return Retorna el habilitar.
     */
    public String getHabilitar() {
        return this.habilitar;
    }
    /**
     * Metodo modificador de habilitar.
     * @param habilitar El habilitar a modificar.
     */
    public void setHabilitar(String habilitar) {
        this.habilitar = habilitar;
    }
    /**
     * Metodo modificador de consecutivoClorBO.
     * @param consecutivoClorBO El consecutivoClorBO a modificar.
     */
    public void setConsecutivoClorBO(ConsecutivoClorBO consecutivoClorBO) {
        this.consecutivoClorBO = consecutivoClorBO;
    }
    /**
     * Metodo modificador de oficinaBO.
     * @param oficinaBO El oficinaBO a modificar.
     */
    public void setOficinaBO(OficinaBO oficinaBO) {
        this.oficinaBO = oficinaBO;
    }
    /**
     * Metodo accesor de consecutivo.
     * @return Retorna el consecutivo.
     */
    public ConsecutivoClor getConsecutivo() {
        return this.consecutivo;
    }
    /**
     * Metodo modificador de consecutivo.
     * @param consecutivo El consecutivo a modificar.
     */
    public void setConsecutivo(ConsecutivoClor consecutivo) {
        this.consecutivo = consecutivo;
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
