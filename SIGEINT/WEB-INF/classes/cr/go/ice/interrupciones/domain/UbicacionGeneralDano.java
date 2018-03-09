package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

public class UbicacionGeneralDano implements Serializable{
	
	private static final long serialVersionUID = -8277732359406L;
	
	private Integer codigo;
	
	private String descripcion;
	
	private Integer estado;
	
	public UbicacionGeneralDano()
	{
		this.codigo = new Integer(0);
		this.descripcion = "";
		this.estado = new Integer(0);
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

}
