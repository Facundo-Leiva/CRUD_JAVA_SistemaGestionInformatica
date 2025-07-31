package programa.dao.implementation;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import programa.dao.MySQLConnectionDAO;
import programa.dao.UsuariosDAO;
import programa.dao.data_transfer_objet.UsuariosDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLUsuarios implements UsuariosDAO
{
 //===============================================================================================================================

    @Override
    public UsuariosDTO buscarUsuario(int id_us) 
    {
        UsuariosDTO usuario = null;

        Connection cnx = MySQLConnectionDAO.createConnection();

        PreparedStatement prepStat = null;

        ResultSet rs = null;

        try 
        {
            prepStat = cnx.prepareStatement("Select * from Usuarios where id_usuario = ?");
            prepStat.setInt(1, id_us);

            rs = prepStat.executeQuery();

            while (rs.next())
            {
                String nombre = rs.getString("nombre_usuario");
                String email = rs.getString("email_usuario");
                String telefono = rs.getString("tel_usuario");
                String user = rs.getString("username");
                String pass = rs.getString("password");
                String tipoNivel = rs.getString("tipo_nivel");
                String estatus = rs.getString("estatus");
                String regisPor = rs.getString("registrado_por");

                usuario = new UsuariosDTO();
                usuario.setNombre_usuario(nombre);
                usuario.setEmail_usuario(email);
                usuario.setTel_usuario(telefono);
                usuario.setUsername(user);
                usuario.setPassword(pass);
                usuario.setTipo_nivel(tipoNivel);
                usuario.setEstatus(estatus);
                usuario.setRegistrado_por(regisPor);
            }
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "Error! " + e);
        }
        finally 
        {
            try 
            {
                if (rs != null) 
                {
                    rs.close();
                }
                if (prepStat != null) 
                {
                    prepStat.close();
                }
                if (cnx != null) 
                {
                    cnx.close();
                }
            } 
            catch (Exception e) 
            {
                JOptionPane.showMessageDialog(null, "Error! " + e);
            }
        }

        return usuario;
    }

 //===============================================================================================================================

    @Override
    public void ingresarUsuario(int id_us, String nombre, String email, String telefono, String username, String password,
            String tipo_nivel, String estatus, String regisPor) 
    {
        Connection cnx = MySQLConnectionDAO.createConnection();

        PreparedStatement prepStat = null;

        try 
        {
            prepStat = cnx.prepareStatement("Insert into Usuarios values (?,?,?,?,?,?,?,?,?)");
            prepStat.setInt(1, id_us);
            prepStat.setString(2, nombre);
            prepStat.setString(3, email);
            prepStat.setString(4, telefono);
            prepStat.setString(5, username);
            prepStat.setString(6, password);
            prepStat.setString(7, tipo_nivel);
            prepStat.setString(8, estatus);
            prepStat.setString(9, regisPor);

            prepStat.executeUpdate();
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "Error! " + e);
        }
        finally 
        {
            try 
            {
                if (prepStat != null) 
                {
                    prepStat.close();
                }
                if (cnx != null) 
                {
                    cnx.close();
                }
            } 
            catch (Exception e) 
            {
                JOptionPane.showMessageDialog(null, "Error! " + e);
            }
        }
    }

 //===============================================================================================================================

    @Override
    public void eliminarUsuario(int id_us) 
    {
        Connection cnx = MySQLConnectionDAO.createConnection();

        PreparedStatement prepStat = null;

        try
        {
            prepStat = cnx.prepareStatement("Delete from Usuarios where id_usuario = ?");
            prepStat.setInt(1, id_us);

            prepStat.executeUpdate();
        }
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "Error! " + e);
        } 
        finally 
        {
            try 
            {
                if (prepStat != null) 
                {
                    prepStat.close();    
                }
                if (cnx != null) 
                {
                    cnx.close();
                }
            } 
            catch (SQLException e) 
            {
                JOptionPane.showMessageDialog(null, "Error! " + e);
            }
        }  
    }

 //===============================================================================================================================

    @Override
    public void actualizarUsuario(int id_us, String nombre, String email, String telefono, String username, String password,
            String tipo_nivel, String estatus, String regisPor) 
    {
        Connection cnx = MySQLConnectionDAO.createConnection();

        PreparedStatement prepStat = null;

        try
        {
            prepStat = cnx.prepareStatement("Update Usuarios Set nombre_usuario = ?, email_usuario = ?, tel_usuario = ?, username = ?, password = ?, tipo_nivel = ?, estatus = ?, registrado_por = ? where id_usuario = ?");
            prepStat.setString(1, nombre);
            prepStat.setString(2, email);
            prepStat.setString(3, telefono);
            prepStat.setString(4, username);
            prepStat.setString(5, password);
            prepStat.setString(6, tipo_nivel);
            prepStat.setString(7, estatus);
            prepStat.setString(8, regisPor);
            prepStat.setInt(9, id_us);

            prepStat.executeUpdate();
        }
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "Error! " + e);
        } 
        finally 
        {
            try 
            {
                if (prepStat != null) 
                {
                    prepStat.close();    
                }
                if (cnx != null) 
                {
                    cnx.close();
                }
            } 
            catch (SQLException e) 
            {
                JOptionPane.showMessageDialog(null, "Error! " + e);
            }
        }
    }

 //===============================================================================================================================

    @Override
    public UsuariosDTO loginUsuario(String nombre, String password) 
    {
        UsuariosDTO usuario = null;

        Connection cnx = MySQLConnectionDAO.createConnection();

        PreparedStatement prepStat = null;

        ResultSet rs = null;

        try 
        {
            prepStat = cnx.prepareStatement("Select nombre_usuario, tipo_nivel, estatus from Usuarios where username = ? and password = ?");
            prepStat.setString(1, nombre);
            prepStat.setString(2, password);

            rs = prepStat.executeQuery();

            while (rs.next())
            {
                String nombreUser = rs.getString("nombre_usuario");
                String tipoNivel = rs.getString("tipo_nivel");
                String estatus = rs.getString("estatus");

                usuario = new UsuariosDTO();
                usuario.setNombre_usuario(nombreUser);
                usuario.setTipo_nivel(tipoNivel);
                usuario.setEstatus(estatus);
            }
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "Error! " + e);
        }
        finally 
        {
            try 
            {
                if (rs != null) 
                {
                    rs.close();
                }
                if (prepStat != null) 
                {
                    prepStat.close();
                }
                if (cnx != null) 
                {
                    cnx.close();
                }
            } 
            catch (Exception e) 
            {
                JOptionPane.showMessageDialog(null, "Error! " + e);
            }
        }
        
        return usuario;
    }

 //===============================================================================================================================

    @Override
    public void cambiarPassword(int id_us, String password) 
    {
        Connection cnx = MySQLConnectionDAO.createConnection();

        PreparedStatement prepStat = null;

        try
        {
            prepStat = cnx.prepareStatement("Update Usuarios Set password = ? where id_usuario = ?");
            prepStat.setString(1, password);
            prepStat.setInt(2, id_us);

            prepStat.executeUpdate();
        }
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "Error! " + e);
        } 
        finally 
        {
            try 
            {
                if (prepStat != null) 
                {
                    prepStat.close();    
                }
                if (cnx != null) 
                {
                    cnx.close();
                }
            } 
            catch (SQLException e) 
            {
                JOptionPane.showMessageDialog(null, "Error! " + e);
            }
        }
    }

 //===============================================================================================================================

    @Override
    public JTable tablaUsuarios() 
    {
        Connection cnx = MySQLConnectionDAO.createConnection();

        PreparedStatement prepStat = null;
        
        ResultSet rs = null;

        DefaultTableModel model = new DefaultTableModel();
        JTable tabla = null;

        try
        {
            prepStat = cnx.prepareStatement("Select id_usuario, nombre_usuario, username, tipo_nivel, estatus from Usuarios");
            
            rs = prepStat.executeQuery();

            tabla = new JTable(model);

            model.addColumn("ID");
            model.addColumn("Nombre");
            model.addColumn("Username");
            model.addColumn("Permisos");
            model.addColumn("Estatus");

            while (rs.next())
            {
                Object[] fila = new Object[5];

                for (int i = 0; i < 5; i++) 
                {
                    fila[i] = rs.getObject(i + 1);
                }
                
                model.addRow(fila);
            }
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "Error! " + e);
        } 
        finally 
        {
            try 
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (prepStat != null) 
                {
                    prepStat.close();    
                }
                if (cnx != null) 
                {
                    cnx.close();
                }
            } 
            catch (SQLException e) 
            {
                JOptionPane.showMessageDialog(null, "Error! " + e);
            }
        }
        
        return tabla;
    }
   
 //===============================================================================================================================
}