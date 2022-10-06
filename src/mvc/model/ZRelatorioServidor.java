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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author breno
 */
public class ZRelatorioServidor {

    private static int cont;

    public void gerarRelatorioServidor(int id) throws FileNotFoundException, DocumentException {
        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("select nome, cargo from servidor where idservidor = " + id);
            
            int somaTotal = 0;
            
            ResultSet rs = stmt.executeQuery();
            List<String> nome = new ArrayList();
            List<String> cargo = new ArrayList();

            while (rs.next()) {
                nome.add(rs.getString("nome"));
                cargo.add(rs.getString("cargo"));
            }

            PreparedStatement stmt2 = conexao.prepareStatement("select atividades.descricao, atividades.horasSemanais "
                    + " from atividades where idservidor = " + id);
            ResultSet rs2 = stmt2.executeQuery();
            List<Atividades> atvs = new ArrayList();

            while (rs2.next()) {
                Atividades a1 = new Atividades();

                a1.setDescricao(rs2.getString("descricao"));
                a1.setHorasSemanais(rs2.getInt("horasSemanais"));

                atvs.add(a1);
            }

            PreparedStatement stmt3 = conexao.prepareStatement("select disciplina.nome, disciplina.cargaHoraria, disciplina.planejamento "
                    + " from disciplina inner join ofertadisciplinacurso on disciplina.iddisciplina = ofertadisciplinacurso.iddisciplina"
                    + " where ofertadisciplinacurso.idservidor = " + id);
            ResultSet rs3 = stmt3.executeQuery();
            List<Disciplina> ds = new ArrayList();

            while (rs3.next()) {
                Disciplina d1 = new Disciplina();

                d1.setNome(rs3.getString("nome"));
                d1.setCargaHoraria(rs3.getInt("cargaHoraria"));
                d1.setPlanejamento(rs3.getInt("planejamento"));

                ds.add(d1);
            }

            PreparedStatement stmt4 = conexao.prepareStatement("select orientacoes.tipoOrientacao, orientacoes.nomeAluno, orientacoes.horasSemanais "
                    + " from orientacoes where idservidor = " + id);
            ResultSet rs4 = stmt4.executeQuery();
            List<Orientacoes> ots = new ArrayList();

            while (rs4.next()) {
                Orientacoes o1 = new Orientacoes();

                o1.setTipoOrientacao(rs4.getString("tipoOrientacao"));
                o1.setNomeAluno(rs4.getString("nomeAluno"));
                o1.setHorasSemanais(rs4.getInt("horasSemanais"));

                ots.add(o1);
            }

            PreparedStatement stmt5 = conexao.prepareStatement("select comissoes.comissao, comissoes.horasSemanais"
                    + " from comissoes inner join vinculoservidorescomissao on comissoes.idcomissao = vinculoservidorescomissao.idcomissao"
                    + " where vinculoservidorescomissao.idservidor = " + id);
            ResultSet rs5 = stmt5.executeQuery();
            List<Comissoes> cs = new ArrayList();

            while (rs5.next()) {
                Comissoes o1 = new Comissoes();

                o1.setComissao(rs5.getString("comissao"));
                o1.setHorasSemanais(rs5.getInt("horasSemanais"));

                cs.add(o1);
            }

            Document doc = new Document();
            Document document = new Document();

            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("RelatorioServidor/Servidor" + cont + ".pdf"));

            document.open();

            document.add(new Paragraph("    Relatório do Servidor " + nome.get(0) + " " + cargo.get(0)));
            document.add(new Paragraph("--------------------------------------------------------------------------------------------------------"));

            document.add(new Paragraph("\n"));

            for (int i = 0; i < atvs.size(); i++) {
                document.add(new Paragraph("ATIVIDADE"));
                document.add(new Paragraph("Descricao atividade = " + atvs.get(i).getDescricao()));
                document.add(new Paragraph("Horas semanais = " + atvs.get(i).getHorasSemanais()));
                somaTotal += atvs.get(i).getHorasSemanais();
            }

            document.add(new Paragraph("\n"));

            for (int i = 0; i < ds.size(); i++) {
                document.add(new Paragraph("DISCIPLINA"));
                document.add(new Paragraph("Nome disciplina = " + ds.get(i).getNome()));
                document.add(new Paragraph("Carga Horaria disciplina = " + ds.get(i).getCargaHoraria()));
                document.add(new Paragraph("Planejamento disciplina = " + ds.get(i).getPlanejamento()));
                
                somaTotal += ds.get(i).getPlanejamento() + ds.get(i).getCargaHoraria();
            }

            document.add(new Paragraph("\n"));

            for (int i = 0; i < ots.size(); i++) {
                document.add(new Paragraph("ORIENTACOES"));
                document.add(new Paragraph("Tipo orientacao = " + ots.get(i).getTipoOrientacao()));
                document.add(new Paragraph("Nome do aluno = " + ots.get(i).getNomeAluno()));
                document.add(new Paragraph("Horas Semanais = " + ots.get(i).getHorasSemanais()));
                
                somaTotal += ots.get(i).getHorasSemanais();
            }
            
            document.add(new Paragraph("\n"));

            for (int i = 0; i < cs.size(); i++) {
                document.add(new Paragraph("COMISSOES"));
                document.add(new Paragraph("Comissao = " + cs.get(i).getComissao()));
                document.add(new Paragraph("Horas Semanais = " + cs.get(i).getHorasSemanais()));
                
                somaTotal += cs.get(i).getHorasSemanais();
            }
            
            document.add(new Paragraph("\nTotal de horas = " + somaTotal + " horas"));
            document.add(new Paragraph("--------------------------------------------------------------------------------------------------------"));

            document.close();
            cont++;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
