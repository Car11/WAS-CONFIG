package cr.go.ice.interrupciones.web.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import cr.go.ice.interrupciones.domain.Region;

/**
 * <p>Clase cr.go.ice.interrupciones.web.converter.ConvertRegion.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ConvertRegion.java</code> Convertirdor de <code>String</code> a Objeto.</p>
 * <p>Fecha creación: 03/04/2007</p>
 * <p>Ultima actualización: 03/04/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public class ConvertRegion implements Converter{

	/* (sin Javadoc)
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	/**
	 * Comment for getAsObject
	 * @param context
	 * @param component
	 * @param value
	 * @return Region
	 * @throws ConverterException
	 */
	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException{
	    try {
			Region region = new Region();
			int index = value.indexOf("-");
			if (index > 0) {
				region.setRegion(new Integer(value.substring(0, index)));
				region.setNombreRegion(value.substring(index + 1, value.length()));
				return region;
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

	/*
	 * (sin Javadoc)
	 * 
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext,
	 *      javax.faces.component.UIComponent, java.lang.Object)
	 */
	/**
	 * Comment for getAsString
	 * @param context
	 * @param component
	 * @param object
	 * @return valor 
	 * @throws ConverterException
	 */
	public String getAsString(FacesContext context, UIComponent component, Object object) throws ConverterException{
		String valor = "";
		if(object instanceof Region){
			Region region = (Region) object;
			valor = region.getRegion() + "-" + region.getNombreRegion();
		}
		else{
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error de conversión, el objeto no corresponde a una región");
			msg.setDetail("Error de conversión, el objeto no corresponde a una región");
			throw new ConverterException(msg);
		}
		return valor;
	}

	
	
}
