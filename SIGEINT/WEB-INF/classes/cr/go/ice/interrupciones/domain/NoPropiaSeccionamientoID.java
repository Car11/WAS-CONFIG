package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.NoPropiaSeccionamientoID.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>NoPropiaSeccionamientoID.java</code>Permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 11/04/2007</p>
 * <p>Ultima actualización: 11/04/2007</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class NoPropiaSeccionamientoID implements Serializable {

	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -5945908463350941040L;
    /**
	 * <code>aa</code> Año
	 */
	private Integer aa;
	/**
	 * <code>codigoOficina</code> Codigo de la oficina
	 */
	private Integer codigoOficina;
	/**
	 * <code>numeroInterrupcion</code> Numero de la interrupcion
	 */
	private Long numeroInterrupcion;
	/**
	 * <code>seccion</code> Codigo de la seccion
	 */
	private Long seccion;

	
	/**
	 * Metodo accesor de aa.
	 * @return Retorna el aa.
	 */
	public Integer getAa() {
		return aa;
	}
	/**
	 * Metodo modificador de aa.
	 * @param aa El aa a modificar.
	 */
	public void setAa(Integer aa) {
		this.aa = aa;
	}
	/**
	 * Metodo accesor de codigoOficina.
	 * @return Retorna el codigoOficina.
	 */
	public Integer getCodigoOficina() {
		return codigoOficina;
	}
	/**
	 * Metodo modificador de codigoOficina.
	 * @param codigoOficina El codigoOficina a modificar.
	 */
	public void setCodigoOficina(Integer codigoOficina) {
		this.codigoOficina = codigoOficina;
	}
	/**
	 * Metodo accesor de numeroInterrupcion.
	 * @return Retorna el numeroInterrupcion.
	 */
	public Long getNumeroInterrupcion() {
		return numeroInterrupcion;
	}
	/**
	 * Metodo modificador de numeroInterrupcion.
	 * @param numeroInterrupcion El numeroInterrupcion a modificar.
	 */
	public void setNumeroInterrupcion(Long numeroInterrupcion) {
		this.numeroInterrupcion = numeroInterrupcion;
	}
	/**
	 * Metodo accesor de seccion.
	 * @return Retorna el seccion.
	 */
	public Long getSeccion() {
		return seccion;
	}
	/**
	 * Metodo modificador de seccion.
	 * @param seccion El seccion a modificar.
	 */
	public void setSeccion(Long seccion) {
		this.seccion = seccion;
	}	
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */	
	public boolean equals(Object objeto) {
		boolean resultado;
		if (!(objeto instanceof NoPropiaSeccionamientoID)) {
			resultado = false;
		} else {
			NoPropiaSeccionamientoID noPropiaSeccionamientoID = (NoPropiaSeccionamientoID) objeto;
			return new EqualsBuilder().append(getAa(), noPropiaSeccionamientoID.getAa()).append(getCodigoOficina(), 
					noPropiaSeccionamientoID.getCodigoOficina()).append(getNumeroInterrupcion(),noPropiaSeccionamientoID.getNumeroInterrupcion()).append(getSeccion(),noPropiaSeccionamientoID.getSeccion()).isEquals();
		}
		return resultado;
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getAa()).append(getCodigoOficina()).append(getNumeroInterrupcion()).append(getSeccion()).toHashCode();
	}

}
