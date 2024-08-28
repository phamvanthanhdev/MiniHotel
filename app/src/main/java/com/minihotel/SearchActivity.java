package com.minihotel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.minihotel.utils.Common;
import com.minihotel.utils.Utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity {
    private TextView txtNgayDenDat, txtNgayDiDat;
    private TextInputEditText edtSoNguoi, edtGiaThapNhat, edtGiaCaoNhat;
    private Button btnTimKiem;
    private ConstraintLayout layoutNgayDen, layoutNgayDi;
    private List<String> mucGias = new ArrayList<>();
    private List<String> soNguois = new ArrayList<>();
    private int soNguoi = 1;
    private long giaThapNhat = 0, giaCaoNhat = 1000000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initViews();
        showData();
        setEvents();
        setupBtnBack();
    }

    private void setupBtnBack(){
        ImageButton btnBack = findViewById(R.id.imageButton);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setEvents() {
        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String mucGia = autoTextMucGia.getText().toString();
                String soNguoiText = autoTextSoNguoi.getText().toString();
                long giaMin = 0, giaMax = 0;
                for (int i = 0; i < mucGias.size(); i++) {
                    if(mucGias.get(i).equals(mucGia)){
                        if(i == 0) {giaMin = 0; giaMax = 5000000;}
                        if(i == 1) {giaMin = 0; giaMax = 200000;}
                        if(i == 2) {giaMin = 200000; giaMax = 500000;}
                        if(i == 3) {giaMin = 500000; giaMax = 1000000;}
                        if(i == 4) {giaMin = 1000000; giaMax = 5000000;}
                        break;
                    }
                }
                int soNguoi = 0;
                for (int i = 0; i < soNguois.size(); i++) {
                    if(soNguois.get(i).equals(soNguoiText)){
                        if(i == 0) soNguoi = 1;
                        if(i == 1) soNguoi = 2;
                        if(i == 2) soNguoi = 3;
                        if(i == 3) soNguoi = 4;
                        if(i == 4) soNguoi = 5;
                        break;
                    }
                }
                if(soNguoi <= 0 || giaMax <= 0){
                    Toast.makeText(SearchActivity.this, "Vui lòng chọn đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }*/
                String soNguoiStr = edtSoNguoi.getText().toString().trim();
                String giaThapNhatStr = edtGiaThapNhat.getText().toString().trim();
                String giaCaoNhatStr = edtGiaCaoNhat.getText().toString().trim();
                if(soNguoiStr.equals("") ||
                        edtGiaThapNhat.getText().toString().trim().equals("") ||
                        edtGiaCaoNhat.getText().toString().trim().equals("")){
                    Utils.onCreateMessageDialog(SearchActivity.this,
                            "Vui lòng nhập đủ thông tin").show();
                    return;
                }

                soNguoi = Integer.parseInt(soNguoiStr);
                giaThapNhat = Integer.parseInt(giaThapNhatStr);
                giaCaoNhat = Integer.parseInt(giaCaoNhatStr);

                Intent intent = new Intent(SearchActivity.this, HangPhongListActivity.class);
                intent.putExtra("soNguoi", soNguoi);
                intent.putExtra("giaMin", giaThapNhat);
                intent.putExtra("giaMax", giaCaoNhat);

                startActivity(intent);
            }
        });

        layoutNgayDen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDate(txtNgayDenDat, "ngayNhanPhong");
            }
        });

        layoutNgayDi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDate(txtNgayDiDat, "ngayTraPhong");
            }
        });
    }

    private void initViews() {
        txtNgayDenDat = findViewById(R.id.txtNgayDenDat);
        txtNgayDiDat = findViewById(R.id.txtNgayDiDat);
        btnTimKiem = findViewById(R.id.btnTimKiem);
        layoutNgayDen = findViewById(R.id.layoutNgayDen);
        layoutNgayDi = findViewById(R.id.layoutNgayDi);
        edtSoNguoi = findViewById(R.id.edtSoNguoi);
        edtGiaThapNhat = findViewById(R.id.edtGiaThapNhat);
        edtGiaCaoNhat = findViewById(R.id.edtGiaCaoNhat);
    }

    public void showData(){
        txtNgayDenDat.setText(Utils.fommatDateShow(Utils.ngayNhanPhong));
        txtNgayDiDat.setText(Utils.fommatDateShow(Utils.ngayTraPhong));
        edtSoNguoi.setText(String.valueOf(soNguoi));
        edtGiaThapNhat.setText(String.valueOf(giaThapNhat));
        edtGiaCaoNhat.setText(String.valueOf(giaCaoNhat));
    }

    private void pickDate(TextView txt, String type){
        Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DAY_OF_MONTH);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(SearchActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month, dayOfMonth);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O ) {
                    if(type.equals("ngayNhanPhong"))
                        Utils.ngayNhanPhong = LocalDate.of(year, month + 1, dayOfMonth);
                    else if(type.equals("ngayTraPhong"))
                        Utils.ngayTraPhong = LocalDate.of(year, month + 1, dayOfMonth);
                }
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("'Th 'e', 'dd' thg 'MM", new Locale("vi"));
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    LocalDate chooseDate = LocalDate.of(year, month + 1, dayOfMonth);
                    txt.setText(Utils.fommatDateShow(chooseDate));
                }

            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }
}