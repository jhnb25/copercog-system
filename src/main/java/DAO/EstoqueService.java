package DAO;

public class EstoqueService {

    private EstoqueDAO dao = new EstoqueDAO();

    public boolean adicionar(String nome, String qtdTexto) {

        if (nome == null || nome.trim().isEmpty()) {
            return false;
        }

        if (!nome.matches("^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$")) {
            return false;
        }

        double qtd;

        try {
            qtd = Double.parseDouble(qtdTexto);
        } catch (NumberFormatException e) {
            return false;
        }

        if (qtd <= 0) {
            return false;
        }

        return dao.addQuantidade(nome, qtd);
    }

    public boolean remover(String nome, String qtdTexto) {

        if (nome == null || nome.trim().isEmpty()) {
            return false;
        }

        double qtd;

        try {
            qtd = Double.parseDouble(qtdTexto);
        } catch (NumberFormatException e) {
            return false;
        }

        if (qtd <= 0) {
            return false;
        }

        return dao.diminuirQuantidade(nome, qtd);
    }
}