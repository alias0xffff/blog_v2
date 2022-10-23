package com.minzheng.blog.filter;

import com.minzheng.blog.exception.BizException;
import com.minzheng.blog.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chenjiashuai
 * @since 2022/9/25
 */
@Component
public class LoginVerifyCodeFilter extends UsernamePasswordAuthenticationFilter {
    @Autowired
    private RedisService redisService;

    public LoginVerifyCodeFilter() {
        this.setPostOnly(false);
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String key = request.getParameter("key");
        String code = request.getParameter("code");
        String redisCode = (String) redisService.get(key);
        if (StringUtils.isEmpty(redisCode) || !redisCode.equals(code)) {
            throw new BizException("验证码错误");
        }
        return super.attemptAuthentication(request, response);
    }
    @Autowired
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }
}
