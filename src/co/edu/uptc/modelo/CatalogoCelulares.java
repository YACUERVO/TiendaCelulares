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