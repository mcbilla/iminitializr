package com.mcb.iminitializr.config;

import org.springframework.util.StreamUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

public class WapperRequestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServletRequest requestWrapper = null;
        if (request instanceof HttpServletRequest) {
            requestWrapper = new RequestWrapper((HttpServletRequest) request);
        }
        if (requestWrapper == null) {
            chain.doFilter(request, response);
        } else {
            // 将请求封装并传递下去
            chain.doFilter(requestWrapper, response);
        }
    }

    @Override
    public void destroy() {

    }

    /**
     * body可以从从HttpServletRequest里的inputStream里获取，但是根据java中inputStream的特性，从里面读出内容后，之后就没法读了，也就是说取出了Body，后面的流程就再也取不到了
     * 解决办法是重写HttpServletRequestWrapper，要把读取过的内容存下来，然后需要的时候对外提供可被重复读取的ByteArrayInputStream。
     */
    public static class RequestWrapper extends HttpServletRequestWrapper {

        /**
         * 缓存下来的HTTP body
         */
        private byte[] body;

        public RequestWrapper(HttpServletRequest request) throws IOException {
            super(request);
            body = StreamUtils.copyToByteArray(request.getInputStream());
        }

        /**
         * 重新包装输入流
         *
         * @return
         * @throws IOException
         */
        @Override
        public ServletInputStream getInputStream() throws IOException {
            InputStream bodyStream = new ByteArrayInputStream(body);
            return new ServletInputStream() {

                @Override
                public int read() throws IOException {
                    return bodyStream.read();
                }

                /**
                 * 下面的方法一般情况下不会被使用，如果你引入了一些需要使用ServletInputStream的外部组件，可以重点关注一下。
                 * @return
                 */
                @Override
                public boolean isFinished() {
                    return false;
                }

                @Override
                public boolean isReady() {
                    return true;
                }

                @Override
                public void setReadListener(ReadListener readListener) {

                }
            };
        }

        @Override
        public BufferedReader getReader() throws IOException {
            return new BufferedReader(new InputStreamReader(getInputStream()));
        }
    }
}
