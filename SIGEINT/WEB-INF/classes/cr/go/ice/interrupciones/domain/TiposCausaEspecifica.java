package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

public class TiposCausaEspecifica implements Serializable{
	
	private static final long serialVersionUID = -82777323529941406L;
	
	private TiposCausaEspecificaID tiposCausaEspecificaID;
	
	private String descripcion;
	
	private Integer estado;
	
	public TiposCausaEspecifica()
	{
		this.tiposCausaEspecificaID = new TiposCausaEspecificaID();
		this.descripcion = "";
		this.estado = new Integer(0);
	}

	public TiposCausaEspecificaID getTiposCausaEspecificaID() {
		return tiposCausaEspecificaID;
	}

	public void setTiposCausaEspecificaID(TiposCausaEspecificaID tiposCausaEspecificaID) {
		this.tiposCausaEspecificaID = tiposCausaEspecificaID;
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
