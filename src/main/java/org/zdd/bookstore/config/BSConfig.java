package org.zdd.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class BSConfig {

    @Bean
    public Converter<String, Date> stringDateConverter(){
        return new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = sdf.parse((String) source);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return date;
            }
        };
    }

}
