import java.util.ArrayList;
import java.util.List;

public class Filme {
    private String titulo;
    private int duracao;
    private GeneroFilme generoFilme;

    public Filme(String titulo, int duracao, GeneroFilme generoFilme) {
        this.titulo = titulo;
        this.duracao = duracao;
        this.generoFilme = generoFilme;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public GeneroFilme getGeneroFilme() {
        return generoFilme;
    }

    public void setGeneroFilme(GeneroFilme generoFilme) {
        this.generoFilme = generoFilme;
    }

    @Override
    public String toString() {
        return String.format("Título: %s | Duração: %dh | Gênero: %s",
                titulo, duracao, generoFilme);
    }
}
