package com.example.myshoppinglist;

import android.content.Context;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Urun_Miktar_ekle extends AppCompatActivity {
    private EditText Miktar_adedi;
    private EditText Miktar_birimi;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun__miktar_ekle);
    }
}
