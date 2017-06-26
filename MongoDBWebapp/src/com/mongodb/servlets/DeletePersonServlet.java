package com.mongodb.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mongodb.MongoClient;
import com.mongodb.dao.MongoDBPersonDAO;
import com.mongodb.model.Person;

@WebServlet("/deletePerson")
public class DeletePersonServlet extends HttpServlet {
	Logger logger = Logger.getLogger(DeletePersonServlet.class);
	private static final long serialVersionUID = 6798036766148281767L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id == null || "".equals(id)) {
			throw new ServletException("id missing for delete operation");
		}
		MongoClient mongo = (MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");
		MongoDBPersonDAO personDAO = new MongoDBPersonDAO(mongo);
		Person p = new Person();
		p.setId(id);
		personDAO.deletePerson(p);
		logger.debug("Person deleted successfully with id=" + id);
		request.setAttribute("success", "Person deleted successfully");
		List<Person> persons = personDAO.readAllPerson();
		request.setAttribute("persons", persons);

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/persons.jsp");
		rd.forward(request, response);
	}

}