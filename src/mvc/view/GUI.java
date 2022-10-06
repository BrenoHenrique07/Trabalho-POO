/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import mvc.model.AtaDeReunioes;
import mvc.model.AtaDeReunioesPresentes;
import mvc.model.Atividades;

import mvc.model.Campus;
import mvc.model.Comissoes;
import mvc.model.Servidor;
import mvc.model.Curso;
import mvc.model.Disciplina;
import mvc.model.OfertaDisciplinaCurso;
import mvc.model.Orientacoes;
import mvc.model.VinculoServidorComissao;
import mvc.model.ZMostrarAtas;

public class GUI {

    Scanner scanner = new Scanner(System.in);

    public int recebeOpcao() {
        StringBuilder builder = new StringBuilder();

        builder.append("Seja bem vindo\n");
        builder.append("1 - Ver opcoes de campus\n");
        builder.append("2 - Ver opcoes de servidor\n");
        builder.append("3 - Ver opcoes de cursos\n");
        builder.append("4 - Ver opcoes de disciplinas\n");
        builder.append("5 - Ver opcoes de oferta da disciplina no curso\n");
        builder.append("6 - Ver opcoes de orientacoes\n");
        builder.append("7 - Ver opcoes de atividades\n");
        builder.append("8 - Ver opcoes de comissoes\n");
        builder.append("9 - Ver opcoes de vinculo de servidoes a comissaoes\n");
        builder.append("10 - Ver opcoes de ata de reunioes\n");
        builder.append("11 - Ver opcoes de ata de reunioes presentes\n");
        builder.append("12 - Gerar relatório de atas por período\n");
        builder.append("13 - Gerar relatório de trabalho por servidor cadastrado\n");
        builder.append("14 - Gerar um relatório com as informações da aulas ofertadas em um dado campus.\n");

        builder.append("21 - Sair\n");
        builder.append("Qual opcao gostaria? R: ");

        System.out.println(builder);
        return Integer.parseInt(scanner.nextLine());
    }

    //campus
    public int recebeOpcaoCampus() {
        StringBuilder builder = new StringBuilder();

        builder.append("CRUD campus\n");
        builder.append("1 - Criar campus\n");
        builder.append("2 - Mostrar campus\n");
        builder.append("3 - alterar campus\n");
        builder.append("4 - remover campus\n");
        builder.append("Qual opcao gostaria? R: ");

        System.out.println(builder);
        return Integer.parseInt(scanner.nextLine());
    }

    public void criaCampus(Campus c) {
        System.out.println("Digite o nome do campus, R:");
        c.setNome(scanner.nextLine());

        System.out.println("Digite a abreviação do campus, R:");
        c.setAbreviacao(scanner.nextLine());

        System.out.println("Digite a Cidade do campus, R:");
        c.setCidade(scanner.nextLine());

        System.out.println("Digite o bairro do campus, R:");
        c.setBairro(scanner.nextLine());

        System.out.println("Digite o endereço do campus, R:");
        c.setEndereco(scanner.nextLine());

        System.out.println("Digite o cep do campus, R:");
        c.setCep(scanner.nextLine());

        System.out.println("Digite a duracção das aulas do campus, R:");
        c.setDuracaoAulas(Integer.parseInt(scanner.nextLine()));

        c.setCriacao(LocalDate.now());
    }

    public void alterarCampus(Campus c) {
        System.out.println("Digite o nome que gostaria mudar, R: ");
        c.setNome(scanner.nextLine());

        System.out.println("Digite a nova abreviação, R:");
        c.setAbreviacao(scanner.nextLine());

        c.setModificacao(LocalDate.now());
    }

    //Servidores
    public int recebeOpcaoServidores() {
        StringBuilder builder = new StringBuilder();

        builder.append("CRUD Servidores\n");
        builder.append("1 - Cadastrar servidor\n");
        builder.append("2 - Mostrar servidor\n");
        builder.append("3 - Alterar servidor\n");
        builder.append("4 - Remover servidor\n");
        builder.append("Qual opcao gostaria? R: ");

        System.out.println(builder);
        return Integer.parseInt(scanner.nextLine());
    }

    public int designaServidor() {
        System.out.println("Digite o campus(id) do servidor, R:");
        int aux = Integer.parseInt(scanner.nextLine());

        return aux;
    }

    public void criaServidor(Servidor s) {
        System.out.println("Digite o nome do servidor, R:");
        s.setNome(scanner.nextLine());

        System.out.println("Digite o email do servidor, R:");
        s.setEmail(scanner.nextLine());

        System.out.println("Digite o cargo do servidor(tae ou pebtt), R:");
        s.setCargo(scanner.nextLine());

        System.out.println("Digite o login R:");
        s.setLogin(scanner.nextLine());

        System.out.println("Digite a senha, R:");
        s.setSenha(scanner.nextLine());

        System.out.println("Digite o tipo de usuario(normal ou adm), R:");
        s.setUsuario(scanner.nextLine());

        s.setCriacao(LocalDate.now());
    }

    public void alterarServidores(Servidor s) {
        System.out.println("Digite o nome que gostaria mudar, R: ");
        s.setNome(scanner.nextLine());

        System.out.println("Digite o novo cargo(tae ou pebtt), R:");
        s.setCargo(scanner.nextLine());

        s.setModificacao(LocalDate.now());
    }

    //Cursos
    public int recebeOpcaoCursos() {
        StringBuilder builder = new StringBuilder();

        builder.append("CRUD Cursos\n");
        builder.append("1 - Criar cursos\n");
        builder.append("2 - Mostrar cursos\n");
        builder.append("3 - alterar cursos\n");
        builder.append("4 - remover cursos\n");
        builder.append("Qual opcao gostaria? R: ");

        System.out.println(builder);
        return Integer.parseInt(scanner.nextLine());
    }

    public int designaCursos() {
        System.out.println("Digite o campus(id) do curso, R:");
        int aux = Integer.parseInt(scanner.nextLine());

        return aux;
    }

    public void criaCurso(Curso cs1) {

        System.out.println("Digite o nome do curso, R: ");
        cs1.setNome(scanner.nextLine());

        System.out.println("Digite se o curso esta ativo ou inativo(true or false), R: ");
        cs1.setEstado(Boolean.parseBoolean(scanner.nextLine()));

        System.out.println("Digite o ano de início, R: ");
        cs1.setAnoInicio(Integer.parseInt(scanner.nextLine()));

        System.out.println("Digite o ano de termino, R: ");
        cs1.setAnoTermino((Integer.parseInt(scanner.nextLine())));

        cs1.setCriacao(LocalDate.now());
    }

    public void alterarCursos(Curso cs1) {
        System.out.println("Digite o nome que gostaria mudar, R: ");
        cs1.setNome(scanner.nextLine());

        System.out.println("Digite o novo estado do curso(true or false), R:");
        cs1.setEstado(Boolean.parseBoolean(scanner.nextLine()));

        cs1.setModificao(LocalDate.now());
    }

    //Disciplina
    public int recebeOpcaoDisciplina() {
        StringBuilder builder = new StringBuilder();

        builder.append("CRUD disciplina\n");
        builder.append("1 - Cadastrar disciplina\n");
        builder.append("2 - Mostrar disciplina\n");
        builder.append("3 - Alterar disciplina\n");
        builder.append("4 - Remover disciplina\n");
        builder.append("Qual opcao gostaria? R: ");

        System.out.println(builder);
        return Integer.parseInt(scanner.nextLine());
    }

    public int designaDisciplina() {
        System.out.println("Digite o curso(id) da disciplina, R:");
        int aux = Integer.parseInt(scanner.nextLine());

        return aux;
    }

    public void criaDisciplina(Disciplina d) {

        System.out.println("Qual o nome da diciplina, R: ");
        d.setNome(scanner.nextLine());

        System.out.println("Qual a carga horária da diciplina, R: ");
        d.setCargaHoraria(Integer.parseInt(scanner.nextLine()));

        d.setPlanejamento(d.getCargaHoraria());

        System.out.println("Qual a periodicidade da diciplina(semestral ou anual), R: ");
        d.setPeriodicidade(scanner.nextLine());

        d.setCriacao(LocalDate.now());
    }

    public void alteraDisciplina(Disciplina d) {
        System.out.println("Digite o nome que gostaria mudar, R: ");
        d.setNome(scanner.nextLine());

        System.out.println("Digite a nova carga horaria");
        d.setCargaHoraria(Integer.parseInt(scanner.nextLine()));

        d.setPlanejamento(d.getCargaHoraria());

        d.setModificacao(LocalDate.now());
    }

    //oferta disciplina curso
    public int recebeOpcaoOferta() {
        StringBuilder builder = new StringBuilder();

        builder.append("CRUD Oferta da disciplina no curso\n");
        builder.append("1 - Cadastrar Oferta\n");
        builder.append("2 - Mostrar Oferta\n");
        builder.append("3 - Alterar Oferta\n");
        builder.append("4 - Remover Oferta\n");
        builder.append("Qual opcao gostaria? R: ");

        System.out.println(builder);
        return Integer.parseInt(scanner.nextLine());
    }

    public int designaOpcaoOfertaD() {
        System.out.println("Digite a disciplina(id) da oferta, R:");
        int aux = Integer.parseInt(scanner.nextLine());

        return aux;
    }

    public int designaOpcaoOfertaP() {
        System.out.println("Digite o professor(id) da oferta, R:");
        int aux = Integer.parseInt(scanner.nextLine());

        return aux;
    }

    public void criaOferta(OfertaDisciplinaCurso of) {
        System.out.println("Qual o ano da disciplina, R: ");
        of.setAno(scanner.nextLine());

        System.out.println("Qual o semestre da disciplina(1 ou 2), R: ");
        of.setSemestre(Integer.parseInt(scanner.nextLine()));

        System.out.println("Quantidade de aulas semanais, R: ");
        of.setAulasSemanais(Integer.parseInt(scanner.nextLine()));

        of.setCriacao(LocalDate.now());
    }

    public void alteraOferta(OfertaDisciplinaCurso of) {
        System.out.println("Digite a nova quantidade de aulas semanais, R: ");
        of.setAulasSemanais(Integer.parseInt(scanner.nextLine()));

        of.setModificacao(LocalDate.now());
    }

    //Orientacoes
    public int recebeOpcaoOrientacoes() {
        StringBuilder builder = new StringBuilder();

        builder.append("CRUD Orientacoes\n");
        builder.append("1 - Criar Orientacoes\n");
        builder.append("2 - Mostrar Orientacoes\n");
        builder.append("3 - Alterar Orientacao\n");
        builder.append("4 - Remover Orientacao\n");
        builder.append("Qual opcao gostaria? R: ");

        System.out.println(builder);
        return Integer.parseInt(scanner.nextLine());
    }

    public int designaOrientacoes() {
        System.out.println("Qual o servidor que irá designar as oriencoes(digite o id), R:");
        int aux = Integer.parseInt(scanner.nextLine());

        return aux;
    }

    public void criaOrientacoes(Orientacoes or) {
        System.out.println("Digite o tipo de orientação(ensino, pesquisa, extensão, estagio, tcc, mestrado, doutorado), R:");
        or.setTipoOrientacao(scanner.nextLine());

        System.out.println("Digite o nome do aluno, R: ");
        or.setNomeAluno(scanner.nextLine());

        System.out.println("Digite as horas semanais, R: ");
        or.setHorasSemanais(Integer.parseInt(scanner.nextLine()));

        System.out.println("Digite a data de início(yyyy-MM-dd): ");
        or.setDataInicio(LocalDate.parse(scanner.nextLine()));

        System.out.println("Digite a data de término(yyyy-MM-dd): ");
        or.setDataTermino(LocalDate.parse(scanner.nextLine()));

        or.setCriacao(LocalDate.now());
    }

    public void alteraOrientacoes(Orientacoes or) {
        System.out.println("Digite o nome do novo aluno, R:");
        or.setNomeAluno(scanner.nextLine());

        System.out.println("Digite a nova quantidade de horas semanais, R: ");
        or.setHorasSemanais(Integer.parseInt(scanner.nextLine()));

        or.setModificacao(LocalDate.now());
    }

    //Atividades
    public int recebeOpcaoAtividades() {
        StringBuilder builder = new StringBuilder();

        builder.append("CRUD Atividades\n");
        builder.append("1 - Criar Atividades\n");
        builder.append("2 - Mostrar Atividades\n");
        builder.append("3 - Alterar Atividades\n");
        builder.append("4 - Remover Atividades\n");
        builder.append("Qual opcao gostaria? R: ");

        System.out.println(builder);
        return Integer.parseInt(scanner.nextLine());
    }

    public int designaAtividades() {
        System.out.println("Qual o servidor que irá designar as atividades(digite o id), R:");
        int aux = Integer.parseInt(scanner.nextLine());

        return aux;
    }

    public void criaAtividades(Atividades a) {
        System.out.println("Digite a descricao da atividade, R: ");
        a.setDescricao(scanner.nextLine());

        System.out.println("Digite a quantidade de horas semanais, R: ");
        a.setHorasSemanais(Integer.parseInt(scanner.nextLine()));

        System.out.println("Digite a data de início(yyyy-MM-dd): ");
        a.setDataInicio(LocalDate.parse(scanner.nextLine()));

        System.out.println("Digite a data de término(yyyy-MM-dd): ");
        a.setDataTermino(LocalDate.parse(scanner.nextLine()));

        a.setCriacao(LocalDate.now());
    }

    public void alteraAtividades(Atividades a) {
        System.out.println("Digite a nova descricao da atividade, R: ");
        a.setDescricao(scanner.nextLine());

        System.out.println("Digite a nova quantidade de horas semanais, R: ");
        a.setHorasSemanais(Integer.parseInt(scanner.nextLine()));

        a.setModificacao(LocalDate.now());
    }

    //Comissoes
    public int recebeOpcaoComissoes() {
        StringBuilder builder = new StringBuilder();

        builder.append("CRUD Comissoes\n");
        builder.append("1 - Criar Comissoes\n");
        builder.append("2 - Mostrar Comissoes\n");
        builder.append("3 - Alterar Comissoes\n");
        builder.append("4 - Encerrar Comissoes\n");
        builder.append("Qual opcao gostaria? R: ");

        System.out.println(builder);
        return Integer.parseInt(scanner.nextLine());
    }

    public void criaComissoes(Comissoes c) {

        System.out.println("Digite a comissao, R: ");
        c.setComissao(scanner.nextLine());

        System.out.println("Digite as horas semanais, R: ");
        c.setHorasSemanais(Integer.parseInt(scanner.nextLine()));

        System.out.println("Digite o estado ativo ou inativo(true ou false), R: ");
        c.setEstado(Boolean.parseBoolean(scanner.nextLine()));

        System.out.println("Digite a data de início(yyyy-MM-dd): ");
        c.setDataInicio(LocalDate.parse(scanner.nextLine()));

        System.out.println("Digite a data de término(yyyy-MM-dd): ");
        c.setDataTermino(LocalDate.parse(scanner.nextLine()));

        c.setCriacao(LocalDate.now());
    }

    public void alteraComissoes(Comissoes c) {
        System.out.println("Digite as novas horas semanais, R: ");
        c.setHorasSemanais(Integer.parseInt(scanner.nextLine()));

        System.out.println("Digite o novo estado ativo ou inativo(true ou false), R: ");
        c.setEstado(Boolean.parseBoolean(scanner.nextLine()));

        c.setModificacao(LocalDate.now());
    }

    //VinculoServidorComissao
    public int recebeOpcaoVSC() {
        StringBuilder builder = new StringBuilder();

        builder.append("CRUD Vincular servidor a uma comissao\n");
        builder.append("1 - Vincular servidor\n");
        builder.append("2 - Mostrar servidores vinculados a comissoes\n");
        builder.append("3 - Alterar vinculo\n");
        builder.append("4 - Remover vinculo\n");
        builder.append("Qual opcao gostaria? R: ");

        System.out.println(builder);
        return Integer.parseInt(scanner.nextLine());
    }

    public void criaVSC(VinculoServidorComissao vsc) {

        System.out.println("Digite o papel do servidor(presidente, vice, secretario, participante, suplente), R: ");
        vsc.setPapel(scanner.nextLine());

        System.out.println("Digite a data de entrada(yyyy-MM-dd): ");
        vsc.setDataEntrada(LocalDate.parse(scanner.nextLine()));

        System.out.println("Digite a data de saída(yyyy-MM-dd): ");
        vsc.setDataSaida(LocalDate.parse(scanner.nextLine()));

        vsc.setCriacao(LocalDate.now());
    }

    public int designaVSCC() {
        System.out.println("Qual a comissao que irá designar a um servidor(digite o id), R:");
        int aux = Integer.parseInt(scanner.nextLine());

        return aux;
    }

    public int designaVSCS() {
        System.out.println("Qual o servidor que irá designar a comissao(digite o id), R:");
        int aux = Integer.parseInt(scanner.nextLine());

        return aux;
    }

    public void alteraVSC(VinculoServidorComissao vsc) {
        System.out.println("Digite o novo papel do servidor, R: ");
        vsc.setPapel(scanner.nextLine());

        vsc.setModificacao(LocalDate.now());
    }

    //AtaDeReunioes
    public int recebeOpcaoADR() {
        StringBuilder builder = new StringBuilder();

        builder.append("CRUD Ata de Reuniões\n");
        builder.append("1 - Criar ata de reuniões\n");
        builder.append("2 - Mostrar ata de reunião\n");
        builder.append("3 - Alterar ata de reunião\n");
        builder.append("4 - Remover ata de reunião\n");
        builder.append("Qual opcao gostaria? R: ");

        System.out.println(builder);
        return Integer.parseInt(scanner.nextLine());
    }

    public int designaADRS() {
        System.out.println("Digite o servidor(id) para inserir na ata de reunioes, R:");
        int aux = Integer.parseInt(scanner.nextLine());

        return aux;
    }

    public int designaADRC() {
        System.out.println("Digite a comissao(id) para inserir na ata de reunioes, R:");
        int aux = Integer.parseInt(scanner.nextLine());

        return aux;
    }

    public void criaADR(AtaDeReunioes a) {
        System.out.println("Digite o conteudo da ata, R: ");
        a.setConteudo(scanner.nextLine());

        System.out.println("Digite a data da reunião(yyyy-MM-dd): ");
        a.setDataReuniao(LocalDate.parse(scanner.nextLine()));

        a.setCriacao(LocalDate.now());
    }

    public void alteraADR(AtaDeReunioes a) {
        System.out.println("Digite o novo conteudo da ata, R: ");
        a.setConteudo(scanner.nextLine());

        a.setModificacao(LocalDate.now());
    }

    //AtaDeReunioesPresentes
    public int recebeOpcaoADRP() {
        StringBuilder builder = new StringBuilder();

        builder.append("CRUD Ata de Reuniões\n");
        builder.append("1 - Criar ata de reuniões e presentes\n");
        builder.append("2 - Mostrar ata de reunião e presentes\n");
        builder.append("3 - Alterar ata de reunião e presentes\n");
        builder.append("4 - Remover ata de reunião e presentes\n");
        builder.append("Qual opcao gostaria? R: ");

        System.out.println(builder);
        return Integer.parseInt(scanner.nextLine());
    }

    public int designaADRA() {
        System.out.println("Digite a ata de reuniao(id) para inserir na ata de reunioes e presentes, R:");
        int aux = Integer.parseInt(scanner.nextLine());

        return aux;
    }

    public void criaADRP(AtaDeReunioesPresentes a) {
        a.setCriacao(LocalDate.now());
    }

    public void alteraADRP(AtaDeReunioesPresentes a, AtaDeReunioes a1) {
        a.setAtadereuniao(a1);
        a.setModificacao(LocalDate.now());
    }

    //atas gerar
    public void gerarAtasPeriodo(ZMostrarAtas mt) {
        System.out.println("   Digite o periodo que deseja mostrar as atas");
        System.out.println("---------------------------------------------------");
        System.out.println("Digite a data de inicio(yyyy-MM-dd), R: ");
        mt.setInicio(LocalDate.parse(scanner.nextLine()));
        System.out.println("Digite a data final(yyyy-MM-dd), R: ");
        mt.setFim(LocalDate.parse(scanner.nextLine()));
    }
}
