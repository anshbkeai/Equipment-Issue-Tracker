package com.anshbkeai.issuetracker.issuetracker.dto;

import com.anshbkeai.issuetracker.issuetracker.model.IssueStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueStatusUpdateForm {
 private IssueStatus status;
}
