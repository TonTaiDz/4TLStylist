package com.example.a4tlstylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.a4tlstylist.adapter.DatLichAdapter;
import com.example.a4tlstylist.dao.DatLichDAO;

public class DonDatActivity extends AppCompatActivity {
    private DatLichDAO datLichDAO;
    private RecyclerView rcvDonDat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ativity_dslichdat);

        rcvDonDat = findViewById(R.id.rcvDonDat);
        datLichDAO = new DatLichDAO(this);



    }




}