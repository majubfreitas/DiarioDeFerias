import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DiarioDeFerias {
    private static Scanner scanner = new Scanner(System.in);
    private static MeuDiarioDeFerias diario = new MeuDiarioDeFerias();

    public static void main(String[] args) {
        System.out.println("===== DIÁRIO DE FÉRIAS =====");
        System.out.println("Bem vindo(a) ao seu registro de atividade das férias!\n");

        boolean executando = true;

        while (executando) {
            exibirMenu();
            int opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    adicionarAtividade();
                    break;
                case 2:
                    listarTodasAtividades();
                    break;
                case 3:
                    listarAtividadesOrdenadas();
                    break;
                case 4:
                    pesquisarPorTag();
                    break;
                case 5:
                    System.out.println("Obrigada por usar o Diário de Férias! Até logo!");
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida! Escolha uma opção entre 1 e 5, por favor.");
            }
        }

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n===== MENU PRINCIPAL =====");
        System.out.println("1. Adicionar nova atividade");
        System.out.println("2. Listar todas as atividades");
        System.out.println("3. Listar atividades ordenadas pela nota");
        System.out.println("4. Pesquisar atividades por tag");
        System.out.println("5. Sair do programa");
        System.out.println("Escolha uma opção: ");
    }

    private static int lerOpcao() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida! Digite um número entre 1 e 5: ");
                scanner.nextLine();
            }
        }
    }

    private static void adicionarAtividade() {
        scanner.nextLine();

        System.out.println("\n===== ADICIONAR NOVA ATIVIDADE =====");

        System.out.println("Descrição da atividade: ");
        String descricao = scanner.nextLine().trim();
        if (descricao.isEmpty()) {
            System.out.println("A descrição não pode ser vazia!");
            return;
        }

        int nota = 0;
        while (true) {
            try {
                System.out.println("Nota de 0 a 10: ");
                nota = scanner.nextInt();
                scanner.nextLine();

                if (nota < 0 || nota > 10) {
                    System.out.println("A nota deve estar entre 0 e 10!");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Digite um número inteiro!");
                scanner.nextLine();
            }
        }

        System.out.println("Tag (ex: lazer, cultural, esportes, família): ");
        String tag = scanner.nextLine().trim();
        if (tag.isEmpty()) {
            System.out.println("A tag não pode estar vazia!");
            return;
        }

        AtividadeDeFerias atividade = new AtividadeDeFerias(descricao, nota, tag);
        diario.adicionarAtividade(atividade);

        System.out.println("Sua atividade foi adicionada com sucesso!");
    }

    private static void listarTodasAtividades() {
        System.out.println("\n===== TODAS AS ATIVIDADES =====");

        try {
            if (diario.estaVazio()) {
                throw new IllegalStateException("Nenhuma atividade cadastrada!");
            }

            List<AtividadeDeFerias> atividades = diario.listarTodasAtividades();
            System.out.println("Total de atividades: " + diario.quantidadeAtividades());

            for (int i = 0; i < atividades.size(); i++) {
                System.out.println((i + 1) + ". " + atividades.get(i));
            }
        } catch (IllegalStateException e) {
            System.out.println("Aviso: " + e.getMessage());
        }
    }

    private static void listarAtividadesOrdenadas() {
        System.out.println("\n===== ATIVIDADES ORDENADAS POR NOTA =====");

        try {
            if (diario.estaVazio()) {
                throw new IllegalStateException("Nenhuma atividade cadastrada!");
            }

            List<AtividadeDeFerias> atividades = diario.listarPorNota();
            System.out.println("Atividades ordenadas da maior para a menor nota: ");

            for (int i = 0; i < atividades.size(); i++) {
                System.out.println((i + 1) + ". " + atividades.get(i));
            }

        } catch (IllegalStateException e) {
            System.out.println("Aviso: " + e.getMessage());
        }
    }

    private static void pesquisarPorTag() {
        scanner.nextLine();

        System.out.println("\n===== PESQUISAR ATIVIDADES POR TAG =====");

        System.out.println("Digite a tag para pesquisar: ");
        String tag = scanner.nextLine().trim();

        if (tag.isEmpty()) {
            System.out.println("A tag não pode ser vazia!");
            return;
        }

        try {
            List<AtividadeDeFerias> resultado = diario.pesquisarPorTag(tag);

            if (resultado.isEmpty()) {
                System.out.println("Nenhuma atividade encontrada com a tag: " + tag);
            } else {
                System.out.println("Atividade(s) com a tag " + tag + ": ");
                for (int i = 0; i < resultado.size(); i++) {
                    System.out.println((i + 1) + ". " + resultado.get(i));
                }
                System.out.println("Total encontrado: " + resultado.size());
            }

        } catch (Exception e) {
            System.out.println("Erro ao pesquisar atividade(s): " + e.getMessage());
        }
    }
}