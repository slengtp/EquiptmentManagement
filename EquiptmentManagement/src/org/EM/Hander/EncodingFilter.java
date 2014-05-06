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
		//����request�����ַ���
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		//����ȫ��ҳ�漰Servlet����ת�����У�ͳͳ����web.xml�ļ��ж���ı��뷽ʽ��UTF-8�����б���
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
		//��ʼ��config
		this.config = fConfig;
	
	}

}
