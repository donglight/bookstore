package org.zdd.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@EnableCaching//开启缓存
@MapperScan("org.zdd.bookstore.model.dao")
@EnableAsync
public class BookstoreApplication extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BookstoreApplication.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }
}
