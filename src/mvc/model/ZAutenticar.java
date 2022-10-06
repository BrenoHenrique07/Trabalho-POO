/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author breno
 */
public class ZAutenticar {
    
    public String login(String login, String senha){
        try (Connection conexao = new FabricaConexao().getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("select usuario from servidor where login = ? and senha = ?");
            stmt.setString(1, login);
            stmt.setString(2, senha);
            
            ResultSet rs = stmt.executeQuery();
            
            List<String> usuario = new ArrayList();
            
            while(rs.next()){
                String teste = new String();
                
                teste = rs.getString("usuario");
                usuario.add(teste);
            }
            
            if(usuario.size() > 0){
                return usuario.get(0);
            }else{
                return "fim";
            }

        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    } 
}
