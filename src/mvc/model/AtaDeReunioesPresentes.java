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
public class AtaDeReunioesPresentes {

    private int id;
    private Comissoes comissao;
    private AtaDeReunioes atadereuniao;
    private Servidor servidor;
    private LocalDate criacao;
    private LocalDate modificacao;

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

    public AtaDeReunioes getAtadereuniao() {
        return atadereuniao;
    }

    public void setAtadereuniao(AtaDeReunioes atadereuniao) {
        this.atadereuniao = atadereuniao;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 13 * hash + Objects.hashCode(this.comissao);
        hash = 13 * hash + Objects.hashCode(this.atadereuniao);
        hash = 13 * hash + Objects.hashCode(this.servidor);
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
        final AtaDeReunioesPresentes other = (AtaDeReunioesPresentes) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.comissao, other.comissao)) {
            return false;
        }
        if (!Objects.equals(this.atadereuniao, other.atadereuniao)) {
            return false;
        }
        return Objects.equals(this.servidor, other.servidor);
    }

    @Override
    public String toString() {

        return "AtaDeReunioesPresentes{" + "id=" + id + ", comissao=" + comissao.getComissao() + ","
                + " atadereuniao=" + atadereuniao.getConteudo() + ", servidor=" + servidor.getNome() + ", criacao=" + criacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + ", modificacao=" + modificacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + '}';
    }

}
