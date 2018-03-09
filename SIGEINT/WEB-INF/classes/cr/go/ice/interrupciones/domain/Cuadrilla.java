package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


/**
 * <p>Clase cr.go.ice.interrupciones.domain.Cuadrilla.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>Cuadrilla.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 26/03/2007</p>
 * <p>Ultima actualización: 26/03/2007</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class Cuadrilla implements Serializable {
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -5524902108547401804L;
    /**
	 * <code>cuadrillaID</code> Identificacion de la Cuadrilla
	 */
	private CuadrillaID cuadrillaID;
	/**
	 * <code>indicador</code> Indicador
	 */
	private String indicador;
	/**
	 * <code>nombreEmpleado</code> Nombre del empleado de la cuadrilla
	 */	
	private String nombreEmpleado;
	
	private Long cedula;
	
	
	/**
	 * Constructor	
	 */
	public Cuadrilla(){
		
	}
	/**
	 * Comment for getCuadrillaID
	 * @return Identificacion de la Cuadrilla
	 */
	public CuadrillaID getCuadrillaID() {
		return cuadrillaID;
	}
	
	/**
	 * Comment for setCuadrillaID
	 * @param cuadrillaID
	 */
	public void setCuadrillaID(CuadrillaID cuadrillaID) {
		this.cuadrillaID = cuadrillaID;
	}
	
	/**
	 * Comment for getIndicador
	 * @return Indicador
	 */
	public String getIndicador() {
		return indicador;
	}
	
	/**
	 * Comment for setIndicador
	 * @param indicador
	 */
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}
	
	
	/**
     * 
     * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		boolean resultado;
		if (!(obj instanceof Cuadrilla)) {
			resultado = false;
		} else {
			Cuadrilla cuadrilla = (Cuadrilla) obj;
			resultado = new EqualsBuilder().append(getCuadrillaID(), cuadrilla.getCuadrillaID()).isEquals();
		}
		return resultado;
	}
	/**
     * 
     * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getCuadrillaID()).toHashCode();
	}

	/**
	 * Metodo accesor de nombreEmpleado.
	 * @return Retorna el nombreEmpleado.
	 */
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	/**
	 * Metodo modificador de nombreEmpleado.
	 * @param nombreEmpleado El nombreEmpleado a modificar.
	 */
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
    
    
    public String getNombreCompleto(){
        String nombreCompleto = this.nombreEmpleado.toString();
        return nombreCompleto.replace("&", " ");
    }
    
    /**
     * Metodo accesor de cedula.
     * @return Retorna el cedula.
     */
    public Long getCedula() {
        return cedula;
    }
    /**
     * Metodo modificador de cedula.
     * @param cedula El cedula a modificar.
     */
    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }
}
