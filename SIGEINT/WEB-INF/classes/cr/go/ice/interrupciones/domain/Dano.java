package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


/**
 * <p>Clase cr.go.ice.interrupciones.domain.Dano.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>Dano.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 13/12/2006</p>
 * <p>Ultima actualización: 13/12/2006</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class Dano implements Serializable {
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -7232343079961406831L;
    /**
	 * <code>codigoDano</code> Codigo de Dano
	 */
	private Integer codigoDano;
	/**
	 * <code>nombreDano</code> Nombre de Dano
	 */
	private String nombreDano;

	
	/**
	 * Constructor
	 */
	public Dano(){}
	
	
	/**
	 * Comment for getCodigoDano
	 * @return Codigo de Dano
	 */
	public Integer getCodigoDano() {
		return codigoDano;
	}

	
	/**
	 * Comment for getNombreDano
	 * @return Nombre de Dano
	 */
	public String getNombreDano() {
		return nombreDano;
	}

	
	/**
	 * Comment for setCodigoDano
	 * @param integer
	 */
	public void setCodigoDano(Integer integer) {
		codigoDano = integer;
	}

	
	/**
	 * Comment for setNombreDano
	 * @param string
	 */
	public void setNombreDano(String string) {
		nombreDano = string;
	}
    
    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */ 
    public boolean equals(Object objeto) {
        boolean resultado;
        if (!(objeto instanceof Dano)) {
            resultado = false;
        } else {
            Dano dano = (Dano) objeto;
            return new EqualsBuilder().append(getCodigoDano(), dano.getCodigoDano()).isEquals();
        }
        return resultado;
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder().append(getCodigoDano()).toHashCode();
    }    

}
