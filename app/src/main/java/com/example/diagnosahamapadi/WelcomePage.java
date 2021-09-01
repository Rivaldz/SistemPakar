package com.example.diagnosahamapadi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmen_selamat_datang);
    }

    public void deteksiHamaButton(View view) {
        Intent mainActivity = new Intent(WelcomePage.this, MainActivity.class);
        startActivity(mainActivity);
    }
}