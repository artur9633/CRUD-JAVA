package com.artur.agendador_de_horario.controller;

import com.artur.agendador_de_horario.entity.Agendamento;
import com.artur.agendador_de_horario.services.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor


public class AgendamentoController {

private  final AgendamentoService agendamentoService;

@PostMapping
    public ResponseEntity<Agendamento> salvarAgendamento(@RequestBody Agendamento agendamento);
return ResponseEntity.ok().body(agendamentoService.salvarAgendamento(agendamento));
}
}