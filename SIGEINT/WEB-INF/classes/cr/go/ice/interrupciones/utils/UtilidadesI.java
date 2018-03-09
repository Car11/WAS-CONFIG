package cr.go.ice.interrupciones.utils;

import java.util.*;

import com.vvs.utilidades.Fechas;

import cr.go.ice.interrupciones.domain.*;

/**
 * <p>Clase cr.go.ice.interrupciones.utils.Utilidades.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>Utilidades.java</code>.</p>
 * <p>Fecha creación: 17/10/2007</p>
 * <p>Ultima actualización: 17/10/2007</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class UtilidadesI {

    
    /**
     * Metodo que recibe la fecha de un dia en particular en formato dd/MM/yyyy y las horas y minutos transcurridos
     * para ese día en formato de punto flotante
     * @param fecha de un día específico
     * @param horasMinutos horas y minutos transcurridos para ese día
     * @return la fecha del dia incluyendo la hora del día
     */
    public static Date getFechaCompleta(Date fecha, double horasMinutos){
        
        GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(fecha);
		Double minutos = null;
		Double hora = new Double(horasMinutos);
		gc.set(GregorianCalendar.HOUR_OF_DAY,hora.intValue());
		if(horasMinutos != 0){
			double min = horasMinutos - hora.intValue();
			minutos = new Double(min * 100);
		}
		else
			minutos = new Double(0);
		long minute = Math.round(minutos.doubleValue());
		minutos = new Double(minute);
		gc.set(GregorianCalendar.MINUTE,minutos.intValue());			
		return gc.getTime();                        
    }
    
    /**
     * Redondea un número a 2 decimales
     * @param num Numero que se desea redondear
     * @return Numero redondeado
     */
    public static double roundNum(double num){
        double valor = 0;    
        valor = num;
        
        valor = valor*100;
        valor = java.lang.Math.round(valor);
        valor = valor/100;
    
        return valor;

    }
    
    /**
     * Retorna el parametro en formato horas en formato de horas y minutos
     * @param horas formato horas
     * @return formato de horas y minutos
     */
    public static double getHorasMinutos(double horas){
        double resultado = 0;
        Double hora = new Double(horas);
        resultado = hora.intValue();
        Double minutos = new Double((horas - hora.intValue()) * 60);
        resultado = resultado + (minutos.doubleValue() / 100.0);
        return resultado;
    }
    
    /**
     * Determina si la fecha dada por parametro posee un mes anterior al actual
     * @param fechaInicioInterrup
     * @return true o false
     */
    public static boolean esMesAnterior(GregorianCalendar fechaInicioInterrup){
        int mesActual, mesInicioInterup, anoActual, anoInicioInterup = 0;
        GregorianCalendar hoy = new GregorianCalendar();
        mesActual = hoy.get(GregorianCalendar.MONTH);
        mesInicioInterup = fechaInicioInterrup.get(GregorianCalendar.MONTH);
        anoActual = hoy.get(GregorianCalendar.YEAR);
        anoInicioInterup = fechaInicioInterrup.get(GregorianCalendar.YEAR);
        if(anoActual == anoInicioInterup){
            if(mesActual == (mesInicioInterup + 1))
                return true;
            else
                return false;
        }
        else{
            if(anoActual == (anoInicioInterup + 1) && mesActual == 0 && mesInicioInterup == 11){
                return true;
            }
            else
                return false;
        }
    }

    /**
     * Determina el tiempo transcurrido en minutos luego del dia 5 del mes siguiente con respecto a la fecha
     * de inicio de una interrupción
     * @param fechaInicioInterrup
     * @return tiempo transcurrido
     */
    public static long getTiempoTranscurridoDespuesDia5MesSiguiente(GregorianCalendar fechaInicioInterrup){
        long duracion = 0;
        long minutosInicio = 0;
        long minutosFinal = 0;
        GregorianCalendar fecha = new GregorianCalendar();
        fecha.setTime(fechaInicioInterrup.getTime());
        int mesInicioInterup = fecha.get(GregorianCalendar.MONTH);
        if (mesInicioInterup == 11) {
            mesInicioInterup = 0;
            fecha.set(GregorianCalendar.YEAR, fecha.get(GregorianCalendar.YEAR) + 1);
        } else {
            mesInicioInterup = mesInicioInterup + 1;
        }
        fecha.set(GregorianCalendar.MONTH, mesInicioInterup);
        fecha.set(GregorianCalendar.DAY_OF_MONTH, 6);
        fecha.set(GregorianCalendar.HOUR_OF_DAY, 0);
        fecha.set(GregorianCalendar.MINUTE, 0);
        GregorianCalendar hoy = new GregorianCalendar();
        minutosInicio = Fechas.millisToMinutes(fecha.getTimeInMillis());
        minutosFinal = Fechas.millisToMinutes(hoy.getTimeInMillis());
        duracion = minutosFinal - minutosInicio;
        return duracion;
    }
    
    /**
     * Metodo que convierte una lista de Integers en una lista de sus correspondientes valores separados por una coma, ej: (1,2,3)
     * @param items lista de Integers
     * @return lista de codigos en formato string
     */    
    public static String getListaStr(List items){
        StringBuffer resultado = new StringBuffer("");
        for(int i = 0; i < items.size(); i++){
           Integer item = (Integer)items.get(i);
           resultado.append(item + ",");
        }
        resultado = new StringBuffer(resultado.substring(0,resultado.length() - 1));
        return resultado.toString();
    }
    
    /**
     * Metodo que convierte una lista de objetos Region en una lista de sus correspondientes codigos separados por una coma, ej: (1,2,3)
     * @param items lista de objetos
     * @return lista de codigos en formato string
     */
    public static String getListaItemsRegion(List items){
        StringBuffer resultado = new StringBuffer("");
        for(int i = 0; i < items.size(); i++){
           Region item = (Region)items.get(i);
           resultado.append(item.getRegion() + ",");
        }
        resultado = new StringBuffer(resultado.substring(0,resultado.length() - 1));
        return resultado.toString();
    }    
    
    /**
     * Metodo que convierte una lista de objetos Subregion en una lista de sus correspondientes codigos separados por una coma, ej: (1,2,3)
     * @param items lista de objetos
     * @return lista de codigos en formato string
     */    
    public static String getListaItemsSubregion(List items){
        StringBuffer resultado = new StringBuffer("");
        for(int i = 0; i < items.size(); i++){
           SubRegion item = (SubRegion)items.get(i);
           resultado.append(item.getSubRegionID().getSubRegion() + ",");
        }
        resultado = new StringBuffer(resultado.substring(0,resultado.length() - 1));
        return resultado.toString();
    }
    
    /**
     * Metodo que convierte una lista de objetos Subestacion en una lista de sus correspondientes codigos separados por una coma, ej: (1,2,3)
     * @param items lista de objetos
     * @return lista de codigos en formato string
     */    
    public static String getListaItemsSubestacion(List items){
        StringBuffer resultado = new StringBuffer("");
        for(int i = 0; i < items.size(); i++){
           SubEstacion item = (SubEstacion)items.get(i);
           resultado.append(item.getCodigoSubEstacion() + ",");
        }
        resultado = new StringBuffer(resultado.substring(0,resultado.length() - 1));
        return resultado.toString();
    }
    
    /**
     * Metodo que convierte una lista de objetos Circuito en una lista de sus correspondientes codigos separados por una coma, ej: (1,2,3)
     * @param items lista de objetos
     * @return lista de codigos en formato string
     */    
    public static String getListaItemsCircuito(List items){
        StringBuffer resultado = new StringBuffer("");
        for(int i = 0; i < items.size(); i++){
            Circuito item = (Circuito)items.get(i);            
            resultado.append(item.getCircuitoID().getCircuito() + ",");
        }
        resultado = new StringBuffer(resultado.substring(0,resultado.length() - 1));
        return resultado.toString();
    }   
    
    /**
     * Metodo que convierte una lista de objetos Proteccion en una lista de sus correspondientes codigos separados por una coma, ej: (1,2,3)
     * @param items lista de objetos
     * @return lista de codigos en formato string
     */    
    public static String getListaItemsProteccion(List items){
        StringBuffer resultado = new StringBuffer("");
        for(int i = 0; i < items.size(); i++){
        	Proteccion item = (Proteccion)items.get(i);
        	resultado.append(item.getCodigoProteccion() + ",");
        }
        resultado = new StringBuffer(resultado.substring(0,resultado.length() - 1));
        return resultado.toString();
    }      
    
    /**
     * Metodo que convierte una lista de objetos Circuito en una lista de sus correspondientes codigos de subestacion separados por una coma, ej: (1,2,3)
     * @param items lista de objetos
     * @return lista de codigos
     */    
    public static String getListaItemsSubestacionFromListaCircuitos(List items){
        StringBuffer resultado = new StringBuffer("");
        for(int i = 0; i < items.size(); i++){
            Circuito item = (Circuito)items.get(i);
            resultado.append(item.getCircuitoID().getSubEstacion().getCodigoSubEstacion() + ",");
           
        }
        resultado = new StringBuffer(resultado.substring(0,resultado.length() - 1));
        return resultado.toString();
    }    
    
    /**
     * Metodo que convierte una lista de objetos Seccion en una lista de sus correspondientes codigos de subestacion separados por una coma, ej: (1,2,3)
     * @param items lista de objetos
     * @return lista de codigos
     */    
    public static String getListaItemsSubestacionFromListaSecciones(List items){
        StringBuffer resultado = new StringBuffer("");
        for(int i = 0; i < items.size(); i++){
            Seccion item = (Seccion)items.get(i);
            resultado.append(item.getSeccionID().getSubEstacion() + ",");
           
        }
        resultado = new StringBuffer(resultado.substring(0,resultado.length() - 1));
        return resultado.toString();
    } 
    
    /**
     * Metodo que convierte una lista de objetos Circuito en una lista de sus correspondientes codigos de circuito separados por una coma, ej: (1,2,3)
     * @param items lista de objetos
     * @return lista de codigos
     */    
    public static String getListaItemsCircuitoFromListaSecciones(List items){
        StringBuffer resultado = new StringBuffer("");
        for(int i = 0; i < items.size(); i++){
            Seccion item = (Seccion)items.get(i);
            resultado.append(item.getSeccionID().getCircuito() + ",");
           
        }
        resultado = new StringBuffer(resultado.substring(0,resultado.length() - 1));
        return resultado.toString();
    }      
    
    /**
     * Metodo que convierte una lista de objetos Seccion en una lista de sus correspondientes codigos separados por una coma, ej: (1,2,3)
     * @param items lista de objetos
     * @return lista de codigos en formato string
     */    
    public static String getListaItemsSeccion(List items){
        StringBuffer resultado = new StringBuffer("");
        for(int i = 0; i < items.size(); i++){
            Seccion item = (Seccion)items.get(i);
           resultado.append(item.getSeccionID().getSeccion() + ",");
        }
        resultado = new StringBuffer(resultado.substring(0,resultado.length() - 1));
        return resultado.toString();
    }    
    
    /**
     * Metodo que convierte una lista de objetos en una lista de sus correspondientes codigos separados por una coma, ej: (1,2,3)
     * @param items lista de objetos
     * @return lista de codigos en formato string
     */    
    public static String getListaItemsSubestacionDeSeccion(List items){
        StringBuffer resultado = new StringBuffer("");
        for(int i = 0; i < items.size(); i++){
            Seccion item = (Seccion)items.get(i);
           resultado.append(item.getSeccionID().getSubEstacion() + ",");
        }
        resultado = new StringBuffer(resultado.substring(0,resultado.length() - 1));
        return resultado.toString();
    }  
    
    /**
     * Metodo que convierte una lista de objetos en una lista de sus correspondientes codigos separados por una coma, ej: (1,2,3)
     * @param items lista de objetos
     * @return lista de codigos en formato string
     */    
    public static String getListaItemsCircuitoDeSeccion(List items){
        StringBuffer resultado = new StringBuffer("");
        for(int i = 0; i < items.size(); i++){
            Seccion item = (Seccion)items.get(i);
           resultado.append(item.getSeccionID().getCircuito() + ",");
        }
        resultado = new StringBuffer(resultado.substring(0,resultado.length() - 1));
        return resultado.toString();
    }      
    
}
