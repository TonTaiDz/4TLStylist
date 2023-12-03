package com.example.a4tlstylist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.a4tlstylist.databinding.ActivityXacNhanMaOtpactivityBinding;

public class XacNhanMaOTPActivity extends AppCompatActivity {

    private ActivityXacNhanMaOtpactivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityXacNhanMaOtpactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    private void initView() {
        Intent intent = getIntent();

        if (intent == null){
            return;
        }

        String otp = intent.getStringExtra("otp");
        binding.imgBackScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(XacNhanMaOTPActivity.this, QuenMatKhauActivity.class));
                finish();
            }
        });

        binding.btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ma = binding.edtMaXacNhan.getText().toString();

                int count = 0;

                if (ma.isEmpty()){
                    binding.edtMaXacNhan.setError("rỗng");
                    count ++;
                } else {
                    binding.edtMaXacNhan.setError(null);
                }

                if (count != 0){
                    return;
                }

                if (ma.equals(otp)){
                    startActivity(new Intent(XacNhanMaOTPActivity.this, NhapMatKhauAcitivity.class));
                    finish();
                }
            }
        });
    }
}