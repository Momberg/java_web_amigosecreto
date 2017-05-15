package br.com.fiap.filter;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.entity.Usuarios;


@WebFilter("/admin/*")
public class Filterlogin implements Filter {

   
    public Filterlogin() {
      
    }

	public void destroy() {
	
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpSession session = ((HttpServletRequest)request).getSession();
		
		Usuarios usuario = (Usuarios)session.getAttribute("usuario");
		
		if(usuario == null) {
			((HttpServletResponse)response).sendRedirect("/Aula_JSF_PrimeFaces/index.faces");
		}else {
			chain.doFilter(request, response);
		}
	
	}

	public void init(FilterConfig fConfig) throws ServletException {
	
	}

}
