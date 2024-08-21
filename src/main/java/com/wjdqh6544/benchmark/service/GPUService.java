package com.wjdqh6544.benchmark.service;

import com.wjdqh6544.benchmark.dto.RequestDto;
import com.wjdqh6544.benchmark.entity.GPU;
import com.wjdqh6544.benchmark.exception.NotFoundException;
import com.wjdqh6544.benchmark.repository.GPURepository;
import com.wjdqh6544.benchmark.vo.GetResultListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
BLOG_Benchmark_Data_Parser_with_JSON
- service/GPUService: a service class that processes the raw data given by gpuRepository.
*/
@Service
@RequiredArgsConstructor
public class GPUService extends absService {
    private final GPURepository gpuRepository;

    public GetResultListVo getGPUBenchList(RequestDto requestDto){
        if (requestDto.getBenchmark() == null || requestDto.getBenchmark().isEmpty()){
            return NotFoundException.benchmarkIsEmpty();
        } else if (requestDto.getProductList() == null || requestDto.getProductList().isEmpty()) {
            return NotFoundException.productListIsEmpty("GPU");
        }
        List<GPU> rawList = gpuRepository.findAll(Sort.by(Sort.Direction.ASC, "productName"));
        Map<String, String[]> filterList = new HashMap<>();
        switch (requestDto.getBenchmark().toLowerCase()) {
            case "_3dmark_time_spy" -> {
                for (GPU obj : rawList) {
                    if(obj.get_3DMark_Time_Spy() != null){
                        filterList.put(obj.getProductName().replace(" ", "").toLowerCase(),
                                new String[]{obj.getProductName(), obj.get_3DMark_Time_Spy().toString()});
                    }
                }
            }
            default -> {
                return NotFoundException.benchmarkNotFound(requestDto.getBenchmark());
            }
        }
        return getReturnVo("GPU", requestDto.getProductList(), filterList);
    }
}
