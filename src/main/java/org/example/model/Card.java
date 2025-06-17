package org.example.model;

import java.time.LocalDate;

public class Card {
    private int id;
    private String name;
    private LocalDate releaseDate;
    private int attack;
    private int defense;

    private boolean foil;
    private float price;
    private String image;

    // getters & setters...

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }

    public int getAttack() { return attack; }
    public void setAttack(int attack) { this.attack = attack; }

    public int getDefense() { return defense; }
    public void setDefense(int defense) { this.defense = defense; }

    public boolean isFoil() { return foil; }
    public void setFoil(boolean foil) { this.foil = foil; }

    public float getPrice() { return price; }
    public void setPrice(float price) { this.price = price; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
}
