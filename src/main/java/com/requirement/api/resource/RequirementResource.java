package com.requirement.api.resource;

import com.requirement.api.model.Requirement;
import com.requirement.api.service.RequirementService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/requirement")
public class RequirementResource {

    private RequirementService service;

    public RequirementResource(RequirementService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Requirement> getRequirements(){
        return service.getRequirements();
    }

    @RequestMapping(method = RequestMethod.POST, value="/add" )
    public void insertRequirement(@RequestParam("idWorker") String idWorker,
                                  @RequestParam("description") String description,
                                  @RequestParam("file") MultipartFile file) throws IOException {

        Requirement req = new Requirement();
        req.setIdWorkerApplicant(Long.valueOf(idWorker));
        req.setDescription(description);
        req.setAttachedFile(file.getOriginalFilename());

        service.insertRequirement(req,file);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRequirement(@PathVariable Integer id) {
        service.deleteRequirement(id);
    }

    @GetMapping("/find/{id}")
    public Requirement getRequirement(@PathVariable Integer id){
        return service.getRequirement(id);
    }

    public @ResponseBody byte[] getImageWithMediaType(@PathVariable(name = "imageName") String fileName) throws IOException {
        return this.service.getImageWithMediaType(fileName);
    }
}
