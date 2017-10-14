package inventario.inventario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;


public class CargaInvActivity extends AppCompatActivity {

    EditText codArt;
    EditText codBarra;
    EditText cantidad;
    EditText ubicacion;
    EditText descr;
    EditText buscarCod;
    Button btnSave;
    Button btnCancel;
    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_inv);
        this.setTitle("Carga de Inventario");


        btnSave = (Button) findViewById(R.id.boton_aceptar);
        btnCancel = (Button) findViewById(R.id.boton_cancelar);
        search = (Button) findViewById(R.id.btnSearch);
        btnSave = (Button) findViewById(R.id.boton_aceptar);
        codArt = (EditText) findViewById(R.id.idArt);
        codBarra = (EditText) findViewById(R.id.codBarra);
        cantidad = (EditText) findViewById(R.id.cantidad);
        descr = (EditText) findViewById(R.id.desc);
        ubicacion = (EditText) findViewById(R.id.ubi);
        buscarCod = (EditText) findViewById(R.id.searchId);

        descr.setFocusable(false);
        descr.setEnabled(false);
       codArt.setFocusable(false);
        codArt.setEnabled(false);





        btnSave.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (!validarDatos()) {
                    Toast.makeText(getApplicationContext(), "Error de Datos, verifique", Toast.LENGTH_LONG).show();
                    return;
                }

                if(!validarIdArt()){

                    Toast.makeText(getApplicationContext(), "El Articulo ya fue inventariado", Toast.LENGTH_LONG).show();
                    clean();
                    return;
                }

                try {
                    final ConnectDB connectDB = new ConnectDB(getApplicationContext());

                    SQLiteDatabase db = connectDB.getWritableDatabase();

                    ContentValues valores = new ContentValues();
                    valores.put(ConnectDB.datos.ID_ARTICULO, codArt.getText().toString());
                    valores.put(ConnectDB.datos.COD_BARRA, codBarra.getText().toString());
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


        search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final ConnectDB connectDB = new ConnectDB(getApplicationContext());

                SQLiteDatabase db = connectDB.getReadableDatabase();

                String[] argsel = {buscarCod.getText().toString()};
                String[] projection = {ConnectDB.datos.COD_ARTICULO, ConnectDB.datos.DESCRIPCION};
                Cursor c = db.query(ConnectDB.datos.TABLE_ARTICULO, projection, ConnectDB.datos.COD_ORIGINAL + "=?", argsel, null, null, null);

                if (c.moveToFirst()) {
                    do {
                        codArt.setText(c.getString(0));
                        descr.setText(c.getString(1));
                        codBarra.requestFocus();

                    }
                    while (c.moveToNext());


                } else {
                    Toast toast1 = Toast.makeText(getApplicationContext(), "No existe ese Articulo", Toast.LENGTH_SHORT);
                    toast1.show();
                    buscarCod.setText("");
                    buscarCod.requestFocus();
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
        // campos obligatorios..
        if (codArt.getText().toString().trim().isEmpty() || codBarra.getText().toString().trim().isEmpty() ||
                cantidad.getText().toString().trim().isEmpty() || ubicacion.getText().toString().trim().isEmpty()) {
            out = false;
        }


        return out;
    }

    public boolean validarIdArt() {
        boolean out1=true;
        final ConnectDB connectDB = new ConnectDB(getApplicationContext());

        SQLiteDatabase db = connectDB.getReadableDatabase();

            String idArt=codArt.getText().toString();
            Cursor c= db.rawQuery("select "+ ConnectDB.datos.ID_ARTICULO+ " from " + ConnectDB.datos.TABLE_INVENTARIO+ " where "+ ConnectDB.datos.ID_ARTICULO+ "=" + idArt, null);
        if(c.moveToFirst()){
            String idArt1=c.getString(0);
        if(idArt.equals(idArt1)) {

            out1 =false;
        }
        }

        return out1;
    }




    public void clean() {
        buscarCod.setText("");
        descr.setText("");
        codArt.setText("");
        codBarra.setText("");
        cantidad.setText("");
        ubicacion.setText("");
        buscarCod.requestFocus();
    }

        }

