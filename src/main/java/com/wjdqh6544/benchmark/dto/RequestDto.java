package com.wjdqh6544.benchmark.dto;

import lombok.Getter;

import java.beans.ConstructorProperties;
import java.util.List;

/*
BLOG_Benchmark_Data_Parser_with_JSON
- dto/RequestDto: a DTO class that transfers request parameter to service class.
*/
@Getter
public class RequestDto {
    private String benchmark;
    private List<String> productList;

    @ConstructorProperties({"benchmark", "name"})
    public RequestDto(String benchmark, List<String> productList){
        if (benchmark != null){
            this.benchmark = benchmark.replace(" ", "");
        } else {
            this.benchmark = null;
        }
        this.productList = productList;
    }
}
