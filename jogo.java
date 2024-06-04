import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Local {
    private String nome;
    private List<String> itens;
    private List<String> acoes;

    public Local(String nome) {
        this.nome = nome;
        this.itens = new ArrayList<>();
        this.acoes = new ArrayList<>();

    }

    public String getNome() {
        return nome;
    }

    public List<String> getItens() {
        return itens;
    }

    public void adicionarItem(String item) {
        itens.add(item);
    }

    public void removerItem(String item) {
        itens.remove(item);
    }

    public List<String> getAcoes() {
        return acoes;
    }

    public void adicionarAcao(String acao) {
        acoes.add(acao);
    }
}

public class JogoMisterio {
    private static final Scanner scanner = new Scanner(System.in);
    private Map<String, Local> locais;
    private List<String> inventario;
    private Local localAtual;
    
    public JogoMisterio() {
        locais = new HashMap<>();
        inventario = new ArrayList<>();
        inicializarLocais();
        localAtual = locais.get("MERCADO MUNICIPAL");
    }

    private void inicializarLocais() {
        Local mercado = new Local("MERCADO MUNICIPAL");
        mercado.adicionarAcao("Ver mercado");
        mercado.adicionarAcao("Perguntar vendedor");
        mercado.adicionarItem("chave - \"Uma chave de ferro enferrujada que pode abrir portas antigas.\"");
        mercado.adicionarItem("mapa - \"Temos ao norte um beco escuro e ao oeste uma estação de trem.\"");

        Local beco = new Local("BECO ESCURO");
        beco.adicionarAcao("Ver beco escuro");
        beco.adicionarAcao("Perguntar informante");
        beco.adicionarItem("lanterna - \"Uma lanterna velha e enferrujada que emite uma luz fraca.\"");
        beco.adicionarItem("faca - \"Uma faca afiada e robusta, útil para se defender de ameaças.\"");

        Local mansao = new Local("MANSÃO ABANDONADA");
        fantasma = false;
        mansao.adicionarAcao("Ver mansão abandonada");
        mansao.adicionarAcao("Perguntar fantasma");
        mansao.adicionarItem("diario - \"Um diário empoeirado com páginas amareladas que contém segredos há muito esquecidos.\"");
        mansao.adicionarItem("joia - \"Uma joia cintilante e rara escondida em uma gaveta empoeirada.\"");
        
        Local tunel = new Local("TUNEL SECRETO");
        tunel.adicionarAcao("Ver tunel");
        tunel.adicionarAcao("Ler bilhete");
        tunel.adicionarItem("mapa velho - \"Se quer a relíquia do dragao, siga para o oeste(CEMITERIO ASSOMBRADO)\"");

        Local estacao = new Local("ESTAÇÃO DE TREM");
        estacao.adicionarAcao("Ver estação de trem");
        estacao.adicionarAcao("Perguntar viajante");
        estacao.adicionarItem("bilhete - \"Um bilhete de papel amassado que indica um destino distante.\"");
        estacao.adicionarItem("horário - \"Um folheto dobrado que lista os horários de partida e chegada dos trens.\"");

        Local cemiterio = new Local("CEMITÉRIO ASSOMBRADO");
        cemiterio.adicionarAcao("Ver cemitério assombrado");
        cemiterio.adicionarAcao("Perguntar zelador");
        cemiterio.adicionarItem("amuleto - \"Um amuleto de prata adornado com símbolos estranhos e inscrições antigas.\"");
        cemiterio.adicionarItem("chave - \"Uma chave coberta de ferrugem que pode abrir portões antigos.\"");

        locais.put(mercado.getNome(), mercado);
        locais.put(beco.getNome(), beco);
        locais.put(mansao.getNome(), mansao);
        locais.put(tunel.getNome(), tunel);
        locais.put(estacao.getNome(), estacao);
        locais.put(cemiterio.getNome(), cemiterio);
    }

    public void jogar() {
        String acao;
        do {
            System.out.println("Você está em um jogo de mistério. O que deseja fazer?");
            System.out.println("1. Explorar (digite 'explorar')");
            System.out.println("2. Pegar item (digite 'pegar')");
            System.out.println("3. Largar item (digite 'largar')");
            System.out.println("4. Ver inventário (digite 'inventario')");
            System.out.println("5. Mover (digite 'mover')");
            System.out.println("6. Sair (digite 'sair')");
            acao = scanner.nextLine();

            switch (acao) {
                case "explorar":
                    explorar();
                    break;
                case "pegar":
                    pegarItem();
                    break;
                case "largar":
                    largarItem();
                    break;
                case "inventario":
                    verInventario();
                    break;
                case "mover":
                    mover();
                    break;
                case "sair":
                    System.out.println("Até a próxima aventura!");
                    break;
                default:
                    System.out.println("Ação desconhecida, tente novamente.");
                    break;
            }
        } while (!acao.equals("sair"));
    }

    private void explorar() {
        System.out.println("Você está em " + localAtual.getNome() + ". O que deseja fazer?");
        List<String> acoes = localAtual.getAcoes();
        for (int i = 0; i < acoes.size(); i++) {
            System.out.println((i + 1) + ". " + acoes.get(i));
        }
        String escolha = scanner.nextLine();
        realizarAcao(escolha);
    }

    private void realizarAcao(String escolha) {
        List<String> acoes = localAtual.getAcoes();
        try {
            int acaoIndex = Integer.parseInt(escolha) - 1;
            if (acaoIndex >= 0 && acaoIndex < acoes.size()) {
                String acaoEscolhida = acoes.get(acaoIndex);
                System.out.println("Você escolheu realizar a ação: " + acaoEscolhida);
                if (localAtual.getNome().equals("MERCADO MUNICIPAL")) {
                    if (acaoEscolhida.equals("Ver mercado")) {
                        verMercado();
                    } else if (acaoEscolhida.equals("Perguntar vendedor")) {
                        perguntarVendedor();
                    }
                } else if (localAtual.getNome().equals("BECO ESCURO")) {
                    if (acaoEscolhida.equals("Ver beco escuro")) {
                        verBecoEscuro();
                    } else if (acaoEscolhida.equals("Perguntar informante")) {
                        perguntarInformante();
                    }
                } else if (localAtual.getNome().equals("MANSÃO ABANDONADA")) {
                    if (acaoEscolhida.equals("Ver mansão abandonada")) {
                        verMansao();
                    } else if (acaoEscolhida.equals("Perguntar fantasma")) {
                        perguntarFantasma();
                    }
                } else if (localAtual.getNome().equals("TUNEL SECRETO")){
                    if (acaoEscolhida.equals("Ver tunel")) {
                        verTunel();
                    } else if (acaoEscolhida.equals("Ler bilhete")) {
                        lerBilhete();
                    }
                } else if (localAtual.getNome().equals("ESTAÇÃO DE TREM")) {
                    if (acaoEscolhida.equals("Ver estação de trem")) {
                        verEstacaoDeTrem();
                    } else if (acaoEscolhida.equals("Perguntar viajante")) {
                        perguntarViajante();
                    }
                } else if (localAtual.getNome().equals("CEMITÉRIO ASSOMBRADO")) {
                    if (acaoEscolhida.equals("Ver cemitério assombrado")) {
                        verCemiterio();
                    } else if (acaoEscolhida.equals("Perguntar zelador")) {
                        perguntarZelador();
                    }
                } else {
                    System.out.println("Você não pode realizar esta ação aqui.");
                }
            } else {
                System.out.println("Escolha inválida.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
        }
    }

    private void verMercado() {
        System.out.println("Vejo um vendedor ambulante que pode fornecer dicas sobre rumores na cidade. Há também uma chave de ferro enferrujada em cima da mesa. E um mapa no canto do mercado.");
    }

    private void perguntarVendedor() {
        System.out.println("Ouvi dizer que um estranho foi visto rondando a mansão abandonada recentemente. Talvez seja uma pista para você.");
    }

    private void verBecoEscuro() {
        System.out.println("Vejo um informante misterioso que pode oferecer informações sobre o submundo da cidade. Há também uma lanterna velha e enferrujada no chão e uma faca afiada encostada na parede.");
    }

    private void perguntarInformante() {
        System.out.println("Se você está procurando informações sobre a relíquia, sugiro que investigue a gangue que opera na área do beco. Eles podem ter informações importantes.");
    }

    private void verMansao() {
        System.out.println("Vejo um fantasma assombrado que pode revelar segredos do passado. Há também um diário empoeirado em cima de uma mesa e uma joia cintilante em uma gaveta.");
    }

    private void perguntarFantasma() {
        System.out.println("Eu era o mordomo desta mansão antes de minha morte. Há um túnel secreto nos aposentos do antigo senhor, pode ser o que você está procurando.");
    }
    private void verTunel() {
        System.out.println("Vejo um bilhete o qual contém algo difícil de ser lido");
    }
    private void lerBilhete() {
        System.out.println("À sua direita contém um mapa. pegue-o");
    }

    private void verEstacaoDeTrem() {
        System.out.println("Vejo um viajante curioso que pode compartilhar histórias sobre suas viagens. Há também um bilhete de trem no chão e um horário de trem pendurado na parede.");
    }

    private void perguntarViajante() {
        System.out.println("Passei pelo mercado hoje e vi um homem estranho carregando algo parecido com a relíquia. Ele entrou em um trem com destino ao sul().");
    }

    private void verCemiterio() {
        System.out.println("Vejo um zelador taciturno que pode dar pistas sobre o que realmente acontece durante a noite. Há também um amuleto sinistro no chão e uma chave enferrujada encostada em uma lápide.");
    }

    private void perguntarZelador() {
        System.out.println("Se você está procurando algo valioso, sugiro que evite os corredores da ala leste(ESTAÇÂO DE TREM). Muitos intrusos encontraram problemas por lá.");
    }

    private void pegarItem() {
        System.out.println("Itens disponíveis:");
        List<String> itens = localAtual.getItens();
        for (int i = 0; i < itens.size(); i++) {
            System.out.println((i + 1) + ". " + itens.get(i));
        }
        String escolha = scanner.nextLine();
        try {
            int itemIndex = Integer.parseInt(escolha) - 1;
            if (itemIndex >= 0 && itemIndex < itens.size()) {
                String itemEscolhido = itens.get(itemIndex);
                inventario.add(itemEscolhido);
                localAtual.removerItem(itemEscolhido);
                System.out.println("Você pegou o item: " + itemEscolhido);
            } else {
                System.out.println("Escolha inválida.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
        }
    }

    private void largarItem() {
        System.out.println("Itens no seu inventário:");
        for (int i = 0; i < inventario.size(); i++) {
            System.out.println((i + 1) + ". " + inventario.get(i));
        }
        String escolha = scanner.nextLine();
        try {
            int itemIndex = Integer.parseInt(escolha) - 1;
            if (itemIndex >= 0 && itemIndex < inventario.size()) {
                String itemEscolhido = inventario.get(itemIndex);
                inventario.remove(itemEscolhido);
                localAtual.adicionarItem(itemEscolhido);
                System.out.println("Você largou o item: " + itemEscolhido);
            } else {
                System.out.println("Escolha inválida.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
        }
    }

    private void verInventario() {
        System.out.println("Seu inventário contém:");
        for (String item : inventario) {
            System.out.println("- " + item);
        }
    }

    private void mover() {
        System.out.println("Locais disponíveis:");
        List<String> locaisDisponiveis = new ArrayList<>(locais.keySet());
        for (int i = 0; i < locaisDisponiveis.size(); i++) {
            System.out.println((i + 1) + ". " + locaisDisponiveis.get(i));
        }
        String escolha = scanner.nextLine();
        try {
            int localIndex = Integer.parseInt(escolha) - 1;
            if (localIndex >= 0 && localIndex < locaisDisponiveis.size()) {
                String localEscolhido = locaisDisponiveis.get(localIndex);
                localAtual = locais.get(localEscolhido);
                System.out.println("Você se moveu para: " + localAtual.getNome());
            } else {
                System.out.println("Escolha inválida.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
        }
    }

    public static void main(String[] args) {
        JogoMisterio jogo = new JogoMisterio();
        jogo.jogar();
    }
}
