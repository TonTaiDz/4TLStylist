package com.example.a4tlstylist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.a4tlstylist.dao.DatLichDAO;
import com.example.a4tlstylist.databinding.ActivityLichDatCuaKhachBinding;
import com.example.a4tlstylist.models.HoaDonCT;

import java.util.ArrayList;

public class lichDatCuaKhachActivity extends AppCompatActivity {

    private ActivityLichDatCuaKhachBinding binding;
    DatLichDAO datLichDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLichDatCuaKhachBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        datLichDAO = new DatLichDAO(this);
        Intent intent = getIntent();

        HoaDonCT hoaDonCT = (HoaDonCT) intent.getSerializableExtra("HD");

        if (hoaDonCT == null){
            return;
        }
        ArrayList<String> dvs = datLichDAO.getTenDV(hoaDonCT.getIdlichdat());
        String dichvu = "";

        for (String dv: dvs){
            dichvu += dv+" ";
        }

        binding.name.setText("Tên: "+ hoaDonCT.getTenKH());
        binding.txtDichVu.setText("Dịch vụ: "+dichvu);

        binding.txtThoiGian.setText("Thời gian: " + hoaDonCT.getThoigian());
        binding.txtNgay.setText("Ngày: " + hoaDonCT.getNgay());
        binding.txtTongTien.setText(hoaDonCT.getGiaTien() + "VND");
        binding.txtTrangThai.setText("Trạng thái: " + hoaDonCT.getTrangthai());



        if (hoaDonCT.getTrangthai().equals("Đã xong") || hoaDonCT.getTrangthai().equals("Hủy")){
            binding.btnHuy.setVisibility(View.GONE);
            binding.btnXacNhan.setVisibility(View.GONE);
        }else{
            binding.btnHuy.setVisibility(View.VISIBLE);
            binding.btnXacNhan.setVisibility(View.VISIBLE);
        }

        binding.btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(lichDatCuaKhachActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn muốn hủy lịch?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        datLichDAO.upDateStatus(hoaDonCT.getIdlichdat(),"Hủy");
                        Toast.makeText(lichDatCuaKhachActivity.this, "Hủy thành công",Toast.LENGTH_SHORT).show();
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

        binding.btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(lichDatCuaKhachActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Xác nhận hoàn thành đơn?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        datLichDAO.upDateStatus(hoaDonCT.getIdlichdat(),"Đã xong");
                        binding.txtTrangThai.setText("Trạng thái: Đã xong");
                        Toast.makeText(lichDatCuaKhachActivity.this, "Hoàn thành đơn hàng",Toast.LENGTH_SHORT).show();
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

        binding.imgBackScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(lichDatCuaKhachActivity.this, DonDatActivity.class));
                finish();
            }
        });
    }
}