package programa.dao.implementation;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.pdf.PdfPTable;
import programa.dao.EquiposDAO;
import programa.dao.MySQLConnectionDAO;
import programa.dao.data_transfer_objet.EquiposDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLEquipos implements EquiposDAO
{
 //===============================================================================================================================

    @Override
    public EquiposDTO buscarEquipo(int id_eq) 
    {
        EquiposDTO equipo = null;

        Connection cnx = MySQLConnectionDAO.createConnection();

        PreparedStatement prepStat = null;

        ResultSet rs = null;

        try 
        {
            prepStat = cnx.prepareStatement("Select * from Equipos where id_equipo = ?");
            prepStat.setInt(1, id_eq);

            rs = prepStat.executeQuery();

            while (rs.next())
            {
                int id_cliente= rs.getInt("id_cliente");
                String tipo = rs.getString("tipo_equipo");
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                String num_serie = rs.getString("num_serie");
                String dia = rs.getString("dia_ingreso");
                String mes = rs.getString("mes_ingreso");
                String annio = rs.getString("annio_ingreso");
                String obser = rs.getString("observaciones");
                String estatus = rs.getString("estatus");
                String ult_mod = rs.getString("ultima_modificacion");
                String comen_tec = rs.getString("coment_tecnico");
                String revision = rs.getString("revision_tecnica_de");

                equipo = new EquiposDTO();
                equipo.setId_cliente(id_cliente);
                equipo.setTipo_equipo(tipo);
                equipo.setMarca(marca);
                equipo.setModelo(modelo);
                equipo.setNum_serie(num_serie);
                equipo.setDia_ingreso(dia);
                equipo.setMes_ingreso(mes);
                equipo.setAnnio_ingreso(annio);
                equipo.setObservaciones(obser);
                equipo.setEstatus(estatus);
                equipo.setUltima_modificacion(ult_mod);
                equipo.setComent_tecnico(comen_tec);
                equipo.setRev_tec_de(revision);
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

        return equipo;
    }

 //===============================================================================================================================

    @Override
    public void ingresarEquipo(int id_eq, int id_cli, String tipo, String marca, String modelo, String numSerie, String dia, String mes,
            String annio, String obser, String estatus, String ultMod, String comenTec, String revTec) 
    {
        Connection cnx = MySQLConnectionDAO.createConnection();

        PreparedStatement prepStat = null;

        try 
        {
            prepStat = cnx.prepareStatement("Insert into Equipos values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            prepStat.setInt(1, id_eq);
            prepStat.setInt(2, id_cli);
            prepStat.setString(3, tipo);
            prepStat.setString(4, marca);
            prepStat.setString(5, modelo);
            prepStat.setString(6, numSerie);
            prepStat.setString(7, dia);
            prepStat.setString(8, mes);
            prepStat.setString(9, annio);
            prepStat.setString(10, obser);
            prepStat.setString(11, estatus);
            prepStat.setString(12, ultMod);
            prepStat.setString(13, comenTec);
            prepStat.setString(14, revTec);

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
    public void eliminarEquipo(int id_eq) 
    {
        Connection cnx = MySQLConnectionDAO.createConnection();

        PreparedStatement prepStat = null;

        try
        {
            prepStat = cnx.prepareStatement("Delete from Equipos where id_equipo = ?");
            prepStat.setInt(1, id_eq);

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
    public void actualizarEquipo(int id_eq, int id_cli, String tipo, String marca, String modelo, String numSerie, String dia, String mes,
            String annio, String obser, String estatus, String ultMod, String comenTec, String revTec) 
    {
        Connection cnx = MySQLConnectionDAO.createConnection();

        PreparedStatement prepStat = null;

        try
        {
            prepStat = cnx.prepareStatement("Update Equipos Set id_cliente = ?, tipo_equipo = ?, marca = ?, modelo = ?, num_serie = ?, dia_ingreso = ?, mes_ingreso = ?, annio_ingreso = ?, observaciones = ?, estatus = ?, ultima_modificacion = ?, coment_tecnico = ?, revision_tecnica_de = ? where id_equipo = ?");
            prepStat.setInt(1, id_cli);
            prepStat.setString(2, tipo);
            prepStat.setString(3, marca);
            prepStat.setString(4, modelo);
            prepStat.setString(5, numSerie);
            prepStat.setString(6, dia);
            prepStat.setString(7, mes);
            prepStat.setString(8, annio);
            prepStat.setString(9, obser);
            prepStat.setString(10, estatus);
            prepStat.setString(11, ultMod);
            prepStat.setString(12, comenTec);
            prepStat.setString(13, revTec);
            prepStat.setInt(14, id_eq);

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
    public JTable tablaEquiposIDCliente(int id_cliente) 
    {
        Connection cnx = MySQLConnectionDAO.createConnection();

        PreparedStatement prepStat = null;

        ResultSet rs = null;

        DefaultTableModel model = new DefaultTableModel();
        JTable tabla = null;

        try
        {
            prepStat = cnx.prepareStatement("Select id_equipo, tipo_equipo, marca, estatus from Equipos where id_cliente = ?");
            prepStat.setInt(1, id_cliente);
            
            rs = prepStat.executeQuery();

            tabla = new JTable(model);

            model.addColumn("ID");
            model.addColumn("Tipo de Equipo");
            model.addColumn("Marca");
            model.addColumn("Estatus");

            while (rs.next())
            {
                Object[] fila = new Object[4];

                for (int i = 0; i < 4; i++) 
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
    public JTable tablaEquiposEstatusGeneral() 
    {
        Connection cnx = MySQLConnectionDAO.createConnection();

        PreparedStatement prepStat = null;

        ResultSet rs = null;

        DefaultTableModel model = new DefaultTableModel();
        JTable tabla = null;

        try
        {
            prepStat = cnx.prepareStatement("Select id_equipo, tipo_equipo, marca, estatus from Equipos");
            
            rs = prepStat.executeQuery();

            tabla = new JTable(model);

            model.addColumn("ID");
            model.addColumn("Tipo de Equipo");
            model.addColumn("Marca");
            model.addColumn("Estatus");

            while (rs.next())
            {
                Object[] fila = new Object[4];

                for (int i = 0; i < 4; i++) 
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
    public JTable tablaEquiposEstatus(String estatus) 
    {
        Connection cnx = MySQLConnectionDAO.createConnection();

        PreparedStatement prepStat = null;
        
        ResultSet rs = null;

        DefaultTableModel model = new DefaultTableModel();
        JTable tabla = null;

        try
        {
            prepStat = cnx.prepareStatement("Select id_equipo, tipo_equipo, marca, estatus from Equipos where estatus = ?");
            prepStat.setString(1, estatus);
            
            rs = prepStat.executeQuery();

            tabla = new JTable(model);

            model.addColumn("ID");
            model.addColumn("Tipo de Equipo");
            model.addColumn("Marca");
            model.addColumn("Estatus");

            while (rs.next())
            {
                Object[] fila = new Object[4];

                for (int i = 0; i < 4; i++) 
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
    public PdfPTable crearReporte(int id_cl) 
    {
        Connection cnx = MySQLConnectionDAO.createConnection();

        PreparedStatement prepStat = null;

        ResultSet rs = null;

        PdfPTable tabla = new PdfPTable(4);
        tabla.addCell("ID Equipo");
        tabla.addCell("Tipo");
        tabla.addCell("Marca");
        tabla.addCell("Estatus");

        try 
        {
            prepStat = cnx.prepareStatement("select id_equipo, tipo_equipo, marca, estatus from Equipos where id_cliente = ?");
            prepStat.setInt(1, id_cl);
            rs = prepStat.executeQuery();

            if (rs.next())
            {
                do 
                {
                    tabla.addCell(rs.getString(1));
                    tabla.addCell(rs.getString(2));
                    tabla.addCell(rs.getString(3));
                    tabla.addCell(rs.getString(4));
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