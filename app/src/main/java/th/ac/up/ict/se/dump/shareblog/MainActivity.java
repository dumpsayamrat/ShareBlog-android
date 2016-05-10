package th.ac.up.ict.se.dump.shareblog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnCreateAc;
    Button btnAllAc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreateAc = (Button) findViewById(R.id.btnCreateAc);
        btnAllAc = (Button) findViewById(R.id.btnAllAc);

        btnCreateAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, BlogCreateActivity.class);
                startActivity(i);
            }
        });

        btnAllAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, BlogActivity.class);
                startActivity(i);
            }
        });
        
    }
}
