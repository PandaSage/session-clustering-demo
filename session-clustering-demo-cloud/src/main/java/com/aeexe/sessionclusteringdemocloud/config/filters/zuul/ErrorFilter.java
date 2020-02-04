package com.aeexe.sessionclusteringdemocloud.config.filters.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ERROR_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_ERROR_FILTER_ORDER;

@Slf4j
public class ErrorFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        return SEND_ERROR_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().getThrowable() != null;
    }

    @Override
    public Object run() throws ZuulException {
        Throwable throwable = RequestContext.getCurrentContext().getThrowable();
        log.error("Exception was thrown in filters: ", throwable);
        log.error("I need token!!!!!!!!!!!!!!!!!!!!!!!!! : s%", "errorFilter!!!!!!!!!!!!!!!!!");
        return null;
    }
}
