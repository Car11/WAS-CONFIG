package cr.go.ice.interrupciones.DAO;

import java.util.List;

import cr.go.ice.interrupciones.domain.Animal;

/**

 * <p>Interface cr.go.ice.interrupciones.DAO.AnimalDAO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>AnimalDAO.java</code> Define los metodos de los Datos para Animal</p>
 * <p>Fecha creación: 26/01/2007</p>
 * <p>Ultima actualización: 26/01/2007</p>
 * @author Vista Verde Tecnologia (Cesar y Doc Cristian)
 * @version 1.1
 */
public interface AnimalDAO {
	
    /**
     * Agrega un Animal
     * @param animal a agregar
     */
	public void agregar(Animal animal);
	    
    /**
     * Modifica un Animal
     * @param animal a modificar
     */
	public void modificar(Animal animal);
	    
    /**
     * Elimina un Animal
     * @param animal a eliminar
     */
	public void eliminar(Animal animal);
	    
    /**
     * Busca un Animal
     * @param codigoAnimal Código de animal deseado
     * @return Animal y null si no esta
     */
	public Animal buscar(Integer codigoAnimal);
	    
    /**
     * Lista de animales
     * @return Lista Animales
     */
	public List getAnimales();
    
    /**
     * Lista de animales
     * @return Lista Animales
     */
    public List getAnimalesOrdenNombre();    
	 
    /**
     * Determina la existencia de un Animal
     * @param animal Animal a determinar la existencia
     * @return true o false
     */
	public boolean animalExiste(Animal animal);
	 
	/**
	 * Retorna la cantidad de registros asociados en las interrupciones mayores y menores a cinco minutos
	 * @param codigoAnimal que indica el codigoAnimal
	 * @return cantidad de registros asociados
	 */
	public Long registrosAsociados(Integer codigoAnimal);  
	
}
