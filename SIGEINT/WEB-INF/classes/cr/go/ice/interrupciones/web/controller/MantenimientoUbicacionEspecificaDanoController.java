package cr.go.ice.interrupciones.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.springframework.dao.DataIntegrityViolationException;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.UbicacionEspecificaDanoBO;
import cr.go.ice.interrupciones.BO.UbicacionGeneralDanoBO;
import cr.go.ice.interrupciones.domain.UbicacionEspecificaDano;
import cr.go.ice.interrupciones.domain.UbicacionEspecificaDanoID;
import cr.go.ice.interrupciones.domain.UbicacionGeneralDano;

public class MantenimientoUbicacionEspecificaDanoController extends AbstractFacesController{
	
	private UbicacionGeneralDanoBO ubicacionGeneralDanoBO;
	
	private UbicacionEspecificaDanoBO ubicacionEspecificaDanoBO;
	
	private Integer codigo;
	
	private Integer codigoUbicacionGeneral;
	
	private String descripcion;
	
	private Integer estado;
	
	private Integer estadoActivo = new Integer(0);
	
	private Integer estadoInactivo = new Integer(1);
	
	public MantenimientoUbicacionEspecificaDanoController()
	{
		this.codigo = new Integer(0);
		this.codigoUbicacionGeneral = new Integer(0);
		this.descripcion = "";
		this.estado = new Integer(0);
	}
	
	
	public String getInit(){
		return "success";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getUbicacionGeneralSelectItems()
	{
    	List selectItems = new ArrayList();
    	List<UbicacionGeneralDano> ubicaciones = this.ubicacionGeneralDanoBO.buscarTodos();
    	
    	selectItems.add(new SelectItem(new Integer(0), "Seleccione una Ubicación General del Daño"));
    	
    	for(int i=0; i<ubicaciones.size();i++)
    	{
    		UbicacionGeneralDano ubicacion = ubicaciones.get(i);
    		selectItems.add(new SelectItem(ubicacion.getCodigo(), ubicacion.getCodigo() +" - "+ ubicacion.getDescripcion()));
    	}
    	return selectItems;
    }
	
	public boolean validarAgregar()
	{
		boolean resultado = true;
		if(this.codigo ==null || this.codigo.equals(Integer.valueOf(0)))
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar la Ubicación Específica del Daño."));
			resultado = false;
			return resultado;
		}
		if(this.codigoUbicacionGeneral ==null || this.codigoUbicacionGeneral.equals(Integer.valueOf(0)))
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar la Ubicación General del Daño."));
			resultado = false;
			return resultado;
		}
		if(this.descripcion=="" || this.descripcion.length() == Integer.valueOf(0))
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar la Discrepción."));
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
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe digitar la Ubicación Específica del Daño."));
			resultado = false;
			return resultado;
		}
		if(this.codigoUbicacionGeneral ==null || this.codigoUbicacionGeneral.equals(Integer.valueOf(0)))
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar la Ubicaciín General del Daño."));
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
				if(!this.ubicacionEspecificaDanoBO.existe(this.codigo,this.codigoUbicacionGeneral))
				{
					UbicacionGeneralDano temp = new UbicacionGeneralDano();
					UbicacionEspecificaDanoID id = new UbicacionEspecificaDanoID();
					UbicacionEspecificaDano temp2 = new UbicacionEspecificaDano();
					
					temp.setCodigo(this.codigoUbicacionGeneral);
					
					id.setDanoEspecifico(this.codigo);
					id.setUbicacionGeneralDano(temp);
					
					temp2.setDescripcion(this.descripcion);
					temp2.setEstado(this.estado);
					temp2.setUbicacionEspecificaDanoID(id);
					
					this.ubicacionEspecificaDanoBO.agregar(temp2);
					
					 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Se agregó con éxito."));
		            this.limpiar();
				}else
				{
					this.descripcion="";
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La Ubicación Específica del Daño ya existe."));
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
				if(this.ubicacionEspecificaDanoBO.existe(this.codigo,this.codigoUbicacionGeneral))
				{
					UbicacionGeneralDano temp = new UbicacionGeneralDano();
					UbicacionEspecificaDanoID id = new UbicacionEspecificaDanoID();
					UbicacionEspecificaDano temp2 = new UbicacionEspecificaDano();
					
					temp.setCodigo(this.codigoUbicacionGeneral);
					
					id.setDanoEspecifico(this.codigo);
					id.setUbicacionGeneralDano(temp);
					
					temp2.setDescripcion(this.descripcion);
					temp2.setEstado(this.estado);
					temp2.setUbicacionEspecificaDanoID(id);
					
					this.ubicacionEspecificaDanoBO.modificar(temp2);
					
					 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Se modificó con éxito."));
		            this.limpiar();
				}else
				{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No existe la Ubicación Específica del Daño."));
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
		try
		{
			if(this.validarEliminar())
			{
				if(this.ubicacionEspecificaDanoBO.existe(this.codigo,this.codigoUbicacionGeneral))
				{
					UbicacionGeneralDano temp = new UbicacionGeneralDano();
					UbicacionEspecificaDanoID id = new UbicacionEspecificaDanoID();
					UbicacionEspecificaDano temp2 = new UbicacionEspecificaDano();
					
					temp.setCodigo(this.codigoUbicacionGeneral);
					
					id.setDanoEspecifico(this.codigo);
					id.setUbicacionGeneralDano(temp);
					
					temp2.setDescripcion(this.descripcion);
					temp2.setEstado(this.estado);
					temp2.setUbicacionEspecificaDanoID(id);
					
					this.ubicacionEspecificaDanoBO.eliminar(temp2);
					 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Se eliminó con éxito."));
		            this.limpiar();
				}else
				{
					this.descripcion="";
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No existe la Ubicación Específica del Daño."));
					
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
		try
		{
			if(this.validarEliminar())
			{
				UbicacionGeneralDano temp = new UbicacionGeneralDano();
				UbicacionEspecificaDanoID id = new UbicacionEspecificaDanoID();
				UbicacionEspecificaDano temp2 = new UbicacionEspecificaDano();
				UbicacionEspecificaDano tempBusqueda = new UbicacionEspecificaDano();
				
				temp.setCodigo(this.codigoUbicacionGeneral);
				
				id.setDanoEspecifico(this.codigo);
				id.setUbicacionGeneralDano(temp);
				
				temp2.setUbicacionEspecificaDanoID(id);
				
				tempBusqueda = this.ubicacionEspecificaDanoBO.buscar(temp2);

				
				if(tempBusqueda.getUbicacionEspecificaDanoID().getDanoEspecifico()!=null && !tempBusqueda.getUbicacionEspecificaDanoID().getDanoEspecifico().equals(Integer.valueOf(0)))
				{
					this.codigoUbicacionGeneral = tempBusqueda.getUbicacionEspecificaDanoID().getUbicacionGeneralDano().getCodigo();
					this.codigo = tempBusqueda.getUbicacionEspecificaDanoID().getDanoEspecifico();
					this.descripcion = tempBusqueda.getDescripcion();
					this.estado = tempBusqueda.getEstado();
//					msg.setSeverity(FacesMessage.SEVERITY_INFO);
//		            msg.setDetail(null);
//		            FacesContext.getCurrentInstance().addMessage("form1:tiposCausa",msg);
		            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", ""));
		           
				}else
				{
					this.descripcion="";
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se encontró la Ubicación Específica del Daño."));
		           
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
		this.codigoUbicacionGeneral = new Integer(0);
		this.codigo = new Integer(0);
		this.descripcion = "";
		this.estado = new Integer(0);
		return "success";
	}
	
	public String limpiar()
	{
		this.codigoUbicacionGeneral = new Integer(0);
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

	public UbicacionEspecificaDanoBO getUbicacionEspecificaDanoBO() {
		return ubicacionEspecificaDanoBO;
	}

	public void setUbicacionEspecificaDanoBO(UbicacionEspecificaDanoBO ubicacionEspecificaDanoBO) {
		this.ubicacionEspecificaDanoBO = ubicacionEspecificaDanoBO;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigoUbicacionGeneral() {
		return codigoUbicacionGeneral;
	}

	public void setCodigoUbicacionGeneral(Integer codigoUbicacionGeneral) {
		this.codigoUbicacionGeneral = codigoUbicacionGeneral;
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
