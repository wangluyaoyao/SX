package com.suixing.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.io.IOException;

/** * @author LaZY(李志一) * @create 2019-06-08 11:30 */
@Configuration
public class IndexConfig {
    @EventListener({ApplicationReadyEvent.class})
    void applicationReadyEvent() {
        System.out.println("应用已经准备就绪 ... 启动浏览器");
        String url = "http://localhost:8089/";
        Runtime runtime = Runtime.getRuntime();
        System.out.println(url);
//        try {
//            runtime.exec(url);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
