package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.Pueblo.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>Pueblo.java</code>Permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 13/04/2007</p>
 * <p>Ultima actualización: 13/04/2007</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class Pueblo implements Serializable{

    /**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -8771160384369011490L;

    /** atributo <code>ORDEN_CODIGO</code>*/
    public static final Integer ORDEN_CODIGO = new Integer(1);
    
    /** atributo <code>ORDEN_NOMBRE</code>*/
    public static final Integer ORDEN_NOMBRE = new Integer(2);
	/**
	 * <code>codigoPueblo</code> Código del pueblo
	 */
	private Integer codigoPueblo;
	/**
	 * <code>nombrePueblo</code> Nombre del pueblo
	 */
	private String nombrePueblo;
	
	
	/**
	 * Metodo accesor de codigoPueblo.
	 * @return Retorna el codigoPueblo.
	 */
	public Integer getCodigoPueblo() {
		return codigoPueblo;
	}
	/**
	 * Metodo modificador de codigoPueblo.
	 * @param codigoPueblo El codigoPueblo a modificar.
	 */
	public void setCodigoPueblo(Integer codigoPueblo) {
		this.codigoPueblo = codigoPueblo;
	}
	/**
	 * Metodo accesor de nombrePueblo.
	 * @return Retorna el nombrePueblo.
	 */
	public String getNombrePueblo() {
		return nombrePueblo;
	}
	/**
	 * Metodo modificador de nombrePueblo.
	 * @param nombrePueblo El nombrePueblo a modificar.
	 */
	public void setNombrePueblo(String nombrePueblo) {
		this.nombrePueblo = nombrePueblo;
	}
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		boolean resultado;
		if (!(obj instanceof Pueblo)) {
			resultado = false;
		} else {
			Pueblo pueblo = (Pueblo) obj;
			resultado = new EqualsBuilder().append(getCodigoPueblo(), pueblo.getCodigoPueblo()).isEquals();
		}
		return resultado;
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getCodigoPueblo()).toHashCode();
	}
}
