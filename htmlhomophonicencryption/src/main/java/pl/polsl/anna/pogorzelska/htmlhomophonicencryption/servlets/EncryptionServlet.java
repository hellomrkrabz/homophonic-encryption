/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.polsl.anna.pogorzelska.htmlhomophonicencryption.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import pl.polsl.anna.pogorzelska.htmlhomophonicencryption.Transcriptor;
import pl.polsl.anna.pogorzelska.htmlhomophonicencryption.exceptions.ReadFileFailureException;

/**
 * Main class of the servlet that demonstrates parameter download given during
 * servlet initialization
 *
 * @author Gall Anonim
 * @version 1.0
 */
@WebServlet("/Encrypt")
public class EncryptionServlet extends HttpServlet {

    /**
     * Collection of statistics
     */
    private final HashMap<String, String> stats;
    Transcriptor transcriptor;
    String output;

    /**
     * Constructor initiating statistics collection
     * @throws pl.polsl.anna.pogorzelska.htmlhomophonicencryption.exceptions.ReadFileFailureException
     */
    public EncryptionServlet() throws ReadFileFailureException {
        this.stats = new HashMap<>();
        this.transcriptor = new Transcriptor();
    }
//ADD GET REAL PATH
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        //response.setContentType("text/html; charset=ISO-8859-2");
        //FileInputStream in = new FileInputStream(alphabetFile);
        PrintWriter out = response.getWriter();
        // Get parameter values - firstName i lastName
        String input = request.getParameter("input");

        // FirstName or lastName was not given - send error message
        if (input.length() == 0 || input == null) {
            response.sendError(response.SC_BAD_REQUEST, "You should give a parameter!");
        } else {
            output = this.transcriptor.encryption(input);
            out.println("<html>\n<body>\n<h1>Input is " + input + "!!!</h1>\n");
            out.println("<html>\n<body>\n<h1>Output is " + output + "!!!</h1>\n");
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/plain; charset=ISO-8859-2");
        PrintWriter out = response.getWriter();

        out.println("Passed parameters:");

        Enumeration enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String name = (String) enumeration.nextElement();
            out.println(name + " = " + request.getParameter(name));
        }
    }
}