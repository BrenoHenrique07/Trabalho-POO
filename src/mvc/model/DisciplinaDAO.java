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

public class DisciplinaDAO {

    private List<Disciplina> disciplinas = new ArrayList();

    public List<Disciplina> getDisciplina() {
        return disciplinas;
    }

    public void adiciona(Disciplina disciplina) throws SQLException {
        String sql = "insert into disciplina "
                + "(nome, criacao, modificacao, periodicidade, idcurso, cargaHoraria, planejamento)"
                + " values (?,?,?,?,?,?,?)";
        try (Connection conexao = new FabricaConexao().getConnection()) {

            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, disciplina.getNome());
            stmt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setString(4, disciplina.getPeriodicidade());
            stmt.setInt(5, disciplina.getCurso().getId());
            stmt.setInt(6, disciplina.getCargaHoraria());
            stmt.setInt(7, disciplina.getPlanejamento());


            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrar() {

        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("select * from disciplina");
            ResultSet rs = stmt.executeQuery();

            List<Disciplina> disciplinas = new ArrayList();

            while (rs.next()) {
                Disciplina d1 = new Disciplina();
                
                d1.setId(rs.getInt("iddisciplina"));
                d1.setNome(rs.getString("nome"));
                d1.setPeriodicidade(rs.getString("periodicidade"));
                d1.setCargaHoraria(rs.getInt("cargaHoraria"));
                d1.setPlanejamento(rs.getInt("planejamento"));

                Date currentDate = rs.getDate("criacao");
                LocalDate data = currentDate.toLocalDate();

                Date currentDate2 = rs.getDate("modificacao");
                LocalDate data2 = currentDate2.toLocalDate();

                if (data2 != null) {
                    d1.setModificacao(data2);
                }

                CursoDAO c = new CursoDAO();

                d1.setCriacao(data);
                d1.setCurso(c.buscar(rs.getInt("idcurso")));

                disciplinas.add(d1);
            }

            for (int i = 0; i < disciplinas.size(); i++) {
                System.out.println(disciplinas.get(i).toString());
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public Disciplina buscar(int id) {

        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("select * from disciplina where iddisciplina = " + id);

            ResultSet rs = stmt.executeQuery();

             List<Disciplina> disciplinas = new ArrayList();

            while (rs.next()) {
                Disciplina d1 = new Disciplina();
                
                d1.setId(rs.getInt("iddisciplina"));
                d1.setNome(rs.getString("nome"));
                d1.setPeriodicidade(rs.getString("periodicidade"));
                d1.setCargaHoraria(rs.getInt("cargaHoraria"));
                d1.setPlanejamento(rs.getInt("planejamento"));

                Date currentDate = rs.getDate("criacao");
                LocalDate data = currentDate.toLocalDate();

                Date currentDate2 = rs.getDate("modificacao");
                LocalDate data2 = currentDate2.toLocalDate();

                if (data2 != null) {
                    d1.setModificacao(data2);
                }

                CursoDAO c = new CursoDAO();

                d1.setCriacao(data);
                d1.setCurso(c.buscar(rs.getInt("idcurso")));

                disciplinas.add(d1);
            }
            if (disciplinas.size() != 0) {
                return disciplinas.get(0);
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void alterar(Disciplina d, int id) {
        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("update disciplina set nome = ?, cargaHoraria = ?, planejamento = ?, modificacao = ? where iddisciplina = ?");
            stmt.setString(1, d.getNome());
            stmt.setInt(2, d.getCargaHoraria());
            stmt.setInt(3, d.getPlanejamento());
            stmt.setDate(4, java.sql.Date.valueOf(d.getModificacao()));
            stmt.setInt(5, id);

            stmt.executeUpdate();

            System.out.println("Atualizado com sucesso");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void excluir(int id) {
        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("delete from disciplina where iddisciplina = ?");
            stmt.setInt(1, id);

            stmt.executeUpdate();

            System.out.println("Deletado com sucesso");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
