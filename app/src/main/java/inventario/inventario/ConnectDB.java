package inventario.inventario;


import android.content.Context;



import android.provider.BaseColumns;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


/**
 * Created by Acer on 6/9/2017.
 */
public class ConnectDB extends SQLiteAssetHelper{
   public static abstract class datos implements BaseColumns {

       public static final String TABLE_ARTICULO = "articulo";
       public static final String TABLE_INVENTARIO = "inventario";
       public static final String COD_ARTICULO = "_id";
       public static final String COD_INTERNO = "codigoInterno";
       public static final String COD_ORIGINAL = "codOriginal";
       public static final String COD_SISTEMA = "codigoSistema";
       public static final String COD_MARCA = "codMarca";
       public static final String DESCRIPCION = "descripcion";
       public static final String COD_INVENTARIO = "_id";
       public static final String ID_ARTICULO = "idArticulo";
       public static final String COD_BARRA = "codBarra";
       public static final String UBICACION = "ubicacion";
       public static final String CANTIDAD = "cantidad";
       public static final String INV_SENT = "inv_sent";


       private static final String DB_NAME = "inventario.db";
       private static final int DB_VERSION = 1;
   }

 public  ConnectDB  (Context context) {
        super(context, datos.DB_NAME, context.getExternalFilesDir(null).getAbsolutePath(),null, datos.DB_VERSION);

    }





    }


