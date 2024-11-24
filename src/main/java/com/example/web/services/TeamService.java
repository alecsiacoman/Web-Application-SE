package com.example.web.services;

import com.example.web.models.TeamMember;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class TeamService {
    public List<TeamMember> getTeamMembers() {
        try{
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = getClass().getResourceAsStream("/data/team.json");
            return mapper.readValue(is, new TypeReference<List<TeamMember>>() {});
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
