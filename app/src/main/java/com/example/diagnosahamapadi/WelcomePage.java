package com.example.diagnosahamapadi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WelcomePage extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    EditText username, address;
    Button deteksiHama;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmen_selamat_datang);
        username = findViewById(R.id.editTextTextPersonName);
        address = findViewById(R.id.editTextTextAddresName);
        deteksiHama = findViewById(R.id.button);
        sharedPreferences = getSharedPreferences("user_details",MODE_PRIVATE);
        sharedPreferences.contains("username");
        sharedPreferences.contains("address");

        deteksiHama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameSt = username.getText().toString();
                String addressSt = address.getText().toString();

                if (usernameSt.matches("") && addressSt.matches("")){
                    Toast.makeText(getBaseContext(),"Silahkan isi semua kolom",Toast.LENGTH_SHORT).show();
                }else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username",usernameSt);
                    editor.putString("address",addressSt);
                    editor.apply();

                    Toast.makeText(WelcomePage.this, "Berhasil Memuat Aksi",Toast.LENGTH_SHORT).show();
                    Intent mainActivity = new Intent(WelcomePage.this, MainActivity.class);
                    startActivity(mainActivity);
                }

            }
        });


    }

//    public void deteksiHamaButton(View view) {
//        Intent mainActivity = new Intent(WelcomePage.this, MainActivity.class);
//        startActivity(mainActivity);
//    }
}