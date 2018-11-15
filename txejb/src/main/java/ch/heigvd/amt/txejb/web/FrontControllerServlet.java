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

      response.getWriter().println("We want to delete an user.");

      transactionalContextLocal.deleteUser(user);

      response.getWriter().println("The user has been deleted");
    } catch (Exception e) {
      response.getWriter().println("There was a problem while deleting this user.");
    } finally {
      numberOfUserAfter = userDAOLocal.countUser();
      numberOfAppsAfter = applicationDAOLocal.countApplication(user);
      numberOfPasswordAfter = oldPasswordDAOLocal.countOldPassword(user);

      response.getWriter().println(String.format("Number of Users before: %d", numberOfUserBefore));
      response.getWriter().println(String.format("Number of Users after: %d", numberOfUserAfter));
      response.getWriter().println(String.format("Number of Apps before: %d", numberOfAppsBefore));
      response.getWriter().println(String.format("Number of Apps after: %d", numberOfAppsAfter));
      response.getWriter().println(String.format("Number of Passwords before: %d", numberOfPasswordBefore));
      response.getWriter().println(String.format("Number of Passwords after: %d", numberOfPasswordAfter));
    }
  }
}
