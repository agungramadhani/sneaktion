package com.example.sneaktion;

import java.math.BigDecimal;

public class DataHome {

    private String title, image_url;
    private int rank;
    private BigDecimal score;


    public String getImage_url(){
        return image_url;
    }
    public void setImage_url(String image_url){
        this.image_url = image_url;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public int getRank() {
        return rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }
    public void setScore(BigDecimal score) {
        this.score = score;
    }
    public BigDecimal getScore() {
        return score;
    }

}