package DAO;

import copercog.model.Cogumelos;

public class EstoqueService {

    private EstoqueDAO dao = new EstoqueDAO();

    public boolean adicionar(Cogumelos cogumelo, String qtdTexto) {
        if (cogumelo == null) {
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

        return dao.addQuantidade(cogumelo.getNome(), qtd);
    }

    public boolean remover(Cogumelos cogumelo, String qtdTexto) {
        if (cogumelo == null) {
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

        return dao.diminuirQuantidade(cogumelo.getNome(), qtd);
    }
}