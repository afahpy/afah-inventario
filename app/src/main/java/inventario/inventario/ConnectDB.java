package inventario.inventario;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.provider.BaseColumns;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Acer on 6/9/2017.
 */
public class ConnectDB extends SQLiteOpenHelper{
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
        public static final String ID_ARTICULO= "idArticulo";
        public static final String COD_BARRA = "codBarra";
        public static final String UBICACION = "ubicacion";
        public static final String CANTIDAD = "cantidad";
        public static final String INV_SENT = "inv_sent";



        private static final String DB_NAME = "inventario.db";
        private static final int DB_VERSION = 1;


       static final String CREATE_TABLE_ARTICULO = "CREATE TABLE " + TABLE_ARTICULO + " ("
                + COD_ARTICULO + " INTEGER PRIMARY KEY, " + COD_SISTEMA + " VARCHAR(200), " + COD_INTERNO
                + " VARCHAR(200), " + COD_ORIGINAL + " VARCHAR(200), "
                + COD_MARCA + " VARCHAR(200), " + DESCRIPCION + " VARCHAR(200))";

        static final String CREATE_TABLE_INVENTARIO = "CREATE TABLE " + TABLE_INVENTARIO + " ("
                + COD_INVENTARIO + " INTEGER PRIMARY KEY, " + ID_ARTICULO + " INTEGER, " + COD_BARRA + " VARCHAR(200), "
                + CANTIDAD + " INT(200), " + UBICACION + " VARCHAR(200), " + INV_SENT + " TINYINT(1), "
                + " FOREIGN KEY ("+ID_ARTICULO+") REFERENCES "+TABLE_ARTICULO+ " ("+COD_ARTICULO+ "))";

       static  final String INSERT_ARTICULO_ROW1 = "INSERT INTO " + TABLE_ARTICULO + " ("+ COD_ARTICULO+ "," +COD_SISTEMA+ ", "+ COD_INTERNO+ ", "+
               COD_ORIGINAL+ ", "+ COD_MARCA+ ", "+ DESCRIPCION+  ") VALUES( " + "1, 1, '7895', '51236', '789', 'BATERIA')";
       static  final String INSERT_ARTICULO_ROW2 = "INSERT INTO " + TABLE_ARTICULO + " ("+ COD_ARTICULO+ "," +COD_SISTEMA+ ", "+ COD_INTERNO+ ", "+
               COD_ORIGINAL+ ", "+ COD_MARCA+ ", "+ DESCRIPCION+  ") VALUES( " + "2, 7, '788955', '521356', '7897', 'AA')";
       static  final String INSERT_ARTICULO_ROW3 = "INSERT INTO " + TABLE_ARTICULO + " ("+ COD_ARTICULO+ "," +COD_SISTEMA+ ", "+ COD_INTERNO+ ", "+
               COD_ORIGINAL+ ", "+ COD_MARCA+ ", "+ DESCRIPCION+  ") VALUES( " + "3, 6, '789545', '528356', '78397', 'HELADERA')";
    }

 public  ConnectDB  (Context context) {
        super(context, datos.DB_NAME, null, datos.DB_VERSION);

    }



    @Override
    public void onCreate(SQLiteDatabase db){

        db.execSQL (datos.CREATE_TABLE_ARTICULO);
        db.execSQL (datos.CREATE_TABLE_INVENTARIO);
        db.execSQL(datos.INSERT_ARTICULO_ROW1);
        db.execSQL(datos.INSERT_ARTICULO_ROW2);
        db.execSQL(datos.INSERT_ARTICULO_ROW3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + datos.CREATE_TABLE_ARTICULO);
        db.execSQL("DROP TABLE IF EXISTS" + datos.CREATE_TABLE_INVENTARIO);


        onCreate(db);

    }


    }


