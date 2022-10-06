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
public class ZMostrarAtas {

    private LocalDate inicio;
    private LocalDate fim;
    private static int cont;

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFim() {
        return fim;
    }

    public void setFim(LocalDate fim) {
        this.fim = fim;
    }

    public void receberDados(LocalDate inicio, LocalDate fim) throws FileNotFoundException, DocumentException {
        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("select * from atadereunioes where dataReuniao between date(?) and date(?)");
            stmt.setDate(1, java.sql.Date.valueOf(inicio));
            stmt.setDate(2, java.sql.Date.valueOf(fim));
            ResultSet rs = stmt.executeQuery();

            List<AtaDeReunioes> atas = new ArrayList();

            while (rs.next()) {
                AtaDeReunioes a1 = new AtaDeReunioes();

                a1.setId(rs.getInt("idatadereunioes"));
                a1.setConteudo(rs.getString("conteudo"));

                Date currentDate = rs.getDate("criacao");
                LocalDate data = currentDate.toLocalDate();
                a1.setCriacao(data);

                Date currentDate2 = rs.getDate("modificacao");
                LocalDate data2 = currentDate2.toLocalDate();

                if (data2 != null) {
                    a1.setModificacao(data2);
                }

                Date currentDate3 = rs.getDate("dataReuniao");
                LocalDate data3 = currentDate3.toLocalDate();
                a1.setDataReuniao(data3);

                ComissoesDAO c1 = new ComissoesDAO();
                a1.setComissao(c1.buscar(rs.getInt("idcomissao")));

                ServidorDAO s1 = new ServidorDAO();
                a1.setServidorSecretario(s1.buscar(rs.getInt("servidor")));

                atas.add(a1);
            }

            Document doc = new Document();
            Document document = new Document();

            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("RelatorioAtas/ata" + cont + ".pdf"));

            document.open();

            document.add(new Paragraph("    RELATÓRIO DE ATAS DE " + inicio + " ATÉ " + fim));
            document.add(new Paragraph("-------------------------------------------------------------------------------------------------"));

            for (int i = 0; i < atas.size(); i++) {
                document.add(new Paragraph("Id da ata = " + atas.get(i).getId()));
                document.add(new Paragraph("Conteúdo da ata = " + atas.get(i).getConteudo()));
                document.add(new Paragraph("Data de criação da ata = " + atas.get(i).getCriacao()));
                document.add(new Paragraph("Data de reunião da ata = " + atas.get(i).getDataReuniao()));
                document.add(new Paragraph("Comissão da ata = " + atas.get(i).getComissao().getComissao()));
                document.add(new Paragraph("Servidor da ata = " + atas.get(i).getServidorSecretario().getNome()));
                document.add(new Paragraph("-------------------------------------------------------------------------------------------------"));
            }

            document.close();
            cont++;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
