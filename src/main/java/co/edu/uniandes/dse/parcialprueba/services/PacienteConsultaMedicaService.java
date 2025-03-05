package co.edu.uniandes.dse.parcialprueba.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.ConsultaMedicaEntity;
import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.ConsultaMedicaRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;

import jakarta.transaction.Transactional;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Service
public class PacienteConsultaMedicaService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ConsultaMedicaRepository consultaMedicaRepository;

    @Transactional
    public PacienteEntity addConsultaMedica(Long pacienteId, Long consultaMedicaId) throws EntityNotFoundException, IllegalOperationException {
        
        log.info("Inicia proceso de agregar una consulta medica al paciente con id = {0}", pacienteId);

        Optional<PacienteEntity> pacienteEntity = pacienteRepository.findById(pacienteId);
        Optional<ConsultaMedicaEntity> consultaMedicaEntity = consultaMedicaRepository.findById(consultaMedicaId);

        if (pacienteEntity.isEmpty()){
            throw new EntityNotFoundException("No se encontró el profesional con id = " + pacienteId);
        }

        if (consultaMedicaEntity.isEmpty()){
            throw new EntityNotFoundException("No se encontró la consulta medica con id = " + consultaMedicaId);
        }
        
        List<ConsultaMedicaEntity> consultas = pacienteEntity.get().getConsultas();

        for (ConsultaMedicaEntity consulta : consultas) {
            if (consulta.getFecha().equals(consultaMedicaEntity.get().getFecha())) {
                throw new IllegalOperationException( "Ya existe una consulta medica con la misma fecha");
            }
        }

        pacienteEntity.get().getConsultas().add(consultaMedicaEntity.get());

        log.info("Finaliza proceso de agregar una consulta medica al paciente con id = {0}", pacienteId);

        return pacienteEntity.get();
    }

    @Transactional
    public List<ConsultaMedicaEntity> getConsultasProgramadas(Long pacienteId) throws EntityNotFoundException {
        
        log.info("Inicia proceso de consultar las consultas medicas programadas del paciente con id = {0}", pacienteId);

        Optional<PacienteEntity> pacienteEntity = pacienteRepository.findById(pacienteId);

        if (pacienteEntity.isEmpty()){
            throw new EntityNotFoundException("No se encontró el paciente con id = " + pacienteId);
        }

        List<ConsultaMedicaEntity> consultas = pacienteEntity.get().getConsultas();
        List<ConsultaMedicaEntity> consultasProgramadas = new ArrayList<>();

        for (ConsultaMedicaEntity consulta : consultas) {
            if (consulta.getFecha().after(new Date())) {
                consultasProgramadas.add(consulta);
            }
        }

        log.info("Finaliza proceso de consultar las consultas medicas programadas del paciente con id = {0}", pacienteId);

        return consultasProgramadas;
    }
}