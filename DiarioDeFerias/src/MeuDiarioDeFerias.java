import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MeuDiarioDeFerias {
    private List<AtividadeDeFerias> atividades;

    public MeuDiarioDeFerias() {
        this.atividades = new ArrayList<>();
    }

    public void adicionarAtividade(AtividadeDeFerias atividade) {
        atividades.add(atividade);
    }

    public List<AtividadeDeFerias> listarTodasAtividades() {
        return new ArrayList<>(atividades);
    }

    public List<AtividadeDeFerias> listarPorNota() {
        List<AtividadeDeFerias> ordenadas = new ArrayList<>(atividades);
        ordenadas.sort(Comparator.comparingInt(AtividadeDeFerias::getNota).reversed());
        return ordenadas;
    }

    public List<AtividadeDeFerias> pesquisarPorTag(String tag) {
        List<AtividadeDeFerias> resultado = new ArrayList<>();
        for (AtividadeDeFerias atividade : atividades) {
            if (atividade.getTag().equalsIgnoreCase(tag)) {
                resultado.add(atividade);
            }
        }
        return resultado;
    }

    public boolean estaVazio() {
        return atividades.isEmpty();
    }

    public int quantidadeAtividades() {
        return atividades.size();
    }
}
