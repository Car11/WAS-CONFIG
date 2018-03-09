package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.SeccionID.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>SeccionID.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 23/02/2007</p>
 * <p>Ultima actualización: 23/02/2007</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class SeccionID implements Serializable {
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -4513388437474156241L;
    /**
	 * <code>seccion</code> Seccion
	 */
	private Long seccion;
	/**
	 * <code>subEstacion</code> Sub Estacion
	 */
	private Integer subEstacion;
	/**
	 * <code>circuito</code> Circuito
	 */
	private Integer circuito;
	
    /**
     * Constructor sobrecargado
     * @param subEstacion deseada
     * @param circuito deseada
     * @param seccion deseada
     */
    public SeccionID(Integer subEstacion, Integer circuito, Long seccion){
        this.subEstacion = subEstacion;
        this.circuito = circuito;
        this.seccion = seccion;
    }
    
    /**
     * Constructor por defecto
     */
    public SeccionID(){
        
    }
	
	/**
	 * @return Returns the circuito.
	 */
	public Integer getCircuito() {
		return circuito;
	}
	/**
	 * @param circuito The circuito to set.
	 */
	public void setCircuito(Integer circuito) {
		this.circuito = circuito;
	}
	/**
	 * @return Returns the seccion.
	 */
	public Long getSeccion() {
		return seccion;
	}
	/**
	 * @param seccion The seccion to set.
	 */
	public void setSeccion(Long seccion) {
		this.seccion = seccion;
	}
	/**
	 * @return Returns the subEstacion.
	 */
	public Integer getSubEstacion() {
		return subEstacion;
	}
	/**
	 * @param subEstacion The subEstacion to set.
	 */
	public void setSubEstacion(Integer subEstacion) {
		this.subEstacion = subEstacion;
	}
	
	/** 
     * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		boolean resultado;
		if (!(obj instanceof SeccionID)) {
			resultado = false;
		} else {
			SeccionID seccionID = (SeccionID) obj;			
			return new EqualsBuilder().append(getSeccion(), seccionID.getSeccion()).append(getSubEstacion(), 
					seccionID.getSubEstacion()).append(getCircuito(),seccionID.getCircuito()).isEquals();
					
		}
		return resultado;
	}
	/** 
     * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getSeccion()).append(getSubEstacion()).append(getCircuito()).toHashCode();
	}
}
