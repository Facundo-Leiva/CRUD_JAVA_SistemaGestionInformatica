package programa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class MySQLConnectionDAO 
{
    public static final String DRIVER = "org.sqlite.JDBC";
    
    public static final String DBURL = "jdbc:sqlite:programa/dao/data_source/bd_sistema.sqlite";

    public static Connection createConnection()
    {
        Connection cnx = null;

        try 
        {
            Class.forName(DRIVER);

            cnx = DriverManager.getConnection(DBURL);
        } 
        catch (ClassNotFoundException | SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error! " + ex);
        }

        return cnx;
    }
}