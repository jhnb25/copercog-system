package copercog.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "CLIENTES")
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_CLIENTE")
    private int id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "CPF", unique = true)
    private String cpf;

    @Column(name = "SEXO")
    private String sexo;

    @Column(name = "DATA_DE_NASCIMENTO")
    private LocalDate dataNascimento;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    private Endereco_Fisico enderecoFisico;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    private ENDERECO_DIGITAL enderecoDigital;

    @OneToMany(mappedBy = "cliente")
    private List<Pedidos> pedidos;

    //setter pra futuro update
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setPedidos(List<Pedidos> pedidos) {
        this.pedidos = pedidos;
    }

    
    public void setEnderecoFisico(Endereco_Fisico ef) {
    this.enderecoFisico = ef;
    ef.setCliente(this);
}

public void setEnderecoDigital(ENDERECO_DIGITAL ed) {
    this.enderecoDigital = ed;
    ed.setCliente(this);
}
    
    
    
    @Override
    public String toString() {
        return this.nome; // ou whatever campo quiser mostrar
    }
    
    
    public Clientes() {}

    public Clientes(String nome, String cpf, String sexo, LocalDate dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getSexo() { return sexo; }
    public LocalDate getDataNascimento() { return dataNascimento; }

    public int getIdade() {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }
}