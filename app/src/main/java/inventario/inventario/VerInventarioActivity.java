package inventario.inventario;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.inputmethodservice.Keyboard;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class VerInventarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_inventario);
        this.setTitle("Vista de Inventario");

       try {

        final ConnectDB connectDB = new ConnectDB(getApplicationContext());
        SQLiteDatabase db = connectDB.getReadableDatabase();

            GridView invent = (GridView) findViewById(R.id.gvInv);

            List<String> listaInv = new ArrayList<>();
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, listaInv);
            dataAdapter.setDropDownViewResource(R.layout.activity_ver_inventario);
            String query = "SELECT  * FROM " + ConnectDB.datos.TABLE_INVENTARIO + " WHERE " + ConnectDB.datos.INV_SENT + " =0";
            Cursor c = db.rawQuery(query, null);

            if(c!=null){
                if(c.moveToFirst()){
                    do{

                        String idInv=c.getString(c.getColumnIndex(ConnectDB.datos.COD_INVENTARIO));
                        String idArt=c.getString(c.getColumnIndex(ConnectDB.datos.ID_ARTICULO));
                        String cantidad=c.getString(c.getColumnIndex(ConnectDB.datos.CANTIDAD));

                        String intSent=c.getString(c.getColumnIndex(ConnectDB.datos.INV_SENT));

                        listaInv.add(idInv);
                        listaInv.add(idArt);
                        listaInv.add(cantidad);
                        listaInv.add(intSent);



                    invent.setAdapter(dataAdapter);
                }while (c.moveToNext());
            }else{
                Toast.makeText(getApplicationContext(), "No hay registros para mostrar", Toast.LENGTH_LONG).show();
            }
        }
        c.close();
        db.close();
    }catch (Exception e){
        Toast.makeText(getApplicationContext(),"Error al mostrar Registros", Toast.LENGTH_SHORT).show();
    }

        }


}
