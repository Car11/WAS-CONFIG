package cr.go.ice.interrupciones.BO.impl;

import java.util.List;

import cr.go.ice.interrupciones.BO.MaterialBO;
import cr.go.ice.interrupciones.DAO.MaterialDAO;
import cr.go.ice.interrupciones.domain.Material;

/**
 * <p>Clase cr.go.ice.interrupciones.BO.impl.MaterialBOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>MaterialBOImpl.java</code>Define los Metodos de Logica y Negocio de las Clases para Material.</p>
 * <p>Fecha creación: 22/01/2007</p>
 * <p>Ultima actualización: 22/01/2007</p>
 * @author Vista Verde Soft (Administrador y Doc Cristian)
 * @version 1.1
 */
public class MaterialBOImpl implements MaterialBO {
    /**
     * <code>materialDAO</code> Material Dao
     */
    private MaterialDAO materialDAO = null;
    
    /**
     * Constructor
     */
    public MaterialBOImpl(){
    }
    
   
    /**
     * Asigna MaterialDAO
     * @param materialDAO
     */
    public void setMaterialDAO(MaterialDAO materialDAO) {
        this.materialDAO = materialDAO;
    }

    /**
     * @see cr.go.ice.interrupciones.BO.MaterialBO#agregar(cr.go.ice.interrupciones.domain.Material)
     */
    public void agregar(Material material) {
        this.materialDAO.agregar(material);
    }
    
    /**
     * @see cr.go.ice.interrupciones.BO.MaterialBO#modificar(cr.go.ice.interrupciones.domain.Material)
     */
    public void modificar(Material material) {
        this.materialDAO.modificar(material);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.MaterialBO#eliminar(cr.go.ice.interrupciones.domain.Material)
     */
    public void eliminar(Material material) {
        this.materialDAO.eliminar(material);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.MaterialBO#buscar(java.lang.Integer)
     */
    public Material buscar(Integer codigo) {
        return this.materialDAO.buscar(codigo);
    }
    
    public Material buscar(Integer codigo, Integer ubicacionGeneral, Integer ubicacionEspecifica) {
        return this.materialDAO.buscar(codigo,ubicacionGeneral, ubicacionEspecifica);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.MaterialBO#buscar(java.lang.String)
     */
    public Material buscar(String descripcion) {
        return this.materialDAO.buscar(descripcion);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.MaterialBO#getMateriales()
     */
    public List getMateriales() {
        return this.materialDAO.getMateriales();
    }

    /**
     * @see cr.go.ice.interrupciones.BO.MaterialBO#materialExiste(cr.go.ice.interrupciones.domain.Material)
     */
    public boolean materialExiste(Material material) {
        return this.materialDAO.materialExiste(material);
    }
    
    public boolean existe(Material material)
    {
    	return this.materialDAO.existe(material);
    }


    /**
     * @see cr.go.ice.interrupciones.BO.MaterialBO#registrosAsociados(java.lang.Integer)
     */
    public Long registrosAsociados(Integer codigoMaterial) {
        return this.materialDAO.registrosAsociados(codigoMaterial);
    }
}