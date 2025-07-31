package programa.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import programa.dao.data_transfer_objet.UsuariosDTO;
import programa.dao.implementation.MySQLClientes;
import programa.dao.implementation.MySQLUsuarios;

public class RegistrarClienteUI extends JFrame implements ActionListener
{
    private final JTextField id, nom, ema, tel, dir;

    public RegistrarClienteUI()
    {
        caractFrame();
        setTitle("Registrar Nuevo Cliente - Sesion de " + nombreUser());

        Image im = Toolkit.getDefaultToolkit().getImage("programa/images/RegisClient.png");
        setIconImage(im);

        JPanel panel = new JPanel();
        caractPanel(panel);
        add(panel);

     //============================================================================================================================

        JLabel titulo = new JLabel();
        titulo.setText("Registro de Clientes");
        titulo.setFont(new Font("", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(200, 5, 250, 30);
        panel.add(titulo);

        JLabel leyenda = new JLabel("Creado por Facundo Leiva", SwingConstants.CENTER);
        leyenda.setBounds(225, 337, 150, 30);
        leyenda.setFont(new Font("", Font.BOLD, 12));
        leyenda.setForeground(Color.LIGHT_GRAY);
        panel.add(leyenda);

        JLabel id_cliente = new JLabel("ID Cliente:");
        caractLabel(id_cliente);
        id_cliente.setBounds(50, 50, 100, 30);
        panel.add(id_cliente);

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

        JLabel direccion = new JLabel("Direccion:");
        caractLabel(direccion);
        direccion.setBounds(330, 120, 100, 30);
        panel.add(direccion);

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

        dir = new JTextField();
        caractText(dir);
        dir.setBounds(330, 155, 225, 25);
        panel.add(dir);

        JButton registrar = new JButton(new ImageIcon("programa/images/RegisClient.png"));
        caractBoton(registrar);
        registrar.setBounds(350, 210, 125, 125);
        panel.add(registrar);
        registrar.addActionListener(this);
    }

 //============================================================================================================================

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        try {
            if (!id.getText().equals("") && !nom.getText().equals("") && !ema.getText().equals("") && !tel.getText().equals("") && !dir.getText().equals("")) {
                int id_us = Integer.parseInt(id.getText());
                String nombre_us = nom.getText();
                String email_us = ema.getText();
                String telefono_us = tel.getText();
                String direccion_us = dir.getText();
                String regis_por = nombreUser();

                MySQLClientes SQLCliente = new MySQLClientes();
                SQLCliente.ingresarCliente(id_us, nombre_us, email_us, telefono_us, direccion_us, regis_por);

                id.setBorder(new LineBorder(Color.BLACK, 2));
                nom.setBorder(new LineBorder(Color.BLACK, 2));
                ema.setBorder(new LineBorder(Color.BLACK, 2));
                tel.setBorder(new LineBorder(Color.BLACK, 2));
                dir.setBorder(new LineBorder(Color.BLACK, 2));

                id.setText("");
                nom.setText("");
                ema.setText("");
                tel.setText("");
                dir.setText("");

                JOptionPane.showMessageDialog(null, "Cliente Ingresado Correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos.");

                id.setBorder(new LineBorder(Color.RED, 2));
                nom.setBorder(new LineBorder(Color.RED, 2));
                ema.setBorder(new LineBorder(Color.RED, 2));
                tel.setBorder(new LineBorder(Color.RED, 2));
                dir.setBorder(new LineBorder(Color.RED, 2));
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

    public void caractBoton(JButton b)
    {
        b.setBackground(new Color(30,70,110));
        b.setBorder(new LineBorder(new Color(30,70,110)));
    }
}