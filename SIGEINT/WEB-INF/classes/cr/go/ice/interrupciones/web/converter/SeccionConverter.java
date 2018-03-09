package cr.go.ice.interrupciones.web.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import cr.go.ice.interrupciones.domain.Seccion;
import cr.go.ice.interrupciones.domain.SeccionID;

/**
 * <p>Clase cr.go.ice.interrupciones.web.converter.SeccionConverter.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>SeccionConverter.java</code> Convertirdor de <code>String</code> a Objeto.</p>
 * <p>Fecha creación: 03/04/2007</p>
 * <p>Ultima actualización: 03/04/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public class SeccionConverter implements Converter{

	/**
	 * Comment for getAsObject
	 * @param context
	 * @param component
	 * @param value
	 * @return Seccion
	 */
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		try {
			SeccionID seccionID = new SeccionID();
			Seccion seccion = new Seccion();
			String[] valores = value.split("~");
			if(valores.length == 8) {
				
				seccionID.setSeccion(new Long(Long.parseLong(valores[0].trim()))); 
				seccionID.setSubEstacion(new Integer(Integer.parseInt(valores[1].trim())));
				seccionID.setCircuito(new Integer(Integer.parseInt(valores[2].trim())));
				
				
				seccion.setSeccionID(seccionID);
				seccion.setNombreSeccion(valores[3].trim());
				seccion.setKmsSeccion(new Double(Double.parseDouble(valores[4].trim())));
				seccion.setAbonadoSeccion(new Long(Long.parseLong(valores[5].trim())));
				seccion.setRegion(new Integer(Integer.parseInt(valores[6].trim())));
				seccion.setSubRegion(new Integer(Integer.parseInt(valores[7].trim())));

				
				return seccion;
				
			} else {
				
				FacesMessage msg = new FacesMessage();
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary("Error de conversión de seccion");				
				throw new ConverterException(msg);
				
				
			}
			
		} catch (NumberFormatException e){
			
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ConverterException(msg);
		
		}
		
	}


	/**
	 * Comment for getAsString
	 * @param context
	 * @param component
	 * @param object
	 * @return Valor
	 */
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		
		String valor = "";
		
		if(object instanceof Seccion) { 
			
			Seccion seccion = (Seccion) object;					
			valor = seccion.getSeccionID().getSeccion() + "~" + 
					seccion.getSeccionID().getSubEstacion() + "~" + 
					seccion.getSeccionID().getCircuito() + "~" + 
					seccion.getNombreSeccion() + "~" + 
					seccion.getKmsSeccion() + "~" + 
					seccion.getAbonadoSeccion() + "~" + 
					seccion.getRegion() + "~" + 
					seccion.getSubRegion();
			
		} else {
			
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error de conversión, el objeto no corresponde a una sección");
			throw new ConverterException(msg);
			
		}
		return valor;
		
	}
	
}
