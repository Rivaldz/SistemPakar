package com.example.diagnosahamapadi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
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


import com.example.diagnosahamapadi.adapter.RecyclerViewAdapter;
//import com.example.model.Fuzzy;
import com.example.fuzzy.Defuzzyfikasi;
import com.example.fuzzy.FungsiImplikasi;
import com.example.fuzzy.Fuzzy;
import com.example.model.DataClass;
import com.example.model.Gejala;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    List<DataClass> gejalaList = new ArrayList<>();
    DatabaseReference databaseReference;
    private ArrayList<Gejala> dataGejala;

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;


    // this navigation layout
    private DrawerLayout draw;

    //this CF
    Button btnProsess;
    TextView tcOutput;
    public CheckBox chkGejala1,chkGejala2,chkGejala3,chkGejala4,chkGejala5,chkGejala6,chkGejala7,chkGejala8,chkGejala9,chkGejala10,chkGejala11,chkGejala12,chkGejala13
            ,chkGejala14,chkGejala15,chkGejala16,chkGejala17,chkGejala18,chkGejala19,chkGejala20,chkGejala21,chkGejala22,chkGejala23,chkGejala24,chkGejala25,chkGejala26;

    public AutoCompleteTextView txxtNilaiGejala1,txxtNilaiGejala2,txxtNilaiGejala3,txxtNilaiGejala4,txxtNilaiGejala5,txxtNilaiGejala6,txxtNilaiGejala7,txxtNilaiGejala8
            ,txxtNilaiGejala9,txxtNilaiGejala10,txxtNilaiGejala11,txxtNilaiGejala12,txxtNilaiGejala13,txxtNilaiGejala14,txxtNilaiGejala15,txxtNilaiGejala16,txxtNilaiGejala17
            ,txxtNilaiGejala18,txxtNilaiGejala19,txxtNilaiGejala20;


    String[] trustedValueGej1 = {"","0.2","0.4","0.6","0.8","1"};
    String[] trustedValueGej2 = {"","0.2","0.4","0.6","0.8","1"};
    String[] trustedValueGej3 = {"","0.2","0.4","0.6","0.8","1"};
    String[] trustedValueGej4 = {"","0.2","0.4","0.6","0.8","1"};
    String[] trustedValueGej5 = {"","0.2","0.4","0.6","0.8","1"};
    String[] trustedValueGej6 = {"","0.2","0.4","0.6","0.8","1"};
    String[] trustedValueGej7 = {"","0.2","0.4","0.6","0.8","1"};
    String[] trustedValueGej8 = {"","0.2","0.4","0.6","0.8","1"};
    String[] trustedValueGej9 = {"","0.2","0.4","0.6","0.8","1"};
    String[] trustedValueGej10 = {"","0.2","0.4","0.6","0.8","1"};
    String[] trustedValueGej11 = {"","0.2","0.4","0.6","0.8","1"};
    String[] trustedValueGej12 = {"","0.2","0.4","0.6","0.8","1"};
    String[] trustedValueGej13 = {"","0.2","0.4","0.6","0.8","1"};
    String[] trustedValueGej14 = {"","0.2","0.4","0.6","0.8","1"};
    String[] trustedValueGej15 = {"","0.2","0.4","0.6","0.8","1"};
    String[] trustedValueGej16 = {"","0.2","0.4","0.6","0.8","1"};
    String[] trustedValueGej17 = {"","0.2","0.4","0.6","0.8","1"};
    String[] trustedValueGej18 = {"","0.2","0.4","0.6","0.8","1"};
    String[] trustedValueGej19 = {"","0.2","0.4","0.6","0.8","1"};
    String[] trustedValueGej20 = {"","0.2","0.4","0.6","0.8","1"};





    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        recyclerView = findViewById(R.id.recycler);

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
        chkGejala8 = findViewById(R.id.checkBox8);
        chkGejala9 = findViewById(R.id.checkBox9);
        chkGejala10 = findViewById(R.id.checkBox10);
        chkGejala11 = findViewById(R.id.checkBox11);

        txxtNilaiGejala1 = findViewById(R.id.autoCompleteTextView1);
        txxtNilaiGejala2 = findViewById(R.id.autoCompleteTextView2);
        txxtNilaiGejala3 = findViewById(R.id.autoCompleteTextView3);
        txxtNilaiGejala4 = findViewById(R.id.autoCompleteTextView4);
        txxtNilaiGejala5 = findViewById(R.id.autoCompleteTextView5);
        txxtNilaiGejala6 = findViewById(R.id.autoCompleteTextView6);
        txxtNilaiGejala7 = findViewById(R.id.autoCompleteTextView7);
        txxtNilaiGejala8 = findViewById(R.id.autoCompleteTextView8);
        txxtNilaiGejala9 = findViewById(R.id.autoCompleteTextView9);
        txxtNilaiGejala10 = findViewById(R.id.autoCompleteTextView10);
        txxtNilaiGejala11 = findViewById(R.id.autoCompleteTextView11);
        txxtNilaiGejala12 = findViewById(R.id.autoCompleteTextView12);
        txxtNilaiGejala13 = findViewById(R.id.autoCompleteTextView13);
        txxtNilaiGejala14 = findViewById(R.id.autoCompleteTextView14);


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

        final ArrayAdapter<String> adapterGejala8 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala8.setThreshold(1);
        txxtNilaiGejala8.setAdapter(adapterGejala8);

        final ArrayAdapter<String> adapterGejala9 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala9.setThreshold(1);
        txxtNilaiGejala9.setAdapter(adapterGejala9);

        final ArrayAdapter<String> adapterGejala10 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala10.setThreshold(1);
        txxtNilaiGejala10.setAdapter(adapterGejala10);

        final ArrayAdapter<String> adapterGejala11 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala11.setThreshold(1);
        txxtNilaiGejala11.setAdapter(adapterGejala11);

        final ArrayAdapter<String> adapterGejala12 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala12.setThreshold(1);
        txxtNilaiGejala12.setAdapter(adapterGejala12);

        final ArrayAdapter<String> adapterGejala13 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala13.setThreshold(1);
        txxtNilaiGejala13.setAdapter(adapterGejala13);

        final ArrayAdapter<String> adapterGejala14 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala14.setThreshold(1);
        txxtNilaiGejala14.setAdapter(adapterGejala14);

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

         txxtNilaiGejala8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala8, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala8.setText(trustedValueGej8[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });

         txxtNilaiGejala9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala9, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala9.setText(trustedValueGej9[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });

         txxtNilaiGejala10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala10, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala10.setText(trustedValueGej10[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });

        btnProsess.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Penyakit1
               if (chkGejala1.isChecked() && chkGejala2.isChecked() && chkGejala4.isChecked()){
                    List<String>stringBobotPenyakit1 = new ArrayList<>();

                   databaseReference = FirebaseDatabase.getInstance().getReference().child("Database").child("Pentakit T01");
                   databaseReference.addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot snapshot) {
                           for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                               DataClass gejala = dataSnapshot.getValue(DataClass.class);
                               stringBobotPenyakit1.add(gejala.getBobotGejala());

                           }
                           String namaPentakit = "penyakit";
                           double valueUserGejala1 = Double.parseDouble(txxtNilaiGejala1.getText().toString());
                           double valueUserGejala2 = Double.parseDouble(txxtNilaiGejala2.getText().toString());
                           double valueUserGejala4 = Double.parseDouble(txxtNilaiGejala4.getText().toString());

                           double valueGejala1 = Double.parseDouble(stringBobotPenyakit1.get(0));
                           double valueGejala2 = Double.parseDouble(stringBobotPenyakit1.get(1));
                           double valueGejala4 = Double.parseDouble(stringBobotPenyakit1.get(2));

                           double resultCalculateGej1 = valueGejala1 * valueUserGejala1;
                           double resultCalculateGej2 = valueGejala2 * valueUserGejala2;
                           double resultCalculateGej4 = valueGejala4 * valueUserGejala4;

                           double combine_CF1_CF2 = resultCalculateGej1 + resultCalculateGej2 * (1 - resultCalculateGej1);
                           double combine_CFold_CF4 = combine_CF1_CF2 + resultCalculateGej4 * (1 - combine_CF1_CF2);

                           String endResult = String.valueOf((combine_CFold_CF4 * 100));

                           Fuzzy fuzzy = new Fuzzy();
                           // counting fuzzyfikasi
                           fuzzy.penyakitH007_User(valueUserGejala1, valueUserGejala2, valueUserGejala4);
                           fuzzy.penyakitH007_Pakar(valueGejala1, valueGejala2, valueGejala4);

                           // fungsi implikasi
//                           try {
//                               TimeUnit.SECONDS.sleep(2);
//                           } catch (InterruptedException e) {
//                               e.printStackTrace();
//                           }
//                           FungsiImplikasi fungsiImplikasi = new FungsiImplikasi();
//                           fungsiImplikasi.penyakitH007();
                           namaPentakit = "Test Result Penyakit " + "\n" + endResult + " %";
                           Log.i("This value gejala",stringBobotPenyakit1.get(2));

                           try {
                               TimeUnit.SECONDS.sleep(2);
                           } catch (InterruptedException e) {
                               e.printStackTrace();
                           }
                           Defuzzyfikasi defuzzyfikasi = new Defuzzyfikasi();
                           defuzzyfikasi.defuzzyFikasi();

                           tcOutput.setText(""+namaPentakit);
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError error) {

                       }
                   });

               }

               if (chkGejala1.isChecked()){

               }

            }
        });

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

    public void buttonData(View view) {
        startActivity(new Intent(MainActivity.this, MyListData.class));
    }
}