
package cr.go.ice.interrupciones.web.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import cr.go.ice.interrupciones.domain.TipoVoltaje;
import cr.go.ice.interrupciones.domain.TipoVoltajeID;

/**
 * <p>Clase cr.go.ice.interrupciones.web.converter.ConvertTipoVoltaje.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ConvertTipoVoltaje.java</code> Convertirdor de <code>String</code> a Objeto.</p>
 * <p>Fecha creación: 03/04/2007</p>
 * <p>Ultima actualización: 03/04/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public class ConvertTipoVoltaje implements Converter{

	/* (sin Javadoc)
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	/**
	 * Comment for getAsObject
	 * @param context
	 * @param component
	 * @param value
	 * @return Voltaje
	 * @throws ConverterException
	 */
	public Object getAsObject(FacesContext context, UIComponent component, String value)throws ConverterException {
	    try {
			TipoVoltaje voltaje = new TipoVoltaje();
			//String index = s value.indexOf("-");
			String []values = value.split("-");
			if (values.length > 0) {
				TipoVoltajeID tipoVoltajeID = new TipoVoltajeID();
				tipoVoltajeID.setCodigoVoltaje(new Integer(values[0]));
				tipoVoltajeID.setTipoVoltaje(new Integer(values[1]));				
				voltaje.setTipoVoltajeID(tipoVoltajeID);				
				voltaje.setCodigoDescripcion(values[2]);				
				return voltaje;
			} else {
				FacesMessage msg = new FacesMessage();
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary("No hay datos para convertir.");
				msg.setDetail("No hay datos para convertir.");
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

	/* (sin Javadoc)
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	/**
	 * Comment for getAsString
	 * @param context
	 * @param component
	 * @param object
	 * @return Valor
	 * @throws ConverterException
	 */
	public String getAsString(FacesContext context, UIComponent component, Object object)throws ConverterException {
		String valor = "";
		if(object instanceof TipoVoltaje){
			TipoVoltaje voltaje = (TipoVoltaje) object;
			valor = voltaje.getTipoVoltajeID().getCodigoVoltaje() + "-" + voltaje.getTipoVoltajeID().getTipoVoltaje() + "-" + voltaje.getCodigoDescripcion();
			//valor = voltaje.getTipoVoltajeID().getCodigoVoltaje() + "-" + voltaje.getCodigoDescripcion();
		}
		else{
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error de conversión, el objeto no corresponde a un TipoVoltaje");
			msg.setDetail("Error de conversión, el objeto no corresponde a un TipoVoltaje");
			throw new ConverterException(msg);
		}
		return valor;
	}

}
