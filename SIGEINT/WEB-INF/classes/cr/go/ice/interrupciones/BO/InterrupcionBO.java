
package cr.go.ice.interrupciones.BO;

import java.util.Date;
import java.util.List;

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
 * <p>Interface cr.go.ice.interrupciones.BO.InterrupcionBO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>InterrupcionBO.java</code>Define los Metodos de Logica y Negocio de las Clases para Interrupcion.</p>
 * <p>Fecha creación: 26/01/2007</p>
 * <p>Ultima actualización: 26/01/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public interface InterrupcionBO {

    /**
     * Agrega una interrupcion
     * @param interrupcion a agregar
     */
    public void agregar(Interrupcion interrupcion);
    /**
     * Agrega una nueva interrupcion con todos los datos que esta requiere.
     * @param interrupcion Interrupcion a agregar.
     * @param irCuadrillaVehiculo Indicador de ir a cuadrilla vehiculo.
     * @param empleadosCuadrilla Lista de cuadrillas a agregar.
     * @param codigoProteccion Codigo de proteccion seleccionado.
     * @param fusibles Lista de fusibles a agregar.
     * @param codigoMaterial Codigo del material seleccionado.
     * @param trafos Lista de trafos a agregar.
     * @param postes Lista de postes a agregar.
     * @param equipos Lista de equipos a agregar.
     * @param gemelas Indica si las interrupciones son gemelas.
     * @param interrupcionesGemelas Lista de interrupciones gemelas a agregar.
     * @param vehiculo Vehiculo a agregar.
     * @param saleTotalOParcial Indica si la interrupcion es total o parcial.
     * @param seccionamientos Lista de seccionamientos a agregar.
     */
    public void guardarInterrupcion(Interrupcion interrupcion,
		boolean irCuadrillaVehiculo, List<Cuadrilla> empleadosCuadrilla,
		Integer codigoProteccion, List<Fusible> fusibles,
		Integer codigoMaterial, List<Trafo> trafos,
		List<PosteInstaladoRetirado> postes,
		List<EquipoEspecial> equipos,
		boolean gemelas, List<InterrupcionGemela> interrupcionesGemelas,
		Vehiculo vehiculo,
		boolean saleTotalOParcial, List<NoPropiaSeccionamiento> seccionamientos);
    /**
     * Modifica una interrupcion
     * @param interrupcion a modificar
     */
    public void modificar(Interrupcion interrupcion);
    /**
     * Modifica una interrupcion y todos los datos asociados a ella.
     * @param interrupcion Interrupcion a modificar.
     * @param codigoMaterial Codigo de material seleccionado.
     * @param irCuadrillaVehiculo Indicador de ir a cuadrilla vehiculo.
     * @param empleadosCuadrilla Lista de cuadrillas a modificar.
     * @param cuadrillaID Id de la cuadrilla.
     * @param fusibles Lista de fusibles a modificar.
     * @param fusibleID Id del fusible.
     * @param listaFusibles Lista de fusibles a eliminar.
     * @param trafos Lista de trafos a modificar.
     * @param trafoID Id del trafo.
     * @param postes Lista de postes a modificar.
     * @param posteInstaladoRetiradoID Id del poste.
     * @param equipos Lista de equipos a modificar.
     * @param equipoEspecialID Id del equipo.
     * @param gemelas Indica si las interrupciones son gemelas.
     * @param interrupcionesGemelas Lista de interrupciones gemelas a modificar.
     * @param interrupcionGemelaID Id de las interrupciones gemelas.
     * @param vehiculo Vehiculo a modificar.
     * @param vehiculoID Id del vehiculo.
     * @param vehiculoIDViejo Id del vehiculo anterior.
     * @param saleTotalOParcial Indica si la interrupcion es total o parcial.
     * @param seccionamientos Lista de seccionamientos a modificar.
     * @param seccionamientoID Id de los seccionamientos.
     */
    public void modificarInterrupcion(Interrupcion interrupcion, Integer codigoMaterial, boolean irCuadrillaVehiculo,
		List<Cuadrilla> empleadosCuadrilla, CuadrillaID cuadrillaID,
		List<Fusible> fusibles, FusibleID fusibleID, List<Fusible> listaFusibles,
		List<Trafo> trafos, TrafoID trafoID,
		List<PosteInstaladoRetirado> postes, PosteInstaladoRetiradoID posteInstaladoRetiradoID,
		List<EquipoEspecial> equipos, EquipoEspecialID equipoEspecialID,
		boolean gemelas, List<InterrupcionGemela> interrupcionesGemelas, InterrupcionGemelaID interrupcionGemelaID,
		Vehiculo vehiculo, VehiculoID vehiculoID, VehiculoID vehiculoIDViejo,
		boolean saleTotalOParcial, List<NoPropiaSeccionamiento> seccionamientos, NoPropiaSeccionamientoID seccionamientoID);
    /**
     * Elimina una interrupcion
     * @param interrupcion a eliminar
     */
    public void eliminar(Interrupcion interrupcion);
    
    /**
     * Retorna una interrupción específica
     * @param interrupcionID indica cual es la interrupción deseada
     * @return Interrupcion que se requiere
     */
    public Interrupcion getInterrupcion(InterrupcionID interrupcionID);    
    
    /**
     * Retorna la lista de interrupciones almacenadas en la base de datos
     * @return Lista de Interrupciones
     */
    public List getInterrupciones();        
    
    /**
     * Determina si una interrupción existe
     * @param interrupcionID indica la interrupción a determinar su existencia
     * @return La existencia o no de la interrupción
     */
    public boolean interrupcionExiste(InterrupcionID interrupcionID);
	
	/**
	 * Método que ejecuta el procedimiento almacenado de cálculo de índices propios
	 * @param ano Año a partir del cual se desea realizar el cálculo de índices
	 * @param mes Mes a partir del cual se desea realizar el cálculo de índices
	 * @return S o N de acuerdo a la correcta ejecución o no del procedimiento almacenado
	 */
    public String ejecutarIndicesPropios(Integer ano, Integer mes);   
    
    /**
     * Retorna las interrupciones que sucedieron dentro un determinado período de tiempo.  Tales interrupciones filtradas por
     * oficina, tipo de interrupción, bitácora y circuito
     * @param codigoOficina Oficina por la cual se filtrarán las interrupciones
     * @param fechaInicio Fecha de inicio a partir de la cual se obtendrán las interrupciones
     * @param fechaFinal Fecha de fin hasta la cual se obtendrán las interrupciones
     * @param voltajes Lista de tipos de voltajes, en formato String, para los cuales se filtrarán las interrupciones
     * @param causa1 Indica si se debe filtrar por la causa 417 o no
     * @param bitacora Indica si debe filtrar por bitácora o no, o si más bien es indiferente
     * @param codigoCircuito Indica el circuito para el cual se obtendrán las interrupciones
     * @param subEstacion Indica la subestacion para el cual se obtendrán las interrupciones
     * @return Cantidad de interrupciones deseadas
     */
    public Long getInterrupcionesPorCircuito(Integer codigoOficina, Date fechaInicio, Date fechaFinal, String voltajes, Boolean causa1, Integer bitacora, Integer codigoCircuito, Integer subEstacion);
    
    /**
     * Retorna las interrupciones que sucedieron dentro un determinado período de tiempo.  Tales interrupciones filtradas por
     * oficina o por agencia
     * @param codigoOficina Oficina por la cual se filtrarán las interrupciones
     * @param codigoAgencia Agencia por la cual se filtrarán las interrupciones
     * @param fechaInicio Fecha de inicio a partir de la cual se obtendrán las interrupciones
     * @param fechaFinal Fecha de fin hasta la cual se obtendrán las interrupciones
     * @return Cantidad de interrupciones deseadas
     */
    public Long getInterrupcionesPorPeriodo(Integer codigoOficina, Integer codigoAgencia, Date fechaInicio, Date fechaFinal);
    
    /**
     * Retorna un lista con los años en que han ocurrido interrupciones
     * @return Lista de años
     */
    public List getAnosDeInterrupciones();
        
    
    /**
     * Determina los tipos de voltaje para el reporte de interrupciones por causa
     * @param tipoInterrupcion
     * @return tipos de voltaje
     */
    public List getTiposVoltajeInterrupcionCausa(Integer tipoInterrupcion);
    
    /**
     * Determina si se deben utilizar informacion historica de acuerdo a la fecha dada
     * @param fechaFinal indica la fecha a ser evaluada
     * @return true o false
     */
    public boolean utilizarDatosHistoricos(Date fechaFinal);
    
    public boolean existeInterrupciones(Integer reg, Integer subR, Integer sub, Integer cir, Long sec);
}
