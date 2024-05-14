package org.zerock.w2.filter;

/**
 * UTF-8 인코딩 필터
 */

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
@Log4j2
public class UTF8Filter implements Filter {

    /**
     * POST 요청이 들어오면 UTF-8로 인코딩함
     * @param request the <code>ServletRequest</code> object contains the client's request
     * @param response the <code>ServletResponse</code> object contains the filter's response
     * @param chain the <code>FilterChain</code> for invoking the next filter or the resource
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("UTF8 filter....");

        HttpServletRequest req = (HttpServletRequest)  request;

        req.setCharacterEncoding("UTF-8");

        chain.doFilter(request, response);
    }
}

