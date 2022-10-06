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
public class Comissoes {

    private int id;
    private String comissao;
    private int horasSemanais;
    private LocalDate criacao;
    private LocalDate modificacao;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private boolean estado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComissao() {
        return comissao;
    }

    public void setComissao(String comissao) {
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

    public int getHorasSemanais() {
        return horasSemanais;
    }

    public void setHorasSemanais(int horasSemanais) {
        this.horasSemanais = horasSemanais;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 79 * hash + Objects.hashCode(this.comissao);
        hash = 79 * hash + this.horasSemanais;
        hash = 79 * hash + (this.estado ? 1 : 0);
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
        final Comissoes other = (Comissoes) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.horasSemanais != other.horasSemanais) {
            return false;
        }
        if (this.estado != other.estado) {
            return false;
        }
        return Objects.equals(this.comissao, other.comissao);
    }

    @Override
    public String toString() {
        return "Comissoes{" + "id=" + id + ", comissao=" + comissao + ", "
                + "horasSemanais=" + horasSemanais + ", criacao=" + criacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ", "
                + "modificacao=" + modificacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ", "
                + "dataInicio=" + dataInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ", dataTermino="
                + dataTermino.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ", estado=" + estado + '}';

    }
}
