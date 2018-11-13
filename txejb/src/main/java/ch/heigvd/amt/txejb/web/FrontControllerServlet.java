package ch.heigvd.amt.txejb.web;

import ch.heigvd.amt.txejb.model.Car;
import ch.heigvd.amt.txejb.services.OrderServiceLocal;
import ch.heigvd.amt.txejb.services.TireDAOLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FrontControllerServlet", urlPatterns = "/front")
public class FrontControllerServlet extends javax.servlet.http.HttpServlet {

  @EJB
  OrderServiceLocal orderService;

  @EJB
  TireDAOLocal tireDAO;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    long numberOfTiresBefore = -1;
    long numberOfTiresAfter = -1;

    try {
      numberOfTiresBefore = tireDAO.count();
      Car car = orderService.orderCar("John");

      response.getWriter().println(car);
    } catch (Exception e) {
      response.getWriter().println("There was a problem while manufacturing the car or its parts");
    } finally {
      numberOfTiresAfter = tireDAO.count();
      response.getWriter().println(String.format("Number of tires before: %d", numberOfTiresBefore));
      response.getWriter().println(String.format("Number of tires after: %d", numberOfTiresAfter));
    }
  }
}
