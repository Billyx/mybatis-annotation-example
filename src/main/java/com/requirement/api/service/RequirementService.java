package com.requirement.api.service;

import com.requirement.api.mapper.RequirementMapper;
import com.requirement.api.model.Requirement;
import com.requirement.api.util.FileUploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class RequirementService {

    private RequirementMapper requirementMapper;
    private final Path root = Paths.get("uploads");
    private final Logger logger = LoggerFactory.getLogger(RequirementService.class);

    public RequirementService(RequirementMapper requirementMapper) {
        this.requirementMapper = requirementMapper;
    }

    public List<Requirement> getRequirements() {
        return this.requirementMapper.findAll();
    }

    public void insertRequirement(Requirement requirement, MultipartFile file) throws IOException {

        // Uploading the file to server
        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        String code = FileUploadUtil.saveFile(filename, file);
        requirement.setAttachedFile(code + filename);

        // Inserting requirement to database
        this.requirementMapper.insert(requirement);

    }

    public void deleteRequirement(Integer id) {

        Requirement req = this.getRequirement(id);
        String file = req.getAttachedFile();

        try {
            Boolean delete = Files.deleteIfExists(this.root.resolve(file));
            logger.info( "Borrado" );
        }catch (IOException e){
            e.printStackTrace();
            logger.info( "Error Borrando ");
        }

        this.requirementMapper.delete(id);
    }

    public Requirement getRequirement(Integer id) {
        return this.requirementMapper.findRequirement(id);
    }

    public byte[] getImageWithMediaType(String imageName) throws IOException {
        return FileUploadUtil.getImageWithMediaType(imageName);
    }
}
