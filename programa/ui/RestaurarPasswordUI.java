package programa.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import programa.dao.implementation.MySQLUsuarios;

public class RestaurarPasswordUI extends JFrame implements ActionListener
{
    private final JPasswordField nuev, confir;
    private final JButton restaurar;

    public RestaurarPasswordUI()
    {
        caractFrame();
        setTitle("Cambio de Password para " + GestionarUsuariosUI.user_name);

        Image im = Toolkit.getDefaultToolkit().getImage("programa/images/GestUser.png");
        setIconImage(im);

        JPanel panel = new JPanel();
        caractPanel(panel);
        add(panel);

     //============================================================================================================================

        JLabel titulo = new JLabel("Cambio de Password");
        caractLabel(titulo);
        titulo.setFont(new Font("", Font.BOLD, 16));
        titulo.setBounds(75, 10, 250, 30);
        panel.add(titulo);

        JLabel nuevo = new JLabel("Nuevo Password:");
        caractLabel(nuevo);
        nuevo.setBounds(50, 50, 150, 30);
        panel.add(nuevo);

        JLabel confirmar = new JLabel("Confirmar Password:");
        caractLabel(confirmar);
        confirmar.setBounds(50, 110, 150, 30);
        panel.add(confirmar);

        nuev = new JPasswordField();
        caractText(nuev);
        nuev.setBounds(50, 85, 250, 25);
        panel.add(nuev);

        confir = new JPasswordField();
        caractText(confir);
        confir.setBounds(50, 145, 250, 25);
        panel.add(confir);

        restaurar = new JButton("Restaurar Password");
        caractBoton(restaurar);
        restaurar.setBounds(50, 200, 150, 25);
        panel.add(restaurar);
        restaurar.addActionListener(this);
    }

 //============================================================================================================================

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == restaurar)
        {
            char[] arrayNuev = nuev.getPassword();
            char[] arrayConfir = confir.getPassword();

            String string_nuev = new String(arrayNuev);
            String string_confir = new String(arrayConfir);

            if (!string_nuev.equals("") && !string_confir.equals("") && string_nuev.equals(string_confir))
            {
                MySQLUsuarios usuarios = new MySQLUsuarios();

                usuarios.cambiarPassword(GestionarUsuariosUI.user_id, string_nuev);

                nuev.setBorder(new LineBorder(Color.BLACK, 2));
                confir.setBorder(new LineBorder(Color.BLACK, 2));

                JOptionPane.showMessageDialog(null, "Password Actualizada Correctamente.");

                dispose();
            }
            else 
            {
                JOptionPane.showMessageDialog(null, "Por favor, llena todos los Campos y verifica que sean IGUALES.");

                nuev.setBorder(new LineBorder(Color.RED, 2));
                confir.setBorder(new LineBorder(Color.RED, 2));
            }
        }
    }

 //============================================================================================================================

    public void caractFrame()
    {
        setSize(400, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
    }   

    public void caractPanel(JPanel p)
    {
        p.setLayout(null);
        p.setSize(400, 300);
        p.setBackground(new Color(30,70,110));
    }

    public void caractLabel(JLabel l)
    {
        l.setFont(new Font("", Font.BOLD, 14));
        l.setForeground(Color.WHITE);
    }

    public void caractText(JPasswordField p)
    {
        p.setBackground(new Color(70,185,250));
        p.setBorder(new LineBorder(Color.BLACK, 2));
        p.setFont(new Font("", Font.BOLD, 14));
        p.setForeground(Color.BLACK);
    }

    public void caractBoton(JButton b)
    {
        b.setFont(new Font("", Font.BOLD, 15));
        b.setForeground(Color.BLACK);
        b.setBackground(new Color(70,185,250));
        b.setBorder(new LineBorder(Color.BLACK, 2));
    }
}