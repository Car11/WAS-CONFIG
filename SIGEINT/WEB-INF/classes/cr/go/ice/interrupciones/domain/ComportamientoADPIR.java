package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>TODO <<Interface|Clase>> cr.go.ice.interrupciones.domain.ComportamientoADPIR.java</p>
 * <p>Módulo (subsistema): TODO <<Sistema>></p>
 * <p>TODO (Descripción) La <<Interface|Clase>> <code>ComportamientoADPIR.java</code>.</p>
 * <p>Fecha creación: 26/10/2010</p>
 * <p>Última actualización: 26/10/2010</p>
 * @author Vista Verde Tecnología (root)
 * @version 1.0
 */
public class ComportamientoADPIR implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 5327072518082136033L;

	private ComportamientoADPIRid id;
    
    private Integer numeroSalidasAcum;
    private Integer numeroSalidas;
    
    private Double FPI;
    private Double DPIC;
    private Double DPS;
    private Double DPIR;
    
    private Double ADPIC;
    private Double AFPI;
    private Double ADPS;
    private Double ADPIR;
    
    private Double DPA;
    private Double DPAacum;
    private Double duracionAcum;
    
    private Long ABOafectaAcum;
    private Double duracion;
    private Long ABOafecta;
    private Double horasABOacum;
    private Double horasABO;
    private Double interrupcionKilometroAcum;
    
    private Double interrupcionKilometro;

    
    /**
     * Constructor
     */
    public ComportamientoADPIR() {
        super();
        this.id = new ComportamientoADPIRid();
        this.numeroSalidasAcum = null;
        this.numeroSalidas = null;
        this.FPI = null;
        this.DPIC = null;
        this.DPS = null;
        this.DPIR = null;
        this.ADPIC = null;
        this.AFPI = null;
        this.ADPS = null;
        this.ADPIR = null;
        this.DPA = null;
        this.DPAacum = null;
        this.duracionAcum = null;
        this.ABOafectaAcum = null;
        this.duracion = null;
        this.ABOafecta = null;
        this.horasABOacum = null;
        this.horasABO = null;
        this.interrupcionKilometroAcum = null;
        this.interrupcionKilometro = null;
    }

    /**
     * Método accesor del atributo aBOafecta.
     * @return Retorna el atributo aBOafecta.
     */
    public Long getABOafecta() {
        return this.ABOafecta;
    }

    /**
     * Método modificador del atributo aBOafecta.
     * @param oafecta El dato para modificar el atributo aBOafecta.
     */
    public void setABOafecta(Long oafecta) {
        this.ABOafecta = oafecta;
    }

    /**
     * Método accesor del atributo aBOafectaAcum.
     * @return Retorna el atributo aBOafectaAcum.
     */
    public Long getABOafectaAcum() {
        return this.ABOafectaAcum;
    }

    /**
     * Método modificador del atributo aBOafectaAcum.
     * @param oafectaAcum El dato para modificar el atributo aBOafectaAcum.
     */
    public void setABOafectaAcum(Long oafectaAcum) {
        this.ABOafectaAcum = oafectaAcum;
    }

    /**
     * Método accesor del atributo aDPIC.
     * @return Retorna el atributo aDPIC.
     */
    public Double getADPIC() {
        return this.ADPIC;
    }

    /**
     * Método modificador del atributo aDPIC.
     * @param adpic El dato para modificar el atributo aDPIC.
     */
    public void setADPIC(Double adpic) {
        this.ADPIC = adpic;
    }

    /**
     * Método accesor del atributo aDPIR.
     * @return Retorna el atributo aDPIR.
     */
    public Double getADPIR() {
        return this.ADPIR;
    }

    /**
     * Método modificador del atributo aDPIR.
     * @param adpir El dato para modificar el atributo aDPIR.
     */
    public void setADPIR(Double adpir) {
        this.ADPIR = adpir;
    }

    /**
     * Método accesor del atributo aDPS.
     * @return Retorna el atributo aDPS.
     */
    public Double getADPS() {
        return this.ADPS;
    }

    /**
     * Método modificador del atributo aDPS.
     * @param adps El dato para modificar el atributo aDPS.
     */
    public void setADPS(Double adps) {
        this.ADPS = adps;
    }

    /**
     * Método accesor del atributo aFPI.
     * @return Retorna el atributo aFPI.
     */
    public Double getAFPI() {
        return this.AFPI;
    }

    /**
     * Método modificador del atributo aFPI.
     * @param afpi El dato para modificar el atributo aFPI.
     */
    public void setAFPI(Double afpi) {
        this.AFPI = afpi;
    }

    /**
     * Método accesor del atributo dPA.
     * @return Retorna el atributo dPA.
     */
    public Double getDPA() {
        return this.DPA;
    }

    /**
     * Método modificador del atributo dPA.
     * @param dpa El dato para modificar el atributo dPA.
     */
    public void setDPA(Double dpa) {
        this.DPA = dpa;
    }

    /**
     * Método accesor del atributo dPAacum.
     * @return Retorna el atributo dPAacum.
     */
    public Double getDPAacum() {
        return this.DPAacum;
    }

    /**
     * Método modificador del atributo dPAacum.
     * @param aacum El dato para modificar el atributo dPAacum.
     */
    public void setDPAacum(Double aacum) {
        this.DPAacum = aacum;
    }

    /**
     * Método accesor del atributo dPIC.
     * @return Retorna el atributo dPIC.
     */
    public Double getDPIC() {
        return this.DPIC;
    }

    /**
     * Método modificador del atributo dPIC.
     * @param dpic El dato para modificar el atributo dPIC.
     */
    public void setDPIC(Double dpic) {
        this.DPIC = dpic;
    }

    /**
     * Método accesor del atributo dPIR.
     * @return Retorna el atributo dPIR.
     */
    public Double getDPIR() {
        return this.DPIR;
    }

    /**
     * Método modificador del atributo dPIR.
     * @param dpir El dato para modificar el atributo dPIR.
     */
    public void setDPIR(Double dpir) {
        this.DPIR = dpir;
    }

    /**
     * Método accesor del atributo dPS.
     * @return Retorna el atributo dPS.
     */
    public Double getDPS() {
        return this.DPS;
    }

    /**
     * Método modificador del atributo dPS.
     * @param dps El dato para modificar el atributo dPS.
     */
    public void setDPS(Double dps) {
        this.DPS = dps;
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
     * Método accesor del atributo duracionAcum.
     * @return Retorna el atributo duracionAcum.
     */
    public Double getDuracionAcum() {
        return this.duracionAcum;
    }

    /**
     * Método modificador del atributo duracionAcum.
     * @param duracionAcum El dato para modificar el atributo duracionAcum.
     */
    public void setDuracionAcum(Double duracionAcum) {
        this.duracionAcum = duracionAcum;
    }

    /**
     * Método accesor del atributo fPI.
     * @return Retorna el atributo fPI.
     */
    public Double getFPI() {
        return this.FPI;
    }

    /**
     * Método modificador del atributo fPI.
     * @param fpi El dato para modificar el atributo fPI.
     */
    public void setFPI(Double fpi) {
        this.FPI = fpi;
    }

    /**
     * Método accesor del atributo horasABO.
     * @return Retorna el atributo horasABO.
     */
    public Double getHorasABO() {
        return this.horasABO;
    }

    /**
     * Método modificador del atributo horasABO.
     * @param horasABO El dato para modificar el atributo horasABO.
     */
    public void setHorasABO(Double horasABO) {
        this.horasABO = horasABO;
    }

    /**
     * Método accesor del atributo horasABOacum.
     * @return Retorna el atributo horasABOacum.
     */
    public Double getHorasABOacum() {
        return this.horasABOacum;
    }

    /**
     * Método modificador del atributo horasABOacum.
     * @param horasABOacum El dato para modificar el atributo horasABOacum.
     */
    public void setHorasABOacum(Double horasABOacum) {
        this.horasABOacum = horasABOacum;
    }

    /**
     * Método accesor del atributo id.
     * @return Retorna el atributo id.
     */
    public ComportamientoADPIRid getId() {
        return this.id;
    }

    /**
     * Método modificador del atributo id.
     * @param id El dato para modificar el atributo id.
     */
    public void setId(ComportamientoADPIRid id) {
        this.id = id;
    }

    /**
     * Método accesor del atributo interrupcionKilometro.
     * @return Retorna el atributo interrupcionKilometro.
     */
    public Double getInterrupcionKilometro() {
        return this.interrupcionKilometro;
    }

    /**
     * Método modificador del atributo interrupcionKilometro.
     * @param interrupcionKilometro El dato para modificar el atributo interrupcionKilometro.
     */
    public void setInterrupcionKilometro(Double interrupcionKilometro) {
        this.interrupcionKilometro = interrupcionKilometro;
    }

    /**
     * Método accesor del atributo interrupcionKilometroAcum.
     * @return Retorna el atributo interrupcionKilometroAcum.
     */
    public Double getInterrupcionKilometroAcum() {
        return this.interrupcionKilometroAcum;
    }

    /**
     * Método modificador del atributo interrupcionKilometroAcum.
     * @param interrupcionKilometroAcum El dato para modificar el atributo interrupcionKilometroAcum.
     */
    public void setInterrupcionKilometroAcum(Double interrupcionKilometroAcum) {
        this.interrupcionKilometroAcum = interrupcionKilometroAcum;
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
     * Método accesor del atributo numeroSalidasAcum.
     * @return Retorna el atributo numeroSalidasAcum.
     */
    public Integer getNumeroSalidasAcum() {
        return this.numeroSalidasAcum;
    }

    /**
     * Método modificador del atributo numeroSalidasAcum.
     * @param numeroSalidasAcum El dato para modificar el atributo numeroSalidasAcum.
     */
    public void setNumeroSalidasAcum(Integer numeroSalidasAcum) {
        this.numeroSalidasAcum = numeroSalidasAcum;
    }
    
    
    /** 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        boolean resultado;
        if (!(obj instanceof ComportamientoADPIR)) {
            resultado = false;
        } else {
            ComportamientoADPIR comportamientoADPIR = (ComportamientoADPIR) obj;
            resultado = new EqualsBuilder().append(getId(), comportamientoADPIR.getId()).isEquals();
        }
        return resultado;
    }
    /** 
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder().append(getId()).toHashCode();
    }
}
