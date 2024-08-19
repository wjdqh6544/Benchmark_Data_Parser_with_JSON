package com.wjdqh6544.benchmark.service;

import com.wjdqh6544.benchmark.dto.CrawlerPageDto;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.metamodel.Attribute;
import jakarta.persistence.metamodel.EntityType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
BLOG_Benchmark_Data_Parser_with_JSON
- service/InsertService: a service class that return adequate information using CrawlerPageDto.
*/
@Service
@RequiredArgsConstructor
public class InsertService {
    private final EntityManagerFactory emf;
    private final CrawlingService crawlingService;

    public CrawlerPageDto getCrawledData(CrawlerPageDto crawlerPageDto) throws IOException {
        crawlerPageDto.setSources(crawlingService.getSources(crawlerPageDto.getURL()));
        crawlerPageDto.setCrawledData(crawlingService.selectSources(crawlerPageDto));
        if (crawlerPageDto.getCrawledData() == null){
            crawlerPageDto.setNumOfBench(-1);
        } else {
            crawlerPageDto.setNumOfBench(crawlerPageDto.getCrawledData().size());
        }
        return crawlerPageDto;
    }

    public HashMap<String, List<String>> getSavedBenchmarkList(){
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
