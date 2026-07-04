package com.anshbkeai.issuetracker.issuetracker.model;

import java.time.LocalDateTime;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WTPart {

    @Id
    private String partId;

    private String partName;

    private String partDescription;

    private String partCreatedBy;

    private LocalDateTime dateCreated;

    private LocalDateTime dateUpdated;

    @OneToMany(mappedBy = "wtPart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WTDocument> documents ;

    @OneToMany(mappedBy = "wtPart", cascade = CascadeType.ALL, orphanRemoval = true , fetch = FetchType.LAZY)
    private List<Issue> issues ;

}
