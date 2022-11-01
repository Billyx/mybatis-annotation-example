package com.requirement.api.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {

    String storageDirectoryPath = "uploads";

    public static String saveFile(String filename, MultipartFile file) throws IOException {
        Path uploadDirectory = Paths.get("uploads");

        String fileCode = RandomStringUtils.randomAlphanumeric(8);

        try (InputStream inputStream = file.getInputStream()) {
            Path filePath = uploadDirectory.resolve(fileCode + "-" + filename);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            return fileCode;

        } catch (IOException e) {
            throw new IOException("Error uploading file " + filename , e);
        }
    }

    public static byte[] getImageWithMediaType(String imageName) throws IOException {

        Path destination =   Paths.get("uploads" + "\\" + imageName);// retrieve the image by its name

        return IOUtils.toByteArray(String.valueOf(destination.toUri()));
    }
}
