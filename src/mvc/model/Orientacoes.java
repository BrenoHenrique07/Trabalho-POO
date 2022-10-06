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
public class Orientacoes {

    private int id;
    private String tipoOrientacao;
    private String nomeAluno;
    private int horasSemanais;
    private Servidor servidor;
    private LocalDate criacao;
    private LocalDate modificacao;
    private LocalDate dataInicio;
    private LocalDate dataTermino;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoOrientacao() {
        return tipoOrientacao;
    }

    public void setTipoOrientacao(String tipoOrientacao) {
        this.tipoOrientacao = tipoOrientacao;
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

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public int getHorasSemanais() {
        return horasSemanais;
    }

    public void setHorasSemanais(int horasSemanais) {
        this.horasSemanais = horasSemanais;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 59 * hash + Objects.hashCode(this.tipoOrientacao);
        hash = 59 * hash + Objects.hashCode(this.nomeAluno);
        hash = 59 * hash + this.horasSemanais;
        hash = 59 * hash + Objects.hashCode(this.servidor);
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
        final Orientacoes other = (Orientacoes) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.horasSemanais != other.horasSemanais) {
            return false;
        }
        if (!Objects.equals(this.tipoOrientacao, other.tipoOrientacao)) {
            return false;
        }
        if (!Objects.equals(this.nomeAluno, other.nomeAluno)) {
            return false;
        }
        return Objects.equals(this.servidor, other.servidor);
    }

    @Override
    public String toString() {
        return "Orientacoes{" + "id=" + id + ", tipoOrientacao=" + tipoOrientacao + ", nomeAluno=" + nomeAluno + ", "
                + "horasSemanais=" + horasSemanais + ",\n servidor=" + servidor.getNome() + ", criacao=" + criacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ", modificacao="
                + modificacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ",\n dataInicio=" + dataInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ", dataTermino=" + dataTermino.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + '}';

    }

}
