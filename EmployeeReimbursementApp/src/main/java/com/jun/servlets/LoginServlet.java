package com.jun.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jun.dao.LoginDAO;
import com.jun.dao.LoginDAOImpl;
import com.jun.model.Login;
import com.jun.util.ConnectionUtil;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public LoginDAO loginDAO;
	
    public LoginServlet() {
        super();
        this.loginDAO = new LoginDAOImpl();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	try (Connection con = ConnectionUtil.getConnection()) {
    		response.setContentType("text/html");
    		RequestDispatcher dispatcher;
    		HttpSession session=request.getSession();
    		String username = request.getParameter("username");
    		String password = request.getParameter("password");
    		Login user = loginDAO.authenticateUser(username, password, con);
    		con.close();
    		String firstName = user.getFirstName();
    		String lastName = user.getLastName();
			int id = user.getLoginId();
			session.setAttribute("firstName", firstName);
			session.setAttribute("lastName", lastName);
			session.setAttribute("id", id);
			session.setAttribute("user", user);
    		boolean isManager = user.isManager();
    		
    		if (!isManager) {
    			dispatcher = getServletContext().getRequestDispatcher("/employee.jsp");
    			dispatcher.forward(request, response);
    		} else if (isManager){
    			dispatcher = getServletContext().getRequestDispatcher("/manager.html");
    			dispatcher.forward(request, response);
    		} else {
    			dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
    			dispatcher.forward(request,response);
    		}
    	}
		
	}
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (SQLException | NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (SQLException | NullPointerException e) {
			RequestDispatcher dispatcher;
			request.setAttribute("invalidPassword", "Invalid Username or Password");
			dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request,response);
		}
	}

}