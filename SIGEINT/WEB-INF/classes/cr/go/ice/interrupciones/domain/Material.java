package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.Material.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>Material.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 13/12/2006</p>
 * <p>Ultima actualización: 13/12/2006</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class Material implements Serializable {
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 5162456956131065458L;
    /**
     * Estado 0=Activo.
     */
    public static final int ESTADO_ACTIVO = 0;
    /**
     * Estado 1=Inactivo.
     */
    public static final int ESTADO_INACTIVO = 1;
    /**
	 * <code>codigoMaterial</code> Codigo de Material
	 */
	private Integer codigoMaterial;
	/**
	 * <code>nombreMaterial</code> Nombre de Material
	 */
	private String nombreMaterial;
	
	private Integer danoGeneral;
	
	private Integer danoEspecifico;
	
	private Integer estado;
	
	/**
	 * Constructor
	 */
	public Material(){}
	
	
	
	/**
	 * Comment for getCodigoMaterial
	 * @return Codigo de Material
	 */
	public Integer getCodigoMaterial() {
		return codigoMaterial;
	}

	
	/**
	 * Comment for getNombreMaterial
	 * @return Nombre del MAterial
	 */
	public String getNombreMaterial() {
		return nombreMaterial;
	}

	
	/**
	 * Comment for setCodigoMaterial
	 * @param integer
	 */
	public void setCodigoMaterial(Integer integer) {
		codigoMaterial = integer;
	}

	
	/**
	 * Comment for setNombreMaterial
	 * @param string
	 */
	public void setNombreMaterial(String string) {
		nombreMaterial = string;
	}
    
    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */ 
    public boolean equals(Object objeto) {
        boolean resultado;
        if (!(objeto instanceof Material)) {
            resultado = false;
        } else {
            Material mat = (Material) objeto;
            return new EqualsBuilder().append(getCodigoMaterial(), mat.getCodigoMaterial()).isEquals();
        }
        return resultado;
    }
    public Integer getDanoGeneral() {
    	if(this.danoGeneral==null)
    	{
    		return new Integer(0);
    	}else
    	{
    		return danoGeneral;
    	}
		
	}

	public void setDanoGeneral(Integer danoGeneral) {
		this.danoGeneral = danoGeneral;
	}

	public Integer getDanoEspecifico() {
		if(this.danoEspecifico==null)
		{
			return new Integer(0);
		}else
		{
			return danoEspecifico;
		}
	}

	public void setDanoEspecifico(Integer danoEspecifico) {
		this.danoEspecifico = danoEspecifico;
	}
	
	public Integer getEstado() {
		if(this.estado ==null)
		{
			return new Integer(0);
		}else
		{
			return estado;
		}
	}
	
	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	/**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder().append(getCodigoMaterial()).toHashCode();
    }

}
