package com.example.myshoppinglist;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

public class Favori_Urun_Degistir_Sil extends Activity {
    private long rowID;
    private EditText urun_adi;
    private Spinner urun_miktari;
    private Spinner urun_miktari_birimi;
    private Spinner urun_turu;
    private EditText urun_fiyati;
    private CheckBox Favori_tick;
    private CursorAdapter TurAdapter;
    private CursorAdapter MiktarAdapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] alanListesi;
        int[] gosterimListesi;
        super.onCreate(savedInstanceState);
        context = this.getApplicationContext();
        setContentView(R.layout.activity_favori__urun__degistir__sil);
        Button degisiklikleriKaydet = (Button) findViewById(R.id.degisiklikleriKaydet);
        Button sil = (Button) findViewById(R.id.sil);
        Button geriDon = (Button) findViewById(R.id.geriDon);
        urun_adi = (EditText) findViewById(R.id.ekleAd);
        urun_miktari = (Spinner) findViewById(R.id.eklemiktar);
        urun_miktari_birimi = (Spinner) findViewById(R.id.eklemiktarbirimi);
        urun_turu = (Spinner) findViewById(R.id.ekleTur);
        urun_fiyati = (EditText)findViewById(R.id.eklefiyat);
        Favori_tick = (CheckBox) findViewById(R.id.Favori);
        alanListesi = new String[]{"ad"};
        gosterimListesi = new int[]{R.id.turAdi};
        TurAdapter = new SimpleCursorAdapter(this, R.layout.tur_hucre, null, alanListesi, gosterimListesi, 0);
        urun_turu.setAdapter(TurAdapter);
        new TurleriGetirGorev(context, TurAdapter).execute((Object[]) null);
        alanListesi = new String[]{"ad"};
        gosterimListesi = new int[]{R.id.urun_miktari};
        MiktarAdapter = new SimpleCursorAdapter(this, R.layout.miktar_hucre, null, alanListesi, gosterimListesi, 0);
        urun_miktari.setAdapter(MiktarAdapter);
        new MiktarlariGetirGorev(context, MiktarAdapter).execute((Object[]) null);
        degisiklikleriKaydet.setOnClickListener(degisiklikleriKaydetTikla);
        sil.setOnClickListener(silTikla);
        geriDon.setOnClickListener(geriDonTikla);
        Bundle extras = getIntent().getExtras();
        rowID = extras.getLong("row_id");
        new FavorileriGetir().execute(rowID);
    }

    private class FavorileriGetirGorev extends AsyncTask<Long, Object, Cursor> {

        AlisverisVeritabani veriTabani = new AlisverisVeritabani(context);

        @Override
        protected Cursor doInBackground(Long... params) {
            veriTabani.open();
            return veriTabani.FavoriUrunleriGetir(params[0]);
        }

        @Override
        protected void onPostExecute(Cursor result) {
            super.onPostExecute(result);
            result.moveToFirst();
            int adPos = result.getColumnIndex("ad");
            int surePos = result.getColumnIndex("sure");
            int yilPos = result.getColumnIndex("yil");
            int turPos = result.getColumnIndex("tur");
            int yonetmenPos = result.getColumnIndex("yonetmen");
            ad.setText(result.getString(adPos));
            sure.setText("" + result.getInt(surePos));
            yil.setText("" + result.getInt(yilPos));
            for (int i = 0; i < turAdapter.getCount(); i++) {
                Cursor cursor = (Cursor) turAdapter.getItem(i);
                String turAdi = cursor.getString(cursor.getColumnIndex("ad"));
                String sonucTurAdi = result.getString(turPos);
                if (turAdi.equals(sonucTurAdi)) {
                    tur.setSelection(i);
                    break;
                }
            }
            for (int i = 0; i < yonetmenAdapter.getCount(); i++) {
                Cursor cursor = (Cursor) yonetmenAdapter.getItem(i);
                String yonetmenAdi = cursor.getString(cursor.getColumnIndex("ad")) + " " + cursor.getString(cursor.getColumnIndex("soyad"));
                String sonucYonetmenAdi = result.getString(yonetmenPos);
                if (yonetmenAdi.equals(sonucYonetmenAdi)) {
                    yonetmen.setSelection(i);
                    break;
                }
            }
            result.close();
            veriTabani.close();
        }
    }

    AsyncTask<Object, Object, Object> favoriDegistirGorev = new AsyncTask<Object, Object, Object>() {
        @Override
        protected Object doInBackground(Object... params) {
            AlisverisVeritabani veriTabani = new AlisverisVeritabani(context);
            Cursor turCursor = (Cursor) turAdapter.getItem(tur.getSelectedItemPosition());
            String turAdi = turCursor.getString(turCursor.getColumnIndex("ad"));
            Cursor yonetmenCursor = (Cursor) yonetmenAdapter.getItem(yonetmen.getSelectedItemPosition());
            String yonetmenAdi = yonetmenCursor.getString(yonetmenCursor.getColumnIndex("ad")) + " " + yonetmenCursor.getString(yonetmenCursor.getColumnIndex("soyad"));
            veriTabani.filmDegistir(rowID, ad.getText().toString(), Integer.parseInt(sure.getText().toString()),
                    Integer.parseInt(yil.getText().toString()), turAdi, yonetmenAdi);
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            finish();
        }
    };

    AsyncTask<Long, Object, Object> favoriSilGorev = new AsyncTask<Long, Object, Object>() {
        @Override
        protected Object doInBackground(Long... params) {
            AlisverisVeritabani veriTabani = new AlisverisVeritabani(context);
            veriTabani.Favori_Sil(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            finish();
        }
    };

    public OnClickListener degisiklikleriKaydetTikla = new OnClickListener() {
        public void onClick(View v) {
            favoriDegistirGorev.execute((Object[]) null);
        }
    };

    public OnClickListener silTikla = new OnClickListener() {
        public void onClick(View v) {
            filmSilGorev.execute(new Long[]{rowID});
        }
    };

    public OnClickListener geriDonTikla = new OnClickListener() {
        public void onClick(View v) {
            finish();
        }
    };

}