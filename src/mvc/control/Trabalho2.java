/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.control;

import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import mvc.view.GUI;

import mvc.model.Campus;
import mvc.model.Servidor;
import mvc.model.Curso;
import mvc.model.Disciplina;
import mvc.model.OfertaDisciplinaCurso;
import mvc.model.Orientacoes;
import mvc.model.Atividades;
import mvc.model.Comissoes;
import mvc.model.VinculoServidorComissao;
import mvc.model.AtaDeReunioes;
import mvc.model.AtaDeReunioesPresentes;

import mvc.model.CampusDAO;
import mvc.model.ServidorDAO;
import mvc.model.CursoDAO;
import mvc.model.DisciplinaDAO;
import mvc.model.OfertaDisciplinaCursoDAO;
import mvc.model.OrientacoesDAO;
import mvc.model.AtividadesDAO;
import mvc.model.ComissoesDAO;
import mvc.model.VinculoServidorComissaoDAO;
import mvc.model.AtaDeReunioesDAO;
import mvc.model.AtasDeReunioesPresentesDAO;

import mvc.model.ZMostrarAtas;
import mvc.model.ZGerarAulas;
import mvc.model.ZRelatorioServidor;
import mvc.model.ZAutenticar;

public class Trabalho2 {

    Scanner s = new Scanner(System.in);
    GUI gui = new GUI();

    CampusDAO campus = new CampusDAO();
    ServidorDAO servidor = new ServidorDAO();
    CursoDAO curso = new CursoDAO();
    DisciplinaDAO disciplina = new DisciplinaDAO();
    OfertaDisciplinaCursoDAO ofc = new OfertaDisciplinaCursoDAO();
    OrientacoesDAO orientacoes = new OrientacoesDAO();
    AtividadesDAO atividades = new AtividadesDAO();
    ComissoesDAO comissoes = new ComissoesDAO();
    VinculoServidorComissaoDAO vsc = new VinculoServidorComissaoDAO();
    AtaDeReunioesDAO atas = new AtaDeReunioesDAO();
    AtasDeReunioesPresentesDAO atasp = new AtasDeReunioesPresentesDAO();

    int opcao = 0;

    Trabalho2() {
        int opcao = 0;
        String confirm = new String();
        String teste = new String();

        confirm = "sim";

        while (confirm.equals("sim")) {
            do {
                switch (opcao) {
                    case 1:
                        int opcao1 = 0;

                        if (teste.equals("normal")) {
                            opcao1 = 2;
                        } else {
                            opcao1 = gui.recebeOpcaoCampus();
                        }

                        switch (opcao1) {
                            case 1:
                                Campus c1 = new Campus();
                                gui.criaCampus(c1);

                                try {
                                    campus.adiciona(c1);
                                    System.out.println("Campus adicionado com sucesso");
                                } catch (SQLException ex) {
                                    System.out.println("Erro na insercao");
                                }

                                break;

                            case 2:

                                try {
                                    campus.mostrar();
                                } catch (RuntimeException ex) {
                                    System.out.println("Erro ao mostrar");
                                }

                                break;

                            case 3:

                                System.out.println("Digite o id do campus que gostaria de alterar: ");
                                int id = Integer.parseInt(s.nextLine());

                                try {
                                    Campus achar = campus.buscar(id);

                                    if (achar != null) {
                                        System.out.println("Campus encontrado");
                                        Campus c2 = new Campus();

                                        gui.alterarCampus(c2);

                                        try {
                                            campus.alterar(c2, id);
                                        } catch (RuntimeException e) {
                                            System.out.println("Nao foi possivel alterar, erro");
                                        }
                                    } else {
                                        System.out.println("Campus inexistente");
                                    }
                                } catch (RuntimeException ex) {
                                    System.out.println("Erro ao buscar");
                                }
                                break;

                            case 4:
                                System.out.println("Digite o id do campus que gostaria de excluir: ");
                                int id2 = Integer.parseInt(s.nextLine());

                                try {
                                    Campus achar = campus.buscar(id2);

                                    if (achar != null) {
                                        System.out.println("Campus encontrado");
                                        Campus c2 = new Campus();

                                        try {
                                            campus.excluir(id2);
                                        } catch (RuntimeException e) {
                                            System.out.println("Nao foi possivel excluir, erro");
                                        }
                                    } else {
                                        System.out.println("Campus inexistente");
                                    }
                                } catch (RuntimeException ex) {
                                    System.out.println("Erro ao buscar");
                                }
                                break;
                        }

                        break;
                    case 2:
                        int opcao2 = 0;

                        if (teste.equals("normal")) {
                            opcao2 = 2;
                        } else {
                            opcao2 = gui.recebeOpcaoServidores();
                        }

                        switch (opcao2) {
                            case 1:
                                Servidor s1 = new Servidor();
                                gui.criaServidor(s1);

                                int c = gui.designaServidor();
                                Campus achar = campus.buscar(c);

                                if (achar != null) {
                                    System.out.println("Campus encontrado");
                                    s1.setCampus(achar);
                                    try {
                                        servidor.adiciona(s1);
                                        System.out.println("Servidor adicionado com sucesso");
                                    } catch (SQLException ex) {
                                        System.out.println("Erro na insercao");
                                    }

                                } else {
                                    System.out.println("Campus inexistente");
                                }

                                break;

                            case 2:

                                try {
                                    servidor.mostrar();
                                } catch (RuntimeException ex) {
                                    System.out.println("Erro ao mostrar");
                                }

                                break;

                            case 3:
                                System.out.println("Digite o id do servidor que gostaria de alterar: ");
                                int id = Integer.parseInt(s.nextLine());

                                try {
                                    Servidor achar2 = servidor.buscar(id);

                                    if (achar2 != null) {
                                        System.out.println("Servidor encontrado");
                                        Servidor s2 = new Servidor();

                                        gui.alterarServidores(s2);

                                        try {
                                            servidor.alterar(s2, id);
                                        } catch (RuntimeException e) {
                                            System.out.println("Nao foi possivel alterar, erro");
                                        }
                                    } else {
                                        System.out.println("Servidor inexistente");
                                    }
                                } catch (RuntimeException ex) {
                                    System.out.println("Erro ao buscar");
                                }
                                break;

                            case 4:
                                System.out.println("Digite o id do servidor que gostaria de excluir: ");
                                int id2 = Integer.parseInt(s.nextLine());

                                try {
                                    Servidor achar2 = servidor.buscar(id2);

                                    if (achar2 != null) {
                                        System.out.println("Servidor encontrado");

                                        try {
                                            servidor.excluir(id2);
                                        } catch (RuntimeException e) {
                                            System.out.println("Nao foi possivel excluir, erro");
                                        }
                                    } else {
                                        System.out.println("Servidor inexistente");
                                    }
                                } catch (RuntimeException ex) {
                                    System.out.println("Erro ao buscar");
                                }
                                break;
                        }
                        break;
                    case 3:
                        int opcao3 = 0;

                        if (teste.equals("normal")) {
                            opcao3 = 2;
                        } else {
                            opcao3 = gui.recebeOpcaoCursos();
                        }

                        switch (opcao3) {
                            case 1:
                                Curso cr1 = new Curso();
                                gui.criaCurso(cr1);

                                int c = gui.designaCursos();
                                Campus achar = campus.buscar(c);

                                if (achar != null) {
                                    System.out.println("Campus encontrado");
                                    cr1.setCampusC(achar);
                                    try {
                                        curso.adiciona(cr1);
                                        System.out.println("Curso adicionado com sucesso");
                                    } catch (SQLException ex) {
                                        System.out.println("Erro na insercao");
                                    }

                                } else {
                                    System.out.println("Campus inexistente");
                                }

                                break;

                            case 2:

                                try {
                                    curso.mostrar();
                                } catch (RuntimeException ex) {
                                    System.out.println("Erro ao mostrar");
                                }

                                break;

                            case 3:
                                System.out.println("Digite o id do curso que gostaria de alterar: ");
                                int id = Integer.parseInt(s.nextLine());

                                try {
                                    Curso achar2 = curso.buscar(id);

                                    if (achar2 != null) {
                                        System.out.println("Curso encontrado");
                                        Curso cr2 = new Curso();

                                        gui.alterarCursos(cr2);

                                        try {
                                            curso.alterar(cr2, id);
                                        } catch (RuntimeException e) {
                                            System.out.println("Nao foi possivel alterar, erro");
                                        }
                                    } else {
                                        System.out.println("Curso inexistente");
                                    }
                                } catch (RuntimeException ex) {
                                    System.out.println("Erro ao buscar");
                                }
                                break;

                            case 4:
                                System.out.println("Digite o id do curso que gostaria de excluir: ");
                                int id2 = Integer.parseInt(s.nextLine());

                                try {
                                    Curso achar2 = curso.buscar(id2);

                                    if (achar2 != null) {
                                        System.out.println("curso encontrado");

                                        try {
                                            curso.excluir(id2);
                                        } catch (RuntimeException e) {
                                            System.out.println("Nao foi possivel excluir, erro");
                                        }
                                    } else {
                                        System.out.println("Curso inexistente");
                                    }
                                } catch (RuntimeException ex) {
                                    System.out.println("Erro ao buscar");
                                }
                                break;
                        }
                        break;
                    case 4:
                        int opcao4 = 0;

                        if (teste.equals("normal")) {
                            opcao4 = 2;
                        } else {
                            opcao4 = gui.recebeOpcaoDisciplina();
                        }

                        switch (opcao4) {
                            case 1:
                                Disciplina d1 = new Disciplina();
                                gui.criaDisciplina(d1);

                                int c = gui.designaDisciplina();
                                Curso achar = curso.buscar(c);

                                if (achar != null) {
                                    System.out.println("Curso encontrado");
                                    d1.setCurso(achar);
                                    try {
                                        disciplina.adiciona(d1);
                                        System.out.println("Disciplina adicionado com sucesso");
                                    } catch (SQLException ex) {
                                        System.out.println("Erro na insercao");
                                    }

                                } else {
                                    System.out.println("Curso inexistente");
                                }

                                break;

                            case 2:

                                try {
                                    disciplina.mostrar();
                                } catch (RuntimeException ex) {
                                    System.out.println("Erro ao mostrar");
                                }

                                break;

                            case 3:
                                System.out.println("Digite o id da disciplina que gostaria de alterar: ");
                                int id = Integer.parseInt(s.nextLine());

                                try {
                                    Disciplina achar2 = disciplina.buscar(id);

                                    if (achar2 != null) {
                                        System.out.println("Disciplina encontrada");
                                        Disciplina d2 = new Disciplina();

                                        gui.alteraDisciplina(d2);

                                        try {
                                            disciplina.alterar(d2, id);
                                        } catch (RuntimeException e) {
                                            System.out.println("Nao foi possivel alterar, erro");
                                        }
                                    } else {
                                        System.out.println("Disciplina inexistente");
                                    }
                                } catch (RuntimeException ex) {
                                    System.out.println("Erro ao buscar");
                                }
                                break;

                            case 4:
                                System.out.println("Digite o id da disciplina que gostaria de excluir: ");
                                int id2 = Integer.parseInt(s.nextLine());

                                try {
                                    Disciplina achar2 = disciplina.buscar(id2);

                                    if (achar2 != null) {
                                        System.out.println("disciplina encontrada");

                                        try {
                                            disciplina.excluir(id2);
                                        } catch (RuntimeException e) {
                                            System.out.println("Nao foi possivel excluir, erro");
                                        }
                                    } else {
                                        System.out.println("disciplina inexistente");
                                    }
                                } catch (RuntimeException ex) {
                                    System.out.println("Erro ao buscar");
                                }
                                break;
                        }
                        break;

                    case 5:
                        int opcao5 = 0;

                        if (teste.equals("normal")) {
                            opcao5 = 2;
                        } else {
                            opcao5 = gui.recebeOpcaoOferta();
                        }

                        switch (opcao5) {
                            case 1:
                                OfertaDisciplinaCurso of1 = new OfertaDisciplinaCurso();
                                gui.criaOferta(of1);

                                int d = gui.designaOpcaoOfertaD();
                                Disciplina achar = disciplina.buscar(d);

                                if (achar != null) {
                                    System.out.println("Disciplina enontrada");
                                    of1.setDisciplina(achar);

                                    int s = gui.designaOpcaoOfertaP();
                                    Servidor achar2 = servidor.buscar(s);

                                    if (achar2 != null) {
                                        try {
                                            System.out.println("Servidor encontrado");
                                            of1.setProfessor(achar2);

                                            ofc.adiciona(of1);
                                            System.out.println("Oferta Disciplina Curso adicionado com sucesso");
                                        } catch (SQLException ex) {
                                            System.out.println("Erro na insercao");
                                        }
                                    } else {
                                        System.out.println("Servidor inexistente");
                                    }

                                } else {
                                    System.out.println("Disciplina inexistente");
                                }

                                break;

                            case 2:

                                try {
                                    ofc.mostrar();
                                } catch (RuntimeException ex) {
                                    System.out.println("Erro ao mostrar");
                                }

                                break;

                            case 3:
                                System.out.println("Digite o id da Oferta Disciplina Curso que gostaria de alterar: ");
                                int id = Integer.parseInt(s.nextLine());

                                try {
                                    OfertaDisciplinaCurso achar3 = ofc.buscar(id);

                                    if (achar3 != null) {
                                        System.out.println("Oferta Disciplina Curso encontrada");
                                        OfertaDisciplinaCurso of2 = new OfertaDisciplinaCurso();

                                        gui.alteraOferta(of2);

                                        try {
                                            ofc.alterar(of2, id);
                                        } catch (RuntimeException e) {
                                            System.out.println("Nao foi possivel alterar, erro");
                                        }
                                    } else {
                                        System.out.println("Oferta Disciplina Curso inexistente");
                                    }
                                } catch (RuntimeException ex) {
                                    System.out.println("Erro ao buscar");
                                }
                                break;

                            case 4:
                                System.out.println("Digite o id da Oferta Disciplina Curso que gostaria de excluir: ");
                                int id2 = Integer.parseInt(s.nextLine());

                                try {
                                    OfertaDisciplinaCurso achar3 = ofc.buscar(id2);

                                    if (achar3 != null) {
                                        System.out.println("Oferta Disciplina Curso encontrada");

                                        try {
                                            ofc.excluir(id2);
                                        } catch (RuntimeException e) {
                                            System.out.println("Nao foi possivel excluir, erro");
                                        }
                                    } else {
                                        System.out.println("Oferta Disciplina Curso inexistente");
                                    }
                                } catch (RuntimeException ex) {
                                    System.out.println("Erro ao buscar");
                                }
                                break;
                        }
                        break;

                    case 6:
                        int opcao6 = 0;

                        if (teste.equals("normal")) {
                            opcao6 = 2;
                        } else {
                            opcao6 = gui.recebeOpcaoOrientacoes();
                        }
                        switch (opcao6) {
                            case 1:
                                Orientacoes o1 = new Orientacoes();
                                gui.criaOrientacoes(o1);

                                int sv = gui.designaOrientacoes();
                                Servidor achar = servidor.buscar(sv);

                                if (achar != null) {
                                    System.out.println("Servidor encontrado");
                                    o1.setServidor(achar);
                                    try {
                                        orientacoes.adiciona(o1);
                                        System.out.println("Orientacao adicionada com sucesso");
                                    } catch (SQLException ex) {
                                        System.out.println("Erro na insercao");
                                    }

                                } else {
                                    System.out.println("Servidor inexistente");
                                }

                                break;

                            case 2:

                                try {
                                    orientacoes.mostrar();
                                } catch (RuntimeException ex) {
                                    System.out.println("Erro ao mostrar");
                                }

                                break;

                            case 3:
                                System.out.println("Digite o id da orientacao que gostaria de alterar: ");
                                int id = Integer.parseInt(s.nextLine());

                                try {
                                    Orientacoes achar2 = orientacoes.buscar(id);

                                    if (achar2 != null) {
                                        System.out.println("Orientacao encontrada");
                                        Orientacoes o2 = new Orientacoes();

                                        gui.alteraOrientacoes(o2);

                                        try {
                                            orientacoes.alterar(o2, id);
                                        } catch (RuntimeException e) {
                                            System.out.println("Nao foi possivel alterar, erro");
                                        }
                                    } else {
                                        System.out.println("Orientacao inexistente");
                                    }
                                } catch (RuntimeException ex) {
                                    System.out.println("Erro ao buscar");
                                }
                                break;

                            case 4:
                                System.out.println("Digite o id da orientacao que gostaria de excluir: ");
                                int id2 = Integer.parseInt(s.nextLine());

                                try {
                                    Orientacoes achar2 = orientacoes.buscar(id2);

                                    if (achar2 != null) {
                                        System.out.println("orientacao encontrada");

                                        try {
                                            orientacoes.excluir(id2);
                                        } catch (RuntimeException e) {
                                            System.out.println("Nao foi possivel excluir, erro");
                                        }
                                    } else {
                                        System.out.println("orientacao inexistente");
                                    }
                                } catch (RuntimeException ex) {
                                    System.out.println("Erro ao buscar");
                                }
                                break;
                        }
                        break;

                    case 7:
                        int opcao7 = 0;

                        if (teste.equals("normal")) {
                            opcao7 = 2;
                        } else {
                            opcao7 = gui.recebeOpcaoAtividades();
                        }
                        switch (opcao7) {
                            case 1:
                                Atividades a1 = new Atividades();
                                gui.criaAtividades(a1);

                                int sv = gui.designaAtividades();
                                Servidor achar = servidor.buscar(sv);

                                if (achar != null) {
                                    System.out.println("Servidor encontrado");
                                    a1.setServidor(achar);
                                    try {
                                        atividades.adiciona(a1);
                                        System.out.println("Atividade adicionada com sucesso");
                                    } catch (SQLException ex) {
                                        System.out.println("Erro na insercao");
                                    }

                                } else {
                                    System.out.println("Servidor inexistente");
                                }

                                break;

                            case 2:

                                try {
                                    atividades.mostrar();
                                } catch (RuntimeException ex) {
                                    System.out.println("Erro ao mostrar");
                                }

                                break;

                            case 3:
                                System.out.println("Digite o id da atividade que gostaria de alterar: ");
                                int id = Integer.parseInt(s.nextLine());

                                try {
                                    Atividades achar2 = atividades.buscar(id);

                                    if (achar2 != null) {
                                        System.out.println("Atividade encontrada");
                                        Atividades a2 = new Atividades();

                                        gui.alteraAtividades(a2);

                                        try {
                                            atividades.alterar(a2, id);
                                        } catch (RuntimeException e) {
                                            System.out.println("Nao foi possivel alterar, erro");
                                        }
                                    } else {
                                        System.out.println("Atividade inexistente");
                                    }
                                } catch (RuntimeException ex) {
                                    System.out.println("Erro ao buscar");
                                }
                                break;

                            case 4:
                                System.out.println("Digite o id da atividade que gostaria de excluir: ");
                                int id2 = Integer.parseInt(s.nextLine());

                                try {
                                    Atividades achar2 = atividades.buscar(id2);

                                    if (achar2 != null) {
                                        System.out.println("atividade encontrada");

                                        try {
                                            atividades.excluir(id2);
                                        } catch (RuntimeException e) {
                                            System.out.println("Nao foi possivel excluir, erro");
                                        }
                                    } else {
                                        System.out.println("atividade inexistente");
                                    }
                                } catch (RuntimeException ex) {
                                    System.out.println("Erro ao buscar");
                                }
                                break;
                        }
                        break;

                    case 8:           
                        int opcao8 = 0;

                        if (teste.equals("normal")) {
                            opcao8 = 2;
                        } else {
                            opcao8 = gui.recebeOpcaoComissoes();
                        }
                        switch (opcao8) {
                            case 1:
                                Comissoes cs1 = new Comissoes();
                                gui.criaComissoes(cs1);

                                try {
                                    comissoes.adiciona(cs1);
                                    System.out.println("Comissao adicionada com sucesso");
                                } catch (SQLException ex) {
                                    System.out.println("Erro na insercao");
                                }

                                break;

                            case 2:

                                try {
                                    comissoes.mostrar();
                                } catch (RuntimeException ex) {
                                    System.out.println("Erro ao mostrar");
                                }

                                break;

                            case 3:
                                System.out.println("Digite o id da comissao que gostaria de alterar: ");
                                int id = Integer.parseInt(s.nextLine());

                                try {
                                    Comissoes achar2 = comissoes.buscar(id);

                                    if (achar2 != null) {
                                        System.out.println("Coomissao encontrada");
                                        Comissoes cs2 = new Comissoes();

                                        gui.alteraComissoes(cs2);

                                        try {
                                            comissoes.alterar(cs2, id);
                                        } catch (RuntimeException e) {
                                            System.out.println("Nao foi possivel alterar, erro");
                                        }
                                    } else {
                                        System.out.println("Comissao inexistente");
                                    }
                                } catch (RuntimeException ex) {
                                    System.out.println("Erro ao buscar");
                                }
                                break;

                            case 4:
                                System.out.println("Digite o id da comissao que gostaria de excluir: ");
                                int id2 = Integer.parseInt(s.nextLine());

                                try {
                                    Comissoes achar2 = comissoes.buscar(id2);

                                    if (achar2 != null) {
                                        System.out.println("comissao encontrada");

                                        try {
                                            comissoes.excluir(id2);
                                        } catch (RuntimeException e) {
                                            System.out.println("Nao foi possivel excluir, erro");
                                        }
                                    } else {
                                        System.out.println("comissao inexistente");
                                    }
                                } catch (RuntimeException ex) {
                                    System.out.println("Erro ao buscar");
                                }
                                break;
                        }
                        break;

                    case 9:
                        int opcao9 = 0;

                        if (teste.equals("normal")) {
                            opcao9 = 2;
                        } else {
                            opcao9 = gui.recebeOpcaoVSC();
                        }
                        switch (opcao9) {
                            case 1:
                                VinculoServidorComissao v1 = new VinculoServidorComissao();
                                gui.criaVSC(v1);

                                int d = gui.designaVSCC();
                                Comissoes achar = comissoes.buscar(d);

                                if (achar != null) {
                                    System.out.println("Comissao encontrada");
                                    v1.setComissao(achar);

                                    int s = gui.designaVSCS();
                                    Servidor achar2 = servidor.buscar(s);

                                    if (achar2 != null) {
                                        try {
                                            System.out.println("Servidor encontrado");
                                            v1.setServidor(achar2);

                                            vsc.adiciona(v1);
                                            System.out.println("Vinculo servidor comissao adicionado com sucesso");
                                        } catch (SQLException ex) {
                                            System.out.println("Erro na insercao");
                                        }
                                    } else {
                                        System.out.println("Servidor inexistente");
                                    }

                                } else {
                                    System.out.println("Comissao inexistente");
                                }

                                break;

                            case 2:

                                try {
                                    vsc.mostrar();
                                } catch (RuntimeException ex) {
                                    System.out.println("Erro ao mostrar");
                                }

                                break;

                            case 3:
                                System.out.println("Digite o id do Vinculo servidor comissao que gostaria de alterar: ");
                                int id = Integer.parseInt(s.nextLine());

                                try {
                                    VinculoServidorComissao achar3 = vsc.buscar(id);

                                    if (achar3 != null) {
                                        System.out.println("Vinculo servidor comissao encontrado");
                                        VinculoServidorComissao of2 = new VinculoServidorComissao();

                                        gui.alteraVSC(of2);

                                        try {
                                            vsc.alterar(of2, id);
                                        } catch (RuntimeException e) {
                                            System.out.println("Nao foi possivel alterar, erro");
                                        }
                                    } else {
                                        System.out.println("Vinculo servidor comissao inexistente");
                                    }
                                } catch (RuntimeException ex) {
                                    System.out.println("Erro ao buscar");
                                }
                                break;

                            case 4:
                                System.out.println("Digite o id do Vinculo servidor comissao que gostaria de excluir: ");
                                int id2 = Integer.parseInt(s.nextLine());

                                try {
                                    VinculoServidorComissao achar3 = vsc.buscar(id2);

                                    if (achar3 != null) {
                                        System.out.println("Vinculo servidor comissao encontrado");

                                        try {
                                            vsc.excluir(id2);
                                        } catch (RuntimeException e) {
                                            System.out.println("Nao foi possivel excluir, erro");
                                        }
                                    } else {
                                        System.out.println("Vinculo servidor comissao inexistente");
                                    }
                                } catch (RuntimeException ex) {
                                    System.out.println("Erro ao buscar");
                                }
                                break;
                        }
                        break;

                    case 10:
                        int opcao10 = 0;

                        if (teste.equals("normal")) {
                            opcao10 = 2;
                        } else {
                            opcao10 = gui.recebeOpcaoADR();
                        }
                        switch (opcao10) {
                            case 1:
                                AtaDeReunioes a1 = new AtaDeReunioes();
                                gui.criaADR(a1);

                                int d = gui.designaADRC();
                                Comissoes achar = comissoes.buscar(d);

                                if (achar != null) {
                                    System.out.println("Comissao encontrada");
                                    a1.setComissao(achar);

                                    int s1 = gui.designaADRS();
                                    Servidor achar2 = servidor.buscar(s1);

                                    if (achar2 != null) {
                                        try {
                                            System.out.println("Servidor encontrado");
                                            a1.setServidorSecretario(achar2);

                                            atas.adiciona(a1);
                                            System.out.println("Ata de reuniao adicionada com sucesso");
                                        } catch (SQLException ex) {
                                            System.out.println("Erro na insercao");
                                        }
                                    } else {
                                        System.out.println("Servidor inexistente");
                                    }

                                } else {
                                    System.out.println("Comissao inexistente");
                                }

                                break;

                            case 2:

                                try {
                                    atas.mostrar();
                                } catch (RuntimeException ex) {
                                    System.out.println("Erro ao mostrar");
                                }

                                break;

                            case 3:
                                System.out.println("Digite o id da Ata que gostaria de alterar: ");
                                int id = Integer.parseInt(s.nextLine());

                                try {
                                    AtaDeReunioes achar3 = atas.buscar(id);

                                    if (achar3 != null) {
                                        System.out.println("Ata encontrada");
                                        AtaDeReunioes of2 = new AtaDeReunioes();

                                        gui.alteraADR(of2);

                                        try {
                                            atas.alterar(of2, id);
                                        } catch (RuntimeException e) {
                                            System.out.println("Nao foi possivel alterar, erro");
                                        }
                                    } else {
                                        System.out.println("Ata inexistente");
                                    }
                                } catch (RuntimeException ex) {
                                    System.out.println("Erro ao buscar");
                                }
                                break;

                            case 4:
                                System.out.println("Digite o id da Ata que gostaria de excluir: ");
                                int id2 = Integer.parseInt(s.nextLine());

                                try {
                                    AtaDeReunioes achar3 = atas.buscar(id2);

                                    if (achar3 != null) {
                                        System.out.println("Ata encontrada");

                                        try {
                                            atas.excluir(id2);
                                        } catch (RuntimeException e) {
                                            System.out.println("Nao foi possivel excluir, erro");
                                        }
                                    } else {
                                        System.out.println("Ata inexistente");
                                    }
                                } catch (RuntimeException ex) {
                                    System.out.println("Erro ao buscar");
                                }
                                break;
                        }
                        break;

                    case 11:
                        int opcao11 = 0;

                        if (teste.equals("normal")) {
                            opcao11 = 2;
                        } else {
                            opcao11 = gui.recebeOpcaoADRP();
                        }
                        switch (opcao11) {
                            case 1:
                                AtaDeReunioesPresentes a1 = new AtaDeReunioesPresentes();
                                gui.criaADRP(a1);

                                int d = gui.designaADRC();
                                Comissoes achar = comissoes.buscar(d);

                                if (achar != null) {
                                    System.out.println("Comissao encontrada");
                                    a1.setComissao(achar);

                                    int s1 = gui.designaADRS();
                                    Servidor achar2 = servidor.buscar(s1);

                                    if (achar2 != null) {
                                        System.out.println("Servidor encontrado");
                                        a1.setServidor(achar2);

                                        int ata1 = gui.designaADRA();
                                        AtaDeReunioes achar3 = atas.buscar(ata1);

                                        if (achar3 != null) {
                                            try {
                                                System.out.println("Ata encontrada");
                                                a1.setAtadereuniao(achar3);

                                                atasp.adiciona(a1);
                                                System.out.println("Ata de reuniao Presente adicionada com sucesso");
                                            } catch (SQLException ex) {
                                                System.out.println("Erro na insercao");
                                            }
                                        } else {
                                            System.out.println("Ata inexistente");
                                        }

                                    } else {
                                        System.out.println("Servidor inexistente");
                                    }

                                } else {
                                    System.out.println("Comissao inexistente");
                                }

                                break;

                            case 2:

                                try {
                                    atasp.mostrar();
                                } catch (RuntimeException ex) {
                                    System.out.println("Erro ao mostrar");
                                }

                                break;

                            case 3:
                                System.out.println("Digite o id da Ata Presente que gostaria de alterar: ");
                                int id = Integer.parseInt(s.nextLine());

                                try {
                                    AtaDeReunioesPresentes achar4 = atasp.buscar(id);

                                    if (achar4 != null) {
                                        System.out.println("Ata Presente encontrada");
                                        AtaDeReunioesPresentes of2 = new AtaDeReunioesPresentes();

                                        int ata2 = gui.designaADRA();
                                        AtaDeReunioes ataE = atas.buscar(ata2);

                                        if (ataE != null) {
                                            System.out.println("Ata encontrada, alterando...");
                                            gui.alteraADRP(of2, ataE);

                                            try {
                                                atasp.alterar(of2, id);
                                            } catch (RuntimeException e) {
                                                System.out.println("Nao foi possivel alterar, erro");
                                            }
                                        } else {
                                            System.out.println("Ata inexistente");
                                        }
                                    } else {
                                        System.out.println("Ata Presente inexistente");
                                    }
                                } catch (RuntimeException ex) {
                                    System.out.println("Erro ao buscar");
                                }
                                break;

                            case 4:
                                System.out.println("Digite o id da Ata Presente que gostaria de excluir: ");
                                int id2 = Integer.parseInt(s.nextLine());

                                try {
                                    AtaDeReunioesPresentes achar5 = atasp.buscar(id2);

                                    if (achar5 != null) {
                                        System.out.println("Ata Presente encontrada");

                                        try {
                                            atasp.excluir(id2);
                                        } catch (RuntimeException e) {
                                            System.out.println("Nao foi possivel excluir, erro");
                                        }
                                    } else {
                                        System.out.println("Ata inexistente");
                                    }
                                } catch (RuntimeException ex) {
                                    System.out.println("Erro ao buscar");
                                }
                                break;
                        }
                        break;

                    case 12:
                        ZMostrarAtas ma = new ZMostrarAtas();

                        gui.gerarAtasPeriodo(ma);
                         {
                            try {
                                ma.receberDados(ma.getInicio(), ma.getFim());
                                System.out.println("Relatorio concluido");
                            } catch (FileNotFoundException ex) {
                                System.out.println("Pasta nao encontrada");
                            } catch (DocumentException ex) {
                                System.out.println("Arquivo nao encontrado");
                            }
                        }
                        break;

                    case 13:
                        ZRelatorioServidor rs = new ZRelatorioServidor();

                        System.out.println("Digite o id do servidor que gostaria de fazer um relatorio, R: ");
                        int i = Integer.parseInt(s.nextLine());

                        Servidor achar = servidor.buscar(i);

                        if (achar != null) {
                            System.out.println("Servidor encontrado");
                            try {
                                rs.gerarRelatorioServidor(i);
                                System.out.println("Relatorio criado com sucesso");
                            } catch (FileNotFoundException ex) {
                                System.out.println("Pasta nao encontrada");
                            } catch (DocumentException ex) {
                                System.out.println("Arquivo nao encontrada");
                            }
                        } else {
                            System.out.println("Servidor inexistente");
                        }
                        break;

                    case 14:
                        ZGerarAulas ga = new ZGerarAulas();

                        System.out.println("Digite o id  do campus gostaria de gerar o relatorio, R: ");
                        int id = Integer.parseInt(s.nextLine());

                        Campus c = campus.buscar(id);

                        if (c != null) {
                            System.out.println("Campus encontrado");
                            try {
                                ga.gerarAulas(id);
                                System.out.println("Relatorio gerado1");
                            } catch (FileNotFoundException ex) {
                                System.out.println("Pasta nao encontrada");
                            } catch (DocumentException ex) {
                                System.out.println("Arquivo nao encontrada");
                            }
                        } else {
                            System.out.println("Campus inexistente");
                        }

                        break;

                    case 0:
                        break;

                    case 21:
                        break;

                    default:
                        System.out.println("valor invalido");

                        break;
                }
                opcao = gui.recebeOpcao();
            } while (opcao != 21);

            System.out.println("Gostaria de continuar(sim/nao)?, R:");
            confirm = s.nextLine();

            if (confirm.equals("sim")) {
                ZAutenticar a = new ZAutenticar();

                System.out.println("Digite seu login, R: ");
                String login = s.nextLine();

                System.out.println("Digite sua senha, R: ");
                String senha = s.nextLine();

                teste = a.login(login, senha);

                if (teste.equals("fim")) {
                    System.out.println("Login invalido...saindo");
                    confirm = "nao";
                }
            }
        }
    }

    public static void main(String[] args) {
        new Trabalho2();
    }

}
