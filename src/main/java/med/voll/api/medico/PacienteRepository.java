package med.voll.api.medico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> { //Se envia la entidada y su tipo de id
}
