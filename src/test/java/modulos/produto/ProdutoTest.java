package modulos.produto;

import dataFactory.ProdutoDataFactory;
import dataFactory.UsuarioDataFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.ComponentePojo;
import pojo.ProdutoPojo;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

@DisplayName("Testes de API Rest do módulo de produto")
public class ProdutoTest {

    private String token;

    @BeforeEach
    public  void beforeEach(){
        // Configurando os dados da API Rest da lojinha
        baseURI="http://165.227.93.41";
        basePath="/lojinha";

        // Obter o token do usuário admin
        token = given()
                .contentType(ContentType.JSON)
                .body(UsuarioDataFactory.criarUsuarioAdm())
                .when()
                .post("/v2/login")
                .then()
                .extract()
                .path("data.token");
    }

    @Test
    @DisplayName("Validar que o valor do produto igual a 0.00 não é permitido")
    public void  testValidarLimiteZeradoProibidoProduto(){
        // Tentar inserir um produto com o valor 0.00 e validar que a mensagem de erro foi apresentada e o status code retornado foi o 422

        given()
                .contentType(ContentType.JSON)
                .header("token",token)
                .body(ProdutoDataFactory.criarProdutoComumComValor(0.00))
                .when()
                .post("/v2/produtos")
                .then()
                .assertThat()
                .body("error",equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                .statusCode(422);

    }

    @Test
    @DisplayName("Validar que valores maiores que 7000 não são permitidos")
    public void  testValidarLimitesMaiorSeteMilProibidoProduto(){
        // Tentar inserir um produto com o valor 0.00 e validar que a mensagem de erro foi apresentada e o status code retornado foi o 422
        ProdutoPojo produtoPojo = new ProdutoPojo();
        produtoPojo.setProdutoNome("Mesanino");
        produtoPojo.setProdutoValor(7000.01);
        List<String> cores = new ArrayList<>();
        cores.add("Azul");
        cores.add("Preto");
        cores.add("Branco");
        produtoPojo.setProdutoCores(cores);
        produtoPojo.setProdutoUrlMock("");

        List<ComponentePojo> componentesPojos = new ArrayList<>();

        ComponentePojo componentesPojo = new ComponentePojo();
        componentesPojo.setComponenteNome("Cadeira");
        componentesPojo.setComponenteQuantidade(1);

        produtoPojo.setComponente(componentesPojos);
        given()
                .contentType(ContentType.JSON)
                .header("token",token)
                .body(ProdutoDataFactory.criarProdutoComumComValor(0.00))
                .when()
                .post("/v2/produtos")
                .then()
                .assertThat()
                .body("error",equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                .statusCode(422);

    }
}
