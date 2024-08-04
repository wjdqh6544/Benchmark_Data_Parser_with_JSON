package com.wjdqh6544.benchmark.service;

import com.wjdqh6544.benchmark.dto.RequestDto;
import com.wjdqh6544.benchmark.entity.CPU;
import com.wjdqh6544.benchmark.exception.NotFoundException;
import com.wjdqh6544.benchmark.repository.CPURepository;
import com.wjdqh6544.benchmark.vo.GetResultListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
BLOG_Benchmark_Data_Parser_with_JSON
- service/CPUService: a service class that processes the raw data given by cpuRepository.
*/
@Service
@RequiredArgsConstructor
public class CPUService extends absService {

    private final CPURepository cpuRepository;

    public GetResultListVo getCPUBenchList(RequestDto requestDto){
        if (requestDto.getBenchmark() == null || requestDto.getBenchmark().isEmpty()){
            return NotFoundException.benchmarkIsEmpty();
        } else if (requestDto.getProductList() == null || requestDto.getProductList().isEmpty()) {
            return NotFoundException.productListIsEmpty("CPU");
        }
        List<CPU> rawList = cpuRepository.findAll(Sort.by(Sort.Direction.ASC, "cpuName"));
        Map<String, Integer> filterList = new HashMap<>();
        switch (requestDto.getBenchmark().toLowerCase()) {
            case "cinebenchr23mt" -> {
                for (CPU obj : rawList) {
                    filterList.put(obj.getCpuName(), obj.getCinebenchR23MT());
                }
            }
            case "cinebenchr23st" -> {
                for (CPU obj : rawList) {
                    filterList.put(obj.getCpuName(), obj.getCinebenchR23ST());
                }
            }
            default -> {
                return NotFoundException.benchmarkNotFound(requestDto.getBenchmark());
            }
        }
        return getReturnVo("CPU", requestDto.getProductList(), filterList);
    }
}
