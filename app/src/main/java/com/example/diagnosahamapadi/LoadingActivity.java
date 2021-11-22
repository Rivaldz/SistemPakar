package com.example.diagnosahamapadi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ProgressBar;

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
import java.util.concurrent.TimeUnit;

public class LoadingActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private SharedPreferences sharedPreferences;
    private String userId;
    private DatabaseReference databaseReference;
//    android.os.Handler customHandler = new android.os.Handler();
//
//    List<ShowUser> st = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        progressBar = findViewById(R.id.loadProgres);

        sharedPreferences = getSharedPreferences("user_details",MODE_PRIVATE);
        userId = sharedPreferences.getString("username",null);


//        Defuzzyfikasi defuzzyfikasi = new Defuzzyfikasi();
//        defuzzyfikasi.getLOMFuzzy();
//        defuzzyfikasi.sumKeanggotaan();
        moveInten();

//        updateTimerThread.run();
    }


//    private Runnable updateTimerThread = new Runnable()
//    {
//        public void run()
//        {
//            //write here whaterver you want to repeat
//            moveInten();
//            customHandler.postDelayed(this, 1000);
//        }
//    };

    private void moveInten(){
//        List<String> st = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("DefuzzyLOM");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    ShowUser showUser = dataSnapshot.getValue(ShowUser.class);
//                  showUser.setKeyMethod(dataSnapshot.getKey());
//                    st.add(showUser);
                    if (dataSnapshot.getKey().equals(userId)){
//                        try {
//                            TimeUnit.SECONDS.sleep(3);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                        Intent i = new Intent(LoadingActivity.this, CFResult.class);
//                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        break;

                    }
                }
//                if (){
//                    try {
//                        TimeUnit.SECONDS.sleep(2);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                        onDestroy();
//                    Intent intent = new Intent(LoadingActivity.this, ResultActivity.class);

//                        Intent intent = new Intent(MainActivity.this, LoadingActivity.class);
//                    startActivity(intent);
//                    finish();
//                    }
//                }
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    ShowUser showUser = dataSnapshot.getValue(ShowUser.class);

//                    String statusSt = snapshot.child("countStatus").getValue().toString();

//                    LastModel showUser = dataSnapshot.getValue(LastModel.class);
//                    showUser.setKeyMethod(dataSnapshot.getKey());
//                    st.add(showUser.getCountStatus());

//                    if (st.get(1).countStatus.matches("1")){
//                        try {
//                            TimeUnit.SECONDS.sleep(2);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
////                        onDestroy();
//                        Intent intent = new Intent(LoadingActivity.this, ResultActivity.class);
//
////                        Intent intent = new Intent(MainActivity.this, LoadingActivity.class);
//                        startActivity(intent);
//                        finish();
////                    }
//                }

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
}