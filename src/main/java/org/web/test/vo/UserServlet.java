package org.web.test.vo;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = new UserService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserVO user = new UserVO();
		String uiNumStr = request.getParameter("uiNum");
		
		if(uiNumStr!=null && !"".equals(uiNumStr)) {
			int uiNum = Integer.parseInt(uiNumStr);
			user.setUiNum(uiNum);
		}
		String uiId = request.getParameter("uiId");
		String uiName = request.getParameter("uiName");
		user.setUiName(uiName);
		user.setUiId(uiId);

		
		List<UserVO> users = us.getUsers(user);
		request.setAttribute("users", users);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/user-list.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
