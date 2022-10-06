/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import java.util.Objects;
import mvc.model.CampusDAO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author breno
 */
public class Campus {

    private int id;
    private String nome;
    private String abreviacao;
    private int duracaoAulas;
    private LocalDate criacao;
    private LocalDate modificacao;
    private String cidade;
    private String bairro;
    private String endereco;
    private String cep;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAbreviacao() {
        return abreviacao;
    }

    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
    }

    public int getDuracaoAulas() {
        return duracaoAulas;
    }

    public void setDuracaoAulas(int duracaoAulas) {
        this.duracaoAulas = duracaoAulas;
    }

    public LocalDate getCriacao() {
        return criacao;
    }

    public void setCriacao(LocalDate criacao) {
        this.criacao = criacao;
    }

    public LocalDate getModificacao() {
        return modificacao;
    }

    public void setModificacao(LocalDate modificacao) {
        this.modificacao = modificacao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 23 * hash + Objects.hashCode(this.nome);
        hash = 23 * hash + Objects.hashCode(this.abreviacao);
        hash = 23 * hash + this.duracaoAulas;
        hash = 23 * hash + Objects.hashCode(this.criacao);
        hash = 23 * hash + Objects.hashCode(this.modificacao);
        hash = 23 * hash + Objects.hashCode(this.cidade);
        hash = 23 * hash + Objects.hashCode(this.bairro);
        hash = 23 * hash + Objects.hashCode(this.endereco);
        hash = 23 * hash + Objects.hashCode(this.cep);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Campus other = (Campus) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.duracaoAulas != other.duracaoAulas) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.abreviacao, other.abreviacao)) {
            return false;
        }
        if (!Objects.equals(this.cidade, other.cidade)) {
            return false;
        }
        if (!Objects.equals(this.bairro, other.bairro)) {
            return false;
        }
        if (!Objects.equals(this.endereco, other.endereco)) {
            return false;
        }
        if (!Objects.equals(this.cep, other.cep)) {
            return false;
        }
        if (!Objects.equals(this.criacao, other.criacao)) {
            return false;
        }
        if (!Objects.equals(this.modificacao, other.modificacao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return "Campus{" + "id=" + id + ", nome=" + nome + ", abreviacao=" + abreviacao + ", duracaoAulas=" + duracaoAulas + ", criacao="
                + criacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ", modificacao=" + modificacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + ", cidade=" + cidade + ", bairro=" + bairro + ", endereco=" + endereco + ", cep=" + cep + '}';

    }
}
