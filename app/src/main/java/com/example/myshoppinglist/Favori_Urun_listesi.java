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

public class Favori_Urun_listesi extends ListFragment {
    protected static final String ROW_ID = "row_id";
    private CursorAdapter FavoriUrunAdapter;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View favoriListesiView = inflater.inflate(R.layout.activity_favori_urun_listesi, container, false);
        context = this.getActivity();
        String[] alanListesi = new String[]{"ad", "yil"};
        int[] gosterimListesi = new int[]{R.id.ad, R.id.eklefiyat};
        FavoriUrunAdapter = new SimpleCursorAdapter(this.getActivity(), R.layout.favori_hucre, null, alanListesi, gosterimListesi, 0);
        setListAdapter(FavoriUrunAdapter);
        new FilmleriGetirGorev().execute((Object[]) null);
        return favoriListesiView;
    }

    private class FilmleriGetirGorev extends AsyncTask<Object, Object, Cursor> {
        AlisverisVeritabani veriTabani = new AlisverisVeritabani(context);

        @Override
        protected Cursor doInBackground(Object... params) {
            veriTabani.open();
            return veriTabani.FavoriUrunleriGetir();
        }

        @Override
        protected void onPostExecute(Cursor result) {
            FavoriUrunAdapter.changeCursor(result);
            veriTabani.close();
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent Favori_Urun_Degistir_Sil = new Intent(l.getContext(), Favori_Urun_Degistir_Sil.class);
        Favori_Urun_Degistir_Sil.putExtra(ROW_ID, id);
        startActivity(Favori_Urun_Degistir_Sil);
    }
}
