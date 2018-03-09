package cr.go.ice.interrupciones.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.InterrupcionGemela.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>InterrupcionGemela.java</code>permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 26/03/2007</p>
 * <p>Ultima actualización: 26/03/2007</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class InterrupcionGemela implements Serializable {
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -1321521785858713109L;
    /**
	 * <code>interrupcionGemelaID</code> Interrupcion Gemela identificacion
	 */
	private InterrupcionGemelaID interrupcionGemelaID;
	/**
	 * <code>fechaInicio</code> Fecha de Inicio
	 */
	private Date fechaInicio;
	/**
	 * <code>fechaFin</code> Fecha de fin
	 */
	private Date fechaFin;
	/**
	 * <code>horaInicio</code> Hora de Inicio
	 */
	private Double horaInicio;
	/**
	 * <code>horaFin</code> Hora de fin
	 */
	private Double horaFin;
	/**
	 * <code>tiefue</code> Tiefue
	 */
	private Double tiefue;
	/**
	 * <code>horasAbonado</code> Horas de adonado
	 */
	private Double horasAbonado;
	/**
	 * <code>abonadoAfectado</code> Abonado Afectado
	 */
	private Long abonadoAfectado;
	
    /**
     * Constructor  
     */
	public InterrupcionGemela(){	
	}
	
	/**
	 * Comment for getAbonadoAfectado
	 * @return Abonado Afectado
	 */
	public Long getAbonadoAfectado() {
		return abonadoAfectado;
	}
	
	/**
	 * Comment for setAbonadoAfectado
	 * @param abonadoAfectado
	 */
	public void setAbonadoAfectado(Long abonadoAfectado) {
		this.abonadoAfectado = abonadoAfectado;
	}
	
	/**
	 * Comment for getFechaFin
	 * @return Fecha de fin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}
	
	/**
	 * Comment for setFechaFin
	 * @param fechaFin
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
	 * Comment for getFechaInicio
	 * @return Fecha de Inicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}
	
	/**
	 * Comment for setFechaInicio
	 * @param fechaInicio
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	/**
	 * Comment for getHoraFin
	 * @return Hora de fin
	 */
	public Double getHoraFin() {
		return horaFin;
	}
	
	/**
	 * Comment for setHoraFin
	 * @param horaFin
	 */
	public void setHoraFin(Double horaFin) {
		this.horaFin = horaFin;
	}
	
	/**
	 * Comment for getHoraInicio
	 * @return Hora de Inicio
	 */
	public Double getHoraInicio() {
		return horaInicio;
	}
	
	/**
	 * Comment for setHoraInicio
	 * @param horaInicio
	 */
	public void setHoraInicio(Double horaInicio) {
		this.horaInicio = horaInicio;
	}
	
	/**
	 * Comment for getHorasAbonado
	 * @return Horas de adonado
	 */
	public Double getHorasAbonado() {
		return horasAbonado;
	}
	
	/**
	 * Comment for setHorasAbonado
	 * @param horasAbonado
	 */
	public void setHorasAbonado(Double horasAbonado) {
		this.horasAbonado = horasAbonado;
	}

	
	/**
	 * Comment for getInterrupcionGemelaID
	 * @return Interrupcion Gemela identificacion
	 */
	public InterrupcionGemelaID getInterrupcionGemelaID() {
		return interrupcionGemelaID;
	}
	
	/**
	 * Comment for setInterrupcionGemelaID
	 * @param interrupcionGemelaID
	 */
	public void setInterrupcionGemelaID(
			InterrupcionGemelaID interrupcionGemelaID) {
		this.interrupcionGemelaID = interrupcionGemelaID;
	}
	
	/**
	 * Comment for getTiefue
	 * @return Tiefue
	 */
	public Double getTiefue() {
		return tiefue;
	}
	
	/**
	 * Comment for setTiefue
	 * @param tiefue
	 */
	public void setTiefue(Double tiefue) {
		this.tiefue = tiefue;
	}
	
	
	/**
     * 
     * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof InterrupcionGemela)) {
			return false;
		} else {
			InterrupcionGemela interrupcionGemela = (InterrupcionGemela) obj;
			return new EqualsBuilder().append(getInterrupcionGemelaID(), interrupcionGemela.getInterrupcionGemelaID()).isEquals();
		}
	}
	/**
     * 
     * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getInterrupcionGemelaID()).toHashCode();
	}

}
