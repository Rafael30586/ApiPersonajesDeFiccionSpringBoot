package com.f_rafael.API_Personajes_de_ficcion.api.repository;

import com.f_rafael.API_Personajes_de_ficcion.repositories.ObraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ObraRepositoryTests {

    @Autowired
    private ObraRepository repository;

    public void ObraRepository_Guarda_RetornaObraqGuardada(){

    }
}
