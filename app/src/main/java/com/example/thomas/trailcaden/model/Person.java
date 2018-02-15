package com.example.thomas.trailcaden.model;

/**
 * Created by Thomas on 11/02/2018.
 */

public class Person {
    private String uid, name, firstname, date, genre, adresse, ville, cp, tel, mail;
    private String club, licence;
    private boolean isInscrit, isAdmin;
    private String parcours;

    public Person() {

    }

    public Person(String uid, String name, String firstname, String date, String mail, boolean isInscrit, boolean isAdmin, String parcours) {
        this.uid = uid;
        this.name = name;
        this.firstname = firstname;
        this.date = date;
        this.mail = mail;
        this.isInscrit = isInscrit;
        this.isAdmin = isAdmin;
        this.parcours = parcours;

        this.cp = "";
        this.ville = "";
        this.adresse = "";
        this.genre = "";
        this.tel = "";
        this.club = "";
        this.licence = "";
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public boolean isInscrit() {
        return isInscrit;
    }

    public void setInscrit(boolean inscrit) {
        isInscrit = inscrit;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setParcours(String p){
        this.parcours = p;
    }

    public String getParcours(){
        return this.parcours;
    }
}
