package com.anshbkeai.issuetracker.issuetracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anshbkeai.issuetracker.issuetracker.model.Issue;
import com.anshbkeai.issuetracker.issuetracker.model.IssueStatus;
import com.anshbkeai.issuetracker.issuetracker.model.WTPart;

public interface IssueRepository extends JpaRepository<Issue,String>{

    List<Issue> findByWtPart(WTPart wtPart);
    long countByStatus(IssueStatus status);
}
