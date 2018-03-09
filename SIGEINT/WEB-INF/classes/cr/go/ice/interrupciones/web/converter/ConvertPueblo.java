package cr.go.ice.interrupciones.web.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import cr.go.ice.interrupciones.domain.Pueblo;


/**
 * <p>Clase cr.go.ice.interrupciones.web.converter.ConvertPueblo.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ConvertPueblo.java</code>.</p>
 * <p>Fecha creación: 15/05/2007</p>
 * <p>Ultima actualización: 15/05/2007</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class ConvertPueblo implements Converter{

	/**
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
	    try {
			Pueblo pueblo = new Pueblo();
			String []values = value.split("-");
            
			if (values.length > 0) {				
				pueblo.setCodigoPueblo(new Integer(values[0].trim()));
				pueblo.setNombrePueblo(values[1]);			
				return pueblo;
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

	/**
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	public String getAsString(FacesContext context, UIComponent component, Object object) throws ConverterException {
		String valor = "";
		if(object instanceof Pueblo){
			Pueblo pueblo = (Pueblo) object;
			valor = pueblo.getCodigoPueblo() + "-" + pueblo.getNombrePueblo();
		}
		else{
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error de conversión, el objeto no corresponde a un Pueblo");
			msg.setDetail("Error de conversión, el objeto no corresponde a un Pueblo");
			throw new ConverterException(msg);
		}
		return valor;
	}

}
