package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


/**
 * <p>Clase cr.go.ice.interrupciones.domain.Animal.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>Animal.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 13/12/2006</p>
 * <p>Ultima actualización: 13/12/2006</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class Animal implements Serializable {

    /**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -3889281028085484752L;
    /**
	 * <code>codigoAnimal</code> Codigo del animal
	 */
	private Integer codigoAnimal;
	/**
	 * <code>nombreAnimal</code> Nombre del Animal
	 */
	private String nombreAnimal;
	/**
	 * <code>tipoAnimal</code> Tipo de animal
	 */
	private Integer tipoAnimal;
	
	/**
	 * Constructor
	 */
	public Animal(){}
	
	
	/**
	 * Comment for getCodigoAnimal
	 * @return Codigo Animal
	 */
	public Integer getCodigoAnimal() {
		return codigoAnimal;
	}

	/**
	 * Comment for getNombreAnimal
	 * @return Nombre del Animal
	 */
	public String getNombreAnimal() {
		return nombreAnimal;
	}

	
	/**
	 * Comment for getTipoAnimal
	 * @return Tipo de Animal
	 */
	public Integer getTipoAnimal() {
		return tipoAnimal;
	}

	
	/**
	 * Comment for setCodigoAnimal
	 * @param integer
	 */
	public void setCodigoAnimal(Integer integer) {
		codigoAnimal = integer;
	}

	
	/**
	 * Comment for setNombreAnimal
	 * @param string
	 */
	public void setNombreAnimal(String string) {
		nombreAnimal = string;
	}

	
	/**
	 * Comment for setTipoAnimal
	 * @param integer
	 */
	public void setTipoAnimal(Integer integer) {
		tipoAnimal = integer;
	}

    /**
     * Metodo que determina el tipo de un animal en formato caracter
     * @return <String> Tipo de un animal en formato caracter
     */
    public String getTipoAnimalSTR(){
        if(this.tipoAnimal != null){
            switch(this.tipoAnimal.intValue()){
                case 1:return "Voladores";
                case 2:return "Trepadores";
                case 3:return "Monos";
                default: return "";
            }
        }else
            return "";
    }
    
    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */ 
    public boolean equals(Object objeto) {
        boolean resultado;
        if (!(objeto instanceof Animal)) {
            resultado = false;
        } else {
            Animal animal = (Animal) objeto;
            return new EqualsBuilder().append(getCodigoAnimal(), animal.getCodigoAnimal()).isEquals();
        }
        return resultado;
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder().append(getCodigoAnimal()).toHashCode();
    }        
	

}
