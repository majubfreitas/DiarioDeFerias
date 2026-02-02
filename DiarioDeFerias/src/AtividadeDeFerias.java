import java.util.Objects;

public class AtividadeDeFerias {
    private String descricao;
    private int nota;
    private String tag;

    public AtividadeDeFerias(String descricao, int nota, String tag) {
        this.descricao = descricao;
        this.nota = nota;
        this.tag = tag;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getNota() {
        return nota;
    }

    public String getTag() {
        return tag;
    }

    @Override
    public String toString() {
        return String.format("Atividade: %s | Nota: %d/10 | Tag: %s",
                descricao, nota, tag);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        AtividadeDeFerias that = (AtividadeDeFerias) obj;
        return nota == that.nota &&
                Objects.equals(descricao, that.descricao) &&
                Objects.equals(tag, that.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descricao, nota, tag);
    }
}
