package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

public class TiposCausaEspecificaID implements Serializable{
	
	private static final long serialVersionUID = -82777235299414406L;
	
	private Integer causaEspecifica;
	
	private TiposCausa tiposCausa;
	
	public TiposCausaEspecificaID()
	{
		this.causaEspecifica = new Integer(0);
		this.tiposCausa = new TiposCausa();
	}

	public Integer getCausaEspecifica() {
		return causaEspecifica;
	}

	public void setCausaEspecifica(Integer causaEspecifica) {
		this.causaEspecifica = causaEspecifica;
	}

	public TiposCausa getTiposCausa() {
		return tiposCausa;
	}

	public void setTiposCausa(TiposCausa tiposCausa) {
		this.tiposCausa = tiposCausa;
	}
}
