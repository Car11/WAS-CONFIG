package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>TODO <<Interface|Clase>> cr.go.ice.interrupciones.domain.ComportamientoADPIRid.java</p>
 * <p>Módulo (subsistema): TODO <<Sistema>></p>
 * <p>TODO (Descripción) La <<Interface|Clase>> <code>ComportamientoADPIRid.java</code>.</p>
 * <p>Fecha creación: 26/10/2010</p>
 * <p>Última actualización: 26/10/2010</p>
 * @author Vista Verde Tecnología (root)
 * @version 1.0
 */
public class ComportamientoADPIRid implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 2050028506508143335L;
	private Integer region;
    private Integer subregion;
    private Integer subestacion;
    private Integer circuito;
    private Integer tipoVoltaje;
    private Integer codigoVoltaje;
    private Integer anno;
    private Integer mes;
    
    
    
    /**
     * Constructor
     */
    public ComportamientoADPIRid() {
        super();
        this.region = null;
        this.subregion = null;
        this.subestacion = null;
        this.circuito = null;
        this.tipoVoltaje = null;
        this.codigoVoltaje = null;
        this.anno = null;
        this.mes = null;
    }
    /**
     * Método accesor del atributo anno.
     * @return Retorna el atributo anno.
     */
    public Integer getAnno() {
        return this.anno;
    }
    /**
     * Método modificador del atributo anno.
     * @param anno El dato para modificar el atributo anno.
     */
    public void setAnno(Integer anno) {
        this.anno = anno;
    }
    /**
     * Método accesor del atributo circuito.
     * @return Retorna el atributo circuito.
     */
    public Integer getCircuito() {
        return this.circuito;
    }
    /**
     * Método modificador del atributo circuito.
     * @param circuito El dato para modificar el atributo circuito.
     */
    public void setCircuito(Integer circuito) {
        this.circuito = circuito;
    }
    /**
     * Método accesor del atributo codigoVoltaje.
     * @return Retorna el atributo codigoVoltaje.
     */
    public Integer getCodigoVoltaje() {
        return this.codigoVoltaje;
    }
    /**
     * Método modificador del atributo codigoVoltaje.
     * @param codigoVoltaje El dato para modificar el atributo codigoVoltaje.
     */
    public void setCodigoVoltaje(Integer codigoVoltaje) {
        this.codigoVoltaje = codigoVoltaje;
    }
    /**
     * Método accesor del atributo mes.
     * @return Retorna el atributo mes.
     */
    public Integer getMes() {
        return this.mes;
    }
    /**
     * Método modificador del atributo mes.
     * @param mes El dato para modificar el atributo mes.
     */
    public void setMes(Integer mes) {
        this.mes = mes;
    }
    /**
     * Método accesor del atributo region.
     * @return Retorna el atributo region.
     */
    public Integer getRegion() {
        return this.region;
    }
    /**
     * Método modificador del atributo region.
     * @param region El dato para modificar el atributo region.
     */
    public void setRegion(Integer region) {
        this.region = region;
    }
    /**
     * Método accesor del atributo subestacion.
     * @return Retorna el atributo subestacion.
     */
    public Integer getSubestacion() {
        return this.subestacion;
    }
    /**
     * Método modificador del atributo subestacion.
     * @param subestacion El dato para modificar el atributo subestacion.
     */
    public void setSubestacion(Integer subestacion) {
        this.subestacion = subestacion;
    }
    /**
     * Método accesor del atributo subregion.
     * @return Retorna el atributo subregion.
     */
    public Integer getSubregion() {
        return this.subregion;
    }
    /**
     * Método modificador del atributo subregion.
     * @param subregion El dato para modificar el atributo subregion.
     */
    public void setSubregion(Integer subregion) {
        this.subregion = subregion;
    }
    /**
     * Método accesor del atributo tipoVoltaje.
     * @return Retorna el atributo tipoVoltaje.
     */
    public Integer getTipoVoltaje() {
        return this.tipoVoltaje;
    }
    /**
     * Método modificador del atributo tipoVoltaje.
     * @param tipoVoltaje El dato para modificar el atributo tipoVoltaje.
     */
    public void setTipoVoltaje(Integer tipoVoltaje) {
        this.tipoVoltaje = tipoVoltaje;
    }
    
    /** 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        boolean resultado;
        if (!(obj instanceof ComportamientoADPIRid)) {
            resultado = false;
        } else {
            ComportamientoADPIRid comportamientoADPIRid = (ComportamientoADPIRid) obj;
            resultado = new EqualsBuilder()
                        .append(getAnno(), comportamientoADPIRid.getAnno())
                        .append(getCircuito(), comportamientoADPIRid.getCircuito())
                        .append(getCodigoVoltaje(), comportamientoADPIRid.getCodigoVoltaje())
                        .append(getMes(), comportamientoADPIRid.getMes())
                        .append(getRegion(), comportamientoADPIRid.getRegion())
                        .append(getSubestacion(), comportamientoADPIRid.getSubestacion())
                        .append(getSubregion(), comportamientoADPIRid.getSubregion())
                        .append(getTipoVoltaje(), comportamientoADPIRid.getTipoVoltaje())
                        .isEquals();
        }
        return resultado;
    }
    /** 
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder()
                    .append(getAnno())
                    .append(getCircuito())
                    .append(getCodigoVoltaje())
                    .append(getMes())
                    .append(getRegion())
                    .append(getSubestacion())
                    .append(getSubregion())
                    .append(getTipoVoltaje())
                    .toHashCode();
    }        
}
