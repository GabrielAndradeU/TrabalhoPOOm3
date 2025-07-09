import java.util.List;

public class Ingresso {
    private TipoDeIngresso tipo;
    private CategoriaIngresso categoriaIngresso;
    private Sessao iSessao;
    private PrecoStrategy precoStrategy;

    public Ingresso(TipoDeIngresso tipo, Sessao iSessao, CategoriaIngresso categoriaIngresso, PrecoStrategy precoStrategy) {
        this.tipo = tipo;
        this.iSessao = iSessao;
        this.categoriaIngresso = categoriaIngresso;
        this.precoStrategy = precoStrategy;
    }
    public double getPreco() {
        return precoStrategy.calcularPreco(iSessao.getPrecoBase());
    }

    public TipoDeIngresso getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeIngresso tipo) {
        this.tipo = tipo;
    }

    public CategoriaIngresso getCategoriaIngresso() {
        return categoriaIngresso;
    }

    public void setCategoriaIngresso(CategoriaIngresso categoriaIngresso) {
        this.categoriaIngresso = categoriaIngresso;
    }

    @Override
    public String toString() {
        return String.format(
                "Ingresso { TIPO: %s, CATEGORIA: %s, SESSAO: %s }",
                tipo,
                categoriaIngresso,
                iSessao != null ? iSessao.getHorario() + "h" : "Sessão não definida");
    }
}
