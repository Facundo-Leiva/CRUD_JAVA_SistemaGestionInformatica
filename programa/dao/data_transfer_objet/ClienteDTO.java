package programa.dao.data_transfer_objet;

public class ClienteDTO 
{
    private String nombre_cliente, email_clinte, tel_cliente, dir_cliente, ultima_modificacion;

 //=============================================================================================================================

    public String getUltima_modificacion() {
        return ultima_modificacion;
    }
    public void setUltima_modificacion(String ultima_modificacion) {
        this.ultima_modificacion = ultima_modificacion;
    }

    public String getDir_cliente() {
        return dir_cliente;
    }
    public void setDir_cliente(String dir_cliente) {
        this.dir_cliente = dir_cliente;
    }

    public String getTel_cliente() {
        return tel_cliente;
    }
    public void setTel_cliente(String tel_cliente) {
        this.tel_cliente = tel_cliente;
    }

    public String getEmail_clinte() {
        return email_clinte;
    }
    public void setEmail_clinte(String email_clinte) {
        this.email_clinte = email_clinte;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }
    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

 //=============================================================================================================================
}