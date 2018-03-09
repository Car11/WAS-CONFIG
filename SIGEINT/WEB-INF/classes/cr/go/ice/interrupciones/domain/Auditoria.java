package cr.go.ice.interrupciones.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;



/**
 * <p>Clase cr.go.ice.interrupciones.domain.Auditoria.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>NoPropiaSeccionamiento.java</code>Permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 25/09/2012</p>
 * <p>Ultima actualización: 25/09/2012</p>
 * @author Grettel
 * @version 1.1
 */
public class Auditoria implements Serializable {
	
	

   
	/**
	 * 
	 */
	private static final long serialVersionUID = 3846436854647781875L;
	private Integer region;
	private Integer subregion;
	private Integer subestacion;
	private Integer circuito;
	private Long seccion;
	private Integer codigoVoltaje;
	private Long abonadosAfectados;
	private Double horasAbonado;
	private Date fechaInicio;
	private Date fechaFin;
	private Long numeroInterrupcion;
	private Integer aa;
	private Integer codigoOficina;
	private Double tiefue;
	private Integer tecres;
	private Integer causa1;
	private Integer operador;
	private String usuario;
	private Date fecha;
	private String accion;
	
    
     
     
    /**
     * Constructor por defecto
     */
	public Auditoria(){
	    
    }
	
	
	public Integer getRegion() {
		return region;
	}


	public void setRegion(Integer region) {
		this.region = region;
	}


	public Integer getSubregion() {
		return subregion;
	}


	public void setSubregion(Integer subregion) {
		this.subregion = subregion;
	}


	public Integer getSubestacion() {
		return subestacion;
	}


	public void setSubestacion(Integer subestacion) {
		this.subestacion = subestacion;
	}


	public Integer getCircuito() {
		return circuito;
	}


	public void setCircuito(Integer circuito) {
		this.circuito = circuito;
	}


	public Long getSeccion() {
		return seccion;
	}


	public void setSeccion(Long seccion) {
		this.seccion = seccion;
	}


	public Integer getCodigoVoltaje() {
		return codigoVoltaje;
	}


	public void setCodigoVoltaje(Integer codigoVoltaje) {
		this.codigoVoltaje = codigoVoltaje;
	}


	public Long getAbonadosAfectados() {
		return abonadosAfectados;
	}


	public void setAbonadosAfectados(Long abonadosAfectados) {
		this.abonadosAfectados = abonadosAfectados;
	}


	public Double getHorasAbonado() {
		return horasAbonado;
	}


	public void setHorasAbonado(Double horasAbonado) {
		this.horasAbonado = horasAbonado;
	}


	public Date getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public Date getFechaFin() {
		return fechaFin;
	}


	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


	public Long getNumeroInterrupcion() {
		return numeroInterrupcion;
	}


	public void setNumeroInterrupcion(Long numeroInterrupcion) {
		this.numeroInterrupcion = numeroInterrupcion;
	}


	public Integer getAa() {
		return aa;
	}


	public void setAa(Integer aa) {
		this.aa = aa;
	}


	public Integer getCodigoOficina() {
		return codigoOficina;
	}


	public void setCodigoOficina(Integer codigoOficina) {
		this.codigoOficina = codigoOficina;
	}


	public Double getTiefue() {
		return tiefue;
	}


	public void setTiefue(Double tiefue) {
		this.tiefue = tiefue;
	}


	public Integer getTecres() {
		return tecres;
	}


	public void setTecres(Integer tecres) {
		this.tecres = tecres;
	}


	public Integer getCausa1() {
		return causa1;
	}


	public void setCausa1(Integer causa1) {
		this.causa1 = causa1;
	}


	public Integer getOperador() {
		return operador;
	}


	public void setOperador(Integer operador) {
		this.operador = operador;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public String getAccion() {
		return accion;
	}


	public void setAccion(String accion) {
		this.accion = accion;
	}
	
}
