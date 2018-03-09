package cr.go.ice.interrupciones.domain;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import cr.go.ice.interrupciones.domain.IndiceGlobalRegionId;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.IndiceGlobalRegion.java</p>
 * <p>Modulo (subsistema): Proyecto</p>
 * <p>Fecha creacion: 10/04/2010</p>
 * <p>Ultima actualizacion: 10/04/2010</p>
 * @author VVS Code Generator
 * @version 1.1
 */
public class IndiceGlobalRegion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3700643308620371834L;
	private IndiceGlobalRegionId id;
	private Integer numeroSalidasAcumuladas;
	private Integer numeroSalidas;
	private Double fpi;
	private Double dpic;
	private Double dps;
	private Double dpir;
	private Double adpic;
	private Double afpi;
	private Double adps;
	private Double adpir;
	private Double dpa;
	private Double dpaAcumulado;
	private Double horasAbonado;
	private Integer abonadoAfectado;
	private Long abonadoAfectadoAcumulado;
	private Double duracion;
	private Double duracionAcumulado;
	private Double horasAbonadoAcumulado;
	private Double interrupcionKMAcumulado;
	private Double interrupcionKM;

	/**
 	* Constructor por defecto 
 	*/
	public IndiceGlobalRegion(){
		this.id = null;
		this.numeroSalidasAcumuladas = null;
		this.numeroSalidas = null;
		this.fpi = null;
		this.dpic = null;
		this.dps = null;
		this.dpir = null;
		this.adpic = null;
		this.afpi = null;
		this.adps = null;
		this.adpir = null;
		this.dpa = null;
		this.dpaAcumulado = null;
		this.horasAbonado = null;
		this.abonadoAfectado = null;
		this.abonadoAfectadoAcumulado = null;
		this.duracion = null;
		this.duracionAcumulado = null;
		this.horasAbonadoAcumulado = null;
		this.interrupcionKMAcumulado = null;
		this.interrupcionKM = null;
	}
	/**
 	* Constructor sobrecargado 
 	* @param id
 	* @param numeroSalidasAcumuladas
 	* @param numeroSalidas
 	* @param fpi
 	* @param dpic
 	* @param dps
 	* @param dpir
 	* @param adpic
 	* @param afpi
 	* @param adps
 	* @param adpir
 	* @param dpa
 	* @param dpaAcumulado
 	* @param horasAbonado
 	* @param abonadoAfectado
 	* @param abonadoAfectadoAcumulado
 	* @param duracion
 	* @param duracionAcumulado
 	* @param horasAbonadoAcumulado
 	* @param interrupcionKMAcumulado
 	* @param interrupcionKM
 	*/
	public IndiceGlobalRegion(IndiceGlobalRegionId id,Integer numeroSalidasAcumuladas,Integer numeroSalidas,Double fpi,Double dpic,Double dps,Double dpir,Double adpic,Double afpi,Double adps,Double adpir,Double dpa,Double dpaAcumulado,Double horasAbonado,Integer abonadoAfectado,Long abonadoAfectadoAcumulado,Double duracion,Double duracionAcumulado,Double horasAbonadoAcumulado,Double interrupcionKMAcumulado,Double interrupcionKM){
		this.id = id;
		this.numeroSalidasAcumuladas = numeroSalidasAcumuladas;
		this.numeroSalidas = numeroSalidas;
		this.fpi = fpi;
		this.dpic = dpic;
		this.dps = dps;
		this.dpir = dpir;
		this.adpic = adpic;
		this.afpi = afpi;
		this.adps = adps;
		this.adpir = adpir;
		this.dpa = dpa;
		this.dpaAcumulado = dpaAcumulado;
		this.horasAbonado = horasAbonado;
		this.abonadoAfectado = abonadoAfectado;
		this.abonadoAfectadoAcumulado = abonadoAfectadoAcumulado;
		this.duracion = duracion;
		this.duracionAcumulado = duracionAcumulado;
		this.horasAbonadoAcumulado = horasAbonadoAcumulado;
		this.interrupcionKMAcumulado = interrupcionKMAcumulado;
		this.interrupcionKM = interrupcionKM;
	}

	/**
 	* Metodo accesor de id
 	* @return Retorna el id
 	*/
	public IndiceGlobalRegionId getId(){
		return this.id;
	}
	/**
 	* Metodo modificador de id
 	* @param id El id a modificar
 	*/
	public void setId(IndiceGlobalRegionId id){
		this.id = id;
	}
    
    

	/**
 	* @see java.lang.Object#equals(java.lang.Object)
 	*/
	public boolean equals(Object obj) {
		if(obj instanceof IndiceGlobalRegion){
			IndiceGlobalRegion indiceGlobalRegion = (IndiceGlobalRegion)obj;
			EqualsBuilder eb = new EqualsBuilder();
			eb.append(this.id, indiceGlobalRegion.getId());
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
    /**
     * Método accesor del atributo abonadoAfectado.
     * @return Retorna el atributo abonadoAfectado.
     */
    public Integer getAbonadoAfectado() {
        return this.abonadoAfectado;
    }
    /**
     * Método modificador del atributo abonadoAfectado.
     * @param abonadoAfectado El dato para modificar el atributo abonadoAfectado.
     */
    public void setAbonadoAfectado(Integer abonadoAfectado) {
        this.abonadoAfectado = abonadoAfectado;
    }
    /**
     * Método accesor del atributo abonadoAfectadoAcumulado.
     * @return Retorna el atributo abonadoAfectadoAcumulado.
     */
    public Long getAbonadoAfectadoAcumulado() {
        return this.abonadoAfectadoAcumulado;
    }
    /**
     * Método modificador del atributo abonadoAfectadoAcumulado.
     * @param abonadoAfectadoAcumulado El dato para modificar el atributo abonadoAfectadoAcumulado.
     */
    public void setAbonadoAfectadoAcumulado(Long abonadoAfectadoAcumulado) {
        this.abonadoAfectadoAcumulado = abonadoAfectadoAcumulado;
    }
    /**
     * Método accesor del atributo adpic.
     * @return Retorna el atributo adpic.
     */
    public Double getAdpic() {
        return this.adpic;
    }
    /**
     * Método modificador del atributo adpic.
     * @param adpic El dato para modificar el atributo adpic.
     */
    public void setAdpic(Double adpic) {
        this.adpic = adpic;
    }
    /**
     * Método accesor del atributo adpir.
     * @return Retorna el atributo adpir.
     */
    public Double getAdpir() {
        return this.adpir;
    }
    /**
     * Método modificador del atributo adpir.
     * @param adpir El dato para modificar el atributo adpir.
     */
    public void setAdpir(Double adpir) {
        this.adpir = adpir;
    }
    /**
     * Método accesor del atributo adps.
     * @return Retorna el atributo adps.
     */
    public Double getAdps() {
        return this.adps;
    }
    /**
     * Método modificador del atributo adps.
     * @param adps El dato para modificar el atributo adps.
     */
    public void setAdps(Double adps) {
        this.adps = adps;
    }
    /**
     * Método accesor del atributo afpi.
     * @return Retorna el atributo afpi.
     */
    public Double getAfpi() {
        return this.afpi;
    }
    /**
     * Método modificador del atributo afpi.
     * @param afpi El dato para modificar el atributo afpi.
     */
    public void setAfpi(Double afpi) {
        this.afpi = afpi;
    }
    /**
     * Método accesor del atributo dpa.
     * @return Retorna el atributo dpa.
     */
    public Double getDpa() {
        return this.dpa;
    }
    /**
     * Método modificador del atributo dpa.
     * @param dpa El dato para modificar el atributo dpa.
     */
    public void setDpa(Double dpa) {
        this.dpa = dpa;
    }
    /**
     * Método accesor del atributo dpaAcumulado.
     * @return Retorna el atributo dpaAcumulado.
     */
    public Double getDpaAcumulado() {
        return this.dpaAcumulado;
    }
    /**
     * Método modificador del atributo dpaAcumulado.
     * @param dpaAcumulado El dato para modificar el atributo dpaAcumulado.
     */
    public void setDpaAcumulado(Double dpaAcumulado) {
        this.dpaAcumulado = dpaAcumulado;
    }
    /**
     * Método accesor del atributo dpic.
     * @return Retorna el atributo dpic.
     */
    public Double getDpic() {
        return this.dpic;
    }
    /**
     * Método modificador del atributo dpic.
     * @param dpic El dato para modificar el atributo dpic.
     */
    public void setDpic(Double dpic) {
        this.dpic = dpic;
    }
    /**
     * Método accesor del atributo dpir.
     * @return Retorna el atributo dpir.
     */
    public Double getDpir() {
        return this.dpir;
    }
    /**
     * Método modificador del atributo dpir.
     * @param dpir El dato para modificar el atributo dpir.
     */
    public void setDpir(Double dpir) {
        this.dpir = dpir;
    }
    /**
     * Método accesor del atributo dps.
     * @return Retorna el atributo dps.
     */
    public Double getDps() {
        return this.dps;
    }
    /**
     * Método modificador del atributo dps.
     * @param dps El dato para modificar el atributo dps.
     */
    public void setDps(Double dps) {
        this.dps = dps;
    }
    /**
     * Método accesor del atributo duracion.
     * @return Retorna el atributo duracion.
     */
    public Double getDuracion() {
        return this.duracion;
    }
    /**
     * Método modificador del atributo duracion.
     * @param duracion El dato para modificar el atributo duracion.
     */
    public void setDuracion(Double duracion) {
        this.duracion = duracion;
    }
    /**
     * Método accesor del atributo duracionAcumulado.
     * @return Retorna el atributo duracionAcumulado.
     */
    public Double getDuracionAcumulado() {
        return this.duracionAcumulado;
    }
    /**
     * Método modificador del atributo duracionAcumulado.
     * @param duracionAcumulado El dato para modificar el atributo duracionAcumulado.
     */
    public void setDuracionAcumulado(Double duracionAcumulado) {
        this.duracionAcumulado = duracionAcumulado;
    }
    /**
     * Método accesor del atributo fpi.
     * @return Retorna el atributo fpi.
     */
    public Double getFpi() {
        return this.fpi;
    }
    /**
     * Método modificador del atributo fpi.
     * @param fpi El dato para modificar el atributo fpi.
     */
    public void setFpi(Double fpi) {
        this.fpi = fpi;
    }
    /**
     * Método accesor del atributo horasAbonado.
     * @return Retorna el atributo horasAbonado.
     */
    public Double getHorasAbonado() {
        return this.horasAbonado;
    }
    /**
     * Método modificador del atributo horasAbonado.
     * @param horasAbonado El dato para modificar el atributo horasAbonado.
     */
    public void setHorasAbonado(Double horasAbonado) {
        this.horasAbonado = horasAbonado;
    }
    /**
     * Método accesor del atributo horasAbonadoAcumulado.
     * @return Retorna el atributo horasAbonadoAcumulado.
     */
    public Double getHorasAbonadoAcumulado() {
        return this.horasAbonadoAcumulado;
    }
    /**
     * Método modificador del atributo horasAbonadoAcumulado.
     * @param horasAbonadoAcumulado El dato para modificar el atributo horasAbonadoAcumulado.
     */
    public void setHorasAbonadoAcumulado(Double horasAbonadoAcumulado) {
        this.horasAbonadoAcumulado = horasAbonadoAcumulado;
    }
    /**
     * Método accesor del atributo interrupcionKM.
     * @return Retorna el atributo interrupcionKM.
     */
    public Double getInterrupcionKM() {
        return this.interrupcionKM;
    }
    /**
     * Método modificador del atributo interrupcionKM.
     * @param interrupcionKM El dato para modificar el atributo interrupcionKM.
     */
    public void setInterrupcionKM(Double interrupcionKM) {
        this.interrupcionKM = interrupcionKM;
    }
    /**
     * Método accesor del atributo interrupcionKMAcumulado.
     * @return Retorna el atributo interrupcionKMAcumulado.
     */
    public Double getInterrupcionKMAcumulado() {
        return this.interrupcionKMAcumulado;
    }
    /**
     * Método modificador del atributo interrupcionKMAcumulado.
     * @param interrupcionKMAcumulado El dato para modificar el atributo interrupcionKMAcumulado.
     */
    public void setInterrupcionKMAcumulado(Double interrupcionKMAcumulado) {
        this.interrupcionKMAcumulado = interrupcionKMAcumulado;
    }
    /**
     * Método accesor del atributo numeroSalidas.
     * @return Retorna el atributo numeroSalidas.
     */
    public Integer getNumeroSalidas() {
        return this.numeroSalidas;
    }
    /**
     * Método modificador del atributo numeroSalidas.
     * @param numeroSalidas El dato para modificar el atributo numeroSalidas.
     */
    public void setNumeroSalidas(Integer numeroSalidas) {
        this.numeroSalidas = numeroSalidas;
    }
    /**
     * Método accesor del atributo numeroSalidasAcumuladas.
     * @return Retorna el atributo numeroSalidasAcumuladas.
     */
    public Integer getNumeroSalidasAcumuladas() {
        return this.numeroSalidasAcumuladas;
    }
    /**
     * Método modificador del atributo numeroSalidasAcumuladas.
     * @param numeroSalidasAcumuladas El dato para modificar el atributo numeroSalidasAcumuladas.
     */
    public void setNumeroSalidasAcumuladas(Integer numeroSalidasAcumuladas) {
        this.numeroSalidasAcumuladas = numeroSalidasAcumuladas;
    }

}
