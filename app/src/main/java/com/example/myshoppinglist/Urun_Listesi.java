package com.example.myshoppinglist;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Urun_Listesi extends ListFragment {
    protected static final String ROW_ID = "row_id";
    private CursorAdapter UrunAdapter;
    private Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View favoriListesiView = inflater.inflate(R.layout.activity_urun__listesi, container, false);
        context = this.getActivity();
        String[] alanListesi = new String[]{"ad"};
        int[] gosterimListesi = new int[]{R.id.ad, R.id.eklefiyat};
        UrunAdapter = new SimpleCursorAdapter(this.getActivity(), R.layout.urun_hucre, null, alanListesi, gosterimListesi, 0);
        setListAdapter(UrunAdapter);
        new UrunleriGetirGorev().execute((Object[]) null);
        return favoriListesiView;
    }

    private class UrunleriGetirGorev extends AsyncTask<Object, Object, Cursor> {
        AlisverisVeritabani veriTabani = new AlisverisVeritabani(context);


        protected Cursor doInBackground(Long... params) {
            veriTabani.open();
            return veriTabani.UrunleriGetir(params[0]);
        }

        @Override
        protected Cursor doInBackground(Object... objects) {
            return null;
        }

        @Override
        protected void onPostExecute(Cursor result) {
            UrunAdapter.changeCursor(result);
            veriTabani.close();
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent Urun_degistir_sil = new Intent(l.getContext(), Urun_degistir_sil.class);
        Urun_degistir_sil.putExtra(ROW_ID, id);
        startActivity(Urun_degistir_sil);
    }
}
