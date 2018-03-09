package cr.go.ice.interrupciones.servicios;

/**
 * 
 * <p>TODO <<Interface|Clase>> cr.go.ice.energia.sige.transformadores.servicios.RespuestaConsultaPueblos.java</p>
 * <p>M�dulo (subsistema): TODO <<Sistema>></p>
 * <p>TODO (Descripci�n) La <<Interface|Clase>> <code>RespuestaConsultaPueblos.java</code>.</p>
 *    Clase que contiene la estructura de los datos que se envia en la consulta al broker
 * <p>Fecha creaci�n: 13/07/2011</p>
 * <p>�ltima actualizaci�n: 13/07/2011</p>
 * @author Vista Verde Tecnolog�a (eperaza)
 * @version 1.0
 */
public class RespuestaConsultaPueblos 
{
	private Encabezado 						encabezado;
	private DatosRespuestaConsultaPueblos 	datos;
	
	public RespuestaConsultaPueblos() 
	{
		this.encabezado = new Encabezado();
		this.datos		= new DatosRespuestaConsultaPueblos();
	}

	public DatosRespuestaConsultaPueblos getDatos() {
		return datos;
	}

	public void setDatos(DatosRespuestaConsultaPueblos datos) {
		this.datos = datos;
	}

	public Encabezado getEncabezado() {
		return encabezado;
	}

	public void setEncabezado(Encabezado encabezado) {
		this.encabezado = encabezado;
	}
	
	
}
