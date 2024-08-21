package com.wjdqh6544.benchmark.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
BLOG_Benchmark_Data_Parser_with_JSON
- entity/CPU: an entity class that connects with "CPU" table of Database
*/
@Entity
@Getter
@NoArgsConstructor
public class CPU {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "product_name", nullable = false, unique = true)
    private String productName;

    @Column(name = "cinebench_r23_mt")
    private Integer Cinebench_R23_MT;

    @Column(name = "cinebench_r23_st")
    private Integer Cinebench_R23_ST;

    @Builder
    public CPU(String productName, Integer cinebench_R23_MT, Integer cinebench_R23_ST) {
        this.productName = productName;
        this.Cinebench_R23_MT = cinebench_R23_MT;
        this.Cinebench_R23_ST = cinebench_R23_ST;
    }

    @Override
    public boolean equals(Object otherObj) {
        if (otherObj == null){
            return false;
        } else if (!(otherObj instanceof CPU)) {
            return false;
        } else {
            CPU otherCPUObj = (CPU) otherObj;
            return (this.getProductName().equals(otherCPUObj.getProductName()) && this.getCinebench_R23_MT().equals(otherCPUObj.getCinebench_R23_MT()) &&
                    this.getCinebench_R23_ST().equals(otherCPUObj.getCinebench_R23_ST()));
        }
    }

    @Override
    public String toString(){
        return "productName: " + this.getProductName() +
                "\nCineBench_R23_MT: " + this.getCinebench_R23_MT() +
                "\nCineBench_R23_ST: " + this.getCinebench_R23_ST();
    }
}
