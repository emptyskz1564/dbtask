package com.example.sqlnetwork.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlnetwork.domain.ClassResult;

import java.util.ArrayList;
import java.util.List;

public class GetClassListAdapter extends RecyclerView.Adapter<GetClassListAdapter.InnerHolder> {
    private List<ClassResult.Class> data = new ArrayList<>();


    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(ClassResult classResult) {
        data.clear();
        data.addAll(classResult.getData());
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
