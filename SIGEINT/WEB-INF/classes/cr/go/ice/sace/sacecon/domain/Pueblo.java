package cr.go.ice.sace.sacecon.domain;

import java.io.Serializable;


/**
 * <p> <<Interface|Clase>> cr.go.ice.sace.sacecon.domain.Pueblo.java</p>
 * <p>Modulo (subsistema):  <<Sistema>></p>
 * <p> Descricion de <code>Pueblo.java</code>.</p>
 * <p>Fecha creación: 11/01/2011</p>
 * <p>Ultima actualización: 11/01/2011</p>
 * @author Vista Verde Tecnologia (eperaza)
 * Clase que refleja una tupla de la tabla GC000
 * @version 1.1
 */
public class Pueblo implements Serializable{
	/** Atributo <code>serialVersionUID</code> */
	private static final long serialVersionUID = -6293408419732430558L;	
    /** Atributo <code>CODIGO_LONGITUD</code> define la longitud que debe tener el atributo código*/
    public static final Integer CODIGO_LONGITUD = Integer.valueOf(4);
	/** Atributo <code>ALUMBRADO_PUBLICO</code> */
	public static final Integer ALUMBRADO_PUBLICO = Integer.valueOf(1);
	/** Atributo <code>ALUMBRADO_PRIVADO</code> */
	public static final Integer ALUMBRADO_PRIVADO = Integer.valueOf(2);
	/** Atributo <code>ALUMBRADO_PUBLICO_URBANO</code> */
	public static final Integer ALUMBRADOPUB_URBANO = Integer.valueOf(1);	
	/** Atributo <code>ALUMBRADO_PUBLICO_RURAL</code> */
	public static final Integer ALUMBRADOPUB_RURAL = Integer.valueOf(2);
    
    public static final String ALUMBRADO_DESCRIPCION_PUBLICO = "PÚBLICO";
    public static final String ALUMBRADO_DESCRIPCION_PRIVADO = "PRIVADO";
    public static final String ALUMBRADOPUB_DESCRIPCION_URBANO = "URBANO";
    public static final String ALUMBRADOPUB_DESCRIPCION_RURAL = "RURAL";
    
	/** atributo <code>pueblo</code>*/
    private Integer numero;
    /** atributo <code>nombre1</code>*/
    private String nombre;
    /** atributo <code>agencia</code>*/
    private Integer noAgencia; 
    /** atributo <code>alumbrado</code>*/
    private Integer alumbrado;
    /** atributo <code>coper05</code>*/
    private Integer centroOp;
    /** atributo <code>distrito</code>*/
    private Integer distrito;
    /** atributo <code>hg_100</code>*/
    private Integer subEstacion;
    /** atributo <code>ptomed</code>*/
    private Integer puntoMedicion;
    /** atributo <code>urba_rural</code>*/
    private Integer alumbradoPub;
    /** atributo <code>CODIGO_PROVINCIA</code>*/
    private Integer codigoProvincia;
    /** atributo <code>CODIGO_CANTON</code>*/
    private Integer codigoCanton;
    /** atributo <code>CODIGO_DISTRITO</code>*/
    private Integer codigoDistrito;
    
    /**
     * Metodo accesor de alumbrado.
     * @return Retorna el alumbrado.
     */
    public Integer getAlumbrado() {
        return this.alumbrado;
    }
    /**
     * Metodo modificador de alumbrado.
     * @param alumbrado El alumbrado a modificar.
     */
    public void setAlumbrado(Integer alumbrado) {
        this.alumbrado = alumbrado;
    }
    /**
     * Metodo accesor de alumbradoPub.
     * @return Retorna el alumbradoPub.
     */
    public Integer getAlumbradoPub() {
        return this.alumbradoPub;
    }
    /**
     * Metodo modificador de alumbradoPub.
     * @param alumbradoPub El alumbradoPub a modificar.
     */
    public void setAlumbradoPub(Integer alumbradoPub) {
        this.alumbradoPub = alumbradoPub;
    }
    /**
     * Metodo accesor de centroOp.
     * @return Retorna el centroOp.
     */
    public Integer getCentroOp() {
        return this.centroOp;
    }
    /**
     * Metodo modificador de centroOp.
     * @param centroOp El centroOp a modificar.
     */
    public void setCentroOp(Integer centroOp) {
        this.centroOp = centroOp;
    }
    /**
     * Metodo accesor de distrito.
     * @return Retorna el distrito.
     */
    public Integer getDistrito() {
        return this.distrito;
    }
    /**
     * Metodo modificador de distrito.
     * @param distrito El distrito a modificar.
     */
    public void setDistrito(Integer distrito) {
        this.distrito = distrito;
    }
   
    /**
     * Método accesor del atributo noAgencia.
     * @return Retorna el atributo noAgencia.
     */
    public Integer getNoAgencia() {
        return this.noAgencia;
    }
    /**
     * Método modificador del atributo noAgencia.
     * @param noAgencia El dato para modificar el atributo noAgencia.
     */
    public void setNoAgencia(Integer noAgencia) {
        this.noAgencia = noAgencia;
    }
    /**
     * Método accesor del atributo nombre.
     * @return Retorna el atributo nombre.
     */
    public String getNombre() {
        if (this.nombre != null) {
            return this.nombre.toUpperCase();
        } else {
            return this.nombre;
        }
    }
    /**
     * Metodo modificador de nombre.
     * @param nombre El nombre a modificar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Metodo accesor de numero.
     * @return Retorna el numero.
     */
    public Integer getNumero() {
        return this.numero;
    }
    /**
     * Metodo modificador de numero.
     * @param numero El numero a modificar.
     */
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    /**
     * Metodo accesor de puntoMedicion.
     * @return Retorna el puntoMedicion.
     */
    public Integer getPuntoMedicion() {
        return this.puntoMedicion;
    }
    /**
     * Metodo modificador de puntoMedicion.
     * @param puntoMedicion El puntoMedicion a modificar.
     */
    public void setPuntoMedicion(Integer puntoMedicion) {
        this.puntoMedicion = puntoMedicion;
    }
    /**
     * Metodo accesor de subEstacion.
     * @return Retorna el subEstacion.
     */
    public Integer getSubEstacion() {
        return this.subEstacion;
    }
    /**
     * Metodo modificador de subEstacion.
     * @param subEstacion El subEstacion a modificar.
     */
    public void setSubEstacion(Integer subEstacion) {
        this.subEstacion = subEstacion;
    }
    /**
     * Método accesor del atributo codigoCanton.
     * @return Retorna el atributo codigoCanton.
     */
    public Integer getCodigoCanton() {
        return this.codigoCanton;
    }
    /**
     * Método modificador del atributo codigoCanton.
     * @param codigoCanton El dato para modificar el atributo codigoCanton.
     */
    public void setCodigoCanton(Integer codigoCanton) {
        this.codigoCanton = codigoCanton;
    }
    /**
     * Método accesor del atributo codigoDistrito.
     * @return Retorna el atributo codigoDistrito.
     */
    public Integer getCodigoDistrito() {
        return this.codigoDistrito;
    }
    /**
     * Método modificador del atributo codigoDistrito.
     * @param codigoDistrito El dato para modificar el atributo codigoDistrito.
     */
    public void setCodigoDistrito(Integer codigoDistrito) {
        this.codigoDistrito = codigoDistrito;
    }
    /**
     * Método accesor del atributo codigoProvincia.
     * @return Retorna el atributo codigoProvincia.
     */
    public Integer getCodigoProvincia() {
        return this.codigoProvincia;
    }
    /**
     * Método modificador del atributo codigoProvincia.
     * @param codigoProvincia El dato para modificar el atributo codigoProvincia.
     */
    public void setCodigoProvincia(Integer codigoProvincia) {
        this.codigoProvincia = codigoProvincia;
    }
}
