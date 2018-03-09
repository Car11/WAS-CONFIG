package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

public class UbicacionEspecificaDano implements Serializable{
	
	private static final long serialVersionUID = -8273235299131241406L;
	
	private UbicacionEspecificaDanoID ubicacionEspecificaDanoID;
	
	private String descripcion;
	
	private Integer estado;
	
	public UbicacionEspecificaDano()
	{
		this.ubicacionEspecificaDanoID = new UbicacionEspecificaDanoID();
		this.descripcion = "";
		this.estado = new Integer(0);
	}

	public UbicacionEspecificaDanoID getUbicacionEspecificaDanoID() {
		return ubicacionEspecificaDanoID;
	}
	
	public void setUbicacionEspecificaDanoID(UbicacionEspecificaDanoID ubicacionEspecificaDanoID) {
		this.ubicacionEspecificaDanoID = ubicacionEspecificaDanoID;
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
