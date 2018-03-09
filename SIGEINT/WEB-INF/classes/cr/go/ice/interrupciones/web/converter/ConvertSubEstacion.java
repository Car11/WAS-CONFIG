
package cr.go.ice.interrupciones.web.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import cr.go.ice.interrupciones.domain.SubEstacion;

/**
 * <p>Clase cr.go.ice.interrupciones.web.converter.ConvertSubEstacion.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ConvertSubEstacion.java</code> Convertirdor de <code>String</code> a Objeto.</p>
 * <p>Fecha creación: 08/02/2007</p>
 * <p>Ultima actualización: 08/02/2007</p>
 * @author Vista Verde Soft (Mario Leon y Doc Cristian)
 * @version 1.1
 */
public class ConvertSubEstacion implements Converter {
    
    /**
     * Constructor
     */
    public ConvertSubEstacion(){
        
    }
    
    /**
     * Comment for getAsObject
     * @param context
     * @param component
     * @param value
     * @return se
     * @throws ConverterException
     */
    public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
        try{
            SubEstacion se = new SubEstacion();
            int index = value.indexOf("_");
            if(index > 0){
	            se.setCodigoSubEstacion(new Integer(value.substring(0, index)));
	            se.setNombreSubEstacion(value.substring(index+1, value.length()));
	            return se;
            } else{
                FacesMessage msg = new FacesMessage();
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                msg.setSummary("No hay datos para convertir.");
                msg.setDetail("No hay datos para convertir.");
                throw new ConverterException(msg);
            }
        } catch(NumberFormatException e){
            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            msg.setSummary("Datos para convertir no son validos.");
            msg.setDetail("Datos para convertir no son validos.");
            throw new ConverterException(msg);
        }
    }

    
    /**
     * Comment for getAsString
     * @param context
     * @param component
     * @param object
     * @return se.getCodigoSubEstacion()+"_"+se.getNombreSubEstacion()
     * @throws ConverterException
     */
    public String getAsString(FacesContext context, UIComponent component, Object object) throws ConverterException {
        if(object instanceof SubEstacion){
            SubEstacion se = (SubEstacion) object;
            return se.getCodigoSubEstacion()+"_"+se.getNombreSubEstacion();
        } else{
            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            msg.setSummary("No se puede convertir un objeto que no es de tipo SubEstación.");
            msg.setDetail("No se puede convertir un objeto que no es de tipo SubEstación.");
            throw new ConverterException(msg);
        }
    }
}