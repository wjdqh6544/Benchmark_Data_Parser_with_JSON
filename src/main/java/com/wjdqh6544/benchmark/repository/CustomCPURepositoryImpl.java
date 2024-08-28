package com.wjdqh6544.benchmark.repository;

import com.wjdqh6544.benchmark.entity.CPU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/*
BLOG_Benchmark_Data_Parser_with_JSON
- repository/CustomCPURepositoryImpl: a repository class that save the data to "cpu" table of Database. (Embodiment, Using JPARepository, Custom.)
*/
@Repository
@Primary
public class CustomCPURepositoryImpl implements CustomCPURepository {
    @Autowired
    private CPURepository cpuRepository;

    @Override
    public boolean saveAllWithEdit(List<CPU> saveEntitiesList){
        try {
            for (CPU productObj : saveEntitiesList) {
                Optional<CPU> existingEachEntityOptional = cpuRepository.findByProductName(productObj.getProductName());
                if (existingEachEntityOptional.isPresent()) {
                    CPU existingEachEntity = existingEachEntityOptional.get();
                    if (productObj.getCinebench_R23_MT() != null) {
                        existingEachEntity.setCinebench_R23_MT(productObj.getCinebench_R23_MT());
                    }
                    if (productObj.getCinebench_R23_ST() != null) {
                        existingEachEntity.setCinebench_R23_ST(productObj.getCinebench_R23_ST());
                    }
                    cpuRepository.save(existingEachEntity);
                } else {
                    cpuRepository.save(productObj);
                }
            }
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
