package com.wjdqh6544.benchmark.repository;

import com.wjdqh6544.benchmark.entity.GPU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*
BLOG_Benchmark_Data_Parser_with_JSON
- repository/CustomGPURepositoryImpl: a repository class that save the data to "gpu" table of Database. (Embodiment, Using JPARepository, Custom.)
*/
@Repository
@Primary
public class CustomGPURepositoryImpl implements CustomGPURepository{
    @Autowired
    private GPURepository gpuRepository;

    @Override
    public boolean saveAllWithEdit(List<GPU> saveEntitiesList) {
        try {
            for (GPU productObj : saveEntitiesList) {
                Optional<GPU> existingEachEntityOptional = gpuRepository.findByProductName(productObj.getProductName());
                if (existingEachEntityOptional.isPresent()) {
                    GPU existingEachEntity = existingEachEntityOptional.get();
                    if (existingEachEntity.get_3DMark_Time_Spy() != null) {
                        existingEachEntity.set_3DMark_Time_Spy(productObj.get_3DMark_Time_Spy());
                    }
                    gpuRepository.save(existingEachEntity);
                } else {
                    gpuRepository.save(productObj);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
