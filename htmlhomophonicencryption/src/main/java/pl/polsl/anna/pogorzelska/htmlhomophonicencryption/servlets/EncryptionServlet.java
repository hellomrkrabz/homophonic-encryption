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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import pl.polsl.anna.pogorzelska.htmlhomophonicencryption.Transcriptor;
import pl.polsl.anna.pogorzelska.htmlhomophonicencryption.Validator;
import pl.polsl.anna.pogorzelska.htmlhomophonicencryption.exceptions.NonEnglishInputException;
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
    Validator validator;
    boolean correctInput;

    /**
     * Constructor initiating statistics collection
     * @throws pl.polsl.anna.pogorzelska.htmlhomophonicencryption.exceptions.ReadFileFailureException
     */
    public EncryptionServlet() throws ReadFileFailureException {
        this.stats = new HashMap<>();
        this.transcriptor = new Transcriptor();
        this.validator = new Validator();
        this.correctInput = false;
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
        try {
            if (this.validator.checkValidityOfString(input) ==true) {
                correctInput = true;
            }
        } catch (NonEnglishInputException ex) {
        }
        // FirstName or lastName was not given - send error message
        if (input.length() == 0 || input == null) {
            response.sendError(response.SC_BAD_REQUEST, "You should give a parameter!");
        } 
        else if (this.correctInput == false) {
            response.sendError(response.SC_BAD_REQUEST, "You should give an input from latin letters range!");
        }
        else {
            output = this.transcriptor.encryption(input);
            out.println("<html>\n<body>\n<h1>Input is " + input + "!!!</h1>\n");
            out.println("<html>\n<body>\n<h1>Output is " + output + "!!!</h1>\n");
            }
        this.correctInput = false;
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