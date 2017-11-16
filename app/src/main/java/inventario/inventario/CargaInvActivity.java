package inventario.inventario;

import android.app.Activity;

import android.content.ContentValues;
import android.content.Context;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import android.view.ViewGroup.LayoutParams;
import java.util.ArrayList;


public class CargaInvActivity extends AppCompatActivity {




    EditText cod_marca;
    EditText cod_original;
    EditText cod_proveedor;
    EditText cod_barra_search;
    EditText cod_barra;
    EditText descr;
    EditText cantidad;
    EditText ubicacion;
    EditText id_Articulo;

    Button btnSearchMarca;
    Button btnSearchOriginal;
    Button btnSearchProveedor;
    Button btnSearchCodBarra;

    Button btnCancel;
    Button btnSave;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_inv);
        this.setTitle("Carga de Inventario");



        btnSave = (Button) findViewById(R.id.boton_aceptar);
        btnCancel = (Button) findViewById(R.id.boton_cancelar);
        btnSearchMarca = (Button) findViewById(R.id.btnMarca);
        btnSearchOriginal = (Button) findViewById(R.id.btnOriginal);
        btnSearchProveedor = (Button) findViewById(R.id.btnProveedor);
        btnSearchCodBarra = (Button) findViewById(R.id.btnBarra);
        btnSave = (Button) findViewById(R.id.boton_aceptar);


        cod_marca = (EditText) findViewById(R.id.marca);
        cod_original = (EditText) findViewById(R.id.original);
        cod_proveedor = (EditText) findViewById(R.id.proveedor);
        cod_barra_search = (EditText) findViewById(R.id.codBarra);
        id_Articulo = (EditText) findViewById(R.id.idArt);
        descr = (EditText) findViewById(R.id.desc);
        cantidad = (EditText) findViewById(R.id.cantidad);
        ubicacion = (EditText) findViewById(R.id.ubi);
        cod_barra = (EditText) findViewById(R.id.cBarra);

        descr.setInputType(InputType.TYPE_NULL);



        id_Articulo.setText(String.valueOf(getIntent().getIntExtra("id",0)));
        cod_marca.setText(getIntent().getStringExtra("cod_marca"));
        cod_barra_search.setText(getIntent().getStringExtra("cod_barra"));
        cod_original.setText(getIntent().getStringExtra("cod_original"));
        cod_proveedor.setText(getIntent().getStringExtra("cod_proveedor"));
        descr.setText(getIntent().getStringExtra("descripcion"));
        cod_barra.setText(getIntent().getStringExtra("cod_barra"));


        focus();



        btnSave.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (!validarDatos()) {
                    Toast.makeText(getApplicationContext(), "Error, Cantidad y/o Ubicacion estan vacios o no ha seleccionado el Articulo a Inventariar", Toast.LENGTH_LONG).show();
                    return;
                }

               if(!validarCarga()){

                    Toast.makeText(getApplicationContext(), "El Articulo ya fue inventariado", Toast.LENGTH_LONG).show();
                    clean();
                    return;
                }


                try {
                    final ConnectDB connectDB = new ConnectDB(getApplicationContext());

                    SQLiteDatabase db = connectDB.getWritableDatabase();

                    ContentValues valores = new ContentValues();
                    valores.put(ConnectDB.datos.ID_ARTICULO, id_Articulo.getText().toString());
                    valores.put(ConnectDB.datos.COD_MARCA,cod_marca.getText().toString());
                    valores.put(ConnectDB.datos.COD_BARRA, cod_barra.getText().toString());
                    valores.put(ConnectDB.datos.CANTIDAD, cantidad.getText().toString());
                    valores.put(ConnectDB.datos.UBICACION, ubicacion.getText().toString());
                    valores.put(ConnectDB.datos.INV_SENT, "0");

                    Long idGuardado = db.insert(ConnectDB.datos.TABLE_INVENTARIO, ConnectDB.datos.COD_INVENTARIO, valores);
                    Toast.makeText(getApplicationContext(), "Se guardo exitosamente el registro: " + idGuardado, Toast.LENGTH_LONG).show();
                    clean();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error en la Carga de Inventario", Toast.LENGTH_LONG).show();
                }
            }

        });

        btnSearchMarca. setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(!cod_marca.getText().toString().trim().isEmpty()) {
                    Intent intent = new Intent(CargaInvActivity.this, ConsultaArticulo.class);
                    CargaInvActivity.this.finish();
                    String query = "SELECT  *  FROM " + ConnectDB.datos.TABLE_ARTICULO + " WHERE " + ConnectDB.datos.CODIGO_MARCA + " LIKE '" + cod_marca.getText().toString() + "%'";
                    intent.putExtra("query", query);
                    startActivity(intent);
                    cod_marca.setText("");
                }
                else{
                        Toast.makeText(getApplicationContext(),"Codigo Marca Vacio",Toast.LENGTH_SHORT).show();
                    }


            }

        });
        btnSearchOriginal. setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(!cod_original.getText().toString().trim().isEmpty()) {

                    Intent intent = new Intent(CargaInvActivity.this, ConsultaArticulo.class);
                    CargaInvActivity.this.finish();
                    String query = "SELECT  *  FROM " + ConnectDB.datos.TABLE_ARTICULO + " WHERE " + ConnectDB.datos.COD_ORIGINAL + " LIKE '" + cod_original.getText().toString() + "%'";
                    intent.putExtra("query", query);
                    startActivity(intent);
                    cod_original.setText("");
                }else{
                    Toast.makeText(getApplicationContext(),"Cod Original Vacio",Toast.LENGTH_SHORT).show();
                }

            }




        });

        btnSearchProveedor. setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(!cod_proveedor.getText().toString().trim().isEmpty()) {
                    Intent intent = new Intent(CargaInvActivity.this, ConsultaArticulo.class);
                    CargaInvActivity.this.finish();
                    String query = "SELECT  *  FROM " + ConnectDB.datos.TABLE_ARTICULO + " WHERE " + ConnectDB.datos.COD_PROVEEDOR + " LIKE '" + cod_proveedor.getText().toString() + "%'";
                    intent.putExtra("query", query);
                    startActivity(intent);
                    cod_proveedor.setText("");
                }else{
                    Toast.makeText(getApplicationContext(),"Cod Proveedor Vacio",Toast.LENGTH_SHORT).show();
                }


            }




        });

        btnSearchCodBarra. setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(!cod_barra_search.getText().toString().trim().isEmpty()) {
                Intent intent = new Intent(CargaInvActivity.this, ConsultaArticulo.class);
                    CargaInvActivity.this.finish();
                String query = "SELECT  *  FROM " + ConnectDB.datos.TABLE_ARTICULO + " WHERE " + ConnectDB.datos.CODIGO_BARRA + " LIKE '" + cod_barra_search.getText().toString() + "%'";
                intent.putExtra("query", query);
                startActivity(intent);
                    cod_barra_search.setText("");
            } else{
                    Toast.makeText(getApplicationContext(),"Cod Barra vacio",Toast.LENGTH_SHORT).show();
                }

            }




        });






        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                clean();

            }
        });
    }

    public boolean validarDatos() {
        boolean out = true;
        // campos obligatorios.

        if (cantidad.getText().toString().trim().isEmpty() || ubicacion.getText().toString().trim().isEmpty() || id_Articulo.getText().toString().equals("0") || id_Articulo.getText().toString().trim().isEmpty()) {
            out = false;
        }


        return out;
    }

    public boolean validarCarga() {
        boolean out1 = true;
        final ConnectDB connectDB = new ConnectDB(getApplicationContext());

        SQLiteDatabase db = connectDB.getReadableDatabase();


        String idArt = id_Articulo.getText().toString();
        String ubi = ubicacion.getText().toString();
        Cursor c = db.rawQuery("select " + ConnectDB.datos.ID_ARTICULO + ", " + ConnectDB.datos.UBICACION + " from " + ConnectDB.datos.TABLE_INVENTARIO + " where " + ConnectDB.datos.ID_ARTICULO + " = '" + idArt + "' AND " + ConnectDB.datos.UBICACION + " = '" + ubi + "'", null);
        if (c.moveToFirst()) {

            String idArt1 = c.getString(0);
            String ubic = c.getString(1);
            if (idArt.equals(idArt1) && ubi.equals(ubic)) {

                out1 = false;
            }
        }
            return out1;
        }



    public void clean() {
        cod_marca.setText("");
        cod_original.setText("");
        cod_proveedor.setText("");
        cod_barra_search.setText("");
        descr.setText("");
        cod_barra.setText("");
        cantidad.setText("");
        ubicacion.setText("");
        id_Articulo.setText("");
        cod_marca.requestFocus();
    }
public void focus(){

    if(!id_Articulo.getText().toString().trim().isEmpty() && !descr.getText().toString().trim().isEmpty()){
    cantidad.requestFocus();
}


    }}


