package com.oesia.agarciao.helloworld;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final static int RESULT_CODE_RESULTADO_1 = 1;
    public final static String DATOS_ENVIADOS = "datos_enviados";

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
}
