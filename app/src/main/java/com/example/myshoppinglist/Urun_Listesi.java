package com.example.myshoppinglist;

import android.content.Context;
import android.widget.CursorAdapter;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Urun_Listesi extends AppCompatActivity {
    private CursorAdapter UrunAdapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun__listesi);
    }
}
