package com.wjdqh6544.benchmark.vo;

import lombok.Builder;
import lombok.Getter;

/*
BLOG_Benchmark_Data_Parser_with_JSON
- vo/EachResult: a vo class that contains a score of one product. (Will be work like one JSON Block)
- Using when Result data is return through Controller from Service.
*/
@Getter
public class GetEachResultVo {
    private String productName;
    private int score;

    @Builder
    public GetEachResultVo(String productName, int score){
        this.productName = productName;
        this.score = score;
    }
}
