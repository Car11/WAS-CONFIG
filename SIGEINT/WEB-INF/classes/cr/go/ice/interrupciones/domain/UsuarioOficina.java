package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


/**
 * <p>Clase cr.go.ice.interrupciones.domain.UsuarioOficina.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>UsuarioOficina.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 14/06/2017</p>
 * <p>Ultima actualización: 14/06/2017</p>
 * @author Rossmon (rhidalgo)
 * @version 1.1
 */
public class UsuarioOficina implements Serializable {

    /**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -3889281028085484752L;
    private UsuarioOficinaId id;
	
    
	public UsuarioOficina()
	{
		this.id = new UsuarioOficinaId();
	}
    
    public UsuarioOficinaId getId() {
		return id;
	}

	public void setId(UsuarioOficinaId id) {
		this.id = id;
	}
	
	/**
     * @see java.lang.Object#equals(java.lang.Object)
     */ 
    public boolean equals(Object objeto) {
        boolean resultado;
        if (!(objeto instanceof UsuarioOficina)) {
            resultado = false;
        }else
        {
            UsuarioOficina usuarioOficina = (UsuarioOficina) objeto;
            return new EqualsBuilder().append(getId(), usuarioOficina.getId()).isEquals();
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
