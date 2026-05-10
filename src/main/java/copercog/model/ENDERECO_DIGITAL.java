package copercog.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ENDERECO_DIGITAL")
public class ENDERECO_DIGITAL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ENDERECO_DIGITAL")
    private int id;

    @Column(name = "EMAIL", unique = true, nullable = false)
    private String emailPrincipal;

    @Column(name = "EMAIL_OPCIONAL", unique = true, nullable = true)
    private String emailOpcional;

    @Column(name = "TELEFONE", unique = true, nullable = true)
    private String telefone;

    @OneToOne
    @JoinColumn(name = "id_CLIENTE")
    private Clientes cliente;

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public ENDERECO_DIGITAL() {}

    public ENDERECO_DIGITAL(String emailPrincipal, String emailOpcional,
                            String telefone, Clientes cliente) {
        this.emailPrincipal = emailPrincipal;
        this.emailOpcional = emailOpcional;
        this.telefone = telefone;
        this.cliente = cliente;
    }

    public int getId() { return id; }
    public String getEmailPrincipal() { return emailPrincipal; }
    public String getEmailOpcional() { return emailOpcional; }
    public String getTelefone() { return telefone; }
    public Clientes getCliente() { return cliente; }
}