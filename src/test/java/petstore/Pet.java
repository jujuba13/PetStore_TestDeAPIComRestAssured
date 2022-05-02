// 1- pacote
package petstore;



//2- Bibliotecas


import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;

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
@Test(priority =1)

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

     // checagem de validação
            .body("name", is ("salana"))
            .body("status", is ("available"))
            .body("category.name",is ("13jr35azx"))
            .body("tags.name", contains("TestApi"))
     ;
    }
    @Test(priority = 2)

    public void ConsultarPet(){

       String petId = "1977022313";

        String token =

     given()

                //comum em API Rest, antiga era usada "text/xml"
                .contentType("application/json")
                .log().all()


        .when()
                .get(uri + "/" + petId)

        .then()
             .log().all()
             .statusCode(200)

     //checagem para validação

             .body("name", is ("salana"))
             .body("category.name",is ("13jr35azx"))
             .body("status", is ("available"))
     .extract()
             .path("category.name")
     ;
        System.out.println("O token é " + token );
    }
@Test(priority = 3)

    public void alterarPet() throws IOException {

    String jsonBody = lerJson("db/pet2.json");

    given()

            //comum em API Rest, antiga era usada "text/xml"
            .contentType("application/json")
            .log().all()
            .body(jsonBody)



    .when()
            .put(uri)


    .then()
            .log().all()
            .statusCode(200)

    // Checagem da validação
            .body("name", is("salana"))
            .body("status", is("sold"))
    ;
}
@Test(priority = 4)

    public void excluirPet(){
    String petId = "1977022313";


    given()

            //comum em API Rest, antiga era usada "text/xml"
            .contentType("application/json")
            .log().all()




    .when()
            .delete(uri + "/" + petId)


    .then()
            .log().all()
            .statusCode(200)

    // checagem de validação
            .body("code", is(200))
            .body("type", is("unknown"))
            .body("message", is(petId))
    ;
}



}


