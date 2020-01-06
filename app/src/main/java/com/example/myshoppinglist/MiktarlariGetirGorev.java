package com.example.myshoppinglist;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.widget.CursorAdapter;

public class MiktarlariGetirGorev  extends AsyncTask<Object, Object, Cursor>{

    private AlisverisVeritabani veriTabani;
    private CursorAdapter miktarAdapter;

    public MiktarlariGetirGorev(Context context, CursorAdapter miktarAdapter){
        veriTabani = new AlisverisVeritabani(context);
        this.miktarAdapter = miktarAdapter;
    }

    @Override
    protected Cursor doInBackground(Object... params){
        veriTabani.open();
        return veriTabani.urunmiktarlariniGetir();
    }

    @Override
    protected void onPostExecute(Cursor result){
        miktarAdapter.changeCursor(result);
        veriTabani.close();
    }

}
