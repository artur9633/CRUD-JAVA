package com.artur.agendador_de_horario.services;

import com.artur.agendador_de_horario.entity.Agendamento;
import com.artur.agendador_de_horario.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor //injeção de dependência
public class AgendamentoService {
    private final AgendamentoRepository agendamentoRepository;

    public Agendamento salvarAgendamento(Agendamento agendamento) {

        LocalDateTime horaAgendamento = agendamento.getDataHoraAgendamento();
        LocalDateTime horaFim = agendamento.getDataHoraAgendamento().plusHours(1); //gap de uma hora,para próximo agendamento

        Agendamento agendados = agendamentoRepository.findByServicoAndDataHoraAgendamentoBetween(agendamento.getServico(), horaAgendamento, horaFim);

        if (Objects.nonNull(agendados)) {
            throw new RuntimeException("O Horário já encontra-se preenchido");

        }
        return agendamentoRepository.save(agendamento);


    }

    public void deletarAgendamento(LocalDateTime dataHoraAgendamento, String cliente) {

        agendamentoRepository.deleteByDataHoraAgendamentoAndCliente(dataHoraAgendamento, cliente);

    }

    public Agendamento buscarAgendamentosDia(LocalDate data) {
        LocalDateTime primeiraHoraDia = data.atStartOfDay();
        LocalDateTime horaFinalDia = data.atTime(23, 59);

        return agendamentoRepository.findByServicoAndDataHoraAgendamentoBetween(primeiraHoraDia ,  horaFinalDia);
    }

    public Agendamento alterarAgendamento(Agendamento agendamento, String cliente, LocalDateTime dataHoraAgendamento) {

        Agendamento agenda = agendamentoRepository.findByDataHoraAgendamentoAndCliente(dataHoraAgendamento, cliente);

        if (Objects.isNull(agenda)) {
            throw new RuntimeException("O Horário não encontra-se preenchido");

        }
        agendamento.setId(agenda.getId());
        return agendamentoRepository.save(agendamento);
    }
}