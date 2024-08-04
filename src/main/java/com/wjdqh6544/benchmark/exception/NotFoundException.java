package com.wjdqh6544.benchmark.exception;

import com.wjdqh6544.benchmark.vo.GetEachResultVo;
import com.wjdqh6544.benchmark.vo.GetResultListVo;

import java.util.ArrayList;
import java.util.List;

/*
BLOG_Benchmark_Data_Parser_with_JSON
- exception/NotFoundException: an exception class which invoked when something (e.g. product, benchmark data, and so on) does not exist.
*/
public class NotFoundException {
    private static GetEachResultVo eachResult;
    private static ArrayList<GetEachResultVo> resList = new ArrayList<>();
    public static GetResultListVo productListIsEmpty(String productType){
        eachResult = GetEachResultVo.builder()
                .productName("400 Bad Request. / List of requested" + productType + " is empty.")
                .score(-1)
                .build();
        resList.add(eachResult);
        return GetResultListVo.builder()
                .resultVoList(resList)
                .build();
    }
    public static GetResultListVo benchmarkNotFound(String benchName){
        eachResult = GetEachResultVo.builder()
                .productName("404 Not Found. / " + benchName + " Not Found.")
                .score(-1)
                .build();
        resList.add(eachResult);
        return GetResultListVo.builder()
                .resultVoList(resList)
                .build();
    }

    public static GetEachResultVo productNotFound(String productType, String productName) {
        return GetEachResultVo.builder()
                .productName("404 Not Found. / " + productType + " - " + productName)
                .score(-1)
                .build();
    }
}