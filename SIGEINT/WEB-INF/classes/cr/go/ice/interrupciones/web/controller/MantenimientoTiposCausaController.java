package cr.go.ice.interrupciones.web.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.dao.DataIntegrityViolationException;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.TiposCausaBO;
import cr.go.ice.interrupciones.domain.TiposCausa;

public class MantenimientoTiposCausaController extends AbstractFacesController {
	
	private TiposCausaBO tiposCausaBO;
	
	private Integer codigo;
	
	private String descripcion;
	
	private Integer estado;
	
	private Integer estadoActivo = new Integer(0);
	
	private Integer estadoInactivo = new Integer(1);
	
	public MantenimientoTiposCausaController()
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
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar el C�digo."));
			resultado = false;
			return resultado;
		}
		if(this.descripcion=="" || this.descripcion.length() == Integer.valueOf(0))
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar la Descripci�n."));
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
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar el C�digo."));
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
				if(!this.tiposCausaBO.existe(this.codigo))
				{
					TiposCausa temp = new TiposCausa();
					temp.setCodigo(this.codigo);
					temp.setDescripcion(this.descripcion);
					temp.setEstado(this.estado);
					this.tiposCausaBO.agregar(temp);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informaci�n!", "Se agreg� con �xito."));
		            this.limpiar();
				}else
				{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El Tipo de Causa ya existe."));
				}
				
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ocurri� un error."));
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
				if(this.tiposCausaBO.existe(this.codigo))
				{
					TiposCausa temp = new TiposCausa();
					temp.setCodigo(this.codigo);
					temp.setDescripcion(this.descripcion);
					temp.setEstado(this.estado);
					this.tiposCausaBO.modificar(temp);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informaci�n!", "Se modific� con �xito."));
		            this.limpiar();
				}else
				{
					this.descripcion="";
		            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No existe el Tipo de Causa."));
				}
				
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ocurri� un error."));
		}
		return "success";
	}
	
	public String eliminar()
	{
		
		try
		{
			if(this.validarEliminar())
			{
				if(this.tiposCausaBO.existe(this.codigo))
				{
					TiposCausa temp = new TiposCausa();
					temp.setCodigo(this.codigo);
					this.tiposCausaBO.eliminar(temp);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informaci�n!", "Se elimin� con �xito."));
		            this.limpiar();
				}else
				{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No existe el Tipo de Causa."));
				}
			}
		}catch(DataIntegrityViolationException e)
		{

			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El registro tiene informaci�n asociada."));
            return "success";
		}catch(Exception e)
		{
			e.printStackTrace();
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ocurri� un error."));
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
				TiposCausa temp = new TiposCausa();
				TiposCausa tempBusqueda = new TiposCausa();
				temp.setCodigo(this.codigo);
				tempBusqueda= this.tiposCausaBO.buscar(temp);
				if(tempBusqueda.getCodigo()!=null && !tempBusqueda.getCodigo().equals(Integer.valueOf(0)))
				{
					this.codigo = tempBusqueda.getCodigo();
					this.descripcion = tempBusqueda.getDescripcion();
					this.estado = tempBusqueda.getEstado();
					
//					msg.setSeverity(FacesMessage.SEVERITY_INFO);
//		            msg.setDetail(null);
//		            FacesContext.getCurrentInstance().addMessage("form1:codigo",msg);
		            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informaci�n!", ""));
		            
				}else
				{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se encotr� el Tipo de Causa."));
				}
			}
		}catch(Exception e)
		{
			this.descripcion="";
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ocurri� un error."));
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

	public TiposCausaBO getTiposCausaBO() {
		return tiposCausaBO;
	}

	public void setTiposCausaBO(TiposCausaBO tiposCausaBO) {
		this.tiposCausaBO = tiposCausaBO;
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
