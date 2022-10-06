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
public class VinculoServidorComissao {

    private int id;
    private Comissoes comissao;
    private Servidor servidor;
    private String papel;
    private LocalDate criacao;
    private LocalDate modificacao;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;

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

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 71 * hash + Objects.hashCode(this.comissao);
        hash = 71 * hash + Objects.hashCode(this.servidor);
        hash = 71 * hash + Objects.hashCode(this.papel);
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
        final VinculoServidorComissao other = (VinculoServidorComissao) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.papel, other.papel)) {
            return false;
        }
        if (!Objects.equals(this.comissao, other.comissao)) {
            return false;
        }
        return Objects.equals(this.servidor, other.servidor);
    }

    @Override
    public String toString() {

        return "VinculoServidorComissao{" + "id=" + id + ", comissao=" + comissao.getComissao() + ", "
                + "servidor=" + servidor.getNome() + ", papel=" + papel + ", criacao=" + criacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ", "
                + "modificacao=" + modificacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ", "
                + "dataEntrada=" + dataEntrada.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ", dataSaida=" + dataSaida.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + '}';

    }

}
