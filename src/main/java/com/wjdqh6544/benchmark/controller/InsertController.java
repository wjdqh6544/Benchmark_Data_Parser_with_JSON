package com.wjdqh6544.benchmark.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.wjdqh6544.benchmark.dto.CrawlerPageDto;
import com.wjdqh6544.benchmark.service.InsertService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
BLOG_Benchmark_Data_Parser_with_JSON
- controller/PageController: a controller class that handle HTML Page with Thymeleaf.
*/
@Controller
@RequestMapping("/insert")
@RequiredArgsConstructor
public class InsertController {
    @Autowired
    private InsertService insertService;

    @GetMapping()
    public String crawlingPage(Model model) throws JsonProcessingException {
        Gson gsonObj = new Gson();
        CrawlerPageDto chooseOptions = insertService.getSavedBenchmarkList();
        model.addAttribute("chooseOptions", chooseOptions);
        model.addAttribute("chooseOptionsJSON", gsonObj.toJson(chooseOptions.getSavedStatus()));
        return "insert.html";
    }
}
