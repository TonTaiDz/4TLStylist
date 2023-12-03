package com.example.a4tlstylist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a4tlstylist.dao.NguoiDungDAO;
import com.example.a4tlstylist.databinding.ActivityDangKiBinding;
import com.example.a4tlstylist.models.NguoiDung;

import java.util.ArrayList;

import kotlin.text.Regex;

public class RegisterActivity extends AppCompatActivity {

    private ActivityDangKiBinding dangKiBinding;
    private NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dangKiBinding = ActivityDangKiBinding.inflate(getLayoutInflater());
        setContentView(dangKiBinding.getRoot());

        initView();
    }

    private void initView() {

        nguoiDungDAO = new NguoiDungDAO(this);
        dangKiBinding.btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });

        dangKiBinding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = 0;
                String userName = dangKiBinding.edtUsername.getText().toString();
                String name = dangKiBinding.edtName.getText().toString();
                String phoneNumber = dangKiBinding.edtSDT.getText().toString();
                String diachi = dangKiBinding.edtAddress.getText().toString();
                String pass = dangKiBinding.edtPassword.getText().toString();
                String passConf = dangKiBinding.edtRePassword.getText().toString();

                if (userName.isEmpty()){
                    dangKiBinding.edtUsername.setError("Rỗng");
                    count ++;
                }else{
                    dangKiBinding.edtUsername.setError(null);
                }

                if (name.isEmpty()){
                    dangKiBinding.edtName.setError("Rỗng");
                    count ++;
                }else{
                    dangKiBinding.edtName.setError(null);
                }

                if (phoneNumber.isEmpty()){
                    dangKiBinding.edtSDT.setError("Rỗng");
                    count ++;
                }else{
                    Regex regex = new Regex("^\\+?[0-9]{10,13}$");
                    if (!regex.matches(phoneNumber)){
                        dangKiBinding.edtSDT.setError("Sai định dạng");
                        count ++;
                    }else{
                        dangKiBinding.edtSDT.setError(null);
                    }
                }

                if (diachi.isEmpty()){
                    dangKiBinding.edtAddress.setError("Rỗng");
                    count ++;
                }else{
                    dangKiBinding.edtAddress.setError(null);
                }

                if (pass.isEmpty()){
                    dangKiBinding.edtPassword.setError("Rỗng");
                    count ++;
                }else{
                    dangKiBinding.edtPassword.setError(null);
                }

                if (passConf.isEmpty()){
                    dangKiBinding.edtRePassword.setError("Rỗng");
                    count ++;
                }else{
                    dangKiBinding.edtRePassword.setError(null);
                }

                if (!pass.equals(passConf)){
                    dangKiBinding.edtRePassword.setError("mật khẩu sai");
                    count ++;
                }else{
                    dangKiBinding.edtRePassword.setError(null);
                }

                if (count != 0){
                    return;
                }

                NguoiDung nguoiDung = new NguoiDung(name,diachi,phoneNumber,userName,pass,1);
                nguoiDungDAO.insert(nguoiDung);
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                Toast.makeText(RegisterActivity.this,"Tạo tại thành công",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}