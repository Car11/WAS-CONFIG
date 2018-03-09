package cr.go.ice.interrupciones.DAO.impl;

import java.util.List;


import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cr.go.ice.interrupciones.DAO.AnimalDAO;
import cr.go.ice.interrupciones.domain.Animal;

/**
 * <p>Clase cr.go.ice.interrupciones.DAO.impl.AnimalDAOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>AnimalDAOImpl.java</code>Define los metodos de los Datos para Animal.</p>
 * <p>Fecha creación: 26/01/2007</p>
 * <p>Ultima actualización: 26/01/2007</p>
 * @author Vista Verde Tecnologia (Cesar y Doc Cristian)
 * @version 1.1
 */
public class AnimalDAOImpl extends HibernateDaoSupport implements AnimalDAO {
	


	/**
	 * @see cr.go.ice.interrupciones.DAO.AnimalDAO#agregar(cr.go.ice.interrupciones.domain.Animal)
	 */
	public void agregar(Animal animal) {	    
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.save(animal);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.AnimalDAO#modificar(cr.go.ice.interrupciones.domain.Animal)
	 */
	public void modificar(Animal animal) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.update(animal);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.AnimalDAO#eliminar(cr.go.ice.interrupciones.domain.Animal)
	 */
	public void eliminar(Animal animal) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.delete(animal);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.AnimalDAO#buscar(java.lang.Integer)
	 */
	public Animal buscar(Integer codigoAnimal) {
		Animal animal = null;
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "FROM Animal WHERE codigoAnimal = ? ";
		Object[] values = {codigoAnimal};
		List animales = hibernate.find(hql, values);
		if(animales.size()>0){
			animal = (Animal) animales.get(0);
		}
		return animal;        
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.AnimalDAO#getAnimales()
	 */
	public List getAnimales() {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from Animal ORDER BY codigoAnimal";
		List animales = hibernate.find(hql);
		return animales;			
		
	}
	
	 /**
	  * @see cr.go.ice.interrupciones.DAO.AnimalDAO#animalExiste(cr.go.ice.interrupciones.domain.Animal)
	  */
	public boolean animalExiste(Animal animal){
		return (this.buscar(animal.getCodigoAnimal()) != null);		        
        
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.AnimalDAO#registrosAsociados(java.lang.Integer)
     */
    public Long registrosAsociados(Integer codigoAnimal) {
        Long cantidad = new Long(0);
        HibernateTemplate hibernate = this.getHibernateTemplate();
        String hql = "SELECT COUNT(*) from Interrupcion interup WHERE " +
                "interup.codigoAnimal = ? ";
        
        
        Object values [] = {codigoAnimal};
        List interrupciones = hibernate.find(hql, values);
        
        if(interrupciones.size() > 0){
            cantidad = (Long)interrupciones.get(0);             
        }
        
        hql = "SELECT COUNT(*) from Reporte rep WHERE " +
        "rep.codigoAnimal = ? ";
        
        Object values2 [] = {codigoAnimal};
        interrupciones = hibernate.find(hql, values2);
        
        if(interrupciones.size() > 0){
            Long cantidadRep = (Long)interrupciones.get(0);
            cantidad = new Long(cantidad.longValue() + cantidadRep.longValue());                
        }       
        
        return cantidad;        
        
    }

    /**
     * @see cr.go.ice.interrupciones.DAO.AnimalDAO#getAnimalesOrdenNombre()
     */
    public List getAnimalesOrdenNombre() {
        HibernateTemplate hibernate = this.getHibernateTemplate();
        String hql = "from Animal ORDER BY nombreAnimal";
        List animales = hibernate.find(hql);
        return animales;        
    }

}
