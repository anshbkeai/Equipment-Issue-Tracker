package com.anshbkeai.issuetracker.issuetracker.dto;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WTPartFrom {
     
    @NotEmpty(message = "Should not be empty")
    private String name ;

    @Length(max = 200)
    private String description;
} 
