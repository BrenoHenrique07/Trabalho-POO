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

public class VinculoServidorComissaoDAO {

    private List<VinculoServidorComissao> vsc = new ArrayList();

    public List<VinculoServidorComissao> getVinculoServidorComissao() {
        return vsc;
    }

    public void adiciona(VinculoServidorComissao vs) throws SQLException {
        String sql = "insert into vinculoservidorescomissao "
                + "(idcomissao, idservidor, papel, criacao, modificacao, dataEntrada, dataSaida)"
                + " values (?,?,?,?,?,?,?)";
        try (Connection conexao = new FabricaConexao().getConnection()) {

            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, vs.getComissao().getId());
            stmt.setInt(2, vs.getServidor().getId());
            stmt.setString(3, vs.getPapel());
            stmt.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setDate(6, java.sql.Date.valueOf(vs.getDataEntrada()));
            stmt.setDate(7, java.sql.Date.valueOf(vs.getDataSaida()));

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrar() {

        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("select * from vinculoservidorescomissao");
            ResultSet rs = stmt.executeQuery();

            List<VinculoServidorComissao> vss = new ArrayList();

            while (rs.next()) {
                VinculoServidorComissao v1 = new VinculoServidorComissao();

                v1.setId(rs.getInt("idvsc"));
                v1.setPapel(rs.getString("papel"));

                Date currentDate = rs.getDate("criacao");
                LocalDate data = currentDate.toLocalDate();
                v1.setCriacao(data);

                Date currentDate2 = rs.getDate("modificacao");
                LocalDate data2 = currentDate2.toLocalDate();

                if (data2 != null) {
                    v1.setModificacao(data2);
                }

                Date currentDate3 = rs.getDate("dataEntrada");
                LocalDate data3 = currentDate3.toLocalDate();
                v1.setDataEntrada(data3);

                Date currentDate4 = rs.getDate("dataSaida");
                LocalDate data4 = currentDate4.toLocalDate();
                v1.setDataSaida(data4);

                ComissoesDAO c1 = new ComissoesDAO();
                v1.setComissao(c1.buscar(rs.getInt("idcomissao")));

                ServidorDAO s1 = new ServidorDAO();
                v1.setServidor(s1.buscar(rs.getInt("idservidor")));

                vss.add(v1);
            }

            for (int i = 0; i < vss.size(); i++) {
                System.out.println(vss.get(i).toString());
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public VinculoServidorComissao buscar(int id) {

        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("select * from vinculoservidorescomissao where idvsc = " + id);

            ResultSet rs = stmt.executeQuery();

            List<VinculoServidorComissao> vss = new ArrayList();

            while (rs.next()) {
                VinculoServidorComissao v1 = new VinculoServidorComissao();

                v1.setId(rs.getInt("idvsc"));
                v1.setPapel(rs.getString("papel"));

                Date currentDate = rs.getDate("criacao");
                LocalDate data = currentDate.toLocalDate();
                v1.setCriacao(data);

                Date currentDate2 = rs.getDate("modificacao");
                LocalDate data2 = currentDate2.toLocalDate();

                if (data2 != null) {
                    v1.setModificacao(data2);
                }

                Date currentDate3 = rs.getDate("dataEntrada");
                LocalDate data3 = currentDate3.toLocalDate();
                v1.setDataEntrada(data3);

                Date currentDate4 = rs.getDate("dataSaida");
                LocalDate data4 = currentDate4.toLocalDate();
                v1.setDataSaida(data4);

                ComissoesDAO c1 = new ComissoesDAO();
                v1.setComissao(c1.buscar(rs.getInt("idcomissao")));

                ServidorDAO s1 = new ServidorDAO();
                v1.setServidor(s1.buscar(rs.getInt("idservidor")));

                vss.add(v1);
            }
            if (vss.size() != 0) {
                return vss.get(0);
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void alterar(VinculoServidorComissao v, int id) {
        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("update vinculoservidorescomissao set papel = ?, modificacao = ? where idvsc = ?");
            stmt.setString(1, v.getPapel());
            stmt.setDate(2, java.sql.Date.valueOf(v.getModificacao()));
            stmt.setInt(3, id);

            stmt.executeUpdate();

            System.out.println("Atualizado com sucesso");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void excluir(int id) {
        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("delete from vinculoservidorescomissao where idvsc = ?");
            stmt.setInt(1, id);

            stmt.executeUpdate();

            System.out.println("Deletado com sucesso");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
