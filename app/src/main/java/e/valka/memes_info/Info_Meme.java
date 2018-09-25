package e.valka.memes_info;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Info_Meme extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info__meme);
        int index = getIntent().getIntExtra(MainActivity.clave,-1);
        InfoFragment inf = new InfoFragment();
        inf.setIndex(index);
        getSupportFragmentManager()
                .beginTransaction().replace (R.id.containerInfo, inf)
                .commit ();
    }
}
