package com.example.a4tlstylist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a4tlstylist.dao.NguoiDungDAO;
import com.example.a4tlstylist.models.NguoiDung;

public class RegisterActivity extends AppCompatActivity {

    NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);

        EditText edtUsername = findViewById(R.id.edtUsername);
        EditText edtName = findViewById(R.id.edtName);
        EditText edtSDT = findViewById(R.id.edtSDT);
        EditText edtAddress = findViewById(R.id.edtAddress);
        EditText edtPassword = findViewById(R.id.edtPassword);
        EditText edtRePassword = findViewById(R.id.edtRePassword);
        Button btnRegister = findViewById(R.id.btnRegister);
        Button btnGoBack = findViewById(R.id.btnGoBack);

        nguoiDungDAO = new NguoiDungDAO(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = edtPassword.getText().toString();
                String repass = edtRePassword.getText().toString();

                if(!pass.equals(repass)){
                    Toast.makeText(RegisterActivity.this, "Mật khẩu không trùng nhau", Toast.LENGTH_SHORT).show();
                }else {
                    String user = edtUsername.getText().toString();
                    String name = edtName.getText().toString();
                    String phone = edtSDT.getText().toString();
                    String addredss = edtAddress.getText().toString();

                    NguoiDung nguoiDung = new NguoiDung(name, phone, addredss, user, pass);
                    boolean check = nguoiDungDAO.dangKiTaiKhoan(nguoiDung);
                    if(check){
                        Toast.makeText(RegisterActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(RegisterActivity.this, "Đăng kí thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}