package com.example.a4tlstylist;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.a4tlstylist.adapter.AccountSingle;
import com.example.a4tlstylist.dao.DatLichDAO;
import com.example.a4tlstylist.dao.LichDatCTDao;
import com.example.a4tlstylist.databinding.ActivityDatlichBinding;
import com.example.a4tlstylist.models.DatLich;
import com.example.a4tlstylist.models.LichDatCT;
import com.example.a4tlstylist.models.NguoiDung;

import java.util.ArrayList;
import java.util.Calendar;

public class DatLichActivity extends AppCompatActivity {

    private ActivityDatlichBinding datlichBinding;

    DatLichDAO datLichDAO;

    LichDatCTDao lichDatCTDao;

    NguoiDung nguoiDung = AccountSingle.getInstance().getNguoiDung();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        datlichBinding = ActivityDatlichBinding.inflate(getLayoutInflater());
        setContentView(datlichBinding.getRoot());

        initView();
    }

    private void initView() {

        datLichDAO = new DatLichDAO(this);
        lichDatCTDao = new LichDatCTDao(this);
        ArrayList<DatLich> data = datLichDAO.getdsDatLich();

        String id = "HD_"+(data.size()+1);
        int idNd = nguoiDung.getIdNgayDung();

        datlichBinding.edtTen.setText(nguoiDung.getTenKH());
        datlichBinding.btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = datlichBinding.edtTen.getText().toString();
                String ngay = datlichBinding.edtNgay.getText().toString();
                String gio = datlichBinding.edtGio.getText().toString();
                int count = 0;
                if (ten.isEmpty()){
                    datlichBinding.edtTen.setError("rỗng");
                    count ++;
                }else{
                    datlichBinding.edtTen.setError(null);
                }

                if (ngay.isEmpty()){
                    datlichBinding.edtNgay.setError("rỗng");
                    count ++;
                }else{
                    datlichBinding.edtNgay.setError(null);
                }

                if (gio.isEmpty()){
                    datlichBinding.edtGio.setError("rỗng");
                    count ++;
                }else{
                    datlichBinding.edtGio.setError(null);
                }

                if (!(datlichBinding.cattoc.isChecked()
                        || datlichBinding.nhuom.isChecked()
                        || datlichBinding.massage.isChecked())){
                    Toast.makeText(DatLichActivity.this,"Bạn chưa chọn dịch vụ",Toast.LENGTH_SHORT).show();
                    count ++;
                }

                if (count != 0){
                    return;
                }

                DatLich datLich = new DatLich(id,gio,ngay,"Chưa Nhận",idNd);
                datLichDAO.insert(datLich);
                LichDatCT lichDatCT = null;
                LichDatCT lichDatCT1 = null;
                LichDatCT lichDatCT2 = null;
                if (datlichBinding.cattoc.isChecked()){
                    lichDatCT = new LichDatCT(id,1,30000);
                }

                if (datlichBinding.nhuom.isChecked()){
                    lichDatCT1 = new LichDatCT(id,2,30000);
                }

                if (datlichBinding.massage.isChecked()){
                    lichDatCT2 = new LichDatCT(id,3,120000);
                }

                if (lichDatCT != null){
                    lichDatCTDao.insert(lichDatCT);
                }

                if (lichDatCT1 != null){
                    lichDatCTDao.insert(lichDatCT1);
                }

                if (lichDatCT2 != null){
                    lichDatCTDao.insert(lichDatCT2);
                }
                clearData();
                Toast.makeText(DatLichActivity.this,"Tạo thành công",Toast.LENGTH_SHORT).show();
            }
        });



        datlichBinding.edtGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimePickerDialog();
            }
        });

        datlichBinding.btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DatLichActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        datlichBinding.edtNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

    }

    private void showDateTimePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Tạo một đối tượng TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Kiểm tra xem giờ đã chọn có nằm trong khoảng giờ hành chính không
                        if (isInWorkingHours(hourOfDay)) {
                            // Xử lý khi người dùng chọn giờ trong khoảng hành chính
                            String selectedDateTime = hourOfDay + ":" + minute;
                            datlichBinding.edtGio.setText(selectedDateTime);
                        } else {
                            // xử lý ngoài h hành chính
                            Toast.makeText(DatLichActivity.this,"Ngoài giờ làm việc",Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                hourOfDay,
                minute,
                true // true để sử dụng định dạng 24 giờ, false để sử dụng định dạng 12 giờ
        );

        // Hiển thị hộp thoại chọn giờ
        timePickerDialog.show();
    }

    // Phương thức kiểm tra xem giờ có nằm trong khoảng giờ hành chính không
    private boolean isInWorkingHours(int hourOfDay) {
        return hourOfDay >= 8 && hourOfDay <= 17; // Giả sử giờ hành chính từ 8:00 sáng đến 5:00 chiều
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Tạo một đối tượng DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Xử lý khi người dùng chọn ngày
                        String selectedDate =  dayOfMonth + "/" + (month + 1)  + "/" + year;
                        datlichBinding.edtNgay.setText(selectedDate);
                    }
                },
                year,
                month,
                dayOfMonth
        );

        // Hiển thị hộp thoại chọn ngày
        datePickerDialog.show();
    }

    public void clearData(){
        datlichBinding.edtTen.setText("");
        datlichBinding.edtGio.setText("");
        datlichBinding.edtNgay.setText("");

        datlichBinding.nhuom.setChecked(false);
        datlichBinding.cattoc.setChecked(false);
        datlichBinding.massage.setChecked(false);

    }
}
