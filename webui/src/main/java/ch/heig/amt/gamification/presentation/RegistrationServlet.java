package ch.heig.amt.gamification.presentation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        

        List<String> errors = new ArrayList<>();
        if (name == null || name.trim().equals("")) {
            errors.add("Name cannot be empty");
        }
        if (email == null || email.trim().equals("")) {
            errors.add("Email cannot be empty");
        }
        if (password == null || password.trim().equals("")) {
            errors.add("Password cannot be empty");
        } else {
            if (email.indexOf('@') == -1) {
                errors.add("Invalid format for email.");
            }
        }

        request.setAttribute("name", name);
        request.setAttribute("password", password);
        request.setAttribute("email", email);
        if (errors.size() == 0) {
            // Ajout à la DB
            request.setAttribute("name", name + " " + password);
            request.getRequestDispatcher("/WEB-INF/pages/profil.jsp").forward(request, response);
        } else {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
        }

    }

}

