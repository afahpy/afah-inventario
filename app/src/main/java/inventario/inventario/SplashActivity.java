package inventario.inventario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        showSplash();


    }


    public void showSplash(){
        Thread splash = new Thread() {
            static final int ESPERA = 3000;

            @Override
            public void run() {
                try {
                    sleep(ESPERA);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(SplashActivity.this, InicioActivity.class);
                    SplashActivity.this.finish();
                    SplashActivity.this.startActivity(intent);
                }

            }
        };
        splash.start();


            }
        }


