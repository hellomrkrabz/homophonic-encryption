package pl.polsl.anna.pogorzelska.htmlhomophonicencryption.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

/** 
 * Servlet responsible for displaying the history of operations performed during current session. 
 * Moreover, it includes cookies responsible for keeping track of visits on this servlet. 
 * 
 * @author Anna Pogorzelska
 * @version 1.2
 */
@WebServlet(name = "History", urlPatterns = {"/History"})
public class HistoryServlet extends HttpServlet {

    /**
     * Main function of history servlet. It processes requests and writes operations into a table which is later displayed. It works the same for both GET and POST methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  protected void historyRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        
        Cookie[] cookies = request.getCookies();
        

        Integer visitCount = (Integer) session.getAttribute("visitCount");
        
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("visitCount")) {
                    visitCount = Integer.valueOf(cookie.getValue());
                    break;
                }
            }
        }
        
        if (visitCount == null)
        {
            visitCount = 1;
        } else 
        {
            visitCount = visitCount + 1;
            Cookie cookie = new Cookie("visitCount", visitCount.toString());
            response.addCookie(cookie);
        }

        session.setAttribute("visitCount", visitCount);
        


        
        try ( PrintWriter out = response.getWriter()) {
            out.print("""
                             <style>
                             table {
                               font-family: "font-family:verdana";
                               border-collapse: collapse;
                               width: 100%;
                             }
                     
                             td, th {
                               border: 2px solid #f3d3e4;
                               text-align: center;
                               padding: 10px;
                             }
                     
                             tr:nth-child(even) {
                               background-color: #f3d9d3;
                             }
                             </style>
                             <table>
                               <tr>
                                 <th>Operation</th>
                                 <th>Input</th>
                                 <th>Output</th>
                               </tr>
                       """);
            
            if (session != null) {
                Integer count = (Integer) session.getAttribute("count");
                if (count != null) {
                out.println("History visit count is " + visitCount.toString()+ "!\n");
                    for (int i = 1; i <= count; i++) {
                        String entries = (String) session.getAttribute(i + "entry");
                        if (entries != null) {
                            String[] entry = entries.split("-");
                            if (entry.length != 0) {
                                out.println("<tr>");
                                out.println("<td>" + entry[0] + "</td>");
                                out.println("<td>" + entry[1] + "</td>");
                                out.println("<td>" + entry[2] + "</td>");
                                out.println("</tr>");
                            }
                        }
                    }
                }
            }

            out.print("""     
                          </table><br>
                            <button onclick="location.href='/htmlhomophonicencryption/'">Main Page</button>
                            </body>
                    </html> 
                      """);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        historyRequest(request, response);
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
            throws ServletException, IOException {
        historyRequest(request, response);
    }
    }




