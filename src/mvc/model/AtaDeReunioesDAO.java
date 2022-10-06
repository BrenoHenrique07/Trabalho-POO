package mvc.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AtaDeReunioesDAO {

    private List<AtaDeReunioes> atareunioes = new ArrayList();

    public List<AtaDeReunioes> getAtaDeReunioes() {
        return atareunioes;
    }

    public void adiciona(AtaDeReunioes at) throws SQLException {
        String sql = "insert into atadereunioes "
                + "(idcomissao, conteudo, servidor, criacao, modificacao, dataReuniao)"
                + " values (?,?,?,?,?,?)";
        try (Connection conexao = new FabricaConexao().getConnection()) {

            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, at.getComissao().getId());
            stmt.setString(2, at.getConteudo());
            stmt.setInt(3, at.getServidorSecretario().getId());
            stmt.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setDate(6, java.sql.Date.valueOf(at.getDataReuniao()));

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrar() {

        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("select * from atadereunioes");
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

            for (int i = 0; i < atas.size(); i++) {
                System.out.println(atas.get(i).toString());
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public AtaDeReunioes buscar(int id) {

        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("select * from atadereunioes where idatadereunioes = " + id);

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
            if (atas.size() != 0) {
                return atas.get(0);
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void alterar(AtaDeReunioes a, int id) {
        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("update atadereunioes set conteudo = ?, modificacao = ? where idatadereunioes = ?");
            stmt.setString(1, a.getConteudo());
            stmt.setDate(2, java.sql.Date.valueOf(a.getModificacao()));
            stmt.setInt(3, id);

            stmt.executeUpdate();

            System.out.println("Atualizado com sucesso");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void excluir(int id) {
        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("delete from atadereunioes where idatadereunioes = ?");
            stmt.setInt(1, id);

            stmt.executeUpdate();

            System.out.println("Deletado com sucesso");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
