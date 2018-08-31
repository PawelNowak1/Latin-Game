package com.latingame.pawel.latingame.game;

import android.os.Parcel;
import android.os.Parcelable;

public class Word implements Parcelable {
    private int id;
    private String englishWord;
    private String translation;
    private String category;

    public Word(){}

    public Word(String maxim, String translation, String extraInfo){
        this.englishWord = maxim;
        this.translation = translation;
        this.category = extraInfo;
    }

    @Override
    public String toString() {
        return englishWord + "\n" + translation + "\n" + category;

    }

    public String getEnglishWord() {
        return englishWord;
    }

    public String getTranslation() {
        return translation;
    }

    public String getCategory() {
        return category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    protected Word(Parcel in) {
        id = in.readInt();
        englishWord = in.readString();
        translation = in.readString();
        category = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(englishWord);
        dest.writeString(translation);
        dest.writeString(category);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Word> CREATOR = new Parcelable.Creator<Word>() {
        @Override
        public Word createFromParcel(Parcel in) {
            return new Word(in);
        }

        @Override
        public Word[] newArray(int size) {
            return new Word[size];
        }
    };
}
