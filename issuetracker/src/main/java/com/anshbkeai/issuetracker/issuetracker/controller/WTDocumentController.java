package com.anshbkeai.issuetracker.issuetracker.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anshbkeai.issuetracker.issuetracker.dto.WTDcoumentForm;
import com.anshbkeai.issuetracker.issuetracker.model.WTDocument;
import com.anshbkeai.issuetracker.issuetracker.service.WTDocumentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/wtdocument")
public class WTDocumentController {

    private final WTDocumentService documentService;


    @GetMapping("/create/{wtpartId}")
    public String showCreateForm(@PathVariable String wtpartId, Model model) {

        WTDcoumentForm form = new WTDcoumentForm();
        form.setWtpartId(wtpartId);

        model.addAttribute("wtdocument", form);

        return "document/createDoc";
    }

    @PostMapping("/create")
    public String createDocument(@Valid @ModelAttribute("wtdocument") WTDcoumentForm form,
                                 BindingResult result,
                                 Model model) {

        if (result.hasErrors()) {
            return "document/createDoc";
        }
         WTDocument saved = documentService.addDocument(form);
        if (saved == null) {
            model.addAttribute("error", "Invalid WT Part ID");
            return "document/createDoc";
        }

        return "redirect:/wtdocument/view/" + saved.getDocumentId();
    }

    @GetMapping("/view/{id}")
    public String viewDocument(@PathVariable String id, Model model) {

        WTDocument document = documentService.getDocumentByID(id);
        model.addAttribute("document", document);

        return "document/view-wtdocument";
    }

    @GetMapping("/part/{partId}")
    public String getDocumentsByPart(@PathVariable String partId, Model model) {

        List<WTDocument> docs = documentService.getAllDocByPartID(partId);

        model.addAttribute("documents", docs);
        model.addAttribute("partId", partId);

        return "document/list-wtdocuments";
    }
}
