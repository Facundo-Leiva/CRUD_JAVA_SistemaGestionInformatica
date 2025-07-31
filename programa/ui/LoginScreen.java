package programa.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import programa.dao.data_transfer_objet.UsuariosDTO;
import programa.dao.implementation.MySQLUsuarios;

public class LoginScreen extends JFrame implements ActionListener
{
    private final JTextField user;
    private final JPasswordField pass;

    public static String usuarioLogin;
    public static String passwordLogin;

    public LoginScreen()
    {
        caractFrame();

        Image im = Toolkit.getDefaultToolkit().getImage("programa/images/icono.png");
        setIconImage(im);

        JPanel panel = new JPanel();
        caractPanel(panel);
        add(panel);

     //===================================================================================================

        JLabel logo = new JLabel(new ImageIcon("programa/images/logo.png"), SwingConstants.CENTER);
        logo.setBounds(113, 15, 175, 175);
        panel.add(logo);

        JLabel linea1 = new JLabel(new ImageIcon("programa/images/linea.png"));
        linea1.setBounds(15, -20, 50, 300);
        panel.add(linea1);

        JLabel linea12 = new JLabel(new ImageIcon("programa/images/linea.png"));
        linea12.setBounds(15, 180, 50, 300);
        panel.add(linea12);

        JLabel linea21 = new JLabel(new ImageIcon("programa/images/linea.png"));
        linea21.setBounds(320, -20, 50, 300);
        panel.add(linea21);

        JLabel linea22 = new JLabel(new ImageIcon("programa/images/linea.png"));
        linea22.setBounds(320, 180, 50, 300);
        panel.add(linea22);

        JLabel leyenda = new JLabel("Creado por Facundo Leiva", SwingConstants.CENTER);
        leyenda.setBounds(125, 435, 150, 30);
        leyenda.setForeground(Color.LIGHT_GRAY);
        leyenda.setFont(new Font("", Font.BOLD, 12));
        panel.add(leyenda);

        JLabel usuario = new JLabel("Usuario:", SwingConstants.CENTER);
        caractLabel(usuario);
        usuario.setBounds(150, 215, 100, 30);
        panel.add(usuario);

        JLabel contraseña = new JLabel("Contraseña:", SwingConstants.CENTER);
        caractLabel(contraseña);
        contraseña.setBounds(125, 290, 150, 30);
        panel.add(contraseña);

        user = new JTextField();
        caractText(user);
        user.setBounds(100, 255, 200, 25);
        panel.add(user);

        pass = new JPasswordField();
        caractText(pass);
        pass.setBounds(100, 330, 200, 25);
        panel.add(pass);

        JButton btnAcceso = new JButton("Acceder");
        caractBoton(btnAcceso);
        panel.add(btnAcceso);
        btnAcceso.addActionListener(this);
    }

 //===================================================================================================

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        usuarioLogin = user.getText().trim();

        char[] arrayPass = pass.getPassword(); //Para obtener la contraseña.
        passwordLogin = new String(arrayPass); //Devuelve un array de char, tenemos que convertirlo en string.

        if (!usuarioLogin.equals("") && !passwordLogin.equals(""))
        {
            try 
            {
                MySQLUsuarios usuarios = new MySQLUsuarios();
                UsuariosDTO userDto = usuarios.loginUsuario(usuarioLogin, passwordLogin);

                user.setBorder(new LineBorder(Color.BLACK, 2));
                pass.setBorder(new LineBorder(Color.BLACK, 2));

                String tipoNivel = userDto.getTipo_nivel();
                String estatus = userDto.getEstatus();

                if (tipoNivel.equalsIgnoreCase("Administrador") && estatus.equalsIgnoreCase("Activo"))
                {
                    new AdministradorUI().setVisible(true);
                    dispose();
                }

                else if (tipoNivel.equalsIgnoreCase("Capturista") && estatus.equalsIgnoreCase("Activo"))
                {
                    new CapturistaUI().setVisible(true);
                    dispose();
                }

                else if (tipoNivel.equalsIgnoreCase("Tecnico") && estatus.equalsIgnoreCase("Activo"))
                {
                    new TecnicoUI().setVisible(true);
                    dispose();
                }

                else
                {
                    JOptionPane.showMessageDialog(null, "Tu usuario esta inactivo, contacta con el administrador.");

                    user.setText("");
                    pass.setText("");
                }
            } 
            catch (Exception ex) 
            {
                //JOptionPane.showMessageDialog(null, "Error! " + ex);

                JOptionPane.showMessageDialog(null, "Error al iniciar sesion, asegurate que hayas introducido bien los datos. Si el error persiste hable con el Administrador.");

                user.setBorder(new LineBorder(Color.RED, 2));
                pass.setBorder(new LineBorder(Color.RED, 2));
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Por favor, complete TODOS los campos.");

            user.setBorder(new LineBorder(Color.RED, 2));
            pass.setBorder(new LineBorder(Color.RED, 2));
        }
    }

 //===================================================================================================

    public void caractFrame()
    {
        setSize(400, 500);
        setTitle("Acceso al Sistema");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public void caractPanel(JPanel p)
    {
        p.setLayout(null);
        p.setSize(400, 500);
        p.setBackground(new Color(30,70,110));
    }

    public void caractLabel(JLabel l)
    {
        l.setFont(new Font("", Font.BOLD, 18));
        l.setForeground(Color.WHITE);
    }

    public void caractText(JTextField j)
    {
        j.setFont(new Font("", Font.BOLD, 15));
        j.setForeground(Color.BLACK);
        j.setBackground(new Color(90,200,255));
        j.setBorder(new LineBorder(Color.BLACK, 2));
    }

    public void caractBoton(JButton b)
    {
        b.setFont(new Font("", Font.BOLD, 18));
        b.setForeground(Color.BLACK);
        b.setBackground(new Color(90,200,255));
        b.setBorder(new LineBorder(Color.BLACK, 2));
        b.setBounds(140, 380, 120, 30);
    }
}