package com.latingame.pawel.latingame.game;

public class Word {
    private int id;
    private String maxim;
    private String translation;
    private String extraInfo;
    public String[] split;

    public Word(){}

    public Word(String maxim, String translation, String extraInfo){
        this.maxim = maxim;
        this.translation = translation;
        this.extraInfo = extraInfo;
        split = maxim.split("\\s+");
    }

    public String[] getSplit() {
        return split;
    }

    @Override
    public String toString() {
        return maxim + "\n" + translation + "\n" + extraInfo;

    }
    public void setSplit(){
        split = maxim.split("\\s+");
    }
    public String getMaxim() {
        return maxim;
    }

    public String getTranslation() {
        return translation;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMaxim(String maxim) {
        this.maxim = maxim;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }
}
