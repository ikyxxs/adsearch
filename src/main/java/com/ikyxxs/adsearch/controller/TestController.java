package com.ikyxxs.adsearch.controller;

import com.alibaba.fastjson.JSONArray;
import com.ikyxxs.adsearch.model.Advert;
import com.ikyxxs.adsearch.model.SearchParam;
import com.ikyxxs.adsearch.service.IndexService;
import com.ikyxxs.adsearch.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TestController {

    @Autowired
    private IndexService indexService;

    @Autowired
    private SearchService searchService;

    @PostMapping("/index")
    public Advert index(@RequestBody Advert advert) throws IOException {
        indexService.index(advert);
        return advert;
    }

    @PostMapping("/search")
    public JSONArray search(@RequestBody SearchParam param) throws Exception {
        return searchService.search(param);
    }
}
