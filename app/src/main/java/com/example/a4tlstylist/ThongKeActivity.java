package com.example.a4tlstylist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.a4tlstylist.Funtions.MoneyFormat;
import com.example.a4tlstylist.dao.DatLichDAO;
import com.example.a4tlstylist.databinding.ActivityThongKeBinding;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ThongKeActivity extends AppCompatActivity {

    private ActivityThongKeBinding thongKeBinding;
    DatLichDAO datLichDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thongKeBinding = ActivityThongKeBinding.inflate(getLayoutInflater());
        setContentView(thongKeBinding.getRoot());

        initView();
    }

    private void initView() {
        datLichDAO = new DatLichDAO(this);
        LocalDate ngayHienTai = LocalDate.now();

        // Định dạng ngày theo mẫu mong muốn
        DateTimeFormatter dinhDang = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String ngayDinhDang = ngayHienTai.format(dinhDang);

        String ttThang = MoneyFormat.moneyFormat(datLichDAO.getTT30()) + "VND";
        String ttngay = MoneyFormat.moneyFormat(datLichDAO.getTT(ngayDinhDang)) +"VND";

        int luotM = datLichDAO.getLuotKhach();
        thongKeBinding.tienThang.setText(ttThang);
        thongKeBinding.tienngay.setText(ttngay);
        thongKeBinding.luotkhach.setText(luotM+"");

        thongKeBinding.imgBackScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThongKeActivity.this, MainTho.class));
                finish();
            }
        });
    }
}