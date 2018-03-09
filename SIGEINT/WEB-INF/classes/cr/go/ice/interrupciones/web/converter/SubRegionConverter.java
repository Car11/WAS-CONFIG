package cr.go.ice.interrupciones.web.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import cr.go.ice.interrupciones.domain.Region;
import cr.go.ice.interrupciones.domain.SubRegion;
import cr.go.ice.interrupciones.domain.SubRegionID;

/**
 * <p>Clase cr.go.ice.interrupciones.web.converter.ConvertCircuito.java</p>
 * <p>Modulo (subsistema): Obras</p>
 * <p>Descricion de <code>SubRegionConverter.java</code> Convertirdor de <code>String</code> a Objeto.</p>
 * <p>Fecha creación: 10/03/2007</p>
 * <p>Ultima actualización: 10/03/2007</p>
 * @author Vista Verde Soft (Juan Manuel y Doc Cristian)
 * @version 1.1
 */

public class SubRegionConverter implements Converter{

	/**
	 * Comment for getAsObject
	 * @param context
	 * @param component
	 * @param value
	 * @return subRegion o <code>new</code> ConverterException(msg)
	 */
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		try {
			
			SubRegion subRegion = new SubRegion();
			String[] valores = value.split("-");
			
			if(valores.length == 3) {				
				Region region = new Region();
				region.setRegion(new Integer(Integer.parseInt(valores[0].trim())));				
				SubRegionID subRegionID = new SubRegionID();
				subRegionID.setSubRegion(new Integer(Integer.parseInt(valores[1].trim())));
				subRegionID.setRegion(region);				
				subRegion.setSubRegionID(subRegionID); 
				subRegion.setNombreSubRegion(valores[2].trim());				
				return subRegion;
				
			} else {
				
				FacesMessage msg = new FacesMessage();
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary("Error de conversión de Sub Región");				
				throw new ConverterException(msg);
				
			}
			
		} catch (NumberFormatException e){
			
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error de conversión de Sub Región");
			throw new ConverterException(msg);
		
		}
		
	}


	/**
	 * Comment for getAsString
	 * @param context
	 * @param component
	 * @param object
	 * @return valor
	 */
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		
		String valor = "";
		
		if(object instanceof SubRegion) { 
			
			SubRegion subRegion = (SubRegion) object;			
			valor = subRegion.getSubRegionID().getRegion().getRegion() + "-" + subRegion.getSubRegionID().getSubRegion() + "-" + subRegion.getNombreSubRegion();
			
		} else {
			
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error de conversión, el objeto no corresponde una Sub Región");
			throw new ConverterException(msg);
			
		}
		
		return valor;
		
	}
	
}
