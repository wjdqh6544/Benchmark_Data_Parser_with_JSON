package com.wjdqh6544.benchmark.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/*
BLOG_Benchmark_Data_Parser_with_JSON
- dto/CrawlerPageDto: a DTO class that transfers Benchmark Information to Crawler Page (Thymeleaf).
*/
@Getter @Setter
public class BenchmarkPageDto {
    @JsonProperty("hostIP")
    private String hostIP;
    @JsonProperty("port")
    private String port;
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
    @JsonProperty("selectedBench")
    private Integer selectedBench;
    @JsonProperty("crawledData")
    private List<LinkedHashMap<String, Integer>> crawledData;
    @JsonProperty("savedStatus")
    private HashMap<String, List<String>> savedStatus; // in DB | Key - productType, List - Platform
    @JsonProperty("saveSuccessfully")
    private boolean saveSuccessfully;

    public BenchmarkPageDto() {
        this.setHostIP(null);
        this.setPort(null);
        this.setURL(null);
        this.setSources(null);
        this.setProductType(null);
        this.setBenchmarkPlatform(null);
        this.setNumOfBench(null);
        this.setCrawledData(null);
        this.setSavedStatus(null);
        this.setSaveSuccessfully(false);
    }

    @Builder
    public BenchmarkPageDto(String hostIP, String port, String URL, String sources, String productType, String benchmarkPlatform, Integer numOfBench,
                            List<LinkedHashMap<String, Integer>> crawledData, HashMap<String, List<String>> savedStatus, boolean status){
        this.setHostIP(hostIP);
        this.setPort(port);
        this.setURL(URL);
        this.setSources(sources);
        this.setProductType(productType);
        this.setBenchmarkPlatform(benchmarkPlatform);
        this.setNumOfBench(numOfBench);
        this.setCrawledData(crawledData);
        this.setSavedStatus(savedStatus);
        this.setSaveSuccessfully(status);
    }
    @Override
    public String toString() {
        return "hostIP: " + this.getHostIP() + "\nport: " + this.getPort() +
                "\nURL: " + this.getURL() + "\nsources: " + this.getSources() + "\nproductType: " + this.getProductType() +
                "\nbenchmarkPlatform: " + this.getBenchmarkPlatform() + "\nnumOfBench: " + this.getNumOfBench() +
                "\ncrawledData: " + this.getCrawledData() + "\nsavedStatus: " + this.getSavedStatus() + "\nsavedSuccessfully: " + this.isSaveSuccessfully();
    }
}
