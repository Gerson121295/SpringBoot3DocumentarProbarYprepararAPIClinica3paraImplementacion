package med.voll.api.domain.paciente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> { //Se envia la entidada y su tipo de id
}
