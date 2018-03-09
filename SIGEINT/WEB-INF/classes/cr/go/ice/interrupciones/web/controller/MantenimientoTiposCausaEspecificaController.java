package cr.go.ice.interrupciones.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.springframework.dao.DataIntegrityViolationException;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.TiposCausaBO;
import cr.go.ice.interrupciones.BO.TiposCausaEspecificaBO;
import cr.go.ice.interrupciones.domain.TiposCausa;
import cr.go.ice.interrupciones.domain.TiposCausaEspecifica;
import cr.go.ice.interrupciones.domain.TiposCausaEspecificaID;

public class MantenimientoTiposCausaEspecificaController extends AbstractFacesController{
	
	private TiposCausaBO tiposCausaBO;
	
	private TiposCausaEspecificaBO tiposCausaEspecificaBO;
	
	private Integer codigo;
	
	private Integer codigoTipoCausa;
	
	private String descripcion;
	
	private Integer estado;
	
	private Integer estadoActivo = new Integer(0);
	
	private Integer estadoInactivo = new Integer(1);
	
	public MantenimientoTiposCausaEspecificaController()
	{
		this.codigo = new Integer(0);
		this.codigoTipoCausa = new Integer(0);
		this.descripcion = "";
		this.estado = new Integer(0);
	}
	
	public String getInit(){
		return "success";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getTiposCausasSelectItems()
	{
    	List selectItems = new ArrayList();
    	List<TiposCausa> causas = this.tiposCausaBO.buscarTodos();
    	
    	selectItems.add(new SelectItem(new Integer(0), "Seleccione un tipo de causa"));
    	
    	for(int i=0; i<causas.size();i++)
    	{
    		TiposCausa causa = causas.get(i);
    		selectItems.add(new SelectItem(causa.getCodigo(), causa.getCodigo() +" - "+ causa.getDescripcion()));
    	}
    	return selectItems;
    }
	
	public boolean validarAgregar()
	{
		
		boolean resultado = true;
		if(this.codigo ==null || this.codigo.equals(Integer.valueOf(0)))
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar el tipo de Causa Espec�fica."));
			resultado = false;
			return resultado;
		}
		if(this.codigoTipoCausa ==null || this.codigoTipoCausa.equals(Integer.valueOf(0)))
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar el tipo de Causa."));
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
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar el Tipo de Causa Espec�fica."));
			resultado = false;
			return resultado;
		}
		if(this.codigoTipoCausa ==null || this.codigoTipoCausa.equals(Integer.valueOf(0)))
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar el Tipo de Causa."));
			resultado = false;
			return resultado;
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
				if(!this.tiposCausaEspecificaBO.existe(this.codigo,this.codigoTipoCausa))
				{
					TiposCausa temp = new TiposCausa();
					TiposCausaEspecificaID id = new TiposCausaEspecificaID();
					TiposCausaEspecifica temp2 = new TiposCausaEspecifica();
					temp.setCodigo(this.codigoTipoCausa);
					
					id.setCausaEspecifica(this.codigo);
					id.setTiposCausa(temp);
					
					temp2.setDescripcion(this.descripcion);
					temp2.setEstado(this.estado);
					temp2.setTiposCausaEspecificaID(id);
					
					this.tiposCausaEspecificaBO.agregar(temp2);
					
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informaci�n!", "Se agreg� con �xito."));
		            this.limpiar();
				}else
				{
					this.descripcion="";
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El Tipo de Causa Espec�fica ya existe."));
				}
				
			}
		}
		catch(Exception e)
		{
			this.descripcion="";
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
				if(this.tiposCausaEspecificaBO.existe(this.codigo,this.codigoTipoCausa))
				{
					TiposCausa temp = new TiposCausa();
					TiposCausaEspecificaID id = new TiposCausaEspecificaID();
					TiposCausaEspecifica temp2 = new TiposCausaEspecifica();
					temp.setCodigo(this.codigoTipoCausa);
					
					id.setCausaEspecifica(this.codigo);
					id.setTiposCausa(temp);
					
					temp2.setDescripcion(this.descripcion);
					temp2.setEstado(this.estado);
					temp2.setTiposCausaEspecificaID(id);
					
					this.tiposCausaEspecificaBO.modificar(temp2);
					
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
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            msg.setDetail("Ocurri� un error");
            FacesContext.getCurrentInstance().addMessage("form1:codigo",msg);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ocurri�."));
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
				if(this.tiposCausaEspecificaBO.existe(this.codigo, this.codigoTipoCausa))
				{
					TiposCausa temp = new TiposCausa();
					TiposCausaEspecificaID id = new TiposCausaEspecificaID();
					TiposCausaEspecifica temp2 = new TiposCausaEspecifica();
					temp.setCodigo(this.codigoTipoCausa);
					
					id.setCausaEspecifica(this.codigo);
					id.setTiposCausa(temp);
					temp2.setTiposCausaEspecificaID(id);
	
					
					this.tiposCausaEspecificaBO.eliminar(temp2);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informaci�n!", "Se elimin� con �xito."));
		            this.limpiar();
				}else
				{
					this.descripcion="";
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No existe el Tipo de Causa Espec�fica."));
				}
			}
		}
		catch(DataIntegrityViolationException e)
		{
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El registro tiene informaci�n asociada."));
            return "success";
		}
		catch(Exception e)
		{
			this.descripcion ="";
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
				TiposCausaEspecificaID id = new TiposCausaEspecificaID();
				TiposCausaEspecifica temp2 = new TiposCausaEspecifica();
				TiposCausaEspecifica tempBusqueda = new TiposCausaEspecifica();
				
				temp.setCodigo(this.codigoTipoCausa);
				
				id.setCausaEspecifica(this.codigo);
				id.setTiposCausa(temp);
				temp2.setTiposCausaEspecificaID(id);
				
				
				tempBusqueda= this.tiposCausaEspecificaBO.buscar(temp2);
				
				if(tempBusqueda.getTiposCausaEspecificaID().getCausaEspecifica()!=null && !tempBusqueda.getTiposCausaEspecificaID().getCausaEspecifica().equals(Integer.valueOf(0)))
				{
					this.codigoTipoCausa = tempBusqueda.getTiposCausaEspecificaID().getTiposCausa().getCodigo();
					this.codigo = tempBusqueda.getTiposCausaEspecificaID().getCausaEspecifica();
					this.descripcion = tempBusqueda.getDescripcion();
					this.estado = tempBusqueda.getEstado();

//					msg.setSeverity(FacesMessage.SEVERITY_INFO);
//		            msg.setDetail(null);
//		            FacesContext.getCurrentInstance().addMessage("form1:tiposCausa",msg);
		            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informaci�n!", ""));
				}else
				{
					this.descripcion="";
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se encontr� el Tipo de Causa Espec�fica."));
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
		this.codigoTipoCausa = new Integer(0);
		this.codigo = new Integer(0);
		this.descripcion = "";
		this.estado = new Integer(0);
		return "success";
	}
	
	public String limpiar()
	{
		this.codigoTipoCausa = new Integer(0);
		this.codigo = new Integer(0);
		this.descripcion = "";
		this.estado = new Integer(0);
		return "success";
	}

	public TiposCausaBO getTiposCausaBO() {
		return tiposCausaBO;
	}

	public void setTiposCausaBO(TiposCausaBO tiposCausaBO) {
		this.tiposCausaBO = tiposCausaBO;
	}

	public TiposCausaEspecificaBO getTiposCausaEspecificaBO() {
		return tiposCausaEspecificaBO;
	}

	public void setTiposCausaEspecificaBO(TiposCausaEspecificaBO tiposCausaEspecificaBO) {
		this.tiposCausaEspecificaBO = tiposCausaEspecificaBO;
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

	public Integer getCodigoTipoCausa() {
		return codigoTipoCausa;
	}

	public void setCodigoTipoCausa(Integer codigoTipoCausa) {
		this.codigoTipoCausa = codigoTipoCausa;
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
