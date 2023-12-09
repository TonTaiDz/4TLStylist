package com.example.a4tlstylist.Funtions;

import java.text.NumberFormat;
import java.util.Locale;

public class MoneyFormat {
    public static String moneyFormat(double price){
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(localeVN);
        return currencyFormat.format(price);
    }
}
