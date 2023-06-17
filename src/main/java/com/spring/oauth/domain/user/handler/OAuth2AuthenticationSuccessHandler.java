package com.spring.oauth.domain.user.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    // jwt 인증 관련 처리 수행

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        HttpSession httpSession = request.getSession();

        log.info(">> OAuth2AuthenticationSuccessHandler 진입");
        System.out.println("request.getParameter(nameAttributeKey) = " + request.getParameter("NameAttributeKey"));
        System.out.println("request.getParameter(name) = " + request.getParameter("name"));
        System.out.println("httpSession.getAttribute(name) = " + httpSession.getAttribute("name"));
        System.out.println("httpSession.getAttribute(NameAttributeKey) = " + httpSession.getAttribute("NameAttributeKey"));

        if (response.isCommitted()) {
            log.debug("응답이 이미 존재합니다.");
            return;
        }

        getRedirectStrategy().sendRedirect(request, response, "/");
    }
}
