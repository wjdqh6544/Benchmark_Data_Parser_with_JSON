package com.wjdqh6544.benchmark.vo;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

/*
BLOG_Benchmark_Data_Parser_with_JSON
- vo/ResultList: a vo class that contains JSON Block from "EachResult" class more than one.
- Using when Result data is return through Controller from Service.
*/
@Getter
public class GetResultListVo {
    private List<GetEachResultVo> resultVoList;

    @Builder
    public GetResultListVo(List<GetEachResultVo> resultVoList){
        this.resultVoList = resultVoList;
    }
}
