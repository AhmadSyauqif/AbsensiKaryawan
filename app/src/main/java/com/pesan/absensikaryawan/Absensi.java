package com.pesan.absensikaryawan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Absensi extends Activity implements View.OnClickListener {
    Button startButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absensi);
        startButton = (Button) findViewById(R.id.buttonStart);
        startButton.setOnClickListener(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.buttonStart : Intent i = new Intent(this, QRScanner.class);
                startActivity(i);
        }
    }
}