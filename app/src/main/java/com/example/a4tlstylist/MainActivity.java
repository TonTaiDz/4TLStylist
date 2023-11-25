package com.example.a4tlstylist;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DateFormat;
import java.util.Calendar;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity{

    TextView tvDate;
    Button btPickDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout llDonDat = findViewById(R.id.llDonDat);
        LinearLayout llDatLich = findViewById(R.id.llDatLich);
        LinearLayout llTaiKhoan = findViewById(R.id.llTaiKhoan);





        //lấy role đã lưu trong SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("dataUser",MODE_PRIVATE);

        int role = sharedPreferences.getInt("role",-1);
        switch (role){
            case 1:// người dùng
                llDatLich.setVisibility(View.GONE);
                llTaiKhoan.setVisibility(View.GONE);
                break;
            case 2:// thợ tóc
                llDonDat.setVisibility(View.GONE);
                llTaiKhoan.setVisibility(View.GONE);
                break;
            default:
                llDonDat.setVisibility(View.GONE);
                llDatLich.setVisibility(View.GONE);
                llTaiKhoan.setVisibility(View.GONE);
                break;
        }

        llDonDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,DonDatActivity.class));
            }
        });
        llDatLich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,DatLichActivity.class));
            }
        });

    }

}

