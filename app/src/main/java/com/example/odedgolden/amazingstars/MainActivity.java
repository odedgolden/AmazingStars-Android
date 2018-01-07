package com.example.odedgolden.amazingstars;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView apodTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadImagesData();

        apodTextView = (TextView) findViewById(R.id.apod_data);
//        imageView.setImageBitmap(bmp);
    }

    private void loadImagesData() {

        new FetchImagesDataTask().execute();
    }

    public class FetchImagesDataTask extends AsyncTask<String, Void, String[]> {

        @Override
        protected String[] doInBackground(String... params) {

            String location = "";
            URL APODRequestUrl = NetworkUtils.buildUrl(location);

            try {
                String jsonAPODResponse = NetworkUtils
                        .getResponseFromHttpUrl(APODRequestUrl);

                APODObject apodObject = APODJsonUtils.getAPODObjectFromJson(MainActivity.this, jsonAPODResponse);

                return new String[]{apodObject.title, apodObject.hdurl};

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String[] apodData) {
            if (apodData != null) {

                apodTextView.setText(apodData[0]);
                new DownloadImageTask((ImageView) findViewById(R.id.my_image)).execute(apodData[1]);
                System.out.print(apodData);

            }

        }
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}

