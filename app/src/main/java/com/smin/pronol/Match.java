package com.smin.pronol;

public class Match {

    private String Date;
    private String Domicile;
    private String Exterieur;
    private int Score_domicile;
    private int Score_exterieur;

    public Match(){

    }

    public Match(String Date, String Domicile, String Exterieur, int Score_domicile, int Score_exterieur) {
        this.Date = Date;
        this.Domicile = Domicile;
        this.Exterieur = Exterieur;
        this.Score_domicile = Score_domicile;
        this.Score_exterieur = Score_exterieur;
    }

    public String getDate() {
        return Date;
    }

    public String getDomicile() {
        return Domicile;
    }

    public String getExterieur() {
        return Exterieur;
    }

    public int getScore_domicile() {
        return Score_domicile;
    }

    public int getScore_exterieur() {
        return Score_exterieur;
    }
}
