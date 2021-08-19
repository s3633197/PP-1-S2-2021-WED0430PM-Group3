package com.matchmaking.backend.controller;


import com.matchmaking.backend.common.lang.Result;
import com.matchmaking.backend.entity.Resume;
import com.matchmaking.backend.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasRole('Personal')")
@RequestMapping("/api/seeker")
public class ResumeController {

    @Autowired
    ResumeService resumeService;

    @PostMapping("/create")
    public Result createResume(@RequestBody Resume resume){
        return   resumeService.createResume(resume);
    }

    @GetMapping("/resume")
    public Result getResume(){
        return  resumeService.getResume();
    }

    @PutMapping("/update")
    public Result updateResume(@RequestBody Resume resume){
        return resumeService.updateResume(resume);
    }


}
