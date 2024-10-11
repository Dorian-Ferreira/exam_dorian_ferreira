package fr.dorian_ferreira.exam.service.utils;

import fr.dorian_ferreira.exam.slugger.Slugger;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class FileUploaderService {
    private Path path;
    private final Slugger slugger;

    public FileUploaderService(Slugger slugger) {
        this.slugger = slugger;
    }

    private void init(String path) {
        this.path = Path.of(path);
        try {
            if (!Files.exists(this.path)) {
                Files.createDirectories(this.path);
            }
        } catch (IOException e) {
            System.err.println("Cannot create folders.");
        }
    }

    public String save(MultipartFile file, String pathString) {
        if (file.isEmpty() || file.getOriginalFilename() == null) {
            return null;
        }
        init(pathString);
        try {
            String uniqId = UUID.randomUUID().toString();
            String fileName = uniqId + "-" + StringUtils.cleanPath(slugger.slugify(file.getOriginalFilename()));
            Path targetLocation = path.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return targetLocation.toString();
        } catch (IOException e) {
            return null;
        }
    }
}


