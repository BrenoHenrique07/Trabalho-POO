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
public class OfertaDisciplinaCurso {

    private int id;
    private Disciplina disciplina;
    private Servidor professor;
    private String ano;
    private int semestre;
    private int aulasSemanais;
    private LocalDate criacao;
    private LocalDate modificacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Servidor getProfessor() {
        return professor;
    }

    public void setProfessor(Servidor professor) {
        this.professor = professor;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
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

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getAulasSemanais() {
        return aulasSemanais;
    }

    public void setAulasSemanais(int aulasSemanais) {
        this.aulasSemanais = aulasSemanais;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.disciplina);
        hash = 23 * hash + Objects.hashCode(this.professor);
        hash = 23 * hash + Objects.hashCode(this.ano);
        hash = 23 * hash + Objects.hashCode(this.semestre);
        hash = 23 * hash + this.aulasSemanais;
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
        final OfertaDisciplinaCurso other = (OfertaDisciplinaCurso) obj;
        if (this.aulasSemanais != other.aulasSemanais) {
            return false;
        }
        if (!Objects.equals(this.professor, other.professor)) {
            return false;
        }
        if (!Objects.equals(this.ano, other.ano)) {
            return false;
        }
        if (!Objects.equals(this.semestre, other.semestre)) {
            return false;
        }
        return Objects.equals(this.disciplina, other.disciplina);
    }

    @Override
    public String toString() {
        return "OfertaDisciplinaCurso{" + "id=" + id + ", disciplina=" + disciplina.getNome() + ", professor=" + professor.getNome() + ", ano=" + ano + ", "
                + "semestre=" + semestre + ", aulasSemanais=" + aulasSemanais + ", criacao=" + criacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ", "
                + "modificacao=" + modificacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + '}';

    }

}
