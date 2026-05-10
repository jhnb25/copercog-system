package copercog.model;

import jakarta.persistence.*;

@Entity
@Table(name = "COGUMELOS")
public class Cogumelos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_COGUMELOS")
    private int id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "TIPO")
    private String tipo;

    @Column(name = "PRECO_KG")
    private double precoBase;

 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(double precoBase) {
        this.precoBase = precoBase;
    }

 

    @Override
    public String toString() {
        return this.nome; 
    }

    
    
    
    
    public Cogumelos() {}

   
    
    
    public Cogumelos(String nome,String tipo,Double precoBase ){
 
   this.nome = nome;
   this.tipo=tipo;
   
        this.precoBase = precoBase;
 
 }
    
    
    
}