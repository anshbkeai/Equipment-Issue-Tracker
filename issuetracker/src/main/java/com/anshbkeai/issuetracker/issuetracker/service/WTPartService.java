package com.anshbkeai.issuetracker.issuetracker.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.anshbkeai.issuetracker.issuetracker.dto.WTPartFrom;
import com.anshbkeai.issuetracker.issuetracker.model.WTPart;
import com.anshbkeai.issuetracker.issuetracker.repository.WTPartRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WTPartService {

    private  final WTPartRepository partRepository;

    @Transactional
    public WTPart createProduct(WTPartFrom partFrom, String createdBy) {
        WTPart part = WTPart.builder()
                            .partId("WTPART-" + UUID.randomUUID().toString())
                            .partName(partFrom.getName())
                            .partDescription(partFrom.getDescription())
                            .dateCreated(LocalDateTime.now())
                            .dateUpdated(LocalDateTime.now())
                            .build();
        return partRepository.save(part);
    }

    public List<WTPart> getAllParts() {
        return partRepository.findAll();
    }
}

