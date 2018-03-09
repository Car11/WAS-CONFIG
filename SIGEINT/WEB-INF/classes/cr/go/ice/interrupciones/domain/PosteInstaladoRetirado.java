package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.PosteInstaladoRetirado.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>PosteInstaladoRetirado.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 26/03/2007</p>
 * <p>Ultima actualización: 26/03/2007</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class PosteInstaladoRetirado implements Serializable {
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 7951298046791948837L;
    /**
	 * <code>posteInstaladoRetiradoID</code> Poste Instalado y Retirado la Identificacion
	 */
	private PosteInstaladoRetiradoID posteInstaladoRetiradoID;
	/**
	 * <code>tipoPoste</code> Tipo de Poste
	 */
	private Integer tipoPoste;
	/**
	 * <code>longuitud</code> Longitud
	 */
	private Integer longuitud;
	/**
	 * <code>indicador</code> Indicador
	 */
	private Integer indicador;
	/**
	 * <code>movimiento</code> movimiento
	 */
	private String movimiento;	
	/**
	 * <code>tipoPosteDescripcion</code> tipoPosteDescripcion
	 */
	private String tipoPosteDescripcion;		
	

	
	
	/**
	 * Comment for getIndicador
	 * @return Indicador
	 */
	public Integer getIndicador() {
		return indicador;
	}
	
	/**
	 * Comment for setIndicador
	 * @param indicador
	 */
	public void setIndicador(Integer indicador) {
		this.indicador = indicador;
		if(indicador.intValue() == 0)
			this.movimiento = "RETIRADO";
		else
			this.movimiento = "INSTALADO";
	}
	
	/**
	 * Comment for getLonguitud
	 * @return Longitud
	 */
	public Integer getLonguitud() {
		return longuitud;
	}

	/**
	 * Comment for setLonguitud
	 * @param longuitud
	 */
	public void setLonguitud(Integer longuitud) {
		this.longuitud = longuitud;
	}
	
	/**
	 * Comment for getPosteInstaladoRetiradoID
	 * @return Poste Instalado y Retidaro
	 */
	public PosteInstaladoRetiradoID getPosteInstaladoRetiradoID() {
		return posteInstaladoRetiradoID;
	}
	
	/**
	 * Comment for setPosteInstaladoRetiradoID
	 * @param posteInstaladoRetiradoID
	 */
	public void setPosteInstaladoRetiradoID(
			PosteInstaladoRetiradoID posteInstaladoRetiradoID) {
		this.posteInstaladoRetiradoID = posteInstaladoRetiradoID;
	}
	
	/**
	 * Comment for getTipoPoste
	 * @return Tipo de Poste
	 */
	public Integer getTipoPoste() {
		return tipoPoste;
	}
	
	/**
	 * Comment for setTipoPoste
	 * @param tipoPoste
	 */
	public void setTipoPoste(Integer tipoPoste) {
		this.tipoPoste = tipoPoste;
		switch(tipoPoste.intValue()){
			case 1:this.tipoPosteDescripcion = "CONCRETO";break;
			case 2:this.tipoPosteDescripcion = "MADERA";break;
			case 3:this.tipoPosteDescripcion = "RIEL";break;
			case 4:this.tipoPosteDescripcion = "AUTOSOPORTADO";break;
			case 5:this.tipoPosteDescripcion = "METAL";break;
		}
	}
	
	
	/**
     * 
     * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof PosteInstaladoRetirado)) {
			return false;
		} else {
			PosteInstaladoRetirado posteInstaladoRetirado = (PosteInstaladoRetirado) obj;
			return new EqualsBuilder().append(getPosteInstaladoRetiradoID(), posteInstaladoRetirado.getPosteInstaladoRetiradoID()).isEquals();
		}
	}
	/**
     * 
     * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getPosteInstaladoRetiradoID()).toHashCode();
	}
	/**
	 * Metodo accesor de movimiento.
	 * @return Retorna el movimiento.
	 */
	public String getMovimiento() {
		return movimiento;
	}
	/**
	 * Metodo modificador de movimiento.
	 * @param movimiento El movimiento a modificar.
	 */
	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
	}
	/**
	 * Metodo accesor de tipoPosteDescripcion.
	 * @return Retorna el tipoPosteDescripcion.
	 */
	public String getTipoPosteDescripcion() {
		return tipoPosteDescripcion;
	}
	/**
	 * Metodo modificador de tipoPosteDescripcion.
	 * @param tipoPosteDescripcion El tipoPosteDescripcion a modificar.
	 */
	public void setTipoPosteDescripcion(String tipoPosteDescripcion) {
		this.tipoPosteDescripcion = tipoPosteDescripcion;
	}
}
