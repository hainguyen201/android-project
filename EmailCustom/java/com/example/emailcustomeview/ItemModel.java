package com.example.emailcustomeview;

public class ItemModel {
    private String textTile;
    private String textContent;
    private String time;
    private boolean ckFavorite;
    private String bgColor;

    public ItemModel(String textTile, String textContent, String time, boolean ckFavorite, String bgColor) {
        this.textTile = textTile;
        this.textContent = textContent;
        this.time = time;
        this.ckFavorite = ckFavorite;
        this.bgColor = bgColor;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getTextTile() {
        return textTile;
    }

    public void setTextTile(String textTile) {
        this.textTile = textTile;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isCkFavorite() {
        return ckFavorite;
    }

    public void setCkFavorite(boolean ckFavorite) {
        this.ckFavorite = ckFavorite;
    }
}
