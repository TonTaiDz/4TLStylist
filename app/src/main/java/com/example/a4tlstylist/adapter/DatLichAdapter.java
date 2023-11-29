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


import com.example.a4tlstylist.R;
import com.example.a4tlstylist.models.DatLich;

import java.util.ArrayList;

public class DatLichAdapter extends RecyclerView.Adapter<DatLichAdapter.ViewHolder>{

    private Context context;
    private ArrayList<DatLich> list;

    public DatLichAdapter(Context context, ArrayList<DatLich> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_dondat,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtIDNguoiDung.setText(String.valueOf(list.get(position).getIdnguoidung()));
//        holder.txtTenKH.setText(list.get(position).());
        holder.txtIDLichDat.setText(list.get(position).getIdlichdat());
        holder.txtThoiGian.setText(list.get(position).getThoigian());
//        holder.txtGia.setText(String.valueOf(list.get(position).()+ " VND"));


        holder.btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtIDNguoiDung,txtTenKH,txtIDLichDat,txtThoiGian,txtGia,txtTrangThai;
        Button btnHuy;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIDNguoiDung = itemView.findViewById(R.id.txtIDNguoiDung);
            txtTenKH = itemView.findViewById(R.id.txtTenKH);
            txtIDLichDat = itemView.findViewById(R.id.txtIDLichDat);
            txtThoiGian = itemView.findViewById(R.id.txtThoiGian);
            txtTrangThai = itemView.findViewById(R.id.txtTrangThai);
            txtGia = itemView.findViewById(R.id.txtGia);
            btnHuy = itemView.findViewById(R.id.btnHuy);
        }
    }
}
