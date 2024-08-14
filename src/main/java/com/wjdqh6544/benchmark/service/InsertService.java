package com.wjdqh6544.benchmark.service;

import com.wjdqh6544.benchmark.dto.CrawlerPageDto;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.metamodel.Attribute;
import jakarta.persistence.metamodel.EntityType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InsertService {
    @Autowired
    private EntityManagerFactory emf;

    public CrawlerPageDto getSavedBenchmarkList(){
        Set<EntityType<?>> entitySet = emf.getMetamodel().getEntities();
        HashMap<String, List<String>> savedBenchmarkList = new HashMap<>();
        for (EntityType<?> entityType : entitySet) {
            List<String> tmpList = entityType.getAttributes().stream()
                    .map(Attribute::getName).collect(Collectors.toList());
            tmpList.remove("id");
            tmpList.remove("productName");
            savedBenchmarkList.put(entityType.getName(), tmpList);
        }
        return CrawlerPageDto.builder()
                .productType(null)
                .benchmarkPlatform(null)
                .numOfBench(2)
                .savedBenchmarkList(savedBenchmarkList)
                .build();
    }
}
