package com.wjdqh6544.benchmark.controller;

import com.wjdqh6544.benchmark.dto.SpecificationPageDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
BLOG_Benchmark_Data_Parser_with_JSON
- controller/SpecificationController: a controller class that handle HTML Page (Saving specification of product) with Thymeleaf.
*/
@Controller
@RequestMapping("/specification")
public class SpecificationController {
    @GetMapping()
    public String getInitialPage(Model model) {
        SpecificationPageDto specificationPageDto = new SpecificationPageDto();
        model.addAttribute("specificationPageDto", specificationPageDto);
        return "specification.html";
    }
}
