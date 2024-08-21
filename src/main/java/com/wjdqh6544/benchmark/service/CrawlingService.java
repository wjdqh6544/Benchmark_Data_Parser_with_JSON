package com.wjdqh6544.benchmark.service;

import com.wjdqh6544.benchmark.dto.CrawlerPageDto;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/*
BLOG_Benchmark_Data_Parser_with_JSON
- service/CrawlingService: a service class that crawl entered URL, process collected data from it, and return result data.
*/
@Service
@RequiredArgsConstructor
class CrawlingService {
    public List<LinkedHashMap<String, Integer>> selectSources(CrawlerPageDto requestDto) {
        try {
            switch(requestDto.getSources()) {
                case "wccftech.com" -> {
                    return crawlWccfTech(requestDto.getURL());
                }
                default -> {
                    return null;
                }
            }
        } catch (IOException e) {
            return null;
        }

    }

    private List<LinkedHashMap<String, Integer>> crawlWccfTech(String enteredURL) throws IOException {
        Document pageHtml = Jsoup.connect(enteredURL).get();
        Elements elementList = pageHtml.select(".chart-data");
        List<LinkedHashMap<String, Integer>> BenchmarkList = new ArrayList<>();
        for (Element element : elementList) {
            LinkedHashMap<String, Integer> eachBench = new LinkedHashMap<>();
            for (Element tmp : element.children()) {
                eachBench.put(tmp.children().get(0).text(), Integer.parseInt(tmp.children().get(1).text()));
            }
            BenchmarkList.add(eachBench);
        }
        return BenchmarkList;
    }

    public String getSources(String enteredURL) throws MalformedURLException {
        URL url = new URL(enteredURL);
        return url.getHost().replace("www.", "");
    }
}
