package com.example.savicstest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.savicstest.R;
import com.example.savicstest.model.Patient;

import java.util.ArrayList;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.ExampleViewHolder> {
    private ArrayList<Patient> mExampleList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView patientName;
        public TextView patientEmail;
        public TextView patientGender;
        public ExampleViewHolder(View itemView) {
            super(itemView);
            patientName = itemView.findViewById(R.id.patient_name);
            patientEmail = itemView.findViewById(R.id.patient_email);
            patientGender = itemView.findViewById(R.id.patient_gender);
        }
    }
    public PatientAdapter(ArrayList<Patient> exampleList) {
        mExampleList = exampleList;
    }
    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }
    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        Patient currentItem = mExampleList.get(position);
        holder.patientEmail.setText(currentItem.getEmail());
        holder.patientName.setText(currentItem.getFullName());
        holder.patientGender.setText(currentItem.getAge());
    }
    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}