package com.example.savicstest.ui.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.savicstest.R;
import com.example.savicstest.adapter.PatientAdapter;
import com.example.savicstest.model.Patient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {
    ArrayList<Patient> patientList;
    SharedPreferences sharedpreferences;
    TextView name;
    private RecyclerView mRecyclerView;
    TextView email;
    TextView age;
    FloatingActionButton btnSave;
    private PatientAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RadioGroup radioGroup;
    private RadioButton radioButton;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadData();
        buildRecyclerView(view);
        insertAndSave(view);

    }

    private void insertAndSave(View view) {

        FloatingActionButton buttonInsert = view.findViewById(R.id.addPatientBtn);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences =requireActivity().getSharedPreferences("shared preferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(patientList);
                editor.putString("task list", json);
                editor.apply();
                name = (TextView) view.findViewById(R.id.name);
                email = (TextView) view.findViewById(R.id.email);
                radioGroup = (RadioGroup) view.findViewById(R.id.radio);
                age = (TextView) view.findViewById(R.id.age);
                insertItem(name.getText().toString(), email.getText().toString(), age.getText().toString());

            }
        });
    }

    private void buildRecyclerView(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new PatientAdapter(patientList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void loadData() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<Patient>>() {}.getType();
        patientList = gson.fromJson(json, type);
        if (patientList == null) {
            patientList = new ArrayList<>();
        }
    }

    private void insertItem(String name, String email, String age) {
        patientList.add(new Patient(name, email, age));
        mAdapter.notifyItemInserted(patientList.size());
    }


}