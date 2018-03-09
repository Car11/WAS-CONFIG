
package cr.go.ice.interrupciones.BO.impl;

import java.util.List;

import cr.go.ice.interrupciones.BO.ProvolmaBO;
import cr.go.ice.interrupciones.DAO.ProvolmaDAO;
import cr.go.ice.interrupciones.domain.Provolma;
import cr.go.ice.interrupciones.domain.ProvolmaID;

/**
 * <p>Clase cr.go.ice.interrupciones.BO.impl.ProvolmaBOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ProvolmaBOImpl.java</code>Define los Metodos de Logica y Negocio de las Clases para Provolma.</p>
 * <p>Fecha creación: 23/02/2007</p>
 * <p>Ultima actualización: 23/02/2007</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class ProvolmaBOImpl implements ProvolmaBO {
	/**
	 * <code>provolmaDAO</code> Provolma Dao
	 */
	private ProvolmaDAO provolmaDAO;
	
	
	/**
	 * Asigna ProvolmaDAO
	 * @param provolmaDAO
	 */
	public void setProvolmaDAO(ProvolmaDAO provolmaDAO) {
		this.provolmaDAO = provolmaDAO;
	}
    
	/**
	 * @see cr.go.ice.interrupciones.BO.ProvolmaBO#agregar(cr.go.ice.interrupciones.domain.Provolma)
	 */
	public void agregar(Provolma provolma) {
		this.provolmaDAO.agregar(provolma);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.ProvolmaBO#modificar(cr.go.ice.interrupciones.domain.Provolma)
	 */
	public void modificar(Provolma provolma) {
		this.provolmaDAO.modificar(provolma);

	}

	/**
	 * @see cr.go.ice.interrupciones.BO.ProvolmaBO#eliminar(cr.go.ice.interrupciones.domain.Provolma)
	 */
	public void eliminar(Provolma provolma) {
		this.provolmaDAO.eliminar(provolma);

	}

	/**
	 * @see cr.go.ice.interrupciones.BO.ProvolmaBO#existe(cr.go.ice.interrupciones.domain.Provolma)
	 */
	public boolean existe(Provolma provolma) {
		return this.provolmaDAO.existe(provolma);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.ProvolmaBO#getProvolmas()
	 */
	public List getProvolmas() {
		return this.provolmaDAO.getProvolmas();
	}

    /**
     * @see cr.go.ice.interrupciones.BO.ProvolmaBO#buscar(cr.go.ice.interrupciones.domain.ProvolmaID)
     */
    public Provolma buscar(ProvolmaID provolmaID) {
        return this.provolmaDAO.buscar(provolmaID);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.ProvolmaBO#registrosAsociados(cr.go.ice.interrupciones.domain.ProvolmaID)
     */
    public Long registrosAsociados(ProvolmaID provolmaID) {
        return this.provolmaDAO.registrosAsociados(provolmaID);
    }

}
