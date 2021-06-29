package com.example.diagnosahamapadi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.Fuzzy;

import com.example.model.Gejala;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

//    private  DatabaseReference reference;

//    private ArrayList<Gejala> dataGejala;
//    private ArrayList<Gejala> listGejala;
    List<Gejala> gejalaList = new ArrayList<>();
    DatabaseReference databaseReference;




    // this navigation layout

    private DrawerLayout draw;

    //this CF
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



        //this drawable layout
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        draw = findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle t = new ActionBarDrawerToggle(this, draw,
                R.string.open,
                R.string.close);

        draw.addDrawerListener(t);
        t.syncState();

        NavigationView navigationview = findViewById(R.id.nav_view);
        navigationview.setItemIconTintList(null);

        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id) {
                    case R.id.item1:
                        Toast.makeText(MainActivity.this,
                                "Item1 di klik", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item2:
                        Toast.makeText(MainActivity.this,
                                "Item2 di klik", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item3:
                        Toast.makeText(MainActivity.this,
                                "Item3 di klik", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item4:
                        Toast.makeText(MainActivity.this,
                                "Item4 di klik", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.manis1:
                        Toast.makeText(MainActivity.this,
                                "Manis1 di klik", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.manis2:
                        Toast.makeText(MainActivity.this,
                                "Manis2 di klik", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

        //This CF
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

        final ArrayAdapter<String> adapterGejala1 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala1.setThreshold(1);
        txxtNilaiGejala1.setAdapter(adapterGejala1);

        final ArrayAdapter<String> adapterGejala2 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala2.setThreshold(1);
        txxtNilaiGejala2.setAdapter(adapterGejala2);

        final ArrayAdapter<String> adapterGejala3 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala3.setThreshold(1);
        txxtNilaiGejala3.setAdapter(adapterGejala3);

        final ArrayAdapter<String> adapterGejala4 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala4.setThreshold(1);
        txxtNilaiGejala4.setAdapter(adapterGejala4);

        final ArrayAdapter<String> adapterGejala5 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala5.setThreshold(1);
        txxtNilaiGejala5.setAdapter(adapterGejala5);

        final ArrayAdapter<String> adapterGejala6 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala6.setThreshold(1);
        txxtNilaiGejala6.setAdapter(adapterGejala6);

        final ArrayAdapter<String> adapterGejala7 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala7.setThreshold(1);
        txxtNilaiGejala7.setAdapter(adapterGejala7);

        txxtNilaiGejala1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala1.setText(trustedValueGej1[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });

        txxtNilaiGejala2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala2, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala2.setText(trustedValueGej2[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });

        txxtNilaiGejala3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala3, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala3.setText(trustedValueGej3[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });

        txxtNilaiGejala4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala4, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala4.setText(trustedValueGej4[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });

        txxtNilaiGejala5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala5, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala5.setText(trustedValueGej5[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });

        txxtNilaiGejala6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala6, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala6.setText(trustedValueGej6[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });

        txxtNilaiGejala7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala7, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala7.setText(trustedValueGej7[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });

        btnProsess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String namaPentakit = "penyakit";
                //ex deases 1
               if (chkGejala1.isChecked() && chkGejala2.isChecked() && chkGejala4.isChecked()){

                   double valueGejala1 = 0.2;
                   double valueGejala2 = 0.6;
                   double valueGejala4 = 0.8;

                   double valueUserGejala1 = 0;

                   valueUserGejala1 = Double.parseDouble(txxtNilaiGejala1.getText().toString());


                   double valueUserGejala2 = Double.parseDouble(txxtNilaiGejala2.getText().toString());
                   double valueUserGejala4 = Double.parseDouble(txxtNilaiGejala4.getText().toString());

                   double resultCalculateGej1 = valueGejala1 * valueUserGejala1;
                   double resultCalculateGej2 = valueGejala2 * valueUserGejala2;
                   double resultCalculateGej4 = valueGejala4 * valueUserGejala4;

                   double combine_CF1_CF2 = resultCalculateGej1 + resultCalculateGej2 * (1 - resultCalculateGej1);
                   double combine_CFold_CF4 = combine_CF1_CF2 + resultCalculateGej4 * (1 - combine_CF1_CF2);

                   String endResult = String.valueOf((combine_CFold_CF4 * 100));

                   namaPentakit = "Test Result Penyakit " + "\n" + endResult + " %";
                   // logic for fuzzy mamdani

                   Fuzzy fuzzy= new Fuzzy();
                   fuzzy.diseaseBla(valueUserGejala1);
                   String severity = "Tingakt keparahan Penyakit " + "\n" + endResult + " %";
               }

               tcOutput.setText(""+namaPentakit);
            }
        });

        getData();
//        showData();

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            openDrawer();
            if (item.getItemId() == android.R.id.home) {
                if ((draw) != null && (draw.isDrawerOpen(GravityCompat.START)))
                    closeDrawer();
            }
        }
        return true;
    }

    @SuppressWarnings("deprecation")
    private void closeDrawer() {
        draw.setDrawerListener(null);
        draw.closeDrawers();
    }
    @SuppressWarnings("deprecation")
    private void openDrawer() {
        draw.setDrawerListener(null);
        draw.openDrawer(GravityCompat.START);
    }

    private void getData(){

        databaseReference = FirebaseDatabase.getInstance().getReference().child("students");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Gejala gejala = dataSnapshot.getValue(Gejala.class);
                    gejalaList.add(gejala);
                }

                showData();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        databaseReference.addValueEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//
//                    Gejala gejala = dataSnapshot.getValue(Gejala.class);
//                    gejalaList.add(gejala);
//                }
//
//                showData();
//
////                for (int j = 0; j < 3; j++) {
////                    System.out.println("ini data ke " + j + gejalaList.get(j).getName());
////                }
//
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
////                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
////
////                    Gejala gejala = dataSnapshot.getValue(Gejala.class);
////                    gejalaList.add(gejala);
////                }
////
////                showData();
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


    }

    private void showData(){
        for (int j = 0; j < 3; j++) {
            System.out.println("ini data ke " + j + gejalaList.get(j).getName());
        }
//        System.out.println("ini data " + gejalaList.get(2).getName());
    }
}