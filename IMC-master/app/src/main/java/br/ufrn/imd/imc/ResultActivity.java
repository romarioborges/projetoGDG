package br.ufrn.imd.imc;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class ResultActivity extends ActionBarActivity {

    private static final boolean DEBUG = true;
    private static     final String TAG = "ResultActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView tv = (TextView) findViewById(R.id.result_result);

        float imc = getIntent().getFloatExtra("result", -1f);
        float height = getIntent().getFloatExtra("height", -1f);
        float weight = getIntent().getFloatExtra("weight", -1f);

        NumberFormat format = DecimalFormat.getNumberInstance();
        format.setMaximumFractionDigits(2);
        tv.setText(format.format(imc));

        int imgResource = -1;

        if (imc <= 18.5) {
            if (height < 1.70f) { //estaturas
                imgResource = R.mipmap.tall_skinny;
                tv.setText(format.format(imc) + " Abaixo do peso.");

            } else {
                imgResource = R.mipmap.tall_skinny_2;
                tv.setText(format.format(imc) + " Abaixo do peso.");
            }
        } else if ((imc >= 18.5 ) && (imc <= 24.9)) {
            if (height < 1.70f) { //estaturas
                imgResource = R.mipmap.normal;
                tv.setText(format.format(imc) + " Peso normal.");
            } else {
                imgResource = R.mipmap.normal;
                tv.setText(format.format(imc) + " Peso normal.");
            }
        } else if ((imc >= 25) && (imc <=29.9 )) {
            if (height < 1.70f) {
                imgResource = R.mipmap.muscle;
                tv.setText(format.format(imc) + " Sobrepeso.");
            } else {
                imgResource = R.mipmap.muscle;
                tv.setText(format.format(imc) + " Sobrepeso.");
            }

        } else if ((imc >= 30) && (imc <=34.9 )) {
            if (height < 1.70f) {
                imgResource = R.mipmap.fat;
                tv.setText(format.format(imc) + " Obeso grau I.");
            } else {
                imgResource = R.mipmap.fat;
                tv.setText(format.format(imc) + " Obeso grau I.");

        }

        } else if ((imc >= 35) && (imc <=39.9 )) {
            if (height < 1.70f) {
                imgResource = R.mipmap.small_fat;
                tv.setText(format.format(imc) + " Obeso grau II.");
            } else {
                imgResource = R.mipmap.small_fat;
                tv.setText(format.format(imc) + " Obeso grau II.");

            }
        } else {
            if (height < 1.70f) {
                imgResource = R.mipmap.small_obese;
                tv.setText(format.format(imc) + " Obeso grau III ou Mórbido.");
            } else {
                imgResource = R.mipmap.small_obese;
                tv.setText(format.format(imc) + " Obeso grau III ou Mórbido.");
            }
        }

        ImageView img = (ImageView) findViewById(R.id.result_img_result);
        img.setImageResource(imgResource);

        if (DEBUG) {
            Log.d(TAG, "weight = " + weight);
            Log.d(TAG, "height = " + height);
            Log.d(TAG, "imc = " + imc);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
