package com.example.a4tlstylist;

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

import androidx.appcompat.app.AppCompatActivity;

import com.example.a4tlstylist.adapter.AccountSingle;
import com.example.a4tlstylist.dao.NguoiDungDAO;
import com.example.a4tlstylist.databinding.ActivityThongTinTaiKhoanBinding;
import com.example.a4tlstylist.databinding.DialogUpdatePasswordBinding;
import com.example.a4tlstylist.databinding.DialogUpdateTaiKhoanBinding;
import com.example.a4tlstylist.models.NguoiDung;

public class ThongTinTaiKhoanActivity extends AppCompatActivity {

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

        taiKhoanBinding.btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        taiKhoanBinding.btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ThongTinTaiKhoanActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có chắc chắn muốn đăng xuất không ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(ThongTinTaiKhoanActivity.this, LoginActivity.class);
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
                Dialog dialog = new Dialog(ThongTinTaiKhoanActivity.this);
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
                        Toast.makeText(ThongTinTaiKhoanActivity.this, "Cập nhập thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        taiKhoanBinding.capNhatThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUpdateTaiKhoanBinding updateAccountBinding = DialogUpdateTaiKhoanBinding.inflate(getLayoutInflater());
                Dialog dialog = new Dialog(ThongTinTaiKhoanActivity.this);
                dialog.setContentView(updateAccountBinding.getRoot());
                dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                updateAccountBinding.fullName.setText(nguoiDung.getTenKH());
                updateAccountBinding.numberPhone.setText(nguoiDung.getSdt());
                updateAccountBinding.adress.setText(nguoiDung.getDiaChi());

                updateAccountBinding.fullName.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (updateAccountBinding.fullName.getText().toString().trim().length() < 5) {
                            updateAccountBinding.fullName.setError("Tên người dùng không được nhỏ hơn 5 ký tự");
                        } else updateAccountBinding.fullName.setError(null);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                updateAccountBinding.numberPhone.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (updateAccountBinding.numberPhone.getText().toString().trim().length() != 10) {
                            updateAccountBinding.numberPhone.setError("Số điện thoại không đúng định dạng");
                        } else updateAccountBinding.numberPhone.setError(null);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });


                updateAccountBinding.adress.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (updateAccountBinding.adress.getText().toString().trim().length() < 5) {
                            updateAccountBinding.adress.setError("Địa chỉ người dùng không được nhỏ hơn 5 ký tự");
                        } else updateAccountBinding.adress.setError(null);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                updateAccountBinding.btnUpDataInfor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (updateAccountBinding.fullName.getText().toString().trim().length() > 5
                        && updateAccountBinding.numberPhone.getText().toString().trim().length() == 10
                        && updateAccountBinding.adress.getText().toString().trim().length() >5 ){
                            nguoiDung.setTenKH(updateAccountBinding.fullName.getText().toString().trim());
                            nguoiDung.setDiaChi(updateAccountBinding.adress.getText().toString().trim());
                            nguoiDung.setSdt(updateAccountBinding.numberPhone.getText().toString().trim());
                            nguoiDungDAO.updateInfo(nguoiDung);
                            Toast.makeText(ThongTinTaiKhoanActivity.this,"Cập nhật thành công", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                            recreate();
                        } else Toast.makeText(ThongTinTaiKhoanActivity.this,"Vui lòng nhập đúng các yêu cầu!", Toast.LENGTH_LONG).show();
                    }
                });

                dialog.show();
            }
        });
    }
}