
package cr.go.ice.interrupciones.BO.impl;

import java.util.*;

import cr.go.ice.interrupciones.BO.InterrupcionBO;
import cr.go.ice.interrupciones.DAO.CuadrillaDAO;
import cr.go.ice.interrupciones.DAO.EquipoEspecialDAO;
import cr.go.ice.interrupciones.DAO.FusibleDAO;
import cr.go.ice.interrupciones.DAO.InterrupcionDAO;
import cr.go.ice.interrupciones.DAO.InterrupcionGemelaDAO;
import cr.go.ice.interrupciones.DAO.NoPropiaSeccionamientoDAO;
import cr.go.ice.interrupciones.DAO.PosteInstaladoRetiradoDAO;
import cr.go.ice.interrupciones.DAO.TrafoDAO;
import cr.go.ice.interrupciones.DAO.VehiculoDAO;
import cr.go.ice.interrupciones.domain.Cuadrilla;
import cr.go.ice.interrupciones.domain.CuadrillaID;
import cr.go.ice.interrupciones.domain.EquipoEspecial;
import cr.go.ice.interrupciones.domain.EquipoEspecialID;
import cr.go.ice.interrupciones.domain.Fusible;
import cr.go.ice.interrupciones.domain.FusibleID;
import cr.go.ice.interrupciones.domain.Interrupcion;
import cr.go.ice.interrupciones.domain.InterrupcionGemela;
import cr.go.ice.interrupciones.domain.InterrupcionGemelaID;
import cr.go.ice.interrupciones.domain.InterrupcionID;
import cr.go.ice.interrupciones.domain.NoPropiaSeccionamiento;
import cr.go.ice.interrupciones.domain.NoPropiaSeccionamientoID;
import cr.go.ice.interrupciones.domain.PosteInstaladoRetirado;
import cr.go.ice.interrupciones.domain.PosteInstaladoRetiradoID;
import cr.go.ice.interrupciones.domain.Trafo;
import cr.go.ice.interrupciones.domain.TrafoID;
import cr.go.ice.interrupciones.domain.Vehiculo;
import cr.go.ice.interrupciones.domain.VehiculoID;

/**
 * <p>Clase cr.go.ice.interrupciones.BO.impl.InterrupcionBOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>InterrupcionBOImpl.java</code>Define los Metodos de Logica y Negocio de las Clases para Interrupcion.</p>
 * <p>Fecha creación: 20/02/2007</p>
 * <p>Ultima actualización: 20/02/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public class InterrupcionBOImpl implements InterrupcionBO{
	/**
	 * Acceso a los datos de la clase Interrupcion. 
	 */
	private InterrupcionDAO interrupcionDAO;
	/**
	 * Acceso a los datos de la clase Cuadrilla. 
	 */
	private CuadrillaDAO cuadrillaDAO;
	/**
	 * Acceso a los datos de la clase Fusible. 
	 */
	private FusibleDAO fusibleDAO;
	/**
	 * Acceso a los datos de la clase Trafo. 
	 */
	private TrafoDAO trafoDAO;
	/**
	 * Acceso a los datos de la clase PosteInstaladoRetirado. 
	 */
	private PosteInstaladoRetiradoDAO posteInstaladoRetiradoDAO;
	/**
	 * Acceso a los datos de la clase EquipoEspecial. 
	 */
	private EquipoEspecialDAO equipoEspecialDAO;
	/**
	 * Acceso a los datos de la clase InterrupcionGemela. 
	 */
	private InterrupcionGemelaDAO interrupcionGemelaDAO;
	/**
	 * Acceso a los datos de la clase Vehiculo. 
	 */
	private VehiculoDAO vehiculoDAO;
	/**
	 * Acceso a los datos de la clase NoPropiaSeccionamiento. 
	 */
	private NoPropiaSeccionamientoDAO noPropiaSeccionamientoDAO;
    /**
     * Constructor
     */
    public InterrupcionBOImpl(){
    }

	/**
	 * Asigna InterrupcionDAO
	 * @param interrupcionDAO El interrupcionDAO a establecer.
	 */
	public void setInterrupcionDAO(InterrupcionDAO interrupcionDAO) {
		this.interrupcionDAO = interrupcionDAO;
	}
    
	/**
	 * @see cr.go.ice.interrupciones.BO.InterrupcionBO#agregar(cr.go.ice.interrupciones.domain.Interrupcion)
	 */
	public void agregar(Interrupcion interrupcion) {
		this.interrupcionDAO.agregar(interrupcion);
		
	}
	@Override
	public void guardarInterrupcion(Interrupcion interrupcion,
			boolean irCuadrillaVehiculo, List<Cuadrilla> empleadosCuadrilla,
			Integer codigoProteccion, List<Fusible> fusibles,
			Integer codigoMaterial, List<Trafo> trafos,
			List<PosteInstaladoRetirado> postes,
			List<EquipoEspecial> equipos,
			boolean gemelas, List<InterrupcionGemela> interrupcionesGemelas,
			Vehiculo vehiculo,
			boolean saleTotalOParcial, List<NoPropiaSeccionamiento> seccionamientos) {
		try {
			interrupcionDAO.agregar(interrupcion);

			if (empleadosCuadrilla != null && empleadosCuadrilla.size() > 0 && irCuadrillaVehiculo) {
				cuadrillaDAO.agregar(empleadosCuadrilla);
			}
			if ((fusibles != null && fusibles.size() > 0) && (codigoProteccion != null
					&& (codigoProteccion.intValue() == 103  || codigoProteccion.intValue() == 104))) {

				fusibleDAO.agregar(fusibles);

				int indice = 0;
				int largo = fusibles.size();
				while(indice < largo) {
					System.out.println();
					System.out.println("Lista de fusibles: " + fusibles.get(indice));
					System.out.println();
					indice++;
				}
			}
			if ((trafos != null && trafos.size() > 0) && ((codigoMaterial != null) && (codigoMaterial.intValue() == 232))) {
				trafoDAO.agregar(trafos);
			}
			if (codigoMaterial != null && codigoMaterial.intValue() == 201) {
				posteInstaladoRetiradoDAO.agregar(postes);
			}
			if (codigoMaterial != null && (codigoMaterial.intValue() == 230 || codigoMaterial.intValue() == 234)) {
				equipoEspecialDAO.agregar(equipos);
			}
			if (interrupcionesGemelas != null && interrupcionesGemelas.size() > 0 && gemelas) {
				interrupcionGemelaDAO.agregar(interrupcionesGemelas);
			}
			if (vehiculo.getVehiculoID().getNumeroVehiculo() != null
					&& vehiculo.getVehiculoID().getNumeroVehiculo().longValue() > 0 && irCuadrillaVehiculo) {
				vehiculoDAO.agregar(vehiculo);
			}
		    if (saleTotalOParcial && (seccionamientos != null && seccionamientos.size() > 0)) {
				noPropiaSeccionamientoDAO.agregar(seccionamientos);
		    }
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.InterrupcionBO#modificar(cr.go.ice.interrupciones.domain.Interrupcion)
	 */
	public void modificar(Interrupcion interrupcion) {
		this.interrupcionDAO.modificar(interrupcion);
		
	}
	@Override
	public void modificarInterrupcion(Interrupcion interrupcion, Integer codigoMaterial, boolean irCuadrillaVehiculo,
			List<Cuadrilla> empleadosCuadrilla, CuadrillaID cuadrillaID,
			List<Fusible> fusibles, FusibleID fusibleID, List<Fusible> listaFusibles,
			List<Trafo> trafos, TrafoID trafoID,
			List<PosteInstaladoRetirado> postes, PosteInstaladoRetiradoID posteInstaladoRetiradoID,
			List<EquipoEspecial> equipos, EquipoEspecialID equipoEspecialID,
			boolean gemelas, List<InterrupcionGemela> interrupcionesGemelas, InterrupcionGemelaID interrupcionGemelaID,
			Vehiculo vehiculo, VehiculoID vehiculoID, VehiculoID vehiculoIDViejo,
			boolean saleTotalOParcial, List<NoPropiaSeccionamiento> seccionamientos, NoPropiaSeccionamientoID seccionamientoID) {
		try {
			interrupcionDAO.modificar(interrupcion);
			if (irCuadrillaVehiculo) {
				if ((empleadosCuadrilla != null && empleadosCuadrilla.size() > 0) || cuadrillaID != null) {
					cuadrillaDAO.modificar(empleadosCuadrilla, cuadrillaID);
				}
			} else {
			    if (cuadrillaID != null) {
			        cuadrillaDAO.modificar(new ArrayList<Cuadrilla>(), cuadrillaID);
			    }
			}
			if (fusibles != null && fusibles.size() > 0) {
				fusibleID = new FusibleID();
				fusibleID.setAa(interrupcion.getInterrupcionID().getAa());
				fusibleID.setCodigoOficina(interrupcion.getInterrupcionID().getCodigoOficina());
				fusibleID.setNumeroInterrupcion(interrupcion.getInterrupcionID().getNumeroInterrupcion());
				fusibleDAO.modificar(fusibles, fusibleID);
			}
			if ((trafos != null && trafos.size() > 0) || trafoID != null) {
				trafoDAO.modificar(trafos, trafoID);
			}
			if ((postes != null && postes.size() > 0) || posteInstaladoRetiradoID != null) {
				if (codigoMaterial != null && codigoMaterial.intValue() == 201) {
					posteInstaladoRetiradoDAO.modificar(postes, posteInstaladoRetiradoID);
				} else {
					posteInstaladoRetiradoDAO.modificar(new ArrayList<PosteInstaladoRetirado>(), posteInstaladoRetiradoID);
				}
			}
			if ((equipos != null && equipos.size() > 0) || equipoEspecialID != null) {
				if ((codigoMaterial != null) && (codigoMaterial.intValue() == 230 || codigoMaterial.intValue() == 234)) {
					equipoEspecialDAO.modificar(equipos, equipoEspecialID);
				} else {
					equipoEspecialDAO.modificar(new ArrayList<EquipoEspecial>(), equipoEspecialID);
				}
			}
			if (gemelas) {
				if ((interrupcionesGemelas != null && interrupcionesGemelas.size() > 0) || interrupcionGemelaID != null) {
					System.out.println("Entro a gemelas");
					interrupcionGemelaDAO.modificar(interrupcionesGemelas, interrupcionGemelaID);
				}
			} else {
			    if (interrupcionGemelaID != null) {
			        interrupcionGemelaDAO.modificar(new ArrayList<InterrupcionGemela>(), interrupcionGemelaID);
			    }
			}
			if (irCuadrillaVehiculo) {
				if (vehiculo.getVehiculoID().getNumeroVehiculo() != null && vehiculo.getVehiculoID().getNumeroVehiculo().longValue() > 0) {
				    Vehiculo vehiculoViejo = new Vehiculo();
				    vehiculoViejo.setVehiculoID(vehiculoIDViejo);
				    vehiculoViejo.setHoraFin(new Date());
				    vehiculoViejo.setHoraInicio(new Date());
				    vehiculoViejo.setHoraLlegada(new Date());
				    vehiculoViejo.setHoraRestablece(new Date());
				    vehiculoViejo.setKilometrosFinal(new Double(0));
				    vehiculoViejo.setKilometrosInicio(new Double(0));
				    vehiculoViejo.setKilometrosLlegada(new Double(0));
				    vehiculoViejo.setKilometrosTotales(new Double(0));
				    vehiculoViejo.setNumeroVehiculo(new Long(0));
				    vehiculoViejo.setTiempoAviso(new Date());
				    vehiculoDAO.eliminar(vehiculoViejo);
				    vehiculoDAO.agregar(vehiculo);
				}
			} else if (vehiculoID != null) {
				vehiculo.setVehiculoID(vehiculoID);
				vehiculoDAO.eliminar(vehiculo);
			}

			if (!listaFusibles.isEmpty()) {
				int indice = 0;
				int largo = listaFusibles.size();

				while(indice < largo) {
					fusibleDAO.eliminar((Fusible) listaFusibles.get(indice));
					indice++;
				}
				listaFusibles.clear();
			}

			if (saleTotalOParcial) {
				if (seccionamientos != null && seccionamientos.size() > 0) {
					noPropiaSeccionamientoDAO.modificar(seccionamientos, seccionamientoID);
				} else {
					noPropiaSeccionamientoDAO.modificar(new ArrayList<NoPropiaSeccionamiento>(), seccionamientoID);
				}
			} else {
				noPropiaSeccionamientoDAO.modificar(new ArrayList<NoPropiaSeccionamiento>(), seccionamientoID);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	/**
	 * @see cr.go.ice.interrupciones.BO.InterrupcionBO#eliminar(cr.go.ice.interrupciones.domain.Interrupcion)
	 */
	public void eliminar(Interrupcion interrupcion) {
		this.interrupcionDAO.eliminar(interrupcion);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.InterrupcionBO#getInterrupcion(cr.go.ice.interrupciones.domain.InterrupcionID)
	 */
	public Interrupcion getInterrupcion(InterrupcionID interrupcionID) {
		return this.interrupcionDAO.getInterrupcion(interrupcionID);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.InterrupcionBO#getInterrupciones()
	 */
	public List getInterrupciones() {
		return this.interrupcionDAO.getInterrupciones();
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.InterrupcionBO#interrupcionExiste(cr.go.ice.interrupciones.domain.InterrupcionID)
	 */
	public boolean interrupcionExiste(InterrupcionID interrupcionID) {
		return this.interrupcionDAO.interrupcionExiste(interrupcionID);
	}
    
    /**
     * @see cr.go.ice.interrupciones.BO.InterrupcionBO#ejecutarIndicesPropios(java.lang.Integer, java.lang.Integer)
     */
    public String ejecutarIndicesPropios(Integer ano, Integer mes) {
        return this.interrupcionDAO.ejecutarIndicesPropios(ano,mes);
    }
    
    /**
     * @see cr.go.ice.interrupciones.BO.InterrupcionBO#getInterrupcionesPorCircuito(java.lang.Integer, java.util.Date, java.util.Date, java.lang.String, java.lang.Boolean, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    public Long getInterrupcionesPorCircuito(Integer codigoOficina, Date fechaInicio, Date fechaFinal, String voltajes, Boolean causa1, Integer bitacora, Integer codigoCircuito, Integer subEstacion) {                
        Long interrupciones = this.interrupcionDAO.getInterrupcionesPorCircuito(codigoOficina, fechaInicio, fechaFinal, voltajes, causa1, bitacora, codigoCircuito, subEstacion);
        return interrupciones;
    }
    
    /**
     * @see cr.go.ice.interrupciones.BO.InterrupcionBO#getInterrupcionesPorPeriodo(java.lang.Integer, java.lang.Integer, java.util.Date, java.util.Date)
     */
    public Long getInterrupcionesPorPeriodo(Integer codigoOficina, Integer codigoAgencia, Date fechaInicio, Date fechaFinal) {
        Long interrupciones = this.interrupcionDAO.getInterrupcionesPorPeriodo(codigoOficina, codigoAgencia, fechaInicio, fechaFinal);
		return interrupciones;
    }
    
    /**
     * @see cr.go.ice.interrupciones.BO.InterrupcionBO#getAnosDeInterrupciones()
     */
    public List getAnosDeInterrupciones() {
        return this.interrupcionDAO.getAnosDeInterrupciones();
    } 
    
    /**
     * @see cr.go.ice.interrupciones.BO.InterrupcionBO#getTiposVoltajeInterrupcionCausa(java.lang.Integer)
     */
    public List getTiposVoltajeInterrupcionCausa(Integer tipoInterrupcion){
        List tiposVoltaje = new ArrayList();
        
        if(tipoInterrupcion.intValue() == Interrupcion.INTERRUPCION_PRIMARIA.intValue()){
            tiposVoltaje.add(new Integer(2));
        }
        else if(tipoInterrupcion.intValue() == Interrupcion.INTERRUPCION_SECUNDARIA.intValue()){
                tiposVoltaje.add(new Integer(3));
             }
             else if(tipoInterrupcion.intValue() == Interrupcion.INTERRUPCION_SUMINISTRO.intValue()){
                     tiposVoltaje.add(new Integer(1));
                 }
                 else if(tipoInterrupcion.intValue() == Interrupcion.INTERRUPCION_CATASTROFE.intValue()){
                         tiposVoltaje = new ArrayList();
                      }  
                      else if(tipoInterrupcion.intValue() == Interrupcion.INTERRUPCION_DISTRIBUCION.intValue()){
                              tiposVoltaje.add(new Integer(2));
                              tiposVoltaje.add(new Integer(3));
                           }
                           else{
                               tiposVoltaje.add(new Integer(1));
                               tiposVoltaje.add(new Integer(2));
                               tiposVoltaje.add(new Integer(3));
                           }
        return tiposVoltaje;
    }
    
    /**
     * @see cr.go.ice.interrupciones.BO.InterrupcionBO#utilizarDatosHistoricos(java.util.Date)
     */
    public boolean utilizarDatosHistoricos(Date fechaFinal){                 
        GregorianCalendar hoy = new GregorianCalendar();                        
        boolean utilizar = false;
        int anoActual = hoy.get(GregorianCalendar.YEAR);
        int mesActual = hoy.get(GregorianCalendar.MONTH) + 1;
        int diaMesActual = hoy.get(GregorianCalendar.DAY_OF_MONTH);   
        
        GregorianCalendar fechaParametro = new GregorianCalendar();
        fechaParametro.setTime(fechaFinal);
        int anoFechaFinal = fechaParametro.get(GregorianCalendar.YEAR);
        int mesFechaFinal = fechaParametro.get(GregorianCalendar.MONTH) + 1;        
        
        if(anoActual == anoFechaFinal){                     
            if(diaMesActual >= 6 && mesActual == mesFechaFinal){
                utilizar = false;
            }
            else{                           
                if((diaMesActual < 6 && mesActual == mesFechaFinal)){
                    utilizar = false;
                }
                else{
                    mesActual = (mesActual == 1) ? 13 : mesActual;
                    if(diaMesActual < 6 && mesFechaFinal == mesActual - 1){
                        utilizar = false;   
                    }
                    else{
                        utilizar = true;   
                    }
                }
            }
        }
        else{
            anoActual = (anoActual > anoFechaFinal) ? anoActual - 1 : anoActual;
            mesActual = (mesActual == 1) ? 12 : mesActual;
            if(mesFechaFinal == 12){
                if(anoActual == anoFechaFinal && diaMesActual <= 5 && mesActual == mesFechaFinal)
                    utilizar = false;
                else
                    utilizar = true;  
            }
            else{           
                utilizar = true;         
            }
        }
        return utilizar;
    }
        
    public boolean existeInterrupciones(Integer reg, Integer subR, Integer sub, Integer cir, Long sec){
        return this.interrupcionDAO.existeInterrupciones(reg, subR, sub, cir, sec);
    }

	/**
	 * Retorna el valor del atributo cuadrillaDAO.
	 * @return El valor del atributo cuadrillaDAO.
	 */
	public CuadrillaDAO getCuadrillaDAO() {
		return cuadrillaDAO;
	}
	/**
	 * Establece el valor del atributo cuadrillaDAO.
	 * @param cuadrillaDAO Valor del atributo cuadrillaDAO a establecer.
	 */
	public void setCuadrillaDAO(CuadrillaDAO cuadrillaDAO) {
		this.cuadrillaDAO = cuadrillaDAO;
	}
	/**
	 * Retorna el valor del atributo fusibleDAO.
	 * @return El valor del atributo fusibleDAO.
	 */
	public FusibleDAO getFusibleDAO() {
		return fusibleDAO;
	}
	/**
	 * Establece el valor del atributo fusibleDAO.
	 * @param fusibleDAO Valor del atributo fusibleDAO a establecer.
	 */
	public void setFusibleDAO(FusibleDAO fusibleDAO) {
		this.fusibleDAO = fusibleDAO;
	}
	/**
	 * Retorna el valor del atributo trafoDAO.
	 * @return El valor del atributo trafoDAO.
	 */
	public TrafoDAO getTrafoDAO() {
		return trafoDAO;
	}
	/**
	 * Establece el valor del atributo trafoDAO.
	 * @param trafoDAO Valor del atributo trafoDAO a establecer.
	 */
	public void setTrafoDAO(TrafoDAO trafoDAO) {
		this.trafoDAO = trafoDAO;
	}
	/**
	 * Retorna el valor del atributo posteInstaladoRetiradoDAO.
	 * @return El valor del atributo posteInstaladoRetiradoDAO.
	 */
	public PosteInstaladoRetiradoDAO getPosteInstaladoRetiradoDAO() {
		return posteInstaladoRetiradoDAO;
	}
	/**
	 * Establece el valor del atributo posteInstaladoRetiradoDAO.
	 * @param posteInstaladoRetiradoDAO Valor del atributo posteInstaladoRetiradoDAO a establecer.
	 */
	public void setPosteInstaladoRetiradoDAO(PosteInstaladoRetiradoDAO posteInstaladoRetiradoDAO) {
		this.posteInstaladoRetiradoDAO = posteInstaladoRetiradoDAO;
	}
	/**
	 * Retorna el valor del atributo equipoEspecialDAO.
	 * @return El valor del atributo equipoEspecialDAO.
	 */
	public EquipoEspecialDAO getEquipoEspecialDAO() {
		return equipoEspecialDAO;
	}
	/**
	 * Establece el valor del atributo equipoEspecialDAO.
	 * @param equipoEspecialDAO Valor del atributo equipoEspecialDAO a establecer.
	 */
	public void setEquipoEspecialDAO(EquipoEspecialDAO equipoEspecialDAO) {
		this.equipoEspecialDAO = equipoEspecialDAO;
	}
	/**
	 * Retorna el valor del atributo interrupcionGemelaDAO.
	 * @return El valor del atributo interrupcionGemelaDAO.
	 */
	public InterrupcionGemelaDAO getInterrupcionGemelaDAO() {
		return interrupcionGemelaDAO;
	}
	/**
	 * Establece el valor del atributo interrupcionGemelaDAO.
	 * @param interrupcionGemelaDAO Valor del atributo interrupcionGemelaDAO a establecer.
	 */
	public void setInterrupcionGemelaDAO(InterrupcionGemelaDAO interrupcionGemelaDAO) {
		this.interrupcionGemelaDAO = interrupcionGemelaDAO;
	}
	/**
	 * Retorna el valor del atributo vehiculoDAO.
	 * @return El valor del atributo vehiculoDAO.
	 */
	public VehiculoDAO getVehiculoDAO() {
		return vehiculoDAO;
	}
	/**
	 * Establece el valor del atributo vehiculoDAO.
	 * @param vehiculoDAO Valor del atributo vehiculoDAO a establecer.
	 */
	public void setVehiculoDAO(VehiculoDAO vehiculoDAO) {
		this.vehiculoDAO = vehiculoDAO;
	}
	/**
	 * Retorna el valor del atributo noPropiaSeccionamientoDAO.
	 * @return El valor del atributo noPropiaSeccionamientoDAO.
	 */
	public NoPropiaSeccionamientoDAO getNoPropiaSeccionamientoDAO() {
		return noPropiaSeccionamientoDAO;
	}
	/**
	 * Establece el valor del atributo noPropiaSeccionamientoDAO.
	 * @param noPropiaSeccionamientoDAO Valor del atributo noPropiaSeccionamientoDAO a establecer.
	 */
	public void setNoPropiaSeccionamientoDAO(NoPropiaSeccionamientoDAO noPropiaSeccionamientoDAO) {
		this.noPropiaSeccionamientoDAO = noPropiaSeccionamientoDAO;
	}
}
