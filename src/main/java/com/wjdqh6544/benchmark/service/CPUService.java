package com.wjdqh6544.benchmark.service;

import com.wjdqh6544.benchmark.dto.RequestDto;
import com.wjdqh6544.benchmark.entity.CPU;
import com.wjdqh6544.benchmark.exception.NotFoundException;
import com.wjdqh6544.benchmark.repository.CPURepository;
import com.wjdqh6544.benchmark.repository.CustomCPURepository;
import com.wjdqh6544.benchmark.vo.GetResultListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.*;

/*
BLOG_Benchmark_Data_Parser_with_JSON
- service/CPUService: a service class that processes the raw data given by cpuRepository.
*/
@Service
@RequiredArgsConstructor
public class CPUService extends absService {
    private final CPURepository cpuRepository;
    private final CustomCPURepository customCPURepository;

    boolean saveCPUData(String benchPlatform, int selectedBench, List<LinkedHashMap<String, Integer>> crawledData) {
        List<CPU> saveResList = new ArrayList<>();
        CPU eachCPU;
        switch (benchPlatform) {
            case "Cinebench_R23_MT" -> {
                LinkedHashMap<String, Integer> savedDataMap = crawledData.get(selectedBench);
                for (String productName : savedDataMap.keySet()){
                    eachCPU = CPU.builder().productName(productName).cinebench_R23_MT(savedDataMap.get(productName)).build();
                    saveResList.add(eachCPU);
                }
            }
            case "Cinebench_R23_ST" -> {
                LinkedHashMap<String, Integer> savedDataMap = crawledData.get(selectedBench);
                for (String productName : savedDataMap.keySet()){
                    eachCPU = CPU.builder().productName(productName).cinebench_R23_ST(savedDataMap.get(productName)).build();
                    saveResList.add(eachCPU);
                }
            }
            default -> {
                return false;
            }
        }
        return customCPURepository.saveAllWithEdit(saveResList);
    }

    public GetResultListVo getCPUBenchList(RequestDto requestDto){
        if (requestDto.getBenchmark() == null || requestDto.getBenchmark().isEmpty()){
            return NotFoundException.benchmarkIsEmpty();
        } else if (requestDto.getProductList() == null || requestDto.getProductList().isEmpty()) {
            return NotFoundException.productListIsEmpty("CPU");
        }
        List<CPU> rawList = cpuRepository.findAll(Sort.by(Sort.Direction.ASC, "productName"));
        Map<String, String[]> filterList = new HashMap<>();
        switch (requestDto.getBenchmark().toLowerCase()) {
            case "cinebench_r23_mt" -> {
                for (CPU obj : rawList) {
                    if (obj.getCinebench_R23_MT() != null){
                        filterList.put(obj.getProductName().replace(" ", "").toLowerCase(),
                                new String[]{obj.getProductName(), obj.getCinebench_R23_MT().toString()});
                    }
                }
        }
            case "cinebench_r23_st" -> {
                for (CPU obj : rawList) {
                    if (obj.getCinebench_R23_ST() != null) {
                        filterList.put(obj.getProductName().replace(" ", "").toLowerCase(),
                                new String[]{obj.getProductName(), obj.getCinebench_R23_ST().toString()});
                    }
                }
            }
            default -> {
                return NotFoundException.benchmarkNotFound(requestDto.getBenchmark());
            }
        }
        return getReturnVo("CPU", requestDto.getProductList(), filterList);
    }
}
