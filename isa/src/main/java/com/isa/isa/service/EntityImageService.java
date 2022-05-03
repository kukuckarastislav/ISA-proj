package com.isa.isa.service;

import com.isa.isa.DTO.EntityImageDTO;
import com.isa.isa.model.Adventure;
import com.isa.isa.model.EntityImage;
import com.isa.isa.model.Instructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Base64;

@Service
public class EntityImageService {

    public ArrayList<EntityImageDTO> getImageForAdventure(Adventure adventure) throws IOException {
        ArrayList<EntityImageDTO> images = new ArrayList<EntityImageDTO>();
        Instructor instructor = adventure.getInstructor();

        String path = new File("./static").getCanonicalPath() + File.separator + "EntityImages" + File.separator +
                "Instructors" + File.separator + instructor.getEmail() + File.separator + adventure.getName().replace(" ", "_");

        for (EntityImage img: adventure.getImages()) {
            String imgstr = "";
           // images.add(new EntityImageDTO(img.getName(), imgstr));
        }

        return images;
    }

    public ArrayList<EntityImage> createAndSaveImages(String role, String owner, String name, ArrayList<String> images){
        ArrayList<EntityImage> entityImages = new ArrayList<EntityImage>();

        String path = "main" + File.separator + "resources" + File.separator + "public";
        String dirName = name.replace(' ', '_');
        String imgPath = "images" + File.separator + role + File.separator + owner + File.separator + dirName;

        File dir = null;
        try {
            dir = new File(new File("./src").getCanonicalPath() + File.separator + path + File.separator + imgPath);
            if(!dir.exists()){
                if(dir.mkdir()){
                    System.out.println("Succesfuly created new directory for new entity");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int numberOfImages = 0;
        for(String img : images) {
            numberOfImages++;
            String imageString = img.split(",")[1];

            BufferedImage image = null;
            byte[] imageByte;

            imageByte = Base64.getDecoder().decode(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            try {
                image = ImageIO.read(bis);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String imgName = "img" + numberOfImages + ".jpg";

            try {
                System.out.println("Path to save image: " + dir.getCanonicalPath() + File.separator + imgName);
                ImageIO.write(image, "jpg", new File(dir.getCanonicalPath() + File.separator + imgName));
            } catch (IOException e) {
                e.printStackTrace();
            }

            entityImages.add(new EntityImage(imgName, (imgPath + File.separator + imgName).replace('\\','/' )));
        }

        return entityImages;
    }

    public byte[] getImageByte(String role, String user, String entity, String imgName){
        String path = "public" + File.separator + "images" + File.separator + role + File.separator + user + File.separator + entity + File.separator + imgName;
        ClassPathResource imgFile = new ClassPathResource(path);

        System.out.println("ENTITY IMAGE " + path);

        try {
            return StreamUtils.copyToByteArray(imgFile.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }



    public byte[] getEntityImage(String role, String user, String entity, String imgName){
        BufferedImage bufferedImage = readImage(role, user, entity, imgName);
        String imgStr = "";
        if(bufferedImage != null){
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            try {
                ImageIO.write(bufferedImage, "jpg", bos);
                byte[] imageBytes = bos.toByteArray();
                bos.close();
                return imageBytes;
                //imgStr = Base64.getEncoder().encodeToString(imageBytes);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private BufferedImage readImage(String role, String user, String entity, String imgName){
        String path = "main" + File.separator + "resources" + File.separator + "public";
        String imgPath = "images" + File.separator + role + File.separator + user + File.separator + entity + File.separator + imgName;

        File f = null;
        try {
            f = new File(new File("./src").getCanonicalPath() + File.separator + path + File.separator + imgPath);
            if(!f.exists()){
                System.out.println("ERROR img ne postoji");
            }else{
                // file postoji sada treba da ucitamo sliku
                BufferedImage img = null;
                try {
                    img = ImageIO.read(f);
                    return img;
                } catch (IOException e) {

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }



}
