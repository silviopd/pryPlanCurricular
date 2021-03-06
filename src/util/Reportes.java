package util;

import datos.Conexion;
import java.awt.Frame;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.type.OrientationEnum;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

public class Reportes extends Conexion {

    public static final String RUTA_REPORTES = System.getProperties().getProperty("user.dir") + "/src/reportes/";

    public JRViewer reporteInterno(String archivoReporte, Map<String, Object> parametros) throws Exception {
        try {
            //URL rutaR = new URL(getClass().getResource("/reportes/"+archivoReporte).toString());
            //JasperPrint reporte = JasperFillManager.fillReport(rutaR.getPath(), parametros, this.abrirConexion());
            JasperPrint reporte = JasperFillManager.fillReport(Reportes.RUTA_REPORTES + archivoReporte, parametros, this.abrirConexion());
            JRViewer visor = new JRViewer(reporte);
            return visor;
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }

        return null;

    }

    public JRViewer reporteInternoHorizontal(String archivoReporte, Map<String, Object> parametros) throws Exception {
        try {
            //URL rutaR = new URL(getClass().getResource("/reportes/"+archivoReporte).toString());
            //JasperPrint reporte = JasperFillManager.fillReport(rutaR.getPath(), parametros, this.abrirConexion());
            JasperPrint reporte = JasperFillManager.fillReport(Reportes.RUTA_REPORTES + archivoReporte, parametros, this.abrirConexion());
            JRViewer visor = new JRViewer(reporte);

            //OrientationEnum.LANDSCAPE = Horizontal
            reporte.setOrientation(OrientationEnum.LANDSCAPE);
            return visor;

        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return null;

    }

    public void reporteProduccion(String ruta, java.sql.Date fechaInicio, java.sql.Date fechaFinal, int Tipo) {
        try {
            Map parametros = new HashMap();
            parametros.put("fecha_inicio", fechaInicio);
            parametros.put("fecha_final", fechaFinal);
            parametros.put("tipo", Tipo);

            JasperPrint reporte = JasperFillManager.fillReport(Reportes.RUTA_REPORTES + ruta, parametros, this.abrirConexion());
            JasperViewer ventana = new JasperViewer(reporte,false);
            ventana.setExtendedState(Frame.MAXIMIZED_BOTH);
            ventana.setTitle("Reporte");
            ventana.setVisible(true);
        } catch (Exception e) {
            Funciones.mensajeError(e.getMessage(), "ERROR");
        }

    }
}
