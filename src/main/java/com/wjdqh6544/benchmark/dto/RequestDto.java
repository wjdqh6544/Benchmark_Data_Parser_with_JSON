package com.wjdqh6544.benchmark.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/*
BLOG_Benchmark_Data_Parser_with_JSON
- dto/RequestDto: a DTO class that transfers request parameter to service class.
*/
@Getter
@AllArgsConstructor
public class RequestDto {
    private String benchmark;
    private List<String> productList;
}
