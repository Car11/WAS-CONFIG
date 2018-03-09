package cr.go.ice.interrupciones.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.NoPropiaSeccionamiento.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>NoPropiaSeccionamiento.java</code>Permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 11/04/2007</p>
 * <p>Ultima actualización: 11/04/2007</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class NoPropiaSeccionamiento implements Serializable {
	
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -4272417834170141814L;
    /**
	 * <code>noPropiaSeccionamientoID</code> ID de noPropiaSeccionamiento
	 */
	private NoPropiaSeccionamientoID noPropiaSeccionamientoID;
	/**
	 * <code>region</code> Codigo de la region
	 */
	private Integer region;
	/**
	 * <code>subregion</code> Codigo de la subregion
	 */
	private Integer subregion;
	/**
	 * <code>subestacion</code> Codigo de la subestacion
	 */
	private Integer subestacion;
	/**
	 * <code>circuito</code> Codigo del circuito
	 */
	private Integer circuito;
	/**
	 * <code>codigoVoltaje</code> Codigo de voltaje
	 */
	private Integer codigoVoltaje;
	/**
	 * <code>tipoVoltaje</code> Codigo del tipo de voltaje
	 */
	private Integer tipoVoltaje;
	/**
	 * <code>lugar</code> Descripcion del lugar
	 */
	private String lugar;
	/**
	 * <code>tiefue</code> Tiempo fuera
	 */
	private Double tiefue;
	/**
	 * <code>trifasica</code> Trifasica
	 */
	private Integer trifasica;
	/**
	 * <code>abonadosAfectados</code> Cantidad de abonados afectados
	 */
	private Long abonadosAfectados;
	/**
	 * <code>horasAbonado</code> Horas abonado
	 */
	private Double horasAbonado;
	/**
	 * <code>horaInicio</code> Hora de inicio
	 */
	private Double horaInicio;
	/**
	 * <code>horaFin</code> Hora final
	 */
	private Double horaFin;
	/**
	 * <code>fechaInicio</code> Fecha de inicio
	 */
	private Date fechaInicio;
	/**
	 * <code>fechaFin</code> Fecha de fin
	 */
	private Date fechaFin;
	/**
	 * <code>bitacora</code> Bitacora
	 */
	private Integer bitacora;
	/**
	 * <code>numeroMovimiento</code> Numero de Movimiento
	 */
	private Integer numeroMovimiento;	
	/**
	 * <code>nombreSeccion</code> Nombre de la Seccion
	 */
	private String nombreSeccion;	
	
	private Boolean bitacoraBool;
	private Boolean trifasicaBool;
	private Circuito circuitoObj;
	String nombreCircuito;
	private Seccion seccionObj;
	private Long seccion;
    
    private boolean seleccion;
	
    /**
     * Constructor por defecto
     */
	public NoPropiaSeccionamiento(){
	    this.seleccion = true;
    }
	
	/**
	 * Metodo accesor de abonadosAfectados.
	 * @return Retorna el abonadosAfectados.
	 */
	public Long getAbonadosAfectados() {
		return abonadosAfectados;
	}
	/**
	 * Metodo modificador de abonadosAfectados.
	 * @param abonadosAfectados El abonadosAfectados a modificar.
	 */
	public void setAbonadosAfectados(Long abonadosAfectados) {
		this.abonadosAfectados = abonadosAfectados;
	}
	/**
	 * Metodo accesor de bitacora.
	 * @return Retorna el bitacora.
	 */
	public Integer getBitacora() {
		return bitacora;
	}
	/**
	 * Metodo modificador de bitacora.
	 * @param bitacora El bitacora a modificar.
	 */
	public void setBitacora(Integer bitacora) {
		this.bitacora = bitacora;
		if(this.bitacora.intValue() == 1)
			this.bitacoraBool = Boolean.TRUE;
		else
			this.bitacoraBool = Boolean.FALSE;
	}
	/**
	 * Metodo accesor de circuito.
	 * @return Retorna el circuito.
	 */
	public Integer getCircuito() {
		return circuito;
	}
	/**
	 * Metodo modificador de circuito.
	 * @param circuito El circuito a modificar.
	 */
	public void setCircuito(Integer circuito) {
		this.circuito = circuito;
	}
	/**
	 * Metodo accesor de codigoVoltaje.
	 * @return Retorna el codigoVoltaje.
	 */
	public Integer getCodigoVoltaje() {
		return codigoVoltaje;
	}
	/**
	 * Metodo modificador de codigoVoltaje.
	 * @param codigoVoltaje El codigoVoltaje a modificar.
	 */
	public void setCodigoVoltaje(Integer codigoVoltaje) {
		this.codigoVoltaje = codigoVoltaje;
	}
	/**
	 * Metodo accesor de horaFin.
	 * @return Retorna el horaFin.
	 */
	public Double getHoraFin() {
		return horaFin;
	}
	/**
	 * Metodo modificador de horaFin.
	 * @param horaFin El horaFin a modificar.
	 */
	public void setHoraFin(Double horaFin) {
		this.horaFin = horaFin;
	}
	/**
	 * Metodo accesor de horaInicio.
	 * @return Retorna el horaInicio.
	 */
	public Double getHoraInicio() {
		return horaInicio;
	}
	/**
	 * Metodo modificador de horaInicio.
	 * @param horaInicio El horaInicio a modificar.
	 */
	public void setHoraInicio(Double horaInicio) {
		this.horaInicio = horaInicio;
	}
	/**
	 * Metodo accesor de horasAbonado.
	 * @return Retorna el horasAbonado.
	 */
	public Double getHorasAbonado() {
		return horasAbonado;
	}
	/**
	 * Metodo modificador de horasAbonado.
	 * @param horasAbonado El horasAbonado a modificar.
	 */
	public void setHorasAbonado(Double horasAbonado) {
		this.horasAbonado = horasAbonado;
	}
	/**
	 * Metodo accesor de lugar.
	 * @return Retorna el lugar.
	 */
	public String getLugar() {
		return lugar;
	}
	/**
	 * Metodo modificador de lugar.
	 * @param lugar El lugar a modificar.
	 */
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	/**
	 * Metodo accesor de noPropiaSeccionamiento.
	 * @return Retorna el noPropiaSeccionamiento.
	 */
	public NoPropiaSeccionamientoID getNoPropiaSeccionamientoID() {
		return noPropiaSeccionamientoID;
	}
	/**
	 * Metodo modificador de noPropiaSeccionamiento.
	 * @param noPropiaSeccionamiento El noPropiaSeccionamiento a modificar.
	 */
	public void setNoPropiaSeccionamientoID(
			NoPropiaSeccionamientoID noPropiaSeccionamientoID) {
		this.noPropiaSeccionamientoID = noPropiaSeccionamientoID;
	}
	/**
	 * Metodo accesor de region.
	 * @return Retorna el region.
	 */
	public Integer getRegion() {
		return region;
	}
	/**
	 * Metodo modificador de region.
	 * @param region El region a modificar.
	 */
	public void setRegion(Integer region) {
		this.region = region;
	}
	/**
	 * Metodo accesor de subestacion.
	 * @return Retorna el subestacion.
	 */
	public Integer getSubestacion() {
		return subestacion;
	}
	/**
	 * Metodo modificador de subestacion.
	 * @param subestacion El subestacion a modificar.
	 */
	public void setSubestacion(Integer subestacion) {
		this.subestacion = subestacion;
	}
	/**
	 * Metodo accesor de subRegion.
	 * @return Retorna el subRegion.
	 */
	public Integer getSubregion() {
		return subregion;
	}
	/**
	 * Metodo modificador de subRegion.
	 * @param subRegion El subRegion a modificar.
	 */
	public void setSubregion(Integer subregion) {
		this.subregion = subregion;
	}
	/**
	 * Metodo accesor de tiefue.
	 * @return Retorna el tiefue.
	 */
	public Double getTiefue() {
		return tiefue;
	}
	/**
	 * Metodo modificador de tiefue.
	 * @param tiefue El tiefue a modificar.
	 */
	public void setTiefue(Double tiefue) {
		this.tiefue = tiefue;
	}
	/**
	 * Metodo accesor de tipoVoltaje.
	 * @return Retorna el tipoVoltaje.
	 */
	public Integer getTipoVoltaje() {
		return tipoVoltaje;
	}
	/**
	 * Metodo modificador de tipoVoltaje.
	 * @param tipoVoltaje El tipoVoltaje a modificar.
	 */
	public void setTipoVoltaje(Integer tipoVoltaje) {
		this.tipoVoltaje = tipoVoltaje;
	}
	/**
	 * Metodo accesor de trifasica.
	 * @return Retorna el trifasica.
	 */
	public Integer getTrifasica() {
		return trifasica;
	}
	/**
	 * Metodo modificador de trifasica.
	 * @param trifasica El trifasica a modificar.
	 */
	public void setTrifasica(Integer trifasica) {
		this.trifasica = trifasica;
		if(this.trifasica.intValue() == 1)
			this.trifasicaBool = Boolean.TRUE;
		else
			this.trifasicaBool = Boolean.FALSE;
	}
	/**
	 * Metodo accesor de fechaFin.
	 * @return Retorna el fechaFin.
	 */
	public Date getFechaFin() {
		return fechaFin;
	}
	/**
	 * Metodo modificador de fechaFin.
	 * @param fechaFin El fechaFin a modificar.
	 */
	public void setFechaFin(Date fechaFin) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaFin);
        if(calendar.get(Calendar.YEAR) != 1970){
            this.fechaFin = fechaFin;
        }else{
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(this.fechaFin);
            calendar2.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
            calendar2.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
            this.fechaFin = calendar2.getTime();
        }
	}
	/**
	 * Metodo accesor de fechaInicio.
	 * @return Retorna el fechaInicio.
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * Metodo modificador de fechaInicio.
	 * @param fechaInicio El fechaInicio a modificar.
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		boolean resultado;
		if (!(obj instanceof NoPropiaSeccionamiento)) {
			resultado = false;
		} else {
			NoPropiaSeccionamiento noPropiaSeccionamiento = (NoPropiaSeccionamiento) obj;
			return new EqualsBuilder().append(getNoPropiaSeccionamientoID(), noPropiaSeccionamiento.getNoPropiaSeccionamientoID()).isEquals();
		}
		return resultado;
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getNoPropiaSeccionamientoID()).toHashCode();
	}
	/**
	 * Metodo accesor de numeroMovimiento.
	 * @return Retorna el numeroMovimiento.
	 */
	public Integer getNumeroMovimiento() {
		return numeroMovimiento;
	}
	/**
	 * Metodo modificador de numeroMovimiento.
	 * @param numeroMovimiento El numeroMovimiento a modificar.
	 */
	public void setNumeroMovimiento(Integer numeroMovimiento) {
		this.numeroMovimiento = numeroMovimiento;
	}
	/**
	 * Metodo accesor de nombreSeccion.
	 * @return Retorna el nombreSeccion.
	 */
	public String getNombreSeccion() {
		return nombreSeccion;
	}
	/**
	 * Metodo modificador de nombreSeccion.
	 * @param nombreSeccion El nombreSeccion a modificar.
	 */
	public void setNombreSeccion(String nombreSeccion) {
		this.nombreSeccion = nombreSeccion;
	}
	/**
	 * Metodo accesor de bitacoraBool.
	 * @return Retorna el bitacoraBool.
	 */
	public Boolean getBitacoraBool() {
		return bitacoraBool;
	}
	/**
	 * Metodo modificador de bitacoraBool.
	 * @param bitacoraBool El bitacoraBool a modificar.
	 */
	public void setBitacoraBool(Boolean bitacoraBool) {
		this.bitacoraBool = bitacoraBool;
		if(this.bitacoraBool.booleanValue())
			this.bitacora = new Integer(1);
		else
			this.bitacora = new Integer(0);
	}
	/**
	 * Metodo accesor de trifasicaBool.
	 * @return Retorna el trifasicaBool.
	 */
	public Boolean getTrifasicaBool() {
		return trifasicaBool;
	}
	/**
	 * Metodo modificador de trifasicaBool.
	 * @param trifasicaBool El trifasicaBool a modificar.
	 */
	public void setTrifasicaBool(Boolean trifasicaBool) {
		this.trifasicaBool = trifasicaBool;
		if(this.trifasicaBool.booleanValue())
			this.trifasica = new Integer(1);
		else
			this.trifasica = new Integer(0);
	}
    /**
     * Metodo accesor de circuitoObj.
     * @return Retorna el circuitoObj.
     */
    public Circuito getCircuitoObj() {
        return this.circuitoObj;
    }
    /**
     * Metodo modificador de circuitoObj.
     * @param circuitoObj El circuitoObj a modificar.
     */
    public void setCircuitoObj(Circuito circuitoObj) {
        this.circuitoObj = circuitoObj;
        this.nombreCircuito = this.circuitoObj.getNombreCircuito();
    }
    /**
     * Metodo accesor de nombreCircuito.
     * @return Retorna el nombreCircuito.
     */
    public String getNombreCircuito() {
        return this.nombreCircuito;
    }
    /**
     * Metodo modificador de nombreCircuito.
     * @param nombreCircuito El nombreCircuito a modificar.
     */
    public void setNombreCircuito(String nombreCircuito) {
        this.nombreCircuito = nombreCircuito;
    }
    /**
     * Metodo accesor de seccionObj.
     * @return Retorna el seccionObj.
     */
    public Seccion getSeccionObj() {
        return this.seccionObj;
    }
    /**
     * Metodo modificador de seccionObj.
     * @param seccionObj El seccionObj a modificar.
     */
    public void setSeccionObj(Seccion seccionObj) {
        this.seccionObj = seccionObj;
        this.nombreSeccion = seccionObj.getNombreSeccion();
    }
    /**
     * Metodo accesor de seccion.
     * @return Retorna el seccion.
     */
    public Long getSeccion() {
        return this.seccion;
    }
    /**
     * Metodo modificador de seccion.
     * @param seccion El seccion a modificar.
     */
    public void setSeccion(Long seccion) {
        this.seccion = seccion;
    }
    /**
     * Metodo accesor de seleccion.
     * @return Retorna el seleccion.
     */
    public boolean isSeleccion() {
        return this.seleccion;
    }
    /**
     * Metodo modificador de seleccion.
     * @param seleccion El seleccion a modificar.
     */
    public void setSeleccion(boolean seleccion) {
        this.seleccion = seleccion;
    }
}
