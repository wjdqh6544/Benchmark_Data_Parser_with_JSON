package com.wjdqh6544.benchmark.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
BLOG_Benchmark_Data_Parser_with_JSON
- entity/GPU: an entity class that connects with "GPU" table of Database
*/
@Entity
@RequiredArgsConstructor
@Getter
public class GPU {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String gpuName;

    @Column(name = "timespy")
    private Integer timeSpy;
}
