package com.artur.agendador_de_horario.repository;

import com.artur.agendador_de_horario.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento,Long> {
}
