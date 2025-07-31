package programa.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Objects;

import programa.dao.data_transfer_objet.UsuariosDTO;
import programa.dao.implementation.MySQLEquipos;
import programa.dao.implementation.MySQLUsuarios;

public class RegistrarEquipoUI extends JFrame implements ActionListener
{
    private final JTextField id, model, num_ser;
    private final JComboBox<String> tipo, marc;
    private final JTextArea repor_obser;

    public RegistrarEquipoUI()
    {
        caractFrame();
        setTitle("Registrar Nuevo Equipo para " + GestionarClientesUI.user_name);

        Image im = Toolkit.getDefaultToolkit().getImage("programa/images/GestEquip.png");
        setIconImage(im);

        JPanel panel = new JPanel();
        caractPanel(panel);
        add(panel);

     //============================================================================================================================

        JLabel titulo = new JLabel();
        titulo.setText("Registro de Equipo");
        titulo.setFont(new Font("", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(225, 5, 250, 30);
        panel.add(titulo);

        JLabel leyenda = new JLabel("Creado por Facundo Leiva", SwingConstants.CENTER);
        leyenda.setBounds(275, 337, 150, 30);
        leyenda.setFont(new Font("", Font.BOLD, 12));
        leyenda.setForeground(Color.LIGHT_GRAY);
        panel.add(leyenda);

        JLabel nombre = new JLabel("Nombre del cliente:");
        caractLabel(nombre);
        nombre.setBounds(40, 50, 150, 30);
        panel.add(nombre);

        JButton nombre_cliente = new JButton(GestionarClientesUI.user_name);
        caractBoton(nombre_cliente);
        nombre_cliente.setBounds(40, 85, 225, 25);
        panel.add(nombre_cliente);

        JLabel id_equipo = new JLabel("ID del equipo:");
        caractLabel(id_equipo);
        id_equipo.setBounds(40, 110, 100, 30);
        panel.add(id_equipo);

        id = new JTextField();
        caractText(id);
        id.setBounds(40, 145, 225, 25);
        panel.add(id);

        JLabel modelo = new JLabel("Modelo:");
        caractLabel(modelo);
        modelo.setBounds(40, 170, 100, 30);
        panel.add(modelo);

        model = new JTextField();
        caractText(model);
        model.setBounds(40, 205, 225, 25);
        panel.add(model);

        JLabel num_serie = new JLabel("Numero de serie:");
        caractLabel(num_serie);
        num_serie.setBounds(40, 230, 150, 30);
        panel.add(num_serie);
        
        num_ser = new JTextField();
        caractText(num_ser);
        num_ser.setBounds(40, 265, 225, 25);
        panel.add(num_ser);

        JLabel tipo_equipo = new JLabel("Tipo de Equipo:");
        caractLabel(tipo_equipo);
        tipo_equipo.setBounds(330, 50, 150, 30);
        panel.add(tipo_equipo);

        JLabel marca = new JLabel("Marca:");
        caractLabel(marca);
        marca.setBounds(500, 50, 100, 30);
        panel.add(marca);

        tipo = new JComboBox<>();
        caractCombo(tipo);
        tipo.addItem("");
        tipo.addItem("Laptop");
        tipo.addItem("Desktop");
        tipo.addItem("Impresora");
        tipo.addItem("Multifuncional");
        tipo.setBounds(330, 85, 150, 25);
        panel.add(tipo);

        marc = new JComboBox<>();
        caractCombo(marc);
        marc.addItem("");
        marc.addItem("Apple");
        marc.addItem("Dell");
        marc.addItem("Lenovo");
        marc.addItem("HP");
        marc.addItem("Asus");
        marc.addItem("Acer");
        marc.addItem("Samsung");
        marc.addItem("MSI");
        marc.setBounds(500, 85, 150, 25);
        panel.add(marc);

        JLabel reportes_obser = new JLabel("Daño reportado y observaciones:");
        caractLabel(reportes_obser);
        reportes_obser.setBounds(330, 120, 300, 30);
        panel.add(reportes_obser);

        repor_obser = new JTextArea();
        caractArea(repor_obser);
        repor_obser.setBounds(330, 155, 320, 125);
        panel.add(repor_obser);

        JButton registrar = new JButton("Registrar Equipo");
        caractBoton(registrar);
        registrar.setBounds(330, 295, 150, 30);
        panel.add(registrar);
        registrar.addActionListener(this);
    }

 //============================================================================================================================

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        try {
            if (!id.getText().equals("") && !model.getText().equals("") && !num_ser.getText().equals("") && !Objects.equals(tipo.getSelectedItem(), "") && !Objects.equals(marc.getSelectedItem(), "")) {
                Calendar calendario = Calendar.getInstance();
                String dia = Integer.toString(calendario.get(Calendar.DATE));
                String mes = Integer.toString(calendario.get(Calendar.MONTH));
                String annio = Integer.toString(calendario.get(Calendar.YEAR));

                int id_eq = Integer.parseInt(id.getText());
                int id_cl = GestionarClientesUI.user_id;
                String tipo_eq = (String) tipo.getSelectedItem();
                String marca = (String) marc.getSelectedItem();
                String modelo = model.getText();
                String num_serie = num_ser.getText();
                String obser = repor_obser.getText();
                String estatus = "Nuevo Ingreso";
                String ult_modif = nombreUser();
                String coment_tec = "";
                String revis_tec = "";

                MySQLEquipos equipo = new MySQLEquipos();
                equipo.ingresarEquipo(id_eq, id_cl, tipo_eq, marca, modelo, num_serie, dia, mes, annio, obser, estatus, ult_modif, coment_tec, revis_tec);

                id.setBorder(new LineBorder(Color.BLACK, 2));
                tipo.setBorder(new LineBorder(Color.BLACK, 2));
                marc.setBorder(new LineBorder(Color.BLACK, 2));
                model.setBorder(new LineBorder(Color.BLACK, 2));
                num_ser.setBorder(new LineBorder(Color.BLACK, 2));
                repor_obser.setBorder(new LineBorder(Color.BLACK, 2));

                id.setText("");
                tipo.setSelectedIndex(0);
                marc.setSelectedIndex(0);
                model.setText("");
                num_ser.setText("");
                repor_obser.setText("");

                JOptionPane.showMessageDialog(null, "Equipo Ingresado Correctamente.");

                new InformacionClienteUI().setVisible(true);

                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos.");

                id.setBorder(new LineBorder(Color.RED, 2));
                model.setBorder(new LineBorder(Color.RED, 2));
                num_ser.setBorder(new LineBorder(Color.RED, 2));
                tipo.setBorder(new LineBorder(Color.RED, 2));
                marc.setBorder(new LineBorder(Color.RED, 2));
                repor_obser.setBorder(new LineBorder(Color.RED, 2));
            }
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Los campos ID y NUMERO DE SERIE solo aceptan numeros.");

            id.setBorder(new LineBorder(Color.RED, 2));
            num_ser.setBorder(new LineBorder(Color.RED, 2));
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
        setSize(700, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
    }   

    public void caractPanel(JPanel p)
    {
        p.setLayout(null);
        p.setSize(700, 400);
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

    public void caractArea(JTextArea a)
    {
        a.setBackground(new Color(70,185,250));
        a.setBorder(new LineBorder(Color.BLACK, 2));
        a.setFont(new Font("", Font.BOLD, 14));
        a.setForeground(Color.BLACK);
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