package cr.go.ice.interrupciones.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.component.datatable.DataTable;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.EmpleadoBO;
import cr.go.ice.interrupciones.BO.OficinaBO;
import cr.go.ice.interrupciones.BO.UsuarioOficinaBO;
import cr.go.ice.interrupciones.domain.Empleado;
import cr.go.ice.interrupciones.domain.Oficina;
import cr.go.ice.interrupciones.domain.UsuarioOficina;

public class MantenimientoUsuarioOficinaController extends AbstractFacesController{
	
	private UsuarioOficinaBO usuarioOficinaBO;
	private OficinaBO oficinaBO;
	private EmpleadoBO empleadoBO;
	
	private Empleado empleadoSeleccionado;
	
	private Integer cedula;
	private Integer cedulaBuscar;
	private Integer codigoOficina;
	
	private String nombre;
	private String nombreBuscar;
	private String criterioBusqueda;
	
	private DataTable dataTableOficinas;
	private DataTable dataTableEmpleados;
	
	private List<UsuarioOficina> listaOficinas;
	private List<Empleado> listaEmpleados;
	@SuppressWarnings("rawtypes")
	private List listaOficinasSeleccionadas;
	
	
	@SuppressWarnings("rawtypes")
	public MantenimientoUsuarioOficinaController()
	{
		this.cedula = null;
		this.cedulaBuscar = null;
		this.codigoOficina = 0;
		this.nombre = "";
		this.nombreBuscar= "";
		this.criterioBusqueda ="";
		this.listaOficinas = new ArrayList<UsuarioOficina>();
		this.listaEmpleados = new ArrayList<Empleado>();
		this.listaOficinasSeleccionadas = new ArrayList();
		this.empleadoSeleccionado = new Empleado();
	}
	
	public String init(FacesContext context)
	{
		String nombreUsuarioSession = (String) context.getApplication().createValueBinding("#{autorizacionController.nombreUsuario}").getValue(this.getFacesContext());
        String[] valores = nombreUsuarioSession.split("-");
        this.cedula = new Integer(valores[0].trim());
		System.out.println("-\n----"+this.cedula);
		this.buscarTodos();
        if(this.cedula==null || this.cedula==0)
        {
        	if(this.nombre ==null || this.nombre=="" || this.nombre.length()==0)
        	{
        		if(this.codigoOficina==null || this.codigoOficina==0)
        		{
        			this.buscarTodos();
        		}
        	}
        }
        return "success";
	}
	
	@SuppressWarnings("rawtypes")
	public void resetController()
	{
		this.cedula = null;
		this.cedulaBuscar = null;
		this.codigoOficina = 0;
		this.nombre = "";
		this.nombreBuscar = "";
		this.criterioBusqueda ="";
		this.listaOficinas = new ArrayList<UsuarioOficina>();
		this.listaEmpleados = new ArrayList<Empleado>();
		this.listaOficinasSeleccionadas = new ArrayList();
		this.empleadoSeleccionado = new Empleado();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getOficinasSelectItems(){
    	List selectItems = new ArrayList();
    	List oficinas = this.oficinaBO.getOficinas();
    	
    	for(int i=0; i<oficinas.size();i++){
    		Oficina oficina = (Oficina) oficinas.get(i);
    		selectItems.add(new SelectItem(oficina.getCodigoOficina(), oficina.getCodigoOficina() +" - "+ oficina.getNombreOficina()));
    	}
    	return selectItems;
    }
	
	public String buscar()
	{
		this.listaOficinas.clear();
		
		if(this.cedula==null)
		{
			this.cedula = 0;
		}
		if(this.nombre ==null)
		{
			this.nombre = "";
		}
		this.listaOficinas.addAll(this.usuarioOficinaBO.buscarCedulaNombreOficina(this.cedula.longValue(), this.nombre, this.codigoOficina));
		return "success";
	}
	
	public String buscarCedula()
	{
		
		if(this.validarCriterioNumerico())
		{
			this.listaEmpleados.clear();
			Integer cedula = Integer.parseInt(this.criterioBusqueda);
			this.listaEmpleados.add( this.empleadoBO.buscar(cedula.longValue()) );
		}else
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingrese solamente números."));
		}
		return "success";
	}
	
	@SuppressWarnings("unchecked")
	public String buscarNombre()
	{
		this.listaEmpleados.clear();
		this.listaEmpleados.addAll( this.empleadoBO.getEmpleadosTodos(this.criterioBusqueda) );
		return "success";
	}
	
	public String buscarTodos()
	{
		this.listaOficinas.clear();
		this.listaOficinas.addAll(this.usuarioOficinaBO.buscarTodos());
		return "success";
	}
	
	public String guardar()
	{		
		
		try
		{
			if(this.validarAgregar())
			{
				if(this.usuarioOficinaBO.existeCedula(this.cedulaBuscar))
				{
					this.usuarioOficinaBO.eliminarPorCedula(this.cedulaBuscar);
				}
				for(int i=0; i<this.listaOficinasSeleccionadas.size(); i++)
				{
					UsuarioOficina pivote = new UsuarioOficina();
					pivote.getId().getEmpleado().setCedula(this.cedulaBuscar.longValue());
					pivote.getId().getOficina().setCodigoOficina( Integer.parseInt(this.listaOficinasSeleccionadas.get(i).toString()) );
					this.usuarioOficinaBO.agregar(pivote);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Se agregó con éxito."));
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ocurrió un error."));
		}
		return "success";
	}
	
	public String agregar()
	{
		return "success";
	}
	
	public String regresar()
	{
		this.resetController();
		return "success";
	}
	
	public String cancelar()
	{
		this.resetController();
		return "success";
	}
	
	public String seleccionarEmpleado()
	{
		this.empleadoSeleccionado = (Empleado) this.dataTableEmpleados.getRowData();
		this.cedulaBuscar = this.empleadoSeleccionado.getCedula().intValue();
		this.nombreBuscar = this.empleadoSeleccionado.getNombreCompleto();
		return "jeniaClosePopupFrameWithAction";
	}
	
	@SuppressWarnings("unchecked")
	public String abrirPopUpUsuarios()
	{
		this.listaEmpleados = this.empleadoBO.getEmpleados();
		return "success";
	}

	public String cerrarPopUpUsuarios()
	{
		this.listaEmpleados.clear();
		return "success";
	}
	
	public boolean validarCriterioNumerico()
	{
		try
		{
			Integer.parseInt(this.criterioBusqueda);
			return true;
		}catch(NumberFormatException nfe)
		{
			return false;
		}
	}
	
	public boolean validarAgregar()
	{
		
		if(this.listaOficinasSeleccionadas.isEmpty())
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Seleccione al menos una oficina."));
            return false;
		}
		if(this.cedulaBuscar==null || this.cedulaBuscar<Integer.valueOf(1))
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingrese un número de cédula."));
            return false;
		}
		if(this.nombreBuscar ==""|| this.nombreBuscar.length()==0)
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El usuario no existe."));
            return false;
		}
		return true;
	}

	public UsuarioOficinaBO getUsuarioOficinaBO() {
		return usuarioOficinaBO;
	}

	public void setUsuarioOficinaBO(UsuarioOficinaBO usuarioOficinaBO) {
		this.usuarioOficinaBO = usuarioOficinaBO;
	}

	public OficinaBO getOficinaBO() {
		return oficinaBO;
	}

	public void setOficinaBO(OficinaBO oficinaBO) {
		this.oficinaBO = oficinaBO;
	}

	public Integer getCedula() {
		return cedula;
	}

	public void setCedula(Integer cedula) {
		this.cedula = cedula;
	}

	public Integer getCedulaBuscar() {
		return cedulaBuscar;
	}

	public void setCedulaBuscar(Integer cedulaBuscar) {
		this.cedulaBuscar = cedulaBuscar;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@SuppressWarnings("unchecked")
	public String getNombreBuscar()
	{
		this.listaOficinasSeleccionadas.clear();
		if(this.cedulaBuscar!=null && this.cedulaBuscar!=0)
		{
			Empleado pivote = new Empleado();
			pivote.setCedula(this.cedulaBuscar.longValue());
			boolean existe = this.empleadoBO.existe(pivote);
			if(existe)
			{
				pivote = this.empleadoBO.buscar(this.cedulaBuscar.longValue());
				this.nombreBuscar = pivote.getNombreCompleto();
				List<UsuarioOficina> temporal = this.usuarioOficinaBO.buscarCedula(this.cedulaBuscar.longValue());
				for(int us = 0; us<temporal.size(); us++)
				{
					this.listaOficinasSeleccionadas.add(temporal.get(us).getId().getOficina().getCodigoOficina());
				}
			}else
			{
				this.nombreBuscar = "";
			}
		}else
		{
			this.nombreBuscar = "";
		}
		return nombreBuscar;
	}

	public void setNombreBuscar(String nombreBuscar) {
		this.nombreBuscar = nombreBuscar;
	}

	public Integer getCodigoOficina() {
		return codigoOficina;
	}

	public void setCodigoOficina(Integer codigoOficina) {
		this.codigoOficina = codigoOficina;
	}

	public DataTable getDataTableOficinas() {
		return dataTableOficinas;
	}

	public void setDataTableOficinas(DataTable dataTableOficinas) {
		this.dataTableOficinas = dataTableOficinas;
	}

	public List<UsuarioOficina> getListaOficinas() {
		return listaOficinas;
	}

	public void setListaOficinas(List<UsuarioOficina> listaOficinas) {
		this.listaOficinas = listaOficinas;
	}

	@SuppressWarnings("rawtypes")
	public List getListaOficinasSeleccionadas() {
		return listaOficinasSeleccionadas;
	}

	@SuppressWarnings("rawtypes")
	public void setListaOficinasSeleccionadas(List listaOficinasSeleccionadas) {
		this.listaOficinasSeleccionadas = listaOficinasSeleccionadas;
	}

	public String getCriterioBusqueda() {
		return criterioBusqueda;
	}

	public void setCriterioBusqueda(String criterioBusqueda) {
		this.criterioBusqueda = criterioBusqueda;
	}

	public EmpleadoBO getEmpleadoBO() {
		return empleadoBO;
	}

	public void setEmpleadoBO(EmpleadoBO empleadoBO) {
		this.empleadoBO = empleadoBO;
	}

	public DataTable getDataTableEmpleados() {
		return dataTableEmpleados;
	}

	public void setDataTableEmpleados(DataTable dataTableEmpleados) {
		this.dataTableEmpleados = dataTableEmpleados;
	}

	public List<Empleado> getListaEmpleados() {
		return listaEmpleados;
	}

	public void setListaEmpleados(List<Empleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}

	public Empleado getEmpleadoSeleccionado() {
		return empleadoSeleccionado;
	}

	public void setEmpleadoSeleccionado(Empleado empleadoSeleccionado) {
		this.empleadoSeleccionado = empleadoSeleccionado;
	}

	@Override
	protected String getPropertyFieldName(String property) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
