package cr.go.ice.interrupciones.BO;

import java.util.List;

import cr.go.ice.interrupciones.domain.UbicacionEspecificaDano;

public interface UbicacionEspecificaDanoBO {
	
	public void agregar(UbicacionEspecificaDano ubicacionEspecificaDano);
	
	public void modificar(UbicacionEspecificaDano ubicacionEspecificaDano);
	
	public void eliminar(UbicacionEspecificaDano ubicacionEspecificaDano);
	
	public boolean existe(Integer codigo, Integer codigoGeneral);
	
	public UbicacionEspecificaDano buscar(UbicacionEspecificaDano ubicacionEspecificaDano);
	
	public List<UbicacionEspecificaDano> buscarTodos();
	
	public List<UbicacionEspecificaDano> buscarTodosGeneral(Integer ubicacionGeneral);

}
