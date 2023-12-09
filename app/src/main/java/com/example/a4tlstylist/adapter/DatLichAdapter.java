package com.example.a4tlstylist.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.a4tlstylist.Funtions.MoneyFormat;
import com.example.a4tlstylist.R;
import com.example.a4tlstylist.dao.DatLichDAO;
import com.example.a4tlstylist.databinding.ItemDondatBinding;
import com.example.a4tlstylist.models.DatLich;
import com.example.a4tlstylist.models.HoaDonCT;

import java.util.ArrayList;

public class DatLichAdapter extends RecyclerView.Adapter<DatLichAdapter.ViewHolder>{

    private ArrayList<HoaDonCT> listData;
    private Click click;

    public DatLichAdapter(ArrayList<HoaDonCT> listData, Click click) {
        this.listData = listData;
        this.click = click;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDondatBinding binding = ItemDondatBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HoaDonCT hoaDonCT = listData.get(position);
        holder.binding.txtTenKH.setText("Tên KH: " + hoaDonCT.getTenKH());
        holder.binding.txtIDLichDat.setText("Id lịch: "+ hoaDonCT.getIdlichdat());
        holder.binding.txtThoiGian.setText("Thời gian: "+ hoaDonCT.getThoigian()+"\n"+hoaDonCT.getNgay());
        holder.binding.txtTrangThai.setText("Trạng thái: " + hoaDonCT.getTrangthai());
        holder.binding.txtGia.setText("Đơn giá: " + MoneyFormat.moneyFormat(hoaDonCT.getGiaTien()));
        holder.binding.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.click(hoaDonCT);
            }
        });
    }

    public interface Click {
        void click(HoaDonCT hoaDonCT);
    }


    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemDondatBinding binding;

        public ViewHolder(@NonNull ItemDondatBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
