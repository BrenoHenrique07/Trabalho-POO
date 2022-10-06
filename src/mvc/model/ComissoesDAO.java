/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class ComissoesDAO {

    private List<Comissoes> comissoes = new ArrayList();

    public List<Comissoes> getComissoes() {
        return comissoes;
    }

    public void adiciona(Comissoes comissao) throws SQLException {
        String sql = "insert into comissoes "
                + "(comissao, horasSemanais, criacao, modificacao, dataInicio, dataTermino, estado)"
                + " values (?,?,?,?,?,?,?)";
        try (Connection conexao = new FabricaConexao().getConnection()) {

            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, comissao.getComissao());
            stmt.setInt(2, comissao.getHorasSemanais());
            stmt.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setDate(5, java.sql.Date.valueOf(comissao.getDataInicio()));
            stmt.setDate(6, java.sql.Date.valueOf(comissao.getDataTermino()));
            stmt.setBoolean(7, comissao.getEstado());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrar() {

        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("select * from comissoes");
            ResultSet rs = stmt.executeQuery();

            List<Comissoes> comissoes2 = new ArrayList();

            while (rs.next()) {
                Comissoes cs1 = new Comissoes();

                cs1.setId(rs.getInt("idcomissao"));
                cs1.setComissao(rs.getString("comissao"));
                cs1.setHorasSemanais(rs.getInt("horasSemanais"));

                Date currentDate = rs.getDate("criacao");
                LocalDate data = currentDate.toLocalDate();
                cs1.setCriacao(data);

                Date currentDate2 = rs.getDate("modificacao");
                LocalDate data2 = currentDate2.toLocalDate();

                if (data2 != null) {
                    cs1.setModificacao(data2);
                }

                Date currentDate3 = rs.getDate("dataInicio");
                LocalDate data3 = currentDate3.toLocalDate();
                cs1.setDataInicio(data3);

                Date currentDate4 = rs.getDate("dataTermino");
                LocalDate data4 = currentDate4.toLocalDate();
                cs1.setDataTermino(data4);
                
                cs1.setEstado(rs.getBoolean("estado"));

                comissoes2.add(cs1);
            }

            for (int i = 0; i < comissoes2.size(); i++) {
                System.out.println(comissoes2.get(i).toString());
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public Comissoes buscar(int id) {

        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("select * from comissoes where idcomissao = " + id);

            ResultSet rs = stmt.executeQuery();

            List<Comissoes> comissoes2 = new ArrayList();

            while (rs.next()) {
                Comissoes cs1 = new Comissoes();

                cs1.setId(rs.getInt("idcomissao"));
                cs1.setComissao(rs.getString("comissao"));
                cs1.setHorasSemanais(rs.getInt("horasSemanais"));

                Date currentDate = rs.getDate("criacao");
                LocalDate data = currentDate.toLocalDate();
                cs1.setCriacao(data);

                Date currentDate2 = rs.getDate("modificacao");
                LocalDate data2 = currentDate2.toLocalDate();

                if (data2 != null) {
                    cs1.setModificacao(data2);
                }

                Date currentDate3 = rs.getDate("dataInicio");
                LocalDate data3 = currentDate3.toLocalDate();
                cs1.setDataInicio(data3);

                Date currentDate4 = rs.getDate("dataTermino");
                LocalDate data4 = currentDate4.toLocalDate();
                cs1.setDataTermino(data4);
                
                cs1.setEstado(rs.getBoolean("estado"));

                comissoes2.add(cs1);
            }
            if (comissoes2.size() != 0) {
                return comissoes2.get(0);
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void alterar(Comissoes cs, int id) {
        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("update comissoes set horasSemanais = ?, estado = ?, modificacao = ? where idcomissao = ?");
            stmt.setInt(1, cs.getHorasSemanais());
            stmt.setBoolean(2, cs.getEstado());
            stmt.setDate(3, java.sql.Date.valueOf(cs.getModificacao()));
            stmt.setInt(4, id);

            stmt.executeUpdate();

            System.out.println("Atualizado com sucesso");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void excluir(int id) {
        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("delete from comissoes where idcomissao = ?");
            stmt.setInt(1, id);

            stmt.executeUpdate();

            System.out.println("Deletado com sucesso");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
