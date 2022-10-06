/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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

/**
 *
 * @author breno
 */
public class CampusDAO {

    private List<Campus> campus = new ArrayList();

    public List<Campus> getCampus() {
        return campus;
    }

    public void adiciona(Campus campus) throws SQLException {
        String sql = "insert into campus "
                + "(nome,abreviacao,duracaoAulas,criacao,modificacao,cidade, bairro, endereco, cep)"
                + " values (?,?,?,?,?,?,?,?,?)";
        try (Connection conexao = new FabricaConexao().getConnection()) {

            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, campus.getNome());
            stmt.setString(2, campus.getAbreviacao());
            stmt.setInt(3, campus.getDuracaoAulas());
            stmt.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setString(6, campus.getCidade());
            stmt.setString(7, campus.getBairro());
            stmt.setString(8, campus.getEndereco());
            stmt.setString(9, campus.getCep());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrar() {

        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("select * from campus");
            ResultSet rs = stmt.executeQuery();

            List<Campus> campus = new ArrayList();

            while (rs.next()) {
                Campus c1 = new Campus();

                c1.setId(rs.getInt("idcampus"));
                c1.setNome(rs.getString("nome"));
                c1.setAbreviacao(rs.getString("abreviacao"));
                c1.setDuracaoAulas(rs.getInt("duracaoAulas"));
                c1.setEndereco(rs.getString("endereco"));

                Date currentDate = rs.getDate("criacao");
                LocalDate data = currentDate.toLocalDate();

                Date currentDate2 = rs.getDate("modificacao");
                LocalDate data2 = currentDate2.toLocalDate();

                if (data2 != null) {
                    c1.setModificacao(data2);
                }

                c1.setCriacao(data);
                c1.setCidade(rs.getString("cidade"));
                c1.setBairro(rs.getString("bairro"));
                c1.setCep(rs.getString("cep"));

                campus.add(c1);
            }

            for (int i = 0; i < campus.size(); i++) {
                System.out.println(campus.get(i).toString());
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public Campus buscar(int id) {

        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("select * from campus where idcampus = " + id);   
            
            ResultSet rs = stmt.executeQuery();
            
            List<Campus> campus = new ArrayList();

            while (rs.next()) {
                Campus c1 = new Campus();

                c1.setId(rs.getInt("idcampus"));
                c1.setNome(rs.getString("nome"));
                c1.setAbreviacao(rs.getString("abreviacao"));
                c1.setDuracaoAulas(rs.getInt("duracaoAulas"));
                c1.setEndereco(rs.getString("endereco"));

                Date currentDate = rs.getDate("criacao");
                LocalDate data = currentDate.toLocalDate();

                Date currentDate2 = rs.getDate("modificacao");
                LocalDate data2 = currentDate2.toLocalDate();

                if (data2 != null) {
                    c1.setModificacao(data2);
                }

                c1.setCriacao(data);
                c1.setCidade(rs.getString("cidade"));
                c1.setBairro(rs.getString("bairro"));
                c1.setCep(rs.getString("cep"));

                campus.add(c1);
            }
            
            if(campus.size() != 0){
                return campus.get(0);
            }else{
                return null;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void alterar(Campus c, int id){
            try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("update campus set nome = ?, abreviacao = ?, modificacao = ? where idcampus = ?");
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getAbreviacao());
            stmt.setDate(3, java.sql.Date.valueOf(c.getModificacao()));
            stmt.setInt(4, id);
            
            stmt.executeUpdate();
            
            System.out.println("Atualizado com sucesso");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void excluir(int id){
            try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("delete from campus where idcampus = ?");
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
            
            System.out.println("Deletado com sucesso");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
