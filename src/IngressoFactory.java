public class IngressoFactory {
    public static Ingresso criarIngresso(TipoDeIngresso tipo, Sessao sessao, CategoriaIngresso categoria) {
        PrecoStrategy precoStrategy;

        switch (tipo) {
            case IngressoInteiro:
                precoStrategy = new PrecoInteira();
                break;
            case MeioIngresso:
                precoStrategy = new PrecoMeia();
                break;
            default:
                throw new IllegalArgumentException("Tipo de ingresso inválido: " + tipo);
        }

        return new Ingresso(tipo, sessao, categoria, precoStrategy);
    }
}
