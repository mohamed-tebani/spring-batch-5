package com.springbatch.springbatch.config;

import org.springframework.batch.item.ItemProcessor;

public class MyCustomProcessor implements ItemProcessor<String, String> {

    @Override
    public String process(String data) {
        System.out.println("MyCustomProcessor : Processing data : "+data);
        data = data.toUpperCase();
        return data;
    }

}
