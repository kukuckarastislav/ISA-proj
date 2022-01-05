package com.isa.isa.DTO;

import com.isa.isa.model.EntityImage;

public class EntityImageDTO {

    private String name;
    private String imgstr;
    private String path;

    public EntityImageDTO(String name, String imgstr, String path) {
        this.name = name;
        this.imgstr = imgstr;
        this.path = path;
    }

    public EntityImageDTO(EntityImage img) {
        this.name = img.getName();
        this.imgstr = "";
        this.path = img.getPath();
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgstr() {
        return imgstr;
    }

    public void setImgstr(String imgstr) {
        this.imgstr = imgstr;
    }
}
