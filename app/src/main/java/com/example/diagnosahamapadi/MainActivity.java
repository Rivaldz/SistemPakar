package com.example.diagnosahamapadi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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


//import com.example.model.Fuzzy;
import com.example.diagnosahamapadi.webview.JenisPadi;
import com.example.diagnosahamapadi.webview.Pemasaran;
import com.example.diagnosahamapadi.webview.PenyakitHama;
import com.example.diagnosahamapadi.webview.TipsMenanam;
import com.example.fuzzy.Defuzzyfikasi;
import com.example.fuzzy.FungsiImplikasi;
import com.example.fuzzy.Fuzzy;
import com.example.model.DataClass;
import com.example.model.Gejala;
import com.example.model.LastModel;
import com.example.model.ShowUser;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {


    List<DataClass> gejalaList = new ArrayList<>();
    DatabaseReference databaseReference, databaseInserFM, databaseReferenceH001,
            databaseReferenceH002, databaseReferenceH003,databaseReferenceH004, databaseReferenceH005,databaseReferenceH006
            ,databaseReferenceH007,databaseReferenceH008,databaseReferenceH009,databaseReferenceH0010,databaseReferenceH0011,databaseReferenceH0012
            ,databaseReferenceH0013,databaseReferenceH0014,databaseReferenceH0015,databaseReferenceH0016,databaseReferenceH0017,databaseReferenceH0018
            ,databaseReferenceH0019;


    private ArrayList<Gejala> dataGejala;

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;

    // this navigation layout
    private DrawerLayout draw;

    //this CF
    Button btnProsess;
    TextView tcOutput;
    public CheckBox chkGejala1,chkGejala2,chkGejala3,chkGejala4,chkGejala5,chkGejala6,chkGejala7,chkGejala8,chkGejala9,chkGejala10,chkGejala11,chkGejala12,chkGejala13
            ,chkGejala14,chkGejala15,chkGejala16,chkGejala17,chkGejala18,chkGejala19,chkGejala20,chkGejala21,chkGejala22,chkGejala23,chkGejala24,chkGejala25,chkGejala26,
            chkGejala27,chkGejala28,chkGejala29,chkGejala30,chkGejala31,chkGejala32,chkGejala33,chkGejala34,chkGejala35,chkGejala36,chkGejala37,chkGejala38,chkGejala39,chkGejala40
            ,chkGejala41,chkGejala42,chkGejala43,chkGejala44,chkGejala45,chkGejala46,chkGejala47,chkGejala48,chkGejala49,chkGejala50,chkGejala51,chkGejala52,chkGejala53;

    public AutoCompleteTextView txxtNilaiGejala1,txxtNilaiGejala2,txxtNilaiGejala3,txxtNilaiGejala4,txxtNilaiGejala5,txxtNilaiGejala6,txxtNilaiGejala7,txxtNilaiGejala8
            ,txxtNilaiGejala9,txxtNilaiGejala10,txxtNilaiGejala11,txxtNilaiGejala12,txxtNilaiGejala13,txxtNilaiGejala14,txxtNilaiGejala15,txxtNilaiGejala16,txxtNilaiGejala17
            ,txxtNilaiGejala18,txxtNilaiGejala19,txxtNilaiGejala20,txxtNilaiGejala21,txxtNilaiGejala22,txxtNilaiGejala23,txxtNilaiGejala24,txxtNilaiGejala25,txxtNilaiGejala26
            ,txxtNilaiGejala27,txxtNilaiGejala28,txxtNilaiGejala29,txxtNilaiGejala30,txxtNilaiGejala31,txxtNilaiGejala32,txxtNilaiGejala33,txxtNilaiGejala34,txxtNilaiGejala35,txxtNilaiGejala36
            ,txxtNilaiGejala37,txxtNilaiGejala38,txxtNilaiGejala39,txxtNilaiGejala40,txxtNilaiGejala41,txxtNilaiGejala42,txxtNilaiGejala43,txxtNilaiGejala44,txxtNilaiGejala45
            ,txxtNilaiGejala46,txxtNilaiGejala47,txxtNilaiGejala48,txxtNilaiGejala49,txxtNilaiGejala50,txxtNilaiGejala51,txxtNilaiGejala52,txxtNilaiGejala53;

    private Button penyakitDanHama, tipsMenanam, pemasaran, jenisTanaman;

    String[] trustedValueGej1 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej2 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej3 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej4 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej5 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej6 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej7 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej8 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej9 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej10 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej11 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej12 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej13 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej14 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej15 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej16 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej17 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej18 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej19 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej20 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej21 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej22 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej23 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej24 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej25 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej26 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej27 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej28 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej29 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej30 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej31 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej32 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej33 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej34 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej35 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej36 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej37 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej38 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej39 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej40 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej41 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej42 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej43 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej44 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej45 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej46 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej47 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej48 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej49 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej50 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej51 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej52 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};
    String[] trustedValueGej53 = {"","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","1"};

    SharedPreferences sharedPreferences, sharedPreferencesSession;
    String userId = null;
    int sessionId = 0;
    public String sessionString = null;
    private DecimalFormat df = new DecimalFormat("#.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("user_details",MODE_PRIVATE);
        userId = sharedPreferences.getString("username",null);

        sharedPreferencesSession = getSharedPreferences("session",MODE_PRIVATE);
        sharedPreferencesSession.contains("varSession");

        Log.e("LIHAT USERNAME",userId);

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
                                "Halaman Home", Toast.LENGTH_SHORT).show();
                        findViewById(R.id.layoutKnw).setVisibility(View.GONE);
                        findViewById(R.id.layoutMain).setVisibility(View.VISIBLE);
                        findViewById(R.id.layoutHistory).setVisibility(View.GONE);
                        break;

                    case R.id.item2:
                        Toast.makeText(MainActivity.this,
                                "Halaman Budidaya", Toast.LENGTH_SHORT).show();
                        findViewById(R.id.layoutKnw).setVisibility(View.VISIBLE);
                        findViewById(R.id.layoutMain).setVisibility(View.GONE);
                        findViewById(R.id.layoutHistory).setVisibility(View.GONE);
                        break;
                    case R.id.item3:
                        Toast.makeText(MainActivity.this,
                                "Tentang Aplikasi", Toast.LENGTH_SHORT).show();
                        findViewById(R.id.layoutHistory).setVisibility(View.VISIBLE);
                        findViewById(R.id.layoutMain).setVisibility(View.GONE);
                        findViewById(R.id.layoutKnw).setVisibility(View.GONE);
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
        chkGejala12 = findViewById(R.id.checkBox12);
        chkGejala13 = findViewById(R.id.checkBox13);
        chkGejala14 = findViewById(R.id.checkBox14);
        chkGejala15 = findViewById(R.id.checkBox15);
        chkGejala16 = findViewById(R.id.checkBox16);
        chkGejala17 = findViewById(R.id.checkBox17);
        chkGejala18 = findViewById(R.id.checkBox18);
        chkGejala19 = findViewById(R.id.checkBox19);
        chkGejala20 = findViewById(R.id.checkBox20);
        chkGejala21 = findViewById(R.id.checkBox21);
        chkGejala22 = findViewById(R.id.checkBox22);
        chkGejala23 = findViewById(R.id.checkBox23);
        chkGejala24 = findViewById(R.id.checkBox24);
        chkGejala25 = findViewById(R.id.checkBox25);
        chkGejala26 = findViewById(R.id.checkBox26);
        chkGejala27 = findViewById(R.id.checkBox27);
        chkGejala28 = findViewById(R.id.checkBox28);
        chkGejala29 = findViewById(R.id.checkBox29);
        chkGejala30 = findViewById(R.id.checkBox30);
        chkGejala31 = findViewById(R.id.checkBox31);
        chkGejala32 = findViewById(R.id.checkBox32);
        chkGejala33 = findViewById(R.id.checkBox33);
        chkGejala34 = findViewById(R.id.checkBox34);
        chkGejala35 = findViewById(R.id.checkBox35);
        chkGejala36 = findViewById(R.id.checkBox36);
        chkGejala37 = findViewById(R.id.checkBox37);
        chkGejala38 = findViewById(R.id.checkBox38);
        chkGejala39 = findViewById(R.id.checkBox39);
        chkGejala40 = findViewById(R.id.checkBox40);
        chkGejala41 = findViewById(R.id.checkBox41);
        chkGejala42 = findViewById(R.id.checkBox42);
        chkGejala43 = findViewById(R.id.checkBox43);
        chkGejala44 = findViewById(R.id.checkBox44);
        chkGejala45 = findViewById(R.id.checkBox45);
        chkGejala46 = findViewById(R.id.checkBox46);
        chkGejala47 = findViewById(R.id.checkBox47);
        chkGejala48 = findViewById(R.id.checkBox48);
        chkGejala49 = findViewById(R.id.checkBox49);
        chkGejala50 = findViewById(R.id.checkBox50);
        chkGejala51 = findViewById(R.id.checkBox51);
        chkGejala52 = findViewById(R.id.checkBox52);
        chkGejala53 = findViewById(R.id.checkBox53);

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
        txxtNilaiGejala15 = findViewById(R.id.autoCompleteTextView15);
        txxtNilaiGejala16 = findViewById(R.id.autoCompleteTextView16);
        txxtNilaiGejala17 = findViewById(R.id.autoCompleteTextView17);
        txxtNilaiGejala18 = findViewById(R.id.autoCompleteTextView18);
        txxtNilaiGejala19 = findViewById(R.id.autoCompleteTextView19);
        txxtNilaiGejala20 = findViewById(R.id.autoCompleteTextView20);
        txxtNilaiGejala21 = findViewById(R.id.autoCompleteTextView21);
        txxtNilaiGejala22 = findViewById(R.id.autoCompleteTextView22);
        txxtNilaiGejala23 = findViewById(R.id.autoCompleteTextView23);
        txxtNilaiGejala24 = findViewById(R.id.autoCompleteTextView24);
        txxtNilaiGejala25 = findViewById(R.id.autoCompleteTextView25);
        txxtNilaiGejala26 = findViewById(R.id.autoCompleteTextView26);
        txxtNilaiGejala27 = findViewById(R.id.autoCompleteTextView27);
        txxtNilaiGejala28 = findViewById(R.id.autoCompleteTextView28);
        txxtNilaiGejala29 = findViewById(R.id.autoCompleteTextView29);
        txxtNilaiGejala30 = findViewById(R.id.autoCompleteTextView30);
        txxtNilaiGejala31 = findViewById(R.id.autoCompleteTextView31);
        txxtNilaiGejala32 = findViewById(R.id.autoCompleteTextView32);
        txxtNilaiGejala33 = findViewById(R.id.autoCompleteTextView33);
        txxtNilaiGejala34 = findViewById(R.id.autoCompleteTextView34);
        txxtNilaiGejala35 = findViewById(R.id.autoCompleteTextView35);
        txxtNilaiGejala36 = findViewById(R.id.autoCompleteTextView36);
        txxtNilaiGejala37 = findViewById(R.id.autoCompleteTextView37);
        txxtNilaiGejala38 = findViewById(R.id.autoCompleteTextView38);
        txxtNilaiGejala39 = findViewById(R.id.autoCompleteTextView39);
        txxtNilaiGejala40 = findViewById(R.id.autoCompleteTextView40);
        txxtNilaiGejala41 = findViewById(R.id.autoCompleteTextView41);
        txxtNilaiGejala42 = findViewById(R.id.autoCompleteTextView42);
        txxtNilaiGejala43 = findViewById(R.id.autoCompleteTextView43);
        txxtNilaiGejala44 = findViewById(R.id.autoCompleteTextView44);
        txxtNilaiGejala45 = findViewById(R.id.autoCompleteTextView45);
        txxtNilaiGejala46 = findViewById(R.id.autoCompleteTextView46);
        txxtNilaiGejala47 = findViewById(R.id.autoCompleteTextView47);
        txxtNilaiGejala48 = findViewById(R.id.autoCompleteTextView48);
        txxtNilaiGejala49 = findViewById(R.id.autoCompleteTextView49);
        txxtNilaiGejala50 = findViewById(R.id.autoCompleteTextView50);
        txxtNilaiGejala51 = findViewById(R.id.autoCompleteTextView51);
        txxtNilaiGejala52 = findViewById(R.id.autoCompleteTextView52);
        txxtNilaiGejala53 = findViewById(R.id.autoCompleteTextView53);

        penyakitDanHama = findViewById(R.id.imageViewPenyakitHama);
        tipsMenanam     = findViewById(R.id.imageViewTipsMenanam);
        pemasaran       = findViewById(R.id.imageViewPemasaran);
        jenisTanaman    = findViewById(R.id.imageViewJenisTanaman);

        penyakitDanHama.setOnClickListener((View.OnClickListener) this);
        tipsMenanam.setOnClickListener((View.OnClickListener) this);
        pemasaran.setOnClickListener((View.OnClickListener) this);
        jenisTanaman.setOnClickListener((View.OnClickListener) this);

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

        final ArrayAdapter<String> adapterGejala15 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala15.setThreshold(1);
        txxtNilaiGejala15.setAdapter(adapterGejala15);

        final ArrayAdapter<String> adapterGejala16 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala16.setThreshold(1);
        txxtNilaiGejala16.setAdapter(adapterGejala16);

        final ArrayAdapter<String> adapterGejala17 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala17.setThreshold(1);
        txxtNilaiGejala17.setAdapter(adapterGejala17);

        final ArrayAdapter<String> adapterGejala18 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala18.setThreshold(1);
        txxtNilaiGejala18.setAdapter(adapterGejala18);

        final ArrayAdapter<String> adapterGejala19 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala19.setThreshold(1);
        txxtNilaiGejala19.setAdapter(adapterGejala19);

        final ArrayAdapter<String> adapterGejala20 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala20.setThreshold(1);
        txxtNilaiGejala20.setAdapter(adapterGejala20);

        final ArrayAdapter<String> adapterGejala21 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala21.setThreshold(1);
        txxtNilaiGejala21.setAdapter(adapterGejala21);

        final ArrayAdapter<String> adapterGejala22 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala22.setThreshold(1);
        txxtNilaiGejala22.setAdapter(adapterGejala22);

        final ArrayAdapter<String> adapterGejala23 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala24.setThreshold(1);
        txxtNilaiGejala24.setAdapter(adapterGejala23);

        final ArrayAdapter<String> adapterGejala24 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala24.setThreshold(1);
        txxtNilaiGejala24.setAdapter(adapterGejala24);

        final ArrayAdapter<String> adapterGejala25 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala25.setThreshold(1);
        txxtNilaiGejala25.setAdapter(adapterGejala25);

        final ArrayAdapter<String> adapterGejala26 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala26.setThreshold(1);
        txxtNilaiGejala26.setAdapter(adapterGejala26);

        final ArrayAdapter<String> adapterGejala27 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala27.setThreshold(1);
        txxtNilaiGejala27.setAdapter(adapterGejala27);

        final ArrayAdapter<String> adapterGejala28 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala28.setThreshold(1);
        txxtNilaiGejala28.setAdapter(adapterGejala28);

        final ArrayAdapter<String> adapterGejala29 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala29.setThreshold(1);
        txxtNilaiGejala29.setAdapter(adapterGejala29);

        final ArrayAdapter<String> adapterGejala30 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala30.setThreshold(1);
        txxtNilaiGejala30.setAdapter(adapterGejala30);

        final ArrayAdapter<String> adapterGejala31 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala31.setThreshold(1);
        txxtNilaiGejala31.setAdapter(adapterGejala31);

        final ArrayAdapter<String> adapterGejala32 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala32.setThreshold(1);
        txxtNilaiGejala32.setAdapter(adapterGejala32);

        final ArrayAdapter<String> adapterGejala33 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala33.setThreshold(1);
        txxtNilaiGejala33.setAdapter(adapterGejala33);

         final ArrayAdapter<String> adapterGejala34 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala34.setThreshold(1);
        txxtNilaiGejala34.setAdapter(adapterGejala34);

        final ArrayAdapter<String> adapterGejala35 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala35.setThreshold(1);
        txxtNilaiGejala35.setAdapter(adapterGejala35);

        final ArrayAdapter<String> adapterGejala36 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala36.setThreshold(1);
        txxtNilaiGejala36.setAdapter(adapterGejala36);

        final ArrayAdapter<String> adapterGejala37 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala37.setThreshold(1);
        txxtNilaiGejala37.setAdapter(adapterGejala37);

        final ArrayAdapter<String> adapterGejala38 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala38.setThreshold(1);
        txxtNilaiGejala38.setAdapter(adapterGejala38);

        final ArrayAdapter<String> adapterGejala39 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala39.setThreshold(1);
        txxtNilaiGejala39.setAdapter(adapterGejala39);

        final ArrayAdapter<String> adapterGejala40 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala40.setThreshold(1);
        txxtNilaiGejala40.setAdapter(adapterGejala40);

        final ArrayAdapter<String> adapterGejala41 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala41.setThreshold(1);
        txxtNilaiGejala41.setAdapter(adapterGejala41);

        final ArrayAdapter<String> adapterGejala42 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala42.setThreshold(1);
        txxtNilaiGejala42.setAdapter(adapterGejala42);

        final ArrayAdapter<String> adapterGejala43 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala43.setThreshold(1);
        txxtNilaiGejala43.setAdapter(adapterGejala43);

        final ArrayAdapter<String> adapterGejala44 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala44.setThreshold(1);
        txxtNilaiGejala44.setAdapter(adapterGejala44);

        final ArrayAdapter<String> adapterGejala45 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala45.setThreshold(1);
        txxtNilaiGejala45.setAdapter(adapterGejala45);

        final ArrayAdapter<String> adapterGejala46 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala46.setThreshold(1);
        txxtNilaiGejala46.setAdapter(adapterGejala46);

        final ArrayAdapter<String> adapterGejala47 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala47.setThreshold(1);
        txxtNilaiGejala47.setAdapter(adapterGejala47);

        final ArrayAdapter<String> adapterGejala48 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala48.setThreshold(1);
        txxtNilaiGejala48.setAdapter(adapterGejala48);

        final ArrayAdapter<String> adapterGejala49 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala49.setThreshold(1);
        txxtNilaiGejala49.setAdapter(adapterGejala49);

        final ArrayAdapter<String> adapterGejala50 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala50.setThreshold(1);
        txxtNilaiGejala50.setAdapter(adapterGejala50);

        final ArrayAdapter<String> adapterGejala51 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala51.setThreshold(1);
        txxtNilaiGejala51.setAdapter(adapterGejala51);

        final ArrayAdapter<String> adapterGejala52 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala52.setThreshold(1);
        txxtNilaiGejala52.setAdapter(adapterGejala52);

        final ArrayAdapter<String> adapterGejala53 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, trustedValueGej1);
        txxtNilaiGejala53.setThreshold(1);
        txxtNilaiGejala53.setAdapter(adapterGejala53);

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
        txxtNilaiGejala11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala11, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala11.setText(trustedValueGej11[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala12, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala12.setText(trustedValueGej12[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala13, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala13.setText(trustedValueGej13[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala14, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala14.setText(trustedValueGej14[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala15, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala15.setText(trustedValueGej15[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala16, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala16.setText(trustedValueGej16[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala17, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala17.setText(trustedValueGej17[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala18, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala18.setText(trustedValueGej18[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala19, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala19.setText(trustedValueGej19[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala20, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala20.setText(trustedValueGej20[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala21, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala21.setText(trustedValueGej21[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala22, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala22.setText(trustedValueGej22[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala23, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala23.setText(trustedValueGej23[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala24, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala24.setText(trustedValueGej24[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala25, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala25.setText(trustedValueGej25[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala26, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala26.setText(trustedValueGej26[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala27, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala27.setText(trustedValueGej27[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala28, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala28.setText(trustedValueGej28[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala29, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala29.setText(trustedValueGej29[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala30, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala30.setText(trustedValueGej30[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala31, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala31.setText(trustedValueGej31[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala32, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala32.setText(trustedValueGej32[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala33, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala33.setText(trustedValueGej33[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala34, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala34.setText(trustedValueGej34[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala35, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala35.setText(trustedValueGej35[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala36, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala36.setText(trustedValueGej36[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala37, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala37.setText(trustedValueGej37[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala38, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala38.setText(trustedValueGej38[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala39.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala39, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala39.setText(trustedValueGej39[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala40, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala40.setText(trustedValueGej40[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala41, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala41.setText(trustedValueGej41[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala42, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala42.setText(trustedValueGej42[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala43, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala43.setText(trustedValueGej43[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala44, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala44.setText(trustedValueGej44[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala45, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala45.setText(trustedValueGej45[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala46.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala46, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala46.setText(trustedValueGej46[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala47.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala47, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala47.setText(trustedValueGej47[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala48.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala48, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala48.setText(trustedValueGej48[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala49.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala49, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala49.setText(trustedValueGej49[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala50, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala50.setText(trustedValueGej50[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala51, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala51.setText(trustedValueGej51[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala52.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala52, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala52.setText(trustedValueGej52[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });
        txxtNilaiGejala53.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Pilihlah Nilai Gejala ").
                        setAdapter(adapterGejala53, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                txxtNilaiGejala53.setText(trustedValueGej53[i].toString());
                                dialogInterface.dismiss();
                            }
                        }).create().show();;
            }
        });

        btnProsess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference mDatabaseFunsiImp = FirebaseDatabase.getInstance().getReference().child("Fungsiimplikasi").child(userId);
                DatabaseReference mDatabaseDefuzzy = FirebaseDatabase.getInstance().getReference().child("Defuzzyfikasi").child(userId);
                DatabaseReference mDatabaseLom = FirebaseDatabase.getInstance().getReference().child("DefuzzLOM").child(userId);
                DatabaseReference mDatabaseResult = FirebaseDatabase.getInstance().getReference().child("LastResult").child(userId);
                mDatabaseFunsiImp.removeValue();
                mDatabaseDefuzzy.removeValue();
                mDatabaseLom.removeValue();
//                mDatabaseResult.removeValue();
//                IsNullErrorHandling errorHandMain = new IsNullErrorHandling();
//                errorHandMain.checkInputUser();
                if (userHandling() == true && emptySelect() == true) {

                    //Hama H001
                    if (chkGejala1.isChecked() || chkGejala3.isChecked() || chkGejala4.isChecked() || chkGejala5.isChecked() || chkGejala6.isChecked() || chkGejala12.isChecked() || chkGejala13.isChecked()) {
                        List<String> listH001 = new ArrayList<>();
                        databaseReferenceH001 = FirebaseDatabase.getInstance().getReference().child("Database").child("H001");
                        databaseReferenceH001.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                    DataClass gejala = dataSnapshot.getValue(DataClass.class);
                                    listH001.add(gejala.getBobotGejala());

                                }
                                String namaPentakit = "penyakit";
                                double valueUserGejala1 = txxtNilaiGejala1.getText().toString().matches("") ? 0 : Double.parseDouble(txxtNilaiGejala1.getText().toString());
                                double valueUserGejala3 = txxtNilaiGejala3.getText().toString().matches("") ? 0 : Double.parseDouble(txxtNilaiGejala3.getText().toString());
                                double valueUserGejala4 = txxtNilaiGejala4.getText().toString().matches("") ? 0 : Double.parseDouble(txxtNilaiGejala4.getText().toString());
                                double valueUserGejala5 = txxtNilaiGejala5.getText().toString().matches("") ? 0 : Double.parseDouble(txxtNilaiGejala5.getText().toString());
                                double valueUserGejala6 = txxtNilaiGejala6.getText().toString().matches("") ? 0 : Double.parseDouble(txxtNilaiGejala6.getText().toString());
                                double valueUserGejala12 = txxtNilaiGejala12.getText().toString().matches("") ? 0 : Double.parseDouble(txxtNilaiGejala12.getText().toString());
                                double valueUserGejala13 = txxtNilaiGejala13.getText().toString().matches("") ? 0 : Double.parseDouble(txxtNilaiGejala13.getText().toString());

                                double valueGejala1 = Double.parseDouble(listH001.get(0));
                                double valueGejala3 = Double.parseDouble(listH001.get(1));
                                double valueGejala4 = Double.parseDouble(listH001.get(2));
                                double valueGejala5 = Double.parseDouble(listH001.get(3));
                                double valueGejala6 = Double.parseDouble(listH001.get(4));
                                double valueGejala12 = Double.parseDouble(listH001.get(5));
                                double valueGejala13 = Double.parseDouble(listH001.get(6));

                                double resultCalculateGej1 = valueGejala1 * valueUserGejala1;
                                double resultCalculateGej3 = valueGejala3 * valueUserGejala3;
                                double resultCalculateGej4 = valueGejala4 * valueUserGejala4;
                                double resultCalculateGej5 = valueGejala5 * valueUserGejala5;
                                double resultCalculateGej6 = valueGejala6 * valueUserGejala6;
                                double resultCalculateGej12 = valueGejala12 * valueUserGejala12;
                                double resultCalculateGej13 = valueGejala13 * valueUserGejala13;

                                double combine_CF1_CF2 = resultCalculateGej1 + resultCalculateGej3 * (1 - resultCalculateGej1);
                                double combine_CFold_CF4 = combine_CF1_CF2 + resultCalculateGej4 * (1 - combine_CF1_CF2);
                                double combine_CFold_CF5 = combine_CFold_CF4 + resultCalculateGej5 * (1 - combine_CFold_CF4);
                                double combine_CFold_CF6 = combine_CFold_CF5 + resultCalculateGej6 * (1 - combine_CFold_CF5);
                                double combine_CFold_CF12 = combine_CFold_CF6 + resultCalculateGej12 * (1 - combine_CFold_CF6);
                                double combine_CFold_CF13 = combine_CFold_CF12 + resultCalculateGej13 * (1 - combine_CFold_CF12);
                                String endResult = String.valueOf(df.format(combine_CFold_CF13 * 100));
                                createDataCF(endResult, "H001", userId,"Hama Pengerek Batang Padi Kuning");

                                Fuzzy fuzzy = new Fuzzy("H001", userId);
                                // counting fuzzyfikasi
                                fuzzy.penyakitH001_User(valueUserGejala1, valueUserGejala3, valueUserGejala4, valueUserGejala5, valueUserGejala6, valueUserGejala12,
                                        valueUserGejala13);
                                fuzzy.penyakitH001_Pakar(valueGejala1, valueGejala3, valueGejala4, valueGejala5, valueGejala6, valueGejala12, valueGejala13);

                                // fungsi implikasi
                                try {
                                    TimeUnit.SECONDS.sleep(2);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                FungsiImplikasi fungsiImplikasi = new FungsiImplikasi("H001", userId);
                                fungsiImplikasi.onPenyakit();
                                namaPentakit = "Test Result Penyakit " + "\n" + endResult + " %";
//                            Log.i("This value gejala",stringBobotPenyakit1.get(2));

                                try {
                                    TimeUnit.SECONDS.sleep(2);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                Defuzzyfikasi defuzzyfikasi = new Defuzzyfikasi("H001", userId, "Hama pengerek batang padi kuning");
                                Defuzzyfikasi defuzzyfikasiCons = new Defuzzyfikasi();
                                defuzzyfikasi.defuzzyFikasi();
                                defuzzyfikasi.onAgregasi();
                                defuzzyfikasi.shortAgregations();
//                                defuzzyfikasi.sumKeanggotaan();
//                                defuzzyfikasiCons.getLOMFuzzy(userId,"H001");
//                                defuzzyfikasi.sumKeanggotaan();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }
                    //Hama H002
                    if (chkGejala7.isChecked() && chkGejala8.isChecked() && chkGejala9.isChecked() && chkGejala10.isChecked() && chkGejala11.isChecked()) {
                        List<String> listH002 = new ArrayList<>();
                        databaseReferenceH002 = FirebaseDatabase.getInstance().getReference().child("Database").child("H002");
                        databaseReferenceH002.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                    DataClass gejala = dataSnapshot.getValue(DataClass.class);
                                    listH002.add(gejala.getBobotGejala());

                                }
//                            String namaPentakit = "penyakit";
                                double UserGejala7 = Double.parseDouble(txxtNilaiGejala7.getText().toString());
                                double UserGejala8 = Double.parseDouble(txxtNilaiGejala8.getText().toString());
                                double UserGejala9 = Double.parseDouble(txxtNilaiGejala9.getText().toString());
                                double UserGejala10 = Double.parseDouble(txxtNilaiGejala10.getText().toString());
                                double UserGejala11 = Double.parseDouble(txxtNilaiGejala11.getText().toString());

                                double valueGejala7 = Double.parseDouble(listH002.get(0));
                                double valueGejala8 = Double.parseDouble(listH002.get(1));
                                double valueGejala9 = Double.parseDouble(listH002.get(2));
                                double valueGejala10 = Double.parseDouble(listH002.get(3));
                                double valueGejala11 = Double.parseDouble(listH002.get(4));


                                double resultCalculateGej7 = valueGejala7 * UserGejala7;
                                double resultCalculateGej8 = valueGejala8 * UserGejala8;
                                double resultCalculateGej9 = valueGejala9 * UserGejala9;
                                double resultCalculateGej10 = valueGejala10 * UserGejala10;
                                double resultCalculateGej11 = valueGejala11 * UserGejala11;

                                double combine_CF7_CF8 = resultCalculateGej7 + resultCalculateGej8 * (1 - resultCalculateGej7);
                                double combine_CFold_CF9 = combine_CF7_CF8 + resultCalculateGej9 * (1 - combine_CF7_CF8);
                                double combine_CFold_CF10 = combine_CFold_CF9 + resultCalculateGej10 * (1 - combine_CFold_CF9);
                                double combine_CFold_CF11 = combine_CFold_CF10 + resultCalculateGej11 * (1 - combine_CFold_CF10);
                                String endResult = String.valueOf((combine_CFold_CF11 * 100));
                                createDataCF(endResult, "H002", userId,"Hama Putih Palsu atau Pelipat Daun");

                                Fuzzy fuzzy = new Fuzzy("H002", userId);
                                // counting fuzzyfikasi
                                fuzzy.penyakitH002_User(UserGejala7, UserGejala8, UserGejala9, UserGejala10, UserGejala11);
                                fuzzy.penyakitH002_Pakar(valueGejala7, valueGejala8, valueGejala9, valueGejala10, valueGejala11);

                                // fungsi implikasi
                                try {
                                    TimeUnit.SECONDS.sleep(2);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                FungsiImplikasi fungsiImplikasi = new FungsiImplikasi("H002", userId);
                                fungsiImplikasi.onPenyakit();
//                            namaPentakit = "Test Result Penyakit " + "\n" + endResult + " %";
//                            Log.i("This value gejala",stringBobotPenyakit1.get(2));

                                try {
                                    TimeUnit.SECONDS.sleep(2);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                Defuzzyfikasi defuzzyfikasi = new Defuzzyfikasi("H002", userId, "Hama putih palsu atau pelipat daun");
                                defuzzyfikasi.defuzzyFikasi();
                                defuzzyfikasi.onAgregasi();
//                           defuzzyfikasi.shortAgregations();

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }
                    //Hama H003
                    if (chkGejala14.isChecked() && chkGejala15.isChecked() && chkGejala16.isChecked() && chkGejala17.isChecked() & chkGejala18.isChecked()) {
                        List<String> listBobotH003 = new ArrayList<>();
                        databaseReferenceH003 = FirebaseDatabase.getInstance().getReference().child("Database").child("H003");
                        databaseReferenceH003.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                    DataClass gejala = dataSnapshot.getValue(DataClass.class);
                                    listBobotH003.add(gejala.getBobotGejala());

                                }
                                String namaPentakit = "penyakit";
                                double valueUserGejala14 = Double.parseDouble(txxtNilaiGejala14.getText().toString());
                                double valueUserGejala15 = Double.parseDouble(txxtNilaiGejala15.getText().toString());
                                double valueUserGejala16 = Double.parseDouble(txxtNilaiGejala16.getText().toString());
                                double valueUserGejala17 = Double.parseDouble(txxtNilaiGejala17.getText().toString());
                                double valueUserGejala18 = Double.parseDouble(txxtNilaiGejala18.getText().toString());


                                double valueGejala14 = Double.parseDouble(listBobotH003.get(0));
                                double valueGejala15 = Double.parseDouble(listBobotH003.get(1));
                                double valueGejala16 = Double.parseDouble(listBobotH003.get(2));
                                double valueGejala17 = Double.parseDouble(listBobotH003.get(3));
                                double valueGejala18 = Double.parseDouble(listBobotH003.get(4));

                                double resultCalculateGej14 = valueGejala14 * valueUserGejala14;
                                double resultCalculateGej15 = valueGejala15 * valueUserGejala15;
                                double resultCalculateGej16 = valueGejala16 * valueUserGejala16;
                                double resultCalculateGej17 = valueGejala17 * valueUserGejala17;
                                double resultCalculateGej18 = valueGejala18 * valueUserGejala18;

                                double combine_CF14_CF15 = resultCalculateGej14 + resultCalculateGej15 * (1 - resultCalculateGej14);
                                double combine_CFold_CF16 = combine_CF14_CF15 + resultCalculateGej16 * (1 - combine_CF14_CF15);
                                double combine_CFold_CF17 = combine_CFold_CF16 + resultCalculateGej17 * (1 - combine_CFold_CF16);
                                double combine_CFold_CF18 = combine_CFold_CF17 + resultCalculateGej18 * (1 - combine_CFold_CF17);
                                String endResult = String.valueOf((combine_CFold_CF18 * 100));
                                createDataCF(endResult, "H003", userId,"Hama Wereng Batang Coklat");

                                Fuzzy fuzzy = new Fuzzy("H003", userId);
                                // counting fuzzyfikasi
                                fuzzy.penyakitH003_User(valueUserGejala14, valueUserGejala15, valueUserGejala16, valueUserGejala17, valueGejala18);
                                fuzzy.penyakitH003_Pakar(valueGejala14, valueGejala15, valueGejala16, valueGejala17, valueGejala18);

                                // fungsi implikasi
                                try {
                                    TimeUnit.SECONDS.sleep(2);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                FungsiImplikasi fungsiImplikasi = new FungsiImplikasi("H003", userId);
                                fungsiImplikasi.onPenyakit();
                                namaPentakit = "Test Result Penyakit " + "\n" + endResult + " %";
                                Log.i("This value gejala", listBobotH003.get(2));

                                try {
                                    TimeUnit.SECONDS.sleep(2);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                Defuzzyfikasi defuzzyfikasi = new Defuzzyfikasi("H003", userId, "hama wereng batang coklat");
                                defuzzyfikasi.defuzzyFikasi();
                                defuzzyfikasi.onAgregasi();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }
                    //Hama H004
                    if (chkGejala19.isChecked() && chkGejala20.isChecked() && chkGejala21.isChecked() && chkGejala22.isChecked() && chkGejala23.isChecked()) {
                        List<String> listBobotH004 = new ArrayList<>();
                        databaseReferenceH004 = FirebaseDatabase.getInstance().getReference().child("Database").child("H004");
                        databaseReferenceH004.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                    DataClass gejala = dataSnapshot.getValue(DataClass.class);
                                    listBobotH004.add(gejala.getBobotGejala());

                                }
                                String namaPentakit = "penyakit";
                                double valueUserGejala19 = Double.parseDouble(txxtNilaiGejala14.getText().toString());
                                double valueUserGejala20 = Double.parseDouble(txxtNilaiGejala15.getText().toString());
                                double valueUserGejala21 = Double.parseDouble(txxtNilaiGejala16.getText().toString());
                                double valueUserGejala22 = Double.parseDouble(txxtNilaiGejala17.getText().toString());
                                double valueUserGejala23 = Double.parseDouble(txxtNilaiGejala18.getText().toString());


                                double valueGejala19 = Double.parseDouble(listBobotH004.get(0));
                                double valueGejala20 = Double.parseDouble(listBobotH004.get(1));
                                double valueGejala21 = Double.parseDouble(listBobotH004.get(2));
                                double valueGejala22 = Double.parseDouble(listBobotH004.get(3));
                                double valueGejala23 = Double.parseDouble(listBobotH004.get(4));

                                double resultCalculateGej19 = valueGejala19 * valueUserGejala19;
                                double resultCalculateGej20 = valueGejala20 * valueUserGejala20;
                                double resultCalculateGej21 = valueGejala21 * valueUserGejala21;
                                double resultCalculateGej22 = valueGejala22 * valueUserGejala22;
                                double resultCalculateGej23 = valueGejala23 * valueUserGejala23;

                                double combine_CF19_CF20 = resultCalculateGej19 + resultCalculateGej20 * (1 - resultCalculateGej20);
                                double combine_CFold_CF21 = combine_CF19_CF20 + resultCalculateGej21 * (1 - combine_CF19_CF20);
                                double combine_CFold_CF22 = combine_CFold_CF21 + resultCalculateGej22 * (1 - combine_CFold_CF21);
                                double combine_CFold_CF23 = combine_CFold_CF22 + resultCalculateGej23 * (1 - combine_CFold_CF22);
                                String endResult = String.valueOf((combine_CFold_CF23 * 100));
                                createDataCF(endResult, "H004", userId,"Bla Bla");

                                Fuzzy fuzzy = new Fuzzy("H004", userId);
                                // counting fuzzyfikasi
                                fuzzy.penyakitH004_User(valueUserGejala19, valueUserGejala20, valueUserGejala21, valueUserGejala22, valueGejala23);
                                fuzzy.penyakitH004_Pakar(valueGejala19, valueGejala20, valueGejala21, valueGejala22, valueGejala23);

                                // fungsi implikasi
                                try {
                                    TimeUnit.SECONDS.sleep(2);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                FungsiImplikasi fungsiImplikasi = new FungsiImplikasi("H004", userId);
                                fungsiImplikasi.onPenyakit();
                                namaPentakit = "Test Result Penyakit " + "\n" + endResult + " %";

//                            Log.i("This value gejala",listBobotH004.get(2));

                                try {
                                    TimeUnit.SECONDS.sleep(2);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                Defuzzyfikasi defuzzyfikasi = new Defuzzyfikasi("H004", userId, "on progress");
                                defuzzyfikasi.defuzzyFikasi();
                                defuzzyfikasi.onAgregasi();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }
                    //Hama H005
                    if (chkGejala14.isChecked() && chkGejala15.isChecked() && chkGejala16.isChecked() && chkGejala17.isChecked()) {
                        List<String> listBobotH005 = new ArrayList<>();
                        databaseReferenceH005 = FirebaseDatabase.getInstance().getReference().child("Databaase").child("H005");
                        databaseReferenceH005.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                    DataClass gejala = dataSnapshot.getValue(DataClass.class);
                                    listBobotH005.add(gejala.getBobotGejala());

                                }
                                String namaPentakit = "penyakit";
                                double valueUserGejala14 = Double.parseDouble(txxtNilaiGejala14.getText().toString());
                                double valueUserGejala15 = Double.parseDouble(txxtNilaiGejala15.getText().toString());
                                double valueUserGejala16 = Double.parseDouble(txxtNilaiGejala16.getText().toString());
                                double valueUserGejala17 = Double.parseDouble(txxtNilaiGejala17.getText().toString());


                                double valueGejala14 = Double.parseDouble(listBobotH005.get(0));
                                double valueGejala15 = Double.parseDouble(listBobotH005.get(1));
                                double valueGejala16 = Double.parseDouble(listBobotH005.get(2));
                                double valueGejala17 = Double.parseDouble(listBobotH005.get(3));

                                double resultCalculateGej14 = valueGejala14 * valueUserGejala14;
                                double resultCalculateGej15 = valueGejala15 * valueUserGejala15;
                                double resultCalculateGej16 = valueGejala16 * valueUserGejala16;
                                double resultCalculateGej17 = valueGejala17 * valueUserGejala17;

                                double combine_CF14_CF15 = resultCalculateGej14 + resultCalculateGej15 * (1 - resultCalculateGej14);
                                double combine_CFold_CF16 = combine_CF14_CF15 + resultCalculateGej16 * (1 - combine_CF14_CF15);
                                double combine_CFold_CF17 = combine_CFold_CF16 + resultCalculateGej17 * (1 - combine_CFold_CF16);
                                String endResult = String.valueOf((combine_CFold_CF17 * 100));
                                createDataCF(endResult, "H005", userId,"on");

                                Fuzzy fuzzy = new Fuzzy("H005", userId);
                                // counting fuzzyfikasi
                                fuzzy.penyakitH005_User(valueUserGejala14, valueUserGejala15, valueUserGejala16, valueUserGejala17);
                                fuzzy.penyakitH005_Pakar(valueGejala14, valueGejala15, valueGejala16, valueGejala17);

                                // fungsi implikasi
                                try {
                                    TimeUnit.SECONDS.sleep(2);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                FungsiImplikasi fungsiImplikasi = new FungsiImplikasi("H005", userId);
                                fungsiImplikasi.onPenyakit();
                                namaPentakit = "Test Result Penyakit " + "\n" + endResult + " %";
                                Log.i("This value gejala", listBobotH005.get(2));

                                try {
                                    TimeUnit.SECONDS.sleep(2);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                Defuzzyfikasi defuzzyfikasi = new Defuzzyfikasi("H005", userId, "on progress");
                                defuzzyfikasi.defuzzyFikasi();
                                defuzzyfikasi.onAgregasi();

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                    //Hama H006
                    if (chkGejala2.isChecked() && chkGejala27.isChecked() && chkGejala28.isChecked() && chkGejala29.isChecked() && chkGejala30.isChecked()) {
                        List<String> listBobotH006 = new ArrayList<>();
                        databaseReferenceH006 = FirebaseDatabase.getInstance().getReference().child("Database").child("H006");
                        databaseReferenceH006.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                    DataClass gejala = dataSnapshot.getValue(DataClass.class);
                                    listBobotH006.add(gejala.getBobotGejala());

                                }
                                String namaPentakit = "penyakit";
                                double valueUserGejala2 = Double.parseDouble(txxtNilaiGejala2.getText().toString());
                                double valueUserGejala27 = Double.parseDouble(txxtNilaiGejala27.getText().toString());
                                double valueUserGejala28 = Double.parseDouble(txxtNilaiGejala28.getText().toString());
                                double valueUserGejala29 = Double.parseDouble(txxtNilaiGejala29.getText().toString());
                                double valueUserGejala30 = Double.parseDouble(txxtNilaiGejala30.getText().toString());

                                double valueGejala2 = Double.parseDouble(listBobotH006.get(0));
                                double valueGejala27 = Double.parseDouble(listBobotH006.get(1));
                                double valueGejala28 = Double.parseDouble(listBobotH006.get(2));
                                double valueGejala29 = Double.parseDouble(listBobotH006.get(3));
                                double valueGejala30 = Double.parseDouble(listBobotH006.get(4));

                                double resultCalculateGej2 = valueGejala2 * valueUserGejala2;
                                double resultCalculateGej27 = valueGejala27 * valueUserGejala27;
                                double resultCalculateGej28 = valueGejala28 * valueUserGejala28;
                                double resultCalculateGej29 = valueGejala29 * valueUserGejala29;
                                double resultCalculateGej30 = valueGejala30 * valueUserGejala30;

                                double combine_CF2_CF27 = resultCalculateGej2 + resultCalculateGej27 * (1 - resultCalculateGej2);
                                double combine_CFold_CF28 = combine_CF2_CF27 + resultCalculateGej28 * (1 - combine_CF2_CF27);
                                double combine_CFold_CF29 = combine_CFold_CF28 + resultCalculateGej29 * (1 - combine_CFold_CF28);
                                double combine_CFold_CF30 = combine_CFold_CF29 + resultCalculateGej30 * (1 - combine_CFold_CF29);
                                String endResult = String.valueOf((combine_CFold_CF30 * 100));
                                createDataCF(endResult, "H006", userId,"on");

                                Fuzzy fuzzy = new Fuzzy("H006", userId);
                                // counting fuzzyfikasi
                                fuzzy.penyakitH006_User(valueUserGejala2, valueUserGejala27, valueUserGejala28, valueUserGejala29, valueUserGejala30);
                                fuzzy.penyakitH006_Pakar(valueGejala2, valueGejala27, valueGejala28, valueGejala29, valueGejala30);

                                // fungsi implikasi
                                try {
                                    TimeUnit.SECONDS.sleep(2);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                FungsiImplikasi fungsiImplikasi = new FungsiImplikasi("H006", userId);
                                fungsiImplikasi.onPenyakit();
                                namaPentakit = "Test Result Penyakit " + "\n" + endResult + " %";

                                try {
                                    TimeUnit.SECONDS.sleep(2);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                Defuzzyfikasi defuzzyfikasi = new Defuzzyfikasi("H006", userId, "on progress");
                                defuzzyfikasi.defuzzyFikasi();
                                defuzzyfikasi.onAgregasi();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                    //Hama H007
                    if (chkGejala34.isChecked() || chkGejala35.isChecked() || chkGejala36.isChecked()) {
                            List<String> listGejalaH007 = new ArrayList<>();

                            databaseReferenceH007 = FirebaseDatabase.getInstance().getReference().child("Database").child("H007");
                            databaseReferenceH007.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                        DataClass gejala = dataSnapshot.getValue(DataClass.class);
                                        listGejalaH007.add(gejala.getBobotGejala());

                                    }
                                    String namaPentakit = "penyakit";

                                    double valueUserGejala34 = txxtNilaiGejala34.getText().toString().matches("") ? 0 : Double.parseDouble(txxtNilaiGejala34.getText().toString());
                                    double valueUserGejala35 = txxtNilaiGejala35.getText().toString().matches("") ? 0 : Double.parseDouble(txxtNilaiGejala35.getText().toString());
                                    double valueUserGejala36 = txxtNilaiGejala36.getText().toString().matches("") ? 0 : Double.parseDouble(txxtNilaiGejala36.getText().toString());

                                    double valueGejala34 = Double.parseDouble(listGejalaH007.get(0));
                                    double valueGejala35 = Double.parseDouble(listGejalaH007.get(1));
                                    double valueGejala36 = Double.parseDouble(listGejalaH007.get(2));

                                    double resultCalculateGej1 = valueGejala34 * valueUserGejala34;
                                    double resultCalculateGej2 = valueGejala35 * valueUserGejala35;
                                    double resultCalculateGej4 = valueGejala36 * valueUserGejala36;

                                    double combine_CF34_CF35 = resultCalculateGej1 + resultCalculateGej2 * (1 - resultCalculateGej1);
                                    double combine_CFold_CF36 = combine_CF34_CF35 + resultCalculateGej4 * (1 - combine_CF34_CF35);
                                    String endResult = String.valueOf(df.format(combine_CFold_CF36 * 100));
                                    createDataCF(endResult, "H007", userId,"Hama Ulat Garayak");

                                    Fuzzy fuzzy = new Fuzzy("H007", userId);
                                    // counting fuzzyfikasi
                                    fuzzy.penyakitH007_User(valueUserGejala34, valueUserGejala35, valueUserGejala36);
                                    fuzzy.penyakitH007_Pakar(valueGejala34, valueGejala35, valueGejala36);

                                    // fungsi implikasi
                                    try {
                                        TimeUnit.SECONDS.sleep(2);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    FungsiImplikasi fungsiImplikasi = new FungsiImplikasi("H007", userId);
                                    fungsiImplikasi.onPenyakit();
                                    namaPentakit = "Test Result Penyakit " + "\n" + endResult + " %";
//                           Log.i("This value gejala",stringBobotPenyakit1.get(2));

                                    try {
                                        TimeUnit.SECONDS.sleep(2);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    Defuzzyfikasi defuzzyfikasi = new Defuzzyfikasi("H007", userId, "Hama ulat grayak");
                                    Defuzzyfikasi defuzzyfikasiCons = new Defuzzyfikasi();
                                    defuzzyfikasi.defuzzyFikasi();
                                    defuzzyfikasi.onAgregasi();
                                    defuzzyfikasi.shortAgregations();
//                                    defuzzyfikasi.sumKeanggotaan();
//                                    defuzzyfikasiCons.getLOMFuzzy(userId,"H007");
//                                    defuzzyfikasiCons.sumKeanggotaan();

//                           defuzzyfikasi.shortAgregations();

//                           tcOutput.setText(""+namaPentakit);

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                    }
                    //Hama H008
                    if (chkGejala37.isChecked() && chkGejala38.isChecked() && chkGejala39.isChecked() && chkGejala40.isChecked() && chkGejala41.isChecked()) {
                        List<String> listGejalaH008 = new ArrayList<>();
                        databaseReferenceH008 = FirebaseDatabase.getInstance().getReference().child("Database").child("H009");
                        databaseReferenceH008.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                    DataClass gejala = dataSnapshot.getValue(DataClass.class);
                                    listGejalaH008.add(gejala.getBobotGejala());

                                }
                                String namaPentakit = "penyakit";
                                double valueUserGejala2 = Double.parseDouble(txxtNilaiGejala37.getText().toString());
                                double valueUserGejala27 = Double.parseDouble(txxtNilaiGejala38.getText().toString());
                                double valueUserGejala28 = Double.parseDouble(txxtNilaiGejala39.getText().toString());
                                double valueUserGejala29 = Double.parseDouble(txxtNilaiGejala40.getText().toString());
                                double valueUserGejala30 = Double.parseDouble(txxtNilaiGejala41.getText().toString());

                                double valueGejala2 = Double.parseDouble(listGejalaH008.get(0));
                                double valueGejala27 = Double.parseDouble(listGejalaH008.get(1));
                                double valueGejala28 = Double.parseDouble(listGejalaH008.get(2));
                                double valueGejala29 = Double.parseDouble(listGejalaH008.get(3));
                                double valueGejala30 = Double.parseDouble(listGejalaH008.get(4));

                                double resultCalculateGej2 = valueGejala2 * valueUserGejala2;
                                double resultCalculateGej27 = valueGejala27 * valueUserGejala27;
                                double resultCalculateGej28 = valueGejala28 * valueUserGejala28;
                                double resultCalculateGej29 = valueGejala29 * valueUserGejala29;
                                double resultCalculateGej30 = valueGejala30 * valueUserGejala30;

                                double combine_CF2_CF27 = resultCalculateGej2 + resultCalculateGej27 * (1 - resultCalculateGej2);
                                double combine_CFold_CF28 = combine_CF2_CF27 + resultCalculateGej28 * (1 - combine_CF2_CF27);
                                double combine_CFold_CF29 = combine_CFold_CF28 + resultCalculateGej29 * (1 - combine_CFold_CF28);
                                double combine_CFold_CF30 = combine_CFold_CF29 + resultCalculateGej30 * (1 - combine_CFold_CF29);
                                String endResult = String.valueOf((combine_CFold_CF30 * 100));
                                createDataCF(endResult, "H008", userId,"n");

                                Fuzzy fuzzy = new Fuzzy("H008", userId);
                                // counting fuzzyfikasi
                                fuzzy.penyakitH006_User(valueUserGejala2, valueUserGejala27, valueUserGejala28, valueUserGejala29, valueUserGejala30);
                                fuzzy.penyakitH006_Pakar(valueGejala2, valueGejala27, valueGejala28, valueGejala29, valueGejala30);

                                // fungsi implikasi
                                try {
                                    TimeUnit.SECONDS.sleep(2);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                FungsiImplikasi fungsiImplikasi = new FungsiImplikasi("H008", userId);
                                fungsiImplikasi.onPenyakit();
                                namaPentakit = "Test Result Penyakit " + "\n" + endResult + " %";

                                try {
                                    TimeUnit.SECONDS.sleep(2);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                Defuzzyfikasi defuzzyfikasi = new Defuzzyfikasi("H008", userId, "Hama Ganjur");
                                defuzzyfikasi.defuzzyFikasi();
                                defuzzyfikasi.onAgregasi();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


                    }
                    //Hama H009
                    if (chkGejala43.isChecked() && chkGejala45.isChecked() && chkGejala46.isChecked()) {
                        List<String> listHamaH009 = new ArrayList<>();
                        databaseReferenceH009 = FirebaseDatabase.getInstance().getReference().child("Database").child("H009");
                        databaseReferenceH009.addValueEventListener(new ValueEventListener() {
                            List<String> listGejalaH009 = new ArrayList<>();

                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                    DataClass gejala = dataSnapshot.getValue(DataClass.class);
                                    listGejalaH009.add(gejala.getBobotGejala());

                                }
                                String namaPentakit = "penyakit";
                                double valueUserGejala34 = Double.parseDouble(txxtNilaiGejala43.getText().toString());
                                double valueUserGejala35 = Double.parseDouble(txxtNilaiGejala45.getText().toString());
                                double valueUserGejala36 = Double.parseDouble(txxtNilaiGejala46.getText().toString());

                                double valueGejala34 = Double.parseDouble(listGejalaH009.get(0));
                                double valueGejala35 = Double.parseDouble(listGejalaH009.get(1));
                                double valueGejala36 = Double.parseDouble(listGejalaH009.get(2));

                                double resultCalculateGej1 = valueGejala34 * valueUserGejala34;
                                double resultCalculateGej2 = valueGejala35 * valueUserGejala35;
                                double resultCalculateGej4 = valueGejala36 * valueUserGejala36;

                                double combine_CF34_CF35 = resultCalculateGej1 + resultCalculateGej2 * (1 - resultCalculateGej1);
                                double combine_CFold_CF36 = combine_CF34_CF35 + resultCalculateGej4 * (1 - combine_CF34_CF35);
                                String endResult = String.valueOf((combine_CFold_CF36 * 100));
                                createDataCF(endResult, "H009", userId,"Hama Ganjurj");

                                Fuzzy fuzzy = new Fuzzy("H009", userId);
                                // counting fuzzyfikasi
                                fuzzy.penyakitH007_User(valueUserGejala34, valueUserGejala35, valueUserGejala36);
                                fuzzy.penyakitH007_Pakar(valueGejala34, valueGejala35, valueGejala36);

                                // fungsi implikasi
                                try {
                                    TimeUnit.SECONDS.sleep(2);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                FungsiImplikasi fungsiImplikasi = new FungsiImplikasi("H009", userId);
                                fungsiImplikasi.onPenyakit();
                                namaPentakit = "Test Result Penyakit " + "\n" + endResult + " %";
//                           Log.i("This value gejala",stringBobotPenyakit1.get(2));

                                try {
                                    TimeUnit.SECONDS.sleep(2);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                Defuzzyfikasi defuzzyfikasi = new Defuzzyfikasi("H009", userId, "Hama ganjur");
                                defuzzyfikasi.defuzzyFikasi();
                                defuzzyfikasi.onAgregasi();
//                           defuzzyfikasi.shortAgregations();

//                           tcOutput.setText(""+namaPentakit);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }
                    //Hama H010
                    if (chkGejala47.isChecked() || chkGejala48.isChecked() || chkGejala49.isChecked()) {
                        List<String> listGejalaH010 = new ArrayList<>();
                        databaseReferenceH0010 = FirebaseDatabase.getInstance().getReference().child("Database").child("H010");
                        databaseReferenceH0010.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                    DataClass gejala = dataSnapshot.getValue(DataClass.class);
                                    listGejalaH010.add(gejala.getBobotGejala());

                                }
                                String namaPentakit = "penyakit";
                                double valueUserGejala34 = txxtNilaiGejala47.getText().toString().matches("") ? 0 : Double.parseDouble(txxtNilaiGejala47.getText().toString());
                                double valueUserGejala35 = txxtNilaiGejala48.getText().toString().matches("") ? 0 : Double.parseDouble(txxtNilaiGejala48.getText().toString());
                                double valueUserGejala36 = txxtNilaiGejala49.getText().toString().matches("") ? 0 : Double.parseDouble(txxtNilaiGejala49.getText().toString());

                                double valueGejala34 = Double.parseDouble(listGejalaH010.get(0));
                                double valueGejala35 = Double.parseDouble(listGejalaH010.get(1));
                                double valueGejala36 = Double.parseDouble(listGejalaH010.get(2));

                                double resultCalculateGej1 = valueGejala34 * valueUserGejala34;
                                double resultCalculateGej2 = valueGejala35 * valueUserGejala35;
                                double resultCalculateGej4 = valueGejala36 * valueUserGejala36;

                                double combine_CF34_CF35 = resultCalculateGej1 + resultCalculateGej2 * (1 - resultCalculateGej1);
                                double combine_CFold_CF36 = combine_CF34_CF35 + resultCalculateGej4 * (1 - combine_CF34_CF35);
                                String endResult = String.valueOf(df.format(combine_CFold_CF36 * 100));
                                createDataCF(endResult, "H010", userId,"");

                                Fuzzy fuzzy = new Fuzzy("H010", userId);
                                // counting fuzzyfikasi
                                fuzzy.penyakitH007_User(valueUserGejala34, valueUserGejala35, valueUserGejala36);
                                fuzzy.penyakitH007_Pakar(valueGejala34, valueGejala35, valueGejala36);

                                // fungsi implikasi
                                try {
                                    TimeUnit.SECONDS.sleep(2);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                FungsiImplikasi fungsiImplikasi = new FungsiImplikasi("H010", userId);
                                fungsiImplikasi.onPenyakit();
                                namaPentakit = "Test Result Penyakit " + "\n" + endResult + " %";
//                           Log.i("This value gejala",stringBobotPenyakit1.get(2));

                                try {
                                    TimeUnit.SECONDS.sleep(2);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                Defuzzyfikasi defuzzyfikasi = new Defuzzyfikasi("H010", userId, "hama belalang kembara");
                                defuzzyfikasi.defuzzyFikasi();
                                defuzzyfikasi.onAgregasi();
//                           defuzzyfikasi.shortAgregations();

//                           tcOutput.setText(""+namaPentakit);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }
                    //Hama H011
                    if (chkGejala22.isChecked() && chkGejala50.isChecked() && chkGejala51.isChecked() && chkGejala52.isChecked() && chkGejala53.isChecked()) {
                        List<String> listGejalaH011 = new ArrayList<>();
                        databaseReferenceH0011 = FirebaseDatabase.getInstance().getReference().child("Database").child("H011");
                        databaseReferenceH0011.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                    DataClass gejala = dataSnapshot.getValue(DataClass.class);
                                    listGejalaH011.add(gejala.getBobotGejala());

                                }
                                String namaPentakit = "penyakit";
                                double valueUserGejala2 = Double.parseDouble(txxtNilaiGejala22.getText().toString());
                                double valueUserGejala27 = Double.parseDouble(txxtNilaiGejala50.getText().toString());
                                double valueUserGejala28 = Double.parseDouble(txxtNilaiGejala51.getText().toString());
                                double valueUserGejala29 = Double.parseDouble(txxtNilaiGejala52.getText().toString());
                                double valueUserGejala30 = Double.parseDouble(txxtNilaiGejala53.getText().toString());

                                double valueGejala2 = Double.parseDouble(listGejalaH011.get(0));
                                double valueGejala27 = Double.parseDouble(listGejalaH011.get(1));
                                double valueGejala28 = Double.parseDouble(listGejalaH011.get(2));
                                double valueGejala29 = Double.parseDouble(listGejalaH011.get(3));
                                double valueGejala30 = Double.parseDouble(listGejalaH011.get(4));

                                double resultCalculateGej2 = valueGejala2 * valueUserGejala2;
                                double resultCalculateGej27 = valueGejala27 * valueUserGejala27;
                                double resultCalculateGej28 = valueGejala28 * valueUserGejala28;
                                double resultCalculateGej29 = valueGejala29 * valueUserGejala29;
                                double resultCalculateGej30 = valueGejala30 * valueUserGejala30;

                                double combine_CF2_CF27 = resultCalculateGej2 + resultCalculateGej27 * (1 - resultCalculateGej2);
                                double combine_CFold_CF28 = combine_CF2_CF27 + resultCalculateGej28 * (1 - combine_CF2_CF27);
                                double combine_CFold_CF29 = combine_CFold_CF28 + resultCalculateGej29 * (1 - combine_CFold_CF28);
                                double combine_CFold_CF30 = combine_CFold_CF29 + resultCalculateGej30 * (1 - combine_CFold_CF29);
                                String endResult = String.valueOf((combine_CFold_CF30 * 100));
                                createDataCF(endResult, "H011", userId,"");

                                Fuzzy fuzzy = new Fuzzy("H011", userId);
                                // counting fuzzyfikasi
                                fuzzy.penyakitH006_User(valueUserGejala2, valueUserGejala27, valueUserGejala28, valueUserGejala29, valueUserGejala30);
                                fuzzy.penyakitH006_Pakar(valueGejala2, valueGejala27, valueGejala28, valueGejala29, valueGejala30);

                                // fungsi implikasi
                                try {
                                    TimeUnit.SECONDS.sleep(2);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                FungsiImplikasi fungsiImplikasi = new FungsiImplikasi("H011", userId);
                                fungsiImplikasi.onPenyakit();
                                namaPentakit = "Test Result Penyakit " + "\n" + endResult + " %";

                                try {
                                    TimeUnit.SECONDS.sleep(2);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                Defuzzyfikasi defuzzyfikasi = new Defuzzyfikasi("H011", userId, "hama kepinding tanah");
                                defuzzyfikasi.defuzzyFikasi();
                                defuzzyfikasi.onAgregasi();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }


//                try {
//                    TimeUnit.SECONDS.sleep(2);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

//                    moveInten();
                    // add sharedpreference

//                    SharedPreferences.Editor editorSes = sharedPreferencesSession.edit();
//                    editorSes.putString("varSession", String.valueOf(sessionId));
//                    editorSes.apply();
//                    sessionString = sharedPreferencesSession.getString("varSession", null);
//                    new Defuzzyfikasi(sessionString);
//                    sessionId++;
                      startActivity(new Intent(MainActivity.this, CFResult.class));

                }
            }

        });

    }

    private void createDataCF(String result, String penyakit, String userId,String namaPenyakit){
        databaseInserFM = FirebaseDatabase.getInstance().getReference();
        DatabaseReference databaseInserSession = FirebaseDatabase.getInstance().getReference();
        LastModel lastModel = new LastModel(namaPenyakit,result);
        databaseInserFM.child("LastResult").child(userId).child(penyakit).setValue(lastModel);

//        LastModel lastModelSes = new LastModel(penyakit,"1");
//        databaseInserSession.child("LastResult").child(userId).child("SES").child("keyy").setValue(lastModelSes);
    }
    private void moveInten(){
        List<String> st = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("LastResult").child(userId).child("SES");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ShowUser showUser = dataSnapshot.getValue(ShowUser.class);
//                    showUser.setKeyMethod(dataSnapshot.getKey());
                    st.add(showUser.nilaiAkhir);

                    if (showUser.nilaiAkhir.equalsIgnoreCase("1")){
                        try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
//                        onDestroy();
                        Intent intent = new Intent(MainActivity.this, ResultActivity.class);

//                        Intent intent = new Intent(MainActivity.this, LoadingActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

//                int size = st.size();
//
//                if (st.get(0). > 1){
//                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
//                    startActivity(intent);
//                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageViewPenyakitHama:
                Toast.makeText(this, "Button Penaykit dan Hama clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, PenyakitHama.class));
                break;
            case R.id.imageViewTipsMenanam:
                Toast.makeText(this, "Button Tips Menanam clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, TipsMenanam.class));
                break;
            case R.id.imageViewPemasaran:
                Toast.makeText(this, "Button Pemasaran clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, Pemasaran.class));
                break;
            case R.id.imageViewJenisTanaman:
                Toast.makeText(this, "Button Jenis Tanaman clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, JenisPadi.class));
                break;

        }
    }

    private boolean userHandling(){
        boolean hasil = true;
        if (chkGejala1.isChecked() && txxtNilaiGejala1.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        }
//        else if (chkGejala2.isChecked() && txxtNilaiGejala2.getText().toString().matches("")){
//            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
//            hasil = false;
//        }
        else if (chkGejala2.isChecked() && txxtNilaiGejala2.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala3.isChecked() && txxtNilaiGejala3.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala4.isChecked() && txxtNilaiGejala4.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala6.isChecked() && txxtNilaiGejala6.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala7.isChecked() && txxtNilaiGejala7.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala8.isChecked() && txxtNilaiGejala8.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala9.isChecked() && txxtNilaiGejala9.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala10.isChecked() && txxtNilaiGejala10.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala11.isChecked() && txxtNilaiGejala11.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala12.isChecked() && txxtNilaiGejala12.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala13.isChecked() && txxtNilaiGejala13.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala14.isChecked() && txxtNilaiGejala14.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala15.isChecked() && txxtNilaiGejala15.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala16.isChecked() && txxtNilaiGejala16.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala17.isChecked() && txxtNilaiGejala17.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala18.isChecked() && txxtNilaiGejala18.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala19.isChecked() && txxtNilaiGejala19.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala20.isChecked() && txxtNilaiGejala20.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala21.isChecked() && txxtNilaiGejala21.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala22.isChecked() && txxtNilaiGejala22.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala23.isChecked() && txxtNilaiGejala23.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala24.isChecked() && txxtNilaiGejala24.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala25.isChecked() && txxtNilaiGejala25.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala26.isChecked() && txxtNilaiGejala26.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala27.isChecked() && txxtNilaiGejala27.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala28.isChecked() && txxtNilaiGejala28.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala29.isChecked() && txxtNilaiGejala29.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala30.isChecked() && txxtNilaiGejala30.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala31.isChecked() && txxtNilaiGejala31.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala32.isChecked() && txxtNilaiGejala32.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala33.isChecked() && txxtNilaiGejala33.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala34.isChecked() && txxtNilaiGejala34.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala35.isChecked() && txxtNilaiGejala35.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala36.isChecked() && txxtNilaiGejala36.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala37.isChecked() && txxtNilaiGejala37.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala38.isChecked() && txxtNilaiGejala38.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala39.isChecked() && txxtNilaiGejala39.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala40.isChecked() && txxtNilaiGejala40.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala41.isChecked() && txxtNilaiGejala41.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala42.isChecked() && txxtNilaiGejala42.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala43.isChecked() && txxtNilaiGejala43.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala44.isChecked() && txxtNilaiGejala44.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala45.isChecked() && txxtNilaiGejala45.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala46.isChecked() && txxtNilaiGejala46.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala47.isChecked() && txxtNilaiGejala47.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala48.isChecked() && txxtNilaiGejala48.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala49.isChecked() && txxtNilaiGejala49.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala50.isChecked() && txxtNilaiGejala50.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala51.isChecked() && txxtNilaiGejala51.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala52.isChecked() && txxtNilaiGejala52.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        } else if (chkGejala53.isChecked() && txxtNilaiGejala53.getText().toString().matches("")){
            Toast.makeText(getBaseContext(),"Silahkan isi nilai dari gejala yang dipilih",Toast.LENGTH_LONG).show();
            hasil = false;
        }
        return hasil;
    }

    private boolean emptySelect(){
        boolean selectVar = true;

        if (!chkGejala1.isChecked() && !chkGejala2.isChecked() && !chkGejala3.isChecked() && !chkGejala4.isChecked() && !chkGejala5.isChecked() && !chkGejala6.isChecked()
                && !chkGejala7.isChecked() && !chkGejala8.isChecked() && !chkGejala9.isChecked() && !chkGejala10.isChecked() && !chkGejala11.isChecked() && !chkGejala12.isChecked()
                && !chkGejala13.isChecked() && !chkGejala14.isChecked() && !chkGejala15.isChecked() && !chkGejala16.isChecked() && !chkGejala17.isChecked() && !chkGejala18.isChecked()
                && !chkGejala19.isChecked() && !chkGejala20.isChecked() && !chkGejala21.isChecked() && !chkGejala22.isChecked() && !chkGejala23.isChecked() && !chkGejala24.isChecked()
                && !chkGejala25.isChecked() && !chkGejala26.isChecked() && !chkGejala27.isChecked() && !chkGejala28.isChecked() && !chkGejala29.isChecked() && !chkGejala30.isChecked()
                && !chkGejala31.isChecked() && !chkGejala32.isChecked() && !chkGejala33.isChecked() && !chkGejala34.isChecked() && !chkGejala35.isChecked() && !chkGejala36.isChecked()
                && !chkGejala37.isChecked() && !chkGejala38.isChecked() && !chkGejala39.isChecked() && !chkGejala40.isChecked() && !chkGejala41.isChecked() && !chkGejala42.isChecked()
                && !chkGejala43.isChecked() && !chkGejala44.isChecked() && !chkGejala45.isChecked() && !chkGejala46.isChecked() && !chkGejala47.isChecked() && !chkGejala48.isChecked()
                && !chkGejala49.isChecked() && !chkGejala50.isChecked() && !chkGejala51.isChecked() && !chkGejala52.isChecked() && !chkGejala53.isChecked()) {

            Toast.makeText(MainActivity.this, "Silahkan Pilih Gejala", Toast.LENGTH_LONG).show();
            selectVar = false;
        }
        return selectVar;
    }
}