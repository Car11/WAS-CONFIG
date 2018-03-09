package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.Fusible.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>Fusible.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 26/03/2007</p>
 * <p>Ultima actualización: 26/03/2007/p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class Fusible implements Serializable {
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 6259345010006641304L;
    /**
	 * <code>fusibleID</code> Identificacion de los Fusibles
	 */
	private FusibleID fusibleID;
	/**
	 * <code>capacidadInstalada</code> Capacidad de Instalada de los Fusibles
	 */
	private Integer capacidadInstalada;
	/**
	 * <code>capacidadRetirada</code> Capacidad de Retirada de los Fusibles
	 */
	private Integer capacidadRetirada;
	/**
	 * <code>tipoInstalada</code> Tipo de Instalada de los Fusibles
	 */
	private Integer tipoInstalada;
	/**
	 * <code>tipoRetirada</code> Tipo de Retirada de los Fusibles 
	 */
	private Integer tipoRetirada;
	
	/**
	 * <code>tipoInstaladaDescripcion</code> Descripcion Tipo de Instalada de los Fusibles
	 */
	private String tipoInstaladaDescripcion;
	/**
	 * <code>tipoRetiradaDescripcion</code> Descripcion Tipo de Retirada de los Fusibles 
	 */
	private String tipoRetiradaDescripcion;	
	
	
		
    /**
     * Constructor  
     */
	public Fusible(){
		
	}
	/**
	 * Comment for getCapacidadInstalada
	 * @return Capacidad de Instalada de los Fusibles
	 */
	public Integer getCapacidadInstalada() {
		return capacidadInstalada;
	}
	
	/**
	 * Comment for setCapacidadInstalada
	 * @param capacidadInstalada
	 */
	public void setCapacidadInstalada(Integer capacidadInstalada) {
		this.capacidadInstalada = capacidadInstalada;
	}
	
	/**
	 * Comment for getCapacidadRetirada
	 * @return Capacidad de Retirada de los Fusibles
	 */
	public Integer getCapacidadRetirada() {
		return capacidadRetirada;
	}
	
	/**
	 * Comment for setCapacidadRetirada
	 * @param capacidadRetirada
	 */
	public void setCapacidadRetirada(Integer capacidadRetirada) {
		this.capacidadRetirada = capacidadRetirada;
	}
	
	/**
	 * Comment for getFusibleID
	 * @return Identificacion de los Fusibles
	 */
	public FusibleID getFusibleID() {
		return fusibleID;
	}
	
	/**
	 * Comment for setFusibleID
	 * @param fusibleID
	 */
	public void setFusibleID(FusibleID fusibleID) {
		this.fusibleID = fusibleID;
	}
	
	/**
	 * Comment for getTipoInstalada
	 * @return Tipo de Instalada de los Fusibles
	 */
	public Integer getTipoInstalada() {
		return tipoInstalada;
	}
	
	/**
	 * Comment for setTipoInstalada
	 * @param tipoInstalada
	 */
	public void setTipoInstalada(Integer tipoInstalada) {
		this.tipoInstalada = tipoInstalada;
        if(this.tipoInstalada != null){
    		switch(tipoInstalada.intValue()){
    			case 0:this.tipoInstaladaDescripcion = "K";break;
    			case 1:this.tipoInstaladaDescripcion = "T";break;
    			case 2:this.tipoInstaladaDescripcion = "H";break;
    			case 3:this.tipoInstaladaDescripcion = "SF";break;
    			case 4:this.tipoInstaladaDescripcion = "LC";break;
    		}
        }else{
            this.tipoInstaladaDescripcion = "";
        }
	}
	
	/**
	 * Comment for getTipoRetirada
	 * @return Tipo de Retirada de los Fusibles 
	 */
	public Integer getTipoRetirada() {
		return tipoRetirada;
	}
	
	/**
	 * Comment for setTipoRetirada
	 * @param tipoRetirada
	 */
	public void setTipoRetirada(Integer tipoRetirada) {
		this.tipoRetirada = tipoRetirada;
        if(this.tipoRetirada != null){
    		switch(tipoRetirada.intValue()){
    			case 0:this.tipoRetiradaDescripcion = "K";break;		
    			case 1:this.tipoRetiradaDescripcion = "T";break;
    			case 2:this.tipoRetiradaDescripcion = "H";break;
    			case 3:this.tipoRetiradaDescripcion = "SF";break;
    			case 4:this.tipoRetiradaDescripcion = "LC";break;
    		}
        }else{
            this.tipoRetiradaDescripcion = "";
        }
	}
	
	
	/**
     * 
     * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof Fusible)) {
			return false;
		} else {
			Fusible fusible = (Fusible) obj;
			return new EqualsBuilder().append(getFusibleID(), fusible.getFusibleID()).isEquals();
		}
	}
	/**
     * 
     * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getFusibleID()).toHashCode();
	}

	/**
	 * Metodo accesor de tipoInstaladaDescripcion.
	 * @return Retorna el tipoInstaladaDescripcion.
	 */
	public String getTipoInstaladaDescripcion() {
		return tipoInstaladaDescripcion;
	}
	/**
	 * Metodo modificador de tipoInstaladaDescripcion.
	 * @param tipoInstaladaDescripcion El tipoInstaladaDescripcion a modificar.
	 */
	public void setTipoInstaladaDescripcion(String tipoInstaladaDescripcion) {
		this.tipoInstaladaDescripcion = tipoInstaladaDescripcion;
	}
	/**
	 * Metodo accesor de tipoRetiradaDescripcion.
	 * @return Retorna el tipoRetiradaDescripcion.
	 */
	public String getTipoRetiradaDescripcion() {
		return tipoRetiradaDescripcion;
	}
	/**
	 * Metodo modificador de tipoRetiradaDescripcion.
	 * @param tipoRetiradaDescripcion El tipoRetiradaDescripcion a modificar.
	 */
	public void setTipoRetiradaDescripcion(String tipoRetiradaDescripcion) {
		this.tipoRetiradaDescripcion = tipoRetiradaDescripcion;
	}
}
