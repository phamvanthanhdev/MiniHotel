package com.minihotel.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Common {
    public static final String convertCurrencyVietnamese(Integer currency){
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(currency);
    }

    //Chuyển hình ảnh về dạng Bitmap
    public static final Bitmap decodeBase64ToBitmap(String base64Str) throws IllegalArgumentException {
        byte[] decodedBytes = Base64.decode(base64Str, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    public static Long calculateBetweenDate(LocalDate date1, LocalDate date2){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            return ChronoUnit.DAYS.between(date1, date2);
        }
        return null;
    }
}
