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

public class OrientacoesDAO {

    private List<Orientacoes> orientacoes = new ArrayList();

    public List<Orientacoes> getOrientacoes() {
        return orientacoes;
    }

    public void adiciona(Orientacoes orientacao) throws SQLException {
        String sql = "insert into orientacoes "
                + "(tipoOrientacao, nomeAluno, horasSemanais, criacao, modificacao, dataInicio, dataTermino, idservidor)"
                + " values (?,?,?,?,?,?,?,?)";
        try (Connection conexao = new FabricaConexao().getConnection()) {

            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, orientacao.getTipoOrientacao());
            stmt.setString(2, orientacao.getNomeAluno());
            stmt.setInt(3, orientacao.getHorasSemanais());
            stmt.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setDate(6, java.sql.Date.valueOf(orientacao.getDataInicio()));
            stmt.setDate(7, java.sql.Date.valueOf(orientacao.getDataTermino()));
            stmt.setInt(8, orientacao.getServidor().getId());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrar() {

        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("select * from orientacoes");
            ResultSet rs = stmt.executeQuery();

            List<Orientacoes> orientacoes2 = new ArrayList();

            while (rs.next()) {
                Orientacoes o1 = new Orientacoes();

                o1.setId(rs.getInt("idorientacao"));
                o1.setTipoOrientacao(rs.getString("tipoOrientacao"));
                o1.setNomeAluno(rs.getString("nomeAluno"));
                o1.setHorasSemanais(rs.getInt("horasSemanais"));

                Date currentDate = rs.getDate("criacao");
                LocalDate data = currentDate.toLocalDate();
                o1.setCriacao(data);

                Date currentDate2 = rs.getDate("modificacao");
                LocalDate data2 = currentDate2.toLocalDate();

                if (data2 != null) {
                    o1.setModificacao(data2);
                }

                Date currentDate3 = rs.getDate("dataInicio");
                LocalDate data3 = currentDate3.toLocalDate();
                o1.setDataInicio(data3);

                Date currentDate4 = rs.getDate("dataTermino");
                LocalDate data4 = currentDate4.toLocalDate();
                o1.setDataTermino(data4);

                ServidorDAO s = new ServidorDAO();
                o1.setServidor(s.buscar(rs.getInt("idservidor")));

                orientacoes2.add(o1);
            }

            for (int i = 0; i < orientacoes2.size(); i++) {
                System.out.println(orientacoes2.get(i).toString());
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public Orientacoes buscar(int id) {

        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("select * from orientacoes where idorientacao = " + id);

            ResultSet rs = stmt.executeQuery();

            List<Orientacoes> orientacoes2 = new ArrayList();

            while (rs.next()) {
                Orientacoes o1 = new Orientacoes();

                o1.setId(rs.getInt("idorientacao"));
                o1.setTipoOrientacao(rs.getString("tipoOrientacao"));
                o1.setNomeAluno(rs.getString("nomeAluno"));
                o1.setHorasSemanais(rs.getInt("horasSemanais"));

                Date currentDate = rs.getDate("criacao");
                LocalDate data = currentDate.toLocalDate();
                o1.setCriacao(data);

                Date currentDate2 = rs.getDate("modificacao");
                LocalDate data2 = currentDate2.toLocalDate();

                if (data2 != null) {
                    o1.setModificacao(data2);
                }

                Date currentDate3 = rs.getDate("dataInicio");
                LocalDate data3 = currentDate3.toLocalDate();
                o1.setDataInicio(data3);

                Date currentDate4 = rs.getDate("dataTermino");
                LocalDate data4 = currentDate4.toLocalDate();
                o1.setDataTermino(data4);

                ServidorDAO s = new ServidorDAO();
                o1.setServidor(s.buscar(rs.getInt("idservidor")));

                orientacoes2.add(o1);
            }
            if (orientacoes2.size() != 0) {
                return orientacoes2.get(0);
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void alterar(Orientacoes o, int id) {
        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("update orientacoes set nomeAluno = ?, horasSemanais = ?, modificacao = ? where idorientacao = ?");
            stmt.setString(1, o.getNomeAluno());
            stmt.setInt(2, o.getHorasSemanais());
            stmt.setDate(3, java.sql.Date.valueOf(o.getModificacao()));
            stmt.setInt(4, id);

            stmt.executeUpdate();

            System.out.println("Atualizado com sucesso");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void excluir(int id) {
        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("delete from orientacoes where idorientacao = ?");
            stmt.setInt(1, id);

            stmt.executeUpdate();

            System.out.println("Deletado com sucesso");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
