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

public class Urun_degistir_sil extends Activity {
    private long rowID;
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
    protected void onCreate(Bundle savedInstanceState) {
        String[] alanListesi;
        int[] gosterimListesi;
        super.onCreate(savedInstanceState);
        context = this.getApplicationContext();
        setContentView(R.layout.activity_urun_degistir_sil);
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
        new UrunleriGetirGorev().execute(rowID);
    }

    private class UrunleriGetirGorev extends AsyncTask<Long, Object, Cursor> {

        AlisverisVeritabani veriTabani = new AlisverisVeritabani(context);

        @Override
        protected Cursor doInBackground(Long... params) {
            veriTabani.open();
            return veriTabani.UrunleriGetir(params[0]);
        }

        @Override
        protected void onPostExecute(Cursor result) {
            super.onPostExecute(result);
            result.moveToFirst();
            int urun_adiPos = result.getColumnIndex("urun_adi");
            int urun_miktariPos = result.getColumnIndex("urun_miktari");
            int urun_miktari_birimiPos = result.getColumnIndex("urun_miktari_birimi");
            int urun_turuPos = result.getColumnIndex("urun_turu");
            int urun_fiyatiPos = result.getColumnIndex("urun_fiyati");
            urun_adi.setText(result.getString(urun_adiPos));
            urun_fiyati.setText("" + result.getInt(urun_fiyatiPos));
            for (int i = 0; i < TurAdapter.getCount(); i++) {
                Cursor cursor = (Cursor) TurAdapter.getItem(i);
                String turAdi = cursor.getString(cursor.getColumnIndex("ad"));
                String sonucTurAdi = result.getString(urun_turuPos);
                if (turAdi.equals(sonucTurAdi)) {
                    urun_turu.setSelection(i);
                    break;
                }
            }
            for (int i = 0; i < MiktarAdapter.getCount(); i++) {
                Cursor cursor = (Cursor) MiktarAdapter.getItem(i);
                String miktarAdi = cursor.getString(cursor.getColumnIndex("miktar"));
                String sonucmiktarAdi= result.getString(urun_miktariPos);
                if (miktarAdi.equals(sonucmiktarAdi)) {
                    urun_miktari.setSelection(i);
                    break;
                }
            }
            for (int i = 0; i < BirimAdapter.getCount(); i++) {
                Cursor cursor = (Cursor) BirimAdapter.getItem(i);
                String BirimnAdi = cursor.getString(cursor.getColumnIndex("birim"));
                String sonucBirimnAdi = result.getString(urun_miktari_birimiPos);
                if (BirimnAdi.equals(sonucBirimnAdi)) {
                    urun_miktari_birimi.setSelection(i);
                    break;
                }
            }
            result.close();
            veriTabani.close();
        }
    }

    AsyncTask<Object, Object, Object> urunDegistirGorev = new AsyncTask<Object, Object, Object>() {
        @Override
        protected Object doInBackground(Object... params) {
            AlisverisVeritabani veriTabani = new AlisverisVeritabani(context);
            Cursor turCursor = (Cursor) TurAdapter.getItem(urun_turu.getSelectedItemPosition());
            String turAdi = turCursor.getString(turCursor.getColumnIndex("ad"));
            Cursor miktarCursor = (Cursor) MiktarAdapter.getItem(urun_miktari.getSelectedItemPosition());
            int miktar = miktarCursor.getInt(miktarCursor.getColumnIndex("urun_miktari"));
            Cursor birimCursor = (Cursor) BirimAdapter.getItem(urun_miktari_birimi.getSelectedItemPosition());
            String birim = turCursor.getString(birimCursor.getColumnIndex("birim"));
            veriTabani.Urun_degistir(rowID,urun_adi.getText().toString(), Favori_tick.isChecked(),
                    Integer.parseInt(urun_fiyati.getText().toString()), turAdi, miktar,birim);
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            finish();
        }
    };

    AsyncTask<Long, Object, Object> urunSilGorev = new AsyncTask<Long, Object, Object>() {
        @Override
        protected Object doInBackground(Long... params) {
            AlisverisVeritabani veriTabani = new AlisverisVeritabani(context);
            veriTabani.Urun_Sil(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            finish();
        }
    };

    public OnClickListener degisiklikleriKaydetTikla = new OnClickListener() {
        public void onClick(View v) {
            urunDegistirGorev.execute((Object[]) null);
        }
    };

    public OnClickListener silTikla = new OnClickListener() {
        public void onClick(View v) {
            urunSilGorev.execute(new Long[]{rowID});
        }
    };

    public OnClickListener geriDonTikla = new OnClickListener() {
        public void onClick(View v) {
            finish();
        }
    };

}