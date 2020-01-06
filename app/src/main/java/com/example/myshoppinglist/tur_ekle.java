package com.example.myshoppinglist;

import android.content.Context;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class tur_ekle extends AppCompatActivity {
    private EditText tur_adi;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tur_ekle);
    }
}
