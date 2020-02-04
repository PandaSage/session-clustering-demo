package com.aeexe.sessionclusteringdemocloud.config.filters.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ROUTE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SIMPLE_HOST_ROUTING_FILTER_ORDER;

/**
 * pre filter 이후 request, response 를 client 가 요구하는 모델로 변환 하는 작업 수행
 * 참조 https://spring.io/guides/gs/routing-and-filtering/
 * 참조 https://supawer0728.github.io/2018/03/11/Spring-Cloud-Zuul/
 * 참조 https://jsonobject.tistory.com/464
 */
@Slf4j
public class RouteFilter extends ZuulFilter {
    @Override
    public int filterOrder() {
        return SIMPLE_HOST_ROUTING_FILTER_ORDER; // run before PreDecoration
    }

    @Override
    public String filterType() {
        return ROUTE_TYPE;
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
        return false;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

        String aeexeToken = AccessTokenToAeexe(request);
        log.info(String.format("X-AEEXE-TOKEN : %s", aeexeToken));
        context.addZuulRequestHeader("X-AEEXE-TOKEN", aeexeToken);
        return null;
    }

    //create access token for client
    private String AccessTokenToAeexe(HttpServletRequest request) {
        //TODO 차후 request 에서 특정 정보를 받아 가공 or 매핑 하는 방식으로 변경
        //일단 sampling 에서는 random uuid 발행
        return UUID.randomUUID().toString();
    }

//    아래의 예제는 Servlet Request를 OkHttp3 Request로 변환하고, 요청을 실행하고,
//
//    OkHttp3 Response를 Servlet Response로 변환하는 작업을 수행한다
//    @Autowired
//    private ProxyRequestHelper helper;
//
//    @Override
//    public String filterType() {
//        return ROUTE_TYPE;
//    }
//
//    @Override
//    public int filterOrder() {
//        return SIMPLE_HOST_ROUTING_FILTER_ORDER - 1;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return RequestContext.getCurrentContext().getRouteHost() != null
//                && RequestContext.getCurrentContext().sendZuulResponse();
//    }
//
//    @Override
//    public Object run() {
//        OkHttpClient httpClient = new OkHttpClient.Builder()
//                // customize
//                .build();
//
//        RequestContext context = RequestContext.getCurrentContext();
//        HttpServletRequest request = context.getRequest();
//
//        String method = request.getMethod();
//
//        String uri = this.helper.buildZuulRequestURI(request);
//
//        Headers.Builder headers = new Headers.Builder();
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String name = headerNames.nextElement();
//            Enumeration<String> values = request.getHeaders(name);
//
//            while (values.hasMoreElements()) {
//                String value = values.nextElement();
//                headers.add(name, value);
//            }
//        }
//
//        InputStream inputStream = request.getInputStream();
//
//        RequestBody requestBody = null;
//        if (inputStream != null && HttpMethod.permitsRequestBody(method)) {
//            MediaType mediaType = null;
//            if (headers.get("Content-Type") != null) {
//                mediaType = MediaType.parse(headers.get("Content-Type"));
//            }
//            requestBody = RequestBody.create(mediaType, StreamUtils.copyToByteArray(inputStream));
//        }
//
//        Request.Builder builder = new Request.Builder()
//                .headers(headers.build())
//                .url(uri)
//                .method(method, requestBody);
//
//        Response response = httpClient.newCall(builder.build()).execute();
//
//        LinkedMultiValueMap<String, String> responseHeaders = new LinkedMultiValueMap<>();
//
//        for (Map.Entry<String, List<String>> entry : response.headers().toMultimap().entrySet()) {
//            responseHeaders.put(entry.getKey(), entry.getValue());
//        }
//
//        this.helper.setResponse(response.code(), response.body().byteStream(),
//                responseHeaders);
//        context.setRouteHost(null); // prevent SimpleHostRoutingFilter from running
//        return null;
//    }
}
