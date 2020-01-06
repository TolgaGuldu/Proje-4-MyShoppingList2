package com.example.myshoppinglist;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class Alisveris_Listesi_ekle extends Fragment {
    private EditText ad;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] alanListesi;
        int[] gosterimListesi;
        View ListeEkleView = inflater.inflate(R.layout.activity_alisveris__listesi_ekle, container, false);
        context = this.getActivity();
        ad = (EditText) ListeEkleView.findViewById(R.id.ekleAd);
        Button ekle = (Button) ListeEkleView.findViewById(R.id.ekle);
        ekle.setOnClickListener(ekleTikla);
        alanListesi = new String[] {"ad"};
        return ListeEkleView;
    }

    AsyncTask<Object, Object, Object> ListeEkleGorev = new AsyncTask<Object, Object, Object>(){
        @Override
        protected Object doInBackground(Object... params){
            AlisverisVeritabani veriTabani = new AlisverisVeritabani(context);
            veriTabani.Alisveris_Listesi_ekle(ad.getText().toString());
            return null;
        }

        @Override
        protected void onPostExecute(Object result){
        }
    };

    public OnClickListener ekleTikla = new OnClickListener() {
        public void onClick(View v){
            ListeEkleGorev.execute((Object[]) null);
        }
    };
}
