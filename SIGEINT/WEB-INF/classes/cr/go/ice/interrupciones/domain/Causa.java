package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.Causa.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>Causa.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 13/12/2006</p>
 * <p>Ultima actualización: 13/12/2006</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class Causa implements Serializable {
	
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -3362371569352285681L;
    /**
     * Estado 0=Activo.
     */
    public static final int ESTADO_ACTIVO = 0;
    /**
     * Estado 1=Inactivo.
     */
    public static final int ESTADO_INACTIVO = 1;
    /**
	 * <code>codigoCausa</code> Codigo de la Causa
	 */
	private Integer codigoCausa;
	/**
	 * <code>nombreCausa</code> Nombre de la Causa
	 */
	private String nombreCausa;
	
	private Integer tiposCausa;
	
	private Integer causaEspecifica;
	
	private Integer detalleCausa;
	
	private Integer estado;
    
    /**
     * <code>CODIGO_CATASTROFE</code> Codigo de catastrofe
     */
    public static Integer CODIGO_CATASTROFE = new Integer(417);

	
	/**
	 * Constructor
	 */
	public Causa(){}
	
	
	/**
	 * Comment for getCodigoCausa
	 * @return Codigo de la Causa
	 */
	public Integer getCodigoCausa() {
		return codigoCausa;
	}

	
	/**
	 * Comment for getNombreCausa
	 * @return Nombre de la Causa 
	 */
	public String getNombreCausa() {
		return nombreCausa;
	}

	
	/**
	 * Comment for setCodigoCausa
	 * @param integer
	 */
	public void setCodigoCausa(Integer integer) {
		codigoCausa = integer;
	}

	
	/**
	 * Comment for setNombreCausa
	 * @param string
	 */
	public void setNombreCausa(String string) {
		nombreCausa = string;
	}
	
    public Integer getTiposCausa() {
    	if(this.tiposCausa ==null)
    	{
    		return new Integer(0);
    	}else
    	{
    		return tiposCausa;
    	}
	}


	public void setTiposCausa(Integer tiposCausa) {
		this.tiposCausa = tiposCausa;
	}


	public Integer getCausaEspecifica() {
		if(this.causaEspecifica==null)
		{
			return new Integer(0);
		}else
		{
			return causaEspecifica;
		}
		
	}


	public void setCausaEspecifica(Integer causaEspecifica) {
		this.causaEspecifica = causaEspecifica;
	}


	public Integer getDetalleCausa() {
		if(this.detalleCausa == null)
		{
			return new Integer(0);
		}else
		{
			return detalleCausa;
		}
		
	}


	public void setDetalleCausa(Integer detalleCausa) {
		this.detalleCausa = detalleCausa;
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


//	/**
//     * @see java.lang.Object#equals(java.lang.Object)
//     */ 
//    public boolean equals(Object objeto) {
//        boolean resultado;
//        if (!(objeto instanceof Causa)) {
//            resultado = false;
//        } else {
//            Causa causa = (Causa) objeto;
//            return new EqualsBuilder().append(getCodigoCausa(), causa.getCodigoCausa()).isEquals();
//        }
//        return resultado;
//    }
//    /**
//     * @see java.lang.Object#hashCode()
//     */
//    public int hashCode() {
//        return new HashCodeBuilder().append(getCodigoCausa()).toHashCode();
//    }        
    
}
