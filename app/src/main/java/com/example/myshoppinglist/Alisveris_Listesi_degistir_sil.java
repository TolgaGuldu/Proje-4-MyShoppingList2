package com.example.myshoppinglist;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class Alisveris_Listesi_degistir_sil extends Activity {
    private long rowID;
    private EditText ad;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] alanListesi;
        int[] gosterimListesi;
        super.onCreate(savedInstanceState);
        context = this.getApplicationContext();
        setContentView(R.layout.activity_alisveris__listesi_degistir_sil);
        Button degisiklikleriKaydet = (Button) findViewById(R.id.degisiklikleriKaydet);
        Button sil = (Button) findViewById(R.id.sil);
        Button geriDon = (Button) findViewById(R.id.geriDon);
        ad = (EditText) findViewById(R.id.ad);
        alanListesi = new String[] {"ad"};
        degisiklikleriKaydet.setOnClickListener(degisiklikleriKaydetTikla);
        sil.setOnClickListener(silTikla);
        geriDon.setOnClickListener(geriDonTikla);
        Bundle extras = getIntent().getExtras();
        rowID = extras.getLong("row_id");
        new ListeleriGetirGorev().execute(rowID);
    }

    private class ListeleriGetirGorev extends AsyncTask<Long, Object, Cursor>{

        AlisverisVeritabani veriTabani = new AlisverisVeritabani(context);

        @Override
        protected Cursor doInBackground(Long... params){
            veriTabani.open();
            return veriTabani.ListeleriGetir(params[0]);
        }

        @Override
        protected void onPostExecute(Cursor result){
            super.onPostExecute(result);
            result.moveToFirst();
            int adPos = result.getColumnIndex("ad");
            int surePos = result.getColumnIndex("sure");
            int yilPos = result.getColumnIndex("yil");
            int turPos = result.getColumnIndex("tur");
            int yonetmenPos = result.getColumnIndex("yonetmen");
            ad.setText(result.getString(adPos));
            veriTabani.close();
        }
    }

    AsyncTask<Object, Object, Object> ListeDegistirGorev = new AsyncTask<Object, Object, Object>(){
        @Override
        protected Object doInBackground(Object... params){
            AlisverisVeritabani veriTabani = new AlisverisVeritabani(context);;
            veriTabani.Alisveris_Listesi_degistir(rowID, ad.getText().toString());
            return null;
        }

        @Override
        protected void onPostExecute(Object result){
            finish();
        }
    };

    AsyncTask<Long, Object, Object> ListeSilGorev = new AsyncTask<Long, Object, Object>(){
        @Override
        protected Object doInBackground(Long... params){
            AlisverisVeritabani veriTabani = new AlisverisVeritabani(context);
            veriTabani.Alisveris_Listesi_Sil(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Object result){
            finish();
        }
    };

    public OnClickListener degisiklikleriKaydetTikla = new OnClickListener() {
        public void onClick(View v){
            ListeDegistirGorev.execute((Object[]) null);
        }
    };

    public OnClickListener silTikla = new OnClickListener() {
        public void onClick(View v){
            ListeSilGorev.execute(new Long[] { rowID });
        }
    };

    public OnClickListener geriDonTikla = new OnClickListener() {
        public void onClick(View v){
            finish();
        }
    };



























}
