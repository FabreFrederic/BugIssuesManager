package com.fabrefrederic.webapp.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fabrefrederic.business.Issue;
import com.fabrefrederic.service.interfaces.IssueService;
import com.fabrefrederic.webapp.form.IssueForm;

@Controller
public class IssueSearchController {

    @Autowired
    private IssueService issueService;

    @RequestMapping(value = "/issueSearch", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("issueSearch", "issueForm", new IssueForm());
    }

    @RequestMapping(value = "/issueSearchResult", method = RequestMethod.POST)
    public ModelAndView submit(@ModelAttribute("issue") final Issue issue, final BindingResult result,
            final ModelMap model) {

        final Set<Issue> issues = issueService.getAffectedIssuesByIssueId(issue.getName());

        final ModelAndView modelResult = new ModelAndView("issueSearchResult");
        modelResult.addObject("issueList", issues);
        return modelResult;
    }

}