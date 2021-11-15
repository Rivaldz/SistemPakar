package com.example.diagnosahamapadi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diagnosahamapadi.R;
import com.example.model.HasilAkhir;

import java.util.ArrayList;
import java.util.List;

public class FMAdapter extends RecyclerView.Adapter<FMAdapter.ViewHolder> {
//    private List<String> namaHamaPenyakit = new ArrayList<>();
//    private List<String>  hasilCF = new ArrayList<>();
    private List<HasilAkhir>  hasilAkhirsvar = new ArrayList<>();
    //    private String[] SubjectValues;
    private Context context;

    public FMAdapter(List<HasilAkhir> hasilAkhirs, Context context) {
        this.hasilAkhirsvar = hasilAkhirs;
        this.context = context;
    }

    @NonNull
    @Override
    public FMAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_fm, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FMAdapter.ViewHolder holder, int position) {
        System.out.println("Hsil dari bind holder " + hasilAkhirsvar.get(position).namaPenyakit);

        holder.namaPenha.setText(hasilAkhirsvar.get(position).namaPenyakit);
        holder.resultCF.setText(hasilAkhirsvar.get(position).hasilCF);
        holder.resulFM.setText(hasilAkhirsvar.get(position).hasiFM);
//        holder.namaPenha.setText("sej");
//        holder.resultCF.setText("sut");
//        holder.resulFM.setText("sitj");
    }

    @Override
    public int getItemCount() {
        return hasilAkhirsvar.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namaPenha, resultCF, resulFM;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaPenha = itemView.findViewById(R.id.textItemPenyakitFM);
            resultCF  = itemView.findViewById(R.id.textitemNilaiCF);
            resulFM   = itemView.findViewById(R.id.textitemNilaiFM);
        }
    }
}
