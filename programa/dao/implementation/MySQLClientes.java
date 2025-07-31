package programa.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.pdf.PdfPTable;
import programa.dao.ClientesDAO;
import programa.dao.MySQLConnectionDAO;
import programa.dao.data_transfer_objet.ClienteDTO;

public class MySQLClientes implements ClientesDAO
{
 //===============================================================================================================================

    @Override
    public ClienteDTO buscarCliente(int id_cl) 
    {
        ClienteDTO cliente = null;

        Connection cnx = MySQLConnectionDAO.createConnection();

        PreparedStatement prepStat = null;

        ResultSet rs = null;

        try 
        {
            prepStat = cnx.prepareStatement("Select * from Clientes where id_cliente = ?");
            prepStat.setInt(1, id_cl);

            rs = prepStat.executeQuery();

            while (rs.next())
            {
                String nombre = rs.getString("nombre_cliente");
                String email = rs.getString("email_cliente");
                String telefono = rs.getString("tel_cliente");
                String direccion = rs.getString("dir_cliente");
                String ultModif = rs.getString("ultima_modificacion");

                cliente = new ClienteDTO();
                cliente.setNombre_cliente(nombre);
                cliente.setEmail_clinte(email);
                cliente.setTel_cliente(telefono);
                cliente.setDir_cliente(direccion);
                cliente.setUltima_modificacion(ultModif);
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

        return cliente;
    }

 //===============================================================================================================================

    @Override
    public void ingresarCliente(int id_cl, String nombre, String email, String telefono, String direccion, String ult_mod) 
    {
        Connection cnx = MySQLConnectionDAO.createConnection();

        PreparedStatement prepStat = null;

        try 
        {
            prepStat = cnx.prepareStatement("Insert into Clientes values (?,?,?,?,?,?)");
            prepStat.setInt(1, id_cl);
            prepStat.setString(2, nombre);
            prepStat.setString(3, email);
            prepStat.setString(4, telefono);
            prepStat.setString(5, direccion);
            prepStat.setString(6, ult_mod);

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
    public void eliminarCliente(int id_cl) 
    {
        Connection cnx = MySQLConnectionDAO.createConnection();

        PreparedStatement prepStat = null;

        try
        {
            prepStat = cnx.prepareStatement("Delete from Clientes where id_cliente = ?");
            prepStat.setInt(1, id_cl);

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
    public void actualizarCliente(int id_cl, String nombre, String email,String telefono, String direccion,  String ult_mod) 
    {
        Connection cnx = MySQLConnectionDAO.createConnection();

        PreparedStatement prepStat = null;

        try
        {
            prepStat = cnx.prepareStatement("Update Clientes Set nombre_cliente = ?, email_cliente = ?, tel_cliente = ?, dir_cliente = ?, ultima_modificacion = ? where id_cliente = ?");
            prepStat.setString(1, nombre);
            prepStat.setString(2, email);
            prepStat.setString(3, telefono);
            prepStat.setString(4, direccion);
            prepStat.setString(5, ult_mod);
            prepStat.setInt(6, id_cl);

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
    public JTable tablaClientes() 
    {
        Connection cnx = MySQLConnectionDAO.createConnection();

        PreparedStatement prepStat = null;

        ResultSet rs = null;

        DefaultTableModel model = new DefaultTableModel();
        JTable tabla = null;

        try
        {
            prepStat = cnx.prepareStatement("Select id_cliente, nombre_cliente, email_cliente, tel_cliente, ultima_modificacion from Clientes");
            
            rs = prepStat.executeQuery();

            tabla = new JTable(model);

            model.addColumn("ID");
            model.addColumn("Nombre");
            model.addColumn("Email");
            model.addColumn("Telefono");
            model.addColumn("Modificado por");

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

    @Override
    public PdfPTable crearReporteClienteParticular(int id_cliente) 
    {
        Connection cnx = MySQLConnectionDAO.createConnection();

        PreparedStatement prepStat = null;

        ResultSet rs = null;

        PdfPTable tabla = new PdfPTable(5);
        tabla.addCell("ID");
        tabla.addCell("Nombre");
        tabla.addCell("Email");
        tabla.addCell("Telefono");
        tabla.addCell("Direccion");

        try 
        {
            prepStat = cnx.prepareStatement("select id_cliente, nombre_cliente, email_cliente, tel_cliente, dir_cliente from Clientes where id_cliente = ?");
            prepStat.setInt(1, id_cliente);

            rs = prepStat.executeQuery();

            if (rs.next())
            {
                do 
                {
                    tabla.addCell(rs.getString(1));
                    tabla.addCell(rs.getString(2));
                    tabla.addCell(rs.getString(3));
                    tabla.addCell(rs.getString(4));
                    tabla.addCell(rs.getString(5));
                } 
                while (rs.next());
            }
        } 
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error!: " + ex);
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

    @Override
    public PdfPTable crearReporteClientes() 
    {
        Connection cnx = MySQLConnectionDAO.createConnection();

        PreparedStatement prepStat = null;

        ResultSet rs = null;

        PdfPTable tabla = new PdfPTable(5);
        tabla.addCell("ID");
        tabla.addCell("Nombre");
        tabla.addCell("Email");
        tabla.addCell("Telefono");
        tabla.addCell("Direccion");
        
        try 
        {
            prepStat = cnx.prepareStatement("select id_cliente, nombre_cliente, email_cliente, tel_cliente, dir_cliente from Clientes");
            rs = prepStat.executeQuery();

            if (rs.next())
            {
                do 
                {
                    tabla.addCell(rs.getString(1));
                    tabla.addCell(rs.getString(2));
                    tabla.addCell(rs.getString(3));
                    tabla.addCell(rs.getString(4));
                    tabla.addCell(rs.getString(5));
                } 
                while (rs.next());
            }
        } 
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error!: " + ex);
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