package com.anshbkeai.issuetracker.issuetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anshbkeai.issuetracker.issuetracker.model.WTDocument;

@Repository
public interface WTDocumentRepository  extends JpaRepository<WTDocument,String>{

}
