package com.example.web.controller;

import com.example.web.dto.DreamDto;
import com.example.web.services.DreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DreamController {
    private DreamService dreamService;

    @Autowired
    public DreamController(DreamService clubService) {
        this.dreamService = clubService;
    }

    @GetMapping("/clubs")
    public String listCLubs(Model model){
        List<DreamDto> clubs = dreamService.findAllClubs();
        model.addAttribute("clubs", clubs);
        return "clubs-list";
    }
}
