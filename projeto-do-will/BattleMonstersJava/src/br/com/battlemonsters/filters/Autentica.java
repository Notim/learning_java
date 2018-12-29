package br.com.battlemonsters.filters;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.battlemonsters.entidades.Jogador;

/**
 * Servlet Filter implementation class Autentica
 */
@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.ERROR
		}, urlPatterns = { "*.jsp" })
public class Autentica implements Filter {

    /**
     * Default constructor. 
     */
    public Autentica() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = ((HttpServletRequest)request);
		HttpSession session = req.getSession();

		Jogador logado = (Jogador) session.getAttribute("LOGADO");
		
		if (logado != null || req.getRequestURI().equals("/prjBattleMonstersWeb/cadastro.jsp")){
			chain.doFilter(request, response);
		} else {
			RequestDispatcher rd = req.getRequestDispatcher("./index.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
