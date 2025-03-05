package co.edu.uniandes.dse.parcialprueba.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
public class ConsultaMedicaEntity extends BaseEntity{

    private String causa;    
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @ManyToOne
    private PacienteEntity paciente;
    
}
