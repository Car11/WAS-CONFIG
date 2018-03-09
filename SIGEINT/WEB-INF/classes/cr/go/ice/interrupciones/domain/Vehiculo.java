package cr.go.ice.interrupciones.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.Vehiculo.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>Vehiculo.java</code>Permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 26/03/2007</p>
 * <p>Ultima actualización: 26/03/2007</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class Vehiculo implements Serializable {
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -8282242073563818233L;
    /**
	 * <code>vehiculoID</code> Identificacion del Vehiculo 
	 */
	private VehiculoID vehiculoID;
	/**
	 * <code>tiempoAviso</code> Tiempo de Aviso
	 */
	private Date tiempoAviso;
	/**
	 * <code>horaInicio</code> Hora de Inicio
	 */
	private Date horaInicio;
	/**
	 * <code>horaFin</code> Hora fin
	 */
	private Date horaFin;
	/**
	 * <code>horaLlegada</code> Hora de llegada
	 */
	private Date horaLlegada;
	/**
	 * <code>horaRestablece</code> Hora Restablece
	 */
	private Date horaRestablece;
	/**
	 * <code>kilometrosTotales</code> Kilometros Totales
	 */
	private Double kilometrosTotales;
	/**
	 * <code>kilometrosInicio</code> kilometros de Inicio
	 */
	private Double kilometrosInicio;
	/**
	 * <code>kilometrosFinal</code> Kilometros final
	 */
	private Double kilometrosFinal;
	/**
	 * <code>kilometrosLlegada</code> Kilometros de llegada
	 */
	private Double kilometrosLlegada;
	
	private Long numeroVehiculo;
	
	private Date fechaLocalizacion;
	
	private Double kilometrosLocalizacion;
	
	/**
	 * Comment for getHoraFin
	 * @return Hora fin
	 */
	public Date getHoraFin() {
		return horaFin;
	}
	
	/**
	 * Comment for setHoraFin
	 * @param horaFin
	 */
	public void setHoraFin(Date horaFin) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(horaFin);
        if(calendar.get(Calendar.YEAR) != 1970){
            this.horaFin = horaFin;
        }else{
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(this.horaFin);
            calendar2.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
            calendar2.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
            this.horaFin = calendar2.getTime();
        }
	}
	
	/**
	 * Comment for getHoraInicio
	 * @return Hora de Inicio
	 */
	public Date getHoraInicio() {
		return horaInicio;
	}
	
	/**
	 * Comment for setHoraInicio
	 * @param horaInicio
	 */
	public void setHoraInicio(Date horaInicio) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(horaInicio);
        if(calendar.get(Calendar.YEAR) != 1970){
            this.horaInicio = horaInicio;
        }else{
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(this.horaInicio);
            calendar2.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
            calendar2.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
            this.horaInicio = calendar2.getTime();
        }
	}
	
	/**
	 * Comment for getHoraLlegada
	 * @return  horaLlegada
	 */
	public Date getHoraLlegada() {
		return horaLlegada;
	}
	
	/**
	 * Comment for setHoraLlegada
	 * @param horaLlegada
	 */
	public void setHoraLlegada(Date horaLlegada) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(horaLlegada);
        if(calendar.get(Calendar.YEAR) != 1970){
            this.horaLlegada = horaLlegada;
        }else{
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(this.horaLlegada);
            calendar2.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
            calendar2.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
            this.horaLlegada = calendar2.getTime();
        }
	}
	
	/**
	 * Comment for getHoraRestablece
	 * @return horaRestablece
	 */
	public Date getHoraRestablece() {
		return horaRestablece;
	}
	
	/**
	 * Comment for setHoraRestablece
	 * @param horaRestablece
	 */
	public void setHoraRestablece(Date horaRestablece) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(horaRestablece);
        if(calendar.get(Calendar.YEAR) != 1970){
            this.horaRestablece = horaRestablece;
        }else{
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(this.horaRestablece);
            calendar2.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
            calendar2.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
            this.horaRestablece = calendar2.getTime();
        }
	}
	
	/**
	 * Comment for getKilometrosFinal
	 * @return kilometrosFinal
	 */
	public Double getKilometrosFinal() {
		return kilometrosFinal;
	}
	
	/**
	 * Comment for setKilometrosFinal
	 * @param kilometrosFinal
	 */
	public void setKilometrosFinal(Double kilometrosFinal) {
		this.kilometrosFinal = kilometrosFinal;
	}
	
	/**
	 * Comment for getKilometrosInicio
	 * @return kilometrosInicio
	 */
	public Double getKilometrosInicio() {
		return kilometrosInicio;
	}
	
	
	/**
	 * Comment for setKilometrosInicio
	 * @param kilometrosInicio
	 */
	public void setKilometrosInicio(Double kilometrosInicio) {
		this.kilometrosInicio = kilometrosInicio;
	}
	
	/**
	 * Comment for getKilometrosLlegada
	 * @return kilometrosLlegada
	 */
	public Double getKilometrosLlegada() {
		return kilometrosLlegada;
	}
	
	/**
	 * Comment for setKilometrosLlegada
	 * @param kilometrosLlegada
	 */
	public void setKilometrosLlegada(Double kilometrosLlegada) {
		this.kilometrosLlegada = kilometrosLlegada;
	}
	
	/**
	 * Comment for getKilometrosTotales
	 * @return kilometrosTotales
	 */
	public Double getKilometrosTotales() {
		return kilometrosTotales;
	}
	
	/**
	 * Comment for setKilometrosTotales
	 * @param kilometrosTotales
	 */
	public void setKilometrosTotales(Double kilometrosTotales) {
		this.kilometrosTotales = kilometrosTotales;
	}
	
	/**
	 * Comment for getTiempoAviso
	 * @return tiempoAviso
	 */
	public Date getTiempoAviso() {
		return tiempoAviso;
	}
	
	/**
	 * Comment for setTiempoAviso
	 * @param tiempoAviso
	 */
	public void setTiempoAviso(Date tiempoAviso) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tiempoAviso);
        if(calendar.get(Calendar.YEAR) != 1970){
            this.tiempoAviso = tiempoAviso;
        }else{
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(this.tiempoAviso);
            calendar2.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
            calendar2.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
            this.tiempoAviso = calendar2.getTime();
        }
	}
	
	/**
	 * Comment for getVehiculoID
	 * @return vehiculoID
	 */
	public VehiculoID getVehiculoID() {
		return vehiculoID;
	}
	
	/**
	 * Comment for setVehiculoID
	 * @param vehiculoID
	 */
	public void setVehiculoID(VehiculoID vehiculoID) {
		this.vehiculoID = vehiculoID;
	}
	
	
	/** 
     * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof Vehiculo)) {
			return false;
		} else {
			Vehiculo vehiculo = (Vehiculo) obj;
			return new EqualsBuilder().append(getVehiculoID(), vehiculo.getVehiculoID()).isEquals();
		}
	}
	/** 
     * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getVehiculoID()).toHashCode();
	}
    /**
     * Metodo accesor de numeroVehiculo.
     * @return Retorna el numeroVehiculo.
     */
    public Long getNumeroVehiculo() {
        return numeroVehiculo;
    }
    /**
     * Metodo modificador de numeroVehiculo.
     * @param numeroVehiculo El numeroVehiculo a modificar.
     */
    public void setNumeroVehiculo(Long numeroVehiculo) {
        this.numeroVehiculo = numeroVehiculo;
    }

	public Date getFechaLocalizacion() {
		return fechaLocalizacion;
	}

	public void setFechaLocalizacion(Date fechaLocalizacion) {
		if(fechaLocalizacion!=null)
		{
			Calendar calendar = Calendar.getInstance();
	        calendar.setTime(fechaLocalizacion);
	        if(calendar.get(Calendar.YEAR) != 1970){
	            this.fechaLocalizacion = fechaLocalizacion;
	        }else{
	            Calendar calendar2 = Calendar.getInstance();
	            calendar2.setTime(this.fechaLocalizacion);
	            calendar2.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
	            calendar2.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
	            this.fechaLocalizacion = calendar2.getTime();
	        }
		}else
		{
			this.fechaLocalizacion = fechaLocalizacion;
		}
	}

	public Double getKilometrosLocalizacion() {
		return kilometrosLocalizacion;
	}

	public void setKilometrosLocalizacion(Double kilometrosLocalizacion) {
		this.kilometrosLocalizacion = kilometrosLocalizacion;
	}
}
