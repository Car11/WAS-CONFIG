package cr.go.ice.interrupciones.BO.impl;

import com.vvs.bussiness.BussinessObject;

import cr.go.ice.interrupciones.BO.IndiceGlobalRegionBO;
import cr.go.ice.interrupciones.DAO.IndiceGlobalRegionDAO;
import cr.go.ice.interrupciones.domain.IndiceGlobalRegion;

/**Clase cr.go.ice.interrupciones.bo.impl.IndiceGlobalRegionBoImpl
* @author VVS Code Generator
* Creada el 10/04/2010 04:01:38
*/
public class IndiceGlobalRegionBOImpl extends BussinessObject implements IndiceGlobalRegionBO {

	private IndiceGlobalRegionDAO indiceGlobalRegionDAO;

    public IndiceGlobalRegion buscar(Integer region,Integer anno, Integer mes, Integer voltaje){
        return this.indiceGlobalRegionDAO.buscar(region, anno, mes, voltaje);
    }

    /**
     * Método accesor del atributo indiceGlobalRegionDAO.
     * @return Retorna el atributo indiceGlobalRegionDAO.
     */
    public IndiceGlobalRegionDAO getIndiceGlobalRegionDAO() {
        return this.indiceGlobalRegionDAO;
    }

    /**
     * Método modificador del atributo indiceGlobalRegionDAO.
     * @param indiceGlobalRegionDAO El dato para modificar el atributo indiceGlobalRegionDAO.
     */
    public void setIndiceGlobalRegionDAO(IndiceGlobalRegionDAO indiceGlobalRegionDAO) {
        this.indiceGlobalRegionDAO = indiceGlobalRegionDAO;
    }
}
