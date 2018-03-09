package cr.go.ice.interrupciones.servicios;

/**
 * 
 * <p>TODO <<Interface|Clase>> cr.go.ice.energia.sige.transformadores.servicios.Encabezado.java</p>
 * <p>Módulo (subsistema): TODO <<Sistema>></p>
 * <p>TODO (Descripción) La <<Interface|Clase>> <code>Encabezado.java</code>.</p>
 * <p>Fecha creación: 13/07/2011</p>
 * <p>Última actualización: 13/07/2011</p>
 * @author Vista Verde Tecnología (eperaza)
 * @version 1.0
 */
public class Encabezado {
    
    private String      idAplicacion;
    private String      idUsuario;
    private Integer     codigoCompletacion;
    private Integer     codigoRazon;
    
    
    
    /**
     * Constructor
     */
    public Encabezado() {
        this.idAplicacion = "TRX0446";
        this.idUsuario = "TRAFOS";
        this.codigoCompletacion = Integer.valueOf(0);
        this.codigoRazon = Integer.valueOf(0);
    }
    public Integer getCodigoCompletacion() {
        return this.codigoCompletacion;
    }
    public void setCodigoCompletacion(Integer codigoCompletacion) {
        this.codigoCompletacion = codigoCompletacion;
    }
    public Integer getCodigoRazon() {
        return this.codigoRazon;
    }
    public void setCodigoRazon(Integer codigoRazon) {
        this.codigoRazon = codigoRazon;
    }
    public String getIdAplicacion() {
        return this.idAplicacion;
    }
    public void setIdAplicacion(String idAplicacion) {
        this.idAplicacion = idAplicacion;
    }
    public String getIdUsuario() {
        return this.idUsuario;
    }
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    

}
