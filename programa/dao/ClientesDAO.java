package programa.dao;

import programa.dao.data_transfer_objet.ClienteDTO;
import javax.swing.JTable;
import com.itextpdf.text.pdf.PdfPTable;

public interface ClientesDAO 
{
    ClienteDTO buscarCliente(int id_cl);

    JTable tablaClientes();

    PdfPTable crearReporteClienteParticular(int id_cl);

    PdfPTable crearReporteClientes();

    void ingresarCliente(int id_cl, String nombre, String email,  String telefono, String direccion, String ult_mod);

    void eliminarCliente(int id_cl);

    void actualizarCliente(int id_cl, String nombre, String email, String telefono, String direccion, String ult_mod);
}