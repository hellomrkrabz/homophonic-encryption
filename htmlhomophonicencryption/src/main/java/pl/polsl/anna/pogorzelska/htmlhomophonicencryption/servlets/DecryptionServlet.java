package pl.polsl.anna.pogorzelska.htmlhomophonicencryption.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import pl.polsl.anna.pogorzelska.htmlhomophonicencryption.model.Transcriptor;
import pl.polsl.anna.pogorzelska.htmlhomophonicencryption.model.Validator;
import pl.polsl.anna.pogorzelska.htmlhomophonicencryption.exceptions.NotNumberInputException;
import pl.polsl.anna.pogorzelska.htmlhomophonicencryption.exceptions.ReadFileFailureException;

/** 
 * Servlet responsible for decryption of provided input. It communicates with model. 
 * This servlet also displays the content of history servlet.
 * 
 * @author Anna Pogorzelska
 * @version 1.2
 */
@WebServlet("/Decrypt")
public class DecryptionServlet extends HttpServlet {

    private final HashMap<String, String> stats;
    Transcriptor transcriptor;
    String output;
    Validator validator;
    boolean correctInput;
    
    /**
     * Constructor creating decryption servlet's object.
     * @throws pl.polsl.anna.pogorzelska.htmlhomophonicencryption.exceptions.ReadFileFailureException
     */
    public DecryptionServlet() throws ReadFileFailureException {
        this.stats = new HashMap<>();
        this.transcriptor = new Transcriptor();
        this.validator = new Validator();
        this.correctInput = false;
    }

    
    /**
     * Main function of decryption servlet. It processes requests and initialises decryption procedure. It works the same for both GET and POST methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    public void decryptionRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String input = request.getParameter("input");
        

        
        try {
            if (this.validator.checkValidityOfNumbers(input ) == true) {
                this.correctInput = true;
            }
        }
        catch (NotNumberInputException ex) {
        }

        if (input.length() == 0 || input == null) {
            response.sendError(response.SC_BAD_REQUEST, "You should give a parameter!");
        }
        else if (this.correctInput == false) {
            response.sendError(response.SC_BAD_REQUEST, "You should give an input from numbers range!");
        }
        else if (this.validator.alphabetCorrectness(input, this.transcriptor.getDictionary()) == false)
        {
            response.sendError(response.SC_BAD_REQUEST, "Provided input outside of alphabet file range!");
        }
        else {
            Integer count = (Integer) session.getAttribute("count");
                if (count == null)
                {
                    count = 1;
                } else 
                {
                    count = count + 1;
                }

        session.setAttribute("count", count);
            output = this.transcriptor.decrypiton(input);
            out.println("<html>\n<body>\n<h1>Input is " + input + "!</h1>\n");
            out.println("<html>\n<body>\n<h1>Output is " + output + "!</h1>\n");
            String entry = "Decryption " + "-" + input + "-" + output;
            session.setAttribute(count.toString() + "entry", entry);
            getServletContext().getRequestDispatcher("/History").include(request, response);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        decryptionRequest(request, response);
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        decryptionRequest(request, response);
    }
}
