package model;

import com.android.volley.toolbox.StringRequest;

public class GameModel {
    private int id;
    private String name;
    private String description;
    private String officialwebsiteurl;
    private String icon;
    private String background;

    public GameModel(){

    }

    public GameModel(int id, String name, String description, String officialwebsiteurl, String icono, String background) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.officialwebsiteurl = officialwebsiteurl;
        this.icon = icono;
        this.background = background;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icono) {
        this.icon = icono;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }


}
