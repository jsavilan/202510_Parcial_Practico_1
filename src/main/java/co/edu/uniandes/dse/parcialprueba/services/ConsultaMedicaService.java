package co.edu.uniandes.dse.parcialprueba.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.ConsultaMedicaEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.ConsultaMedicaRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Service
public class ConsultaMedicaService {

    @Autowired
    private ConsultaMedicaRepository consultaMedicaRepository;

    @Transactional
    public ConsultaMedicaEntity createConsultaMedica(ConsultaMedicaEntity consultaMedica) throws IllegalOperationException {
        log.info("Inicia proceso de creación de una consulta medica");

        if (!(consultaMedica.getFecha().after(new Date()))) {
            throw new IllegalOperationException("La fecha de la consulta medica debe ser posterior a la fecha actual");
        }

        ConsultaMedicaEntity consultaMedicaNueva = consultaMedicaRepository.save(consultaMedica);

        log.info("Finaliza proceso de creación de una consulta medica");

        return consultaMedicaNueva;
    }

}
