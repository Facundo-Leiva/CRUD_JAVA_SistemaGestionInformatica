package programa.dao.data_transfer_objet;

public class EquiposDTO 
{
    private int id_cliente;

    private String tipo_equipo, marca, modelo, num_serie, dia_ingreso, mes_ingreso, annio_ingreso, observaciones, estatus, ultima_modificacion, coment_tecnico, rev_tec_de;

 //=============================================================================================================================

    public String getRev_tec_de() {
        return rev_tec_de;
    }

    public void setRev_tec_de(String rev_tec_de) {
        this.rev_tec_de = rev_tec_de;
    }

    public String getComent_tecnico() {
        return coment_tecnico;
    }

    public void setComent_tecnico(String coment_tecnico) {
        this.coment_tecnico = coment_tecnico;
    }

    public String getUltima_modificacion() {
        return ultima_modificacion;
    }

    public void setUltima_modificacion(String ultima_modificacion) {
        this.ultima_modificacion = ultima_modificacion;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getAnnio_ingreso() {
        return annio_ingreso;
    }

    public void setAnnio_ingreso(String annio_ingreso) {
        this.annio_ingreso = annio_ingreso;
    }

    public String getMes_ingreso() {
        return mes_ingreso;
    }

    public void setMes_ingreso(String mes_ingreso) {
        this.mes_ingreso = mes_ingreso;
    }

    public String getDia_ingreso() {
        return dia_ingreso;
    }

    public void setDia_ingreso(String dia_ingreso) {
        this.dia_ingreso = dia_ingreso;
    }

    public String getNum_serie() {
        return num_serie;
    }

    public void setNum_serie(String num_serie) {
        this.num_serie = num_serie;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo_equipo() {
        return tipo_equipo;
    }

    public void setTipo_equipo(String tipo_equipo) {
        this.tipo_equipo = tipo_equipo;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
    
 //=============================================================================================================================
}