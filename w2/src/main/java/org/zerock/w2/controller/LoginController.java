package org.zerock.w2.controller;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.zerock.w2.dto.MemberDTO;
import org.zerock.w2.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

/**
 * 로그인 처리 Controller
 * GET -> 로그인 화면
 * POST -> 실제 로그인 처리
 */
@WebServlet("/login")
@Log4j2
public class LoginController extends HttpServlet {
    /**
     * GET 요청 시 로그인 화면으로 이동
     * @param req   an {@link HttpServletRequest} object that
     *                  contains the request the client has made
     *                  of the servlet
     *
     * @param resp  an {@link HttpServletResponse} object that
     *                  contains the response the servlet sends
     *                  to the client
     *
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("login get................");

        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    /**
     * POST 요청시 목록 화면으로 이동
     * @param req   an {@link HttpServletRequest} object that
     *                  contains the request the client has made
     *                  of the servlet
     *
     * @param resp  an {@link HttpServletResponse} object that
     *                  contains the response the servlet sends
     *                  to the client
     *
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("login post................");

        String mid = req.getParameter("mid");
        String mpw = req.getParameter("mpw");
        String auto = req.getParameter("auto");

        //auto라는 이름으로 체크박스에서 전송되는 값이 on인지 확인
        boolean rememberMe = auto != null && auto.equals("on");

        try { // 로그인된 경우 -> HttpSession을 이용해서 loginInfo이름으로 객체를 저장함
            MemberDTO memberDTO = MemberService.INSTANCE.login(mid, mpw);

            //rememberMe가 true라면 UUID를 이용하여 임의의 번호를 생성
            if (rememberMe) {
                String uuid = UUID.randomUUID().toString();
                MemberService.INSTANCE.updateUuid(mid, uuid);
                memberDTO.setUuid(uuid);

                //브라우저에 remember-me 이름의 쿠키를 생성하여 전송함
                Cookie rememberCookie = new Cookie("remember-me", uuid);
                rememberCookie.setMaxAge(60*60*24*7); //쿠키의 유효기간 1주일
                rememberCookie.setPath("/");

                resp.addCookie(rememberCookie);
            }

            HttpSession session = req.getSession();
            session.setAttribute("loginInfo", memberDTO);
            resp.sendRedirect("/todo/list");
        } catch (Exception e) { // 예외가 발생하는 경우 /login으로 이동, result 파라미터로 문제가 발생함을 전달함
            resp.sendRedirect("/login?result=error");
        }
    }
}
