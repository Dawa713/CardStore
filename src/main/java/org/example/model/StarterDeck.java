// src/main/java/org/example/model/StarterDeck.java
package org.example.model;

import java.sql.Date;

public class StarterDeck {
    private int id;
    private String name;
    private Date releaseDate;
    private boolean discontinued;
    private float price;
    private String image;

    // Getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Date getReleaseDate() { return releaseDate; }
    public void setReleaseDate(Date releaseDate) { this.releaseDate = releaseDate; }

    public boolean isDiscontinued() { return discontinued; }
    public void setDiscontinued(boolean discontinued) { this.discontinued = discontinued; }

    public float getPrice() { return price; }
    public void setPrice(float price) { this.price = price; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
}
