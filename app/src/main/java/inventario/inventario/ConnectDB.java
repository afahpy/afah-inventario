package inventario.inventario;


import android.content.Context;


import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.widget.Toast;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


/**
 * Created by Acer on 6/9/2017.
 */
public class ConnectDB extends SQLiteAssetHelper {


   public static abstract class datos implements BaseColumns {

      public static final String TABLE_ARTICULO = "articulo";

      public static final String COD_ARTICULO = "_id";
      public static final String CODIGO_MARCA = "cod_marca";
      public static final String COD_ORIGINAL = "cod_original";
      public static final String COD_PROVEEDOR = "cod_proveedor";
      public static final String CODIGO_BARRA = "cod_barra";
      public static final String DESCRIPCION = "descripcion";

      public static final String TABLE_INVENTARIO = "inventario";
      public static final String COD_INVENTARIO = "_id";
      public static final String ID_ARTICULO = "id_articulo";
      public static final String COD_MARCA = "cod_marca";
      public static final String COD_BARRA = "cod_barra";
      public static final String CANTIDAD = "cantidad";
      public static final String UBICACION = "ubicacion";
      public static final String INV_SENT = "inv_sent";
       public static final String DESC_INV= "descripcion";


      private static final String DB_NAME = "inventario.db";
      private static final int DB_VERSION = 1;
   }

   public ConnectDB(Context context) {
      super(context, datos.DB_NAME, context.getExternalFilesDir(null).getAbsolutePath(), null, datos.DB_VERSION);


   }

    public int eliminar(int rowId) {
        int eliminado=0;

        try{

        SQLiteDatabase db = getWritableDatabase();


        db.delete(ConnectDB.datos.TABLE_INVENTARIO, ConnectDB.datos.COD_INVENTARIO + " = ?", new String[]{String.valueOf(rowId)});
        db.close();



    }catch (Exception e){
            e.printStackTrace();
        }
        return eliminado;



    }}





