package programa.dao;

import programa.dao.data_transfer_objet.UsuariosDTO;
import javax.swing.JTable;

public interface UsuariosDAO 
{
    UsuariosDTO buscarUsuario(int id_us);

    UsuariosDTO loginUsuario(String nombre, String password);

    void cambiarPassword(int id_us, String password);

    JTable tablaUsuarios();

    void ingresarUsuario(int id_us, String nombre, String email, String telefono, String username, String password, String tipo_nivel, String estatus, String regisPor);

    void eliminarUsuario(int id_us);

    void actualizarUsuario(int id_us, String nombre, String email, String telefono, String username, String password, String tipo_nivel, String estatus, String regisPor);
}