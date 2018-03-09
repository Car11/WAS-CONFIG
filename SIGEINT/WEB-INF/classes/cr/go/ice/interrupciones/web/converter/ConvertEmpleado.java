package cr.go.ice.interrupciones.web.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import cr.go.ice.interrupciones.domain.Empleado;

/**
 * <p>Clase cr.go.ice.interrupciones.web.converter.ConvertEmpleado.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ConvertEmpleado.java</code>.</p>
 * <p>Fecha creación: 18/05/2007</p>
 * <p>Ultima actualización: 18/05/2007</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class ConvertEmpleado implements Converter{

	/**
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */    
	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
	    try {
			Empleado empleado = new Empleado();
			String []values = value.split("-");
			if (values.length == 2) {				
				empleado.setCedula(new Long(values[0].trim()));
				empleado.setNombreEmpleado(values[1]);			
				return empleado;
			} else {
				FacesMessage msg = new FacesMessage();
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary("Error de conversión de empleado");				
				throw new ConverterException(msg);
			}
		} catch (NumberFormatException e) {
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Datos para convertir no son validos.");
			msg.setDetail("Datos para convertir no son validos.");
			throw new ConverterException(msg);
		}
	}

	/**
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	public String getAsString(FacesContext context, UIComponent component, Object object) throws ConverterException {
		String valor = "";
		if(object instanceof Empleado){
			Empleado empleado = (Empleado) object;
			valor = empleado.getCedula() + "-" + empleado.getNombreEmpleado();
		}
		else{
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error de conversión, el objeto no corresponde a un Empleado");
			msg.setDetail("Error de conversión, el objeto no corresponde a un Empleado");
			throw new ConverterException(msg);
		}
		return valor;
	}

}
