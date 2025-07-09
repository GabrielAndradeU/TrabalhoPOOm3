import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Filme> filmes = new ArrayList<>();
        List<Sessao> sessoes = new ArrayList<>();

        // Filmes codificados diretamente (opcional, pode também cadastrar no menu)
        filmes.add(new Filme("Vingadores: Ultimato", 3, GeneroFilme.Acao));
        filmes.add(new Filme("Frozen 2", 2, GeneroFilme.Aventura));

        while (true) {
            System.out.println("\n==== MENU ====");
            System.out.println("1 - Adicionar Sessão");
            System.out.println("2 - Consultar Sessões do Dia");
            System.out.println("3 - Comprar Ingresso");
            System.out.println("4 - Listar Filmes");
            System.out.println("5 - Adicionar Filme");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            if (opcao == 0) break;

            switch (opcao) {
                case 1:
                    // Adicionar Sessão
                    if (filmes.isEmpty()) {
                        System.out.println("Nenhum filme cadastrado. Cadastre um filme primeiro.");
                        break;
                    }
                    System.out.println("\nEscolha o filme para a sessão:");
                    for (int i = 0; i < filmes.size(); i++) {
                        System.out.println(i + " - " + filmes.get(i).getTitulo());
                    }
                    int filmeIndex = scanner.nextInt();
                    scanner.nextLine();

                    Filme filmeSelecionado = filmes.get(filmeIndex);

                    System.out.print("Número da sala: ");
                    int numeroSala = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Horário da Sessão (apenas a hora, 0-23): ");
                    int horario = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Tipo de Tela (ex.: 3D, IMAX): ");
                    String tipoTela = scanner.nextLine();
                    System.out.print("Local: ");
                    String local = scanner.nextLine();

                    System.out.print("A sessão está ativa? (1 - Sim, 2 - Não): ");
                    int opcaoStatus = scanner.nextInt();
                    boolean statusSessao = (opcaoStatus == 1);
                    scanner.nextLine();

                    int assentos = 0;
                    if (statusSessao) {
                        System.out.print("Número de assentos: ");
                        assentos = scanner.nextInt();
                        scanner.nextLine();
                    } else {
                        System.out.println("Sessão criada como inativa. Número de assentos não aplicável.");
                    }

                    // Aqui passamos o número da sala (novo argumento!)
                    Sessao novaSessao = new Sessao(statusSessao, horario, numeroSala, assentos, tipoTela, local);
                    novaSessao.adicionarFilmeNaSessao(filmeSelecionado);
                    sessoes.add(novaSessao);
                    System.out.println("Sessão adicionada!");
                    break;

                case 2:
                    if (sessoes.isEmpty()) {
                        System.out.println("Não há sessões cadastradas.");
                    } else {
                        System.out.println("\nSESSÕES DO DIA:");
                        for (int i = 0; i < sessoes.size(); i++) {
                            Sessao s = sessoes.get(i);
                            String status = s.isStatusDaSessao() ? "Ativa" : "Encerrada";
                            System.out.println(i + " - " + s + " | Status: " + status);
                            System.out.println("Ingressos disponíveis: " + s.assentosDisponiveis());
                        }
                    }
                    break;

                case 3:
                    if (sessoes.isEmpty()) {
                        System.out.println("Não há sessões cadastradas.");
                        break;
                    }

                    System.out.println("\nEscolha a sessão:");
                    for (int i = 0; i < sessoes.size(); i++) {
                        Sessao s = sessoes.get(i);
                        System.out.println(i + " - " + s + " | Status: " + (s.isStatusDaSessao() ? "Ativa" : "Encerrada"));
                        System.out.println("Ingressos disponíveis: " + s.assentosDisponiveis());
                    }
                    int sessaoIndex = scanner.nextInt();
                    Sessao sessaoEscolhida = sessoes.get(sessaoIndex);

                    if (!sessaoEscolhida.isStatusDaSessao() || sessaoEscolhida.assentosDisponiveis() <= 0) {
                        System.out.println("Sessão encerrada ou esgotada. Não é possível vender ingresso.");
                        break;
                    }

                    System.out.print("Quantidade de ingressos: ");
                    int qtdIngressos = scanner.nextInt();
                    scanner.nextLine();

                    if (qtdIngressos <= 0) {
                        System.out.println("A quantidade de ingressos deve ser de no mínimo 1.");
                        break;
                    }

                    if (qtdIngressos > sessaoEscolhida.assentosDisponiveis()) {
                        System.out.println("Não há assentos suficientes.");
                        break;
                    }

                    System.out.println("Escolha o tipo de ingresso: 1 - Meio, 2 - Inteiro");
                    int tipoIngresso = scanner.nextInt();
                    System.out.println("Escolha a categoria: 1 - Físico, 2 - Online");
                    int categoriaIngresso = scanner.nextInt();

                    TipoDeIngresso tipo = (tipoIngresso == 1) ? TipoDeIngresso.MeioIngresso : TipoDeIngresso.IngressoInteiro;
                    CategoriaIngresso categoria = (categoriaIngresso == 1) ? CategoriaIngresso.ingressoFisico : CategoriaIngresso.ingressoOnline;

                    for (int i = 0; i < qtdIngressos; i++) {
                        String resultado = sessaoEscolhida.comprarIngresso(tipo, categoria);
                        System.out.println(resultado);
                    }
                    System.out.println("Ingressos restantes: " + sessaoEscolhida.assentosDisponiveis());
                    break;

                case 4:
                    System.out.println("\nFILMES:");
                    for (Filme f : filmes) {
                        System.out.println(f);
                    }
                    break;

                case 5:
                    System.out.print("\nDigite o título do filme: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Duração em horas: ");
                    int duracao = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Escolha o gênero:");
                    for (GeneroFilme g : GeneroFilme.values()) {
                        System.out.println(g.ordinal() + " - " + g);
                    }
                    int generoIndex = scanner.nextInt();
                    scanner.nextLine();
                    GeneroFilme genero = GeneroFilme.values()[generoIndex];

                    Filme novoFilme = new Filme(titulo, duracao, genero);
                    filmes.add(novoFilme);
                    System.out.println("Filme adicionado com sucesso!");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }

        System.out.println("Programa encerrado!");
        scanner.close();
    }
}
