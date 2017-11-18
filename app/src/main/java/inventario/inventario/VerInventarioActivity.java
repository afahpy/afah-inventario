package inventario.inventario;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import java.util.ArrayList;


public class VerInventarioActivity extends AppCompatActivity {

    ArrayList<Inventario> listaInv;
    RecyclerView recyclerInv;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_inventario);
        this.setTitle("Vista de Inventario");


        listaInv = new ArrayList<>();
        recyclerInv = (RecyclerView) findViewById(R.id.invRecycler);
        recyclerInv.setLayoutManager(new LinearLayoutManager(this));
        consulta();

        ListaInvAdapter adapter = new ListaInvAdapter(listaInv,this,context);
        recyclerInv.setAdapter(adapter);





    }

    private void consulta() {

        try {
            final ConnectDB connectDB = new ConnectDB(getApplicationContext());
            SQLiteDatabase db = connectDB.getReadableDatabase();

            Inventario inventario = null;
            String query = "SELECT  * FROM " + ConnectDB.datos.TABLE_INVENTARIO + " ORDER BY " + ConnectDB.datos.COD_INVENTARIO + " DESC";
            Cursor c = db.rawQuery(query, null);
            if (c != null) {
                if (c.moveToFirst()) {
                    do {

                        inventario = new Inventario();

                        inventario.setId(c.getInt(0));
                        inventario.setId_articulo(c.getInt(1));
                        inventario.setDescripcion(c.getString(2));
                        inventario.setCod_marca(c.getString(3));
                        inventario.setCod_barra(c.getString(4));
                        inventario.setCantidad(c.getInt(5));
                        inventario.setUbicacion(c.getString(6));
                        inventario.setInv_sent(c.getInt(7));


                        listaInv.add(inventario);
                    } while (c.moveToNext());
                } else {
                    Toast.makeText(getApplicationContext(), "No hay registros para mostrar", Toast.LENGTH_LONG).show();
                }
            }
            c.close();
            db.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error al mostrar Registros", Toast.LENGTH_SHORT).show();

        }


    }



}