package com.wjdqh6544.benchmark.service;

import com.wjdqh6544.benchmark.dto.BenchmarkPageDto;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.metamodel.Attribute;
import jakarta.persistence.metamodel.EntityType;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.InetAddress;
import java.util.*;
import java.util.stream.Collectors;

/*
BLOG_Benchmark_Data_Parser_with_JSON
- service/InsertService: a service class that return adequate information using CrawlerPageDto.
*/
@Service
@RequiredArgsConstructor
public class IOService {
    private final EntityManagerFactory emf;
    private final CrawlingService crawlingService;
    private final Environment environment;
    private final CPUService cpuService;
    private final GPUService gpuService;

    public BenchmarkPageDto saveDataToDB(BenchmarkPageDto benchmarkPageDto) {
        switch (benchmarkPageDto.getProductType()) {
            case "CPU" -> {
                benchmarkPageDto.setSaveSuccessfully(cpuService.saveCPUData(benchmarkPageDto.getBenchmarkPlatform(),
                        benchmarkPageDto.getSelectedBench(), benchmarkPageDto.getCrawledData()));
            }
            case "GPU" -> {
                benchmarkPageDto.setSaveSuccessfully(gpuService.saveGPUData(benchmarkPageDto.getBenchmarkPlatform(),
                        benchmarkPageDto.getSelectedBench(), benchmarkPageDto.getCrawledData()));
            }
            default -> {
                benchmarkPageDto.setSaveSuccessfully(false);
            }
        }
        return benchmarkPageDto;
    }

    public BenchmarkPageDto getCrawledData(BenchmarkPageDto benchmarkPageDto) throws IOException {
        benchmarkPageDto.setHostIP(InetAddress.getLocalHost().getHostAddress());
        benchmarkPageDto.setPort(environment.getProperty("local.server.port"));
        benchmarkPageDto.setSources(crawlingService.getSources(benchmarkPageDto.getURL()));
        benchmarkPageDto.setSavedStatus(getSavedBenchmarkList());
        benchmarkPageDto.setCrawledData(crawlingService.selectSources(benchmarkPageDto));
        if (benchmarkPageDto.getCrawledData() == null){
            benchmarkPageDto.setNumOfBench(-1);
        } else {
            benchmarkPageDto.setNumOfBench(benchmarkPageDto.getCrawledData().size());
        }
        return benchmarkPageDto;
    }

    private HashMap<String, List<String>> getSavedBenchmarkList(){
        Set<EntityType<?>> entitySet = emf.getMetamodel().getEntities();
        HashMap<String, List<String>> savedBenchmarkList = new HashMap<>();
        for (EntityType<?> entityType : entitySet) {
            List<String> tmpList = entityType.getAttributes().stream()
                    .map(Attribute::getName).collect(Collectors.toList());
            tmpList.remove("id");
            tmpList.remove("productName");
            savedBenchmarkList.put(entityType.getName(), tmpList);
        }
        return savedBenchmarkList;
    }
}
