package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Aluno;
import model.Turma;


public class Conexao {

    Connection conexao = null;
    
    public void conect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost/projeto1", "root", "123");
        } catch (SQLException ex) {
            System.out.println("erro");
        } catch (ClassNotFoundException ex) {
        }
    }
    
    public ArrayList<Turma> selectTurmas(){
        ArrayList turmas = new ArrayList<Turma>(); 
        try {
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery("select cod_turma,disciplina,dh_turma,nome_disciplina, ch_disciplina from turmas inner join disciplinas on disciplina = cod_disciplina");
            
                while(rs.next()){
                    Turma t = new Turma();
                    t.setCod(rs.getNString("cod_turma"));
                    t.setCod_disc(rs.getNString("disciplina"));
                    t.setNome_disc(rs.getNString("nome_disciplina"));
                    t.setDh(rs.getNString("dh_turma"));
                    t.setCh(rs.getNString("ch_disciplina"));
                    turmas.add(t);
                }
                return turmas;
        } catch (SQLException ex) {
        }
        return turmas;
    }
    
    public String selectAluno(String mat){
        String nome = null;
        try {
            Statement smt = conexao.createStatement();
            ResultSet rs = smt.executeQuery("select nome_aluno from alunos where matricula = '"+mat+"'");
            while(rs.next()){
            nome = rs.getNString("nome_aluno");}
            return nome;
        } catch (SQLException ex) {
        }
        return nome;
    }
    
    public Aluno selectDados(String mat){
        Aluno aluno = new Aluno();
        Statement st;
        try {
            st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select matricula,nome_aluno curso, nome_curso from alunos inner join cursos on curso = cod_curso where matricula = '"+mat+"'");
                while(rs.next()){
                    aluno.setMatricula(rs.getNString("matricula"));
                    aluno.setNome(rs.getNString("nome_aluno"));
                    aluno.setCod(rs.getNString("nome_curso"));
                }
                return aluno;
        } catch (SQLException ex) {
        }
        return aluno;
    }
    
    public Turma selectDturma(String cod){
        Turma t = new Turma();
        try {
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery("select cod_turma,disciplina,dh_turma,nome_disciplina, ch_disciplina from turmas inner join disciplinas on disciplina = cod_disciplina where disciplina = '"+cod+"'");
            
                while(rs.next()){
                    t.setCod(rs.getNString("cod_turma"));
                    t.setCod_disc(rs.getNString("disciplina"));
                    t.setNome_disc(rs.getNString("nome_disciplina"));
                    t.setDh(rs.getNString("dh_turma"));
                    t.setCh(rs.getNString("ch_disciplina"));
                }
                return t;
        } catch (SQLException ex) {
        }
        return t;
    }
}
