package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.launchcode.javawebdevtechjobsmvc.models.JobField;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;
import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.tableChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        String checkedValue = "all";
        model.addAttribute("checkedValue", checkedValue);
        return "search";
    }

    @RequestMapping(value = "results")
    public String displaySearchResults(Model model, @RequestParam String column, @RequestParam String value) {
        ArrayList<Job> results;

        if (column.toLowerCase().equals("all")){
            results = JobData.findAll();
            model.addAttribute("title", "All Jobs");
        } else {
            results = JobData.findByColumnAndValue(column, value);
            model.addAttribute("title", "Jobs with " + columnChoices.get(column) + ": " + value);
        }
        String checkedValue = column + "";
        model.addAttribute("checkedValue", checkedValue);
        model.addAttribute("results", results);
        model.addAttribute("columns", columnChoices);

        return "search";
    }

}
