package com.oesia.agarciao.helloworld;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getName();

    private final static int RESULT_CODE_RESULTADO_1 = 1;
    public final static String DATOS_ENVIADOS = "datos_enviados";

    private PeliculasSQLiteOpenHelper openHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.btnHello);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saludar(v);
            }
        });

        this.openHelper = new PeliculasSQLiteOpenHelper(this, getString(R.string.bdName), null, getResources().getInteger(R.integer.version));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //Lanzar las preferencias
            Intent intent = new Intent(this, PreferenciasActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void saludar(View view) {
        EditText etMainName = (EditText) findViewById(R.id.etMainName);
        TextView tvMainSaludo = (TextView) findViewById(R.id.tvName);

        tvMainSaludo.setText(getResources().getString(R.string.hello_world) + " " + etMainName.getText());
    }

    public void newTarget(View view) {
        Intent intent = new Intent(this, DestinoActivity.class);
        startActivity(intent);
    }

    public void actividadConResultado(View view) {
        Intent intent = new Intent(this, ResultadoActivity.class);
        startActivityForResult(intent, RESULT_CODE_RESULTADO_1);
    }

    public void enviarDatos(View view) {
        EditText editText = (EditText) findViewById(R.id.etMainName);
        String datos = editText.getText().toString();
        Intent intent = new Intent(this, ReceiverActivity.class);
        intent.putExtra(MainActivity.DATOS_ENVIADOS, datos);
        startActivityForResult(intent, RESULT_CODE_RESULTADO_1);

    }

    public void actividadImplicita(View view) {
        Intent intent = new Intent("com.oesia.agarciao.helloworld.IMPLICITA");
        startActivity(intent);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case MainActivity.RESULT_CODE_RESULTADO_1:
                    String dato = data.getStringExtra(ResultadoActivity.DATO);
                    TextView textView = (TextView) findViewById(R.id.tvName);
                    textView.setText(dato);
                    break;
            }
        }
    }

    public void llamarTelefono(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:639-333-905"));
        this.startActivity(intent);
    }

    public void openPreferences(View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String color = preferences.getString(getString(R.string.preferences_color), "default");

        Toast.makeText(this, "Preferencia: " + color, Toast.LENGTH_SHORT).show();

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void copiarFichero(View view) {
        InputStream is = getResources().openRawResource(R.raw.archivo);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader entrada = new BufferedReader(isr);
        try (FileOutputStream fos = openFileOutput("copia.txt", MODE_PRIVATE);
             OutputStreamWriter osw = new OutputStreamWriter(fos);
             BufferedWriter salida = new BufferedWriter(osw);
        ) {
            String linea = entrada.readLine();
            while (linea != null) {
                salida.write(linea);
                salida.newLine();
                linea = entrada.readLine();
            }
            salida.close();
            Log.i(TAG, " Copiar fichero() , archivo cerrado");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void copiarToSD(View view) {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            try (FileInputStream fis = openFileInput("copia.txt");
                 InputStreamReader isr = new InputStreamReader(fis);
                 BufferedReader entrada = new BufferedReader(isr);
            ) {
                File sd = Environment.getExternalStorageDirectory();
                File ficheroSalida = new File(sd.getAbsolutePath(), "copiaSD.txt");

                BufferedWriter salida = new BufferedWriter(new FileWriter(ficheroSalida));

                String linea = entrada.readLine();
                while (linea != null) {
                    salida.write(linea);
                    salida.newLine();
                    linea = entrada.readLine();
                }
                salida.close();
                Log.i(TAG, " Copiar ficheroToSD() , archivo cerrado");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            Toast.makeText(this, "Tarjeta SD no disponible", Toast.LENGTH_LONG).show();
        }
    }

    public void parserXml(View view) {
        XmlPullParser parser = Xml.newPullParser();
        try {
            parser.setInput(new InputStreamReader(getResources().openRawResource(R.raw.datos)));

            int event = parser.getEventType();
            Pelicula pelicula = null;
            while (event != XmlPullParser.END_DOCUMENT) {
                if (event == XmlPullParser.START_TAG) {
                    String tag = parser.getName();
                    switch (tag) {
                        case "pelicula":
                            pelicula = new Pelicula();
                            break;
                        case "titulo":
                            pelicula.setTitulo(parser.nextText());
                            break;
                        case "director":
                            pelicula.setDirector(parser.nextText());
                            break;
                        case "anho":
                            pelicula.setAnho(parser.nextText());
                            break;
                    }
                } else if (event == XmlPullParser.END_TAG) {
                    if (parser.getName().equals("pelicula")) {
                        SQLiteDatabase db = this.openHelper.getWritableDatabase();
                        try {
                            db.beginTransaction();
                            db.insert("Peliculas", "Titulo", peliculaToContentValues(pelicula));
                            db.setTransactionSuccessful();
                        } catch (Exception e) {
                            db.endTransaction();
                        }
                        Toast.makeText(this, "Datos copiados", Toast.LENGTH_LONG).show();
                    }
                }
                event = parser.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void selectDB(View view) {
        SQLiteDatabase db = this.openHelper.getReadableDatabase();
        String[] columnas = {"Titulo", "Director", "Anho"};
        String[] whereArgs = {"Anho2"};
        Cursor cursor = db.query(getString(R.string.tablePeliculas), columnas, "Anho = ?", whereArgs, null, null, null);
        List<Pelicula> resultado = cursorToPelicula(cursor);
        String mensaje = "PELIS: ";
        for (Pelicula pelicula : resultado) {
            mensaje += pelicula.getTitulo();
        }

        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }

    private List<Pelicula> cursorToPelicula(Cursor cursor) {
        List<Pelicula> lista = new ArrayList<>();
        while (cursor.moveToNext()) {
            Pelicula pelicula = new Pelicula();
            pelicula.setTitulo(cursor.getString(cursor.getColumnIndex("Titulo")));
            pelicula.setDirector(cursor.getString(cursor.getColumnIndex("Director")));
            pelicula.setAnho(cursor.getString(cursor.getColumnIndex("Anho")));
            lista.add(pelicula);
        }
        return lista;
    }

    private ContentValues peliculaToContentValues(Pelicula pelicula) {
        ContentValues values = new ContentValues();
        //Para meter un null hay que llamar a put.null.
        if (pelicula.getTitulo() == null) {
            values.putNull("Titulo");
        } else {
            values.put("Titulo", pelicula.getTitulo());
        }
        if (pelicula.getDirector() == null) {
            values.putNull("Director");
        } else {
            values.put("Director", pelicula.getDirector());
        }
        if (pelicula.getAnho() == null) {
            values.putNull("Anho");
        } else {
            values.put("Anho", pelicula.getAnho());
        }
        return values;
    }
}
