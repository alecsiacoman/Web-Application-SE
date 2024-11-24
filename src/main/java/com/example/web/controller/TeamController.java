package com.example.web.controller;

import com.example.web.models.TeamMember;
import com.example.web.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping("/team")
    public String teamPage(Model model){
        List<TeamMember> teamMembers = teamService.getTeamMembers();
        model.addAttribute("teamMembers", teamMembers);
        return "team";
    }
}
