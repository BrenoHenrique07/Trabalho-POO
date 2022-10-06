/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import java.util.Objects;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author breno
 */
public class AtaDeReunioes {

    private int id;
    private Comissoes comissao;
    private String conteudo;
    private Servidor servidorSecretario;
    private LocalDate criacao;
    private LocalDate modificacao;
    private LocalDate dataReuniao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Comissoes getComissao() {
        return comissao;
    }

    public void setComissao(Comissoes comissao) {
        this.comissao = comissao;
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

    public LocalDate getDataReuniao() {
        return dataReuniao;
    }

    public void setDataReuniao(LocalDate dataReuniao) {
        this.dataReuniao = dataReuniao;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Servidor getServidorSecretario() {
        return servidorSecretario;
    }

    public void setServidorSecretario(Servidor servidorSecretario) {
        this.servidorSecretario = servidorSecretario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 11 * hash + Objects.hashCode(this.comissao);
        hash = 11 * hash + Objects.hashCode(this.conteudo);
        hash = 11 * hash + Objects.hashCode(this.servidorSecretario);
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
        final AtaDeReunioes other = (AtaDeReunioes) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.conteudo, other.conteudo)) {
            return false;
        }
        if (!Objects.equals(this.comissao, other.comissao)) {
            return false;
        }
        return Objects.equals(this.servidorSecretario, other.servidorSecretario);
    }

    @Override
    public String toString() {

        return "AtaDeReunioes{" + "id=" + id + ", comissao=" + comissao.getComissao() + ", conteudo=" + conteudo + ", "
                + "servidorSecretario=" + servidorSecretario.getNome() + ", criacao=" + criacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ", "
                + "modificacao=" + modificacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ", dataReuniao=" + dataReuniao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + '}';

    }

}
