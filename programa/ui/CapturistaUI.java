package programa.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import programa.dao.data_transfer_objet.UsuariosDTO;
import programa.dao.implementation.MySQLClientes;
import programa.dao.implementation.MySQLUsuarios;

public class CapturistaUI extends JFrame implements ActionListener
{
    private final JButton registrar, gestionar, imprimir;

    MySQLClientes cliente = new MySQLClientes();

    public CapturistaUI()
    {
        caractFrame();
        setTitle("Capturista - Sesion de " + nombreUser());

        Image im = Toolkit.getDefaultToolkit().getImage("programa/images/Capturista.png");
        setIconImage(im);

        JPanel panel = new JPanel();
        caractPanel(panel);
        add(panel);

     //============================================================================================================================

        JLabel mensaje = new JLabel();
        mensaje.setText("Bienvenido " + nombreUser());
        mensaje.setFont(new Font("", Font.BOLD, 16));
        mensaje.setForeground(Color.WHITE);
        mensaje.setBounds(20, 5, 400, 30);
        panel.add(mensaje);

        JLabel leyenda = new JLabel("Creado por Facundo Leiva", SwingConstants.CENTER);
        leyenda.setBounds(200, 235, 150, 30);
        leyenda.setFont(new Font("", Font.BOLD, 11));
        leyenda.setForeground(Color.LIGHT_GRAY);
        panel.add(leyenda);

        JLabel regCliente = new JLabel("Registrar Cliente", SwingConstants.CENTER);
        caractLabel(regCliente);
        regCliente.setBounds(25, 180, 150, 30);
        panel.add(regCliente);

        JLabel gestCliente = new JLabel("Gestionar Cliente", SwingConstants.CENTER);
        caractLabel(gestCliente);
        gestCliente.setBounds(195, 180, 150, 30);
        panel.add(gestCliente);

        JLabel imprClientes = new JLabel("Imprimir Clientes", SwingConstants.CENTER);
        caractLabel(imprClientes);
        imprClientes.setBounds(360, 180, 150, 30);
        panel.add(imprClientes);

        registrar = new JButton(new ImageIcon("programa/images/RegisClient.png"));
        caractBoton(registrar);
        registrar.setBounds(42, 50, 125, 125);
        panel.add(registrar);
        registrar.addActionListener(this);

        gestionar = new JButton(new ImageIcon("programa/images/GestClient.png"));
        caractBoton(gestionar);
        gestionar.setBounds(208, 50, 125, 125);
        panel.add(gestionar);
        gestionar.addActionListener(this);

        imprimir = new JButton(new ImageIcon("programa/images/ImprClient.png"));
        caractBoton(imprimir);
        imprimir.setBounds(375, 50, 125, 125);
        panel.add(imprimir);
        imprimir.addActionListener(this);
    }

 //============================================================================================================================

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == registrar)
        {
            new RegistrarClienteUI().setVisible(true);
        }

        if (e.getSource() == gestionar)
        {
            new GestionarClientesUI().setVisible(true);
        }

        if (e.getSource() == imprimir)
        {
            Document documento = new Document();

            try 
            {
                String ruta = System.getProperty("user.home");
                PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Listado de Clientes.pdf"));
 
                
                documento.open();

                documento.add(ClientesPDF.imagenPDF());
                documento.add(ClientesPDF.parrafoPDF());
                documento.add(ClienteEquipoPDF.parrafoEspacioPDF());

                PdfPTable tabla = cliente.crearReporteClientes();
                documento.add(tabla);

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
        setSize(550, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
    }   

    public void caractPanel(JPanel p)
    {
        p.setLayout(null);
        p.setSize(550, 300);
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