package com.number4.dogbreeds.Models;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity (tableName = "favorite_dog_table")
public class DogModel {
;
    @PrimaryKey(autoGenerate = false)
    @NonNull
    private String name ;
    private int thump;

    private String height;
    private String weight;
    private String origin;
    private String lifeSpan;
    private String place;
    private String description;

    public String getFileName3D() {
        return fileName3D;
    }

    public void setFileName3D(String fileName3D) {
        this.fileName3D = fileName3D;
    }

    private  String fileName3D;

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    private  boolean isFavorite;


    public DogModel( String name, int thump,  String height, String weight, String origin, String lifeSpan, String place, String description, String fileName3D) {

        this.name = name;
        this.thump = thump;
        this.height = height;
        this.weight = weight;
        this.origin = origin;
        this.lifeSpan = lifeSpan;
        this.place = place;
        this.description = description;
        this.isFavorite = false;
        this.fileName3D = fileName3D;
    }

    public DogModel() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getThump() { return thump;}

    public void setThump(int thump) {
        this.thump = thump;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
