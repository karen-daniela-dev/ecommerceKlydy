package com.ecommerce.klydy.service;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {
    @Autowired
    private Cloudinary cloudinary;

    public String subirImagen(MultipartFile file) {

        try {
            Map uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.emptyMap()
            );

            return uploadResult.get("secure_url").toString();

        } catch (IOException e) {
            throw new RuntimeException("Error subiendo imagen a Cloudinary");
        }
    }
    public String extraerPublicId(String url) {
        try {
            String[] partes = url.split("/upload/");
            String path = partes[1]; // v1234567/abc123.jpg

            String sinVersion = path.replaceFirst("v\\d+/", ""); // abc123.jpg
            return sinVersion.substring(0, sinVersion.lastIndexOf(".")); // abc123

        } catch (Exception e) {
            return null;
        }
    }
    public void eliminarImagen(String publicId) {
        try {
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (Exception e) {
            System.out.println("Error eliminando imagen: " + e.getMessage());
        }
    }
}
