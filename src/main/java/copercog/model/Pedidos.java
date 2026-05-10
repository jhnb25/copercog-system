package copercog.model;
import java.time.LocalDate;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "PEDIDOS")
public class Pedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPEDIDOS")
    private int id;

    @Column(name = "PESO")
    private double peso;

    @Column(name = "DATA_PEDIDO", insertable = false, updatable = false)
    private LocalDateTime dataPedido;

    @Column(name = "PRECO_TOTAL")
    private double precoTotal;

    @Column(name = "DATA_ENTREGA")
    private LocalDate dataEntrega;

    @Column(name = "NOME_PRODUTO")
    private String nomeProduto;

@Column(name = "NOME_CLIENTE")
    private String nomeCliente;

    @Column(name = "STATUS")
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_CLIENTE")
    private Clientes cliente;

    public Pedidos() {}

    public Pedidos(Clientes cliente, String nomeProduto, double peso, Cogumelos cogumelo) {
        this.cliente     = cliente;
        this.nomeCliente = cliente.getNome();            
        this.nomeProduto = nomeProduto;
        this.peso        = peso;
        this.status      = "PENDENTE";
        this.precoTotal  = cogumelo.getPrecoBase() * peso;
        this.dataEntrega = LocalDate.now().plusDays(7); 
    }

 
    public int getId()                  { 
        return id; }
    public double getPeso()             { 
        return peso; }
    public void setPeso(double peso)    { 
        this.peso = peso; }
    public double getPrecoTotal()       { 
        return precoTotal; }
    public LocalDate getDataEntrega()   { 
        return dataEntrega; }
    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega; }
    public String getNomeProduto()      { 
        return nomeProduto; }
    public String getNomeCliente()      {
        return nomeCliente; }
    public String getStatus()           {
        return status; }
    public void setStatus(String status){
        this.status = status; }
    public Clientes getCliente()        {
        return cliente; }

    @Transient
    public String getDataPedidoFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return dataPedido != null ? dataPedido.format(formatter) : "";
    }

    public void calcularPrecoTotal(Cogumelos c) {
        this.precoTotal = c.getPrecoBase() * this.peso;
    }
}