package co.edu.uniandes.dse.parcialprueba.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcialprueba.entities.ConsultaMedicaEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import(ConsultaMedicaService.class)
public class ConsultaMedicaServiceTest {

    @Autowired
    private ConsultaMedicaService consultaMedicaService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private List<ConsultaMedicaEntity> consultaMedicaList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from ConsultaMedicaEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ConsultaMedicaEntity consulta = factory.manufacturePojo(ConsultaMedicaEntity.class);
            entityManager.persist(consulta);
            consultaMedicaList.add(consulta);
        }
    }

    @Test
    void createConsultaMedicaTest() {
        ConsultaMedicaEntity consulta = consultaMedicaList.get(0);

        consulta.setFecha(new Date(2036, 10, 10));

        ConsultaMedicaEntity resultado = null;
        try {
            resultado = consultaMedicaService.createConsultaMedica(consulta);
        } catch (IllegalOperationException e) {
            e.printStackTrace();
        }

        assertEquals(consulta.getId(), resultado.getId());
		assertEquals(consulta.getCausa(), resultado.getCausa());
		assertFalse(consulta.getFecha().after(resultado.getFecha()));
    }

}
