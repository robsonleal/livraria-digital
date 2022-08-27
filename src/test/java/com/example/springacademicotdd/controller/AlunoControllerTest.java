package com.example.springacademicotdd.controller;

import com.example.springacademicotdd.model.Aluno;
import com.example.springacademicotdd.service.AlunoService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

@WebMvcTest
public class AlunoControllerTest {

    @Autowired
    private AlunoController alunoController;

    @MockBean
    private AlunoService alunoService;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(alunoController);
    }

    @Test
    public void deveRetornarSucesso_QuandoBuscarAluno() {
        Mockito.when(alunoService.buscarPelaMatricula(1L))
            .thenReturn(new Aluno(1L, "Robson Leal", "2119849553", "Alvorada"));

        RestAssuredMockMvc
            .given()
                .accept(ContentType.JSON)
            .when()
                .get("/alunos/{id}", 1)
            .then()
                .statusCode(HttpStatus.OK.value());
    }
}