package cr.go.ice.interrupciones.servicios;

import java.util.ArrayList;

import cr.go.ice.sace.sacecon.domain.Pueblo;

/**
 * 
 * <p>TODO <<Interface|Clase>> cr.go.ice.energia.sige.transformadores.servicios.DatosRespuestaConsultaPueblos.java</p>
 * <p>M�dulo (subsistema): TODO <<Sistema>></p>
 * <p>TODO (Descripci�n) La <<Interface|Clase>> <code>DatosRespuestaConsultaPueblos.java</code>.</p>
 *    Clase con el objeto de tipo respuesta de pueblos
 * <p>Fecha creaci�n: 13/07/2011</p>
 * <p>�ltima actualizaci�n: 13/07/2011</p>
 * @author Vista Verde Tecnolog�a (eperaza)
 * @version 1.0
 */
public class DatosRespuestaConsultaPueblos 
{
	private ArrayList pueblos;

	public DatosRespuestaConsultaPueblos() {
		this.pueblos = new ArrayList<Pueblo>();
	}

	public ArrayList getPueblos() {
		return pueblos;
	}

	public void setPueblos(ArrayList list) {
		this.pueblos = list;
	}
	
	
}
