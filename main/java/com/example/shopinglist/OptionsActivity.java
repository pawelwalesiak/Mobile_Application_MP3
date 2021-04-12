package com.example.shopinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class OptionsActivity extends AppCompatActivity {

    Switch redSwitch;
    Switch fontSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        redSwitch = findViewById(R.id.colorSwitch);
        boolean color = getSharedPreferences("options", 0).getBoolean("color", false);
        redSwitch.setChecked(color);

        fontSwitch = findViewById(R.id.sizeSwitch);
        boolean bigFont = getSharedPreferences("options", 0).getBoolean("bigFont", false);
        fontSwitch.setChecked(bigFont);
    }
    public void saveSettings(View view){

        SharedPreferences sharedPreferences = getSharedPreferences("options", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        boolean color = redSwitch.isChecked();
        editor.putBoolean("color", color);

        boolean size = fontSwitch.isChecked();
        editor.putBoolean("bigFont", size);
        editor.commit();

        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
