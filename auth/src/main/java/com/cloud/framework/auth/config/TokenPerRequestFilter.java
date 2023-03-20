package com.cloud.framework.auth.config;

import com.alibaba.fastjson.JSON;
import com.cloud.framework.auth.pojo.AccountUser;
import com.cloud.framework.auth.service.AccountUserService;
import com.cloud.framework.model.common.CloudConstant;
import com.cloud.framework.model.common.HttpEnum;
import com.cloud.framework.model.common.Result;
import com.cloud.framework.auth.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

/**
 * Security Token拦截器
 * */
@Component
public class TokenPerRequestFilter extends OncePerRequestFilter {
    @Autowired
    private AccountUserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //  获取Authorization属性
        String token = request.getHeader(CloudConstant.REQUEST_JWT_HEADER);
        if (StringUtils.isNotBlank(token)) {
            //解析Token获取载荷
            try {
                Claims claims = JwtUtil.parseJWT(token);
                //JWT中获取用户信息
                String userName = claims.getSubject();
                //查询用户
                AccountUser accountUser = userService.findAccountUserByEmail(userName);
                //存放用户信息、空权限
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(accountUser.getEmail(), null, Collections.emptyList());
                //放入上下文
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            } catch (ExpiredJwtException exception) {
                //Token过期
                this.WriteJSON(response,new Result<>().fail(HttpEnum.UNAUTHORIZED.getCode(),HttpEnum.UNAUTHORIZED.getDesc()));
                return;
            } catch (Exception e) {
                //非法Token
                e.printStackTrace();
                this.WriteJSON(response,new Result<>().fail(HttpEnum.UNAUTHORIZED.getCode(),HttpEnum.UNAUTHORIZED.getDesc()));
                return;
            }
        }
        //放行 下一个拦截器
        filterChain.doFilter(request, response);
    }

    private void WriteJSON(HttpServletResponse response,
                           Object obj) throws IOException {
        response.setContentType(CloudConstant.RESPONSE_CONTENTTYPE);
        //输出JSON
        PrintWriter out = response.getWriter();
        out.write(JSON.toJSONString(obj));
        out.flush();
        out.close();
    }
}
