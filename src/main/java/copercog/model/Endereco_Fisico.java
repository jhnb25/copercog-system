package copercog.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ENDERECO_FISICO")
public class Endereco_Fisico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ENDERECO_FISICO")
    private int id;

    @Column(name = "PAIS")
    private String pais;

    @Column(name = "UF")
    private String uf;

    @Column(name = "CIDADE")
    private String cidade;

    @Column(name = "BAIRRO")
    private String bairro;

    @Column(name = "RUA")
    private String rua;

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    @Column(name = "CEP")
    private String cep;

    @OneToOne
    @JoinColumn(name = "id_CLIENTE")
    private Clientes cliente;

    public Endereco_Fisico() {}

    public Endereco_Fisico(String pais, String uf, String cidade,
                           String bairro, String rua, String cep, Clientes cliente) {
        this.pais = pais;
        this.uf = uf;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.cep = cep;
        this.cliente = cliente;
    }
}