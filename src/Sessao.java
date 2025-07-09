import java.util.ArrayList;
import java.util.List;

public class Sessao {
    private boolean statusDaSessao;
    private int horario;
    private Sala nSalas;
    private Filme xFilme; // conectar
    private List<Ingresso> xIngresso; // conectar
    private final int precoBase = 25;

    public Sessao(boolean statusDaSessao, int horario, int qtdSalas, int nAssento, String tipoDeTela, String local) {
        this.statusDaSessao = statusDaSessao;
        this.horario = horario;
        nSalas = new Sala(nAssento,tipoDeTela, local); //composição
        this.xIngresso = new ArrayList<>();
    }

    public int assentosDisponiveis() {
        return nSalas.getnAssentos() - xIngresso.size();
    }

    public String comprarIngresso(TipoDeIngresso tipo, CategoriaIngresso categoria) {
        if (assentosDisponiveis() > 0) {
            Ingresso novoIngresso = IngressoFactory.criarIngresso(tipo, this, categoria);
            xIngresso.add(novoIngresso);
            return "Ingresso comprado com sucesso!| VALOR DO INGRESSO: " + novoIngresso.getPreco();
        } else {
            return "Não há assentos disponíveis.";
        }
    }

    public double getPrecoBase() {
        return precoBase;
    }
    public void adicionarFilmeNaSessao(Filme xFilme) {
        this.xFilme = xFilme;
    }

    public boolean isStatusDaSessao() {
        return statusDaSessao;
    }

    public void setStatusDaSessao(boolean statusDaSessao) {
        this.statusDaSessao = statusDaSessao;
    }

    public int getHorario() {
        return horario;
    }

    public void setHorario(int horario) {
        this.horario = horario;
    }

    public Sala getnSalas() {
        return nSalas;
    }

    public void setnSalas(Sala nSalas) {
        this.nSalas = nSalas;
    }


    @Override
    public String toString() {
        return String.format(
                "Sessão | Status: %s | Horário: %02d:00 | %s | Filme: %s",
                (statusDaSessao ? "Ativa" : "Encerrada"), horario, nSalas, xFilme.getTitulo());
    }
}
