package cr.go.ice.interrupciones.domain;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.IndiceGlobalRegionId.java</p>
 * <p>Modulo (subsistema): Proyecto</p>
 * <p>Fecha creacion: 10/04/2010</p>
 * <p>Ultima actualizacion: 10/04/2010</p>
 * @author VVS Code Generator
 * @version 1.1
 */
public class IndiceGlobalRegionId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4052308392314118570L;
	private Integer anno;
	private Integer mes;
	private Integer region;
	private Integer tipoVoltaje;
	private Integer codigoVoltaje;

	/**
 	* Constructor por defecto 
 	*/
	public IndiceGlobalRegionId(){
		this.anno = null;
		this.mes = null;
		this.region = null;
		this.tipoVoltaje = null;
		this.codigoVoltaje = null;
	}
	/**
 	* Constructor sobrecargado 
 	* @param anno
 	* @param mes
 	* @param region
 	* @param tipovoltaje
 	* @param codigoVoltaje
 	*/
	public IndiceGlobalRegionId(Integer anno,Integer mes,Integer region,Integer tipovoltaje,Integer codigoVoltaje){
		this.anno = anno;
		this.mes = mes;
		this.region = region;
		this.tipoVoltaje = tipovoltaje;
		this.codigoVoltaje = codigoVoltaje;
	}

	/**
 	* Metodo accesor de aa
 	* @return Retorna el aa
 	*/
	public Integer getAnno(){
		return this.anno;
	}
	/**
 	* Metodo modificador de anno
 	* @param anno El aa a modificar
 	*/
	public void setAnno(Integer anno){
		this.anno = anno;
	}
	/**
 	* Metodo accesor de mes
 	* @return Retorna el mes
 	*/
	public Integer getMes(){
		return this.mes;
	}
	/**
 	* Metodo modificador de mes
 	* @param mes El mes a modificar
 	*/
	public void setMes(Integer mes){
		this.mes = mes;
	}
	/**
 	* Metodo accesor de reg
 	* @return Retorna el reg
 	*/
	public Integer getRegion(){
		return this.region;
	}
	/**
 	* Metodo modificador de region
 	* @param region El reg a modificar
 	*/
	public void setRegion(Integer region){
		this.region = region;
	}
	/**
 	* Metodo accesor de tipovoltaje
 	* @return Retorna el tipoVoltaje
 	*/
	public Integer getTipoVoltaje(){
		return this.tipoVoltaje;
	}
	/**
 	* Metodo modificador de tipoVoltaje
 	* @param tipoVoltaje El tipoVoltaje a modificar
 	*/
	public void setTipoVoltaje(Integer tipoVoltaje){
		this.tipoVoltaje = tipoVoltaje;
	}
	/**
 	* Metodo accesor de codigoVoltaje
 	* @return Retorna el codigoVoltaje
 	*/
	public Integer getCodigoVoltaje(){
		return this.codigoVoltaje;
	}
	/**
 	* Metodo modificador de codigoVoltaje
 	* @param codigoVoltaje El codigoVoltaje a modificar
 	*/
	public void setCodigoVoltaje(Integer codigoVoltaje){
		this.codigoVoltaje = codigoVoltaje;
	}

	/**
 	* @see java.lang.Object#equals(java.lang.Object)
 	*/
	public boolean equals(Object obj) {
		if(obj instanceof IndiceGlobalRegionId){
			IndiceGlobalRegionId id = (IndiceGlobalRegionId)obj;
			EqualsBuilder eb = new EqualsBuilder();
			eb.append(this.anno, id.getAnno());
			eb.append(this.mes, id.getMes());
			eb.append(this.region, id.getRegion());
			eb.append(this.tipoVoltaje, id.getTipoVoltaje());
			eb.append(this.codigoVoltaje, id.getCodigoVoltaje());
			return eb.isEquals();
		}
		else{
			return false;
		}
	}
	/**
 	* @see java.lang.Object#hashCode()
 	*/
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(this.anno);
		hcb.append(this.mes);
		hcb.append(this.region);
		hcb.append(this.tipoVoltaje);
		hcb.append(this.codigoVoltaje);
		return hcb.toHashCode();
	}

}
