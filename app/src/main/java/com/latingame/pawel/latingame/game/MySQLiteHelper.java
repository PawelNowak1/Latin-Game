package com.latingame.pawel.latingame.game;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "WordsDB";

    // Instance
    private static MySQLiteHelper instance;

    // Books table name
    private static final String TABLE_WORDS = "words";

    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_ENGLISH_WORD = "englishWord";
    private static final String KEY_TRANSLATION = "translation";
    private static final String KEY_CATEGORY = "category";

    private static final String[] COLUMNS = {KEY_ID, KEY_ENGLISH_WORD, KEY_TRANSLATION, KEY_CATEGORY};


    public static synchronized MySQLiteHelper getInstance(Context context){
        if(instance == null){
            instance = new MySQLiteHelper(context.getApplicationContext());
        }

        return instance;
    }

    private MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_BOOK_TABLE = "CREATE TABLE words ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "englishWord TEXT, "+
                "translation TEXT, "+
                "category TEXT )";

        // create books table
        sqLiteDatabase.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS words");

        this.onCreate(sqLiteDatabase);
    }

    public void addWord(Word word){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ENGLISH_WORD, word.getEnglishWord());
        values.put(KEY_TRANSLATION, word.getTranslation());
        values.put(KEY_CATEGORY, word.getCategory());

        db.insert(TABLE_WORDS, null, values);
        db.close();
    }

    public String[] getCategories(){
        /*Cursor cursor =
                db.query(true,TABLE_WORDS,
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        null, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit
        */
        Cursor cursor = getReadableDatabase().rawQuery("SELECT DISTINCT " + KEY_CATEGORY +" FROM " + TABLE_WORDS, null);

        cursor.moveToFirst();
        ArrayList<String> names = new ArrayList<String>();
        while(!cursor.isAfterLast()) {
            names.add(cursor.getString(cursor.getColumnIndex(KEY_CATEGORY)));
            cursor.moveToNext();
        }
        cursor.close();

        return names.toArray(new String[names.size()]);
    }

    public Integer[] getIdsWordsByCategory(String chosenCategory){

        Cursor cursor = getReadableDatabase().rawQuery("SELECT " + KEY_ID + " FROM " + TABLE_WORDS + " WHERE " + KEY_CATEGORY + " = '" + chosenCategory + "'", null);
        cursor.moveToFirst();

        ArrayList<Integer> names = new ArrayList<Integer>();
        while(!cursor.isAfterLast()) {
            names.add(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            cursor.moveToNext();
        }
        cursor.close();

        return names.toArray(new Integer[names.size()]);
    }
    public Word getWord(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =
                db.query(TABLE_WORDS, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        if(cursor != null)
            cursor.moveToFirst();

        Word word = new Word();
        word.setId(Integer.parseInt(cursor.getString(0)));
        word.setEnglishWord(cursor.getString(1));
        word.setTranslation(cursor.getString(2));
        word.setCategory(cursor.getString(3));

        cursor.close();
        return word;
    }
}
