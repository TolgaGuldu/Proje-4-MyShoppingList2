package com.example.myshoppinglist;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Favori_Urun_Degistir_Sil extends AppCompatActivity {
    private EditText urun_adi;
    private Spinner urun_miktari;
    private Spinner urun_miktari_birimi;
    private Spinner urun_turu;
    private EditText urun_fiyati;
    private CheckBox Favori_tick;
    private CursorAdapter UrunAdapter;
    private CursorAdapter TurAdapter;
    private CursorAdapter MiktarAdapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favori__urun__degistir__sil);
    }
}
