package com.example.a4tlstylist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;

import com.example.a4tlstylist.adapter.AccountSingle;
import com.example.a4tlstylist.dao.NguoiDungDAO;
import com.example.a4tlstylist.databinding.ActivityQuenMatKhauBinding;
import com.example.a4tlstylist.models.NguoiDung;

import java.util.ArrayList;
import java.util.Random;

public class QuenMatKhauActivity extends AppCompatActivity {


    private ActivityQuenMatKhauBinding quenMatKhauBinding;
    private NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        quenMatKhauBinding = ActivityQuenMatKhauBinding.inflate(getLayoutInflater());
        setContentView(quenMatKhauBinding.getRoot());

        initView();
    }

    private void initView() {

        nguoiDungDAO = new NguoiDungDAO(this);

        ArrayList<NguoiDung> listData = nguoiDungDAO.getDataNguoiDung();
        quenMatKhauBinding.imgBackScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuenMatKhauActivity.this, LoginActivity.class));
                finish();
            }
        });

        quenMatKhauBinding.btnGetOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numberPhone = quenMatKhauBinding.edtSoDienThoai.getText().toString();

                int count = 0;

                if (numberPhone.isEmpty()){
                    quenMatKhauBinding.edtSoDienThoai.setError("rỗng");
                    count++;
                } else {
                    quenMatKhauBinding.edtSoDienThoai.setError(null);
                }

                if (count != 0){
                    return;
                }

                for (NguoiDung item : listData) {
                    if (item.getSdt().equals(numberPhone)){

                        if (ContextCompat.checkSelfPermission(QuenMatKhauActivity.this, android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                            sendOtp();
                            AccountSingle.getInstance().setNguoiDung(item);
                        } else {
                            ActivityCompat.requestPermissions(QuenMatKhauActivity.this, new String[]{android.Manifest.permission.SEND_SMS}, 100);
                        }

                    }
                }

            }
        });
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sendOtp();
            }
        }
    }

    private void sendOtp() {
        int min = 100000;
        int max = 999999;
        Random random = new Random();
        int randomNumber = random.nextInt((max - min) + 1) + min;
        String otp = String.valueOf(randomNumber);
        String phone = quenMatKhauBinding.edtSoDienThoai.getText().toString();
        SmsManager smsManager = SmsManager.getDefault();
        ArrayList<String> parts = smsManager.divideMessage("Mã otp của bạn là: " + otp);
        smsManager.sendMultipartTextMessage(phone, null, parts, null, null);
        Intent intent = new Intent(this, XacNhanMaOTPActivity.class);
        intent.putExtra("otp", otp);
        startActivity(intent);
    }
}