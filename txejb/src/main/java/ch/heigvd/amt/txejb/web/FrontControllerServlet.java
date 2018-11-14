package ch.heigvd.amt.txejb.web;

import ch.heigvd.amt.txejb.model.User;
import ch.heigvd.amt.txejb.services.ApplicationDAOLocal;
import ch.heigvd.amt.txejb.services.UserDAOLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FrontControllerServlet", urlPatterns = "/front")
public class FrontControllerServlet extends javax.servlet.http.HttpServlet {


  @EJB
  UserDAOLocal userDAOLocal;

  @EJB
  ApplicationDAOLocal applicationDAOLocal;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    long numberOfUserBefore = -1;
    long numberOfUserAfter = -1;

    try {
      numberOfUserBefore = userDAOLocal.countUser();
      User user1 = new User();
      userDAOLocal.createUser(user1);
      response.getWriter().println(user1);
    } catch (Exception e) {
      response.getWriter().println("There was a problem while manufacturing the car or its parts");
    } finally {
      numberOfUserAfter = userDAOLocal.countUser();
      response.getWriter().println(String.format("Number of tires before: %d", numberOfUserBefore));
      response.getWriter().println(String.format("Number of tires after: %d", numberOfUserAfter));
    }
  }
}
