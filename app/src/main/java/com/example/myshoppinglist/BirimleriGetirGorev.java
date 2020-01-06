package com.example.myshoppinglist;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.widget.CursorAdapter;

public class BirimleriGetirGorev extends AsyncTask<Object, Object, Cursor>{

    private AlisverisVeritabani veriTabani;
    private CursorAdapter birimAdapter;

    public BirimleriGetirGorev(Context context, CursorAdapter turAdapter){
        veriTabani = new AlisverisVeritabani(context);
        this.birimAdapter = turAdapter;
    }

    @Override
    protected Cursor doInBackground(Object... params){
        veriTabani.open();
        return veriTabani.turleriGetir();
    }

    @Override
    protected void onPostExecute(Cursor result){
        birimAdapter.changeCursor(result);
        veriTabani.close();
    }

}
