package com.anshbkeai.issuetracker.issuetracker.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.anshbkeai.issuetracker.issuetracker.dto.IssueForm;
import com.anshbkeai.issuetracker.issuetracker.model.Issue;
import com.anshbkeai.issuetracker.issuetracker.model.IssueStatus;
import com.anshbkeai.issuetracker.issuetracker.model.WTPart;
import com.anshbkeai.issuetracker.issuetracker.repository.IssueRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IssueService {

    private final IssueRepository issueRepository;
    private final WTPartService partService;

    @Transactional
    public Issue createIssue(IssueForm form, String createdBy) {
        WTPart part = partService.findbyId(form.getPartId());
        if(part != null) {
            Issue issue = Issue.builder()
                                .createdByUser(createdBy)
                                .dateCreated(LocalDateTime.now())
                                .dateUpdated(LocalDateTime.now())
                                .description(form.getDescription())
                                .issueId("ISSUSE-"+UUID.randomUUID().toString().split("-")[0])
                                .status(IssueStatus.OPENED)
                                .wtPart(part)
                                .build();
            issueRepository.save(issue);
            return issue;
        }
        else {
            return null;
        }
    }

    public List<Issue> allIssuesforPart(String partId) {
        WTPart part = partService.findbyId(partId);
        if(part != null) {
            
            return issueRepository.findByWtPart(part);
        }
        else {
            return null;
        }
    }

    @Transactional
    public void updateIssue(String issueId, IssueStatus issueStatus) {
        Issue issue = issueRepository.findById(issueId).orElse(null);
        if(issue != null) {
            issue.setDateUpdated(LocalDateTime.now());
            issue.setStatus(issueStatus);
        }
    }
}
