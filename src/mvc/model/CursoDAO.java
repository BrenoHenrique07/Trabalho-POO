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

public class CursoDAO {

    private List<Curso> cursos = new ArrayList();

    public List<Curso> getCurso() {
        return cursos;
    }

    public void adiciona(Curso curso) throws SQLException {
        String sql = "insert into curso "
                + "(nome, criacao, modificacao, anoInicio, anoTermino, estado, idcampus)"
                + " values (?,?,?,?,?,?,?)";
        try (Connection conexao = new FabricaConexao().getConnection()) {

            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, curso.getNome());
            stmt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setInt(4, curso.getAnoInicio());
            stmt.setInt(5, curso.getAnoTermino());
            stmt.setBoolean(6, curso.isEstado());
            stmt.setInt(7, curso.getCampusC().getId());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrar() {

        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("select * from curso");
            ResultSet rs = stmt.executeQuery();

            List<Curso> cursos = new ArrayList();

            while (rs.next()) {
                Curso c1 = new Curso();

                c1.setId(rs.getInt("idcurso"));
                c1.setNome(rs.getString("nome"));
                c1.setAnoInicio(rs.getInt("anoInicio"));
                c1.setAnoTermino(rs.getInt("anoTermino"));
                c1.setEstado(rs.getBoolean("estado"));

                Date currentDate = rs.getDate("criacao");
                LocalDate data = currentDate.toLocalDate();

                Date currentDate2 = rs.getDate("modificacao");
                LocalDate data2 = currentDate2.toLocalDate();

                if (data2 != null) {
                    c1.setModificao(data2);
                }

                CampusDAO c = new CampusDAO();

                c1.setCriacao(data);
                c1.setCampusC(c.buscar(rs.getInt("idcampus")));

                cursos.add(c1);
            }

            for (int i = 0; i < cursos.size(); i++) {
                System.out.println(cursos.get(i).toString());
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public Curso buscar(int id) {

        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("select * from curso where idcurso = " + id);

            ResultSet rs = stmt.executeQuery();

            List<Curso> cursos = new ArrayList();

            while (rs.next()) {
                Curso c1 = new Curso();

                c1.setId(rs.getInt("idcurso"));
                c1.setNome(rs.getString("nome"));
                c1.setAnoInicio(rs.getInt("anoInicio"));
                c1.setAnoTermino(rs.getInt("anoTermino"));
                c1.setEstado(rs.getBoolean("estado"));

                Date currentDate = rs.getDate("criacao");
                LocalDate data = currentDate.toLocalDate();

                Date currentDate2 = rs.getDate("modificacao");
                LocalDate data2 = currentDate2.toLocalDate();

                if (data2 != null) {
                    c1.setModificao(data2);
                }

                CampusDAO c = new CampusDAO();

                c1.setCriacao(data);
                c1.setCampusC(c.buscar(rs.getInt("idcampus")));

                cursos.add(c1);
            }

            if (cursos.size() != 0) {
                return cursos.get(0);
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void alterar(Curso c, int id) {
        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("update curso set nome = ?, estado = ?, modificacao = ? where idcurso = ?");
            stmt.setString(1, c.getNome());
            stmt.setBoolean(2, c.isEstado());
            stmt.setDate(3, java.sql.Date.valueOf(c.getModificao()));
            stmt.setInt(4, id);

            stmt.executeUpdate();

            System.out.println("Atualizado com sucesso");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void excluir(int id) {
        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("delete from curso where idcurso = ?");
            stmt.setInt(1, id);

            stmt.executeUpdate();

            System.out.println("Deletado com sucesso");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
