package programa.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import programa.dao.data_transfer_objet.UsuariosDTO;
import programa.dao.implementation.MySQLUsuarios;

public class TecnicoUI extends JFrame implements ActionListener
{
    private final JButton equipos, estatus, marcas;

    public TecnicoUI()
    {
        caractFrame();
        setTitle("Tecnico - Sesion de " + nombreUser());

        Image im = Toolkit.getDefaultToolkit().getImage("programa/images/Tecnico.png");
        setIconImage(im);

        JPanel panel = new JPanel();
        caractPanel(panel);
        add(panel);

     //============================================================================================================================

        JLabel mensaje = new JLabel();
        mensaje.setText("Bienvenido " + nombreUser());
        mensaje.setFont(new Font("", Font.BOLD, 16));
        mensaje.setForeground(Color.WHITE);
        mensaje.setBounds(20, 5, 400, 30);
        panel.add(mensaje);

        JLabel leyenda = new JLabel("Creado por Facundo Leiva", SwingConstants.CENTER);
        leyenda.setBounds(200, 235, 150, 30);
        leyenda.setFont(new Font("", Font.BOLD, 11));
        leyenda.setForeground(Color.LIGHT_GRAY);
        panel.add(leyenda);

        JLabel gestEquipos = new JLabel("Gestion de Equipos", SwingConstants.CENTER);
        caractLabel(gestEquipos);
        gestEquipos.setBounds(25, 180, 150, 30);
        panel.add(gestEquipos);

        JLabel grafEstatus = new JLabel("Grafica de Estatus", SwingConstants.CENTER);
        caractLabel(grafEstatus);
        grafEstatus.setBounds(195, 180, 150, 30);
        panel.add(grafEstatus);

        JLabel grafMarcas = new JLabel("Grafica de Marcas", SwingConstants.CENTER);
        caractLabel(grafMarcas);
        grafMarcas.setBounds(360, 180, 150, 30);
        panel.add(grafMarcas);

        equipos = new JButton(new ImageIcon("programa/images/GestEquip.png"));
        caractBoton(equipos);
        equipos.setBounds(38, 50, 125, 125);
        panel.add(equipos);
        equipos.addActionListener(this);

        estatus = new JButton(new ImageIcon("programa/images/Graf1.png"));
        caractBoton(estatus);
        estatus.setBounds(208, 50, 125, 125);
        panel.add(estatus);
        estatus.addActionListener(this);

        marcas = new JButton(new ImageIcon("programa/images/Graf2.png"));
        caractBoton(marcas);
        marcas.setBounds(375, 50, 125, 125);
        panel.add(marcas);
        marcas.addActionListener(this);
    }

 //============================================================================================================================

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == equipos)
        {
            new GestionarEquiposUI().setVisible(true);
        }

        if (e.getSource() == estatus)
        {
            new GraficaEstatusUI().setVisible(true);
        }

        if (e.getSource() == marcas)
        {
            new GraficaMarcasUI().setVisible(true);
        }
    }

 //============================================================================================================================

    public String nombreUser()
    {
        MySQLUsuarios user = new MySQLUsuarios();
        UsuariosDTO userDto = user.loginUsuario(LoginScreen.usuarioLogin, LoginScreen.passwordLogin);

        return userDto.getNombre_usuario();
    }

    public void caractFrame()
    {
        setSize(550, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
    }   

    public void caractPanel(JPanel p)
    {
        p.setLayout(null);
        p.setSize(550, 300);
        p.setBackground(new Color(30,70,110));
    }

    public void caractLabel(JLabel l)
    {
        l.setFont(new Font("", Font.BOLD, 14));
        l.setForeground(Color.WHITE);
    }

    public void caractBoton(JButton b)
    {
        b.setBackground(new Color(30,70,110));
        b.setBorder(new LineBorder(new Color(30,70,110)));
    }
}