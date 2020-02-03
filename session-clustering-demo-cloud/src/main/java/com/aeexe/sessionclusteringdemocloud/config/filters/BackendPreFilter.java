package com.aeexe.sessionclusteringdemocloud.config.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

/**
 * client의 요청 정보에 access token 을 발행 하기 위한 전처리 필터
 * 참조 https://spring.io/guides/gs/routing-and-filtering/
 * 참조 https://supawer0728.github.io/2018/03/11/Spring-Cloud-Zuul/
 */
@Slf4j
public class BackendPreFilter extends ZuulFilter {

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1; // run before PreDecoration
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
        return "backend-api".equals(context.get(SERVICE_ID_KEY));
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

        String aeexeToken = AccessTokenToAeexe(request);
        context.addZuulRequestHeader("X-AEEXE-TOKEN", aeexeToken);
        return null;
    }

    //create access token for client
    private String AccessTokenToAeexe(HttpServletRequest request) {
        //TODO 차후 request 에서 특정 정보를 받아 가공 or 매핑 하는 방식으로 변경
        //일단 sampling 에서는 random uuid 발행
        return UUID.randomUUID().toString();
    }
}
