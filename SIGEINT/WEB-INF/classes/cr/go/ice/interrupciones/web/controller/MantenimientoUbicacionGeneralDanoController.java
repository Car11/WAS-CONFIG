package cr.go.ice.interrupciones.web.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.dao.DataIntegrityViolationException;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.UbicacionGeneralDanoBO;
import cr.go.ice.interrupciones.domain.UbicacionGeneralDano;

public class MantenimientoUbicacionGeneralDanoController extends AbstractFacesController {
	
	private UbicacionGeneralDanoBO ubicacionGeneralDanoBO;
	
	private Integer codigo;
	
	private String descripcion;
	
	private Integer estado;
	
	private Integer estadoActivo = new Integer(0);
	
	private Integer estadoInactivo = new Integer(1);

	public MantenimientoUbicacionGeneralDanoController()
	{
		this.codigo = new Integer(0);
		this.descripcion = "";
		this.estado = new Integer(0);
	}
	
	public String getInit(){
		return "success";
	}
	
	
	public boolean validarAgregar()
	{
		FacesMessage msg = new FacesMessage();
		boolean resultado = true;
		if(this.codigo ==null || this.codigo.equals(Integer.valueOf(0)))
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar el Código."));
			resultado = false;
			return resultado;
		}
		if(this.descripcion=="" || this.descripcion.length() == Integer.valueOf(0))
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar la Descripción."));
			resultado = false;
			return resultado;
		}
		return resultado;
	}
	public boolean validarEliminar()
	{
		FacesMessage msg = new FacesMessage();
		boolean resultado = true;
		if(this.codigo ==null || this.codigo.equals(Integer.valueOf(0)))
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar el Código."));
			resultado = false;
		}
		return resultado;
	}
	
	public String agregar()
	{
		FacesMessage msg = new FacesMessage();
		try
		{
			if(this.validarAgregar())
			{
				if(!this.ubicacionGeneralDanoBO.existe(this.codigo))
				{
					UbicacionGeneralDano temp = new UbicacionGeneralDano();
					temp.setCodigo(this.codigo);
					temp.setDescripcion(this.descripcion);
					temp.setEstado(this.estado);
					this.ubicacionGeneralDanoBO.agregar(temp);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Se agregó con éxito."));
		            this.limpiar();
				}else
				{
					this.descripcion="";
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La Ubicación General del Daño ya existe."));
				}
				
			}
		}catch(Exception e)
		{
			this.descripcion="";
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ocurrió un error."));
		}
		return "success";
	}
	
	public String modificar()
	{
		FacesMessage msg = new FacesMessage();
		try
		{
			if(this.validarAgregar())
			{
				if(this.ubicacionGeneralDanoBO.existe(this.codigo))
				{
					UbicacionGeneralDano temp = new UbicacionGeneralDano();
					temp.setCodigo(this.codigo);
					temp.setDescripcion(this.descripcion);
					temp.setEstado(this.estado);
					this.ubicacionGeneralDanoBO.modificar(temp);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Se modificó con éxito."));
		            this.limpiar();
				}else
				{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No existe la Ubicación General del Daño."));
				}
				
			}
		}catch(Exception e)
		{
			this.descripcion="";
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ocurrió un error."));
		}
		return "success";
	}
	
	public String eliminar()
	{
		FacesMessage msg = new FacesMessage();
		try
		{
			if(this.validarEliminar())
			{
				if(this.ubicacionGeneralDanoBO.existe(this.codigo))
				{
					UbicacionGeneralDano temp = new UbicacionGeneralDano();
					temp.setCodigo(this.codigo);
					this.ubicacionGeneralDanoBO.eliminar(temp);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Se eliminó con éxito."));
		            this.limpiar();
				}else
				{
					this.descripcion="";
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No existe la Ubicación General del Daño."));
				}
			}
		}catch(DataIntegrityViolationException e)
		{
			this.descripcion="";
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El registro tiene información asociada."));
            return "success";
		}catch(Exception e)
		{
			this.descripcion="";
			e.printStackTrace();
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ocurrió un error."));
		}
		return "success";
	}
	
	public String buscar()
	{
		FacesMessage msg = new FacesMessage();
		try
		{
			if(this.validarEliminar())
			{
				UbicacionGeneralDano temp = new UbicacionGeneralDano();
				UbicacionGeneralDano tempBusqueda = new UbicacionGeneralDano();
				temp.setCodigo(this.codigo);
				tempBusqueda= this.ubicacionGeneralDanoBO.buscar(temp);
				if(tempBusqueda.getCodigo()!=null && !tempBusqueda.getCodigo().equals(Integer.valueOf(0)))
				{
					this.codigo = tempBusqueda.getCodigo();
					this.descripcion = tempBusqueda.getDescripcion();
					this.estado = tempBusqueda.getEstado();
//					msg.setSeverity(FacesMessage.SEVERITY_INFO);
//		            msg.setDetail(null);
//		            FacesContext.getCurrentInstance().addMessage("form1:codigo",msg);
		            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", ""));
				}else
				{
					this.descripcion="";
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se encontró la Ubicación General del Daño."));
				}
			}
		}catch(Exception e)
		{
			this.descripcion="";
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ocurrió un error."));
		}
		return "success";
	}
	
	public String cancelar()
	{
		this.codigo = new Integer(0);
		this.descripcion = "";
		this.estado = new Integer(0);
		return "success";
	}
	
	public String limpiar()
	{
		this.codigo = new Integer(0);
		this.descripcion = "";
		this.estado = new Integer(0);
		return "success";
	}
	
	
	public UbicacionGeneralDanoBO getUbicacionGeneralDanoBO() {
		return ubicacionGeneralDanoBO;
	}

	public void setUbicacionGeneralDanoBO(UbicacionGeneralDanoBO ubicacionGeneralDanoBO) {
		this.ubicacionGeneralDanoBO = ubicacionGeneralDanoBO;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	public Integer getEstadoActivo() {
		return estadoActivo;
	}
	public void setEstadoActivo(Integer estadoActivo) {
		this.estadoActivo = estadoActivo;
	}
	public Integer getEstadoInactivo() {
		return estadoInactivo;
	}
	public void setEstadoInactivo(Integer estadoInactivo) {
		this.estadoInactivo = estadoInactivo;
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
