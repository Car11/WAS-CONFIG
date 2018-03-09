package cr.go.ice.interrupciones.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.Reporte.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>Reporte.java</code>Permite el mapeo a traves de hibernate con la estructura relacionada en la bd.</p>
 * <p>Fecha creación: 20/04/2007</p>
 * <p>Ultima actualización: 20/04/2007</p>
 * @author Vista Verde Soft (David)
 * @version 1.1
 */
public class Reporte implements Serializable {
	
	/**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -4706772720560248428L;
    /**
	 * <code>reporteID</code> ID de reporte
	 */
	private ReporteID reporteID;
	/**
	 * <code>region</code> region 
	 */
	private Integer region;
	/**
	 * <code>subRegion</code> sub region
	 */
	private Integer subRegion;
	/**
	 * <code>subEstacion</code> sub estado
	 */
	private Integer subEstacion;
	/**
	 * <code>circuito</code> Circuito
	 */
	private Integer circuito;
	/**
	 * <code>seccion</code> Seccion
	 */
	private Long seccion;
	/**
	 * <code>operador</code> Operador
	 */
	private Long operador;
	/**
	 * <code>codigoPueblo</code> Codigo de pueblo
	 */
	private Integer codigoPueblo;
	/**
	 * <code>poste</code> Poste
	 */
	private Integer poste;
	/**
	 * <code>secuencia</code> Secuencia
	 */
	private Integer secuencia;
	/**
	 * <code>lugar</code> Lugar de la Interruccipcion
	 */
	private String lugar;
	/**
	 * <code>tiefue</code> Tiefue
	 */
	private Double tiefue;
	/**
	 * <code>trifasica</code> TriFasica
	 */
	private Integer trifasica;
	/**
	 * <code>tipoVoltaje</code> Tipo de voltaje
	 */
	private Integer tipoVoltaje;
	/**
	 * <code>codigoVoltaje</code> Codigo de Voltaje
	 */
	private Integer codigoVoltaje;
	/**
	 * <code>causa1</code> Causa 1
	 */
	private Integer causa1;
	/**
	 * <code>causa2</code> Causa 2
	 */
	private Integer causa2;
	/**
	 * <code>codigoMaterial</code>Codigo de Material
	 */
	private Integer codigoMaterial;
	/**
	 * <code>codigoDano</code> Codigo de dano
	 */
	private Integer codigoDano;
	/**
	 * <code>codigoProteccion</code>Codigo de Proteccion
	 */
	private Integer codigoProteccion;
	/**
	 * <code>abonadoAfectado</code> Abonado de afectado
	 */
	private Long abonadoAfectado;
	/**
	 * <code>horaInicio</code>Hora de Inicio 
	 */
	private Double horaInicio;
	/**
	 * <code>horaFin</code>Hora de fin
	 */
	private Double horaFin;
	/**
	 * <code>fechaInicio</code> Fecha de Inicio
	 */
	private Date fechaInicio;
	/**
	 * <code>fechaFin</code> Fecha fin 
	 */
	private Date fechaFin;
	/**
	 * <code>codigoPuebloEquipo</code> Codigo de pueblo del equipo 
	 */
	private Integer codigoPuebloEquipo;
	/**
	 * <code>codigoCalleEquipo</code> Codigo de la calle del Equipo
	 */
	private Integer codigoCalleEquipo;
	/**
	 * <code>codigoCalle</code> Codigo de la calle
	 */
	private Integer codigoCalle;	
	/**
	 * <code>posteEquipo</code> Poste Equipo
	 */
	private Integer posteEquipo;
	/**
	 * <code>secuenciaEquipo</code> Secuencia de equipo
	 */
	private Integer secuenciaEquipo;
	/**
	 * <code>horasAbonado</code> Horas de Abonado
	 */
	private Double horasAbonado;
	/**
	 * <code>horaAviso</code> Hora de Aviso
	 */
	private Double horaAviso;
	/**
	 * <code>bitacora</code> Bitacora
	 */
	private Integer bitacora;
	/**
	 * <code>indSubEstacion</code> Ind Sub Estacion
	 */
	private Integer indSubEstacion;
	/**
	 * <code>nombreCliente</code> Nombre del Cliente
	 */
	private String nombreCliente;
	/**
	 * <code>telefonoCliente</code> Telefono del Cliente
	 */
	private String telefonoCliente;
	/**
	 * <code>operadoPor</code> Operado por 
	 */
	private Integer operadoPor;
	/**
	 * <code>codigoAgencia</code> Codigo del Agencia
	 */
	private Integer codigoAgencia;
	/**
	 * <code>codigoAnimal</code> Codigo del Animal
	 */
	private Integer codigoAnimal;
	/**
	 * <code>faseR</code> Fase R
	 */
	private String faseR;
	/**
	 * <code>faseS</code> Fase s
	 */
	private String faseS;
	/**
	 * <code>faseT</code> Fase T
	 */
	private String faseT;
	/**
	 * <code>comentario</code> Comentario sobre la Interrupcion
	 */
	private String comentario;
	/**
	 * <code>acometida</code> Acometida
	 */
	private Integer acometida;
	/**
	 * <code>tipoAcometida</code> Tipo de Acometida
	 */
	private Integer tipoAcometida;
	/**
	 * <code>medidor</code> Medidor 
	 */
	private Long medidor;
	/**
	 * <code>operadorScada</code> Operador Scada 
	 */
	private String operadorScada;
	
	private Integer indTension;
	
	
	
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
	 * Comment for getAcometida
	 * @return acometida
	 */
	public Integer getAcometida() {
		return acometida;
	}
	
	/**
	 * Comment for setAcometida
	 * @param acometida
	 */
	public void setAcometida(Integer acometida) {
		this.acometida = acometida;
	}
	
	/**
	 * Comment for getBitacora
	 * @return Bitacora
	 */
	public Integer getBitacora() {
		return bitacora;
	}
	
	/**
	 * Comment for setBitacora
	 * @param bitacora
	 */
	public void setBitacora(Integer bitacora) {
		this.bitacora = bitacora;
	}
	
	/**
	 * Comment for getCausa1
	 * @return Causa 1
	 */
	public Integer getCausa1() {
		return causa1;
	}
	
	/**
	 * Comment for setCausa1
	 * @param causa1
	 */
	public void setCausa1(Integer causa1) {
		this.causa1 = causa1;
	}
	
	/**
	 * Comment for getCausa2
	 * @return Causa 2
	 */
	public Integer getCausa2() {
		return causa2;
	}
	
	/**
	 * Comment for setCausa2
	 * @param causa2
	 */
	public void setCausa2(Integer causa2) {
		this.causa2 = causa2;
	}
	
	/**
	 * Comment for getCircuito
	 * @return Circuito
	 */
	public Integer getCircuito() {
		return circuito;
	}
	
	/**
	 * Comment for setCircuito
	 * @param circuito
	 */
	public void setCircuito(Integer circuito) {
		this.circuito = circuito;
	}
	
	/**
	 * Comment for getCodigoAgencia
	 * @return Codigo Agencia
	 */
	public Integer getCodigoAgencia() {
		return codigoAgencia;
	}
	
	/**
	 * Comment for setCodigoAgencia
	 * @param codigoAgencia
	 */
	public void setCodigoAgencia(Integer codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
	}
	
	/**
	 * Comment for getCodigoAnimal
	 * @return Codigo Animal
	 */
	public Integer getCodigoAnimal() {
		return codigoAnimal;
	}
	
	/**
	 * Comment for setCodigoAnimal
	 * @param codigoAnimal
	 */
	public void setCodigoAnimal(Integer codigoAnimal) {
		this.codigoAnimal = codigoAnimal;
	}
	
	/**
	 * Comment for getCodigoCalleEquipo
	 * @return Codigo Calle Equipo
	 */
	public Integer getCodigoCalleEquipo() {
		return codigoCalleEquipo;
	}
	
	/**
	 * Comment for setCodigoCalleEquipo
	 * @param codigoCalleEquipo
	 */
	public void setCodigoCalleEquipo(Integer codigoCalleEquipo) {
		this.codigoCalleEquipo = codigoCalleEquipo;
	}
	
	/**
	 * Comment for getCodigoDano
	 * @return Codigo Dano
	 */
	public Integer getCodigoDano() {
		return codigoDano;
	}
	
	/**
	 * Comment for setCodigoDano
	 * @param codigoDano
	 */
	public void setCodigoDano(Integer codigoDano) {
		this.codigoDano = codigoDano;
	}	
	
	/**
	 * Comment for getCodigoMaterial
	 * @return Codigo Material
	 */
	public Integer getCodigoMaterial() {
		return codigoMaterial;
	}
	
	/**
	 * Comment for setCodigoMaterial
	 * @param codigoMaterial
	 */
	public void setCodigoMaterial(Integer codigoMaterial) {
		this.codigoMaterial = codigoMaterial;
	}
	
	/**
	 * Comment for getCodigoProteccion
	 * @return Codigo Proteccion
	 */
	public Integer getCodigoProteccion() {
		return codigoProteccion;
	}
	
	/**
	 * Comment for setCodigoProteccion
	 * @param codigoProteccion
	 */
	public void setCodigoProteccion(Integer codigoProteccion) {
		this.codigoProteccion = codigoProteccion;
	}
	
	/**
	 * Comment for getCodigoPueblo
	 * @return Codigo Pueblo
	 */
	public Integer getCodigoPueblo() {
		return codigoPueblo;
	}
	
	/**
	 * Comment for setCodigoPueblo
	 * @param codigoPueblo
	 */
	public void setCodigoPueblo(Integer codigoPueblo) {
		this.codigoPueblo = codigoPueblo;
	}
	
	/**
	 * Comment for getCodigoPuebloEquipo
	 * @return Codigo pueblo Equipo
	 */
	public Integer getCodigoPuebloEquipo() {
		return codigoPuebloEquipo;
	}
	
	/**
	 * Comment for setCodigoPuebloEquipo
	 * @param codigoPuebloEquipo
	 */
	public void setCodigoPuebloEquipo(Integer codigoPuebloEquipo) {
		this.codigoPuebloEquipo = codigoPuebloEquipo;
	}
	
	/**
	 * Comment for getCodigoVoltaje
	 * @return Codigo de voltaje
	 */
	public Integer getCodigoVoltaje() {
		return codigoVoltaje;
	}
	
	/**
	 * Comment for setCodigoVoltaje
	 * @param codigoVoltaje
	 */
	public void setCodigoVoltaje(Integer codigoVoltaje) {
		this.codigoVoltaje = codigoVoltaje;
	}
	
	/**
	 * Comment for getComentario
	 * @return Comentario
	 */
	public String getComentario() {
		return comentario;
	}
	
	/**
	 * Comment for setComentario
	 * @param comentario
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	/**
	 * Comment for getFaseR
	 * @return Fase r
	 */
	public String getFaseR() {
		return faseR;
	}
	
	/**
	 * Comment for setFaseR
	 * @param faseR
	 */
	public void setFaseR(String faseR) {
		this.faseR = faseR;
	}
	
	/**
	 * Comment for getFaseS
	 * @return Fase s
	 */
	public String getFaseS() {
		return faseS;
	}
	
	/**
	 * Comment for setFaseS
	 * @param faseS
	 */
	public void setFaseS(String faseS) {
		this.faseS = faseS;
	}

	/**
	 * Comment for getFaseT
	 * @return Faset
	 */
	public String getFaseT() {
		return faseT;
	}

	/**
	 * Comment for setFaseT
	 * @param faseT
	 */
	public void setFaseT(String faseT) {
		this.faseT = faseT;
	}
	

	
	/**
	 * Comment for getFechaFin
	 * @return Fecha fin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}
	
	/**
	 * Comment for setFechaFin
	 * @param fechaFin
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	/**
	 * Comment for getFechaInicio
	 * @return Fecha Inicio
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
	 * Comment for getHoraAviso
	 * @return Hora Aviso
	 */
	public Double getHoraAviso() {
		return horaAviso;
	}
	
	/**
	 * Comment for setHoraAviso
	 * @param horaAviso
	 */
	public void setHoraAviso(Double horaAviso) {
		this.horaAviso = horaAviso;
	}
	
	/**
	 * Comment for getHoraFin
	 * @return Hora fin
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
	 * @return Hora Inicio
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
	 * @return Horas Abonado
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
	 * Comment for getIndSubEstacion
	 * @return Ind sub estacion
	 */
	public Integer getIndSubEstacion() {
		return indSubEstacion;
	}
	
	/**
	 * Comment for setIndSubEstacion
	 * @param indSubEstacion
	 */
	public void setIndSubEstacion(Integer indSubEstacion) {
		this.indSubEstacion = indSubEstacion;
	}	
	
	/**
	 * Comment for getLugar
	 * @return Lugar de la interrupcion
	 */
	public String getLugar() {
		return lugar;
	}
	
	/**
	 * Comment for setLugar
	 * @param lugar
	 */
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	
	/**
	 * Comment for getMedidor
	 * @return Medidor
	 */
	public Long getMedidor() {
		return medidor;
	}
	
	/**
	 * Comment for setMedidor
	 * @param medidor
	 */
	public void setMedidor(Long medidor) {
		this.medidor = medidor;
	}
	
	/**
	 * Comment for getNombreCliente
	 * @return Nombre Cliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}
	
	/**
	 * Comment for setNombreCliente
	 * @param nombreCliente
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	
	/**
	 * Comment for getOperadoPor
	 * @return Operador por
	 */
	public Integer getOperadoPor() {
		return operadoPor;
	}
	
	/**
	 * Comment for setOperadoPor
	 * @param operadoPor
	 */
	public void setOperadoPor(Integer operadoPor) {
		this.operadoPor = operadoPor;
	}
	
	/**
	 * Comment for getOperador
	 * @return Operador
	 */
	public Long getOperador() {
		return operador;
	}
	
	/**
	 * Comment for setOperador
	 * @param operador
	 */
	public void setOperador(Long operador) {
		this.operador = operador;
	}
	
	/**
	 * Comment for getPoste
	 * @return Poste
	 */
	public Integer getPoste() {
		return poste;
	}
	
	/**
	 * Comment for setPoste
	 * @param poste
	 */
	public void setPoste(Integer poste) {
		this.poste = poste;
	}
	
	/**
	 * Comment for getPosteEquipo
	 * @return Poste Equipo
	 */
	public Integer getPosteEquipo() {
		return posteEquipo;
	}
	
	/**
	 * Comment for setPosteEquipo
	 * @param posteEquipo
	 */
	public void setPosteEquipo(Integer posteEquipo) {
		this.posteEquipo = posteEquipo;
	}
	
	/**
	 * Comment for getRegion
	 * @return Region
	 */
	public Integer getRegion() {
		return region;
	}
	
	/**
	 * Comment for setRegion
	 * @param region
	 */
	public void setRegion(Integer region) {
		this.region = region;
	}
	
	/**
	 * Comment for getSeccion
	 * @return Seccion
	 */
	public Long getSeccion() {
		return seccion;
	}
	
	/**
	 * Comment for setSeccion
	 * @param seccion
	 */
	public void setSeccion(Long seccion) {
		this.seccion = seccion;
	}
	
	/**
	 * Comment for getSecuencia
	 * @return Secuencia
	 */
	public Integer getSecuencia() {
		return secuencia;
	}
	
	/**
	 * Comment for setSecuencia
	 * @param secuencia
	 */
	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}
	
	/**
	 * Comment for getSecuenciaEquipo
	 * @return Secuencia de equipo
	 */
	public Integer getSecuenciaEquipo() {
		return secuenciaEquipo;
	}
	
	/**
	 * Comment for setSecuenciaEquipo
	 * @param secuenciaEquipo
	 */
	public void setSecuenciaEquipo(Integer secuenciaEquipo) {
		this.secuenciaEquipo = secuenciaEquipo;
	}
	
	/**
	 * Comment for getSubEstacion
	 * @return Sub estado
	 */
	public Integer getSubEstacion() {
		return subEstacion;
	}
	
	/**
	 * Comment for setSubEstacion
	 * @param subEstacion
	 */
	public void setSubEstacion(Integer subEstacion) {
		this.subEstacion = subEstacion;
	}
	
	/**
	 * Comment for getSubRegion
	 * @return Sub region de la Interrupcion
	 */
	public Integer getSubRegion() {
		return subRegion;
	}
	
	/**
	 * Comment for setSubRegion
	 * @param subRegion
	 */
	public void setSubRegion(Integer subRegion) {
		this.subRegion = subRegion;
	}
	
	/**
	 * Comment for getTelefonoCliente
	 * @return Telefono del Cliente
	 */
	public String getTelefonoCliente() {
		return telefonoCliente;
	}
	
	/**
	 * Comment for setTelefonoCliente
	 * @param telefonoCliente
	 */
	public void setTelefonoCliente(String telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
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
	 * Comment for getTipoAcometida
	 * @return  Tipo Acometida
	 */
	public Integer getTipoAcometida() {
		return tipoAcometida;
	}
	
	/**
	 * Comment for setTipoAcometida
	 * @param tipoAcometida
	 */
	public void setTipoAcometida(Integer tipoAcometida) {
		this.tipoAcometida = tipoAcometida;
	}
	
	/**
	 * Comment for getTipoVoltaje
	 * @return Tipo de Voltaje
	 */
	public Integer getTipoVoltaje() {
		return tipoVoltaje;
	}
	
	/**
	 * Comment for setTipoVoltaje
	 * @param tipoVoltaje
	 */
	public void setTipoVoltaje(Integer tipoVoltaje) {
		this.tipoVoltaje = tipoVoltaje;
	}
	
	/**
	 * Comment for getTrifasica
	 * @return Trifasica
	 */
	public Integer getTrifasica() {
		return trifasica;
	}
	
	/**
	 * Comment for setTrifasica
	 * @param trifasica
	 */
	public void setTrifasica(Integer trifasica) {
		this.trifasica = trifasica;
	}
	/**
	 * Metodo accesor de reporteID.
	 * @return Retorna el reporteID.
	 */
	public ReporteID getReporteID() {
		return reporteID;
	}
	/**
	 * Metodo modificador de reporteID.
	 * @param reporteID El reporteID a modificar.
	 */
	public void setReporteID(ReporteID reporteID) {
		this.reporteID = reporteID;
	}
	
	/**
     * 
     * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		boolean resultado;
		if (!(obj instanceof Reporte)) {
			resultado = false;
		} else {
			Reporte reporte = (Reporte) obj;
			resultado = new EqualsBuilder().append(getReporteID(), reporte.getReporteID()).isEquals();
		}
		return resultado;
	}
	/**
     * 
     * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getReporteID()).toHashCode();
	}

	/**
	 * Metodo accesor de codigoCalle.
	 * @return Retorna el codigoCalle.
	 */
	public Integer getCodigoCalle() {
		return codigoCalle;
	}
	/**
	 * Metodo modificador de codigoCalle.
	 * @param codigoCalle El codigoCalle a modificar.
	 */
	public void setCodigoCalle(Integer codigoCalle) {
		this.codigoCalle = codigoCalle;
	}
    /**
     * Metodo accesor de operadorScada.
     * @return Retorna el operadorScada.
     */
    public String getOperadorScada() {
        return this.operadorScada;
    }
    /**
     * Metodo modificador de operadorScada.
     * @param operadorScada El operadorScada a modificar.
     */
    public void setOperadorScada(String operadorScada) {
        this.operadorScada = operadorScada;
    }

	public Integer getIndTension() {
		if(this.indTension==null)
		{
			this.indTension = Integer.valueOf(0);
		}
		return this.indTension;
	}

	public void setIndTension(Integer indTension) {
		this.indTension = indTension;
	}
}
