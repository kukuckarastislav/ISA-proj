package com.isa.isa.controller;

import com.isa.isa.DTO.AdventureDTO;
import com.isa.isa.service.EntityImageService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/entityImage")
public class EntityImageController {

    @Autowired
    private EntityImageService entityImageService;


    @RequestMapping(value = "/images/{role}/{user}/{entity}/{imgName}", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImageRes(@PathVariable String role, @PathVariable String user, @PathVariable String entity, @PathVariable String imgName) throws IOException {

        //byte[] img = entityImageService.getImageByte(role, user, entity, imgName);
        byte[] img = entityImageService.getEntityImage(role, user, entity, imgName);
        if(img != null){
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(img);
        }else{
            return ResponseEntity
                    .badRequest()
                    .contentType(MediaType.IMAGE_JPEG).body(null);
        }

    }
}



