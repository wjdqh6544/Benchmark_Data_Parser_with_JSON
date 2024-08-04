package com.wjdqh6544.benchmark.service;

import com.wjdqh6544.benchmark.exception.NotFoundException;
import com.wjdqh6544.benchmark.vo.GetEachResultVo;
import com.wjdqh6544.benchmark.vo.GetResultListVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
BLOG_Benchmark_Data_Parser_with_JSON
- service/absService: an abstract service class that contains method(s) commonly used in many concrete classes.
*/
public abstract class absService {
    GetResultListVo getReturnVo(String productType, List<String> productList, Map<String, Integer> filterList){
        List<GetEachResultVo> finalList = new ArrayList<>();
        for (String productName : productList) {
            if (filterList.get(productName.toLowerCase()) != null){
                GetEachResultVo eachRes = GetEachResultVo.builder()
                        .productName(productName)
                        .score(filterList.get(productName))
                        .build();
                finalList.add(eachRes);
            } else {
                finalList.add(NotFoundException.productNotFound(productType, productName));
            }
        }
        return GetResultListVo.builder()
                .resultVoList(finalList)
                .build();
    }
}
