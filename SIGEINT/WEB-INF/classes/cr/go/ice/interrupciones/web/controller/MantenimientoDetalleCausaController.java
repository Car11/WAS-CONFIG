package cr.go.ice.interrupciones.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.springframework.dao.DataIntegrityViolationException;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.DetalleCausaBO;
import cr.go.ice.interrupciones.BO.TiposCausaBO;
import cr.go.ice.interrupciones.BO.TiposCausaEspecificaBO;
import cr.go.ice.interrupciones.domain.DetalleCausa;
import cr.go.ice.interrupciones.domain.DetalleCausaID;
import cr.go.ice.interrupciones.domain.TiposCausa;
import cr.go.ice.interrupciones.domain.TiposCausaEspecifica;
import cr.go.ice.interrupciones.domain.TiposCausaEspecificaID;

public class MantenimientoDetalleCausaController extends AbstractFacesController {
	
	private TiposCausaBO tiposCausaBO;
	
	private TiposCausaEspecificaBO tiposCausaEspecificaBO;
	
	private DetalleCausaBO detalleCausaBO;
	
	private Integer codigo;
	
	private Integer codigoTipoCausa;
	
	private Integer codigoTipoCausaEspecifica;
	
	private String descripcion;
	
	private Integer estado;
	
	private Integer estadoActivo = new Integer(0);
	
	private Integer estadoInactivo = new Integer(1);
	
	public MantenimientoDetalleCausaController()
	{
		this.codigo = new Integer(0);
		this.codigoTipoCausa = new Integer(0);
		this.codigoTipoCausaEspecifica = new Integer(0);
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getTiposCausasEspecificasSelectItems()
	{
    	List selectItems = new ArrayList();
    	List<TiposCausaEspecifica> causas = this.tiposCausaEspecificaBO.buscarTodosTipoCausa(this.codigoTipoCausa);
    	selectItems.add(new SelectItem(new Integer(0), "Seleccione un tipo de causa específica"));
    	
    	if(!causas.isEmpty())
    	{
    		for(int i=0; i<causas.size();i++)
        	{
    			TiposCausaEspecifica causa = causas.get(i);
        		selectItems.add(new SelectItem(causa.getTiposCausaEspecificaID().getCausaEspecifica(), causa.getTiposCausaEspecificaID().getCausaEspecifica() +" - "+ causa.getDescripcion()));
        	}
    	}
    	
    	return selectItems;
    }
	
	public boolean validarAgregar()
	{
	
		boolean resultado = true;
		if(this.codigo ==null || this.codigo.equals(Integer.valueOf(0)))
		{
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar el detalle de Causa."));
			resultado = false;
			return resultado;
		}
		if(this.codigoTipoCausa ==null || this.codigoTipoCausa.equals(Integer.valueOf(0)))
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar el tipo de Causa."));
			resultado = false;
			return resultado;
		}
		if(this.codigoTipoCausaEspecifica ==null || this.codigoTipoCausaEspecifica.equals(Integer.valueOf(0)))
		{
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar el tipo de Causa Específica."));
			resultado = false;
			return resultado;
		}
		if(this.descripcion=="" || this.descripcion.length() == Integer.valueOf(0))
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar la descripción."));
			resultado = false;
			return resultado;
		}
		return resultado;
	}
	public boolean validarEliminar()
	{
	
		boolean resultado = true;
		if(this.codigo ==null || this.codigo.equals(Integer.valueOf(0)))
		{
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar el Detalle de Causa."));
			resultado = false;
			return resultado;
		}
		if(this.codigoTipoCausa ==null || this.codigoTipoCausa.equals(Integer.valueOf(0)))
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar el Tipo de Causa."));
			resultado = false;
			return resultado;
		}
		if(this.codigoTipoCausaEspecifica ==null || this.codigoTipoCausaEspecifica.equals(Integer.valueOf(0)))
		{
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe selccionar el Tipo de Causa Específica."));
			resultado = false;
			return resultado;
		}
		return resultado;
	}
	
	public String agregar()
	{
	
		try
		{
			if(this.validarAgregar())
			{
				if(!this.detalleCausaBO.existe(this.codigo, this.codigoTipoCausaEspecifica,this.codigoTipoCausa))
				{
					TiposCausa temp = new TiposCausa();
					TiposCausaEspecificaID id = new TiposCausaEspecificaID();
					TiposCausaEspecifica temp2 = new TiposCausaEspecifica();
					DetalleCausa temp3 = new DetalleCausa();
					DetalleCausaID id2 = new DetalleCausaID();
					
					temp.setCodigo(this.codigoTipoCausa);
					
					id.setCausaEspecifica(this.codigoTipoCausaEspecifica);
					id.setTiposCausa(temp);
					
					temp2.setTiposCausaEspecificaID(id);
					
					id2.setDetalleCausa(this.codigo);
					id2.setTiposCausaEspecifica(temp2);
					
					temp3.setDetalleCausaID(id2);
					temp3.setDescripcion(this.descripcion);
					temp3.setEstado(this.estado);
					
					this.detalleCausaBO.agregar(temp3);
					
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Se agregó con éxito."));
		            this.limpiar();
				}else
				{
					this.descripcion="";
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El Detalle de Causa ya existe."));
				}
				
			}
		}
		catch(Exception e)
		{
			this.descripcion="";
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ocurrió un error."));
		}
		return "success";
	}
	
	public String modificar()
	{
	
		try
		{
			if(this.validarAgregar())
			{
				if(this.detalleCausaBO.existe(this.codigo, this.codigoTipoCausaEspecifica,this.codigoTipoCausa))
				{
					TiposCausa temp = new TiposCausa();
					TiposCausaEspecificaID id = new TiposCausaEspecificaID();
					TiposCausaEspecifica temp2 = new TiposCausaEspecifica();
					DetalleCausa temp3 = new DetalleCausa();
					DetalleCausaID id2 = new DetalleCausaID();
					
					temp.setCodigo(this.codigoTipoCausa);
					
					id.setCausaEspecifica(this.codigoTipoCausaEspecifica);
					id.setTiposCausa(temp);
					
					temp2.setTiposCausaEspecificaID(id);
					
					id2.setDetalleCausa(this.codigo);
					id2.setTiposCausaEspecifica(temp2);
					
					temp3.setDetalleCausaID(id2);
					temp3.setDescripcion(this.descripcion);
					temp3.setEstado(this.estado);
					
					this.detalleCausaBO.modificar(temp3);
					
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Se modificó con éxito."));
		            this.limpiar();
				}else
				{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No existe detalle de Causa."));
				}
				
			}
		}
		catch(Exception e)
		{
			this.descripcion="";
			e.printStackTrace();
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ocurrió un error."));
		}
		return "success";
	}
	
	public String eliminar()
	{
	
		try
		{
			if(this.validarEliminar())
			{
				if(this.detalleCausaBO.existe(this.codigo,this.codigoTipoCausa, this.codigoTipoCausaEspecifica))
				{
					TiposCausa temp = new TiposCausa();
					TiposCausaEspecificaID id = new TiposCausaEspecificaID();
					TiposCausaEspecifica temp2 = new TiposCausaEspecifica();
					DetalleCausa temp3 = new DetalleCausa();
					DetalleCausaID id2 = new DetalleCausaID();
					
					temp.setCodigo(this.codigoTipoCausa);
					
					id.setCausaEspecifica(this.codigoTipoCausaEspecifica);
					id.setTiposCausa(temp);
					
					temp2.setTiposCausaEspecificaID(id);
					
					id2.setDetalleCausa(this.codigo);
					id2.setTiposCausaEspecifica(temp2);
					
					temp3.setDetalleCausaID(id2);
					
					this.detalleCausaBO.eliminar(temp3);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Se eliminó con éxito."));
		            this.limpiar();
				}else
				{
					this.descripcion="";
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No existe el Detalle de Causa."));
				}
			}
		}catch(DataIntegrityViolationException e)
		{
			this.descripcion="";
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El registro tiene información asociada."));
            return "success";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ocurrió un error."));
		}
		return "success";
	}
	
	public String buscar()
	{
		
		try
		{
			if(this.validarEliminar())
			{
				TiposCausa temp = new TiposCausa();
				TiposCausaEspecificaID id = new TiposCausaEspecificaID();
				TiposCausaEspecifica temp2 = new TiposCausaEspecifica();
				DetalleCausa temp3 = new DetalleCausa();
				DetalleCausa tempBusqueda = new DetalleCausa();
				DetalleCausaID id2 = new DetalleCausaID();
				
				temp.setCodigo(this.codigoTipoCausa);
				
				id.setCausaEspecifica(this.codigoTipoCausaEspecifica);
				id.setTiposCausa(temp);
				
				temp2.setTiposCausaEspecificaID(id);
				
				id2.setDetalleCausa(this.codigo);
				id2.setTiposCausaEspecifica(temp2);
				
				temp3.setDetalleCausaID(id2);
				
				tempBusqueda= this.detalleCausaBO.buscar(temp3);
				
				if(tempBusqueda.getDetalleCausaID().getDetalleCausa()!=null && !tempBusqueda.getDetalleCausaID().getDetalleCausa().equals(Integer.valueOf(0)))
				{
					this.codigoTipoCausa = tempBusqueda.getDetalleCausaID().getTiposCausaEspecifica().getTiposCausaEspecificaID().getTiposCausa().getCodigo();
					this.codigoTipoCausaEspecifica = tempBusqueda.getDetalleCausaID().getTiposCausaEspecifica().getTiposCausaEspecificaID().getCausaEspecifica();
					this.codigo = tempBusqueda.getDetalleCausaID().getDetalleCausa();
					this.descripcion = tempBusqueda.getDescripcion();
					this.estado = tempBusqueda.getEstado();
					
//					msg.setSeverity(FacesMessage.SEVERITY_INFO);
//		            msg.setDetail(null);
//		            FacesContext.getCurrentInstance().addMessage("form1:codigo",msg);
		            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "."));
				}else
				{
					this.descripcion="";
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se encotró el Detalle de Causa."));
				}
			}	
		}
		catch(Exception e)
		{
			this.descripcion="";
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ocurrió un error."));
		}
		return "success";
	}
	
	public String cancelar()
	{
		this.codigoTipoCausaEspecifica = new Integer(0);
		this.codigoTipoCausa = new Integer(0);
		this.codigo = new Integer(0);
		this.descripcion = "";
		this.estado = new Integer(0);
		return "success";
	}
	
	public String limpiar()
	{
		this.codigoTipoCausaEspecifica = new Integer(0);
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

	public DetalleCausaBO getDetalleCausaBO() {
		return detalleCausaBO;
	}

	public void setDetalleCausaBO(DetalleCausaBO detalleCausaBO) {
		this.detalleCausaBO = detalleCausaBO;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigoTipoCausa() {
		return codigoTipoCausa;
	}

	public void setCodigoTipoCausa(Integer codigoTipoCausa) {
		this.codigoTipoCausa = codigoTipoCausa;
	}

	public Integer getCodigoTipoCausaEspecifica() {
		return codigoTipoCausaEspecifica;
	}

	public void setCodigoTipoCausaEspecifica(Integer codigoTipoCausaEspecifica) {
		this.codigoTipoCausaEspecifica = codigoTipoCausaEspecifica;
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
