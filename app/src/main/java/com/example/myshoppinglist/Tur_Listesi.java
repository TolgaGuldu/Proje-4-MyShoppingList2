package com.example.myshoppinglist;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;


public class Tur_Listesi extends ListFragment {
    private CursorAdapter TurAdapter;
    private Context context;

    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View turListesiView = inflater.inflate(R.layout.activity_tur__listesi, container, false);
            context = this.getActivity();
            String[] alanListesi = new String[] {"ad"};
            int[] gosterimListesi = new int[] {R.id.turAdi};
            TurAdapter = new SimpleCursorAdapter(this.getActivity(), R.layout.tur_hucre, null, alanListesi, gosterimListesi, 0);
            setListAdapter(TurAdapter);
            new TurleriGetirGorev(context, TurAdapter).execute((Object[]) null);
            return turListesiView;
        }

    }

