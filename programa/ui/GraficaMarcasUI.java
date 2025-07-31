package programa.ui;

import javax.swing.*;
import programa.dao.MySQLConnectionDAO;
import programa.dao.data_transfer_objet.UsuariosDTO;
import programa.dao.implementation.MySQLUsuarios;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GraficaMarcasUI extends JFrame
{
    private int appl, del, lenov, h_p, asu, ace, samsu, ms;

    public GraficaMarcasUI()
    {
        caractFrame();
        setTitle("Tecnico - Sesion de " + nombreUser());

        Image im = Toolkit.getDefaultToolkit().getImage("programa/images/Graf2.png");
        setIconImage(im);

        JPanel panel = new JPanel();
        caractPanel(panel);
        add(panel);

     //============================================================================================================================

        Connection cnx = MySQLConnectionDAO.createConnection();

        PreparedStatement prepStat = null;
        ResultSet rs = null;

        String[] vector_marcas_nombre = new String[8];
        int[] vector_marcas_cantidad = new int[8];

        try 
        {
            prepStat = cnx.prepareStatement("select marca, count(marca) as Marcas from Equipos group by marca");

            rs = prepStat.executeQuery();

            if (rs.next())
            {
                int posicion = 0;

                do 
                {
                    vector_marcas_nombre[posicion] = rs.getString(1);
                    vector_marcas_cantidad[posicion] = rs.getInt(2);

                    if (vector_marcas_nombre[posicion].equalsIgnoreCase("Apple"))
                    {
                        appl = vector_marcas_cantidad[posicion];
                    }
                    else if (vector_marcas_nombre[posicion].equalsIgnoreCase("dell"))
                    {
                        del = vector_marcas_cantidad[posicion];
                    }
                    else if (vector_marcas_nombre[posicion].equalsIgnoreCase("lenovo"))
                    {
                        lenov = vector_marcas_cantidad[posicion];
                    }
                    else if (vector_marcas_nombre[posicion].equalsIgnoreCase("hp"))
                    {
                        h_p = vector_marcas_cantidad[posicion];
                    }
                    else if (vector_marcas_nombre[posicion].equalsIgnoreCase("asus"))
                    {
                        asu = vector_marcas_cantidad[posicion];
                    }
                    else if (vector_marcas_nombre[posicion].equalsIgnoreCase("acer"))
                    {
                        ace = vector_marcas_cantidad[posicion];
                    }
                    else if (vector_marcas_nombre[posicion].equalsIgnoreCase("samsung"))
                    {
                        samsu = vector_marcas_cantidad[posicion];
                    }
                    else if (vector_marcas_nombre[posicion].equalsIgnoreCase("msi"))
                    {
                        ms = vector_marcas_cantidad[posicion];
                    }

                    posicion++;
                } 
                while (rs.next());
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

        repaint();

     //============================================================================================================================

        JLabel titulo = new JLabel("Grafica de Marcas", SwingConstants.CENTER);
        titulo.setFont(new Font("", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(20, 5, 300, 30);
        panel.add(titulo);

        JLabel leyenda = new JLabel("Creado por Facundo Leiva", SwingConstants.CENTER);
        leyenda.setBounds(150, 435, 300, 30);
        leyenda.setFont(new Font("", Font.BOLD, 12));
        leyenda.setForeground(Color.LIGHT_GRAY);
        panel.add(leyenda);

        JLabel apple = new JLabel(" de Apple");
        caractLabel(apple);
        apple.setForeground(new Color(245, 100, 100));
        apple.setBounds(380, 80, 150, 30);
        panel.add(apple);

        JLabel dell = new JLabel(" de Dell");
        caractLabel(dell);
        dell.setForeground(new Color(245, 155, 100));
        dell.setBounds(380, 120, 150, 30);
        panel.add(dell);

        JLabel lenovo = new JLabel(" de Lenovo");
        caractLabel(lenovo);
        lenovo.setForeground(new Color(245, 200, 100));
        lenovo.setBounds(380, 160, 150, 30);
        panel.add(lenovo);

        JLabel hp = new JLabel(" de HP");
        caractLabel(hp);
        hp.setForeground(new Color(245, 245, 100));
        hp.setBounds(380, 200, 150, 30);
        panel.add(hp);

        JLabel asus = new JLabel(" de Asus");
        caractLabel(asus);
        asus.setForeground(new Color(140, 245, 100));
        asus.setBounds(380, 240, 150, 30);
        panel.add(asus);

        JLabel acer = new JLabel(" de Acer");
        caractLabel(acer);
        acer.setForeground(new Color(100, 245, 200));
        acer.setBounds(380, 280, 150, 30);
        panel.add(acer);

        JLabel samsung = new JLabel(" de Samsung");
        caractLabel(samsung);
        samsung.setForeground(new Color(100, 145, 245));
        samsung.setBounds(380, 320, 150, 30);
        panel.add(samsung);

        JLabel msi = new JLabel(" de MSI");
        caractLabel(msi);
        msi.setForeground(new Color(170, 100, 245));
        msi.setBounds(380, 360, 150, 30);
        panel.add(msi);
    }

 //======================================================================================================================
 
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        int total_marcas = appl + del + lenov + h_p + asu + ace + samsu + ms;

        int grados_apple = appl * 360 / total_marcas;
        int grados_dell = del * 360 / total_marcas;
        int grados_lenovo = lenov * 360 / total_marcas;
        int grados_hp = h_p * 360 / total_marcas;
        int grados_asus = asu * 360 / total_marcas;
        int grados_acer = ace * 360 / total_marcas;
        int grados_samsu = samsu * 360 / total_marcas;
        int grados_msi = ms * 360 / total_marcas;

        //Marca apple
        g.setColor(new Color(245, 100, 100));
        g.fillArc(25, 100, 270, 270, 0, grados_apple);

        //Marca dell
        g.setColor(new Color(245, 155, 100));
        g.fillArc(25, 100, 270, 270, grados_apple, grados_dell);

        //Marca lenovo
        g.setColor(new Color(245, 200, 100));
        g.fillArc(25, 100, 270, 270, grados_apple + grados_dell, grados_lenovo);

        //Marca HP
        g.setColor(new Color(245, 245, 100));
        g.fillArc(25, 100, 270, 270, grados_apple + grados_dell + grados_lenovo, grados_hp);

        //Marca Asus
        g.setColor(new Color(140, 245, 100));
        g.fillArc(25, 100, 270, 270, grados_apple + grados_dell + grados_lenovo + grados_hp, grados_asus);

        //Marca Acer
        g.setColor(new Color(100, 245, 200));
        g.fillArc(25, 100, 270, 270, grados_apple + grados_dell + grados_lenovo + grados_hp + grados_asus, grados_acer);

        //Marca Samsung
        g.setColor(new Color(100, 145, 245));
        g.fillArc(25, 100, 270, 270, grados_apple + grados_dell + grados_lenovo + grados_hp + grados_asus + grados_acer, grados_samsu);

        //Marca MSI
        g.setColor(new Color(170, 100, 245));
        g.fillArc(25, 100, 270, 270, grados_apple + grados_dell + grados_lenovo + grados_hp + grados_asus + grados_acer + grados_samsu, grados_msi);
    }
 
 //======================================================================================================================

    public String nombreUser()
    {
        MySQLUsuarios user = new MySQLUsuarios();
        UsuariosDTO userDto = user.loginUsuario(LoginScreen.usuarioLogin, LoginScreen.passwordLogin);

        return userDto.getNombre_usuario();
    }

    public void caractFrame()
    {
        setSize(600, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
    }   

    public void caractPanel(JPanel p)
    {
        p.setLayout(null);
        p.setSize(600, 500);
        p.setBackground(new Color(80,80,80));
    }

    public void caractLabel(JLabel l)
    {
        l.setFont(new Font("", Font.BOLD, 18));
    }
}