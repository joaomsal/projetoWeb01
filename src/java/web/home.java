package web;

import control.Conexao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Turma;

@WebServlet(name = "home", urlPatterns = {"/home"})
public class home extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Conexao con = new Conexao();
        con.conect();
        ArrayList<Turma> t = con.selectTurmas();
        response.setContentType("text/html;charset=UTF-8");
        String mat = request.getParameter("mat");
        String nome = con.selectAluno(mat);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>\n" +
"<head>\n" +
"	<meta charset=\"utf-8\">\n" +
"	<title>MATRÍCULA ONLINE</title>\n" +
"	<link rel=\"stylesheet\" type=\"text/css\" href=\"home.css\">\n" +
"</head>\n" +
"<body>\n"); 
            if(nome == null){
                out.println("	<div class=\"info\">ALUNO NÃO MATRICULADO</div>\n"
                +"</body>\n"+"</html>");
                
            } else{
                out.println(
"	<div class=\"info\"> BEM-VINDO(A), "+ nome +
                        "</div>\n" +
"	<div class=\"corpo\">\n" +
"		<div class=\"tab\">\n" +
  "			<form action = \"ServletMatricula\">\n" +
"		<table class=\"tabela\">\n" +
"			<caption id = \"tbt\">TURMAS DISPONÍVEIS</caption>\n" +
"			<tr class=\"tr\">\n" +
"				<td>CÓDIGO DA DISCIPLINA</td>\n" +
"				<td>NOME DA DISCIPLINA</td>\n" +
"				<td>CARGA HORÁRIA</td>\n" +
"				<td>CÓDIGO DA TURMA</td>\n" +
"				<td>HORÁRIO</td>\n" +
"				<td>SELECIONAR</td>\n" +
"			</tr>");
                    for(int i =0; i< t.size(); i ++){
                        out.println(
                                "<tbody>\n" +
                        "<tr class=\"td\">\n"+
"				<td>"+t.get(i).getCod_disc()+"</td>\n" +
"				<td>"+t.get(i).getNome_disc()+"</td>\n" +
"				<td>"+t.get(i).getCh()+"</td>\n" +
"				<td>"+t.get(i).getCod()+"</td>\n" +
"				<td>"+t.get(i).getDh()+"</td>\n" +
"				<td><input type=\"checkbox\" name=\"turmas\"  value="+t.get(i).getCod_disc()+"></td>\n" +
"			</tr>\n");}
                    out.println(
                            "</tbody>\n"+
"</tr>\n" +
"		</table>\n" +
"		<button id=\"bte\" type=\"submit\">REALIZAR MATRÍCULA</button>		\n" +
"			</form>\n" +
"		</div>\n" +
"	</div>\n" +
"</body>\n" +
"</html>");}
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
