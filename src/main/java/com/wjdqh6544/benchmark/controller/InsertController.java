package com.wjdqh6544.benchmark.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wjdqh6544.benchmark.dto.CrawlerPageDto;
import com.wjdqh6544.benchmark.service.InsertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.IOException;

/*
BLOG_Benchmark_Data_Parser_with_JSON
- controller/PageController: a controller class that handle HTML Page with Thymeleaf.
*/
@Controller
@RequestMapping("/crawling")
@RequiredArgsConstructor
public class InsertController {
    private final InsertService insertService;

    @GetMapping()
    public String getInitialPage(Model model) {
        Gson gsonObj = new GsonBuilder().serializeNulls().create();
        CrawlerPageDto crawlerPageDto = new CrawlerPageDto();
        model.addAttribute("crawlerPageDto", crawlerPageDto);
        model.addAttribute("crawlerPageDtoJSON", gsonObj.toJson(crawlerPageDto));
        return "crawling.html";
    }

    @PostMapping()
    @RequestMapping(produces = "application/json; charset=utf8")
    public String getCrawlData(@RequestBody CrawlerPageDto crawlerPageDto, Model model) {
        try {
            Gson gsonObj = new GsonBuilder().serializeNulls().create();
            CrawlerPageDto resDto = insertService.getCrawledData(crawlerPageDto);
            resDto.setSavedStatus(insertService.getSavedBenchmarkList());
            model.addAttribute("crawlerPageDto", resDto);
            model.addAttribute("crawlerPageDtoJSON", gsonObj.toJson(crawlerPageDto));
            return "crawling.html";
        } catch (IOException e) {
            return "Requested URL is invalid.";
        }
    }
}
