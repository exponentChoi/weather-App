package dev.edmt.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class TransprentActivity extends AppCompatActivity {
    Button btClose;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.trans_main);

        btClose = (Button) findViewById(R.id.trclose);

        btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
        //findViewById(R.id.trclose).setOnClickListener(this);
    //}

//    public void onClick(View v){
//        switch(v.getId()){
//            case.R.id.trclose:
//                this.finish();
//                break;
//        }
//    }
//}
//
//
