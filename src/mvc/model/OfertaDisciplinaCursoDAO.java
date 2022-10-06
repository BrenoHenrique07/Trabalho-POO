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

public class OfertaDisciplinaCursoDAO {

    private List<OfertaDisciplinaCurso> ofcs = new ArrayList();

    public List<OfertaDisciplinaCurso> getOfertaDisciplinaCurso() {
        return ofcs;
    }

    public void adiciona(OfertaDisciplinaCurso ofc) throws SQLException {
        String sql = "insert into ofertadisciplinacurso "
                + "(criacao, modificacao, semestre, aulasSemanais, ano, iddisciplina, idservidor)"
                + " values (?,?,?,?,?,?,?)";
        try (Connection conexao = new FabricaConexao().getConnection()) {

            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setInt(3, ofc.getSemestre());
            stmt.setInt(4, ofc.getAulasSemanais());
            stmt.setString(5, ofc.getAno());
            stmt.setInt(6, ofc.getDisciplina().getId());
            stmt.setInt(7, ofc.getProfessor().getId());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrar() {

        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("select * from ofertadisciplinacurso");
            ResultSet rs = stmt.executeQuery();

            List<OfertaDisciplinaCurso> ofcs = new ArrayList();

            while (rs.next()) {
                OfertaDisciplinaCurso of1 = new OfertaDisciplinaCurso();

                of1.setId(rs.getInt("idofc"));
                of1.setSemestre(rs.getInt("semestre"));
                of1.setAulasSemanais(rs.getInt("aulasSemanais"));
                of1.setAno(rs.getString("ano"));

                Date currentDate = rs.getDate("criacao");
                LocalDate data = currentDate.toLocalDate();

                Date currentDate2 = rs.getDate("modificacao");
                LocalDate data2 = currentDate2.toLocalDate();

                if (data2 != null) {
                    of1.setModificacao(data2);
                }
                of1.setCriacao(data);
                
                DisciplinaDAO d1 = new DisciplinaDAO();
                of1.setDisciplina(d1.buscar(rs.getInt("iddisciplina")));
                
                ServidorDAO s1 = new ServidorDAO();
                of1.setProfessor(s1.buscar(rs.getInt("idservidor")));

                ofcs.add(of1);
            }

            for (int i = 0; i < ofcs.size(); i++) {
                System.out.println(ofcs.get(i).toString());
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public OfertaDisciplinaCurso buscar(int id) {

        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("select * from ofertadisciplinacurso where idofc = " + id);

            ResultSet rs = stmt.executeQuery();

            List<OfertaDisciplinaCurso> ofcs = new ArrayList();

            while (rs.next()) {
                OfertaDisciplinaCurso of1 = new OfertaDisciplinaCurso();

                of1.setId(rs.getInt("idofc"));
                of1.setSemestre(rs.getInt("semestre"));
                of1.setAulasSemanais(rs.getInt("aulasSemanais"));
                of1.setAno(rs.getString("ano"));

                Date currentDate = rs.getDate("criacao");
                LocalDate data = currentDate.toLocalDate();

                Date currentDate2 = rs.getDate("modificacao");
                LocalDate data2 = currentDate2.toLocalDate();

                if (data2 != null) {
                    of1.setModificacao(data2);
                }
                of1.setCriacao(data);
                
                DisciplinaDAO d1 = new DisciplinaDAO();
                of1.setDisciplina(d1.buscar(rs.getInt("iddisciplina")));
                
                ServidorDAO s1 = new ServidorDAO();
                of1.setProfessor(s1.buscar(rs.getInt("idservidor")));

                ofcs.add(of1);
            }
            if (ofcs.size() != 0) {
                return ofcs.get(0);
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void alterar(OfertaDisciplinaCurso ofc, int id) {
        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("update ofertadisciplinacurso set aulasSemanais = ?, modificacao = ? where idofc = ?");
            stmt.setInt(1, ofc.getAulasSemanais());
            stmt.setDate(2, java.sql.Date.valueOf(ofc.getModificacao()));
            stmt.setInt(3, id);

            stmt.executeUpdate();

            System.out.println("Atualizado com sucesso");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void excluir(int id) {
        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("delete from ofertadisciplinacurso where idofc = ?");
            stmt.setInt(1, id);

            stmt.executeUpdate();

            System.out.println("Deletado com sucesso");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
