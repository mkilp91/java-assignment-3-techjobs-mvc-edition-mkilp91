package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;
import static org.launchcode.techjobs.mvc.controllers.ListController.tableChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }


    // TODO #3 - Create a handler to process a search request and render the updated search view.
//    @GetMapping("search")
//    public String renderSearchForm(Model model) {
//    model.addAttribute("searchTerm", tableChoices);
//    model.addAttribute("searchType", columnChoices);
//
//
//        return "/results";
//    }


    @PostMapping(value = "results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam(required=false) String searchTerm) {
        ArrayList<Job> jobs;
        if (searchTerm.equals("all") || searchTerm.equals("")){
            jobs = JobData.findAll();
            model.addAttribute("title", "All Jobs");
        } else if (searchType.equals("all")){
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("title", "Jobs with " + columnChoices.get(searchType) + ": " + searchTerm);
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("title", "Jobs with " + columnChoices.get(searchType) + ": " + searchTerm);
        }
        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", columnChoices);

        return "search";
    }
}
