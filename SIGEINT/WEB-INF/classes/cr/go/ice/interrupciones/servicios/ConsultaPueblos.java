package cr.go.ice.interrupciones.servicios;

/**
 * 
 * <p>TODO <<Interface|Clase>> cr.go.ice.energia.sige.transformadores.servicios.ConsultaPueblos.java</p>
 * <p>Módulo (subsistema): TODO <<Sistema>></p>
 * <p>TODO (Descripción) La <<Interface|Clase>> <code>ConsultaPueblos.java</code>.</p>
 *    Clase que contiene la estructura de los datos que se obtienen en la respuesta del broker
 * <p>Fecha creación: 13/07/2011</p>
 * <p>Última actualización: 13/07/2011</p>
 * @author Vista Verde Tecnología (eperaza)
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
