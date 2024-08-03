package com.minihotel.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.minihotel.models.CartItem;
import com.minihotel.models.KhachHang;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Utils {
    public static KhachHang khachHang = new KhachHang(3,
            "", "Phạm Văn Thành", "0987654321",
            "", "", "thanhdever@gmail.com");
    public static LocalDate ngayNhanPhong = getCurrentDate();
    public static LocalDate ngayTraPhong = getPlusDayCurrentDate();
    public static LocalDate getCurrentDate(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return LocalDate.now();
        }
        return null;
    }

    public static LocalDate getPlusDayCurrentDate(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return LocalDate.now().plusDays(1);
        }
        return null;
    }
    // Format Date để hiển thị lên màn hình
    public static String fommatDateShow(LocalDate currentDate){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            //EEEE, dd 'thg' MM yyyy
             return currentDate.format(DateTimeFormatter.ofPattern("dd 'thg' MM, yyyy", new Locale("vi")));
        }
        return null;
    }
    //Format Date để gửi request lên server
    public static String fommatDateRequest(LocalDate currentDate){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            return currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        return null;
    }

    public static Long calculateBetweenDate(LocalDate date1, LocalDate date2){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            return ChronoUnit.DAYS.between(date1, date2);
        }
        return null;
    }

    public static Dialog onCreateMessageDialog(Activity activity, String message) {
        // Use the Builder class for convenient dialog construction.
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(message)
//                    .setPositiveButton(R.string.start, new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            // START THE GAME!
//                        }
//                    })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancels the dialog.
                        dialog.dismiss();
                    }
                });
        // Create the AlertDialog object and return it.
        return builder.create();
    }

    public static List<CartItem> cartItemsGlobal = new ArrayList<>();

    public static void addItemToCart(CartItem item){
        for (CartItem i: cartItemsGlobal) {
            if(i.getIdHangPhong() == item.getIdHangPhong()){
                i.setSoLuong(i.getSoLuong() + 1);
                return;
            }
        }

        cartItemsGlobal.add(item);
    }

    public static int getTongTienCart(){
        long soNgayThue = calculateBetweenDate(Utils.ngayNhanPhong, Utils.ngayTraPhong);
        int tongTien = 0;
        for (CartItem i: cartItemsGlobal) {
            tongTien += (long) i.getGiaPhong() * i.getSoLuong() * soNgayThue;
        }
        return tongTien;
    }

    public static int getSoLuongPhongCart(){
        int soLuong = 0;
        for (CartItem i: cartItemsGlobal) {
            soLuong += i.getSoLuong();
        }
        return soLuong;
    }

    public static void removeItemInCart(int idHangPhong){
        for (CartItem i: cartItemsGlobal) {
            if(i.getIdHangPhong() == idHangPhong){
                cartItemsGlobal.remove(i);
                break;
            }
        }
    }
    public static void tangSoLuongCartItem(int idHangPhong){
        for (CartItem i: cartItemsGlobal) {
            if(i.getIdHangPhong() == idHangPhong){
                i.setSoLuong(i.getSoLuong() + 1);
                break;
            }
        }
    }

    public static void giamSoLuongCartItem(int idHangPhong){
        for (CartItem i: cartItemsGlobal) {
            if(i.getIdHangPhong() == idHangPhong){
                i.setSoLuong(i.getSoLuong() - 1);
                break;
            }
        }
    }
}
