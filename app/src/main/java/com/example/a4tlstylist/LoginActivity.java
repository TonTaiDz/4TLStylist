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

import com.example.a4tlstylist.adapter.AccountSingle;
import com.example.a4tlstylist.dao.DatLichDAO;
import com.example.a4tlstylist.dao.LichDatCTDao;
import com.example.a4tlstylist.dao.NguoiDungDAO;
import com.example.a4tlstylist.databinding.ActivityDangNhapBinding;
import com.example.a4tlstylist.models.DatLich;
import com.example.a4tlstylist.models.HoaDonCT;
import com.example.a4tlstylist.models.LichDatCT;
import com.example.a4tlstylist.models.NguoiDung;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

//    private ActivityDangNhapBinding dangNhapBinding;

    private ActivityDangNhapBinding dangNhapBinding;

    private NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dangNhapBinding = ActivityDangNhapBinding.inflate(getLayoutInflater());
        setContentView(dangNhapBinding.getRoot());
        initView();
    }

    private void initView() {

        nguoiDungDAO = new NguoiDungDAO(this);

        ArrayList<NguoiDung> listdata = nguoiDungDAO.getDataNguoiDung();
        dangNhapBinding.btnForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, QuenMatKhauActivity.class);
                startActivity(intent);
            }
        });

        dangNhapBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = dangNhapBinding.edtUsername.getText().toString().trim();
                String matKhau = dangNhapBinding.edtPassword.getText().toString().trim();
                int count = 0;
                if (userName.isEmpty()){
                    dangNhapBinding.edtUsername.setError("Rỗng");
                    count ++;
                }else{
                    dangNhapBinding.edtUsername.setError(null);
                }

                if (matKhau.isEmpty()){
                    dangNhapBinding.edtPassword.setError("Rỗng");
                    count ++;
                }else{
                    dangNhapBinding.edtPassword.setError(null);
                }

                if (count != 0){
                    return;
                }

                boolean checkLogin=false;

                for (NguoiDung item: listdata) {
                    if ((item.getTenDangNhap().equals(userName)
                            || item.getSdt().equals(userName))
                            && item.getMatKhau().equals(matKhau)){
                        AccountSingle.getInstance().setNguoiDung(item);
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        if (item.getRole() == 1){
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }else{
                            startActivity(new Intent(LoginActivity.this, MainTho.class));
                            finish();
                        }
                        checkLogin=true;
                        break;
                    }
                }
                if (!checkLogin){
                    Toast.makeText(LoginActivity.this, "Thông tin đăng nhập sai", Toast.LENGTH_SHORT).show();
                }


            }
        });

        dangNhapBinding.txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}