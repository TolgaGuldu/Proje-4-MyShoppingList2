package com.example.myshoppinglist;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

public class Urun_Miktar_Listesi extends ListFragment{
    private CursorAdapter MiktarAdapter;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View yonetmenListesiView = inflater.inflate(R.layout.activity_urun__miktar__listesi, container, false);
        context = this.getActivity();
        String[] alanListesi = new String[] {"ad", "soyad"};
        int[] gosterimListesi = new int[] {R.id.eklemiktar};
        MiktarAdapter = new SimpleCursorAdapter(this.getActivity(), R.layout.miktar_hucre, null, alanListesi, gosterimListesi, 0);
        setListAdapter(MiktarAdapter);
        new MiktarlariGetirGorev(context, MiktarAdapter).execute((Object[]) null);
        return yonetmenListesiView;
    }
}
