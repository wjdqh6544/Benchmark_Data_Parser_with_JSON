package com.wjdqh6544.benchmark.service;

import com.wjdqh6544.benchmark.dto.CrawlerPageDto;
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

    public CrawlerPageDto saveDataToDB(CrawlerPageDto crawlerPageDto) {
        switch (crawlerPageDto.getProductType()) {
            case "CPU" -> {
                crawlerPageDto.setSaveSuccessfully(cpuService.saveCPUData(crawlerPageDto.getBenchmarkPlatform(),
                        crawlerPageDto.getSelectedBench(), crawlerPageDto.getCrawledData()));
            }
            case "GPU" -> {
                crawlerPageDto.setSaveSuccessfully(gpuService.saveGPUData(crawlerPageDto.getBenchmarkPlatform(),
                        crawlerPageDto.getSelectedBench(), crawlerPageDto.getCrawledData()));
            }
            default -> {
                crawlerPageDto.setSaveSuccessfully(false);
            }
        }
        return crawlerPageDto;
    }

    public CrawlerPageDto getCrawledData(CrawlerPageDto crawlerPageDto) throws IOException {
        crawlerPageDto.setHostIP(InetAddress.getLocalHost().getHostAddress());
        crawlerPageDto.setPort(environment.getProperty("local.server.port"));
        crawlerPageDto.setSources(crawlingService.getSources(crawlerPageDto.getURL()));
        crawlerPageDto.setSavedStatus(getSavedBenchmarkList());
        crawlerPageDto.setCrawledData(crawlingService.selectSources(crawlerPageDto));
        if (crawlerPageDto.getCrawledData() == null){
            crawlerPageDto.setNumOfBench(-1);
        } else {
            crawlerPageDto.setNumOfBench(crawlerPageDto.getCrawledData().size());
        }
        return crawlerPageDto;
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
