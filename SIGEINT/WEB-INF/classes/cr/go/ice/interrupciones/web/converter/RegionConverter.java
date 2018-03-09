package cr.go.ice.interrupciones.web.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;

import cr.go.ice.interrupciones.domain.Region;



/**
 * <p>Clase cr.go.ice.interrupciones.web.converter.RegionConverter.java</p>
 * <p>Modulo (subsistema): <<Sistema>></p>
 * <p>Descricion de <code>RegionConverter.java</code>  Convertirdor de <code>String</code> a Objeto.</p>
 * <p>Fecha creación: 03/07/2007</p>
 * <p>Ultima actualización: 03/07/2007</p>
 * @author Vista Verde Tecnologia (Juan Manuel)
 * @version 1.1
 */
public class RegionConverter {

	/**
	 * Comment for getAsObject
	 * @param context
	 * @param component
	 * @param value
	 * @return Region
	 */
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		try {
			Region region = new Region();
			
			String[] valores = value.split("~");
			
			if(valores.length == 2) {
				
				region.setRegion(new Integer(Integer.parseInt(valores[0].trim())));
				region.setNombreRegion(valores[1].trim());
				
				return region;
				
			} else {
				
				FacesMessage msg = new FacesMessage();
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary("Error de conversión de region");				
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
		
		if(object instanceof Region) { 
			
			Region region = (Region) object;					
			valor = region.getRegion() + "~" + region.getNombreRegion();
			
		} else {
			
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error de conversión, el objeto no corresponde a una region");
			throw new ConverterException(msg);
			
		}

		return valor;
		
	}
	
}
