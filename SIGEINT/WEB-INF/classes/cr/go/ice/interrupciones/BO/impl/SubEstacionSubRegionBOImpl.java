package cr.go.ice.interrupciones.BO.impl;

import cr.go.ice.interrupciones.BO.SubEstacionSubRegionBO;
import cr.go.ice.interrupciones.DAO.SubEstacionSubRegionDAO;
import cr.go.ice.interrupciones.domain.*;

/**
 * <p>Clase cr.go.ice.interrupciones.BO.impl.SubEstacionSubRegionBOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>SubEstacionSubRegionBOImpl.java</code>Define los Metodos de Logica y Negocio de las Clases para SubEstacionSubRegion.</p>
 * <p>Fecha creación: 08/04/2008</p>
 * <p>Ultima actualización: 08/04/2008</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class SubEstacionSubRegionBOImpl implements SubEstacionSubRegionBO{

    private SubEstacionSubRegionDAO subEstacionSubRegionDAO;
    
    
    
    /**
     * Metodo modificador de subEstacionSubRegionDAO.
     * @param subEstacionSubRegionDAO El subEstacionSubRegionDAO a modificar.
     */
    public void setSubEstacionSubRegionDAO(SubEstacionSubRegionDAO subEstacionSubRegionDAO) {
        this.subEstacionSubRegionDAO = subEstacionSubRegionDAO;
    }
    
    /**
     * @see cr.go.ice.interrupciones.BO.SubEstacionSubRegionBO#agregar(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    public void agregar(Integer oficina, Integer region, Integer subregion, Integer subestacion) {
        SubEstacionSubRegion subEstSubReg = new SubEstacionSubRegion();
        SubEstacionSubRegionID subEstSubRegID = new SubEstacionSubRegionID();
        Oficina oficinaObj = new Oficina();
        oficinaObj.setCodigoOficina(oficina);
        subEstSubRegID.setOficina(oficinaObj);
        SubRegion subReg = new SubRegion();
        SubRegionID subRegID = new SubRegionID();
        subRegID.setSubRegion(subregion);
        Region reg = new Region();
        reg.setRegion(region);
        subRegID.setRegion(reg);        
        subReg.setSubRegionID(subRegID);
        subEstSubRegID.setSubRegion(subReg);
        subEstSubRegID.setSubEstacion(subestacion);
        subEstSubReg.setSubEstacionSubRegionID(subEstSubRegID);
        
        this.subEstacionSubRegionDAO.agregar(subEstSubReg);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.SubEstacionSubRegionBO#eliminar(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    public void eliminar(Integer oficina, Integer region, Integer subregion, Integer subestacion) {
        SubEstacionSubRegion subEstSubReg = new SubEstacionSubRegion();
        SubEstacionSubRegionID subEstSubRegID = new SubEstacionSubRegionID();
        Oficina oficinaObj = new Oficina();
        oficinaObj.setCodigoOficina(oficina);
        subEstSubRegID.setOficina(oficinaObj);
        SubRegion subReg = new SubRegion();
        SubRegionID subRegID = new SubRegionID();
        subRegID.setSubRegion(subregion);
        Region reg = new Region();
        reg.setRegion(region);
        subRegID.setRegion(reg);        
        subReg.setSubRegionID(subRegID);
        subEstSubRegID.setSubRegion(subReg);
        subEstSubRegID.setSubEstacion(subestacion);
        subEstSubReg.setSubEstacionSubRegionID(subEstSubRegID);
        
        this.subEstacionSubRegionDAO.eliminar(subEstSubReg);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.SubEstacionSubRegionBO#existe(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    public boolean existe(Integer oficina, Integer region, Integer subregion, Integer subestacion) {
        SubEstacionSubRegion subEstSubReg = new SubEstacionSubRegion();
        SubEstacionSubRegionID subEstSubRegID = new SubEstacionSubRegionID();
        Oficina oficinaObj = new Oficina();
        oficinaObj.setCodigoOficina(oficina);
        subEstSubRegID.setOficina(oficinaObj);
        SubRegion subReg = new SubRegion();
        SubRegionID subRegID = new SubRegionID();
        subRegID.setSubRegion(subregion);
        Region reg = new Region();
        reg.setRegion(region);
        subRegID.setRegion(reg);        
        subReg.setSubRegionID(subRegID);
        subEstSubRegID.setSubRegion(subReg);
        subEstSubRegID.setSubEstacion(subestacion);
        subEstSubReg.setSubEstacionSubRegionID(subEstSubRegID);
        
        return this.subEstacionSubRegionDAO.existe(subEstSubReg);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.SubEstacionSubRegionBO#buscarOficina(java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    public Integer buscarOficina(Integer region, Integer subregion, Integer subestacion) {
        return this.subEstacionSubRegionDAO.buscarOficina(region, subregion, subestacion);
    }
    
}
