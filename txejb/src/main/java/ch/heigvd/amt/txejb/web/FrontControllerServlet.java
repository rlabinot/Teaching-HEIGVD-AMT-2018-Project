package ch.heigvd.amt.txejb.web;

import ch.heigvd.amt.txejb.services.ApplicationDAOLocal;
import ch.heigvd.amt.txejb.services.OldPasswordDAOLocal;
import ch.heigvd.amt.txejb.services.TransactionalContextLocal;
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

  @EJB
  OldPasswordDAOLocal oldPasswordDAOLocal;

  @EJB
  TransactionalContextLocal transactionalContextLocal;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    long numberOfUserBefore = -1;
    long numberOfUserAfter = -1;
    long numberOfAppsBefore = -1;
    long numberOfAppsAfter = -1;
    long numberOfPasswordBefore = -1;
    long numberOfPasswordAfter = -1;

    String user = "user@stackoveramt.ch";
    try {
      numberOfUserBefore = userDAOLocal.countUser();
      numberOfAppsBefore = applicationDAOLocal.countApplication(user);
      numberOfPasswordBefore = oldPasswordDAOLocal.countOldPassword(user);

      response.getWriter().println("We want to delete an user.\n");

      transactionalContextLocal.deleteUser(user);

      response.getWriter().println("The user has been deleted\n");
    } catch (Exception e) {
      response.getWriter().println(e.getMessage());
      response.getWriter().println("There was a problem while deleting this user.\n");
    } finally {
      numberOfUserAfter = userDAOLocal.countUser();
      numberOfAppsAfter = applicationDAOLocal.countApplication(user);
      numberOfPasswordAfter = oldPasswordDAOLocal.countOldPassword(user);

      response.getWriter().println(String.format("Number of Users before: %d\n", numberOfUserBefore));
      response.getWriter().println(String.format("Number of Users after: %d\n", numberOfUserAfter));
      response.getWriter().println(String.format("Number of Apps before: %d\n", numberOfAppsBefore));
      response.getWriter().println(String.format("Number of Apps after: %d\n", numberOfAppsAfter));
      response.getWriter().println(String.format("Number of Passwords before: %d\n", numberOfPasswordBefore));
      response.getWriter().println(String.format("Number of Passwords after: %d\n", numberOfPasswordAfter));
    }
  }
}
