package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 
 * <p>TODO <<Interface|Clase>> cr.go.ice.interrupciones.domain.CambioSeccionID.java</p>
 * <p>Módulo (subsistema): TODO <<Sistema>></p>
 * <p>TODO (Descripción) La <<Interface|Clase>> <code>CambioSeccionID.java</code>.</p>
 * <p>Fecha creación: 01/10/2012</p>
 * <p>Última actualización: 01/10/2012</p>
 * @author Vista Verde Tecnología (eperaza)
 * @version 1.0
 */
public class CambioSeccionID implements Serializable {
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
	private String cedula;
	
    /**
     * Constructor sobrecargado
     * @param subEstacion deseada
     * @param circuito deseada
     * @param seccion deseada
     */
    public CambioSeccionID(Integer subEstacion, Integer circuito, Long seccion){
        this.subEstacion = subEstacion;
        this.circuito = circuito;
        this.seccion = seccion;
    }
    
    /**
     * Constructor por defecto
     */
    public CambioSeccionID(){
        this.cedula = "";
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
	 *//*
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
	*//** 
     * @see java.lang.Object#hashCode()
	 *//*
	public int hashCode() {
		return new HashCodeBuilder().append(getSeccion()).append(getSubEstacion()).append(getCircuito()).toHashCode();
	}*/

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.circuito == null) ? 0 : this.circuito.hashCode());
        result = prime * result + ((this.seccion == null) ? 0 : this.seccion.hashCode());
        result = prime * result + ((this.subEstacion == null) ? 0 : this.subEstacion.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CambioSeccionID other = (CambioSeccionID) obj;
        if (this.circuito == null) {
            if (other.circuito != null)
                return false;
        } else if (!this.circuito.equals(other.circuito))
            return false;
        if (this.seccion == null) {
            if (other.seccion != null)
                return false;
        } else if (!this.seccion.equals(other.seccion))
            return false;
        if (this.subEstacion == null) {
            if (other.subEstacion != null)
                return false;
        } else if (!this.subEstacion.equals(other.subEstacion))
            return false;
        return true;
    }

    /**
     * Método accesor del atributo cedula.
     * @return Retorna el atributo cedula.
     */
    public String getCedula() {
        return this.cedula;
    }

    /**
     * Método modificador del atributo cedula.
     * @param cedula El dato para modificar el atributo cedula.
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
}
