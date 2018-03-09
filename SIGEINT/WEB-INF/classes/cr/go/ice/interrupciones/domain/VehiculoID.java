package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.VehiculoID.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>VehiculoID.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 26/03/2007</p>
 * <p>Ultima actualización: 26/03/2007</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class VehiculoID implements Serializable {

    /**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -8083810173767797623L;
    /**
	 * <code>aa</code> AA
	 */
	private Integer aa;
	/**
	 * <code>codigoOficina</code> Codigo de Oficina
	 */
	private Integer codigoOficina;
	/**
	 * <code>numeroInterrupcion</code> Numero de Interrupcion
	 */
	private Long numeroInterrupcion;
	/**
	 * <code>tipo</code> Tipo Vehiculo
	 */
	private Integer tipo;
	/**
	 * <code>numeroVehiculo</code> Numero de Vehiculo
	 */
	private Long numeroVehiculo;
	
	
	
	/**
	 * Comment for getAa
	 * @return AA
	 */
	public Integer getAa() {
		return aa;
	}
	
	/**
	 * Comment for setAa
	 * @param aa
	 */
	public void setAa(Integer aa) {
		this.aa = aa;
	}
	
	/**
	 * Comment for getCodigoOficina
	 * @return Codigo de Oficina
	 */
	public Integer getCodigoOficina() {
		return codigoOficina;
	}
	
	/**
	 * Comment for setCodigoOficina
	 * @param codigoOficina
	 */
	public void setCodigoOficina(Integer codigoOficina) {
		this.codigoOficina = codigoOficina;
	}
	
	/**
	 * Comment for getNumeroInterrupcion
	 * @return Numero de Interrupcion
	 */
	public Long getNumeroInterrupcion() {
		return numeroInterrupcion;
	}
	
	/**
	 * Comment for setNumeroInterrupcion
	 * @param numeroInterrupcion
	 */
	public void setNumeroInterrupcion(Long numeroInterrupcion) {
		this.numeroInterrupcion = numeroInterrupcion;
	}
	
	/**
	 * Comment for getNumeroVehiculo
	 * @return Numero de Vehiculo
	 */
	public Long getNumeroVehiculo() {
		return numeroVehiculo;
	}
	
	/**
	 * Comment for setNumeroVehiculo
	 * @param numeroVehiculo
	 */
	public void setNumeroVehiculo(Long numeroVehiculo) {
		this.numeroVehiculo = numeroVehiculo;
	}
	
	/**
	 * Comment for getTipo
	 * @return Tipo
	 */
	public Integer getTipo() {
		return tipo;
	}
	
	/**
	 * Comment for setTipo
	 * @param tipo
	 */
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	
	
	/** 
     * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof VehiculoID)) {
			return false;
		} else {
			VehiculoID vehiculoID = (VehiculoID) obj;
			return new EqualsBuilder().append(getAa(), vehiculoID.getAa())
				.append(getCodigoOficina(), vehiculoID.getCodigoOficina())
				.append(getNumeroInterrupcion(),vehiculoID.getNumeroInterrupcion())
				.append(getTipo(),vehiculoID.getTipo())
				.append(getNumeroVehiculo(), vehiculoID.getNumeroVehiculo()).isEquals();
		}
	}

    /**
     * @see java.lang.Object#hashCode()
     */
	public int hashCode() {
		return new HashCodeBuilder().append(getAa()).append(getCodigoOficina())
		.append(getNumeroInterrupcion()).append(getTipo()).append(getNumeroVehiculo()).toHashCode();
	}
}
