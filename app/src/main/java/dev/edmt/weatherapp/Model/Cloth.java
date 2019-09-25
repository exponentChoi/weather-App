package dev.edmt.weatherapp.Model;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import dev.edmt.weatherapp.Model.Main;
import dev.edmt.weatherapp.Common.Common;
import dev.edmt.weatherapp.MainActivity;
import dev.edmt.weatherapp.Model.OpenWeatherMap;
import dev.edmt.weatherapp.R;

public class Cloth extends AppCompatActivity {

    Activity activity;
    ImageView top1,top2,bot1,bot2;

    OpenWeatherMap openWeatherMap = new OpenWeatherMap();




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.cloth);

        Intent intent = getIntent();
        String celcius = intent.getStringExtra("celsius");
        String on = celcius.substring(0,4);
        double num = Double.parseDouble(on);
        top1= (ImageView) findViewById(R.id.top1);
        top2= (ImageView) findViewById(R.id.top2);
        bot1= (ImageView) findViewById(R.id.bottom1);
        bot2= (ImageView) findViewById(R.id.bottom2);





        if(num>=27) {
            // textview.setText("더워");
            top1.setImageResource(R.drawable.mtop3);
            top2.setImageResource(R.drawable.unitop2);
            bot1.setImageResource(R.drawable.mpants4);
            bot2.setImageResource(R.drawable.mpants3);
        }
        else if(num<27 && num>=23){
            //textview.setText("선선하네");
            top1.setImageResource(R.drawable.unitop1);
            top2.setImageResource(R.drawable.unitop2);
            bot1.setImageResource(R.drawable.mpants4);
            bot2.setImageResource(R.drawable.mpants3);
        }


        if(num<23 && num>=20) {

            top1.setImageResource(R.drawable.mtop2);
            top2.setImageResource(R.drawable.unitop10);
            bot1.setImageResource(R.drawable.mpants1);
            bot2.setImageResource(R.drawable.mpants3);

        }

            else if(num<20 && num>=17){
                //textview.setText("선선하네");
                top1.setImageResource(R.drawable.mtop1);
                top2.setImageResource(R.drawable.unisweater2);
                bot1.setImageResource(R.drawable.mpants1);
                bot2.setImageResource(R.drawable.mpants3);
            }
            else if(num<17 && num>=12){
                //textview.setText("선선하네");
                top1.setImageResource(R.drawable.unijack1);
                top2.setImageResource(R.drawable.unitop10);
                bot1.setImageResource(R.drawable.mpants1);
                bot2.setImageResource(R.drawable.mpants3);
            }


        }

    }


