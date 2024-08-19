package com.wjdqh6544.benchmark.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("URL")
    private String URL;
    @JsonProperty("sources")
    private String sources;
    @JsonProperty("productType")
    private String productType;
    @JsonProperty("benchmarkPlatform")
    private String benchmarkPlatform;
    @JsonProperty("numOfBench")
    private Integer numOfBench;
    @JsonProperty("crawledData")
    private List<HashMap<String, Integer>> crawledData;
    @JsonProperty("savedStatus")
    private HashMap<String, List<String>> savedStatus; // in DB | Key - productType, List - Platform

    public CrawlerPageDto() {
        this.URL = null;
        this.sources = null;
        this.productType = null;
        this.benchmarkPlatform = null;
        this.numOfBench = null;
        this.crawledData = null;
        this.savedStatus = null;
    }

    @Builder
    public CrawlerPageDto(String URL, String sources, String productType, String benchmarkPlatform, Integer numOfBench,
                          List<HashMap<String, Integer>> crawledData, HashMap<String, List<String>> savedStatus){
        this.URL = URL;
        this.sources = sources;
        this.productType = productType;
        this.benchmarkPlatform = benchmarkPlatform;
        this.numOfBench = numOfBench;
        this.crawledData = crawledData;
        this.savedStatus = savedStatus;
    }
    @Override
    public String toString() {
        return "URL: " + this.getURL() + "\nsources: " + this.getSources() + "\nproductType: " + this.getProductType() +
                "\nbenchmarkPlatform: " + this.getBenchmarkPlatform() + "\nnumOfBench: " + this.getNumOfBench() +
                "\ncrawledData: " + this.getCrawledData() + "\nsavedStatus: " + this.getSavedStatus();
    }
}
