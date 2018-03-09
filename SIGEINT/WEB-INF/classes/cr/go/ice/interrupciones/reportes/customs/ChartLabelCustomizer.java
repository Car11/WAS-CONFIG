package cr.go.ice.interrupciones.reportes.customs;

import org.jfree.chart.JFreeChart;
import java.awt.*; //needed for Font

import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;

import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRChartCustomizer;

/**
 * <p>Clase cr.go.ice.interrupciones.reportes.customs.ChartLabelCustomizer.java</p>
 * <p>Modulo (subsistema): InterrupcionesWeb</p>
 * <p>Descricion de <code>ChartLabelCustomizer.java</code> Da formato a las características de los gráficos.</p>
 * <p>Fecha creación: 08/09/2009</p>
 * <p>Ultima actualización: 08/09/2009</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class ChartLabelCustomizer implements JRChartCustomizer {

    /**
     * @see net.sf.jasperreports.engine.JRChartCustomizer#customize(org.jfree.chart.JFreeChart, net.sf.jasperreports.engine.JRChart)
     */
    public void customize(JFreeChart chart, JRChart jasperChart) {

        Plot plot = chart.getPlot();

        if(plot instanceof PiePlot){

            PiePlot piePlot = (PiePlot) plot;                                

            piePlot.setLabelFont(new Font("Verdana",Font.PLAIN,6));
            

        }

    }

}
