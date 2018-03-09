package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


/**
 * <p>Clase cr.go.ice.interrupciones.domain.SubEstacion.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>SubEstacion.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 13/12/2006</p>
 * <p>Ultima actualización: 13/12/2006</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class SubEstacion implements Serializable{
    
    /**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -5088811735786054785L;
    /** atributo <code>ORDEN_CODIGO</code>*/
    public static Integer ORDEN_CODIGO = new Integer(1);
    /** atributo <code>ORDEN_NOMBRE</code>*/
    public static Integer ORDEN_NOMBRE = new Integer(2);

	/**
	 * <code>codigoSubEstacion</code> Codigo de sub Estacion
	 */
	private Integer codigoSubEstacion;
	/**
	 * <code>nombreSubEstacion</code> Nombre de Sub estacion
	 */
	private String nombreSubEstacion;
	/**
	 * <code>kmsSubEstacion</code> KMS Sub Estacion
	 */
	private Double kmsSubEstacion;
	/**
	 * <code>abonadoSubEstacion</code> Abonado de Sub Estacion
	 */
	private Long abonadoSubEstacion;
	
	private Integer subEstacionAresep;
	
	/**
	 * Constructor
	 */
	public SubEstacion(){}
	

	/**
	 * Comment for getAbonadoSubEstacion
	 * @return Abonado Sub Estacion
	 */
	public Long getAbonadoSubEstacion() {
		return abonadoSubEstacion;
	}

	
	/**
	 * Comment for getKmsSubEstacion
	 * @return KMS Sub Estacion
	 */
	public Double getKmsSubEstacion() {
		return kmsSubEstacion;
	}


	/**
	 * Comment for getNombreSubEstacion
	 * @return Nombre de Sub Estacion
	 */
	public String getNombreSubEstacion() {
		return nombreSubEstacion;
	}

	
	/**
	 * Comment for getCodigoSubEstacion
	 * @return Codigo de Sub Estacion
	 */
	public Integer getCodigoSubEstacion() {
		return codigoSubEstacion;
	}

	
	/**
	 * Comment for setAbonadoSubEstacion
	 * @param long1
	 */
	public void setAbonadoSubEstacion(Long long1) {
		abonadoSubEstacion = long1;
	}

	
	/**
	 * Comment for setKmsSubEstacion
	 * @param double1
	 */
	public void setKmsSubEstacion(Double double1) {
		kmsSubEstacion = double1;
	}

	
	/**
	 * Comment for setNombreSubEstacion
	 * @param string
	 */
	public void setNombreSubEstacion(String string) {
		nombreSubEstacion = string;
	}

	
	/**
	 * Comment for setCodigoSubEstacion
	 * @param integer
	 */
	public void setCodigoSubEstacion(Integer integer) {
		codigoSubEstacion = integer;
	}
	
    public Integer getSubEstacionAresep() {
		return subEstacionAresep;
	}


	public void setSubEstacionAresep(Integer subEstacionAresep) {
		this.subEstacionAresep = subEstacionAresep;
	}


	/**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if(obj instanceof SubEstacion){
            SubEstacion sub = (SubEstacion) obj;
            boolean resultado = new EqualsBuilder().append(getCodigoSubEstacion(),sub.getCodigoSubEstacion()).isEquals();
            return resultado;
        } else{
            return false;
        }
    }
	/** 
     * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getCodigoSubEstacion()).toHashCode();
	}
}