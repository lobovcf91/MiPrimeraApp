package model;

public class GameModel {
    private int id;
    private String name;
    private String description;
    private String officialwebsiteurl;
    private int iconoDrawable;
    private int backgroundDrawable;

    public GameModel(int id, String name, String description, String officialwebsiteurl, int iconoDrawable, int backgroundDrawable) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.officialwebsiteurl = officialwebsiteurl;
        this.iconoDrawable = iconoDrawable;
        this.backgroundDrawable = backgroundDrawable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOfficialwebsiteurl() {
        return officialwebsiteurl;
    }

    public void setOfficialwebsiteurl(String officialwebsiteurl) {
        this.officialwebsiteurl = officialwebsiteurl;
    }

    public int getIconoDrawable() {
        return iconoDrawable;
    }

    public void setIconoDrawable(int iconoDrawable) {
        this.iconoDrawable = iconoDrawable;
    }

    public int getBackgroundDrawable() {
        return backgroundDrawable;
    }

    public void setBackgroundDrawable(int backgroundDrawable) {
        this.backgroundDrawable = backgroundDrawable;
    }


}
