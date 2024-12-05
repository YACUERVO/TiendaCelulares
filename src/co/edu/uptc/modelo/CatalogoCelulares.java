package co.edu.uptc.modelo;

import java.util.ArrayList;
import java.util.List;

public class CatalogoCelulares {
    private List<Celular> celulares;

    public CatalogoCelulares() {
        celulares = new ArrayList<>();
        inicializarCatalogo();
    }

    private void inicializarCatalogo() {
        celulares.add(new Celular("Motorola", "One Plus", "MOP", 850000, 40));
        celulares.add(new Celular("Motorola", "One Simple", "MOS", 750000, 39));
        celulares.add(new Celular("Motorola", "G6 Plus", "MG6", 750000, 89));
        celulares.add(new Celular("Motorola", "G7 Power", "MG7", 750000, 39));
        celulares.add(new Celular("Huawei", "Mate 20 plus ", "HM20P", 1050000, 185));
        celulares.add(new Celular("Huawei", "Y9 PRIME", "HY9P", 500000, 20));
        celulares.add(new Celular("Huawei", "P30", "HP30", 1500000, 105));
        celulares.add(new Celular("Huawei", "P30 LITE", "HP30L", 850000, 95));
        celulares.add(new Celular("Samsumg", "Galaxy A40", "SGA40", 450000, 75));
        celulares.add(new Celular("Samsumg", "Galaxy A50", "SGA50", 150000, 80));
        celulares.add(new Celular("Samsumg", "Galaxy A70", "SGA70", 750000, 150));
        celulares.add(new Celular("Samsumg", "Galaxy A80", "SGA80", 3500000, 105));
    }

    public List<Celular> getCelulares() {
        return new ArrayList<>(celulares);
    }

    public Celular buscarCelularPorCodigo(String codigo) {
        return celulares.stream()
                .filter(c -> c.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }
}