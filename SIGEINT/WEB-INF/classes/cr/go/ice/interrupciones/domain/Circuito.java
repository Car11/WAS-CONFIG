package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


/**
 * <p>Clase cr.go.ice.interrupciones.domain.Circuito.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>Circuito.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 13/12/2006</p>
 * <p>Ultima actualización: 13/12/2006</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class Circuito implements Serializable {
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 8850998078059613021L;
    /** atributo <code>TIPO_ORO</code>*/
	public static final Integer TIPO_ORO = new Integer(0);	
	/** atributo <code>TIPO_PLATA</code>*/
	public static final Integer TIPO_PLATA = new Integer(1);
    /** atributo <code>TIPO_BRONCE</code>*/
	public static final Integer TIPO_BRONCE = new Integer(2);
    
    /**
	 * <code>circuitoID</code> Identificacion del Circuito
	 */
	private CircuitoID circuitoID;
	/**
	 * <code>nombreCircuito</code> Nombre del Circuito
	 */
	private String nombreCircuito;
	/**
	 * <code>abonadoCircuito</code> Abonado del Circuito
	 */
	private Long abonadoCircuito;
	/**
	 * <code>kmsCircuito</code> Kms del Circuito
	 */
	private Double kmsCircuito;
	/**
	 * <code>tipo</code> Tipo de Circuito
	 */
	private Integer tipo;
	
	private Integer circuitoAresep;
	
	/**
	 * Constructor
	 */
	public Circuito(){
     this.circuitoID = new CircuitoID();   
    }
	 
	
	/**
	 * Comment for getAbonadoCircuito
	 * @return Abonado del Circuito
	 */
	public Long getAbonadoCircuito() {
		return abonadoCircuito;
	}

	
	/**
	 * Comment for getKmsCircuito
	 * @return Kms del Circuito
	 */
	public Double getKmsCircuito() {
		return kmsCircuito;
	}

	
	/**
	 * Comment for getNombreCircuito
	 * @return Nombre del Circuito
	 */
	public String getNombreCircuito() {
		return nombreCircuito;
	}


	
	/**
	 * Comment for getTipo
	 * @return Tipo de Circuito
	 */
	public Integer getTipo() {
		return tipo;
	}

	
	/**
	 * Comment for setAbonadoCircuito
	 * @param long1
	 */
	public void setAbonadoCircuito(Long long1) {
		abonadoCircuito = long1;
	}


	
	/**
	 * Comment for setKmsCircuito
	 * @param double1
	 */
	public void setKmsCircuito(Double double1) {
		kmsCircuito = double1;
	}

	
	/**
	 * Comment for setNombreCircuito
	 * @param string
	 */
	public void setNombreCircuito(String string) {
		nombreCircuito = string;
	}

	
	/**
	 * Comment for setTipo
	 * @param integer
	 */
	public void setTipo(Integer integer) {
		tipo = integer;
	}
	
	
	/**
	 * Comment for getCircuitoID
	 * @return circuito Identificacion
	 */
	public CircuitoID getCircuitoID() {
		return circuitoID;
	}
	
	/**
	 * Comment for setCircuitoID
	 * @param circuitoID
	 */
	public void setCircuitoID(CircuitoID circuitoID) {
		this.circuitoID = circuitoID;
	}
	
	
	public Integer getCircuitoAresep() {
		return circuitoAresep;
	}


	public void setCircuitoAresep(Integer circuitoAresep) {
		this.circuitoAresep = circuitoAresep;
	}


	/**
     * 
     * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object objeto) {
		boolean resultado;
		if (!(objeto instanceof Circuito)) {
			resultado = false;
		} else {
			Circuito circuito = (Circuito) objeto;
			return new EqualsBuilder().append(getCircuitoID(), circuito.getCircuitoID()).isEquals();
		}
		return resultado;
	}
	
	
	/**
     * 
     * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getCircuitoID()).toHashCode();
	}
}
