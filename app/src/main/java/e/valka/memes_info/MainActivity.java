package e.valka.memes_info;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public final static String clave = "claveMemeInfo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListadoFragment f = new ListadoFragment();
        f.setMemeTouchListener((index)->{
            Intent intent = new Intent(this,Info_Meme.class);
            intent.putExtra(clave,index);
            startActivity(intent);
        });
        getSupportFragmentManager()
                .beginTransaction().replace (R.id.container, f)
                .commit ();

    }
}
