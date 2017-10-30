package inventario.inventario;

import java.io.Serializable;

/**
 * Created by usuario on 18/10/17.
 */

public class Articulo implements Serializable{

    int id;
    String cod_marca;
    String cod_original;
    String cod_proveedor;
    String cod_barra;
    String descricion;

    public Articulo() {
        this.id = id;
        this.cod_marca = cod_marca;
        this.cod_original = cod_original;
        this.cod_proveedor = cod_proveedor;
        this.cod_barra = cod_barra;
        this.descricion = descricion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCod_marca() {
        return cod_marca;
    }

    public void setCod_marca(String cod_marca) {
        this.cod_marca = cod_marca;
    }

    public String getCod_original() {
        return cod_original;
    }

    public void setCod_original(String cod_original) {
        this.cod_original = cod_original;
    }

    public String getCod_proveedor() {
        return cod_proveedor;
    }

    public void setCod_proveedor(String cod_proveedor) {
        this.cod_proveedor = cod_proveedor;
    }

    public String getCod_barra() {
        return cod_barra;
    }

    public void setCod_barra(String cod_barra) {
        this.cod_barra = cod_barra;
    }

    public String getDescricion() {
        return descricion;
    }

    public void setDescricion(String descricion) {
        this.descricion = descricion;
    }
}
