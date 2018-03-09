package cr.go.ice.interrupciones.web.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.DanoBO;
import cr.go.ice.interrupciones.BO.MaterialBO;
import cr.go.ice.interrupciones.BO.ProteccionBO;
import cr.go.ice.interrupciones.BO.ProvolmaBO;
import cr.go.ice.interrupciones.domain.Provolma;
import cr.go.ice.interrupciones.domain.ProvolmaID;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.AnimalController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ProvolmaController.java</code> Establese la relacion entre capa de presentacion y la logica del negocio.</p>
 * <p>Fecha creación: 21/02/2007</p>
 * <p>Ultima actualización: 21/02/2007</p>
 * @author Vista Verde Soft (Juan Manuel y Doc Cristian)
 * @version 1.1
 */
public class ProvolmaController extends AbstractFacesController {
	
	/**
	 * <code>voltage</code> Voltaje
	 */
	private Integer voltage;
	/**
	 * <code>protecciones</code> protecciones
	 */
	private Integer protecciones;
	/**
	 * <code>materiales</code> Materiales
	 */
	private Integer materiales;
	/**
	 * <code>danos</code> Danos
	 */
	private Integer danos; 

	/**
	 * <code>tableData</code> Tabla de Datos
	 */
	private DataTable tableData;

	/**
	 * <code>provolmaBO</code> Provolma BO
	 */
	private ProvolmaBO provolmaBO;
	
	/**
	 * <code>danoBO</code> Dano BO
	 */
	private DanoBO danoBO;
	/**
	 * <code>materialBO</code> Material BO
	 */
	private MaterialBO materialBO;
	/**
	 * <code>proteccionBO</code> Proteccion BO
	 */
	private ProteccionBO proteccionBO;
	/**
	 * <code>listaProvolma</code> lista Provolma
	 */
	private List listaProvolma;
	
	private boolean buscar;

	
	/***********************************************************************/

	public ProvolmaController(){
	    buscar = false;
	}
	
	public String getInit(){
		return "success";
	}
	
	/**
	 * Comment for getDanos
	 * @return Danos
	 */
	public Integer getDanos() {
		return danos;
	}

	
	/**
	 * Comment for setDanos
	 * @param danos
	 */
	public void setDanos(Integer danos) {
		this.danos = danos;
	}

	
	/**
	 * Comment for getMateriales
	 * @return Materiales
	 */
	public Integer getMateriales() {
		return materiales;
	}

	
	/**
	 * Comment for setMateriales
	 * @param materiales
	 */
	public void setMateriales(Integer materiales) {
		this.materiales = materiales;
	}

	
	/**
	 * Comment for getProtecciones
	 * @return protecciones
	 */
	public Integer getProtecciones() {
		return protecciones;
	}

	
	/**
	 * Comment for setProtecciones
	 * @param protecciones
	 */
	public void setProtecciones(Integer protecciones) {
		this.protecciones = protecciones;
	}

	
	/**
	 * Comment for getVoltage
	 * @return Voltage
	 */
	public Integer getVoltage() {
		return voltage;
	}

	
	/**
	 * Comment for setVoltage
	 * @param voltage
	 */
	public void setVoltage(Integer voltage) {
		this.voltage = voltage;
	}
	
	
	/**
	 * Comment for getTableData
	 * @return Tabla de Datos
	 */
	public DataTable getTableData() {
		return tableData;
	}

	
	/**
	 * Comment for setTableData
	 * @param tableData
	 */
	public void setTableData(DataTable tableData) {
		this.tableData = tableData;
	}	


	/**
	 * Comment for getProvolmaBO
	 * @return Provolmo
	 */
	public ProvolmaBO getProvolmaBO() {
		return provolmaBO;
	}


	/**
	 * Comment for setProvolmaBO
	 * @param provolmaBO
	 */
	public void setProvolmaBO(ProvolmaBO provolmaBO) {
		this.provolmaBO = provolmaBO;
	}


	/**
	 * Comment for getDanoBO
	 * @return Dano BO
	 */
	public DanoBO getDanoBO() {
		return danoBO;
	}


	/**
	 * Comment for setDanoBO
	 * @param danoBO
	 */
	public void setDanoBO(DanoBO danoBO) {
		this.danoBO = danoBO;
	}


	/**
	 * Comment for getMaterialBO
	 * @return Material BO
	 */
	public MaterialBO getMaterialBO() {
		return materialBO;
	}


	/**
	 * Comment for setMaterialBO
	 * @param materialBO
	 */
	public void setMaterialBO(MaterialBO materialBO) {
		this.materialBO = materialBO;
	}


	/**
	 * Comment for getProteccionBO
	 * @return Proteccion BO
	 */
	public ProteccionBO getProteccionBO() {
		return proteccionBO;
	}


	/**
	 * Comment for setProteccionBO
	 * @param proteccionBO
	 */
	public void setProteccionBO(ProteccionBO proteccionBO) {
		this.proteccionBO = proteccionBO;
	}
	
	
	/**
	 * Comment for getListaProvolma
	 * @return Lista Provolm
	 */
	public List getListaProvolma() {		
	    if (this.buscar == false) {
	        this.listaProvolma = this.provolmaBO.getProvolmas();
	    }
		return this.listaProvolma;
	}
	
	
	/**
	 * Comment for agregarProvolma
	 * @return "success" o "error" al agregar Provolma
	 */
	public String agregarProvolma() {		
		FacesMessage msg = new FacesMessage();
		
		ProvolmaID provolmaID = new ProvolmaID();
		Provolma provolma = new Provolma();
		provolmaID.setCodigoVoltaje(this.voltage);
		provolmaID.setDano(this.danoBO.buscar(this.danos));
		provolmaID.setMaterial(this.materialBO.buscar(this.materiales));
		provolmaID.setProteccion(this.proteccionBO.buscar(this.protecciones));
		
		provolma.setProvolmaID(provolmaID);
		if(!this.provolmaBO.existe(provolma)){
			this.provolmaBO.agregar(provolma);	
			this.voltage=null;
			this.danos=null;
			this.materiales=null;
			this.protecciones=null;
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "La combinación PROVOLMA grabada exitosamente."));
			return "success";
		}else{
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La combinación PROVOLMA ya existe."));
			return "error";
		}		
	}
	
	/**
	 * Comment for buscarProvolma
	 * @return
	 */
	public String buscarProvolma(){
		ProvolmaID provolmaID = new ProvolmaID();
		Provolma provolma = new Provolma();
		provolmaID.setCodigoVoltaje(this.voltage);
		provolmaID.setDano(this.danoBO.buscar(this.danos));
		provolmaID.setMaterial(this.materialBO.buscar(this.materiales));
		provolmaID.setProteccion(this.proteccionBO.buscar(this.protecciones));

		provolma.setProvolmaID(provolmaID);
		if (this.provolmaBO.existe(provolma)) {
		    this.listaProvolma.clear();
			this.listaProvolma.add(this.provolmaBO.buscar(provolmaID));
			this.buscar = true;
			return "success";
		} else {
			buscar = false;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La combinación PROVOLMA no existe."));
			return "error";
		}				
	}
	
	/**
	 * Comment for eliminarProvolma
	 * @return "success" al agregar Provolma
	 */
	public String eliminarProvolma() {
		Provolma provolma = (Provolma) this.tableData.getRowData();
		if(this.provolmaBO.registrosAsociados(provolma.getProvolmaID()).longValue() > 0){
		   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede eliminar!", "Combinación PROVOLMA se encuentra asociada con diversa información."));
			return "error";
		}
		else
		    this.provolmaBO.eliminar(provolma);
		return "success";		
	}
    
    /**
     * Cancela
     * @return success
     */
    public String cancelar(){
        return "success";
    }
	@Override
	protected String getPropertyFieldName(String property) {
		return null;
	}
	@Override
	protected void resetController() { }

}
