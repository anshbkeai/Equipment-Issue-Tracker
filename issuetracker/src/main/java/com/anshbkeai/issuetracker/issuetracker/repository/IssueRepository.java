package com.anshbkeai.issuetracker.issuetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anshbkeai.issuetracker.issuetracker.model.Issue;

public interface IssueRepository extends JpaRepository<Issue,String>{

}
