package programa.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import programa.dao.data_transfer_objet.UsuariosDTO;
import programa.dao.implementation.MySQLUsuarios;

public class GestionarUsuariosUI extends JFrame
{
    private final JTable tabla;

    MySQLUsuarios usuario = new MySQLUsuarios();

    public static String user_name = "";
    public static int user_id = 0;

    public GestionarUsuariosUI()
    {
        caractFrame();
        setTitle("Usuarios Registrados - Sesion de " + nombreUser());

        Image im = Toolkit.getDefaultToolkit().getImage("programa/images/GestUser.png");
        setIconImage(im);

        JPanel panel = new JPanel();
        caractPanel(panel);
        add(panel);

     //============================================================================================================================

        JLabel titulo = new JLabel();
        titulo.setText("Usuarios Registrados");
        titulo.setFont(new Font("", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(200, 5, 250, 30);
        panel.add(titulo);

        JLabel leyenda = new JLabel("Creado por Facundo Leiva", SwingConstants.CENTER);
        leyenda.setBounds(225, 335, 150, 30);
        leyenda.setFont(new Font("", Font.BOLD, 12));
        leyenda.setForeground(Color.LIGHT_GRAY);
        panel.add(leyenda);

        tabla = usuario.tablaUsuarios();
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(0, 50, 600, 280);
        panel.add(scroll);

        tabla.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                int fila_point = tabla.rowAtPoint(e.getPoint());
                int columna_point = 2;

                if (fila_point > -1)
                {
                    user_name = (String) tabla.getValueAt(fila_point, columna_point);

                    user_id = (int) tabla.getValueAt(fila_point, 0);

                    new InformacionUsuarioUI().setVisible(true);

                    dispose();
                }
            }
        });
    } 

    public String nombreUser()
    {
        MySQLUsuarios user = new MySQLUsuarios();
        UsuariosDTO userDto = user.loginUsuario(LoginScreen.usuarioLogin, LoginScreen.passwordLogin);

        return userDto.getNombre_usuario();
    }

    public void caractFrame()
    {
        setSize(600, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
    }   

    public void caractPanel(JPanel p)
    {
        p.setLayout(null);
        p.setSize(600, 400);
        p.setBackground(new Color(30,70,110));
    }
}