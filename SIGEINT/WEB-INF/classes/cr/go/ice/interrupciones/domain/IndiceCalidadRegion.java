package cr.go.ice.interrupciones.domain;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import cr.go.ice.interrupciones.domain.IndiceCalidadNacionalId;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.IndiceGlobalRegion.java</p>
 * <p>Modulo (subsistema): Proyecto</p>
 * <p>Fecha creacion: 10/04/2010</p>
 * <p>Ultima actualizacion: 10/04/2010</p>
 * @author VVS Code Generator
 * @version 1.1
 */
public class IndiceCalidadRegion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3700643308620371834L;
	private IndiceCalidadRegionId id;
	private Integer abonadosNacional;
	
	private Double horasAbonadoSuspensionProgramada;
	private Double horasAbonadoSuspensionProgramadaAcumulado;
	private Integer abonadosAfectadosSuspensionProgramada;
	private Integer abonadosAfectadosSuspensionProgramadaAcumulado;
	private Double duracionSuspensionProgramada;
	private Double duracionSuspensionProgramadaAcumulado;
	private Integer averiasSuspensionProgramada;
	private Integer averiasSuspensionProgramadaAcumulado;
	
	private Double dpirSuspensionProgramada;
	private Double dpirSuspensionProgramadaAcumulado;
	private Double fpiSuspensionProgramada;
	private Double fpiSuspensionProgramadaAcumulado;
	private Double dpicSuspensionProgramada;
	private Double dpicSuspensionProgramadaAcumulado;
	private Double dpsSuspensionProgramada;
	private Double dpsSuspensionProgramadaAcumulado;
	private Double dpaSuspensionProgramada;
	private Double dpaSuspensionProgramadaAcumulado;
	
	private Double dpirPuro;
	private Double dpirPuroAcumulado;
	private Double fpiPuro;
	private Double fpiPuroAcumulado;
	private Double dpicPuro;
	private Double dpicPuroAcumulado;
	private Double dpsPuro;
	private Double dpsPuroAcumulado;
	private Double dpaPuro;
	private Double dpaPuroAcumulado;

	/**
 	* Constructor por defecto 
 	*/
	public IndiceCalidadRegion(){
		
		this.id = null;
		this.abonadosNacional = null;
		
		this.horasAbonadoSuspensionProgramada= null;
		this.horasAbonadoSuspensionProgramadaAcumulado= null;
		this.abonadosAfectadosSuspensionProgramada= null;
		this.abonadosAfectadosSuspensionProgramadaAcumulado= null;
		this.duracionSuspensionProgramada= null;
		this.duracionSuspensionProgramadaAcumulado= null;
		this.averiasSuspensionProgramada= null;
		this.averiasSuspensionProgramadaAcumulado= null;
		
		this.dpirSuspensionProgramada= null;
		this.dpirSuspensionProgramadaAcumulado= null;
		this.fpiSuspensionProgramada= null;
		this.fpiSuspensionProgramadaAcumulado= null;
		this.dpicSuspensionProgramada= null;
		this.dpicSuspensionProgramadaAcumulado= null;
		this.dpsSuspensionProgramada= null;
		this.dpsSuspensionProgramadaAcumulado= null;
		this.dpaSuspensionProgramada= null;
		this.dpaSuspensionProgramadaAcumulado= null;
		
		this.dpirPuro= null;
		this.dpirPuroAcumulado= null;
		this.fpiPuro= null;
		this.fpiPuroAcumulado= null;
		this.dpicPuro= null;
		this.dpicPuroAcumulado= null;
		this.dpsPuro= null;
		this.dpsPuroAcumulado= null;
		this.dpaPuro= null;
		this.dpaPuroAcumulado= null;
	}
	public IndiceCalidadRegion(IndiceCalidadRegionId id){
		this.id = id;
	}

	
	public boolean equals(Object obj) {
		if(obj instanceof IndiceCalidadNacionalId){
			IndiceCalidadRegionId indiceCalidadRegionId = (IndiceCalidadRegionId)obj;
			EqualsBuilder eb = new EqualsBuilder();
			eb.append(this.id, indiceCalidadRegionId.getAno());
			return eb.isEquals();
		}
		else{
			return false;
		}
	}
	/**
 	* @see java.lang.Object#hashCode()
 	*/
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(this.id);
		return hcb.toHashCode();
	}
	public IndiceCalidadRegionId getId() {
		return id;
	}
	public void setId(IndiceCalidadRegionId id) {
		this.id = id;
	}
	public Integer getAbonadosNacional() {
		return abonadosNacional;
	}
	public void setAbonadosNacional(Integer abonadosNacional) {
		this.abonadosNacional = abonadosNacional;
	}
	public Double getHorasAbonadoSuspensionProgramada() {
		return horasAbonadoSuspensionProgramada;
	}
	public void setHorasAbonadoSuspensionProgramada(Double horasAbonadoSuspensionProgramada) {
		this.horasAbonadoSuspensionProgramada = horasAbonadoSuspensionProgramada;
	}
	public Double getHorasAbonadoSuspensionProgramadaAcumulado() {
		return horasAbonadoSuspensionProgramadaAcumulado;
	}
	public void setHorasAbonadoSuspensionProgramadaAcumulado(Double horasAbonadoSuspensionProgramadaAcumulado) {
		this.horasAbonadoSuspensionProgramadaAcumulado = horasAbonadoSuspensionProgramadaAcumulado;
	}
	public Integer getAbonadosAfectadosSuspensionProgramada() {
		return abonadosAfectadosSuspensionProgramada;
	}
	public void setAbonadosAfectadosSuspensionProgramada(Integer abonadosAfectadosSuspensionProgramada) {
		this.abonadosAfectadosSuspensionProgramada = abonadosAfectadosSuspensionProgramada;
	}
	public Integer getAbonadosAfectadosSuspensionProgramadaAcumulado() {
		return abonadosAfectadosSuspensionProgramadaAcumulado;
	}
	public void setAbonadosAfectadosSuspensionProgramadaAcumulado(Integer abonadosAfectadosSuspensionProgramadaAcumulado) {
		this.abonadosAfectadosSuspensionProgramadaAcumulado = abonadosAfectadosSuspensionProgramadaAcumulado;
	}
	public Double getDuracionSuspensionProgramada() {
		return duracionSuspensionProgramada;
	}
	public void setDuracionSuspensionProgramada(Double duracionSuspensionProgramada) {
		this.duracionSuspensionProgramada = duracionSuspensionProgramada;
	}
	public Double getDuracionSuspensionProgramadaAcumulado() {
		return duracionSuspensionProgramadaAcumulado;
	}
	public void setDuracionSuspensionProgramadaAcumulado(Double duracionSuspensionProgramadaAcumulado) {
		this.duracionSuspensionProgramadaAcumulado = duracionSuspensionProgramadaAcumulado;
	}
	public Integer getAveriasSuspensionProgramada() {
		return averiasSuspensionProgramada;
	}
	public void setAveriasSuspensionProgramada(Integer averiasSuspensionProgramada) {
		this.averiasSuspensionProgramada = averiasSuspensionProgramada;
	}
	public Integer getAveriasSuspensionProgramadaAcumulado() {
		return averiasSuspensionProgramadaAcumulado;
	}
	public void setAveriasSuspensionProgramadaAcumulado(Integer averiasSuspensionProgramadaAcumulado) {
		this.averiasSuspensionProgramadaAcumulado = averiasSuspensionProgramadaAcumulado;
	}
	public Double getDpirSuspensionProgramada() {
		return dpirSuspensionProgramada;
	}
	public void setDpirSuspensionProgramada(Double dpirSuspensionProgramada) {
		this.dpirSuspensionProgramada = dpirSuspensionProgramada;
	}
	public Double getDpirSuspensionProgramadaAcumulado() {
		return dpirSuspensionProgramadaAcumulado;
	}
	public void setDpirSuspensionProgramadaAcumulado(Double dpirSuspensionProgramadaAcumulado) {
		this.dpirSuspensionProgramadaAcumulado = dpirSuspensionProgramadaAcumulado;
	}
	public Double getFpiSuspensionProgramada() {
		return fpiSuspensionProgramada;
	}
	public void setFpiSuspensionProgramada(Double fpiSuspensionProgramada) {
		this.fpiSuspensionProgramada = fpiSuspensionProgramada;
	}
	public Double getFpiSuspensionProgramadaAcumulado() {
		return fpiSuspensionProgramadaAcumulado;
	}
	public void setFpiSuspensionProgramadaAcumulado(Double fpiSuspensionProgramadaAcumulado) {
		this.fpiSuspensionProgramadaAcumulado = fpiSuspensionProgramadaAcumulado;
	}
	public Double getDpicSuspensionProgramada() {
		return dpicSuspensionProgramada;
	}
	public void setDpicSuspensionProgramada(Double dpicSuspensionProgramada) {
		this.dpicSuspensionProgramada = dpicSuspensionProgramada;
	}
	public Double getDpicSuspensionProgramadaAcumulado() {
		return dpicSuspensionProgramadaAcumulado;
	}
	public void setDpicSuspensionProgramadaAcumulado(Double dpicSuspensionProgramadaAcumulado) {
		this.dpicSuspensionProgramadaAcumulado = dpicSuspensionProgramadaAcumulado;
	}
	public Double getDpsSuspensionProgramada() {
		return dpsSuspensionProgramada;
	}
	public void setDpsSuspensionProgramada(Double dpsSuspensionProgramada) {
		this.dpsSuspensionProgramada = dpsSuspensionProgramada;
	}
	public Double getDpsSuspensionProgramadaAcumulado() {
		return dpsSuspensionProgramadaAcumulado;
	}
	public void setDpsSuspensionProgramadaAcumulado(Double dpsSuspensionProgramadaAcumulado) {
		this.dpsSuspensionProgramadaAcumulado = dpsSuspensionProgramadaAcumulado;
	}
	public Double getDpaSuspensionProgramada() {
		return dpaSuspensionProgramada;
	}
	public void setDpaSuspensionProgramada(Double dpaSuspensionProgramada) {
		this.dpaSuspensionProgramada = dpaSuspensionProgramada;
	}
	public Double getDpaSuspensionProgramadaAcumulado() {
		return dpaSuspensionProgramadaAcumulado;
	}
	public void setDpaSuspensionProgramadaAcumulado(Double dpaSuspensionProgramadaAcumulado) {
		this.dpaSuspensionProgramadaAcumulado = dpaSuspensionProgramadaAcumulado;
	}
	public Double getDpirPuro() {
		return dpirPuro;
	}
	public void setDpirPuro(Double dpirPuro) {
		this.dpirPuro = dpirPuro;
	}
	public Double getDpirPuroAcumulado() {
		return dpirPuroAcumulado;
	}
	public void setDpirPuroAcumulado(Double dpirPuroAcumulado) {
		this.dpirPuroAcumulado = dpirPuroAcumulado;
	}
	public Double getFpiPuro() {
		return fpiPuro;
	}
	public void setFpiPuro(Double fpiPuro) {
		this.fpiPuro = fpiPuro;
	}
	public Double getFpiPuroAcumulado() {
		return fpiPuroAcumulado;
	}
	public void setFpiPuroAcumulado(Double fpiPuroAcumulado) {
		this.fpiPuroAcumulado = fpiPuroAcumulado;
	}
	public Double getDpicPuro() {
		return dpicPuro;
	}
	public void setDpicPuro(Double dpicPuro) {
		this.dpicPuro = dpicPuro;
	}
	public Double getDpicPuroAcumulado() {
		return dpicPuroAcumulado;
	}
	public void setDpicPuroAcumulado(Double dpicPuroAcumulado) {
		this.dpicPuroAcumulado = dpicPuroAcumulado;
	}
	public Double getDpsPuro() {
		return dpsPuro;
	}
	public void setDpsPuro(Double dpsPuro) {
		this.dpsPuro = dpsPuro;
	}
	public Double getDpsPuroAcumulado() {
		return dpsPuroAcumulado;
	}
	public void setDpsPuroAcumulado(Double dpsPuroAcumulado) {
		this.dpsPuroAcumulado = dpsPuroAcumulado;
	}
	public Double getDpaPuro() {
		return dpaPuro;
	}
	public void setDpaPuro(Double dpaPuro) {
		this.dpaPuro = dpaPuro;
	}
	public Double getDpaPuroAcumulado() {
		return dpaPuroAcumulado;
	}
	public void setDpaPuroAcumulado(Double dpaPuroAcumulado) {
		this.dpaPuroAcumulado = dpaPuroAcumulado;
	}   
}
