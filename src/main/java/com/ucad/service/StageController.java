package com.ucad.service;


import com.ucad.bean.EtudiantBean;
import com.ucad.dao.StageRepository;
import com.ucad.entities.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RequestMapping("/stage")
public class StageController {
    @Autowired
    StageRepository stageRepository;
    @Autowired
    RestTemplate restTemplate;
    @GetMapping(path = "/all")
    List<Stage> listStage()
    {
        return  stageRepository.findAll();
    }
    private String getUrlWebService()
    {
        return  "http://localhost:9090/etudiants/";
    }

    @GetMapping(value = "/stage/{id}")
    EtudiantBean findByEtudiant(@PathVariable Long id)
    {
        return  restTemplate.getForEntity(getUrlWebService() + id, EtudiantBean.class).getBody();
    }

    @PostMapping(value = "/new/{id}")
    Stage save(@PathVariable  Long id, @RequestBody Stage stage)
    {
        EtudiantBean etudiantBean = findByEtudiant(id);
        if(etudiantBean != null)
        {
            return stageRepository.save(stage);
        }
        throw  new  RuntimeException("l etudiant n existe pas");
    }
}
