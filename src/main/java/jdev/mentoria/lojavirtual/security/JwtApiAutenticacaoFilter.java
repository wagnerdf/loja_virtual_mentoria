package jdev.mentoria.lojavirtual.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

/* Filtro onde todas as requisições serão capturadas para autenticar */
public class JwtApiAutenticacaoFilter extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		/* Estabelecer a autenticação do user */
		Authentication autentication =  new JWTTokenAutenticacaoService().
				getAuthentication((HttpServletRequest) request, (HttpServletResponse) response);
		
		/* Coloca o processo de autenticação para o spring security */
		SecurityContextHolder.getContext().setAuthentication(autentication);
		
		chain.doFilter(request, response);
		
	}
	
}
