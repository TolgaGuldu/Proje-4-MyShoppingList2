package com.example.myshoppinglist;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.os.Bundle;
import android.app.Activity;

public class AnaEkran extends Activity implements ActionBar.TabListener {
    private Fragment Alisveris_Listesi;
    private Fragment Alisveris_Listesi_ekle;
    private Fragment Favori_Urun_listesi;
    private Fragment Favori_Urun_Ekle;
    private Fragment Urun_Listesi;
    private Fragment Urun_Ekle;
    private Fragment Urun_Miktar_ekle;
    private Fragment Urun_Miktar_Listesi;
    private Fragment tur_ekle;
    private Fragment Tur_Listesi;
    private Fragment Miktar_Birimi;
    private Fragment Miktar_Birimi_Ekle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ana_ekran);
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.addTab(actionBar.newTab().setText(R.string.Alisveris_Listesi)
        .setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText(R.string.Alisveris_Listesi_ekle)
        .setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText(R.string.Favori_Urun_listesi)
        .setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText(R.string.Favori_Urun_Ekle)
        .setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText(R.string.Urun_Listesi)
        .setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText(R.string.Urun_Ekle)
        .setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText(R.string.Urun_Miktar_ekle)
        .setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText(R.string.Urun_Miktar_Listesi)
        .setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText(R.string.tur_ekle)
        .setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText(R.string.Tur_Listesi)
        .setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText(R.string.Miktar_Birimi)
                .setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText(R.string.Miktar_Birimi_Ekle)
                .setTabListener(this));
    }

    public void onTabSelected(ActionBar.Tab tab,
                              FragmentTransaction fragmentTransaction) {
        switch (tab.getPosition()){
            case 0:
                Alisveris_Listesi = Fragment.instantiate(this, Alisveris_Listesi.class.getName());
                fragmentTransaction.add(android.R.id.content, Alisveris_Listesi, "Alisveris_Listesi");
                break;
            case 1:
                Alisveris_Listesi_ekle = Fragment.instantiate(this, Alisveris_Listesi_ekle.class.getName());
                fragmentTransaction.add(android.R.id.content, Alisveris_Listesi_ekle, "Alisveris_Listesi_ekle");
                break;
            case 2:
                Favori_Urun_listesi = Fragment.instantiate(this, Favori_Urun_listesi.class.getName());
                fragmentTransaction.add(android.R.id.content, Favori_Urun_listesi, "Favori_Urun_listesi");
                break;
            case 3:
                Favori_Urun_Ekle = Fragment.instantiate(this, Favori_Urun_Ekle.class.getName());
                fragmentTransaction.add(android.R.id.content, Favori_Urun_Ekle, "Favori_Urun_Ekle");
                break;
            case 4:
                Urun_Listesi = Fragment.instantiate(this, Urun_Listesi.class.getName());
                fragmentTransaction.add(android.R.id.content, Urun_Listesi, "Urun_Listesi");
                break;
            case 5:
                Urun_Ekle = Fragment.instantiate(this, Urun_Ekle.class.getName());
                fragmentTransaction.add(android.R.id.content, Urun_Ekle, "Urun_Ekle");
                break;
            case 6:
                Urun_Miktar_ekle = Fragment.instantiate(this, Urun_Miktar_ekle.class.getName());
                fragmentTransaction.add(android.R.id.content, Urun_Miktar_ekle, "Urun_Miktar_ekle");
                break;
            case 7:
                Urun_Miktar_Listesi = Fragment.instantiate(this, Urun_Miktar_Listesi.class.getName());
                fragmentTransaction.add(android.R.id.content, Urun_Miktar_Listesi, "Urun_Miktar_Listesi");
                break;
            case 8:
                tur_ekle = Fragment.instantiate(this, tur_ekle.class.getName());
                fragmentTransaction.add(android.R.id.content, tur_ekle, "tur_ekle");
                break;
            case 10:
                Miktar_Birimi = Fragment.instantiate(this, Miktar_Birimi.class.getName());
                fragmentTransaction.add(android.R.id.content, Miktar_Birimi, "Miktar_Birimi");
                break;
            case 11:
                Miktar_Birimi_Ekle = Fragment.instantiate(this, Miktar_Birimi_Ekle.class.getName());
                fragmentTransaction.add(android.R.id.content, Miktar_Birimi_Ekle, "Miktar_Birimi_Ekle");
                break;
        }
    }

    public void onTabUnselected(ActionBar.Tab tab,
                                FragmentTransaction fragmentTransaction) {
        switch (tab.getPosition()){
            case 0:
                fragmentTransaction.detach(Alisveris_Listesi);
                break;
            case 1:
                fragmentTransaction.detach(Alisveris_Listesi_ekle);
                break;
            case 2:
                fragmentTransaction.detach(Favori_Urun_listesi);
                break;
            case 3:
                fragmentTransaction.detach(Favori_Urun_Ekle);
                break;
            case 4:
                fragmentTransaction.detach(Urun_Listesi);
                break;
            case 5:
                fragmentTransaction.detach(Urun_Ekle);
                break;
            case 6:
                fragmentTransaction.detach(Urun_Miktar_ekle);
                break;
            case 7:
                fragmentTransaction.detach(Urun_Miktar_Listesi);
                break;
            case 8:
                fragmentTransaction.detach(tur_ekle);
                break;
            case 9:
                fragmentTransaction.detach(Tur_Listesi);
                break;
            case 10:
                fragmentTransaction.detach(Miktar_Birimi);
                break;
            case 11:
                fragmentTransaction.detach(Miktar_Birimi_Ekle);
                break;
        }
    }

    public void onTabReselected(ActionBar.Tab tab,
                                FragmentTransaction fragmentTransaction) {
    }


}
