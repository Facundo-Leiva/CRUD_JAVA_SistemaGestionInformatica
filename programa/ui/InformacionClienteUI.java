package programa.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import programa.dao.data_transfer_objet.ClienteDTO;
import programa.dao.data_transfer_objet.UsuariosDTO;
import programa.dao.implementation.MySQLClientes;
import programa.dao.implementation.MySQLEquipos;
import programa.dao.implementation.MySQLUsuarios;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class InformacionClienteUI extends JFrame implements ActionListener
{
    private final JTextField nom, ema, tel, dir;
    private final JButton actualizar_cliente, registrar_equipo, imprimir;
    private final JTable tabla;

    public static int id_equipo = 0;

    MySQLClientes cliente = new MySQLClientes();
    ClienteDTO cliente_DTO = cliente.buscarCliente(GestionarClientesUI.user_id);

    MySQLEquipos equipo = new MySQLEquipos();

    public InformacionClienteUI()
    {
        caractFrame();
        setTitle("Informacion del Cliente " + GestionarClientesUI.user_name + " - Sesion de " + nombreUser());

        Image im = Toolkit.getDefaultToolkit().getImage("programa/images/GestClient.png");
        setIconImage(im);

        JPanel panel = new JPanel();
        caractPanel(panel);
        add(panel);

     //============================================================================================================================

        JLabel titulo = new JLabel();
        titulo.setText("Informacion del Cliente " + GestionarClientesUI.user_name);
        titulo.setFont(new Font("", Font.BOLD, 18));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(200, 5, 300, 30);
        panel.add(titulo);

        JLabel leyenda = new JLabel("Creado por Facundo Leiva", SwingConstants.CENTER);
        leyenda.setBounds(275, 340, 150, 30);
        leyenda.setFont(new Font("", Font.BOLD, 12));
        leyenda.setForeground(Color.LIGHT_GRAY);
        panel.add(leyenda);

        JLabel nombre = new JLabel("Nombre:");
        caractLabel(nombre);
        nombre.setBounds(30, 50, 100, 30);
        panel.add(nombre);

        JLabel email = new JLabel("Email:");
        caractLabel(email);
        email.setBounds(30, 110, 100, 30);
        panel.add(email);

        JLabel telefono = new JLabel("Telefono:");
        caractLabel(telefono);
        telefono.setBounds(30, 170, 100, 30);
        panel.add(telefono);

        JLabel direccion = new JLabel("Direccion:");
        caractLabel(direccion);
        direccion.setBounds(30, 230, 100, 30);
        panel.add(direccion);

        JLabel ult_mod = new JLabel("Ultima Modificacion por:");
        caractLabel(ult_mod);
        ult_mod.setBounds(30, 290, 200, 30);
        panel.add(ult_mod);

        nom = new JTextField();
        caractText(nom);
        nom.setBounds(30, 82, 225, 25);
        panel.add(nom);
        nom.setText(cliente_DTO.getNombre_cliente());

        ema = new JTextField();
        caractText(ema);
        ema.setBounds(30, 142, 225, 25);
        panel.add(ema);
        ema.setText(cliente_DTO.getEmail_clinte());

        tel = new JTextField();
        caractText(tel);
        tel.setBounds(30, 202, 225, 25);
        panel.add(tel);
        tel.setText(cliente_DTO.getTel_cliente());

        dir = new JTextField();
        caractText(dir);
        dir.setBounds(30, 262, 225, 25);
        panel.add(dir);
        dir.setText(cliente_DTO.getDir_cliente());

        JButton ult_mod_por = new JButton(cliente_DTO.getUltima_modificacion());
        caractBoton(ult_mod_por);
        ult_mod_por.setBounds(30, 322, 150, 25);
        panel.add(ult_mod_por);

        actualizar_cliente = new JButton("Actualizar Cliente");
        caractBoton(actualizar_cliente);
        actualizar_cliente.setBounds(360, 270, 150, 25);
        panel.add(actualizar_cliente);
        actualizar_cliente.addActionListener(this);

        registrar_equipo = new JButton("Registrar Equipo");
        caractBoton(registrar_equipo);
        registrar_equipo.setBounds(360, 310, 150, 25);
        panel.add(registrar_equipo);
        registrar_equipo.addActionListener(this);

        imprimir = new JButton(new ImageIcon("programa/images/ImprClient2.png"));
        imprimir.setBackground(new Color(30,70,110));
        imprimir.setBorder(new LineBorder(new Color(30,70,110)));
        imprimir.setBounds(540, 270, 75, 75);
        panel.add(imprimir);
        imprimir.addActionListener(this);

        tabla = equipo.tablaEquiposIDCliente(GestionarClientesUI.user_id);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(275, 60, 400, 200);
        panel.add(scroll);

        tabla.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                int fila_point = tabla.rowAtPoint(e.getPoint());
                int columna_point = 0;

                if (fila_point > -1)
                {
                    id_equipo = (int) tabla.getValueAt(fila_point, columna_point);

                    new InformacionEquipoUI().setVisible(true);

                    dispose();
                }
            }
        });
    }

 //============================================================================================================================

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == actualizar_cliente)
        {
            if (!nom.getText().equals("") && !ema.getText().equals("") && !tel.getText().equals("") && !dir.getText().equals("")) 
            {
                try 
                {
                    cliente.actualizarCliente(GestionarClientesUI.user_id, nom.getText(), ema.getText(), tel.getText(), dir.getText(), nombreUser());

                    JOptionPane.showMessageDialog(null, "Cliente Actualizado Correctamente.");

                    nom.setBorder(new LineBorder(Color.RED, 2));
                    ema.setBorder(new LineBorder(Color.RED, 2));
                    tel.setBorder(new LineBorder(Color.RED, 2));
                    dir.setBorder(new LineBorder(Color.RED, 2));

                    new GestionarClientesUI().setVisible(true);

                    dispose();
                } 
                catch (Exception ex) 
                {
                    JOptionPane.showMessageDialog(null, "Error al actualizar Cliente, contacte con el Administrador.");
                }
            } 
            else 
            {
                JOptionPane.showMessageDialog(null, "Por favor, complete TODOS los campos.");

                nom.setBorder(new LineBorder(Color.RED, 2));
                ema.setBorder(new LineBorder(Color.RED, 2));
                tel.setBorder(new LineBorder(Color.RED, 2));
                dir.setBorder(new LineBorder(Color.RED, 2));
            }
        }

        if (e.getSource() == registrar_equipo)
        {
            new RegistrarEquipoUI().setVisible(true);

            dispose();
        }

        if (e.getSource() == imprimir)
        {
            Document documento = new Document();

            try 
            {
                String ruta = System.getProperty("user.home");
                PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/" + GestionarClientesUI.user_name + ".pdf"));
 
                
                documento.open();

                documento.add(ClienteEquipoPDF.imagenPDF());
                documento.add(ClienteEquipoPDF.parrafoPDF());

                PdfPTable tabla1 = cliente.crearReporteClienteParticular(GestionarClientesUI.user_id);
                documento.add(tabla1);

                documento.add(ClienteEquipoPDF.parrafoEspacioPDF());

                PdfPTable tabla2 = equipo.crearReporte(GestionarClientesUI.user_id);
                documento.add(tabla2);

                documento.close();  

                JOptionPane.showMessageDialog(null, "Reporte creado Exitosamente");
            } 
            catch (DocumentException | HeadlessException | FileNotFoundException ex)
            {
                JOptionPane.showMessageDialog(null, "Error!: " + ex);
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

    public void caractBoton(JButton b)
    {
        b.setFont(new Font("", Font.BOLD, 15));
        b.setForeground(Color.BLACK);
        b.setBackground(new Color(70,185,250));
        b.setBorder(new LineBorder(Color.BLACK, 2));
    }
}