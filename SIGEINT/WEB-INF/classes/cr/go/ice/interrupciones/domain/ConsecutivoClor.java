
package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


/**
 * <p>Clase cr.go.ice.interrupciones.domain.ConsecutivoClor.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ConsecutivoClor.java</code>Permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 26/03/2007</p>
 * <p>Ultima actualización: 26/03/2007</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class ConsecutivoClor implements Serializable {
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -1431922987934843550L;
    /**
	 * <code>consecutivoClorID</code> Consecutivo del Clor Identificacion
	 */
	private ConsecutivoClorID consecutivoClorID;
	/**
	 * <code>consecutivoInt</code> numero del consucutivo
	 */
	private Long consecutivoInt;
	/**
	 * <code>consecutivoRep</code> consecutivo Rep
	 */
	private Long consecutivoRep;
    /**
     * <code>abrirAno</code> abrir Ano
     */    
    private String abrirAno;
	
    /**
     * <code>ABRIR_ANO_SI</code> Indica el parametro que abre el año
     */  
    public static String ABRIR_ANO_SI = "S";
    /**
     * <code>ABRIR_ANO_NO</code> Indica el parametro que cierra el año
     */  
    public static String ABRIR_ANO_NO = "N";    
	
	
	/**
	 * Comment for getConsecutivoClorID
	 * @return Consecutivo del Clor Identificacion
	 */
	public ConsecutivoClorID getConsecutivoClorID() {
		return consecutivoClorID;
	}
	
	/**
	 * Comment for setConsecutivoClorID
	 * @param consecutivoClorID
	 */
	public void setConsecutivoClorID(ConsecutivoClorID consecutivoClorID) {
		this.consecutivoClorID = consecutivoClorID;
	}
	
	/**
	 * Comment for getConsecutivoInt
	 * @return numero del consucutivo
	 */
	public Long getConsecutivoInt() {
		return consecutivoInt;
	}
	
	/**
	 * Comment for setConsecutivoInt
	 * @param consecutivoInt
	 */
	public void setConsecutivoInt(Long consecutivoInt) {
		this.consecutivoInt = consecutivoInt;
	}
	
	/**
	 * Comment for getConsecutivoRep
	 * @return consecutivo Rep
	 */
	public Long getConsecutivoRep() {
		return consecutivoRep;
	}
	
	/**
	 * Comment for setConsecutivoRep
	 * @param consecutivoRep
	 */
	public void setConsecutivoRep(Long consecutivoRep) {
		this.consecutivoRep = consecutivoRep;
	}
	
    /**
     * Metodo accesor de abrirAno.
     * @return Retorna el abrirAno.
     */
    public String getAbrirAno() {
        return (this.abrirAno == null) ? ConsecutivoClor.ABRIR_ANO_NO : this.abrirAno;
    }
    /**
     * Metodo modificador de abrirAno.
     * @param abrirAno El abrirAno a modificar.
     */
    public void setAbrirAno(String abrirAno) {        
        this.abrirAno = abrirAno;
    }    
	
	/**
     * 
     * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof ConsecutivoClor)) {
			return false;
		} else {
			ConsecutivoClor consecutivoClor = (ConsecutivoClor) obj;
			return new EqualsBuilder().append(getConsecutivoClorID(), consecutivoClor.getConsecutivoClorID()).isEquals();
		}
	}
	/**
     * 
     * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getConsecutivoClorID()).toHashCode();
	}

}
