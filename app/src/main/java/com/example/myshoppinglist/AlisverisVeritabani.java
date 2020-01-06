package com.example.myshoppinglist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class AlisverisVeritabani {
    private SQLiteDatabase veriTabani;
    private DatabaseOpenHelper veritabaniYardimci;

    public AlisverisVeritabani(Context context) {
        veritabaniYardimci = new DatabaseOpenHelper(context, "Alisveris", null, 1);
    }

    public void open() throws SQLException {
        veriTabani = veritabaniYardimci.getWritableDatabase();
    }

    public void close() {
        if (veriTabani != null)
            veriTabani.close();
    }


    public void Alisveris_Listesi_ekle(String ad) {
        ContentValues yeniListe = new ContentValues();
        yeniListe.put("ad", ad);
        open();
        veriTabani.insert("Liste", null, yeniListe);
        close();
    }

    public void Favori_Urun_Ekle(String ad, Boolean Favori, double Fiyat, String Türü, int Miktar, String Miktar_Birimi) {
        ContentValues yeniFavoriUrun = new ContentValues();
        yeniFavoriUrun.put("ad", ad);
        yeniFavoriUrun.put("Favori", Favori);
        yeniFavoriUrun.put("Fiyat", Fiyat);
        yeniFavoriUrun.put("Türü", Türü);
        yeniFavoriUrun.put("Miktar", Miktar);
        yeniFavoriUrun.put("Miktar_Birimi", Miktar_Birimi);
        open();
        veriTabani.insert("Favori", null, yeniFavoriUrun);
        close();
    }


    public void Urun_Ekle(String ad, Boolean Favori, double Fiyat, String Türü, int Miktar, String Miktar_Birimi) {
        ContentValues yeniUrun = new ContentValues();
        yeniUrun.put("ad", ad);
        yeniUrun.put("Favori", Favori);
        yeniUrun.put("Fiyat", Fiyat);
        yeniUrun.put("Türü", Türü);
        yeniUrun.put("Miktar", Miktar);
        yeniUrun.put("Miktar_Birimi", Miktar_Birimi);
        open();
        veriTabani.insert("Urun", null, yeniUrun);
        close();
    }

    public void Urun_Miktar_ekle(int Miktar) {
        ContentValues yeniMiktar = new ContentValues();
        yeniMiktar.put("Miktar", Miktar);
        open();
        veriTabani.insert("Miktar", null, yeniMiktar);
        close();
    }

    public void tur_ekle(String Türü) {
        ContentValues yeniTür = new ContentValues();
        yeniTür.put("Türü", Türü);
        open();
        veriTabani.insert("Miktar", null, yeniTür);
        close();
    }

    public void Miktar_Birimi_ekle(String Birim) {
            ContentValues yeniBirim= new ContentValues();
        yeniBirim.put("Birim", Birim);
            open();
            veriTabani.insert("Birim", null, yeniBirim);
            close();
    }

    public void Alisveris_Listesi_degistir(long id,String ad) {
        ContentValues yeniListe = new ContentValues();
        yeniListe.put("ad", ad);
        open();
        veriTabani.update("Liste", yeniListe, "_id=" + id, null);
        close();
    }

    public void Alisveris_Listesi_Sil(long id){
        open();
        veriTabani.delete("Liste", "_id=" + id, null);
        close();
    }

    public void Favori_Sil(long id){
        open();
        veriTabani.delete("Favori", "_id=" + id, null);
        close();
    }

    public void Urun_Sil(long id){
        open();
        veriTabani.delete("Urun", "_id=" + id, null);
        close();
    }

    public void Miktar_Sil(long id){
        open();
        veriTabani.delete("Miktar", "_id=" + id, null);
        close();
    }

    public void Miktar_Birimi_Sil(long id){
        open();
        veriTabani.delete("Birim", "_id=" + id, null);
        close();
    }


    public void Favori_Urun_degistir(long id,String ad, Boolean Favori, double Fiyat, String Türü, int Miktar, String Miktar_Birimi) {
        ContentValues yeniFavoriUrun = new ContentValues();
        yeniFavoriUrun.put("ad", ad);
        yeniFavoriUrun.put("Favori", Favori);
        yeniFavoriUrun.put("Fiyat", Fiyat);
        yeniFavoriUrun.put("Türü", Türü);
        yeniFavoriUrun.put("Miktar", Miktar);
        yeniFavoriUrun.put("Miktar_Birimi", Miktar_Birimi);
        open();
        veriTabani.update("Favori_Urun",yeniFavoriUrun, "_id=" + id, null);
        close();
    }

    public void Urun_degistir(long id,String ad, Boolean Favori, double Fiyat, String Türü, int Miktar, String Miktar_Birimi) {
        ContentValues yeniUrun = new ContentValues();
        yeniUrun.put("ad", ad);
        yeniUrun.put("Favori", Favori);
        yeniUrun.put("Fiyat", Fiyat);
        yeniUrun.put("Türü", Türü);
        yeniUrun.put("Miktar", Miktar);
        yeniUrun.put("Miktar_Birimi", Miktar_Birimi);
        open();
        veriTabani.update("Urun",yeniUrun, "_id=" + id, null);
        close();
    }

    public Cursor ListeleriGetir(Long param){
        return veriTabani.query("Liste", new String[] {"_id", "ad"}, null, null, null, null, "ad");
    }

    public Cursor UrunleriGetir(Long param){
        return veriTabani.query("Urun", new String[] {"_id", "ad","Favori","Fiyat","Türü","Miktar","Miktar_Birimi"}, null, null, null, null, "ad");
    }

    public Cursor FavoriUrunleriGetir(Long param){
        return veriTabani.query("Favori_Urun", new String[]  {"_id", "ad","Favori","Fiyat","Türü","Miktar","Miktar_Birimi"}, null, null, "Favori", null, "ad");
    }
    public Cursor turleriGetir(){
        return veriTabani.query("tur", new String[] {"_id", "ad"}, null, null, null, null, "ad");
    }

    public Cursor urunmiktarlariniGetir(){
        return veriTabani.query("miktar", new String[] {"_id", "ad"}, null, null, null, null, "ad");
    }

    public Cursor ListeleriGetir(){
        return veriTabani.query("Liste", new String[] {"_id", "ad"}, null, null, null, null, "ad");
    }

    public Cursor birimleriGetir(){
        return veriTabani.query("birim", new String[] {"_id", "ad"}, null, null, null, null, "ad");
    }




    private class DatabaseOpenHelper extends SQLiteOpenHelper{

        public DatabaseOpenHelper(Context context, String name, CursorFactory factory, int version){
            super(context, name, factory, version);
        }

        public void onCreate(SQLiteDatabase db){
            String sqlListe = "CREATE TABLE Liste" +
                    "(_id integer primary key autoincrement," +
                    "ad TEXT);";
            db.execSQL(sqlListe);
            String sqlUrun = "CREATE TABLE Urun" +
                    "(_id integer primary key autoincrement," +
                    "ad TEXT,favori BOOLEAN,fiyat double,tur TEXT,"+
                    "miktar integer,miktar_birimi TEXT);";
            db.execSQL(sqlUrun);
            String sqlFavoriUrun = "CREATE TABLE Favori" +
                    "(_id integer primary key autoincrement," +
                    "ad TEXT,favori BOOLEAN,fiyat double,tur TEXT,"+
                    "miktar integer,miktar_birimi TEXT);";
            db.execSQL(sqlFavoriUrun);



            String sqlTur = "CREATE TABLE tur" +
                    "(_id integer primary key autoincrement," +
                    "ad TEXT);";
            db.execSQL(sqlTur);
            String sqlYonetmen = "CREATE TABLE yonetmen" +
                    "(_id integer primary key autoincrement," +
                    "ad TEXT, soyad TEXT);";
            db.execSQL(sqlYonetmen);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        }
    }

}
