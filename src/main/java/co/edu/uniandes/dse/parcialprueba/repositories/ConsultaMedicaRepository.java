package co.edu.uniandes.dse.parcialprueba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniandes.dse.parcialprueba.entities.ConsultaMedicaEntity;

@Repository
public interface ConsultaMedicaRepository extends JpaRepository<ConsultaMedicaEntity, Long> {

}