package co.edu.uniandes.dse.parcialprueba.services;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import(PacienteConsultaMedicaService.class)
public class PacienteConsultaMedicaServiceTest {
    
    @Autowired
    private PacienteConsultaMedicaService pacienteConsultaMedicaService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private List<ConsultaMedicaEntity> consultaMedicaList = new ArrayList<>();
    private List<PacienteEntity> pacienteList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from ConsultaMedicaEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("delete from PacienteEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ConsultaMedicaEntity consulta = factory.manufacturePojo(ConsultaMedicaEntity.class);
            entityManager.persist(consulta);
            consultaMedicaList.add(consulta);

            PacienteEntity paciente = factory.manufacturePojo(PacienteEntity.class);
            entityManager.persist(paciente);
            pacienteList.add(paciente);
        }

        PacienteEntity paciente = pacienteList.get(0);

        ConsultaMedicaEntity consulta = factory.manufacturePojo(ConsultaMedicaEntity.class);
        consulta.setPaciente(paciente);
        entityManager.persist(consulta);
        if (paciente.getConsultas() == null) {
            paciente.setConsultas(new ArrayList<ConsultaMedicaEntity>());
        }
        paciente.getConsultas().add(consulta);
    }

    @Test
    void addConsultaMedicaTest() throws EntityNotFoundException, IllegalOperationException {
        PacienteEntity paciente = pacienteList.get(0);
        ConsultaMedicaEntity consulta = consultaMedicaList.get(0);

        consulta.setFecha(new Date(2036,10,10));

        PacienteEntity resultado = pacienteConsultaMedicaService.addConsultaMedica(paciente.getId(), consulta.getId());

        assert(resultado.getConsultas().contains(consulta));
    }

    @Test
    void addCosultaMedicaTestFail() {
        PacienteEntity paciente = pacienteList.get(0);
        ConsultaMedicaEntity consulta = consultaMedicaList.get(0);

        consulta.setFecha(paciente.getConsultas().get(0).getFecha());

        assertThrows(IllegalOperationException.class, () -> pacienteConsultaMedicaService.addConsultaMedica(paciente.getId(), consulta.getId()));
    }
}
