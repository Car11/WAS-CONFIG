package cr.go.ice.interrupciones.servicios;

/**
 * 
 * <p>TODO <<Interface|Clase>> cr.go.ice.energia.sige.transformadores.servicios.DatosConsultaPueblos.java</p>
 * <p>Módulo (subsistema): TODO <<Sistema>></p>
 * <p>TODO (Descripción) La <<Interface|Clase>> <code>DatosConsultaPueblos.java</code>.</p>
 *    Clase con el objeto de tipo consulta de pueblos
 * <p>Fecha creación: 13/07/2011</p>
 * <p>Última actualización: 13/07/2011</p>
 * @author Vista Verde Tecnología (eperaza)
 * @version 1.0
 */
public class DatosConsultaPueblos 
{
	private Integer tipoConsulta;
	private String	nombre;
	private Integer pueblo;
	private Integer agencia;
	
	public DatosConsultaPueblos() 
	{
		this.agencia 		= Integer.valueOf(0);
		this.nombre 		= ".";
		this.pueblo 		= Integer.valueOf(0);
		this.tipoConsulta 	= Integer.valueOf(0);
	}

	public Integer getAgencia() {
		return agencia;
	}

	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getPueblo() {
		return pueblo;
	}

	public void setPueblo(Integer pueblo) {
		this.pueblo = pueblo;
	}

	public Integer getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(Integer tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}
	
	
}
