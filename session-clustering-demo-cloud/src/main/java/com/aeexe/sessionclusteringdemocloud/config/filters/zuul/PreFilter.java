package com.aeexe.sessionclusteringdemocloud.config.filters.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * client의 요청 정보의 정합성을 확인 하기 위한 전처리 필터
 * 혹은 backend 에 전달 될 request 를 가공하는 전처리 필터
 * 참조 https://spring.io/guides/gs/routing-and-filtering/
 * 참조 https://supawer0728.github.io/2018/03/11/Spring-Cloud-Zuul/
 */
@Slf4j
public class PreFilter extends ZuulFilter {

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER; // run before PreDecoration
    }

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    /**
     * 이 필터를 실행할시기를 결정하는 논리를 포함합니다
     * return true : 이 특정 필터는 항상 실행 됨.
     * "backend-api".equals(context.get(SERVICE_ID_KEY)); : application.yml 설정의 service name 이 backend-api 와 같은 경우 싦행
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

        String axToken = Optional.ofNullable(request.getHeader("X-AEEXE-TOKEN")).orElse("Non");

//        if ("Non".equals(axToken)) {
//            //TODO HasNotTokenException 필요~~~~~~~~
//            throw new NullPointerException("has not token!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//        }
        return null;
    }

}
