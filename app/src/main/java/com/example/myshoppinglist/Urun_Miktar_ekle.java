package com.example.myshoppinglist;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Urun_Miktar_ekle extends Fragment{
    private EditText Miktar;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View yonetmenEkleView = inflater.inflate(R.layout.activity_urun__miktar_ekle, container, false);
        context = this.getActivity();
        Miktar = (EditText) yonetmenEkleView.findViewById(R.id.ekleYonetmenAd);
        Button ekle = (Button) yonetmenEkleView.findViewById(R.id.yonetmenEkle);
        ekle.setOnClickListener(ekleTikla);
        return yonetmenEkleView;
    }

    AsyncTask<Object, Object, Object> MiktarEkleGorev = new AsyncTask<Object, Object, Object>(){
        @Override
        protected Object doInBackground(Object... params){
            AlisverisVeritabani veriTabani = new AlisverisVeritabani(context);
            veriTabani.Urun_Miktar_ekle(Integer.parseInt(Miktar.getText().toString()));
            return null;
        }

        @Override
        protected void onPostExecute(Object result){
        }
    };

    public OnClickListener ekleTikla = new OnClickListener() {
        public void onClick(View v){
            MiktarEkleGorev.execute((Object[]) null);
        }
    };
}
