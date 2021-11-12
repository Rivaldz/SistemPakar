package com.example.diagnosahamapadi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diagnosahamapadi.R;

import java.util.ArrayList;
import java.util.List;

public class CFAdapter extends RecyclerView.Adapter<CFAdapter.ViewHolder>{
    private List<String>  SubjectValues = new ArrayList<>();
    private List<String>  SubjectValues2 = new ArrayList<>();
//    private String[] SubjectValues;
    private Context context;

    public CFAdapter(List<String> subjectValues,List<String >subjectValues2, Context context) {
        SubjectValues = subjectValues;
        SubjectValues2 = subjectValues2;
        this.context = context;
    }

    @NonNull
    @Override
    public CFAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_cf, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CFAdapter.ViewHolder holder, int position) {
        holder.namaPenyakit.setText(SubjectValues2.get(position));
        holder.nilai.setText(SubjectValues.get(position));
    }

    @Override
    public int getItemCount() {
        return SubjectValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namaPenyakit, nilai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaPenyakit = itemView.findViewById(R.id.textItemPenyakit);
            nilai = itemView.findViewById(R.id.textitemNilai);
        }
    }
}
