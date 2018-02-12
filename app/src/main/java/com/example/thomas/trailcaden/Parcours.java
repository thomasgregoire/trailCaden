package com.example.thomas.trailcaden;

import java.sql.Timestamp;

/**
 * Created by Joris on 12/02/2018.
 */

public class Parcours {
    private String uid, libelle, distance;
    private Timestamp heureLimiteInscription, heureDepart;

    public Parcours(String uid, String libelle, String distance, Timestamp heureLimiteInscription, Timestamp heureDepart) {
        this.uid = uid;
        this.libelle = libelle;
        this.distance = distance;
        this.heureLimiteInscription = heureLimiteInscription;
        this.heureDepart = heureDepart;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public Timestamp getHeureLimiteInscription() {
        return heureLimiteInscription;
    }

    public void setHeureLimiteInscription(Timestamp heureLimiteInscription) {
        this.heureLimiteInscription = heureLimiteInscription;
    }

    public Timestamp getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(Timestamp heureDepart) {
        this.heureDepart = heureDepart;
    }
}
