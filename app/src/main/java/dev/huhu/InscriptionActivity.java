package dev.huhu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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


        Button but2 = (Button) findViewById(R.id.button2);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InscriptionActivity.this,SuccessLogin.class);
                startActivity(intent);
            }
        });

    }

}
