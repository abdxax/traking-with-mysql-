package com.example.abdulrahman.tracking;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Abdulrahman on 12/02/18.
 */

public class MyLocation implements LocationListener {
    Context context;

    public MyLocation(Context context) {
        this.context = context;
    }

    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(context,"The lgo "+location.getLongitude()+" and the lat "+location.getLatitude(),Toast.LENGTH_LONG).show();
         String url="http://192.168.1.6/apiWeb/trak.php?log="+location.getLongitude()+"&lat="+location.getLatitude()+"&id=2";
         new conne().execute(url);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        Toast.makeText(context,"Status is change ",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderEnabled(String s) {
        Toast.makeText(context,"GPS is enabled ",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onProviderDisabled(String s) {
        Toast.makeText(context,"GPS is disable ",Toast.LENGTH_LONG).show();

    }

    public class conne extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            Toast.makeText(context,values[0],Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url=new URL(strings[0]);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                InputStream inputStream=new BufferedInputStream(httpURLConnection.getInputStream());
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                String NewData="";
                String Line ;
                while ((Line=bufferedReader.readLine())!=null){
                    NewData+=Line;
                }
                publishProgress(NewData);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
