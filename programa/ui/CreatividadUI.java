package programa.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import programa.dao.data_transfer_objet.UsuariosDTO;
import programa.dao.implementation.MySQLClientes;
import programa.dao.implementation.MySQLEquipos;
import programa.dao.implementation.MySQLUsuarios;

public class CreatividadUI extends JFrame implements ActionListener
{
    private final JTextField id_textField;
    private final JComboBox<String> opciones;
    private final JButton eliminar;

    public CreatividadUI()
    {
        caractFrame();
        setTitle("Eliminar Elemento - Base de Datos - Sesion de " + nombreUser());

        Image im = Toolkit.getDefaultToolkit().getImage("programa/images/Creativity.png");
        setIconImage(im);

        JPanel panel = new JPanel();
        caractPanel(panel);
        add(panel);

     //============================================================================================================================

        JLabel titulo = new JLabel("Eliminar Elemento BS", SwingConstants.CENTER);
        titulo.setFont(new Font("", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(175, 15, 250, 30);
        panel.add(titulo);

        JLabel pregunta = new JLabel("¿De que tabla deseas Eliminar?", SwingConstants.CENTER);
        caractLabel(pregunta);
        pregunta.setBounds(175, 80, 250, 30);
        panel.add(pregunta);

        opciones = new JComboBox<>();
        caractCombo(opciones);
        opciones.setBounds(225, 125, 150, 25);
        opciones.addItem("");
        opciones.addItem("Usuarios");
        opciones.addItem("Clientes");
        opciones.addItem("Equipos");
        panel.add(opciones);

        JLabel id = new JLabel("ID del Elemento", SwingConstants.CENTER);
        caractLabel(id);
        id.setBounds(175, 170, 250, 30);
        panel.add(id);

        id_textField = new JTextField();
        caractText(id_textField);
        id_textField.setBounds(225, 210, 150, 25);
        panel.add(id_textField);

        eliminar = new JButton("Eliminar");
        caractBoton(eliminar);
        eliminar.setBounds(250, 260, 100, 25);
        panel.add(eliminar);
        eliminar.addActionListener(this);
    }
    
 //============================================================================================================================

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        String seleccion = (String) opciones.getSelectedItem();

        try {
            if (e.getSource() == eliminar) {
                assert seleccion != null; //Hacemos que siempre sea verdadero, para evitar errores.

                if (!seleccion.equals("") && !id_textField.getText().equals("")) {
                    opciones.setBorder(new LineBorder(Color.BLACK, 2));
                    id_textField.setBorder(new LineBorder(Color.BLACK, 2));

                    int id_seleccionada = Integer.parseInt(id_textField.getText());

                    if (seleccion.equalsIgnoreCase("Usuarios")) {
                        MySQLUsuarios usuario = new MySQLUsuarios();
                        usuario.eliminarUsuario(id_seleccionada);
                    } else if (seleccion.equalsIgnoreCase("Clientes")) {
                        MySQLClientes cliente = new MySQLClientes();
                        cliente.eliminarCliente(id_seleccionada);
                    } else if (seleccion.equalsIgnoreCase("Equipos")) {
                        MySQLEquipos equipo = new MySQLEquipos();
                        equipo.eliminarEquipo(id_seleccionada);
                    }

                    JOptionPane.showMessageDialog(null, "Elemento Eliminado Correctamente");

                    opciones.setSelectedIndex(0);
                    id_textField.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, completa TODOS los campos.");

                    opciones.setBorder(new LineBorder(Color.RED, 2));
                    id_textField.setBorder(new LineBorder(Color.RED, 2));
                }
            }
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "El campo ID del Elemento solo acepta numeros.");

            id_textField.setBorder(new LineBorder(Color.RED, 2));
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
        setSize(600, 350);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
    }   

    public void caractPanel(JPanel p)
    {
        p.setLayout(null);
        p.setSize(600, 350);
        p.setBackground(new Color(30,70,110));
    }

    public void caractLabel(JLabel l)
    {
        l.setFont(new Font("", Font.BOLD, 16));
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
        b.setBackground(new Color(70,185,250));
        b.setBorder(new LineBorder(Color.BLACK, 2));
        b.setForeground(Color.BLACK);
        b.setFont(new Font("", Font.BOLD, 16));
        b.setBorder(new LineBorder(Color.BLACK, 2));
    }
}