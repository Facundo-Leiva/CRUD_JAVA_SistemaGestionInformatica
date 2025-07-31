package programa.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

import programa.dao.data_transfer_objet.UsuariosDTO;
import programa.dao.implementation.MySQLUsuarios;

public class InformacionUsuarioUI extends JFrame implements ActionListener
{
    private final JTextField nom, ema, tel, user;
    private final JComboBox<String> permi, estat;
    private final JButton actualizar_usuario, restaurar_password;

    MySQLUsuarios usuariosSQL = new MySQLUsuarios();
    UsuariosDTO userDTO = usuariosSQL.buscarUsuario(GestionarUsuariosUI.user_id);

    public InformacionUsuarioUI()
    {
        caractFrame();
        setTitle("Informacion del Usuario " + GestionarUsuariosUI.user_name + " - Sesion de " + nombreUser());

        Image im = Toolkit.getDefaultToolkit().getImage("programa/images/GestUser.png");
        setIconImage(im);

        JPanel panel = new JPanel();
        caractPanel(panel);
        add(panel);

     //============================================================================================================================

        JLabel titulo = new JLabel();
        titulo.setText("Informacion del Usuario " + GestionarUsuariosUI.user_name);
        titulo.setFont(new Font("", Font.BOLD, 18));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(150, 5, 300, 30);
        panel.add(titulo);

        JLabel leyenda = new JLabel("Creado por Facundo Leiva", SwingConstants.CENTER);
        leyenda.setBounds(225, 335, 150, 30);
        leyenda.setFont(new Font("", Font.BOLD, 12));
        leyenda.setForeground(Color.LIGHT_GRAY);
        panel.add(leyenda);

        JLabel nombre = new JLabel("Nombre:");
        caractLabel(nombre);
        nombre.setBounds(50, 50, 100, 30);
        panel.add(nombre);

        JLabel email = new JLabel("Email:");
        caractLabel(email);
        email.setBounds(50, 110, 100, 30);
        panel.add(email);

        JLabel telefono = new JLabel("Telefono:");
        caractLabel(telefono);
        telefono.setBounds(50, 170, 100, 30);
        panel.add(telefono);

        JLabel permisos_de = new JLabel("Permisos de:");
        caractLabel(permisos_de);
        permisos_de.setBounds(50, 230, 100, 30);
        panel.add(permisos_de);

        JLabel username = new JLabel("Username:");
        caractLabel(username);
        username.setBounds(350, 50, 100, 30);
        panel.add(username);

        JLabel estatus = new JLabel("Estatus:");
        caractLabel(estatus);
        estatus.setBounds(350, 110, 100, 30);
        panel.add(estatus);

        JLabel registrado_por = new JLabel("Registrado por:");
        caractLabel(registrado_por);
        registrado_por.setBounds(350, 170, 150, 30);
        panel.add(registrado_por);

        nom = new JTextField();
        caractText(nom);
        nom.setBounds(50, 82, 225, 25);
        panel.add(nom);
        nom.setText(userDTO.getNombre_usuario());

        ema = new JTextField();
        caractText(ema);
        ema.setBounds(50, 142, 225, 25);
        panel.add(ema);
        ema.setText(userDTO.getEmail_usuario());

        tel = new JTextField();
        caractText(tel);
        tel.setBounds(50, 202, 225, 25);
        panel.add(tel);
        tel.setText(userDTO.getTel_usuario());

        user = new JTextField();
        caractText(user);
        user.setBounds(350, 82, 225, 25);
        panel.add(user);
        user.setText(userDTO.getUsername());

        permi = new JComboBox<>();
        caractCombo(permi);
        permi.setBounds(50, 262, 150, 25);
        panel.add(permi);
        permi.addItem("");
        permi.addItem("Administrador");
        permi.addItem("Capturista");
        permi.addItem("Tecnico");
        permi.setSelectedItem(userDTO.getTipo_nivel());

        estat = new JComboBox<>();
        caractCombo(estat);
        estat.setBounds(350, 142, 150, 25);
        panel.add(estat);
        estat.addItem("");
        estat.addItem("Activo");
        estat.addItem("Inactivo");
        estat.setSelectedItem(userDTO.getEstatus());

        JButton regis_por = new JButton(userDTO.getRegistrado_por());
        caractBoton(regis_por);
        regis_por.setBounds(350, 202, 150, 25);
        panel.add(regis_por);

        actualizar_usuario = new JButton("Actualizar Usuario");
        caractBoton(actualizar_usuario);
        actualizar_usuario.setBounds(350, 262, 150, 25);
        panel.add(actualizar_usuario);
        actualizar_usuario.addActionListener(this);

        restaurar_password = new JButton("Restaurar Password");
        caractBoton(restaurar_password);
        restaurar_password.setBounds(350, 297, 150, 25);
        panel.add(restaurar_password);
        restaurar_password.addActionListener(this);
    }

 //============================================================================================================================

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == actualizar_usuario)
        {
            String permi_selec = (String )permi.getSelectedItem();
            String estatus_selec = (String )estat.getSelectedItem();

            if (!nom.getText().equals("") && !ema.getText().equals("") && !tel.getText().equals("") && !user.getText().equals("") && !Objects.equals(permi_selec, "") && !Objects.equals(estatus_selec, ""))
            {
                try 
                {
                    String tipo_nivel = (String) permi.getSelectedItem();
                    String estatus = (String) estat.getSelectedItem();
    
                    usuariosSQL.actualizarUsuario(GestionarUsuariosUI.user_id, nom.getText(), ema.getText(), tel.getText(), user.getText(), userDTO.getPassword(), tipo_nivel, estatus, userDTO.getRegistrado_por());
    
                    JOptionPane.showMessageDialog(null, "Usuario Actualizado Correctamente.");

                    nom.setBorder(new LineBorder(Color.BLACK, 2));
                    ema.setBorder(new LineBorder(Color.BLACK, 2));
                    tel.setBorder(new LineBorder(Color.BLACK, 2));
                    user.setBorder(new LineBorder(Color.BLACK, 2));
                    permi.setBorder(new LineBorder(Color.BLACK, 2));
                    estat.setBorder(new LineBorder(Color.BLACK, 2));
    
                    new GestionarUsuariosUI().setVisible(true);
    
                    dispose();
                } 
                catch (Exception ex) 
                {
                    JOptionPane.showMessageDialog(null, "Error al actualizar Usuario, contacte con el Administrador.");
                }
            } 
            else 
            {
                JOptionPane.showMessageDialog(null, "Por favor, completa TODOS los campos.");

                nom.setBorder(new LineBorder(Color.RED, 2));
                ema.setBorder(new LineBorder(Color.RED, 2));
                tel.setBorder(new LineBorder(Color.RED, 2));
                user.setBorder(new LineBorder(Color.RED, 2));
                permi.setBorder(new LineBorder(Color.RED, 2));
                estat.setBorder(new LineBorder(Color.RED, 2));
            }
        }

        if (e.getSource() == restaurar_password)
        {
            new RestaurarPasswordUI().setVisible(true);
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
        b.setFont(new Font("", Font.BOLD, 15));
        b.setForeground(Color.BLACK);
        b.setBackground(new Color(70,185,250));
        b.setBorder(new LineBorder(Color.BLACK, 2));
    }
}