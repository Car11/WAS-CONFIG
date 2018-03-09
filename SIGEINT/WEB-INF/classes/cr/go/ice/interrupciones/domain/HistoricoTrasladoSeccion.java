package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>TODO <<Interface|Clase>> cr.go.ice.interrupciones.domain.HistoricoTrasladoSeccion.java</p>
 * <p>Módulo (subsistema): TODO <<Sistema>></p>
 * <p>TODO (Descripción) La <<Interface|Clase>> <code>HistoricoTrasladoSeccion.java</code>.</p>
 * <p>Fecha creación: 02/03/2011</p>
 * <p>Última actualización: 02/03/2011</p>
 * @author Vista Verde Tecnología (root)
 * @version 1.0
 */
public class HistoricoTrasladoSeccion implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 955642464194530833L;
	private HistoricoTrasladoSeccionID id;
    
    
    public HistoricoTrasladoSeccion(){
        this.id = new HistoricoTrasladoSeccionID();
    }


    /**
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if (!(obj instanceof HistoricoTrasladoSeccion)) {
            return false;
        } else {
            HistoricoTrasladoSeccion historico = (HistoricoTrasladoSeccion) obj;
            return new EqualsBuilder().append(getId(), historico.getId()).isEquals();
        }
    }
    /**
     * 
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder().append(getId()).toHashCode();
    }
    
    
    public HistoricoTrasladoSeccionID getId() {
        return this.id;
    }


    public void setId(HistoricoTrasladoSeccionID id) {
        this.id = id;
    }
}
