package inventario.inventario;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by usuario on 19/10/17.
 */

public class ConsultaArticulo extends AppCompatActivity {

    ArrayList<Articulo> listArt;
    RecyclerView recyclerArt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        this.setTitle("Consulta");




        listArt = new ArrayList<>();
        recyclerArt = (RecyclerView) findViewById(R.id.artRecycler);

        recyclerArt.setLayoutManager(new LinearLayoutManager(this));

        ListaArtAdapter adapter = new ListaArtAdapter(listArt,this);
        recyclerArt.setAdapter(adapter);
        consulta();





    }


    public void consulta() {


        try{


            final ConnectDB connectDB = new ConnectDB(getApplicationContext());
            SQLiteDatabase db = connectDB.getReadableDatabase();

            Articulo articulo= null;

            Bundle parametro=this.getIntent().getExtras();

            String query= parametro.getString("query");

            Cursor cursor = db.rawQuery(query, null);

            if(cursor!=null){
                if(cursor.moveToFirst()){
                    do{

                        articulo=new Articulo();

                        articulo.setId(cursor.getInt(0));
                        articulo.setCod_marca(cursor.getString(1));
                        articulo.setCod_original(cursor.getString(2));
                        articulo.setCod_proveedor(cursor.getString(3));
                        articulo.setCod_barra(cursor.getString(4));
                        articulo.setDescricion(cursor.getString(5));

                        listArt.add(articulo);
                    }while (cursor.moveToNext());
                }else{
                    Toast.makeText(getApplicationContext(), "No Existe ese Articulo", Toast.LENGTH_LONG).show();
                }
            }
            cursor.close();
            db.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Error al mostrar Registros", Toast.LENGTH_SHORT).show();
        }
    }

}
