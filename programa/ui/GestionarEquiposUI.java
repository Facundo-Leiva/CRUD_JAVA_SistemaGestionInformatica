package programa.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;

import programa.dao.data_transfer_objet.UsuariosDTO;
import programa.dao.implementation.MySQLEquipos;
import programa.dao.implementation.MySQLUsuarios;
import java.awt.*;
import java.awt.event.*;

public class GestionarEquiposUI extends JFrame implements ActionListener
{
    private final JComboBox<String> estatus;
    private JTable tabla;
    private final JScrollPane scroll;
    private final JButton mostrar;

    public static int id_equip = 0;

    MySQLEquipos equipo = new MySQLEquipos();

    public GestionarEquiposUI()
    {
        caractFrame();
        setTitle("Equipos Registrados - Sesion de " + nombreUser());

        Image im = Toolkit.getDefaultToolkit().getImage("programa/images/GestEquip.png");
        setIconImage(im);

        JPanel panel = new JPanel();
        caractPanel(panel);
        add(panel);

     //============================================================================================================================

        JLabel titulo = new JLabel();
        titulo.setText("Equipos Registrados");
        titulo.setFont(new Font("", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(30, 5, 250, 30);
        panel.add(titulo);

        JLabel leyenda = new JLabel("Creado por Facundo Leiva", SwingConstants.CENTER);
        leyenda.setBounds(225, 337, 150, 30);
        leyenda.setFont(new Font("", Font.BOLD, 12));
        leyenda.setForeground(Color.LIGHT_GRAY);
        panel.add(leyenda);

        estatus = new JComboBox<>();
        estatus.setBounds(415, 15, 150, 25);
        estatus.setFont(new Font("", Font.BOLD, 14));
        estatus.setForeground(Color.BLACK);
        estatus.setBorder(new LineBorder(Color.BLACK, 2));
        estatus.setBackground(new Color(70,185,250));
        estatus.addItem("Todos");
        estatus.addItem("Nuevo Ingreso");
        estatus.addItem("No Reparado");
        estatus.addItem("En Revision");
        estatus.addItem("Reparado");
        estatus.addItem("Entregado");
        panel.add(estatus);

        tabla = equipo.tablaEquiposEstatusGeneral();
        scroll = new JScrollPane(tabla);
        scroll.setBounds(0, 45, 600, 260);
        panel.add(scroll);

        mostrar = new JButton("Mostrar");
        mostrar.setBounds(415, 315, 150, 30);
        mostrar.setFont(new Font("", Font.BOLD, 16));
        mostrar.setBackground(new Color(70,185,250));
        mostrar.setForeground(Color.BLACK);
        mostrar.setBorder(new LineBorder(Color.BLACK, 2));
        panel.add(mostrar);
        mostrar.addActionListener(this);

        tabla.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                int fila_point = tabla.rowAtPoint(e.getPoint());
                int columna_point = 0;

                if (fila_point > -1)
                {
                    id_equip = (int) tabla.getValueAt(fila_point, columna_point);

                    new InformacionEquipoTecnicoUI().setVisible(true);

                    dispose();
                }
            }
        });
    }

 //============================================================================================================================

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == mostrar)
        {
            String estatus_selected = (String) estatus.getSelectedItem();

            assert estatus_selected != null;

            if (estatus_selected.equals("Todos"))
            {
                tabla = equipo.tablaEquiposEstatusGeneral();

                scroll.setViewportView(tabla);

                tabla.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        int fila_point = tabla.rowAtPoint(e.getPoint());
                        int columna_point = 0;
        
                        if (fila_point > -1)
                        {
                            id_equip = (int) tabla.getValueAt(fila_point, columna_point);
        
                            new InformacionEquipoTecnicoUI().setVisible(true);
        
                            dispose();
                        }
                    }
                });
            } 
            else 
            {
                tabla = equipo.tablaEquiposEstatus(estatus_selected);

                scroll.setViewportView(tabla);

                tabla.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        int fila_point = tabla.rowAtPoint(e.getPoint());
                        int columna_point = 0;
        
                        if (fila_point > -1)
                        {
                            id_equip = (int) tabla.getValueAt(fila_point, columna_point);
        
                            new InformacionEquipoTecnicoUI().setVisible(true);
        
                            dispose();
                        }
                    }
                });
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