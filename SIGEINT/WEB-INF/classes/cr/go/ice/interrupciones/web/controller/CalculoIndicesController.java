package cr.go.ice.interrupciones.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.BO.CircuitoBO;
import cr.go.ice.interrupciones.BO.InterrupcionBO;
import cr.go.ice.interrupciones.BO.NoPropiaSeccionamientoBO;
import cr.go.ice.interrupciones.BO.ParametroBO;
import cr.go.ice.interrupciones.BO.RegionBO;
import cr.go.ice.interrupciones.BO.SeccionBO;
import cr.go.ice.interrupciones.BO.SubEstacionBO;
import cr.go.ice.interrupciones.BO.SubRegionBO;
import cr.go.ice.interrupciones.domain.Parametro;

/**
 * <p>
 * Clase cr.go.ice.interrupciones.web.controller.CalculoIndicesController.java
 * </p>
 * <p>
 * Modulo (subsistema): InterrupcionesWeb
 * </p>
 * <p>
 * Descricion de <code>CalculoIndicesController.java</code>.
 * </p>
 * <p>
 * Fecha creación: 23/07/2007
 * </p>
 * <p>
 * Ultima actualización: 23/07/2007
 * </p>
 * 
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class CalculoIndicesController extends AbstractFacesController {

	private Integer ano;
	private Integer mes;
	private Integer opcion;
	private ParametroBO parametroBO;
	private SeccionBO seccionBO;
	private CircuitoBO circuitoBO;
	private SubEstacionBO subEstacionBO;
	private SubRegionBO subRegionBO;
	private RegionBO regionBO;
	private NoPropiaSeccionamientoBO noPropiaSeccionamientoBO;
	private InterrupcionBO interrupcionBO;

	private Integer tipoInterrupcion;
	private Integer tipoBitacora;
	private Date fechaInicio;
	private Date fechaFinal;
	private Boolean email;

	/**
	 * 
	 * Constructor por defecto
	 */
	public CalculoIndicesController() {
		GregorianCalendar fechaActual = new GregorianCalendar();
		this.ano = new Integer(fechaActual.get(GregorianCalendar.YEAR));
		this.mes = new Integer(fechaActual.get(GregorianCalendar.MONTH) + 1);
		this.opcion = new Integer(1);
	}

	public String getInit() {
		return "success";
	}

	/**
	 * Retorna una lista de select items correspondientes al tramo de red
	 * 
	 * @return Lista de items del tramo de red
	 */
	public List getListaTramoRedIndicesGlobales() {
		List tramo = new ArrayList();
		tramo.add(new SelectItem(new Integer(1), "Sección"));
		tramo.add(new SelectItem(new Integer(2), "Circuito"));
		tramo.add(new SelectItem(new Integer(3), "Subestación"));
		tramo.add(new SelectItem(new Integer(4), "Subregión"));
		tramo.add(new SelectItem(new Integer(5), "Región"));
		return tramo;

	}

	/**
	 * Retorna una lista de select items correspondientes a una parte del tramo
	 * de red
	 * 
	 * @return Lista de items del tramo de red
	 */
	public List getListaTramoRedIndicesCausa() {
		List tramo = new ArrayList();
		tramo.add(new SelectItem(new Integer(1), "Sección"));
		tramo.add(new SelectItem(new Integer(2), "Circuito"));
		return tramo;
	}

	/**
	 * Retorna una lista de select items correspondientes a interrupciones
	 * propias y no propias
	 * 
	 * @return Lista de items
	 */
	public List getListaTramoRedIndicesPropiaNoPropia() {
		List tramo = new ArrayList();
		tramo.add(new SelectItem(new Integer(1), "Propias"));
		tramo.add(new SelectItem(new Integer(2), "No propias"));
		return tramo;
	}

	/**
	 * Retorna una lista de select items correspondientes a una parte del tramo
	 * de red
	 * 
	 * @return Lista de items del tramo de red
	 */
	public List getListaTramoRedIndicesTrifasica() {
		List tramo = new ArrayList();
		tramo.add(new SelectItem(new Integer(1), "Sección"));
		tramo.add(new SelectItem(new Integer(2), "Circuito"));
		return tramo;
	}

	/**
	 * 
	 * Cancela el proceso de cálculo de índices
	 * 
	 * @return success para cancelar el proceso
	 */
	public String cancelar() {
		return "success";
	}

	/**
	 * Realiza las validaciones correspondientes de acuerdo a los parámetros
	 * introducidos por el usuario y ejecuta el procedimiento almacendado
	 * correspondiente para el calculo de indices globales deseado.
	 * 
	 * @return error o success de acuerdo a la correcta validación de los datos
	 *         y la correscta ejecución del procedimiento almacenado
	 */
	public String ejecutarCalculoIndicesGlobales() {

		String resultado = "";
		if (validarFecha().equals("error")) {
			return "error";
		}
		if (validarFechaGeneracionIndice().equals("error")) {
			return "error";
		}
		if (this.opcion.intValue() == 1) {
			resultado = this.seccionBO.ejecutarIndicesGlobales(this.ano, this.mes);
		}
		if (this.opcion.intValue() == 2) {
			resultado = this.circuitoBO.ejecutarIndicesGlobales(this.ano, this.mes);
		}
		if (this.opcion.intValue() == 3) {
			resultado = this.subEstacionBO.ejecutarIndicesGlobales(this.ano, this.mes);
		}
		if (this.opcion.intValue() == 4) {
			resultado = this.subRegionBO.ejecutarIndicesGlobales(this.ano, this.mes);
		}
		if (this.opcion.intValue() == 5) {
			resultado = this.regionBO.ejecutarIndicesGlobales(this.ano, this.mes);
		}
		if (resultado.equals("S")) {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Información!", "La ejecución del proceso fue exitosa."));
		} else {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"La ejecución del proceso no fue exitosa."));
		}
		return "success";
	}

	/**
	 * Realiza las validaciones correspondientes de acuerdo a los parámetros
	 * introducidos por el usuario y ejecuta el procedimiento almacendado
	 * correspondiente para el calculo de indices por causa.
	 * 
	 * @return error o success de acuerdo a la correcta validación de los datos
	 *         y la correscta ejecución del procedimiento almacenado
	 */
	public String ejecutarCalculoIndicesCausa() {

		String resultado = "";
		if (validarFecha().equals("error")) {
			return "error";
		}
		if (validarFechaGeneracionIndice().equals("error")) {
			return "error";
		}
		if (this.opcion.intValue() == 1) {
			resultado = this.seccionBO.ejecutarIndicesCausa(this.ano, this.mes);
		}
		if (this.opcion.intValue() == 2) {
			resultado = this.circuitoBO.ejecutarIndicesCausa(this.ano, this.mes);
		}
		if (resultado.equals("S")) {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Información!", "La ejecución del proceso fue exitosa."));
		} else {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"La ejecución del proceso no fue exitosa."));
		}
		return "success";
	}

	/**
	 * Realiza las validaciones correspondientes de acuerdo a los parámetros
	 * introducidos por el usuario y ejecuta el procedimiento almacendado
	 * correspondiente para el calculo de indices propios y no propios.
	 * 
	 * @return error o success de acuerdo a la correcta validación de los datos
	 *         y la correscta ejecución del procedimiento almacenado
	 */
	public String ejecutarCalculoIndicesPropiosNoPropios() {

		String resultado = "";
		if (validarFecha().equals("error")) {
			return "error";
		}
		if (validarFechaGeneracionIndice().equals("error")) {
			return "error";
		}
		if (this.opcion.intValue() == 1) {
			resultado = this.interrupcionBO.ejecutarIndicesPropios(this.ano, this.mes);
		}
		if (this.opcion.intValue() == 2) {
			resultado = this.noPropiaSeccionamientoBO.ejecutarIndicesNoPropios(this.ano, this.mes);
		}
		if (resultado.equals("S")) {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Información!", "La ejecución del proceso fue exitosa"));
		} else {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"La ejecución del proceso no fue exitosa."));
		}
		return "success";
	}

	/**
	 * Realiza las validaciones correspondientes de acuerdo a los parámetros
	 * introducidos por el usuario y ejecuta el procedimiento almacendado
	 * correspondiente para el calculo de indices trifásicos.
	 * 
	 * @return error o success de acuerdo a la correcta validación de los datos
	 *         y la correscta ejecución del procedimiento almacenado
	 */
	public String ejecutarCalculoIndicesTrifasicos() {

		String resultado = "";
		if (validarFecha().equals("error")) {
			return "error";
		}
		if (validarFechaGeneracionIndice().equals("error")) {
			return "error";
		}
		if (this.opcion.intValue() == 1) {
			resultado = this.seccionBO.ejecutarIndicesTrifasicos(this.ano, this.mes);
		}
		if (this.opcion.intValue() == 2) {
			resultado = this.circuitoBO.ejecutarIndicesTrifasicos(this.ano, this.mes);
		}
		if (resultado.equals("S")) {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Información!", "La ejecución del proceso fue exitosa."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"La ejecución del proceso no fue exitosa."));
		}
		return "success";
	}

	/**
	 * Valida que la fecha introducida por el usuario sea correcta
	 * 
	 * @return error o success de acuerdo a la correctitud de la fecha
	 */
	private String validarFecha() {
		GregorianCalendar fechaActual = new GregorianCalendar();
		int ano = fechaActual.get(GregorianCalendar.YEAR);
		int mes = fechaActual.get(GregorianCalendar.MONTH) + 1;

		if (this.ano == null) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El año es requerido."));
			return "error";
		}
		if (this.mes == null) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El mes es requerido."));
			return "error";
		}
		if (this.ano.intValue() <= ano && this.ano.intValue() > 0) {
			if (this.mes.intValue() <= 12 && this.mes.intValue() > 0) {
				if (this.ano.intValue() == ano) {
					if (this.mes.intValue() <= mes)
						return "success";
					else {

						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Error!", "El mes debe ser menor o igual al mes actual."));
						return "error";
					}
				} else {
					return "success";
				}
			} else {

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Mes fuera de rango."));
				return "error";
			}
		} else {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Año fuera de rango."));
			return "error";
		}
	}

	/**
	 * Valida que la fecha introducida sea válida para la generación del cálculo
	 * de índices
	 * 
	 * @return error o success de acuerdo a la correctitud de la fecha
	 */
	private String validarFechaGeneracionIndice() {
		
		GregorianCalendar hoy = new GregorianCalendar();
		boolean correcto = false;
		int ano = hoy.get(GregorianCalendar.YEAR);
		int mes = hoy.get(GregorianCalendar.MONTH) + 1;
		int diaMes = hoy.get(GregorianCalendar.DAY_OF_MONTH);

		if (ano == this.ano.intValue()) {
			if (diaMes >= 6 && mes == this.mes.intValue()) {
				correcto = true;
			} else {
				if ((diaMes < 6 && mes == this.mes.intValue()) || (diaMes < 6 && this.mes.intValue() == mes)) {
					correcto = true;
				} else {
					mes = (mes == 1) ? 13 : mes;
					if (diaMes < 6 && this.mes.intValue() == mes - 1) {
						correcto = true;
					} else {
						correcto = false;
					}
				}
			}
		} else {
			ano = (ano > this.ano.intValue()) ? ano - 1 : ano;
			mes = (mes == 1) ? 12 : mes;
			if (this.mes.intValue() == 12) {
				if (ano == this.ano.intValue() && diaMes <= 5 && mes == this.mes.intValue())
					correcto = true;
				else
					correcto = false;
			} else {
				correcto = false;
			}
		}

		List parametros = parametroBO.getParametros();
		Parametro parametro = null;
		if (parametros != null && parametros.size() > 0)
			parametro = (Parametro) parametros.get(0);
		if ((!correcto && parametro == null)
				|| (!correcto && parametro.getParametroID().getGeneracionIndices().equals("S") == false)) {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"Los índices no pueden ser generados después del día 5 de cada mes."));
			return "error";
		} else
			return "success";
	}

	/**
	 * Metodo accesor de ano.
	 * 
	 * @return Retorna el ano.
	 */
	public Integer getAno() {
		return this.ano;
	}

	/**
	 * Metodo modificador de ano.
	 * 
	 * @param ano
	 *            El ano a modificar.
	 */
	public void setAno(Integer ano) {
		this.ano = ano;
	}

	/**
	 * Metodo accesor de mes.
	 * 
	 * @return Retorna el mes.
	 */
	public Integer getMes() {
		return this.mes;
	}

	/**
	 * Metodo modificador de mes.
	 * 
	 * @param mes
	 *            El mes a modificar.
	 */
	public void setMes(Integer mes) {
		this.mes = mes;
	}

	/**
	 * Metodo accesor de opcion.
	 * 
	 * @return Retorna el opcion.
	 */
	public Integer getOpcion() {
		return this.opcion;
	}

	/**
	 * Metodo modificador de opcion.
	 * 
	 * @param opcion
	 *            El opcion a modificar.
	 */
	public void setOpcion(Integer opcion) {
		this.opcion = opcion;
	}

	/**
	 * Metodo accesor de parametroBO.
	 * 
	 * @return Retorna el parametroBO.
	 */
	public ParametroBO getParametroBO() {
		return this.parametroBO;
	}

	/**
	 * Metodo modificador de parametroBO.
	 * 
	 * @param parametroBO
	 *            El parametroBO a modificar.
	 */
	public void setParametroBO(ParametroBO parametroBO) {
		this.parametroBO = parametroBO;
	}

	/**
	 * Metodo accesor de seccionBO.
	 * 
	 * @return Retorna el seccionBO.
	 */
	public SeccionBO getSeccionBO() {
		return this.seccionBO;
	}

	/**
	 * Metodo modificador de seccionBO.
	 * 
	 * @param seccionBO
	 *            El seccionBO a modificar.
	 */
	public void setSeccionBO(SeccionBO seccionBO) {
		this.seccionBO = seccionBO;
	}

	/**
	 * Metodo accesor de circuitoBO.
	 * 
	 * @return Retorna el circuitoBO.
	 */
	public CircuitoBO getCircuitoBO() {
		return this.circuitoBO;
	}

	/**
	 * Metodo modificador de circuitoBO.
	 * 
	 * @param circuitoBO
	 *            El circuitoBO a modificar.
	 */
	public void setCircuitoBO(CircuitoBO circuitoBO) {
		this.circuitoBO = circuitoBO;
	}

	/**
	 * Metodo accesor de regionBO.
	 * 
	 * @return Retorna el regionBO.
	 */
	public RegionBO getRegionBO() {
		return this.regionBO;
	}

	/**
	 * Metodo modificador de regionBO.
	 * 
	 * @param regionBO
	 *            El regionBO a modificar.
	 */
	public void setRegionBO(RegionBO regionBO) {
		this.regionBO = regionBO;
	}

	/**
	 * Metodo accesor de subEstacionBO.
	 * 
	 * @return Retorna el subEstacionBO.
	 */
	public SubEstacionBO getSubEstacionBO() {
		return this.subEstacionBO;
	}

	/**
	 * Metodo modificador de subEstacionBO.
	 * 
	 * @param subEstacionBO
	 *            El subEstacionBO a modificar.
	 */
	public void setSubEstacionBO(SubEstacionBO subEstacionBO) {
		this.subEstacionBO = subEstacionBO;
	}

	/**
	 * Metodo accesor de subRegionBO.
	 * 
	 * @return Retorna el subRegionBO.
	 */
	public SubRegionBO getSubRegionBO() {
		return this.subRegionBO;
	}

	/**
	 * Metodo modificador de subRegionBO.
	 * 
	 * @param subRegionBO
	 *            El subRegionBO a modificar.
	 */
	public void setSubRegionBO(SubRegionBO subRegionBO) {
		this.subRegionBO = subRegionBO;
	}

	/**
	 * Metodo accesor de noPropiaSeccionamientoBO.
	 * 
	 * @return Retorna el noPropiaSeccionamientoBO.
	 */
	public NoPropiaSeccionamientoBO getNoPropiaSeccionamientoBO() {
		return this.noPropiaSeccionamientoBO;
	}

	/**
	 * Metodo modificador de noPropiaSeccionamientoBO.
	 * 
	 * @param noPropiaSeccionamientoBO
	 *            El noPropiaSeccionamientoBO a modificar.
	 */
	public void setNoPropiaSeccionamientoBO(NoPropiaSeccionamientoBO noPropiaSeccionamientoBO) {
		this.noPropiaSeccionamientoBO = noPropiaSeccionamientoBO;
	}

	/**
	 * Metodo accesor de fechaFinal.
	 * 
	 * @return Retorna el fechaFinal.
	 */
	public Date getFechaFinal() {
		return this.fechaFinal;
	}

	/**
	 * Metodo modificador de fechaFinal.
	 * 
	 * @param fechaFinal
	 *            El fechaFinal a modificar.
	 */
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	/**
	 * Metodo accesor de fechaInicio.
	 * 
	 * @return Retorna el fechaInicio.
	 */
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	/**
	 * Metodo modificador de fechaInicio.
	 * 
	 * @param fechaInicio
	 *            El fechaInicio a modificar.
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Metodo accesor de tipoInterrupcion.
	 * 
	 * @return Retorna el tipoInterrupcion.
	 */
	public Integer getTipoInterrupcion() {
		return this.tipoInterrupcion;
	}

	/**
	 * Metodo modificador de tipoInterrupcion.
	 * 
	 * @param tipoInterrupcion
	 *            El tipoInterrupcion a modificar.
	 */
	public void setTipoInterrupcion(Integer tipoInterrupcion) {
		this.tipoInterrupcion = tipoInterrupcion;
	}

	/**
	 * Metodo accesor de email.
	 * 
	 * @return Retorna el email.
	 */
	public Boolean getEmail() {
		return this.email;
	}

	/**
	 * Metodo modificador de email.
	 * 
	 * @param email
	 *            El email a modificar.
	 */
	public void setEmail(Boolean email) {
		this.email = email;
	}

	/**
	 * Metodo accesor de tipoBitacora.
	 * 
	 * @return Retorna el tipoBitacora.
	 */
	public Integer getTipoBitacora() {
		return this.tipoBitacora;
	}

	/**
	 * Metodo modificador de tipoBitacora.
	 * 
	 * @param tipoBitacora
	 *            El tipoBitacora a modificar.
	 */
	public void setTipoBitacora(Integer tipoBitacora) {
		this.tipoBitacora = tipoBitacora;
	}

	/**
	 * Metodo accesor de interrupcionBO.
	 * 
	 * @return Retorna el interrupcionBO.
	 */
	public InterrupcionBO getInterrupcionBO() {
		return this.interrupcionBO;
	}

	/**
	 * Metodo modificador de interrupcionBO.
	 * 
	 * @param interrupcionBO
	 *            El interrupcionBO a modificar.
	 */
	public void setInterrupcionBO(InterrupcionBO interrupcionBO) {
		this.interrupcionBO = interrupcionBO;
	}

	@Override
	protected String getPropertyFieldName(String property) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void resetController() {
		// TODO Auto-generated method stub

	}
}
