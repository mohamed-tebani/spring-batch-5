package com.springbatch.springbatch.config;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class MyCustomWriter implements ItemWriter<String> {

    @Override
    public void write(Chunk<? extends String> chunk) {
        for (String data : chunk) {
            System.out.println("MyCustomWriter    : Writing data    : " + data);
        }
        System.out.println("MyCustomWriter    : Writing data    : completed");
    }
}
