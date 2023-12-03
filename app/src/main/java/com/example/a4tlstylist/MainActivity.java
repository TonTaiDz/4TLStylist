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
import android.widget.Toast;

import com.example.a4tlstylist.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity{

    private ActivityMainBinding mainBinding;
    private boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        initView();
    }

    private void initView() {
        mainBinding.llTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ThongTinTaiKhoanActivity.class));
            }
        });

        mainBinding.llDatLich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DatLichActivity.class));
            }
        });

        mainBinding.llDonDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DonDatActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Nhấn lần nữa để thoát", Toast.LENGTH_SHORT).show();

        // Đặt thời gian chờ để reset trạng thái doubleBackToExitPressedOnce
        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                },
                2000 // 2 giây
        );
    }

}

