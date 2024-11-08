package com.example.web.services.impl;

import com.example.web.dto.DreamDto;
import com.example.web.models.Dream;
import com.example.web.repository.DreamRepository;
import com.example.web.services.DreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DreamServiceImpl implements DreamService {
    private DreamRepository dreamRepository;

    @Autowired
    public DreamServiceImpl(DreamRepository clubRepository) {
        this.dreamRepository = clubRepository;
    }

    @Override
    public List<DreamDto> findAllClubs() {
        List<Dream> clubs = dreamRepository.findAll();
        return clubs.stream().map((club) -> mapToClubDto(club)).collect(Collectors.toList());
    }

    private DreamDto mapToClubDto(Dream club){
        DreamDto clubDto = DreamDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .build();
        return clubDto;
    }
}
