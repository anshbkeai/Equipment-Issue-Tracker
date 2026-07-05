package com.anshbkeai.issuetracker.issuetracker.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueForm {

    @Length(max = 200)
    @NotEmpty(message = "Description cannot be Null")
    private String description;

    private String partId;
}
