package dev.edmt.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class Gender extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.gender);
    }
}

/*        Button bt_close = (Button) findViewById(R.id.button_close);
        bt_close.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();;
            }
      });
    }

}*/
