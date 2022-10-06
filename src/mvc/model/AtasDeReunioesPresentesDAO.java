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

public class AtasDeReunioesPresentesDAO {

    private List<AtaDeReunioesPresentes> atareunioesp = new ArrayList();

    public List<AtaDeReunioesPresentes> getAtaDeReunioesPresentes() {
        return atareunioesp;
    }

    public void adiciona(AtaDeReunioesPresentes atp) throws SQLException {
        String sql = "insert into atadereunioespresentes "
                + "(criacao, modificacao, idcomissao, idatadereunioes, idservidor)"
                + " values (?,?,?,?,?)";
        try (Connection conexao = new FabricaConexao().getConnection()) {

            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setInt(3, atp.getComissao().getId());
            stmt.setInt(4, atp.getAtadereuniao().getId());
            stmt.setInt(5, atp.getServidor().getId());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrar() {

        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("select * from atadereunioespresentes");
            ResultSet rs = stmt.executeQuery();

            List<AtaDeReunioesPresentes> atasp = new ArrayList();

            while (rs.next()) {
                AtaDeReunioesPresentes a1 = new AtaDeReunioesPresentes();

                a1.setId(rs.getInt("idatadereunioespresentes"));

                Date currentDate = rs.getDate("criacao");
                LocalDate data = currentDate.toLocalDate();
                a1.setCriacao(data);

                Date currentDate2 = rs.getDate("modificacao");
                LocalDate data2 = currentDate2.toLocalDate();

                if (data2 != null) {
                    a1.setModificacao(data2);
                }

                ComissoesDAO c1 = new ComissoesDAO();
                a1.setComissao(c1.buscar(rs.getInt("idcomissao")));
                
                AtaDeReunioesDAO at1 = new AtaDeReunioesDAO();
                a1.setAtadereuniao(at1.buscar(rs.getInt("idatadereunioes")));
                
                ServidorDAO s1 = new ServidorDAO();
                a1.setServidor(s1.buscar(rs.getInt("idservidor")));

                atasp.add(a1);
            }

            for (int i = 0; i < atasp.size(); i++) {
                System.out.println(atasp.get(i).toString());
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public AtaDeReunioesPresentes buscar(int id) {

        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("select * from atadereunioespresentes where idatadereunioespresentes = " + id);

            ResultSet rs = stmt.executeQuery();

            List<AtaDeReunioesPresentes> atasp = new ArrayList();

            while (rs.next()) {
                AtaDeReunioesPresentes a1 = new AtaDeReunioesPresentes();

                a1.setId(rs.getInt("idatadereunioesPresentes"));

                Date currentDate = rs.getDate("criacao");
                LocalDate data = currentDate.toLocalDate();
                a1.setCriacao(data);

                Date currentDate2 = rs.getDate("modificacao");
                LocalDate data2 = currentDate2.toLocalDate();

                if (data2 != null) {
                    a1.setModificacao(data2);
                }

                ComissoesDAO c1 = new ComissoesDAO();
                a1.setComissao(c1.buscar(rs.getInt("idcomissao")));
                
                AtaDeReunioesDAO at1 = new AtaDeReunioesDAO();
                a1.setAtadereuniao(at1.buscar(rs.getInt("idatadereunioes")));
                
                ServidorDAO s1 = new ServidorDAO();
                a1.setServidor(s1.buscar(rs.getInt("idservidor")));

                atasp.add(a1);
            }

            for (int i = 0; i < atasp.size(); i++) {
                System.out.println(atasp.get(i).toString());
            }
            if (atasp.size() != 0) {
                return atasp.get(0);
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void alterar(AtaDeReunioesPresentes a, int id) {
        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("update atadereunioespresentes set idatadereunioes = ?, modificacao = ? where idatadereunioespresentes = ?");
            stmt.setInt(1, a.getAtadereuniao().getId());
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
            PreparedStatement stmt = conexao.prepareStatement("delete from atadereunioespresentes where idatadereunioespresentes = ?");
            stmt.setInt(1, id);

            stmt.executeUpdate();

            System.out.println("Deletado com sucesso");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
