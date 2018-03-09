package cr.go.ice.interrupciones.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>TODO <<Interface|Clase>> cr.go.ice.interrupciones.domain.HistoricoTrasladoSeccionID.java</p>
 * <p>Módulo (subsistema): TODO <<Sistema>></p>
 * <p>TODO (Descripción) La <<Interface|Clase>> <code>HistoricoTrasladoSeccionID.java</code>.</p>
 * <p>Fecha creación: 02/03/2011</p>
 * <p>Última actualización: 02/03/2011</p>
 * @author Vista Verde Tecnología (root)
 * @version 1.0
 */
public class HistoricoTrasladoSeccionID implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -2355339369927843757L;
	private Integer region;
    private Integer subRegion;
    private Integer subEstacion;
    private Integer circuito;
    private Long    seccion;
    private Long    abonadoSeccion;
    private Date    fecha;
    private String  usuario;
    private Integer nuevaRegion;
    private Integer nuevaSubRegion;
    private Integer nuevaSubEstacion;
    private Integer nuevoCircuito;
    private Long    nuevaSeccion;

    
    public HistoricoTrasladoSeccionID(){
       super();
       this.region = null;
       this.subRegion = null;
       this.subEstacion = null;
       this.circuito = null;
       this.seccion = null;
       this.abonadoSeccion = null;
       this.fecha = null;
       this.usuario = null;
       this.nuevaRegion = null;
       this.nuevaSubRegion = null;
       this.nuevaSubEstacion = null;
       this.nuevoCircuito = null;
       this.nuevaSeccion = null;
    }

    
    /** 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        boolean resultado;
        if (!(obj instanceof HistoricoTrasladoSeccionID)) {
            resultado = false;
        } else {
            HistoricoTrasladoSeccionID id = (HistoricoTrasladoSeccionID) obj;          
            return new EqualsBuilder().append(getSeccion(), id.getSeccion()).append(getSubEstacion(), 
                    id.getSubEstacion()).append(getCircuito(),id.getCircuito()).append(getRegion(), 
                    id.getRegion()).append(getSubRegion(), id.getSubRegion()).append(getAbonadoSeccion(), 
                    id.getAbonadoSeccion()).append(getNuevaRegion(), id.getNuevaRegion()).append(getNuevaSeccion(), 
                    id.getNuevaSeccion()).append(getNuevaSubEstacion(), id.getNuevaSubEstacion()).append(getNuevaSubRegion(), 
                    id.getNuevaSubRegion()).append(getNuevoCircuito(), id.getNuevoCircuito()).append(getFecha(), 
                    id.getFecha()).append(getUsuario(), id.getUsuario()).isEquals();
                    
        }
        return resultado;
    }
    /** 
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder().append(getSeccion()).append(getSubEstacion()).append(getCircuito())
        .append(getRegion()).append(getSubRegion()).append(getAbonadoSeccion()).append(getNuevaRegion())
        .append(getNuevaSeccion()).append(getNuevaSubEstacion()).append(getNuevaSubRegion()).append(getNuevoCircuito())
        .append(getFecha()).append(getUsuario()).toHashCode();
    }
    

    public Long getAbonadoSeccion() {
        return this.abonadoSeccion;
    }


    public void setAbonadoSeccion(Long abonadoSeccion) {
        this.abonadoSeccion = abonadoSeccion;
    }


    public Integer getCircuito() {
        return this.circuito;
    }


    public void setCircuito(Integer circuito) {
        this.circuito = circuito;
    }


    public Date getFecha() {
        return this.fecha;
    }


    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    public Integer getNuevaRegion() {
        return this.nuevaRegion;
    }


    public void setNuevaRegion(Integer nuevaRegion) {
        this.nuevaRegion = nuevaRegion;
    }


    public Long getNuevaSeccion() {
        return this.nuevaSeccion;
    }


    public void setNuevaSeccion(Long nuevaSeccion) {
        this.nuevaSeccion = nuevaSeccion;
    }


    public Integer getNuevaSubEstacion() {
        return this.nuevaSubEstacion;
    }


    public void setNuevaSubEstacion(Integer nuevaSubEstacion) {
        this.nuevaSubEstacion = nuevaSubEstacion;
    }


    public Integer getNuevaSubRegion() {
        return this.nuevaSubRegion;
    }


    public void setNuevaSubRegion(Integer nuevaSubRegion) {
        this.nuevaSubRegion = nuevaSubRegion;
    }


    public Integer getNuevoCircuito() {
        return this.nuevoCircuito;
    }


    public void setNuevoCircuito(Integer nuevoCircuito) {
        this.nuevoCircuito = nuevoCircuito;
    }


    public Integer getRegion() {
        return this.region;
    }


    public void setRegion(Integer region) {
        this.region = region;
    }


    public Long getSeccion() {
        return this.seccion;
    }


    public void setSeccion(Long seccion) {
        this.seccion = seccion;
    }


    public Integer getSubEstacion() {
        return this.subEstacion;
    }


    public void setSubEstacion(Integer subEstacion) {
        this.subEstacion = subEstacion;
    }


    public Integer getSubRegion() {
        return this.subRegion;
    }


    public void setSubRegion(Integer subRegion) {
        this.subRegion = subRegion;
    }


    public String getUsuario() {
        return this.usuario;
    }


    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
