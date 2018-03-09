package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.TipoVoltaje.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>TipoVoltaje.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 16/01/2007</p>
 * <p>Ultima actualización: 16/01/2007</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class TipoVoltaje implements Serializable {
	
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -6024114548924207108L;
    /**
	 * <code>tipoVoltajeID</code> Identificacion del Tipo de Voltaje
	 */
	private TipoVoltajeID tipoVoltajeID;
	/**
	 * <code>codigoDescripcion</code> Codigo de Descripcion
	 */
	private String codigoDescripcion;
	/**
	 * <code>nombreVoltaje</code> Nombre del Voltaje
	 */
	private String nombreVoltaje;
	
	/**
	 * Constructor
	 */
	public TipoVoltaje(){
        this.tipoVoltajeID = new TipoVoltajeID();
        this.codigoDescripcion = null;
        this.nombreVoltaje = null;
    }


	/**
	 * Comment for getCodigoDescripcion
	 * @return Codigo de Descripcion
	 */
	public String getCodigoDescripcion() {
		return codigoDescripcion;
	}


	/**
	 * Comment for getNombreVoltaje
	 * @return Nombre de Voltaje
	 */
	public String getNombreVoltaje() {
		return nombreVoltaje;
	}


	/**
	 * Comment for setCodigoDescripcion
	 * @param string
	 */
	public void setCodigoDescripcion(String string) {
		codigoDescripcion = string;
	}


	/**
	 * Comment for setNombreVoltaje
	 * @param string
	 */
	public void setNombreVoltaje(String string) {
		nombreVoltaje = string;
	}


	/**
	 * Comment for getTipoVoltajeID
	 * @return Identificacion de tipo de Voltaje
	 */
	public TipoVoltajeID getTipoVoltajeID() {
		return tipoVoltajeID;
	}


	/**
	 * Comment for setTipoVoltajeID
	 * @param tipoVoltajeID
	 */
	public void setTipoVoltajeID(TipoVoltajeID tipoVoltajeID) {
		this.tipoVoltajeID = tipoVoltajeID;
	}
	
	
	/** 
     * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object objeto) {
		 
		if (!(objeto instanceof TipoVoltaje)) {
			return false;
		} else {			
			 
		   TipoVoltaje tipoVoltaje = (TipoVoltaje) objeto;
		   return new EqualsBuilder().append(getTipoVoltajeID(), tipoVoltaje.getTipoVoltajeID()).isEquals();
		
		}
		 
	}


	/** 
     * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getTipoVoltajeID()).toHashCode();
	}
}
