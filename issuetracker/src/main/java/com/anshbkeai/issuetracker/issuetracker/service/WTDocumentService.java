package com.anshbkeai.issuetracker.issuetracker.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.anshbkeai.issuetracker.issuetracker.dto.WTDcoumentForm;
import com.anshbkeai.issuetracker.issuetracker.model.WTDocument;
import com.anshbkeai.issuetracker.issuetracker.model.WTPart;
import com.anshbkeai.issuetracker.issuetracker.repository.WTDocumentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WTDocumentService {

    private final WTDocumentRepository documentRepository;
    private final WTPartService partService;

    public WTDocument addDocument(WTDcoumentForm dcoumentForm) {
        WTPart wtPart = partService.findbyId(dcoumentForm.getWtpartId());
        if(wtPart != null) {
        WTDocument document = WTDocument.builder()
                                .documentId("DOC-"+UUID.randomUUID().toString().split("-")[0])
                                .docUrl(dcoumentForm.getDocUrl())
                                .wtPart(wtPart)
                                .dateCreated(LocalDateTime.now())
                                .dateUpdated(LocalDateTime.now()) 
                                .documentName(dcoumentForm.getDocumentName())                               
                                .build();
            return documentRepository.save(document);
        }
        else {
            return null;
        }
    }

    public List<WTDocument> getAllDocByPartID(String partID) {
        return partService.findbyId(partID).getDocuments();

    }

    public WTDocument getDocumentByID(String id) {
        return documentRepository.findById(id).orElse(null);
    }
}
