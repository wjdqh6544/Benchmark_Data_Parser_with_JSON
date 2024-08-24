package com.wjdqh6544.benchmark.repository;

import com.wjdqh6544.benchmark.entity.CPU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
BLOG_Benchmark_Data_Parser_with_JSON
- repository/CPURepository: a repository interface that gets the data from "cpu" table of Database. (Using JPARepository.)
*/
@Repository
public interface CPURepository extends JpaRepository<CPU, Long> {
    Optional<CPU> findByProductName(String productName);
}
