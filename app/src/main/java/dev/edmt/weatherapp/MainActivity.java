package dev.edmt.weatherapp;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import dev.edmt.weatherapp.Common.Common;
import dev.edmt.weatherapp.Helper.Helper;
import dev.edmt.weatherapp.Model.Cloth;
import dev.edmt.weatherapp.Model.Main;
import dev.edmt.weatherapp.Model.OpenWeatherMap;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class MainActivity extends AppCompatActivity implements LocationListener {
    Activity activity;

    TextView txtCity, txtLastUpdate, txtDescription, txtHumidity, txtTime, txtCelsius,textview,test;
    ImageView imageView,
            top1,top2,bot1,bot2;

    LocationManager locationManager;
    String provider;
    static double lat, lng;
    OpenWeatherMap openWeatherMap = new OpenWeatherMap();




    int MY_PERMISSION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Button cloth = (Button)findViewById(R.id.cloth) ;
        cloth.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Cloth.class);
                intent.putExtra("celsius",txtCelsius.getText().toString());
                startActivity(intent);

            }
        });
        activity = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);

        //툴바 설정
        setSupportActionBar(toolbar); //툴바를 액션바와 같게 만들어 준다.
        getSupportActionBar().setDisplayShowTitleEnabled(false); //툴바 타이틀 제거

        Intent intent = new Intent(activity , TransprentActivity.class);
        startActivity(intent); // 가이드 화면 출력하기

        //Control
        txtCity = (TextView) findViewById(R.id.txtCity);
        txtLastUpdate = (TextView) findViewById(R.id.txtLastUpdate);
        txtDescription = (TextView) findViewById(R.id.txtDescription);
        txtHumidity = (TextView) findViewById(R.id.txtHumidity);
        txtTime = (TextView) findViewById(R.id.txtTime);
        txtCelsius = (TextView) findViewById(R.id.txtCelsius);
        imageView = (ImageView) findViewById(R.id.imageView);
        textview = (TextView) findViewById(R.id.textView);
        top1= (ImageView) findViewById(R.id.top1);
        top2= (ImageView) findViewById(R.id.top2);
        bot1= (ImageView) findViewById(R.id.bottom1);
        bot2= (ImageView) findViewById(R.id.bottom2);
        //imageView3 = (ImageView) findViewById(R.id.imageView3);

        //Get Coordinates
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), false);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE


            }, MY_PERMISSION);
        }
        Location location = locationManager.getLastKnownLocation(provider);
        if (location == null)
            Log.e("TAG","No Location");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE


            }, MY_PERMISSION);
        }
        locationManager.removeUpdates(this);
    }


    // 툴바에 메뉴 넣기
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //툴바 버튼 이벤트
    public boolean onOptionsItemSelected(MenuItem item){
        //각각의 버튼을 클릭할때의 수행할것을 정의해 준다.
        switch (item.getItemId()){
            case R.id.home:
                Toast.makeText(this, "홈버튼을 눌렀습니다.", Toast.LENGTH_SHORT).show();
                return true;
                //break;
            case R.id.action_bt2:
                Toast.makeText(this, "성별 바꾸기를 눌렀습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,Gender.class);
                startActivity(intent);
                return true;

            case R.id.action_bt3:
                Toast.makeText(this, "위치 변경하기를 눌렀습니다.", Toast.LENGTH_SHORT).show();
                return true;
                //break;

            default:
                return super.onOptionsItemSelected(item);
        }

     //   return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE


            }, MY_PERMISSION);
        }
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        lat = location.getLatitude();
        lng = location.getLongitude();

        new GetWeather().execute(Common.apiRequest(String.valueOf(lat),String.valueOf(lng)));

        Geocoder geocoder = new Geocoder(this);

        List<Address> list = null;
        try {


            double d1 = Double.parseDouble(String.valueOf(lat));
            double d2 = Double.parseDouble(String.valueOf(lng));


            list = geocoder.getFromLocation(
                    d1, // 위도
                    d2, // 경도
                    10); // 얻어올 값의 개수
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("test", "입출력 오류 - 서버에서 주소변환시 에러발생");
        }
        if (list != null) {
            if (list.size()==0) {
                txtCity.setText("해당되는 주소 정보는 없습니다");
            } else {
                txtCity.setText(list.get(0).getCountryName() +" "+   //  국가명
                        list.get(0).getAdminArea()+" "+   // 도시명
                        list.get(0).getLocality()+" "+ // 구
                        list.get(0).getThoroughfare()); // 동
                if (list.get(0).getAdminArea()==null){
                    txtCity.setText("해당되는 주소 정보는 없습니다");
                }
                else if (list.get(0).getLocality()==null) {

                    txtCity.setText(list.get(0).getAddressLine(0).substring(5));

                }

            }
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private class GetWeather extends AsyncTask<String,Void,String>{
        ProgressDialog pd = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setTitle("Please wait...");
            pd.show();

        }


        @Override
        protected String doInBackground(String... params) {
            String stream = null;
            String urlString = params[0];

            Helper http = new Helper();
            stream = http.getHTTPData(urlString);
            return stream;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.contains("Error: Not found city")){
                pd.dismiss();
                return;
            }
            Gson gson = new Gson();
            Type mType = new TypeToken<OpenWeatherMap>(){}.getType();
            openWeatherMap = gson.fromJson(s,mType);
            pd.dismiss();



           // txtCity.setText(String.format("%s,%s",openWeatherMap.getName(),openWeatherMap.getSys().getCountry()));  // 지역
            txtLastUpdate.setText(String.format("Last Updated: %s", Common.getDateNow()));  // 마지막 업데이트 정보
            txtDescription.setText(String.format("%s",openWeatherMap.getWeather().get(0).getDescription())); // 기상상태
            txtHumidity.setText(String.format("%d%%",openWeatherMap.getMain().getHumidity())); // 습도
            txtTime.setText(String.format("%s/%s",Common.unixTimeStampToDateTime(openWeatherMap.getSys().getSunrise()),Common.unixTimeStampToDateTime(openWeatherMap.getSys().getSunset()))); // 시간
            txtCelsius.setText(String.format("%.1f °C",openWeatherMap.getMain().getTemp())); // 기온






            /*if(openWeatherMap.getMain().getTemp()>=27) {
               // textview.setText("더워");
                top1.setImageResource(R.drawable.mtop3);
                top2.setImageResource(R.drawable.unitop2);
                bot1.setImageResource(R.drawable.mpants4);
                bot2.setImageResource(R.drawable.mpants3);
            }
            else if(openWeatherMap.getMain().getTemp()<27 && openWeatherMap.getMain().getTemp()>=23){
                //textview.setText("선선하네");
                top1.setImageResource(R.drawable.unitop1);
                top2.setImageResource(R.drawable.unitop2);
                bot1.setImageResource(R.drawable.mpants4);
                bot2.setImageResource(R.drawable.mpants3);
            }*/


            if(openWeatherMap.getMain().getTemp()<23 && openWeatherMap.getMain().getTemp()>=20) {
                //textview.setText("선선하네");

                //top1.setImageResource(R.drawable.mtop2);
                //top2.setImageResource(R.drawable.unitop10);
                //bot1.setImageResource(R.drawable.mpants1);
                //bot2.setImageResource(R.drawable.mpants3);

            }
            /*
            else if(openWeatherMap.getMain().getTemp()<20 && openWeatherMap.getMain().getTemp()>17){
                //textview.setText("선선하네");
                top1.setImageResource(R.drawable.mtop1);
                top2.setImageResource(R.drawable.unisweater2);
                bot1.setImageResource(R.drawable.mpants1);
                bot2.setImageResource(R.drawable.mpants3);
            }
            else if(openWeatherMap.getMain().getTemp()<17 && openWeatherMap.getMain().getTemp()>12){
                //textview.setText("선선하네");
                top1.setImageResource(R.drawable.unijack1);
                top2.setImageResource(R.drawable.unitop10);
                bot1.setImageResource(R.drawable.mpants1);
                bot2.setImageResource(R.drawable.mpants3);
            }*/

            Picasso.get()
                    .load(Common.getImage(openWeatherMap.getWeather().get(0).getIcon()))
                    .into(imageView);

        }

    }
}
