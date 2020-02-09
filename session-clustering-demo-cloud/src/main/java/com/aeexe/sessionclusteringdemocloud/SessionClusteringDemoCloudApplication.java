package com.aeexe.sessionclusteringdemocloud;

import com.aeexe.sessionclusteringdemocloud.config.filters.zuul.PostFilter;
import com.aeexe.sessionclusteringdemocloud.config.filters.zuul.PreFilter;
import com.aeexe.sessionclusteringdemocloud.config.filters.zuul.RouteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class SessionClusteringDemoCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SessionClusteringDemoCloudApplication.class, args);
    }

    //	참조 https://spring.io/guides/gs/routing-and-filtering/
    @Bean
    public PreFilter preFilter() {
        return new PreFilter("x-auth-token");
    }

    @Bean
    public RouteFilter routeFilter() {
        return new RouteFilter();
    }

    @Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }
}
