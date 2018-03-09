package cr.go.ice.interrupciones.BO;

import java.util.List;

import cr.go.ice.interrupciones.domain.UbicacionGeneralDano;

public interface UbicacionGeneralDanoBO {
	
	public void agregar(UbicacionGeneralDano ubicacionGeneralDano);
	
	public void modificar(UbicacionGeneralDano ubicacionGeneralDano);
	
	public void eliminar(UbicacionGeneralDano ubicacionGeneralDano);
	
	public boolean existe(Integer codigo);
	
	public UbicacionGeneralDano buscar(UbicacionGeneralDano ubicacionGeneralDano);
	
	public List<UbicacionGeneralDano> buscarTodos();

}
