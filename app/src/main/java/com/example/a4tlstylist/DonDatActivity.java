package com.example.a4tlstylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.a4tlstylist.adapter.AccountSingle;
import com.example.a4tlstylist.adapter.DatLichAdapter;
import com.example.a4tlstylist.dao.DatLichDAO;
import com.example.a4tlstylist.databinding.AtivityDslichdatBinding;
import com.example.a4tlstylist.models.HoaDonCT;

import java.util.ArrayList;

public class DonDatActivity extends AppCompatActivity {

    private AtivityDslichdatBinding dslichdatBinding;
    private DatLichDAO datLichDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dslichdatBinding = AtivityDslichdatBinding.inflate(getLayoutInflater());
        setContentView(dslichdatBinding.getRoot());

        initView();
    }

    private void initView() {
        datLichDAO = new DatLichDAO(this);
        int role = AccountSingle.getInstance().getNguoiDung().getRole();
        ArrayList<HoaDonCT> listdata = new ArrayList<>();
        if (role == 1){
            dslichdatBinding.ivTitle.setText("Lịch của bạn");
            listdata = datLichDAO.getHd(AccountSingle.getInstance().getNguoiDung().getIdNgayDung());
            DatLichAdapter adapter = new DatLichAdapter(listdata, new DatLichAdapter.Click() {
                @Override
                public void click(HoaDonCT hoaDonCT) {
                    if (role == 1){
                        Intent intent = new Intent(DonDatActivity.this, LichDatCuaBanActivity.class);
                        intent.putExtra("HD",hoaDonCT);
                        startActivity(intent);
                    }
                }
            });
            dslichdatBinding.rcvDonDat.setAdapter(adapter);
            dslichdatBinding.rcvDonDat.setLayoutManager(new LinearLayoutManager(this));
        }else{
            dslichdatBinding.ivTitle.setText("Lịch của Shop");
            listdata = datLichDAO.getHd();
            DatLichAdapter adapter = new DatLichAdapter(listdata, new DatLichAdapter.Click() {
                @Override
                public void click(HoaDonCT hoaDonCT) {
                    Intent intent = new Intent(DonDatActivity.this, lichDatCuaKhachActivity.class);
                    intent.putExtra("HD",hoaDonCT);
                    startActivity(intent);
                }
            });
            dslichdatBinding.rcvDonDat.setAdapter(adapter);
            dslichdatBinding.rcvDonDat.setLayoutManager(new LinearLayoutManager(this));
        }
    }


}