package br.com.lima.codechella;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EventoService {

    @Autowired
    private EventoRepository repositorio;

    public Flux<EventoDto> obterTodos() {
        return repositorio.findAll()
                .map(EventoDto::toDto);
    }

    public Mono<EventoDto> obterPorId(Long id) {
        return repositorio.findById(id)
                .map(EventoDto::toDto);
    }
}
