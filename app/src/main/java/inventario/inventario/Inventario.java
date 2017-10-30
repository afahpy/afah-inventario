package inventario.inventario;

/**
 * Created by usuario on 18/10/17.
 */

public class Inventario {

    int id;
    int id_articulo;
    String cod_marca;
    String cod_barra;
    int cantidad;
    String ubicacion;
    int inv_sent;

    public Inventario() {
        this.id = id;
        this.id_articulo = id_articulo;
        this.cod_marca = cod_marca;
        this.cod_barra = cod_barra;
        this.cantidad = cantidad;
        this.ubicacion = ubicacion;
        this.inv_sent = inv_sent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(int id_articulo) {
        this.id_articulo = id_articulo;
    }

    public String getCod_marca() {
        return cod_marca;
    }

    public void setCod_marca(String cod_marca) {
        this.cod_marca = cod_marca;
    }

    public String getCod_barra() {
        return cod_barra;
    }

    public void setCod_barra(String cod_barra) {
        this.cod_barra = cod_barra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getInv_sent() {
        return inv_sent;
    }

    public void setInv_sent(int inv_sent) {
        this.inv_sent = inv_sent;
    }
}
