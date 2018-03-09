package cr.go.ice.interrupciones.domain;

import java.io.Serializable;

public class UbicacionEspecificaDanoID implements Serializable{
	
	private static final long serialVersionUID = -8277712335299414406L;
	
	private Integer danoEspecifico;
	
	private UbicacionGeneralDano ubicacionGeneralDano;
	
	public UbicacionEspecificaDanoID()
	{
		this.danoEspecifico = new Integer(0);
		this.ubicacionGeneralDano = new UbicacionGeneralDano();
	}

	public Integer getDanoEspecifico() {
		return danoEspecifico;
	}

	public void setDanoEspecifico(Integer danoEspecifico) {
		this.danoEspecifico = danoEspecifico;
	}

	public UbicacionGeneralDano getUbicacionGeneralDano() {
		return ubicacionGeneralDano;
	}

	public void setUbicacionGeneralDano(UbicacionGeneralDano ubicacionGeneralDano) {
		this.ubicacionGeneralDano = ubicacionGeneralDano;
	}
}
