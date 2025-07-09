public class PrecoMeia implements PrecoStrategy {
    @Override
    public double calcularPreco(double precoBase) {
        return precoBase / 2;
    }
}
