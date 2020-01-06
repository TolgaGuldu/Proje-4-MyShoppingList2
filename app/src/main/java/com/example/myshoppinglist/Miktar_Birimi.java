package com.example.myshoppinglist;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

public class Miktar_Birimi extends ListFragment {
        private CursorAdapter BirimAdapter;
        private Context context;

        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View turListesiView = inflater.inflate(R.layout.miktarbirimi_listesi, container, false);
            context = this.getActivity();
            String[] alanListesi = new String[] {"ad"};
            int[] gosterimListesi = new int[] {R.id.turAdi};
            BirimAdapter = new SimpleCursorAdapter(this.getActivity(), R.layout.birim_hucre, null, alanListesi, gosterimListesi, 0);
            setListAdapter(BirimAdapter);
            new TurleriGetirGorev(context, BirimAdapter).execute((Object[]) null);
            return turListesiView;
        }

}
