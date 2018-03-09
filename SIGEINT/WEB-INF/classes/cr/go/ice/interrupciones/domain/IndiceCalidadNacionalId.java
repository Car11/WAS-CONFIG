package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class IndiceCalidadNacionalId implements Serializable{
	private static final long serialVersionUID = 2457700451178066280L;
	
	private Integer ano;
	private Integer mes;
	
	public boolean equals(Object objeto) {
        boolean resultado;
        if (!(objeto instanceof Correo)) {
            resultado = false;
        } else {
        	IndiceCalidadNacionalId indiceCalidadNacionalId = (IndiceCalidadNacionalId) objeto;
            return new EqualsBuilder().append(getAno(), indiceCalidadNacionalId.getAno()).isEquals();
        }
        return resultado;
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder().append(getAno()).toHashCode();
    }      

	public Integer getAno() {
		return ano;
	}


	public void setAno(Integer ano) {
		this.ano = ano;
	}


	public Integer getMes() {
		return mes;
	}


	public void setMes(Integer mes) {
		this.mes = mes;
	}
    
}
