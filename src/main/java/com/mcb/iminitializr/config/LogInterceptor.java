package com.mcb.iminitializr.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.StopWatch;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogInterceptor implements HandlerInterceptor {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private static final ThreadLocal<StopWatch> stopWatchThreadLocal = new NamedThreadLocal<StopWatch>("ThreadLocal StopWatch") {
        @Override
        protected StopWatch initialValue() {
            return new StopWatch();
        }
    };

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        stopWatchThreadLocal.get().start();
        String params = "";
        if (HttpMethod.GET.matches(request.getMethod())) {
            params = request.getQueryString();
        } else if (HttpMethod.POST.matches(request.getMethod())) {
            String contentType = request.getContentType();
            if (contentType.equals(MediaType.APPLICATION_FORM_URLENCODED_VALUE)) {
                params = request.getParameterMap().toString();
            } else if (contentType.equals(MediaType.APPLICATION_JSON_VALUE)) {
                byte[] bodyBytes = StreamUtils.copyToByteArray(request.getInputStream());
                params = new String(bodyBytes, request.getCharacterEncoding());
            }
        }
        log.info("请求开始，url=[{}]，请求参数=[{}]", request.getRequestURI(), params);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        StopWatch stopWatch = stopWatchThreadLocal.get();
        stopWatch.stop();
        log.info("请求结束，url=[{}]，耗时=[{}]ms", request.getRequestURI(), stopWatch.getTotalTimeMillis());
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
