package com.lguplus.medialog.project.login;

import java.util.HashMap;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lguplus.medialog.project.base.auth.AuthPostProcessor;
//import com.lguplus.medialog.project.base.auth.extra.CustomAuthenticationToken;
import com.lguplus.medialog.project.base.user.User;
import com.lguplus.medialog.project.board.BoardController;
import com.lguplus.medialog.project.common.dto.RestResult;
import com.lguplus.medialog.project.config.consts.Const;
import com.lguplus.medialog.project.config.consts.ResultCode;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired 
	private AuthenticationManager authManager;
	@Autowired
	private AuthPostProcessor authPost;

	/**
	 * AjaxAuthenticationFilter??? ???????????? ajax login ????????? ??? ?????? ????????? ???????????? ??????????????? ????????????.
	 * ????????? json?????? ????????? ?????? ????????? ????????? ?????? ????????? ???????????? ????????????.
	 * form login??? ?????? ?????? ????????? model ????????? ????????? ???????????? alert ?????? ?????? ?????????.
	 * ???, ?????? ????????? form login ????????? ????????? ????????? ajax??? ?????? ???????????????.
	 * ?????? AjaxAuthenticationFilter??? ???????????? ??? ???????????? ????????? ???????????? ?????? ????????? ???????????? ????????? ??????.
	 * 
	 * jwt ????????? ?????? ????????? ajax login ????????? ????????? ?????? ?????? ??????.
	 * https://catsbi.oopy.io/4d4859e7-029e-4791-bbe8-0bde66fb7670
	 * https://tech.junhabaek.net/spring-security-usernamepasswordauthenticationfilter???-???-??????-??????-8b5927dbc037
	 */
	
	@RequestMapping(value ="/api/auth/invalidToken", method = RequestMethod.POST)
	public void tokenTerminate(@RequestBody HashMap<String, Object> requestJsonHashMap) {
		logger.info(requestJsonHashMap.toString());
		authPost.updateLog("ff");
	}
	
	@PostMapping("/api/auth/login")
	public RestResult<?> login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		UsernamePasswordAuthenticationToken token = new CustomAuthenticationToken(user.getUsername(), user.getPassword(), user.getUserDomain());
		logger.info(user.toString());
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authentication = null;

        try {
        	authentication = authManager.authenticate(token);
        }
        catch (AuthenticationException e) {
        	ResultCode resultCode = authPost.getFailureResultCode(request, response, e);
        	return new RestResult<String>(false)
        			.setCode(resultCode);
        }

        // form login ?????? UsernamePasswordAuthenticationFilter?????? context ????????? ?????????.
        // ????????? ?????? URL??? ????????? ?????? ????????? ????????? UsernamePasswordAuthenticationFilter??? ?????? ?????????.
        SecurityContextHolder.getContext().setAuthentication(authentication);
        logger.info("??????"+ authentication);
        logger.info("SCH:::::::::"+ SecurityContextHolder.getContext().toString());
        HttpSession session = request.getSession();
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        
        Random ran = new Random();
    	StringBuilder sb = new StringBuilder();
        for(int i=0; i<10; i++) {
        	char a = (char) (ran.nextInt(61)+65);
        	sb.append(a);
        }
        user.setToken(sb.toString());
        authPost.insertLog(user);
        String nextUrl = getReturnUrl(request, response);
        logger.debug("login SUCCESS - nextUrl = [{}]", nextUrl);

        user.setToken(sb.toString());
        ResultCode rcode = authPost.getSuccessResultCode(request, response, authentication);
        logger.info(rcode.toString());

    	

        
        if (rcode != ResultCode.SUCCESS)
        	return new RestResult<String>(false).setCode(rcode);
        return new RestResult<String>().setData(nextUrl).setCode(rcode).setMessage(sb.toString());
    }

	/** 	 * ????????? ?????? ???????????? URL ??????
	 */
	private String getReturnUrl(HttpServletRequest request, HttpServletResponse response) {
		RequestCache requestCache = new HttpSessionRequestCache();
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if (savedRequest == null) {
			return Const.FORM_LOGIN_SUCC_URL;
		}
		return savedRequest.getRedirectUrl();
	}
	
	@GetMapping("/api/auth/logout")
	public RestResult<?> logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		
		return new RestResult<String>();
	}
	
}
