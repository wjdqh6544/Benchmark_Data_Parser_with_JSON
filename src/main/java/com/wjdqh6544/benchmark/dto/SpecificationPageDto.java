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
- dto/SpecificationPageDto: a DTO class that transfers Benchmark Information to Crawler Page (Thymeleaf).
*/
@Getter @Setter
public class SpecificationPageDto {
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
    @JsonProperty("numOfTable")
    private Integer numOfTable;
    @JsonProperty("selectedTable")
    private Integer selectedTable;
    @JsonProperty("crawledData")
    private List<LinkedHashMap<String, Integer>> crawledData;
    @JsonProperty("savedStatus")
    private HashMap<String, List<String>> savedStatus; // in DB | Key - productType, List - Platform
    @JsonProperty("saveSuccessfully")
    private boolean saveSuccessfully;

    public SpecificationPageDto(){
        this.setHostIP(null);
        this.setPort(null);
        this.setURL(null);
        this.setSources(null);
        this.setProductType(null);
        this.setNumOfTable(0);
        this.setSelectedTable(0);
    }

    @Builder
    public SpecificationPageDto(String hostIP, String port, String URL, String sources, String productType, Integer numOfTable, Integer selectedTable) {
        this.setHostIP(hostIP);
        this.setPort(port);
        this.setURL(URL);
        this.setSources(sources);
        this.setProductType(productType);
        this.setNumOfTable(numOfTable);
        this.setSelectedTable(selectedTable);
    }

    @Override
    public String toString(){
        return "hostIP: " + this.getHostIP() + "\nport: " + this.getPort() +
                "\nURL: " + this.getURL() + "\nsources: " + this.getSources() + "\nproductType: " + this.getProductType() +
                "\nnumOfTable: " + this.getNumOfTable() + "\nselectedTable: " + this.getSelectedTable();
    }
}
