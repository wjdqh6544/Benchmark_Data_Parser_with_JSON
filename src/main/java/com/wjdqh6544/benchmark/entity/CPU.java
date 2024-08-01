package com.wjdqh6544.benchmark.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
BLOG_Benchmark_Data_Parser_with_JSON
- entity/CPU: an entity class that connect with "CPU" table of Database
*/
@Entity
@Getter
@RequiredArgsConstructor
public class CPU {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String cpuName;

    @Column(name = "cinebench_r23_mt")
    private Integer cinebenchR23MT;

    @Column(name = "cinebench_r23_st")
    private Integer cinebenchR23ST;
}
