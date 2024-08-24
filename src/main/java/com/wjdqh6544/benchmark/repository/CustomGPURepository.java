package com.wjdqh6544.benchmark.repository;

import com.wjdqh6544.benchmark.entity.GPU;
import java.util.List;

/*
BLOG_Benchmark_Data_Parser_with_JSON
- repository/CustomGPURepository: a repository interface that save the data to "gpu" table of Database. (Using JPARepository, Custom.)
*/
public interface CustomGPURepository {
    boolean saveAllWithEdit(List<GPU> saveEntitiesList);
}
