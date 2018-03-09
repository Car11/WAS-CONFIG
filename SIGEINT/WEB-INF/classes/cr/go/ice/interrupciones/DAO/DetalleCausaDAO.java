package cr.go.ice.interrupciones.DAO;

import java.util.List;

import cr.go.ice.interrupciones.domain.DetalleCausa;

public interface DetalleCausaDAO {
	
	public void agregar(DetalleCausa detalleCausa);
	
	public void modificar(DetalleCausa detalleCausa);
	
	public void eliminar(DetalleCausa detalleCausa);
	
	public boolean existe(Integer codigo, Integer tipoCausaEspecifica, Integer tipoCausa);
	
	public DetalleCausa buscar(DetalleCausa detalleCausa);
	
	public List<DetalleCausa> buscarTodos();
	
	public List<DetalleCausa> buscarTodosEspecifica(Integer tipoCausa, Integer tipoCausaEspecifica);

}
