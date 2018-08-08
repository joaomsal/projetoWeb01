package web;

import control.Conexao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Turma;

@WebServlet(name = "ServletMatricula", urlPatterns = {"/ServletMatricula"})
public class ServletMatricula extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String [] turmas = request.getParameterValues("turmas");
        Conexao con = new Conexao();
        con.conect();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>\n" +
"<head>\n" +
"	<meta charset=\"utf-8\">\n" +
"	<title>SOLICITAÇÃO DE MATRÍCULA</title>\n" +
"	<link rel=\"stylesheet\" type=\"text/css\" href=\"home.css\">\n" +
"</head>\n" +
"<body>\n" +
"	<div class=\"info\">MATRÍCULA: 201600016896<br/>NOME:João Manoel Santos Almeida<br/>CURSO: 170</div>\n" +
"	<div class=\"corpo\">\n" +
"		<div class=\"tab\">\n" +
"			<form>\n" +
"		<table class=\"tabela\">\n" +
"			<caption id = \"tbt\">TURMAS SELECIONADAS</caption>\n" +
"			<tr class=\"tr\">\n" +
"				<tbody>\n" +
"				<td>CÓDIGO DA DISCIPLINA</td>\n" +
"				<td>NOME DA DISCIPLINA</td>\n" +
"				<td>CARGA HORÁRIA</td>\n" +
"				<td>CÓDIGO DA TURMA</td>\n" +
"				<td>HORÁRIO</td>\n" +
"			</tr>\n" );
            for(int i = 0; i < turmas.length; i++){
                Turma t = new Turma();
                 t = con.selectDturma(turmas[i]);
            out.println(
"			<tr class=\"td\">\n" +
"				<td>"+t.getCod_disc()+"</td>\n" +
"				<td>"+t.getNome_disc()+"</td>\n" +
"				<td>"+t.getCh()+"</td>\n" +
"				<td>"+t.getCod()+"</td>\n" +
"				<td>"+t.getDh()+"</td>\n" +
"				</tr>\n" +
"				</tbody>\n" +
"			</tr>\n");}
            out.println(
"				</tr>\n" +
"		</table>\n" +
"			\n" +
"		<button id=\"bte\" type=\"submit\">CONFIRMAR MATRÍCULA</button>		\n" +
"			</form>\n" +
"		</div>\n" +
"	</div>\n" +
"</body>\n" +
"</html>");
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
