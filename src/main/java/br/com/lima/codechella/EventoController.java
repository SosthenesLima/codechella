package br.com.lima.codechella;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.awt.*;

@RestController
@RequestMapping("/eventos")
public class EventoController {


    private  final EventoService servico;
    private final Sinks.Many<EventoDto> eventosSink;

    public EventoController(EventoService servico) {
        this.servico = servico;
        this.eventosSink = Sinks.many().multicast().onBackpressureBuffer();
    }

    @GetMapping //(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<EventoDto> obterTodos() {
        return servico.obterTodos();
    }

    @GetMapping("/{id}")
    public Mono<EventoDto> obterPorId(@PathVariable Long id) {
        return servico.obterPorId(id);
    }

    @PostMapping
    public Mono<EventoDto> cadastrar(@RequestBody EventoDto dto) {
        return servico.cadastrar(dto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> excluir(@PathVariable Long id) {
        return servico.excluir(id);
    }

    @PutMapping("{id}")
    public Mono<EventoDto> alterar(@PathVariable Long id, @RequestBody EventoDto dto) {
        return servico.alterar(id, dto);
    }
}
