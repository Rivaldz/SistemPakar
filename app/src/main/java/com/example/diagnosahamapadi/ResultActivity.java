package com.example.diagnosahamapadi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.diagnosahamapadi.webview.PenyakitHama;
import com.example.fuzzy.Defuzzyfikasi;
import com.example.model.LastModel;
import com.example.model.ShowUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {
    DatabaseReference mDatabase;

    EditText namaPH, keparahan, kemungkinan;
    Button selsai;
    TextView solusi;

    SharedPreferences sharedPreferences, sharedPreferencesSession;

    List<ShowUser> listMethod = new ArrayList<>();

    MainActivity mainActivity = new MainActivity();

    String userIdPub,hama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        namaPH = findViewById(R.id.editTextNamaPenyakit);
        keparahan = findViewById(R.id.editTextKeparahan);
        kemungkinan = findViewById(R.id.editTextKemungkinanPenyakit);
        selsai = findViewById(R.id.buttonSelesai);
        solusi = findViewById(R.id.textViewLihatObat);

        String stringSession = mainActivity.sessionString;

        sharedPreferences = getSharedPreferences("user_details",MODE_PRIVATE);
        String userId = sharedPreferences.getString("username",null);
        userIdPub = userId;

        sharedPreferencesSession = getSharedPreferences("session",MODE_PRIVATE);
        String idSession = sharedPreferencesSession.getString("varSession",null);

        deleteHash();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("LastResult").child(userId);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
              for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                  ShowUser showUser = dataSnapshot.getValue(ShowUser.class);
                  showUser.setKeyMethod(dataSnapshot.getKey());
                  listMethod.add(showUser);

              }
//                Log.e("Lihat nama penyakit : ",listMethod.get(0).getNamaPenyakit());
//                Log.e("Lihat Key method : ", listMethod.get(0).getKeyMethod());
//                Log.e("Lihat hasil method : ", listMethod.get(0).getNilaiAkhir());
//
//                Log.e("Lihat nama penyakit : ",listMethod.get(1).getNamaPenyakit());
//                Log.e("Lihat Key method : ", listMethod.get(1).getKeyMethod());
//                Log.e("Lihat hasil method : ", listMethod.get(1).getNilaiAkhir());

                String namaPHST = listMethod.get(1).getNamaPenyakit();
                String namaMethodeCFSt = listMethod.get(0).getKeyMethod();
                String namaMethodeFMSt = listMethod.get(1).getKeyMethod();
                String nilaiKahirCFSt = listMethod.get(0).getNilaiAkhir();
                String nilaiKahirFMSt = listMethod.get(1).getNilaiAkhir();
                String hasilBobot = null;
                if (Double.parseDouble(nilaiKahirFMSt) <= 0.4){
                   hasilBobot = "RINGAN" ;
                }else if (Double.parseDouble(nilaiKahirFMSt) > 0.4 && Double.parseDouble(nilaiKahirFMSt) < 0.8){
                    hasilBobot = "SEDANG" ;
                }else if (Double.parseDouble(nilaiKahirFMSt) >= 0.8){
                    hasilBobot = "PARAH" ;
                }

                namaPH.setText(namaPHST);
                kemungkinan.setText(nilaiKahirCFSt);
                keparahan.setText(hasilBobot);

                hama = namaPHST;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        selsai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference mDatabaseFunsiImp = FirebaseDatabase.getInstance().getReference().child("Fungsiimplikasi").child(userIdPub);
                DatabaseReference mDatabaseDefuzzy = FirebaseDatabase.getInstance().getReference().child("Defuzzyfikasi").child(userIdPub);
                DatabaseReference mDatabaseResult = FirebaseDatabase.getInstance().getReference();
                mDatabaseFunsiImp.removeValue();
//                mDatabaseResult.removeValue();
                mDatabaseDefuzzy.removeValue();
                LastModel lastModelSes = new LastModel("Penyakit","0");
                mDatabaseResult.child("LastResult").child(userId).child("SES").child("keyy").setValue(lastModelSes);
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            }
        });

        solusi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultActivity.this, PenyakitHama.class));
            }
        });

    }
    private void deleteHash(){
        Defuzzyfikasi defuzzyfikasi = new Defuzzyfikasi();
        defuzzyfikasi.multiMapRendah.clear();
        defuzzyfikasi.multiMapSedang.clear();
        defuzzyfikasi.multiMapTinggi.clear();
    }
}