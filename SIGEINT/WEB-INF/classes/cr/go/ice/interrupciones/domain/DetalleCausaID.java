package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

public class DetalleCausaID implements Serializable{
	
	private static final long serialVersionUID = 952753699895871657L;
	
	private Integer detalleCausa;
	
	private TiposCausaEspecifica tiposCausaEspecifica;
	
	public DetalleCausaID()
	{
		this.detalleCausa = new Integer(0);
		this.tiposCausaEspecifica = new TiposCausaEspecifica();
	}

	public Integer getDetalleCausa() {
		return detalleCausa;
	}

	public void setDetalleCausa(Integer detalleCausa) {
		this.detalleCausa = detalleCausa;
	}

	public TiposCausaEspecifica getTiposCausaEspecifica() {
		return tiposCausaEspecifica;
	}

	public void setTiposCausaEspecifica(TiposCausaEspecifica tiposCausaEspecifica) {
		this.tiposCausaEspecifica = tiposCausaEspecifica;
	}

}
