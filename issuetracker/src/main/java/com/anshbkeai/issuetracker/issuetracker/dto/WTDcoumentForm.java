package com.anshbkeai.issuetracker.issuetracker.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WTDcoumentForm {

    @NotEmpty(message = "Cannot be Empty")
    @Pattern(
    regexp = "^(https?://).+",
    message = "Document URL must start with http:// or https://"
    )
    private String docUrl;

    @NotNull
   private String documentName;
    @NotNull
    private String wtpartId;


}
