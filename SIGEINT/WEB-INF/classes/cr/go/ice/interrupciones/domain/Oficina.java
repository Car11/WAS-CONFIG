package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.Oficina.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>Oficina.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 13/12/2006</p>
 * <p>Ultima actualización: 13/12/2006</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class Oficina implements Serializable {
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -8363102572525495100L;
    /**
	 * <code>codigoOficina</code> Codigo de Oficina
	 */
	private Integer codigoOficina;
	/**
	 * <code>nombreOficina</code> Nombre de Oficina
	 */
	private String nombreOficina;
	
	/**
	 * Constructor
	 */
	public Oficina(){}
	
	
	/**
	 * Comment for getCodigoOficina
	 * @return Codigo Oficina
	 */
	public Integer getCodigoOficina() {
		return codigoOficina;
	}

	
	/**
	 * Comment for getNombreOficina
	 * @return Nombre Oficina
	 */
	public String getNombreOficina() {
		return nombreOficina;
	}

	
	/**
	 * Comment for setCodigoOficina
	 * @param integer
	 */
	public void setCodigoOficina(Integer integer) {
		codigoOficina = integer;
	}

	
	/**
	 * Comment for setNombreOficina
	 * @param string
	 */
	public void setNombreOficina(String string) {
		nombreOficina = string;
	}
	
	public String getNombreOficinaCodigo()
	{
		return this.codigoOficina + " - " + this.nombreOficina;
	}
    
    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */ 
    public boolean equals(Object objeto) {
        boolean resultado;
        if (!(objeto instanceof Oficina)) {
            resultado = false;
        } else {
            Oficina oficina = (Oficina) objeto;
            return new EqualsBuilder().append(getCodigoOficina(), oficina.getCodigoOficina()).isEquals();
        }
        return resultado;
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder().append(getCodigoOficina()).toHashCode();
    }    
    
}