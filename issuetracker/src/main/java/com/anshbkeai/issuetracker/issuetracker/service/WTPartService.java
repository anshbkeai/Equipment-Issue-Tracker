package com.anshbkeai.issuetracker.issuetracker.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.anshbkeai.issuetracker.issuetracker.dto.WTPartFrom;
import com.anshbkeai.issuetracker.issuetracker.model.IssueStatus;
import com.anshbkeai.issuetracker.issuetracker.model.WTPart;
import com.anshbkeai.issuetracker.issuetracker.repository.IssueRepository;
import com.anshbkeai.issuetracker.issuetracker.repository.WTDocumentRepository;
import com.anshbkeai.issuetracker.issuetracker.repository.WTPartRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WTPartService {

    private  final WTPartRepository partRepository;
    private final WTDocumentRepository documentRepository;
    private final IssueRepository issueRepository;

    @Transactional
    public WTPart createProduct(WTPartFrom partFrom, String createdBy) {
        WTPart part = WTPart.builder()
                            .partId("WTPART-" + UUID.randomUUID().toString())
                            .partName(partFrom.getName())
                            .partDescription(partFrom.getDescription())
                            .dateCreated(LocalDateTime.now())
                            .dateUpdated(LocalDateTime.now())
                            .partCreatedBy(createdBy)
                            .build();
        return partRepository.save(part);
    }

    public List<WTPart> getAllParts() {
        return partRepository.findAll();
    }

    public WTPart findbyId(String id) {
        return partRepository.findById(id).orElse(null);
    }

    public void deletePart(String id) {
        partRepository.deleteById(id);
    }

    public Map<String, Long> getDashBoardData() {
        long totalParts = partRepository.count();
        long totalDocuments = documentRepository.count();
        long openIssues = issueRepository.countByStatus(IssueStatus.OPENED);

        return Map.of("totalParts", totalParts, "totalDocuments", totalDocuments, "openIssues", openIssues);
    }
}

