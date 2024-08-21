package com.wjdqh6544.benchmark.entity;

import jakarta.persistence.*;
import lombok.Builder;
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

    @Column(name = "product_name", nullable = false, unique = true)
    private String productName;

    @Column(name = "timespy")
    private Integer _3DMark_Time_Spy;

    @Builder
    public GPU(String productName, Integer _3DMark_Time_Spy) {
        this.productName = productName;
        this._3DMark_Time_Spy = _3DMark_Time_Spy;
    }
}
