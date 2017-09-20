package inventario.inventario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }

    public void carga(View v){
        Intent intent= new Intent(InicioActivity.this,CargaInvActivity.class);
        InicioActivity.this.startActivity(intent);
    }
    public void viewInve(View v){
        Intent intent= new Intent(InicioActivity.this,VerInventarioActivity.class);
       InicioActivity.this.startActivity(intent);
    }


}
