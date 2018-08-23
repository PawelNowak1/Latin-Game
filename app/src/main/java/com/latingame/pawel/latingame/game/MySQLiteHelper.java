package com.latingame.pawel.latingame.game;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "WordsDB";

    // Books table name
    private static final String TABLE_WORDS = "words";

    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_MAXIM = "maxim";
    private static final String KEY_TRANSLATION = "translation";
    private static final String KEY_EXTRAINFO = "extraInfo";

    private static final String[] COLUMNS = {KEY_ID, KEY_MAXIM, KEY_TRANSLATION, KEY_EXTRAINFO};



    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_BOOK_TABLE = "CREATE TABLE words ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "maxim TEXT, "+
                "translation TEXT, "+
                "extraInfo TEXT )";

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
        values.put(KEY_MAXIM, word.getMaxim());
        values.put(KEY_TRANSLATION, word.getTranslation());
        values.put(KEY_EXTRAINFO, word.getExtraInfo());

        db.insert(TABLE_WORDS, null, values);
        db.close();
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
        word.setMaxim(cursor.getString(1));
        word.setTranslation(cursor.getString(2));
        word.setExtraInfo(cursor.getString(3));
        cursor.close();
        return word;
    }
}
