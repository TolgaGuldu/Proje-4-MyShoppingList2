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

public class Alisveris_Listesi extends ListFragment {
    protected static final String ROW_ID = "row_id";
    private CursorAdapter AlisverisListesiAdapter;
    private Context context;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View aliverisListesiView = inflater.inflate(R.layout.alisveris_listesi, container, false);
        context = this.getActivity();
        String[] alanListesi = new String[] {"ad"};
        int[] gosterimListesi = new int[] {R.id.ad};
        AlisverisListesiAdapter = new SimpleCursorAdapter(this.getActivity(), R.layout.liste_hucre, null, alanListesi, gosterimListesi, 0);
        setListAdapter(AlisverisListesiAdapter);
        new ListeleriGetirGorev().execute((Object[]) null);
        return aliverisListesiView;
    }


    private class ListeleriGetirGorev extends AsyncTask<Object, Object, Cursor>{
        AlisverisVeritabani veriTabani = new AlisverisVeritabani(context);

        @Override
        protected Cursor doInBackground(Object... params){
            veriTabani.open();
            return veriTabani.ListeleriGetir();
        }

        @Override
        protected void onPostExecute(Cursor result){
            AlisverisListesiAdapter.changeCursor(result);
            veriTabani.close();
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent Alisveris_Listesi_degistir_sil = new Intent(l.getContext(), Alisveris_Listesi_degistir_sil.class);
        Alisveris_Listesi_degistir_sil.putExtra(ROW_ID, id);
        startActivity(Alisveris_Listesi_degistir_sil);
    }
}
