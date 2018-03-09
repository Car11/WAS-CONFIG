package cr.go.ice.interrupciones.servicios;

/**
 * 
 * <p>TODO <<Interface|Clase>> cr.go.ice.energia.sige.transformadores.servicios.ConsultaPueblos.java</p>
 * <p>M�dulo (subsistema): TODO <<Sistema>></p>
 * <p>TODO (Descripci�n) La <<Interface|Clase>> <code>ConsultaPueblos.java</code>.</p>
 *    Clase que contiene la estructura de los datos que se obtienen en la respuesta del broker
 * <p>Fecha creaci�n: 13/07/2011</p>
 * <p>�ltima actualizaci�n: 13/07/2011</p>
 * @author Vista Verde Tecnolog�a (eperaza)
 * @version 1.0
 */
public class ConsultaPueblos 
{
	private Encabezado 				encabezado;
	private DatosConsultaPueblos 	datos;
	
	public ConsultaPueblos() 
	{
		this.encabezado = new Encabezado();
		this.datos		= new DatosConsultaPueblos();
	}

	public DatosConsultaPueblos getDatos() {
		return datos;
	}

	public void setDatos(DatosConsultaPueblos datos) {
		this.datos = datos;
	}

	public Encabezado getEncabezado() {
		return encabezado;
	}

	public void setEncabezado(Encabezado encabezado) {
		this.encabezado = encabezado;
	}
	
	

}
