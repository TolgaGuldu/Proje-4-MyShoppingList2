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
    private CursorAdapter UrunAdapter;
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
        addListenerOnButton()
        alanListesi = new String[]{"ad"};
        gosterimListesi = new int[]{R.id.ekleTurAd};
        turAdapter = new SimpleCursorAdapter(this.getActivity(), R.layout.tur_hucre, null, alanListesi, gosterimListesi, 0);
        tur.setAdapter(turAdapter);
        new TurleriGetirGorev(context, turAdapter).execute((Object[]) null);
        alanListesi = new String[]{"urun", "miktarbirimi"};
        gosterimListesi = new int[]{R.id.eklemiktar, R.id.eklemiktarbirimi};
        miktarAdapter = new SimpleCursorAdapter(this.getActivity(), R.layout.miktar_hucre, null, alanListesi, gosterimListesi, 0);
        miktar.setAdapter(miktarAdapter);
        new MiktarlariGetirGorev(context, miktarAdapter).execute((Object[]) null);

        ekle.setOnClickListener(new OnClickListener() {

            //Run when button is clicked
            @Override
            public void onClick(View v) {

                StringBuffer result = new StringBuffer();
                result.append("IPhone check : ").append(Favori_tick.isChecked());

                Toast.makeText(Favori_Urun_Ekle.this, result.toString(),
                        Toast.LENGTH_LONG).show();

            }
        });
        return listeEkleView;
    }

    AsyncTask<Object, Object, Object> urunEkleGorev = new AsyncTask<Object, Object, Object>() {
        @Override
        protected Object doInBackground(Object... params) {
            AlisverisVeritabani veriTabani = new AlisverisVeritabani(context);
            Cursor turCursor = (Cursor) turAdapter.getItem(tur.getSelectedItemPosition());
            String turAdi = turCursor.getString(turCursor.getColumnIndex("ad"));
            Cursor miktarCursor = (Cursor) miktarAdapter.getItem(miktar.getSelectedItemPosition());
            String miktar = miktarCursor.getString(miktarCursor.getColumnIndex("urun_miktari")) + " " + miktarCursor.getString(miktarCursor.getColumnIndex("urun_miktari_birimi"));
            veriTabani.Urun_Ekle(ad.getText().toString(), Integer.parseInt(Favori.getText().toString()),
                    Integer.parseInt(Fiyat.getText().toString()), turAdi, Integer.parseInt(Miktar.getText().toString()), Miktar_Birimi.getText().toString());
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
