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

public class GraficaEstatusUI extends JFrame
{
    private int ingreso, no_reparado, en_revision, reparado, entregado;

    public GraficaEstatusUI()
    {
        caractFrame();
        setTitle("Tecnico - Sesion de " + nombreUser());

        Image im = Toolkit.getDefaultToolkit().getImage("programa/images/Graf1.png");
        setIconImage(im);

        JPanel panel = new JPanel();
        caractPanel(panel);
        add(panel);

     //============================================================================================================================

        Connection cnx = MySQLConnectionDAO.createConnection();

        PreparedStatement prepStat = null;
        ResultSet rs = null;

        String[] vector_estatus_nombre = new String[5];
        int[] vector_estatus_cantidad = new int[5];

        try 
        {
            prepStat = cnx.prepareStatement("select estatus, count(estatus) as Cantidad from Equipos group by estatus");

            rs = prepStat.executeQuery();

            if (rs.next())
            {
                int posicion = 0;

                do 
                {
                    vector_estatus_nombre[posicion] = rs.getString(1);
                    vector_estatus_cantidad[posicion] = rs.getInt(2);

                    if (vector_estatus_nombre[posicion].equalsIgnoreCase("En Revision")) //Orden alfabetico.
                    {
                        en_revision = vector_estatus_cantidad[posicion];
                    }
                    else if (vector_estatus_nombre[posicion].equalsIgnoreCase("Entregado"))
                    {
                        entregado = vector_estatus_cantidad[posicion];
                    }
                    else if (vector_estatus_nombre[posicion].equalsIgnoreCase("No Reparado"))
                    {
                        no_reparado = vector_estatus_cantidad[posicion];
                    }
                    else if (vector_estatus_nombre[posicion].equalsIgnoreCase("Nuevo Ingreso"))
                    {
                        ingreso = vector_estatus_cantidad[posicion];
                    }
                    else if (vector_estatus_nombre[posicion].equalsIgnoreCase("Reparado"))
                    {
                        reparado = vector_estatus_cantidad[posicion];
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

        JLabel titulo = new JLabel("Grafica de Estatus", SwingConstants.CENTER);
        titulo.setFont(new Font("", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(150, 5, 300, 30);
        panel.add(titulo);

        JLabel leyenda = new JLabel("Creado por Facundo Leiva", SwingConstants.CENTER);
        leyenda.setBounds(150, 435, 300, 30);
        leyenda.setFont(new Font("", Font.BOLD, 12));
        leyenda.setForeground(Color.LIGHT_GRAY);
        panel.add(leyenda);

        JLabel nuev_ingr = new JLabel("Nuevos Ingresos");
        caractLabel(nuev_ingr);
        nuev_ingr.setBounds(30, 60, 150, 30);
        panel.add(nuev_ingr);

        JLabel cant1 = new JLabel("Cantidad: " + ingreso);
        caractLabel(cant1);
        cant1.setBounds(30, 80, 150, 30);
        panel.add(cant1);

        JLabel no_repa = new JLabel("No Reparados");
        caractLabel(no_repa);
        no_repa.setBounds(30, 120, 150, 30);
        panel.add(no_repa);

        JLabel cant2 = new JLabel("Cantidad: " + no_reparado);
        caractLabel(cant2);
        cant2.setBounds(30, 140, 150, 30);
        panel.add(cant2);

        JLabel en_rev = new JLabel("En Revision");
        caractLabel(en_rev);
        en_rev.setBounds(30, 180, 150, 30);
        panel.add(en_rev);

        JLabel cant3 = new JLabel("Cantidad: " + en_revision);
        caractLabel(cant3);
        cant3.setBounds(30, 200, 150, 30);
        panel.add(cant3);

        JLabel repa = new JLabel("Reparados");
        caractLabel(repa);
        repa.setBounds(30, 240, 150, 30);
        panel.add(repa);

        JLabel cant4 = new JLabel("Cantidad: " + reparado);
        caractLabel(cant4);
        cant4.setBounds(30, 260, 150, 30);
        panel.add(cant4);

        JLabel entre = new JLabel("Entregados");
        caractLabel(entre);
        entre.setBounds(30, 300, 150, 30);
        panel.add(entre);

        JLabel cant5 = new JLabel("Cantidad: " + entregado);
        caractLabel(cant5);
        cant5.setBounds(30, 320, 150, 30);
        panel.add(cant5);
    }

 //======================================================================================================================

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        int estatus_mas_repetido = EstatusMasRepetido();

        int largo_nuevoIngreso = ingreso * 350 / estatus_mas_repetido;
        int largo_no_reparado = no_reparado * 350 / estatus_mas_repetido;
        int largo_en_revision = en_revision * 350 / estatus_mas_repetido;
        int largo_reparado = reparado * 350 / estatus_mas_repetido;
        int largo_entregado = entregado * 350 / estatus_mas_repetido;

        g.setColor(new Color(245,100,100)); //Rojo
        g.fillRect(200, 100, largo_nuevoIngreso, 40);

        g.setColor(new Color(245,230,100)); //Amarillo
        g.fillRect(200, 160, largo_no_reparado, 40);

        g.setColor(new Color(100,245,160)); //Verde
        g.fillRect(200, 220, largo_en_revision, 40);

        g.setColor(new Color(100,235,245)); //Cyan
        g.fillRect(200, 280, largo_reparado, 40);

        g.setColor(new Color(130,100,245)); //Morado
        g.fillRect(200, 340, largo_entregado, 40);
    }

 //======================================================================================================================

    public int EstatusMasRepetido()
    {
        if (ingreso > no_reparado && ingreso > en_revision && ingreso > reparado && ingreso > entregado)
        {
            return ingreso;
        }
        else if (no_reparado > en_revision && no_reparado > reparado && no_reparado > entregado)
        {
            return no_reparado;
        }
        else if (en_revision > reparado && en_revision > entregado)
        {
            return en_revision;
        }
        else return Math.max(reparado, entregado);
    }

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
        l.setFont(new Font("", Font.BOLD, 14));
        l.setForeground(Color.WHITE);
    }
}