// 1- pacote
package petstore;



//2- Bibliotecas


import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

//3- Classe
public class Pet {
//3.1 Atributos
    // Endereço da entidade Pet
String uri= "https://petstore.swagger.io/v2/pet";
//3.2 Métodos e Funções

   public String lerJson(String caminhoJson) throws IOException {

       return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }
// Incluir - Create - post

    //Identifica o método ou função como um teste para o TestNG
@Test

 public void incluirPet() throws IOException {
String jsonBody = lerJson("db/pet1.json");

// Sintaxe - Gherkin
    //Dado - Quando - Entao
    //Given - When - Then

    given()

            //comum em API Rest, antiga era usada "text/xml"
            .contentType("application/json")
            .log().all()
            .body(jsonBody)

    .when()
            .post(uri)

    .then()
            .log().all()
            .statusCode(200)
     ;
    }



}