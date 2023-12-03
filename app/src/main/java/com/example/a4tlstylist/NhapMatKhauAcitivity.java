package com.example.a4tlstylist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.a4tlstylist.adapter.AccountSingle;
import com.example.a4tlstylist.dao.NguoiDungDAO;
import com.example.a4tlstylist.databinding.ActivityNhapMatKhauAcitivityBinding;
import com.example.a4tlstylist.models.NguoiDung;

public class NhapMatKhauAcitivity extends AppCompatActivity {

    private ActivityNhapMatKhauAcitivityBinding binding;
    private NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNhapMatKhauAcitivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    private void initView() {
        nguoiDungDAO = new NguoiDungDAO(this);
        binding.imgBackScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NhapMatKhauAcitivity.this, XacNhanMaOTPActivity.class));
                finish();
            }
        });

        binding.btnCapNHatMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mk = binding.edtMaKhauMoi.getText().toString();
                String xn = binding.edtXacNhanMatKhau.getText().toString();

                int count = 0;

                if (mk.isEmpty()){
                    binding.edtMaKhauMoi.setError("rỗng");
                    count++;
                } else {
                    binding.edtMaKhauMoi.setError(null);
                }

                if (xn.isEmpty()){
                    binding.edtXacNhanMatKhau.setError("rỗng");
                    count++;
                } else {
                    binding.edtXacNhanMatKhau.setError(null);
                }

                if (count != 0){
                    return;
                }

                if (xn.equals(mk)){
                    NguoiDung nguoiDung = AccountSingle.getInstance().getNguoiDung();
                    nguoiDung.setMatKhau(mk);
                    nguoiDungDAO.upDatePass(nguoiDung);
                    Toast.makeText(NhapMatKhauAcitivity.this, "Thành công",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(NhapMatKhauAcitivity.this, LoginActivity.class));
                    finish();
                }else{
                    Toast.makeText(NhapMatKhauAcitivity.this, "Không khớp mật khẩu",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}