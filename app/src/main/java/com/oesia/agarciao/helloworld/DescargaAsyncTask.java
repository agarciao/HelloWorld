package com.oesia.agarciao.helloworld;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Alberto on 15/09/2015.
 */
public class DescargaAsyncTask extends AsyncTask<String, Float, Bitmap> {

    private Bitmap bitmap = null;
    private ImageView imageView;
    private ProgressDialog dialog;

    public DescargaAsyncTask(ImageView imageView, ProgressDialog progressDialog) {
        this.imageView = imageView;
        this.dialog = progressDialog;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            URLConnection connection = url.openConnection();
            int tamano = connection.getContentLength();
            int bytesDescargados = 0;
            int bytesTotalesDescargados = 0;
            byte[] buffer = new byte[512];
            byte[] imagen = new byte[tamano];
            InputStream entrada = connection.getInputStream();
            while (bytesTotalesDescargados < tamano) {
                bytesDescargados = entrada.read(buffer);
                System.arraycopy(buffer, 0, imagen, bytesTotalesDescargados, bytesDescargados);
                bytesTotalesDescargados += bytesDescargados;
                publishProgress((float) bytesTotalesDescargados / tamano * 100);
            }
            bitmap = BitmapFactory.decodeByteArray(imagen, 0, bytesTotalesDescargados);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return bitmap;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.setProgress(0);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMax(100);
        dialog.setTitle("Cargando...");

        dialog.show();
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        this.imageView.setImageBitmap(bitmap);
        this.dialog.hide();
    }

    @Override
    protected void onProgressUpdate(Float... values) {
        super.onProgressUpdate(values);
        this.dialog.setProgress(values[0].intValue());
    }
}
