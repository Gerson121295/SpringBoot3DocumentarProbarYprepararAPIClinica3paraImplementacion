package med.voll.api.domain.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> { //Se envia la entidada y su tipo de id

    Page<Paciente> findAllByActivoTrue(Pageable paginacion);

//where p.id=:idPaciente  //Donde p.id se igual al id del paciente enviado.
    @Query("""
            select p.activo
            from Paciente p
            where p.id=:idPaciente
            """)
    Boolean findActivoById(Long idPaciente);
}
