package org.zerock.club.security.handler;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONObject;
import org.apache.tomcat.util.http.fileupload.impl.IOFileUploadException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Log4j2
public class ApiLoginFailHandler implements AuthenticationFailureHandler {

    @SneakyThrows
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOFileUploadException, ServletException{

        log.info("login fail handler............................");
        log.info(exception.getMessage());

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        response.setContentType("application/json;charset=utf-8");
        JSONObject json=new JSONObject();
        String message= exception.getMessage();
        json.put("code", "401");
        json.put("message", message);

        PrintWriter out=response.getWriter();
        out.print(json);
    }
}
