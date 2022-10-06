/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author breno
 */
public class ZGerarAulas {

    private static int cont;

    public void gerarAulas(int id) throws FileNotFoundException, DocumentException {
        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("select nome from campus where idcampus = " + id);
            ResultSet rs = stmt.executeQuery();
            List<String> nome = new ArrayList();

            while (rs.next()) {
                nome.add(rs.getString("nome"));
            }

            PreparedStatement stmt2 = conexao.prepareStatement("select curso.nome from curso inner join campus on curso.idcampus = campus.idcampus where "
                    + " campus.idcampus = " + id);
            ResultSet rs2 = stmt2.executeQuery();
            List<String> nomeCursos = new ArrayList();

            while (rs2.next()) {
                nomeCursos.add(rs2.getString("nome"));
            }

            PreparedStatement stmt3 = conexao.prepareStatement("select disciplina.nome, disciplina.cargaHoraria, disciplina.planejamento, disciplina.periodicidade"
                    + " from disciplina inner join curso on disciplina.idcurso = curso.idcurso "
                    + " where curso.idcampus = " + id);
            ResultSet rs3 = stmt3.executeQuery();
            List<Disciplina> nomeDisciplina = new ArrayList();

            while (rs3.next()) {
                Disciplina d1 = new Disciplina();
                d1.setNome(rs3.getString("nome"));
                d1.setCargaHoraria(rs3.getInt("cargaHoraria"));
                d1.setPlanejamento(rs3.getInt("planejamento"));
                d1.setPeriodicidade(rs3.getString("periodicidade"));

                nomeDisciplina.add(d1);
            }

            PreparedStatement stmt4 = conexao.prepareStatement("select servidor.nome from servidor inner join ofertadisciplinacurso"
                    + " on servidor.idservidor = ofertadisciplinacurso.idservidor " + "inner join campus on servidor.idcampus = campus.idcampus "
                    + " where campus.idcampus = " + id);
            ResultSet rs4 = stmt4.executeQuery();
            List<String> nomeServidor = new ArrayList();

            while (rs4.next()) {
                nomeServidor.add(rs4.getString("nome"));
            }

            Document doc = new Document();
            Document document = new Document();

            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("RelatorioAulas/Aulas" + cont + ".pdf"));

            document.open();

            document.add(new Paragraph("    Relatório de aulas ofertadas do Campus " + nome.get(0)));
            document.add(new Paragraph("--------------------------------------------------------------------------------------------------------"));

            for (int i = 0; i < nomeCursos.size(); i++) {
                document.add(new Paragraph("Cursos = " + nomeCursos.get(i)));
            }

            for (int i = 0; i < nomeDisciplina.size(); i++) {
                document.add(new Paragraph("Nome Disciplina = " + nomeDisciplina.get(i).getNome()));
                document.add(new Paragraph("Carga Horaria = " + nomeDisciplina.get(i).getCargaHoraria()));
                document.add(new Paragraph("Planejamento = " + nomeDisciplina.get(i).getPlanejamento()));
                document.add(new Paragraph("Periodicidade = " + nomeDisciplina.get(i).getPeriodicidade()));
            }

            for (int i = 0; i < nomeServidor.size(); i++) {
                document.add(new Paragraph("Servidor = " + nomeServidor.get(i)));
            }
            document.add(new Paragraph("--------------------------------------------------------------------------------------------------------"));

            document.close();
            cont++;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
