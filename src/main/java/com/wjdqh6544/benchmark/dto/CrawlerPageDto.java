package com.wjdqh6544.benchmark.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.HashMap;
import java.util.List;

/*
BLOG_Benchmark_Data_Parser_with_JSON
- dto/CrawlerPageDto: a DTO class that transfers Benchmark Information to Crawler Page (Thymeleaf).
*/
@Getter @Setter
public class CrawlerPageDto {
    private String productType;
    private String benchmarkPlatform;
    private Integer numOfBench;
    private HashMap<String, List<String>> savedStatus; // Key - productType, List - Platform

    @Builder
    public CrawlerPageDto(String productType, String benchmarkPlatform, Integer numOfBench, HashMap<String, List<String>> savedBenchmarkList){
        this.productType = productType;
        this.benchmarkPlatform = benchmarkPlatform;
        this.numOfBench = numOfBench;
        this.savedStatus = savedBenchmarkList;
    }
}
