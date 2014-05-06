package org.EM.Hander;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
public class EncodingFilter implements Filter {

	//the configuration class
	private FilterConfig config;
	
    public EncodingFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		String encoding = config.getInitParameter("encoding");
		//设置request编码字符集
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		//这样全局页面及Servlet在跳转过程中，统统都用web.xml文件中定义的编码方式（UTF-8）进行编码
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
		//初始化config
		this.config = fConfig;
	
	}

}
