package com.example.diagnosahamapadi.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diagnosahamapadi.R;
import com.example.model.DataClass;
import com.example.model.Gejala;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    List<Gejala> gejalaModel;

    public RecyclerViewAdapter(List<Gejala> gejalaModel) {
        this.gejalaModel = gejalaModel;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //mengambil objek context dari parrent
        Context context = parent.getContext();
        //objek context digunakan untuk membuat object layoutinflater
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        //object layoutInflater digunakan untuk membuat object view yang merupakan hasil inflate layout
        View gejalaView = layoutInflater.inflate(R.layout.view_design,parent,false);
        //digunakan untuk membuat object viewHolder
        MyViewHolder viewHolder = new MyViewHolder(gejalaView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //ambil satu item hero
        Gejala gejala = gejalaModel.get(position);
//        String email = emailUser.get(position);
        //set text heroName berdasarkan data dari model hero
        holder.nama.setText("Nama Gejala " + gejala.getNamaGejala());
        holder.kode.setText(" Kode Gejala " + gejala.getKodeGejala());

        holder.listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] action = {"Test A, Test B, Test C"};
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setItems(action, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
            }
        });
//        holder.bobot.setText(gejala.getBkobotGejala());
//        System.out.println("ini adalah rank Adapter " + emailUser.get(2));

    }

    @Override
    public int getItemCount() {
        return (gejalaModel!= null) ? gejalaModel.size() : 0 ;
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        public TextView kode;
        public TextView nama;
        public  LinearLayout listItem;
//        public TextView bobot;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            kode = itemView.findViewById(R.id.kode);
            nama = itemView.findViewById(R.id.nama);
            listItem = itemView.findViewById(R.id._diagnosaItem);
//            bobot = itemView.findViewById(R.id.bobot);
        }
    }
}
