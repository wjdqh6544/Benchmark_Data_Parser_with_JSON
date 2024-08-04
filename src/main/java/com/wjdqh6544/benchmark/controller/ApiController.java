package com.wjdqh6544.benchmark.controller;

import com.wjdqh6544.benchmark.dto.RequestDto;
import com.wjdqh6544.benchmark.service.CPUService;
import com.wjdqh6544.benchmark.vo.GetResultListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

/*
BLOG_Benchmark_Data_Parser_with_JSON
- controller/ApiController: a controller class that throws JSON Data (result) in response to invoked URL.
*/
@RestController
@RequiredArgsConstructor
public class ApiController {

    private final CPUService cpuService;

    @GetMapping("/CPU")
    public GetResultListVo getCPUResultList(@ModelAttribute RequestDto requestDto){
        return cpuService.getCPUBenchList(requestDto);
    }
}
