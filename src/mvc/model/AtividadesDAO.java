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

public class AtividadesDAO {

    private List<Atividades> atividades = new ArrayList();

    public List<Atividades> getAtividades() {
        return atividades;
    }

    public void adiciona(Atividades atividade) throws SQLException {
        String sql = "insert into atividades "
                + "(descricao, horasSemanais, criacao, modificacao, dataInicio, dataTermino, idservidor)"
                + " values (?,?,?,?,?,?,?)";
        try (Connection conexao = new FabricaConexao().getConnection()) {

            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, atividade.getDescricao());
            stmt.setInt(2, atividade.getHorasSemanais());
            stmt.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setDate(5, java.sql.Date.valueOf(atividade.getDataInicio()));
            stmt.setDate(6, java.sql.Date.valueOf(atividade.getDataTermino()));
            stmt.setInt(7, atividade.getServidor().getId());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrar() {

        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("select * from atividades");
            ResultSet rs = stmt.executeQuery();

            List<Atividades> atividades2 = new ArrayList();

            while (rs.next()) {
                Atividades a1 = new Atividades();

                a1.setId(rs.getInt("idatividade"));
                a1.setDescricao(rs.getString("descricao"));
                a1.setHorasSemanais(rs.getInt("horasSemanais"));

                Date currentDate = rs.getDate("criacao");
                LocalDate data = currentDate.toLocalDate();
                a1.setCriacao(data);

                Date currentDate2 = rs.getDate("modificacao");
                LocalDate data2 = currentDate2.toLocalDate();

                if (data2 != null) {
                    a1.setModificacao(data2);
                }

                Date currentDate3 = rs.getDate("dataInicio");
                LocalDate data3 = currentDate3.toLocalDate();
                a1.setDataInicio(data3);

                Date currentDate4 = rs.getDate("dataTermino");
                LocalDate data4 = currentDate4.toLocalDate();
                a1.setDataTermino(data4);

                ServidorDAO s = new ServidorDAO();
                a1.setServidor(s.buscar(rs.getInt("idservidor")));

                atividades2.add(a1);
            }

            for (int i = 0; i < atividades2.size(); i++) {
                System.out.println(atividades2.get(i).toString());
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public Atividades buscar(int id) {

        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("select * from atividades where idatividade = " + id);

            ResultSet rs = stmt.executeQuery();

             List<Atividades> atividades2 = new ArrayList();

            while (rs.next()) {
                Atividades a1 = new Atividades();

                a1.setId(rs.getInt("idatividade"));
                a1.setDescricao(rs.getString("descricao"));
                a1.setHorasSemanais(rs.getInt("horasSemanais"));

                Date currentDate = rs.getDate("criacao");
                LocalDate data = currentDate.toLocalDate();
                a1.setCriacao(data);

                Date currentDate2 = rs.getDate("modificacao");
                LocalDate data2 = currentDate2.toLocalDate();

                if (data2 != null) {
                    a1.setModificacao(data2);
                }

                Date currentDate3 = rs.getDate("dataInicio");
                LocalDate data3 = currentDate3.toLocalDate();
                a1.setDataInicio(data3);

                Date currentDate4 = rs.getDate("dataTermino");
                LocalDate data4 = currentDate4.toLocalDate();
                a1.setDataTermino(data4);

                ServidorDAO s = new ServidorDAO();
                a1.setServidor(s.buscar(rs.getInt("idservidor")));

                atividades2.add(a1);
            }
            if (atividades2.size() != 0) {
                return atividades2.get(0);
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void alterar(Atividades a, int id) {
        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("update atividades set descricao = ?, horasSemanais = ?, modificacao = ? where idatividade = ?");
            stmt.setString(1, a.getDescricao());
            stmt.setInt(2, a.getHorasSemanais());
            stmt.setDate(3, java.sql.Date.valueOf(a.getModificacao()));
            stmt.setInt(4, id);

            stmt.executeUpdate();

            System.out.println("Atualizado com sucesso");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void excluir(int id) {
        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("delete from atividades where idatividade = ?");
            stmt.setInt(1, id);

            stmt.executeUpdate();

            System.out.println("Deletado com sucesso");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
