package com.aeexe.sessionclusteringdemocloud.config.filters.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_RESPONSE_FILTER_ORDER;

/**
 * response 를 생성하는 작업 처리
 */
public class PostFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return SEND_RESPONSE_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletResponse servletResponse = context.getResponse();
        servletResponse.addHeader("X-AEEXE-TOKEN", UUID.randomUUID().toString());
        return null;
    }
}
