package com.example.demo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Always set content type and encoding
        response.setContentType("text/html;charset=UTF-8");
        
        // Use try-with-resources to safely handle the writer
        try (var writer = response.getWriter()) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<head><title>Hello</title></head>");
            writer.println("<body>");
            writer.println("<h1>Hello from Real Estate Rating Engine!</h1>");
            writer.println("</body>");
            writer.println("</html>");
        } catch (IOException ex) {
            // Log the error (in a real app, use a logger)
            System.err.println("Error writing response: " + ex.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server error");
        }
    }
}
