
package cr.go.ice.interrupciones.web.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import cr.go.ice.interrupciones.domain.Circuito;
import cr.go.ice.interrupciones.domain.CircuitoID;
import cr.go.ice.interrupciones.domain.SubEstacion;


/**
 * <p>Clase cr.go.ice.interrupciones.web.converter.ConvertCircuito.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ConvertCircuito.java</code> Convertirdor de <code>String</code> a Objeto.</p>
 * <p>Fecha creación: 03/04/2007</p>
 * <p>Ultima actualización: 03/04/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public class ConvertCircuito implements Converter{

	/**
	 * Constructor
	 */
	public ConvertCircuito(){
		
	}
	/* (sin Javadoc)
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	/**
	 * Comment for getAsObject
	 * @param context
	 * @param component
	 * @param value
	 * @return ConverterException(msg)o Circuito
	 * @throws ConverterException
	 */
	public Object getAsObject(FacesContext context, UIComponent component, String value)throws ConverterException  {
        try{
            Circuito circuito = new Circuito();
            String values [] = value.split("_");
            if(values.length > 0){
            	CircuitoID circuitoID = new CircuitoID();
            	SubEstacion sub = new SubEstacion();            	
            	circuitoID.setCircuito(new Integer(values[0]));
            	sub.setCodigoSubEstacion(new Integer(values[1]));            	
            	circuitoID.setSubEstacion(sub);
            	circuito.setCircuitoID(circuitoID);
            	circuito.setNombreCircuito(values[2]);
	            return circuito;
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

	/* (sin Javadoc)
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	/**
	 * Comment for getAsString
	 * @param context
	 * @param component
	 * @param object
	 * @return circuito.getCircuitoID().getCircuito() + "_" + circuito.getCircuitoID().getSubEstacion().getCodigoSubEstacion()
	 * @throws ConverterException
	 */
	public String getAsString(FacesContext context, UIComponent component, Object object)throws ConverterException {
        if(object instanceof Circuito){
        	Circuito circuito = (Circuito) object;
            return circuito.getCircuitoID().getCircuito() + "_" + circuito.getCircuitoID().getSubEstacion().getCodigoSubEstacion() + "_" + circuito.getNombreCircuito();
        } else{
            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            msg.setSummary("No se puede convertir un objeto que no es de tipo Circuito.");
            msg.setDetail("No se puede convertir un objeto que no es de tipo Circuito.");
            throw new ConverterException(msg);
        }
	}

}
