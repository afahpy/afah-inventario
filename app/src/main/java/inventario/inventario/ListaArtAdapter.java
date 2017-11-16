package inventario.inventario;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by usuario on 18/10/17.
 */

public class ListaArtAdapter extends RecyclerView.Adapter<ListaArtAdapter.ArtViewHolder>{

    ArrayList<Articulo> listArt;
Activity activity;

    public ListaArtAdapter(ArrayList<Articulo> listArt,Activity activity) {
        this.listArt = listArt;
        this.activity=activity;

    }

    @Override
    public ListaArtAdapter.ArtViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent .getContext()).inflate(R.layout.item_articulo,null,false);
        return new ListaArtAdapter.ArtViewHolder(view,activity,listArt);
    }

    @Override
    public void onBindViewHolder(ListaArtAdapter.ArtViewHolder holder, int position) {

        holder.idArt.setText(String.valueOf(listArt.get(position).getId()));
        holder.cod_marca.setText(listArt.get(position).getCod_marca());
        holder.cod_original.setText(listArt.get(position).getCod_original());
        holder.cod_proveedor.setText(listArt.get(position).getCod_proveedor());
        holder.cod_barra.setText(listArt.get(position).getCod_barra());
        holder.descripcion.setText(listArt.get(position).getDescricion());

    }

    @Override
    public int getItemCount() {

        return listArt.size();
    }



    public class ArtViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView idArt,cod_marca,cod_original,cod_proveedor, cod_barra,descripcion;

        ArrayList<Articulo>listArt =new ArrayList<Articulo>();
        Activity activity;

        public ArtViewHolder(View itemView , Activity activity,ArrayList<Articulo>listArt) {
            super(itemView);
            this.listArt=listArt;
            this.activity=activity;

            itemView.setOnClickListener(this);

            idArt =(TextView)itemView.findViewById(R.id.id_Art);
            cod_marca =(TextView)itemView.findViewById(R.id.cod_marca);
            cod_original =(TextView)itemView.findViewById(R.id.cod_original);
            cod_proveedor=(TextView)itemView.findViewById(R.id.cod_proveedor);
            cod_barra =(TextView)itemView.findViewById(R.id.cod_barra);
            descripcion =(TextView)itemView.findViewById(R.id.descripcion);

        }

        @Override
        public void onClick(View view) {


        int position = getAdapterPosition();
            Articulo articulo =this.listArt.get(position);
            Intent intent = new Intent (this.activity,CargaInvActivity.class);

            intent.putExtra("id",articulo.getId());
            intent.putExtra("cod_marca",articulo.getCod_marca());
            intent.putExtra("cod_original",articulo.getCod_original());
            intent.putExtra("cod_proveedor",articulo.getCod_proveedor());
            intent.putExtra("cod_barra",articulo.getCod_barra());
            intent.putExtra("descripcion",articulo.getDescricion());
            activity.startActivity(intent);
            activity.finish();



        }
        }
    }



