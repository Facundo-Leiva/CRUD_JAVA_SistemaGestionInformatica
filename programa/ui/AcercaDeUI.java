package programa.ui;

import java.awt.*;
import javax.swing.*;

public class AcercaDeUI extends JFrame
{
    public AcercaDeUI()
    {
        caractFrame();
        setTitle("Acerca del Sistema");

        Image im = Toolkit.getDefaultToolkit().getImage("programa/images/acercaDe.png");
        setIconImage(im);

        JPanel panel = new JPanel();
        caractPanel(panel);
        add(panel);

     //======================================================================================

        JLabel label1 = new JLabel("Sistema Creado por Facundo Leiva", SwingConstants.CENTER);
        caractLabel(label1);
        label1.setBounds(100, 50, 400, 30);
        panel.add(label1);

        JLabel label2 = new JLabel("Todos los Derechos Reservados", SwingConstants.CENTER);
        caractLabel(label2);
        label2.setBounds(100, 125, 400, 30);
        panel.add(label2);

        JLabel label3 = new JLabel("Facundo Leiva © - Febrero / 2023", SwingConstants.CENTER);
        label3.setFont(new Font("", Font.BOLD, 16));
        label3.setForeground(Color.LIGHT_GRAY);
        label3.setBounds(100, 250, 400, 30);
        panel.add(label3);
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
        l.setFont(new Font("", Font.BOLD, 20));
        l.setForeground(Color.WHITE);
    }
}