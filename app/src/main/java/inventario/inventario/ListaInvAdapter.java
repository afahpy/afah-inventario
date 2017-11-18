package inventario.inventario;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import  inventario.inventario.R;

/**
 * Created by usuario on 18/10/17.
 */

public class ListaInvAdapter extends RecyclerView.Adapter<ListaInvAdapter.InventViewHolder> {


    ArrayList<Inventario> listaInv;
    Activity activity;
    Context context;


    public ListaInvAdapter(ArrayList<Inventario> listaInv, Activity activity, Context context) {
        this.listaInv = listaInv;
        this.activity = activity;
        this.context = context;

    }

    @Override
    public InventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inventario, null, false);
        return new InventViewHolder(view, listaInv,activity);
    }

    @Override
    public void onBindViewHolder(InventViewHolder holder, final int position) {



        holder.idInv.setText(String.valueOf(listaInv.get(position).getId()));
        holder.idArt.setText(String.valueOf(listaInv.get(position).getId_articulo()));
        holder.desc.setText(listaInv.get(position).getDescripcion());
        holder.codMarca.setText(listaInv.get(position).getCod_marca());
        holder.codBarra.setText(listaInv.get(position).getCod_barra());
        holder.cant.setText(String.valueOf(listaInv.get(position).getCantidad()));
        holder.ub.setText(listaInv.get(position).getUbicacion());
        holder.sent.setText(String.valueOf(listaInv.get(position).getInv_sent()));

    }


    @Override
    public int getItemCount() {

        return listaInv.size();
    }

    public class InventViewHolder extends RecyclerView.ViewHolder {

        TextView idInv, idArt, codMarca, codBarra, cant, ub, sent, desc;
        Button btndelete;
        ArrayList<Inventario> listaInv = new ArrayList<Inventario>();
        Activity activity;

        public InventViewHolder(View itemView, final ArrayList<Inventario> listaInv, final Activity activity) {
            super(itemView);
            this.listaInv = listaInv;
            this.activity = activity;


            idInv = (TextView) itemView.findViewById(R.id.id_Inv);
            idArt = (TextView) itemView.findViewById(R.id.id_Art);
            codMarca = (TextView) itemView.findViewById(R.id.cod_marca);
            codBarra = (TextView) itemView.findViewById(R.id.cod_barra);
            cant = (TextView) itemView.findViewById(R.id.cantidad);
            ub = (TextView) itemView.findViewById(R.id.ubicacion);
            sent = (TextView) itemView.findViewById(R.id.st);
            desc= (TextView) itemView.findViewById(R.id.descripcion);
            btndelete = (Button) itemView.findViewById(R.id.btnDelete);



         btndelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(activity)

                        .setTitle("Eliminar")
                        .setMessage("Está seguro de eliminar?")
                        .setCancelable(false)
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {



                                final ConnectDB connectDB = new ConnectDB(activity.getApplicationContext());
                                SQLiteDatabase db = connectDB.getWritableDatabase();
                                String[] arg  = new String[]{ idInv.getText().toString()};


                                db.delete(ConnectDB.datos.TABLE_INVENTARIO, ConnectDB.datos.COD_INVENTARIO + "=?", arg);
                                db.close();

                                Toast.makeText(activity.getApplicationContext(),"Inventario eliminado", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(activity,VerInventarioActivity.class);
                                activity.startActivity(intent);
                                activity.finish();


                            }
                        })

                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        })
                        .create().show();

            }
        });


        }
    }



}
