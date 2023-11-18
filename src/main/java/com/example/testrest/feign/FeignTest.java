package com.example.testrest.feign;

import com.example.testrest.feign.dto.TestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "jplaceholder", url = "https://ipapi.co/")
public interface FeignTest {

        @RequestMapping(method = RequestMethod.GET, value = "json", produces = "application/json")
        TestResponse getPostById();
}
