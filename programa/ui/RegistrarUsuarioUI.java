package programa.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

import programa.dao.data_transfer_objet.UsuariosDTO;
import programa.dao.implementation.MySQLUsuarios;

public class RegistrarUsuarioUI extends JFrame implements ActionListener
{
    private final JTextField id, nom, ema, tel, user, pass;
    private final JComboBox<String> permi;

    public RegistrarUsuarioUI()
    {
        caractFrame();
        setTitle("Registrar Nuevo Usuario - Sesion de " + nombreUser());

        Image im = Toolkit.getDefaultToolkit().getImage("programa/images/RegisUser.png");
        setIconImage(im);

        JPanel panel = new JPanel();
        caractPanel(panel);
        add(panel);

     //============================================================================================================================

        JLabel titulo = new JLabel();
        titulo.setText("Registro de Usuarios");
        titulo.setFont(new Font("", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(200, 5, 250, 30);
        panel.add(titulo);

        JLabel leyenda = new JLabel("Creado por Facundo Leiva", SwingConstants.CENTER);
        leyenda.setBounds(225, 337, 150, 30);
        leyenda.setFont(new Font("", Font.BOLD, 12));
        leyenda.setForeground(Color.LIGHT_GRAY);
        panel.add(leyenda);

        JLabel id_usuario = new JLabel("ID Usuario:");
        caractLabel(id_usuario);
        id_usuario.setBounds(50, 50, 100, 30);
        panel.add(id_usuario);

        JLabel nombre = new JLabel("Nombre:");
        caractLabel(nombre);
        nombre.setBounds(50, 120, 100, 30);
        panel.add(nombre);

        JLabel email = new JLabel("Email:");
        caractLabel(email);
        email.setBounds(50, 190, 100, 30);
        panel.add(email);

        JLabel telefono = new JLabel("Telefono:");
        caractLabel(telefono);
        telefono.setBounds(330, 50, 100, 30);
        panel.add(telefono);

        JLabel username = new JLabel("Username:");
        caractLabel(username);
        username.setBounds(330, 120, 100, 30);
        panel.add(username);

        JLabel password = new JLabel("Password:");
        caractLabel(password);
        password.setBounds(330, 190, 100, 30);
        panel.add(password);

        JLabel permisos_de = new JLabel("Permisos De:");
        caractLabel(permisos_de);
        permisos_de.setBounds(50, 260, 100, 30);
        panel.add(permisos_de);

        id = new JTextField();
        caractText(id);
        id.setBounds(50, 85, 230, 25);
        panel.add(id);

        nom = new JTextField();
        caractText(nom);
        nom.setBounds(50, 155, 225, 25);
        panel.add(nom);

        ema = new JTextField();
        caractText(ema);
        ema.setBounds(50, 225, 225, 25);
        panel.add(ema);

        tel = new JTextField();
        caractText(tel);
        tel.setBounds(330, 85, 225, 25);
        panel.add(tel);

        user = new JTextField();
        caractText(user);
        user.setBounds(330, 155, 225, 25);
        panel.add(user);

        pass = new JTextField();
        caractText(pass);
        pass.setBounds(330, 225, 225, 25);
        panel.add(pass);

        permi = new JComboBox<>();
        caractCombo(permi);
        permi.addItem("");
        permi.addItem("Administrador");
        permi.addItem("Capturista");
        permi.addItem("Tecnico");
        permi.setBounds(50, 295, 150, 25);
        panel.add(permi);

        JButton registrar = new JButton(new ImageIcon("programa/images/UserRegis2.png"));
        caractBoton(registrar);
        registrar.setBounds(370, 260, 75, 75);
        panel.add(registrar);
        registrar.addActionListener(this);
    }

 //============================================================================================================================

    @Override
    public void actionPerformed(ActionEvent e)
    {
        try {
            if (!id.getText().equals("") && !nom.getText().equals("") && !ema.getText().equals("") && !tel.getText().equals("") && !user.getText().equals("") && !pass.getText().equals("") && !Objects.equals(permi.getSelectedItem(), "")) {
                int id_cl = Integer.parseInt(id.getText());
                String nombre_cl = nom.getText();
                String email_cl = ema.getText();
                String telefono_cl = tel.getText();
                String username_cl = user.getText();
                String password_cl = pass.getText();
                String permisos_cl = (String) permi.getSelectedItem();
                String estatus = "Activo";
                String regis_por = nombreUser();

                MySQLUsuarios SQLUsuario = new MySQLUsuarios();
                SQLUsuario.ingresarUsuario(id_cl, nombre_cl, email_cl, telefono_cl, username_cl, password_cl, permisos_cl, estatus, regis_por);

                id.setBorder(new LineBorder(Color.BLACK, 2));
                nom.setBorder(new LineBorder(Color.BLACK, 2));
                ema.setBorder(new LineBorder(Color.BLACK, 2));
                tel.setBorder(new LineBorder(Color.BLACK, 2));
                user.setBorder(new LineBorder(Color.BLACK, 2));
                pass.setBorder(new LineBorder(Color.BLACK, 2));
                permi.setBorder(new LineBorder(Color.BLACK, 2));

                id.setText("");
                nom.setText("");
                ema.setText("");
                tel.setText("");
                user.setText("");
                pass.setText("");
                permi.setSelectedIndex(0);

                JOptionPane.showMessageDialog(null, "Usuario Ingresado Correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos.");

                id.setBorder(new LineBorder(Color.RED, 2));
                nom.setBorder(new LineBorder(Color.RED, 2));
                ema.setBorder(new LineBorder(Color.RED, 2));
                tel.setBorder(new LineBorder(Color.RED, 2));
                user.setBorder(new LineBorder(Color.RED, 2));
                pass.setBorder(new LineBorder(Color.RED, 2));
                permi.setBorder(new LineBorder(Color.RED, 2));
            }
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Los campos ID y TELEFONO solo aceptan numeros.");

            id.setBorder(new LineBorder(Color.RED, 2));
            tel.setBorder(new LineBorder(Color.RED, 2));
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

    public void caractLabel(JLabel l)
    {
        l.setFont(new Font("", Font.BOLD, 15));
        l.setForeground(Color.WHITE);
    }

    public void caractText(JTextField t)
    {
        t.setBackground(new Color(70,185,250));
        t.setBorder(new LineBorder(Color.BLACK, 2));
        t.setFont(new Font("", Font.BOLD, 14));
        t.setForeground(Color.BLACK);
    }

    public void caractCombo(JComboBox<String> c)
    {
        c.setBackground(new Color(70,185,250));
        c.setBorder(new LineBorder(Color.BLACK, 2));
        c.setFont(new Font("", Font.BOLD, 13));
        c.setForeground(Color.BLACK);
    }

    public void caractBoton(JButton b)
    {
        b.setBackground(new Color(30,70,110));
        b.setBorder(new LineBorder(new Color(30,70,110)));
    }
}