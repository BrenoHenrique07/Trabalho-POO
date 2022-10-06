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

public class ServidorDAO {

    private List<Servidor> servidores = new ArrayList();

    public List<Servidor> getServidor() {
        return servidores;
    }

    public void adiciona(Servidor servidor) throws SQLException {
        String sql = "insert into servidor "
                + "(nome,email,cargo,login,senha,usuario, criacao, modificacao, idcampus)"
                + " values (?,?,?,?,?,?,?,?,?)";
        try (Connection conexao = new FabricaConexao().getConnection()) {

            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, servidor.getNome());
            stmt.setString(2, servidor.getEmail());
            stmt.setString(3, servidor.getCargo());
            stmt.setString(4, servidor.getLogin());
            stmt.setString(5, servidor.getSenha());
            stmt.setString(6, servidor.getUsuario());
            stmt.setDate(7, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setDate(8, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setInt(9, servidor.getCampus().getId());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrar() {

        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("select * from servidor");
            ResultSet rs = stmt.executeQuery();

            List<Servidor> servidores = new ArrayList();

            while (rs.next()) {
                Servidor s1 = new Servidor();

                s1.setId(rs.getInt("idservidor"));
                s1.setNome(rs.getString("nome"));
                s1.setEmail(rs.getString("email"));
                s1.setCargo(rs.getString("cargo"));
                s1.setLogin(rs.getString("login"));
                s1.setSenha(rs.getString("senha"));
                s1.setUsuario(rs.getString("usuario"));

                Date currentDate = rs.getDate("criacao");
                LocalDate data = currentDate.toLocalDate();

                Date currentDate2 = rs.getDate("modificacao");
                LocalDate data2 = currentDate2.toLocalDate();

                if (data2 != null) {
                    s1.setModificacao(data2);
                }

                CampusDAO c = new CampusDAO();

                s1.setCriacao(data);
                s1.setCampus(c.buscar(rs.getInt("idcampus")));

                servidores.add(s1);
            }

            for (int i = 0; i < servidores.size(); i++) {
                System.out.println(servidores.get(i).toString());
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public Servidor buscar(int id) {

        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("select * from servidor where idservidor = " + id);

            ResultSet rs = stmt.executeQuery();

            List<Servidor> servidores = new ArrayList();

            while (rs.next()) {
                Servidor s1 = new Servidor();

                s1.setId(rs.getInt("idservidor"));
                s1.setNome(rs.getString("nome"));
                s1.setEmail(rs.getString("email"));
                s1.setCargo(rs.getString("cargo"));
                s1.setLogin(rs.getString("login"));
                s1.setSenha(rs.getString("senha"));
                s1.setUsuario(rs.getString("usuario"));

                Date currentDate = rs.getDate("criacao");
                LocalDate data = currentDate.toLocalDate();

                Date currentDate2 = rs.getDate("modificacao");
                LocalDate data2 = currentDate2.toLocalDate();

                if (data2 != null) {
                    s1.setModificacao(data2);
                }

                CampusDAO c = new CampusDAO();

                s1.setCriacao(data);
                s1.setCampus(c.buscar(rs.getInt("idcampus")));

                servidores.add(s1);
            }

            if (servidores.size() != 0) {
                return servidores.get(0);
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void alterar(Servidor s, int id) {
        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("update servidor set nome = ?, cargo = ?, modificacao = ? where idservidor = ?");
            stmt.setString(1, s.getNome());
            stmt.setString(2, s.getCargo());
            stmt.setDate(3, java.sql.Date.valueOf(s.getModificacao()));
            stmt.setInt(4, id);

            stmt.executeUpdate();

            System.out.println("Atualizado com sucesso");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void excluir(int id) {
        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("delete from servidor where idservidor = ?");
            stmt.setInt(1, id);

            stmt.executeUpdate();

            System.out.println("Deletado com sucesso");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
