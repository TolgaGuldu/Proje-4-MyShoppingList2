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

public class Miktar_Birimi_Ekle extends Fragment{
    private EditText Birim;
    private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View turEkleView = inflater.inflate(R.layout.activity_miktar__birimi__ekle, container, false);
        context = this.getActivity();
        Birim = (EditText) turEkleView.findViewById(R.id.ekleTurAd);
        Button ekle = (Button) turEkleView.findViewById(R.id.turEkle);
        ekle.setOnClickListener(ekleTikla);
        return turEkleView;
    }

    AsyncTask<Object, Object, Object> BirimEkleGorev = new AsyncTask<Object, Object, Object>(){
        @Override
        protected Object doInBackground(Object... params){
            AlisverisVeritabani veriTabani = new AlisverisVeritabani(context);
            veriTabani.Miktar_Birimi_ekle(Birim.getText().toString());
            return null;
        }

        @Override
        protected void onPostExecute(Object result){
        }
    };

    public OnClickListener ekleTikla = new OnClickListener() {
        public void onClick(View v){
            BirimEkleGorev.execute((Object[]) null);
        }
    };

}
