package pl.polsl.anna.pogorzelska.htmlhomophonicencryption.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import pl.polsl.anna.pogorzelska.htmlhomophonicencryption.model.Transcriptor;
import pl.polsl.anna.pogorzelska.htmlhomophonicencryption.model.Validator;
import pl.polsl.anna.pogorzelska.htmlhomophonicencryption.exceptions.NonEnglishInputException;
import pl.polsl.anna.pogorzelska.htmlhomophonicencryption.exceptions.ReadFileFailureException;

/** 
 * Servlet responsible for encryption of provided input. It communicates with model. 
 * This servlet also displays the content of history servlet.
 * 
 * @author Anna Pogorzelska
 * @version 1.1
 */
@WebServlet("/Encrypt")
public class EncryptionServlet extends HttpServlet {

    Transcriptor transcriptor;
    String output;
    Validator validator;
    boolean correctInput;

    /**
     * Constructor creating encryption servlet's object.
     * @throws pl.polsl.anna.pogorzelska.htmlhomophonicencryption.exceptions.ReadFileFailureException
     */
    public EncryptionServlet() throws ReadFileFailureException {
        this.transcriptor = new Transcriptor();
        this.validator = new Validator();
        this.correctInput = false;
    }

    /**
     * Main function of decryption servlet. It processes requests and initialises encryption procedure. It works the same for both GET and POST methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    public void encryptionRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String input = request.getParameter("input");
        
        
        Integer count = (Integer) session.getAttribute("count");
        if (count == null)
        {
            count = 1;
        } else 
        {
            count = count + 1;
        }

        session.setAttribute("count", count);
        
        try {
            if (this.validator.checkValidityOfString(input) ==true) {
                correctInput = true;
            }
        } catch (NonEnglishInputException ex) {
        }
        if (input.length() == 0 || input == null) {
            response.sendError(response.SC_BAD_REQUEST, "You should give a parameter!");
        } 
        else if (this.correctInput == false) {
            response.sendError(response.SC_BAD_REQUEST, "You should give an input from latin letters range!");
        }
        else {
            output = this.transcriptor.encryption(input);
            out.println("<html>\n<body>\n<h1>Input is " + input + "!</h1>\n");
            out.println("<html>\n<body>\n<h1>Output is " + output + "!</h1>\n");
            
            String entry = "Encryption " + "-" + input + "-" + output;

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
        encryptionRequest(request, response);
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
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        encryptionRequest(request, response);
    }
}