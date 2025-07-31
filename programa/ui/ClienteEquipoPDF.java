package programa.ui;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.FontFactory;
import java.io.IOException;
import javax.swing.JOptionPane;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;

public class ClienteEquipoPDF 
{
    public static Image imagenPDF() throws BadElementException 
    {
        try 
        {
            Image header = Image.getInstance("programa/images/GestEquip.png"); 
            header.scaleToFit(125, 125); 
            header.setAlignment(Chunk.ALIGN_CENTER); 

            return header;
        } 
        catch (IOException e) 
        {
            JOptionPane.showMessageDialog(null, "Error en la imagen!: " + e);
        }
        
        return null;
    }

    public static Paragraph parrafoPDF()
    {
        try 
        {
            Paragraph parrafo = new Paragraph(); 
            parrafo.setAlignment(Paragraph.ALIGN_CENTER); 
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Informacion del Cliente\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 15, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("&&\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Equipos Registrados\n\n\n");

            return parrafo;
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, "Error en la imagen!: " + e);
        }

        return null;
    }

    public static Paragraph parrafoEspacioPDF()
    {
        try 
        {
            Paragraph parrafo = new Paragraph(); 
            parrafo.setAlignment(Paragraph.ALIGN_CENTER); 
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("\n\n");

            return parrafo;
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, "Error en la imagen!: " + e);
        }

        return null;
    }
}