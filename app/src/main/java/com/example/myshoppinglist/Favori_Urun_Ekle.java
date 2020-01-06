package com.example.myshoppinglist;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.*;

public class Favori_Urun_Ekle extends Fragment {
    private EditText urun_adi;
    private Spinner urun_miktari;
    private Spinner urun_miktari_birimi;
    private Spinner urun_turu;
    private EditText urun_fiyati;
    private CheckBox Favori_tick;
    private CursorAdapter BirimAdapter;
    private CursorAdapter TurAdapter;
    private CursorAdapter MiktarAdapter;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] alanListesi;
        int[] gosterimListesi;
        View listeEkleView = inflater.inflate(R.layout.activity_favori__urun__ekle, container, false);
        context = this.getActivity();
        urun_adi = (EditText) listeEkleView.findViewById(R.id.ekleAd);
        urun_miktari = (Spinner) listeEkleView.findViewById(R.id.eklemiktar);
        urun_miktari_birimi = (Spinner) listeEkleView.findViewById(R.id.eklemiktarbirimi);
        urun_turu = (Spinner) listeEkleView.findViewById(R.id.ekleTur);
        urun_fiyati = (EditText) listeEkleView.findViewById(R.id.eklefiyat);
        Button ekle = (Button) listeEkleView.findViewById(R.id.ekle);
        Favori_tick = (CheckBox) listeEkleView.findViewById(R.id.Favori);
        addCheckbox();
        alanListesi = new String[]{"ad"};
        gosterimListesi = new int[]{R.id.ekleTurAd};
        TurAdapter = new SimpleCursorAdapter(this.getActivity(), R.layout.tur_hucre, null, alanListesi, gosterimListesi, 0);
        urun_turu.setAdapter(TurAdapter);
        new TurleriGetirGorev(context, TurAdapter).execute((Object[]) null);
        alanListesi = new String[]{"urun", "miktarbirimi"};
        gosterimListesi = new int[]{R.id.eklemiktar, R.id.eklemiktarbirimi};
        MiktarAdapter = new SimpleCursorAdapter(this.getActivity(), R.layout.miktar_hucre, null, alanListesi, gosterimListesi, 0);
        urun_miktari.setAdapter(MiktarAdapter);
        new MiktarlariGetirGorev(context, MiktarAdapter).execute((Object[]) null);

        ekle.setOnClickListener(new OnClickListener() {

            //Run when button is clicked
            @Override
            public void onClick(View v) {

                StringBuffer result = new StringBuffer();
                result.append("IPhone check : ").append(Favori_tick.isChecked());

                Toast.makeText(Favori_Urun_Ekle.this, result.toString(),Toast.LENGTH_LONG).show();
            }
        });
        return listeEkleView;
    }

    AsyncTask<Object, Object, Object> urunEkleGorev = new AsyncTask<Object, Object, Object>() {
        @Override
        protected Object doInBackground(Object... params) {
            AlisverisVeritabani veriTabani = new AlisverisVeritabani(context);
            Cursor turCursor = (Cursor) TurAdapter.getItem(urun_turu.getSelectedItemPosition());
            String turAdi = turCursor.getString(turCursor.getColumnIndex("ad"));
            Cursor miktarCursor = (Cursor) MiktarAdapter.getItem(urun_miktari.getSelectedItemPosition());
            String miktar = miktarCursor.getString(miktarCursor.getColumnIndex("urun_miktari"));
            Cursor birimCursor = (Cursor) BirimAdapter.getItem(urun_turu.getSelectedItemPosition());
            String birim = turCursor.getString(turCursor.getColumnIndex("birim"));
            veriTabani.Urun_Ekle(urun_adi.getText().toString(), Integer.parseInt(Favori_tick.getText().toString()),
                    Integer.parseInt(urun_fiyati.getText().toString()), turAdi, miktar,birim);
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
        }
    };


    public void addCheckbox() {

        Favori_tick.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(Favori_Urun_Ekle.this, "Bro, try Android :)", Toast.LENGTH_LONG).show();
                }
            }
        });
    }



}
