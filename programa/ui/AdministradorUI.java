package programa.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import programa.dao.data_transfer_objet.UsuariosDTO;
import programa.dao.implementation.MySQLUsuarios;

public class AdministradorUI extends JFrame implements ActionListener
{
    private final JButton regUser, gesUser, creatividad, panelcaptu, panelTecnico, acerca, login;

    public AdministradorUI()
    {
        caractFrame();
        setTitle("Administrador - Sesion de " + nombreUser());

        Image im = Toolkit.getDefaultToolkit().getImage("programa/images/Admin.png");
        setIconImage(im);

        JPanel panel = new JPanel();
        caractPanel(panel);
        add(panel);

     //============================================================================================================================

        JLabel mensaje = new JLabel();
        mensaje.setText("Bienvenido " + nombreUser());
        mensaje.setFont(new Font("", Font.BOLD, 18));
        mensaje.setForeground(Color.WHITE);
        mensaje.setBounds(20, 5, 400, 30);
        panel.add(mensaje);

        JLabel leyenda = new JLabel("Creado por Facundo Leiva", SwingConstants.CENTER);
        leyenda.setBounds(250, 385, 150, 30);
        leyenda.setFont(new Font("", Font.BOLD, 12));
        leyenda.setForeground(Color.LIGHT_GRAY);
        panel.add(leyenda);

        JLabel regUs = new JLabel("Registrar Usuario", SwingConstants.CENTER);
        caractLabel(regUs);
        regUs.setBounds(60, 175, 125, 30);
        panel.add(regUs);

        JLabel gestUs = new JLabel("Gestionar Usuario", SwingConstants.CENTER);
        caractLabel(gestUs);
        gestUs.setBounds(247, 175, 150, 30);
        panel.add(gestUs);

        JLabel crea = new JLabel("Creatividad", SwingConstants.CENTER);
        caractLabel(crea);
        crea.setBounds(460, 175, 125, 30);
        panel.add(crea);

        JLabel panelCap = new JLabel("Panel Vista Capturista", SwingConstants.CENTER);
        caractLabel(panelCap);
        panelCap.setBounds(40, 350, 170, 30);
        panel.add(panelCap);

        JLabel panelTec = new JLabel("Panel Vista Tecnico", SwingConstants.CENTER);
        caractLabel(panelTec);
        panelTec.setBounds(250, 350, 150, 30);
        panel.add(panelTec);

        JLabel acercaDe = new JLabel("Acerca De", SwingConstants.CENTER);
        caractLabel(acercaDe);
        acercaDe.setBounds(450, 350, 150, 30);
        panel.add(acercaDe);

        regUser = new JButton(new ImageIcon("programa/images/RegisUser.png"));
        regUser.setBounds(60, 50, 125, 125);
        caractBoton(regUser);
        panel.add(regUser);
        regUser.addActionListener(this);

        gesUser = new JButton(new ImageIcon("programa/images/GestUser.png"));
        gesUser.setBounds(260, 50, 125, 125);
        caractBoton(gesUser);
        panel.add(gesUser);
        gesUser.addActionListener(this);

        creatividad = new JButton(new ImageIcon("programa/images/Creativity.png"));
        creatividad.setBounds(460, 50, 125, 125);
        caractBoton(creatividad);
        panel.add(creatividad);
        creatividad.addActionListener(this);

        panelcaptu = new JButton(new ImageIcon("programa/images/Capturista.png"));
        panelcaptu.setBounds(60, 220, 125, 130);
        caractBoton(panelcaptu);
        panel.add(panelcaptu);
        panelcaptu.addActionListener(this);

        panelTecnico = new JButton(new ImageIcon("programa/images/Tecnico.png"));
        panelTecnico.setBounds(263, 220, 125, 130);
        caractBoton(panelTecnico);
        panel.add(panelTecnico);
        panelTecnico.addActionListener(this);

        acerca = new JButton(new ImageIcon("programa/images/acercaDe.png"));
        acerca.setBounds(470, 230, 125, 125);
        caractBoton(acerca);
        panel.add(acerca);
        acerca.addActionListener(this);

        login = new JButton("Volver al Login");
        login.setFont(new Font("", Font.BOLD, 16));
        login.setForeground(Color.WHITE);
        login.setBackground(new Color(30,70,110));
        login.setBorder(new LineBorder(new Color(30,70,110)));
        login.setBounds(480, 10, 175, 25);
        panel.add(login);
        login.addActionListener(this);
    }

 //============================================================================================================================

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == regUser)
        {
            new RegistrarUsuarioUI().setVisible(true);
        }

        if (e.getSource() == gesUser)
        {
            new GestionarUsuariosUI().setVisible(true);
        }

        if (e.getSource() == creatividad)
        {
            new CreatividadUI().setVisible(true);
        }

        if (e.getSource() == panelcaptu)
        {
            CapturistaUI capturista = new CapturistaUI();
            capturista.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            capturista.setVisible(true);
        }

        if (e.getSource() == panelTecnico)
        {
            TecnicoUI tecnico = new TecnicoUI();
            tecnico.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            tecnico.setVisible(true);
        }

        if (e.getSource() == acerca)
        {
            new AcercaDeUI().setVisible(true);
        }

        if (e.getSource() == login)
        {
            dispose();

            new LoginScreen().setVisible(true);
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
        setSize(650, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
    }   

    public void caractPanel(JPanel p)
    {
        p.setLayout(null);
        p.setSize(650, 450);
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