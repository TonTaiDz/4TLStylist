package com.example.a4tlstylist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a4tlstylist.dao.DatLichDAO;
import com.example.a4tlstylist.dao.LichDatCTDao;
import com.example.a4tlstylist.dao.NguoiDungDAO;
import com.example.a4tlstylist.models.DatLich;
import com.example.a4tlstylist.models.LichDatCT;
import com.example.a4tlstylist.models.NguoiDung;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    ArrayList<LichDatCT> list;
    LichDatCTDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        dao = new LichDatCTDao(this);
        list = dao.getdsDatLichCT();

        Log.e("TAG", "loadDataFromSQL: " + list.size());





//

    }
}