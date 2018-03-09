package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

public class DetalleCausa implements Serializable{
	
	private static final long serialVersionUID = -15444374292258835L;
	
	private DetalleCausaID detalleCausaID;
	
	private String descripcion;
	
	private Integer estado;
	
	public DetalleCausa()
	{
		this.detalleCausaID = new DetalleCausaID();
		this.descripcion = "";
		this.estado= new Integer(0);
	}

	public DetalleCausaID getDetalleCausaID() {
		return detalleCausaID;
	}

	public void setDetalleCausaID(DetalleCausaID detalleCausaID) {
		this.detalleCausaID = detalleCausaID;
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
