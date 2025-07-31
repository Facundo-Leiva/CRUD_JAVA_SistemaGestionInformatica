package programa.dao;

import programa.dao.data_transfer_objet.EquiposDTO;
import javax.swing.JTable;
import com.itextpdf.text.pdf.PdfPTable;

public interface EquiposDAO 
{
    EquiposDTO buscarEquipo(int id_eq);

    JTable tablaEquiposIDCliente(int id_cliente);

    JTable tablaEquiposEstatusGeneral();

    JTable tablaEquiposEstatus(String estatus);

    PdfPTable crearReporte(int id_cl);

    void ingresarEquipo(int id_eq, int id_cli, String tipo, String marca, String modelo, String numSerie, String dia, String mes, String annio, String obser, String estatus, String ultMod, String comenTec, String revTec);

    void eliminarEquipo(int id_eq);

    void actualizarEquipo(int id_eq, int id_cli, String tipo, String marca, String modelo, String numSerie, String dia, String mes, String annio, String obser, String estatus,String ultMod, String comenTec, String revTec);
}