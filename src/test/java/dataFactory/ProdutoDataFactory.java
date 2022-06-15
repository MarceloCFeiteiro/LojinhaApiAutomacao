package dataFactory;

import pojo.ComponentePojo;
import pojo.ProdutoPojo;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDataFactory {
    public  static ProdutoPojo criarProdutoComumComValor(double valor){
        ProdutoPojo produtoPojo = new ProdutoPojo();
        produtoPojo.setProdutoNome("Mesanino");
        produtoPojo.setProdutoValor(valor);
        List<String> cores = new ArrayList<>();
        cores.add("Azul");
        cores.add("Preto");
        cores.add("Branco");
        produtoPojo.setProdutoCores(cores);
        produtoPojo.setProdutoUrlMock("");

        List<ComponentePojo> componentesPojo = new ArrayList<>();

        ComponentePojo componentePojo = new ComponentePojo();
        componentePojo.setComponenteNome("Cadeira");
        componentePojo.setComponenteQuantidade(1);

        componentesPojo.add(componentePojo);

        produtoPojo.setComponente(componentesPojo);

        return produtoPojo;
    }
}
