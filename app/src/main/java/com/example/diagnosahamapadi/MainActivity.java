package com.example.diagnosahamapadi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnProsess;
    TextView tcOutput;
    CheckBox chkGejala1,chkGejala2,chkGejala3,chkGejala4,chkGejala5,chkGejala6,chkGejala7;
    AutoCompleteTextView txxtNilaiGejala1,txxtNilaiGejala2,txxtNilaiGejala3,txxtNilaiGejala4,txxtNilaiGejala5,txxtNilaiGejala6,txxtNilaiGejala7;


    String[] trustedValueGej1 = {"","0","0.2","0.4","0.6","0.8","1"};
    String[] trustedValueGej2 = {"","0","0.2","0.4","0.6","0.8","1"};
    String[] trustedValueGej3 = {"","0","0.2","0.4","0.6","0.8","1"};
    String[] trustedValueGej4 = {"","0","0.2","0.4","0.6","0.8","1"};
    String[] trustedValueGej5 = {"","0","0.2","0.4","0.6","0.8","1"};
    String[] trustedValueGej6 = {"","0","0.2","0.4","0.6","0.8","1"};
    String[] trustedValueGej7 = {"","0","0.2","0.4","0.6","0.8","1"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnProsess = findViewById(R.id.buttonCalculate);
        tcOutput = findViewById(R.id.textViewResult);

        chkGejala1 = findViewById(R.id.checkBox1);
        chkGejala2 = findViewById(R.id.checkBox2);
        chkGejala3 = findViewById(R.id.checkBox3);
        chkGejala4 = findViewById(R.id.checkBox4);
        chkGejala5 = findViewById(R.id.checkBox5);
        chkGejala6 = findViewById(R.id.checkBox6);
        chkGejala7 = findViewById(R.id.checkBox7);


        txxtNilaiGejala1 = findViewById(R.id.autoCompleteTextView1);
        txxtNilaiGejala2 = findViewById(R.id.autoCompleteTextView2);
        txxtNilaiGejala3 = findViewById(R.id.autoCompleteTextView3);
        txxtNilaiGejala4 = findViewById(R.id.autoCompleteTextView4);
        txxtNilaiGejala5 = findViewById(R.id.autoCompleteTextView5);
        txxtNilaiGejala6 = findViewById(R.id.autoCompleteTextView6);
        txxtNilaiGejala7 = findViewById(R.id.autoCompleteTextView7);

        ArrayAdapter<String> adapterGejala1 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala1.setThreshold(1);
        txxtNilaiGejala1.setAdapter(adapterGejala1);

        ArrayAdapter<String> adapterGejala2 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala2.setThreshold(1);
        txxtNilaiGejala2.setAdapter(adapterGejala2);

        ArrayAdapter<String> adapterGejala3 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala3.setThreshold(1);
        txxtNilaiGejala3.setAdapter(adapterGejala3);

        ArrayAdapter<String> adapterGejala4 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala4.setThreshold(1);
        txxtNilaiGejala4.setAdapter(adapterGejala4);

        ArrayAdapter<String> adapterGejala5 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala5.setThreshold(1);
        txxtNilaiGejala5.setAdapter(adapterGejala5);

        ArrayAdapter<String> adapterGejala6 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala6.setThreshold(1);
        txxtNilaiGejala6.setAdapter(adapterGejala6);

        ArrayAdapter<String> adapterGejala7 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala7.setThreshold(1);
        txxtNilaiGejala7.setAdapter(adapterGejala7);


    }
}