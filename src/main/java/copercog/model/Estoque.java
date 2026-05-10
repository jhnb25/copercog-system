package copercog.model;
import jakarta.persistence.*;

@Entity
@Table(name = "ESTOQUE")
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ESTOQUE")
    private int id;

    @Column(name = "PESO_ATUAL")
    private double pesoAtual;

    @OneToOne
    @JoinColumn(name = "id_COGUMELOS")
    private Cogumelos produto;

    public Estoque() {}

    public Estoque(double pesoAtual, Cogumelos produto) {
        this.pesoAtual = pesoAtual;
        this.produto = produto;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public double getPesoAtual() { return pesoAtual; }
    public void setPesoAtual(double pesoAtual) { this.pesoAtual = pesoAtual; }

    public Cogumelos getProduto() { return produto; }
    public void setProduto(Cogumelos produto) { this.produto = produto; }
}