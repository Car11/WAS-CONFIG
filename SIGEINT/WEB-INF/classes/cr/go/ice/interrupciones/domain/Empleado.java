package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import cr.go.ice.interrupciones.utils.Usuario;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.Dano.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>Dano.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 13/12/2006</p>
 * <p>Ultima actualización: 13/12/2006</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class Empleado implements Serializable {
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 5282759809607531775L;
    /**
	 * <code>cedula</code> Numero de Cedula del Empleado
	 */
	private Long cedula;
	/**
	 * <code>nombreEmpleado</code> Nombre del Empleado
	 */
	private String nombreEmpleado;
	/**
	 * <code>indicador</code> Indicador
	 */
	private Integer indicador;
	/**
	 * <code>congelado</code> Congelado
	 */
	
	private Integer congelado;
	
    /**
     * <code>ESTADO_CONGELADO</code> Congelado
     */
	public static final int ESTADO_CONGELADO = 1; 
	
	/**
	 * Constructor
	 */
	public Empleado(){
	}
		
	
	/**
	 * Comment for getCedula
	 * @return Numero de Cedula del Empleado
	 */
	public Long getCedula() {
		return cedula;
	}

	
	/**
	 * Comment for getCongelado
	 * @return Congelado
	 */
	public Integer getCongelado() {
		return congelado;
	}

	
	/**
	 * Comment for getIndicador
	 * @return Indicador
	 */
	public Integer getIndicador() {
		return indicador;
	}

	
	/**
	 * Comment for getNombreEmpleado
	 * @return Nombre del Empleado
	 */
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	
	/**
	 * Comment for setCedula
	 * @param long1
	 */
	public void setCedula(Long long1) {
		cedula = long1;
	}

	
	/**
	 * Comment for setCongelado
	 * @param integer
	 */
	public void setCongelado(Integer integer) {
		congelado = integer;
	}

	
	/**
	 * Comment for setIndicador
	 * @param integer
	 */
	public void setIndicador(Integer integer) {
		indicador = integer;
	}

	
	/**
	 * Comment for setNombreEmpleado
	 * @param string
	 */
	public void setNombreEmpleado(String string) {
		nombreEmpleado = string;
	}
    
    public String getNombreCompleto(){
        String nombreCompleto = this.nombreEmpleado.toString();
        return nombreCompleto.replace("&", " ");
    }
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof Empleado)) {
			return false;
		} else {
			Empleado empleado = (Empleado) obj;
			return new EqualsBuilder().append(getCedula(), empleado.getCedula()).isEquals();
		}
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getCedula()).toHashCode();
	}
}
