package com.example.a4tlstylist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.a4tlstylist.adapter.AccountSingle;
import com.example.a4tlstylist.dao.NguoiDungDAO;
import com.example.a4tlstylist.databinding.ActivityThongTinTaiKhoanBinding;
import com.example.a4tlstylist.databinding.DialogUpdatePasswordBinding;
import com.example.a4tlstylist.models.NguoiDung;

public class ThongTinTaiKhoanShop extends AppCompatActivity {

    private ActivityThongTinTaiKhoanBinding taiKhoanBinding;

    NguoiDung nguoiDung = AccountSingle.getInstance().getNguoiDung();

    NguoiDungDAO nguoiDungDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taiKhoanBinding = ActivityThongTinTaiKhoanBinding.inflate(getLayoutInflater());
        setContentView(taiKhoanBinding.getRoot());

        initView();
    }

    private void initView() {
        nguoiDungDAO = new NguoiDungDAO(this);
        taiKhoanBinding.name.setText(nguoiDung.getTenKH());
        taiKhoanBinding.numberPhone.setText(nguoiDung.getSdt());
        taiKhoanBinding.adress.setText(nguoiDung.getDiaChi());

        taiKhoanBinding.btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(ThongTinTaiKhoanShop.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có chắc chắn muốn đăng xuất không ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(ThongTinTaiKhoanShop.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });

        taiKhoanBinding.btnCapNhatMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUpdatePasswordBinding updatePasswordBinding = DialogUpdatePasswordBinding.inflate(getLayoutInflater());
                Dialog dialog = new Dialog(ThongTinTaiKhoanShop.this);
                dialog.setContentView(updatePasswordBinding.getRoot());
                dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                updatePasswordBinding.passwordOld.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (charSequence.toString().equals(nguoiDung.getMatKhau())){
                            updatePasswordBinding.errorMass.setVisibility(View.GONE);
                        }else{
                            updatePasswordBinding.errorMass.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                updatePasswordBinding.btnUpDataPass.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int count = 0;
                        String mk = updatePasswordBinding.passwordNew.getText().toString();
                        if (updatePasswordBinding.passwordOld.getText().toString().trim().equals(nguoiDung.getMatKhau())){
                            updatePasswordBinding.errorMass.setVisibility(View.GONE);
                        }else{
                            updatePasswordBinding.errorMass.setVisibility(View.VISIBLE);
                            count++;
                        }

                        if (mk.isEmpty()){
                            count ++;
                            updatePasswordBinding.errorMass2.setVisibility(View.VISIBLE);
                        }else{
                            updatePasswordBinding.errorMass2.setVisibility(View.GONE);
                        }

                        if (count != 0){
                            return;
                        }
                        nguoiDung.setMatKhau(mk);
                        nguoiDungDAO.upDatePass(nguoiDung);
                        Toast.makeText(ThongTinTaiKhoanShop.this, "Cập nhập thành công",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }
}