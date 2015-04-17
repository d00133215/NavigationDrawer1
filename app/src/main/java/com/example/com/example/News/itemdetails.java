package com.example.com.example.News;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by waihong on 25/03/2015.
 */
public class itemdetails {
    public String id;
    public String title;
    public String content;
    public String date;
    public String img;
    private Bitmap image;

    public itemdetails(String id, String title, String content, String date, String img, Bitmap image) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        //this.img = "http://www.sportsdestinations.com/files/sports_destination_management/nodes/2015/8968/IMG.jpg";
        this.img = "http://egocart.net/testing/CA2/"+img;
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public customAdapter getSta() {
        return sta;
    }

    public void setSta(customAdapter sta) {
        this.sta = sta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "itemdetails{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                ", img='" + img + '\'' +
                '}';
    }

    private customAdapter sta;

    public customAdapter getAdapter() {
        return sta;
    }

    public void setAdapter(customAdapter sta) {
        this.sta = sta;
    }

    public void loadImage(customAdapter sta) {
        // HOLD A REFERENCE TO THE ADAPTER
        this.sta = sta;
        if (img != null && !img.equals("")) {
            new ImageLoadTask().execute(img);
        }
    }

    private class ImageLoadTask extends AsyncTask<String, String, Bitmap> {

        @Override
        protected void onPreExecute() {
            Log.i("ImageLoadTask", "Loading image...");
        }



        // PARAM[0] IS IMG URL
        protected Bitmap doInBackground(String... param) {
            Log.i("ImageLoadTask", "Attempting to load image URL: " + param[0]);
            try {
                System.out.println("df: " + param[0]);
                Bitmap b = getBitmapFromURL(param[0]);
                return b;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onProgressUpdate(String... progress) {
            // NO OP
        }

        protected void onPostExecute(Bitmap ret) {
            if (ret != null) {
                Log.i("ImageLoadTask", "Successfully loaded " + title + " image");
                image = ret;
                if (sta != null) {
                    // WHEN IMAGE IS LOADED NOTIFY THE ADAPTER
                    sta.notifyDataSetChanged();
                }
            } else {
                Log.e("ImageLoadTask", "Failed to load " + title + " image");
            }
        }
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
