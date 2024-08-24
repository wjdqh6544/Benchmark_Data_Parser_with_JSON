package com.wjdqh6544.benchmark.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wjdqh6544.benchmark.dto.CrawlerPageDto;
import com.wjdqh6544.benchmark.service.IOService;
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
public class CrawlingController {
    private final IOService IOService;
    private final Gson gsonObj = new GsonBuilder().serializeNulls().create();;

    @GetMapping()
    public String getInitialPage(Model model) {
        CrawlerPageDto crawlerPageDto = new CrawlerPageDto();
        model.addAttribute("crawlerPageDto", crawlerPageDto);
        model.addAttribute("crawlerPageDtoJSON", gsonObj.toJson(crawlerPageDto));
        return "crawling.html";
    }

    @PostMapping()
    @RequestMapping(produces = "application/json; charset=utf8")
    public String getCrawlData(@RequestBody CrawlerPageDto crawlerPageDto, Model model) {
        try {
            CrawlerPageDto resDto = IOService.getCrawledData(crawlerPageDto);
            model.addAttribute("crawlerPageDto", resDto);
            model.addAttribute("crawlerPageDtoJSON", gsonObj.toJson(crawlerPageDto));
            return "crawling.html";
        } catch (IOException e) {
            return "Requested URL is invalid.";
        }
    }

    @PostMapping("/saveToDB")
    @RequestMapping(value = "/saveToDB", produces = "application/json; charset=utf8")
    public String saveDataToDB(@RequestBody CrawlerPageDto crawlerPageDto, Model model){
        CrawlerPageDto resDto = IOService.saveDataToDB(crawlerPageDto);
        model.addAttribute("crawlerPageDto", resDto);
        model.addAttribute("crawlerPageDtoJSON", gsonObj.toJson(resDto));
        return "crawling.html";
    }
}
