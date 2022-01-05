package com.isa.isa.service;

import com.isa.isa.DTO.EntityImageDTO;
import com.isa.isa.model.Adventure;
import com.isa.isa.model.EntityImage;
import com.isa.isa.model.Instructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

}
