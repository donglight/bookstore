package org.zdd.bookstore.exception;

import org.zdd.bookstore.common.utils.BSResultUtil;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class BSExceptionHandler {

	public static final String BS_ERROR_VIEW_NAME = "exception";

	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.OK)
    public Object ExceptionHandler(HttpServletRequest request, Exception e) throws Exception {
    	
    	e.printStackTrace();
    	
    	if (isAjax(request)) {
    		return BSResultUtil.build(500, e.getMessage(),null);
    	} else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("exception", "系统繁忙，请稍后再试");
            modelAndView.addObject("url", request.getRequestURL());
            modelAndView.setViewName(BS_ERROR_VIEW_NAME);
            return modelAndView;
    	}
    }

	@ExceptionHandler(value = BSException.class)
	@ResponseStatus(HttpStatus.OK)
	public Object BSExceptionHandler(HttpServletRequest request, Exception e) throws Exception {
		e.printStackTrace();
		if (isAjax(request)) {
			return BSResultUtil.build(500, e.getMessage(),null);
		} else {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("exception", e.getMessage());
			modelAndView.addObject("url", request.getRequestURL());
			modelAndView.setViewName(BS_ERROR_VIEW_NAME);
			return modelAndView;
		}
	}
	@ExceptionHandler(value = UnauthorizedException.class)
	@ResponseStatus(HttpStatus.OK)
	public Object UnauthorizedExceptionHandler(HttpServletRequest request, Exception e) throws Exception {
		if (isAjax(request)) {
			return BSResultUtil.build(403, "对不起，您没有访问权限",null);
		} else {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("403");
			return modelAndView;
		}
	}


	/**
	 *
	 * @param httpRequest
	 * @return
	 */
	public static boolean isAjax(HttpServletRequest httpRequest){
		return  (httpRequest.getHeader("X-Requested-With") != null  
					&& "XMLHttpRequest"
						.equals( httpRequest.getHeader("X-Requested-With").toString()) );
	}
}
