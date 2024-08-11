package com.wjdqh6544.benchmark.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
BLOG_Benchmark_Data_Parser_with_JSON
- entity/CPU: an entity class that connects with "CPU" table of Database
*/
@Entity
@Getter
@RequiredArgsConstructor
public class CPU {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "product_name", nullable = false, unique = true)
    private String productName;

    @Column(name = "cinebench_r23_mt")
    private Integer Cinebench_R23_MT;

    @Column(name = "cinebench_r23_st")
    private Integer Cinebench_R23_ST;
}
