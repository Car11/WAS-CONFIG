package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


/**
 * <p>Clase cr.go.ice.interrupciones.domain.UsuarioOficinaId.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>UsuarioOficinaId.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 14/06/2017</p>
 * <p>Ultima actualización: 14/06/2017</p>
 * @author Rossmon (rhidalgo)
 * @version 1.1
 */
public class UsuarioOficinaId implements Serializable {

    /**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -3889281028085484752L;
    
    private Empleado empleado;
    private Oficina oficina ;
	
	public UsuarioOficinaId()
	{
		this.empleado = new Empleado();
		this.oficina = new Oficina();
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Oficina getOficina() {
		return oficina;
	}

	public void setOficina(Oficina oficina) {
		this.oficina = oficina;
	}
    
    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */ 
    public boolean equals(Object objeto) {
        boolean resultado;
        if (!(objeto instanceof UsuarioOficinaId)) {
            resultado = false;
        } else {
            UsuarioOficinaId usuarioOficinaId = (UsuarioOficinaId) objeto;
            return new EqualsBuilder().append(getEmpleado(), usuarioOficinaId.getEmpleado()).append(getOficina(), usuarioOficinaId.getOficina()).isEquals();
        }
        return resultado;
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() 
    {
        return new HashCodeBuilder().append(getEmpleado()).append(getOficina()).toHashCode();
    }       
	

}
