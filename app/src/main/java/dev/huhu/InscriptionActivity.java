package dev.huhu;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by Clara on 22/05/2015.
 */
public class InscriptionActivity extends Activity{

    private String array_spinner[];
    private static final String DEFAULT_LOCAL = "France";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        array_spinner = getResources().getStringArray(R.array.country_arrays);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array_spinner);
        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getPosition(DEFAULT_LOCAL));




    }

}
