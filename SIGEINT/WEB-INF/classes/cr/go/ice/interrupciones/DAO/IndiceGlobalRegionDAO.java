package cr.go.ice.interrupciones.DAO;

import cr.go.ice.interrupciones.domain.IndiceGlobalRegion;

/**
 * <p>Interface cr.go.ice.interrupciones.dao.IndiceGlobalRegionDao.java</p>
 * <p>Modulo (subsistema): Proyecto</p>
 * <p>La interface <code>IndiceGlobalRegionDao.java</code> define los metodos de acceso a datos</p>
 * <p>Fecha creacion: 10/04/2010</p>
 * <p>Ultima actualizacion: 10/04/2010</p>
 * @author VVS Code Generator
 * @version 1.1
 */
public interface IndiceGlobalRegionDAO{


    public IndiceGlobalRegion buscar(Integer region,Integer anno, Integer mes, Integer voltaje);
}
