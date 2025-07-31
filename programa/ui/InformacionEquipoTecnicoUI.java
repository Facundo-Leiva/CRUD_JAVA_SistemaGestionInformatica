package programa.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import programa.dao.data_transfer_objet.ClienteDTO;
import programa.dao.data_transfer_objet.EquiposDTO;
import programa.dao.data_transfer_objet.UsuariosDTO;
import programa.dao.implementation.MySQLClientes;
import programa.dao.implementation.MySQLEquipos;
import programa.dao.implementation.MySQLUsuarios;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class InformacionEquipoTecnicoUI extends JFrame implements ActionListener
{
    private final JTextField model, num_ser;
    private final JComboBox<String> tipo, marc, estat;
    private final JTextArea repor_obser, com_act;
    private final JButton actualizar;

    MySQLEquipos equipo = new MySQLEquipos();
    EquiposDTO equipDto = equipo.buscarEquipo(GestionarEquiposUI.id_equip);

    MySQLClientes cliente = new MySQLClientes();
    ClienteDTO clientDto = cliente.buscarCliente(equipDto.getId_cliente());

    public InformacionEquipoTecnicoUI()
    {
        caractFrame();
        setTitle("Equipo del Cliente " + GestionarClientesUI.user_name);

        Image im = Toolkit.getDefaultToolkit().getImage("programa/images/GestEquip.png");
        setIconImage(im);

        JPanel panel = new JPanel();
        caractPanel(panel);
        add(panel);

     //============================================================================================================================

        JLabel titulo = new JLabel("Informacion del Equipo", SwingConstants.CENTER);
        titulo.setFont(new Font("", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(200, 5, 300, 30);
        panel.add(titulo);

        JLabel leyenda = new JLabel("Creado por Facundo Leiva", SwingConstants.CENTER);
        leyenda.setFont(new Font("", Font.BOLD, 13));
        leyenda.setForeground(Color.LIGHT_GRAY);
        leyenda.setBounds(200, 417, 300, 30);
        panel.add(leyenda);

        JLabel nombre = new JLabel("Nombre del Cliente:");
        caractLabel(nombre);
        nombre.setBounds(40, 50, 150, 30);
        panel.add(nombre);

        JLabel modelo = new JLabel("Modelo:");
        caractLabel(modelo);
        modelo.setBounds(40, 110, 150, 30);
        panel.add(modelo);

        JLabel num_serie = new JLabel("Numero de Serie:");
        caractLabel(num_serie);
        num_serie.setBounds(40, 170, 150, 30);
        panel.add(num_serie);

        JLabel tipo_equipo = new JLabel("Tipo de Equipo:");
        caractLabel(tipo_equipo);
        tipo_equipo.setBounds(40, 230, 150, 30);
        panel.add(tipo_equipo);

        JLabel marca = new JLabel("Marca:");
        caractLabel(marca);
        marca.setBounds(40, 290, 150, 30);
        panel.add(marca);

        JLabel ult_modificacion = new JLabel("Ultima Modificacion por:");
        caractLabel(ult_modificacion);
        ult_modificacion.setBounds(40, 350, 250, 30);
        panel.add(ult_modificacion);

        JLabel fecha_ingreso = new JLabel("Fecha de Ingreso:");
        caractLabel(fecha_ingreso);
        fecha_ingreso.setBounds(330, 50, 150, 30);
        panel.add(fecha_ingreso);

        JLabel estatus = new JLabel("Estatus:");
        caractLabel(estatus);
        estatus.setBounds(510, 50, 150, 30);
        panel.add(estatus);

        JLabel reportes_obser = new JLabel("Daño reportado y observaciones:");
        caractLabel(reportes_obser);
        reportes_obser.setBounds(330, 110, 250, 30);
        panel.add(reportes_obser);

        JLabel com_act_tec = new JLabel("Comentarios y actualizaciones del Tecnico:");
        caractLabel(com_act_tec);
        com_act_tec.setBounds(330, 230, 350, 30);
        panel.add(com_act_tec);

        model = new JTextField(equipDto.getModelo());
        caractText(model);
        model.setBounds(40, 145, 225, 25);
        model.setEditable(false);
        panel.add(model);

        num_ser = new JTextField(equipDto.getNum_serie());
        caractText(num_ser);
        num_ser.setBounds(40, 205, 225, 25);
        num_ser.setEditable(false);
        panel.add(num_ser);

        tipo = new JComboBox<>();
        tipo.addItem("");
        tipo.addItem("Laptop");
        tipo.addItem("Desktop");
        tipo.addItem("Impresora");
        tipo.addItem("Multifuncional");
        tipo.setSelectedItem(equipDto.getTipo_equipo());
        caractCombo(tipo);
        tipo.setBounds(40, 265, 150, 25);
        tipo.setEnabled(false);
        panel.add(tipo);

        marc = new JComboBox<>();
        marc.addItem("");
        marc.addItem("Apple");
        marc.addItem("Dell");
        marc.addItem("Lenovo");
        marc.addItem("HP");
        marc.addItem("Asus");
        marc.addItem("Acer");
        marc.addItem("Samsung");
        marc.addItem("MSI");
        marc.setSelectedItem(equipDto.getMarca());
        caractCombo(marc);
        marc.setBounds(40, 325, 150, 25);
        marc.setEnabled(false);
        panel.add(marc);

        estat = new JComboBox<>();
        estat.addItem("Nuevo Ingreso");
        estat.addItem("No Reparado");
        estat.addItem("En Revision");
        estat.addItem("Reparado");
        estat.addItem("Entregado");
        estat.setSelectedItem(equipDto.getEstatus());
        caractCombo(estat);
        estat.setBounds(510, 85, 150, 25);
        panel.add(estat);

        repor_obser = new JTextArea(equipDto.getObservaciones());
        caractArea(repor_obser);
        repor_obser.setBounds(330, 145, 300, 80);
        repor_obser.setEditable(false);
        panel.add(repor_obser);

        com_act = new JTextArea(equipDto.getComent_tecnico() + "\n\n" + equipDto.getRev_tec_de());
        com_act.setEditable(true);
        caractArea(com_act);
        com_act.setBounds(330, 265, 300, 100);
        panel.add(com_act);

        JButton nombre_cliente = new JButton(clientDto.getNombre_cliente());
        caractBoton(nombre_cliente);
        nombre_cliente.setBounds(40, 85, 150, 25);
        panel.add(nombre_cliente);

        JButton ult_mod = new JButton(equipDto.getUltima_modificacion());
        caractBoton(ult_mod);
        ult_mod.setBounds(40, 385, 150, 25);
        panel.add(ult_mod);

        JButton fecha = new JButton(equipDto.getDia_ingreso() + " del " + equipDto.getMes_ingreso() + " del " + equipDto.getAnnio_ingreso());
        caractBoton(fecha);
        fecha.setBounds(330, 85, 150, 25);
        panel.add(fecha);

        actualizar = new JButton("Actualizar Equipo");
        caractBoton(actualizar);
        actualizar.setBounds(330, 380, 150, 30);
        panel.add(actualizar);
        actualizar.addActionListener(this);
    }

 //============================================================================================================================

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == actualizar)
        {
            if (!Objects.equals(tipo.getSelectedItem(), "") && !Objects.equals(marc.getSelectedItem(), "") && !Objects.equals(estat.getSelectedItem(), "") && !model.getText().equals("") && !num_ser.getText().equals("") && !repor_obser.getText().equals(""))
            {
                String tipo_eq = (String) tipo.getSelectedItem();
                String marca_eq = (String) marc.getSelectedItem();
                String estatus_eq = (String) estat.getSelectedItem();

                equipo.actualizarEquipo(GestionarEquiposUI.id_equip, equipDto.getId_cliente(), tipo_eq, marca_eq, model.getText(), num_ser.getText(), equipDto.getDia_ingreso(), equipDto.getMes_ingreso(), equipDto.getAnnio_ingreso(), repor_obser.getText(), estatus_eq, nombreUser(), com_act.getText(), nombreUser());

                model.setBorder(new LineBorder(Color.BLACK, 2));
                num_ser.setBorder(new LineBorder(Color.BLACK, 2));
                tipo.setBorder(new LineBorder(Color.BLACK, 2));
                marc.setBorder(new LineBorder(Color.BLACK, 2));
                estat.setBorder(new LineBorder(Color.BLACK, 2));
                repor_obser.setBorder(new LineBorder(Color.BLACK, 2));

                JOptionPane.showMessageDialog(null, "Equipo actactualizado con Exito.");

                new GestionarEquiposUI().setVisible(true);

                dispose();
            } 
            else 
            {
                JOptionPane.showMessageDialog(null, "Completa todos los Campos.");

                model.setBorder(new LineBorder(Color.RED, 2));
                num_ser.setBorder(new LineBorder(Color.RED, 2));
                tipo.setBorder(new LineBorder(Color.RED, 2));
                marc.setBorder(new LineBorder(Color.RED, 2));
                estat.setBorder(new LineBorder(Color.RED, 2));
                repor_obser.setBorder(new LineBorder(Color.RED, 2));
            }
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
        setSize(700, 480);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
    }   

    public void caractPanel(JPanel p)
    {
        p.setLayout(null);
        p.setSize(700, 480);
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