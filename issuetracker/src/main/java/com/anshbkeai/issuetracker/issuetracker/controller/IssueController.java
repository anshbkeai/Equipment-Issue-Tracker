package com.anshbkeai.issuetracker.issuetracker.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anshbkeai.issuetracker.issuetracker.dto.IssueForm;
import com.anshbkeai.issuetracker.issuetracker.model.Issue;
import com.anshbkeai.issuetracker.issuetracker.model.IssueStatus;
import com.anshbkeai.issuetracker.issuetracker.service.IssueService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/issues")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    @GetMapping("/create/{partId}")
    public String createForm(@PathVariable String partId, Model model) {

        IssueForm form = new IssueForm();
        form.setPartId(partId);

        model.addAttribute("issueForm", form);

        return "issue/createIssue";
    }


    @PostMapping
    public String createIssue(@ModelAttribute IssueForm form,
                              Authentication auth) {

        issueService.createIssue(form, auth.getName());
        return "redirect:/issues/part/" + form.getPartId();
    }
    @GetMapping("/part/{partId}")
    public String getIssues(@PathVariable String partId, Model model) {

        List<Issue> issues = issueService.allIssuesforPart(partId);

        model.addAttribute("issues", issues);
        model.addAttribute("partId", partId);

        return "issue/issueList";
    }
    @PostMapping("/{issueId}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateStatus(@PathVariable String issueId,
                               @RequestParam IssueStatus status,
                               @RequestParam String partId) {

        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        issueService.updateIssue(issueId, status);

        return "redirect:/issues/part/" + partId;
    }
}
