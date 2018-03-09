package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


/**
 * <p>Clase cr.go.ice.interrupciones.domain.Correo.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>Correo.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 13/12/2006</p>
 * <p>Ultima actualización: 13/12/2006</p>
 * @author Vista Verde Soft (Nano y DOc Cristian)
 * @version 1.1
 */
public class Correo implements Serializable {
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 2457700451178066280L;
    /**
	 * <code>correo</code> Direccion de corrreo
	 */
	private String correo;
	/**
	 * <code>nombre</code> Nombre Usuario
	 */
	private String nombre;
	/**
	 * <code>telefono</code> Numero de Telefono
	 */
	private Long telefono;
	/**
	 * <code>codigoOficina</code> Codigo de Oficina
	 */
	private Integer codigoOficina;
	/**
	 * <code>indicadorEnviado</code> Indicador de enviado
	 */
	private Integer indicadorEnviado;
	/**
	 * <code>enviaCorreo</code> Indicador de envio de correo
	 */	
	private Boolean enviaCorreo;	
	/**
	 * <code>nombreOficina</code> nombre de Oficina
	 */		
	private String nombreOficina;
	
	
	/**
	 * Constructor
	 */
	public Correo(){}
	
	
	/**
	 * Comment for getCodigoOficina
	 * @return Codigo de Oficina
	 */
	public Integer getCodigoOficina() {
		return codigoOficina;
	}

	
	/**
	 * Comment for getCorreo
	 * @return Correo
	 */
	public String getCorreo() {
		return correo;
	}

	
	/**
	 * Comment for getIndicadorEnviado
	 * @return Indicador de enviado
	 */
	public Integer getIndicadorEnviado() {
		return indicadorEnviado;
	}

	
	/**
	 * Comment for getNombre
	 * @return Nombre Usuario
	 */
	public String getNombre() {
		return nombre;
	}

	
	/**
	 * Comment for getTelefono
	 * @return Numero de Telefono
	 */
	public Long getTelefono() {
		return telefono;
	}

	
	/**
	 * Comment for setCodigoOficina
	 * @param integer
	 */
	public void setCodigoOficina(Integer codigoOficina) {
		this.codigoOficina = codigoOficina;
	}

	
	/**
	 * Comment for setCorreo
	 * @param string
	 */
	public void setCorreo(String string) {
		correo = string;
	}

	
	/**
	 * Comment for setIndicadorEnviado
	 * @param integer
	 */
	public void setIndicadorEnviado(Integer integer) {
		indicadorEnviado = integer;
		if(indicadorEnviado != null){
			if(indicadorEnviado.intValue() == 1)
			    this.enviaCorreo = Boolean.TRUE;
			else
			    this.enviaCorreo = Boolean.FALSE;
		}
	}

	
	/**
	 * Comment for setNombre
	 * @param string
	 */
	public void setNombre(String string) {
		nombre = string;
	}

	
	/**
	 * Comment for setTelefono
	 * @param long1
	 */
	public void setTelefono(Long long1) {
		telefono = long1;
	}

    /**
     * Metodo accesor de enviaCorreo.
     * @return Retorna el enviaCorreo.
     */
    public Boolean getEnviaCorreo() {
        return enviaCorreo;
    }
    /**
     * Metodo modificador de enviaCorreo.
     * @param enviaCorreo El enviaCorreo a modificar.
     */
    public void setEnviaCorreo(Boolean enviaCorreo) {
        this.enviaCorreo = enviaCorreo;
        if(this.enviaCorreo.booleanValue())
            this.indicadorEnviado = new Integer(1);
        else
            this.indicadorEnviado = new Integer(0);
    }
    /**
     * Metodo accesor de nombreOficina.
     * @return Retorna el nombreOficina.
     */
    public String getNombreOficina() {
        return this.nombreOficina;
    }
    /**
     * Metodo modificador de nombreOficina.
     * @param nombreOficina El nombreOficina a modificar.
     */
    public void setNombreOficina(String nombreOficina) {
        this.nombreOficina = nombreOficina;
    }
    
    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */ 
    public boolean equals(Object objeto) {
        boolean resultado;
        if (!(objeto instanceof Correo)) {
            resultado = false;
        } else {
            Correo correo = (Correo) objeto;
            return new EqualsBuilder().append(getCorreo(), correo.getCorreo()).isEquals();
        }
        return resultado;
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder().append(getCorreo()).toHashCode();
    }      
    
}
