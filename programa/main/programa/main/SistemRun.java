package programa.main;

import programa.ui.LoginScreen;

public class SistemRun 
{
    public static void main(String[] args) 
    {
        java.awt.EventQueue.invokeLater(() -> new LoginScreen().setVisible(true));
    }
}