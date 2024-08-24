package com.wjdqh6544.benchmark.repository;

import com.wjdqh6544.benchmark.entity.GPU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
BLOG_Benchmark_Data_Parser_with_JSON
- repository/GPURepository: a repository interface that gets the data from "gpu" table of Database. (Using JPARepository.)
*/
@Repository
public interface GPURepository extends JpaRepository<GPU, Long> {
    Optional<GPU> findByProductName(String productName);
}
