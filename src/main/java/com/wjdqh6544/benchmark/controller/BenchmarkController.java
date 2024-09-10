package com.wjdqh6544.benchmark.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wjdqh6544.benchmark.dto.BenchmarkPageDto;
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
- controller/PageController: a controller class that handle HTML Page (Saving benchmark information of product) with Thymeleaf.
*/
@Controller
@RequestMapping("/benchmark")
@RequiredArgsConstructor
public class BenchmarkController {
    private final IOService IOService;
    private final Gson gsonObj = new GsonBuilder().serializeNulls().create();;

    @GetMapping()
    public String getInitialPage(Model model) {
        BenchmarkPageDto benchmarkPageDto = new BenchmarkPageDto();
        model.addAttribute("benchmarkPageDto", benchmarkPageDto);
        model.addAttribute("benchmarkPageDtoJSON", gsonObj.toJson(benchmarkPageDto));
        return "benchmark.html";

    }

    @PostMapping()
    @RequestMapping(produces = "application/json; charset=utf8")
    public String getCrawlData(@RequestBody BenchmarkPageDto benchmarkPageDto, Model model) {
        try {
            BenchmarkPageDto resDto = IOService.getCrawledData(benchmarkPageDto);
            model.addAttribute("benchmarkPageDto", resDto);
            model.addAttribute("benchmarkPageDtoJSON", gsonObj.toJson(benchmarkPageDto));
            return "benchmark.html";
        } catch (IOException e) {
            return "Requested URL is invalid.";
        }
    }

    @PostMapping("/saveToDB")
    @RequestMapping(value = "/saveToDB", produces = "application/json; charset=utf8")
    public String saveDataToDB(@RequestBody BenchmarkPageDto benchmarkPageDto, Model model){
        BenchmarkPageDto resDto = IOService.saveDataToDB(benchmarkPageDto);
        model.addAttribute("benchmarkPageDto", resDto);
        model.addAttribute("benchmarkPageDtoJSON", gsonObj.toJson(resDto));
        return "benchmark.html";
    }
}
