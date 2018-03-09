package cr.go.ice.interrupciones.BO.impl;

import java.util.List;

import cr.go.ice.interrupciones.BO.AnimalBO;
import cr.go.ice.interrupciones.DAO.AnimalDAO;
import cr.go.ice.interrupciones.domain.Animal;


/**
 * <p>Clase cr.go.ice.interrupciones.BO.impl.AnimalBOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>AnimalBOImpl.java</code>Define los Metodos de Logica y Negocio de las Clases para Animal.</p>
 * <p>Fecha creación: 26/01/2007</p>
 * <p>Ultima actualización: 26/01/2007</p>
 * @author Vista Verde Soft (Cesar y Doc Cristian)
 * @version 1.1
 */
public class AnimalBOImpl implements AnimalBO {
	
	 private AnimalDAO animalDAO;
	 
	/**
	 * Asigna AnimalDAO
	 * @param animalDAO
	 */
	public void setAnimalDAO(AnimalDAO animalDAO) {
		this.animalDAO = animalDAO;
	}
    
	/**
	 * @see cr.go.ice.interrupciones.BO.AnimalBO#agregar(cr.go.ice.interrupciones.domain.Animal)
	 */
	public void agregar(Animal animal) {
		this.animalDAO.agregar(animal);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.AnimalBO#modificar(cr.go.ice.interrupciones.domain.Animal)
	 */
	public void modificar(Animal animal) {
		this.animalDAO.modificar(animal);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.AnimalBO#eliminar(cr.go.ice.interrupciones.domain.Animal)
	 */
	public void eliminar(Animal animal) {
		this.animalDAO.eliminar(animal);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.AnimalBO#buscar(cr.go.ice.interrupciones.domain.Animal)
	 */
	public Animal buscar(Integer codigoAnimal) {		
		return this.animalDAO.buscar(codigoAnimal);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.AnimalBO#getAnimales()
	 */
	public List getAnimales() {
		return this.animalDAO.getAnimales();
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.AnimalBO#animalExiste(cr.go.ice.interrupciones.domain.Animal)
	 */
	public boolean animalExiste(Animal animal){
        return this.animalDAO.animalExiste(animal);
    }
    
    /**
     * @see cr.go.ice.interrupciones.BO.AnimalBO#registrosAsociados(java.lang.Integer)
     */
    public Long registrosAsociados(Integer codigoAnimal) {
        return this.animalDAO.registrosAsociados(codigoAnimal);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.AnimalBO#getAnimalesOrdenNombre()
     */
    public List getAnimalesOrdenNombre() {
        return this.animalDAO.getAnimales();
    }
	

}
