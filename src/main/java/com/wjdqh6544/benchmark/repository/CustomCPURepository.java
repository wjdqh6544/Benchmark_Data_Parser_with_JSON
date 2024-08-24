package com.wjdqh6544.benchmark.repository;

import com.wjdqh6544.benchmark.entity.CPU;
import java.util.List;

/*
BLOG_Benchmark_Data_Parser_with_JSON
- repository/CustomCPURepository: a repository interface that save the data to "cpu" table of Database. (Using JPARepository, Custom.)
*/
public interface CustomCPURepository {
    boolean saveAllWithEdit(List<CPU> saveEntitiesList);
}
