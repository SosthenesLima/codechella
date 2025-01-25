package br.com.lima.codechella;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@RestController
@RequestMapping("/ingressos")
public class IngressoController {
    private final IngressoService servico;
    private final Sinks.Many<IngressoDto> ingressoSink;

    public IngressoController(IngressoService servico) {
        this.servico = servico;
        this.ingressoSink = Sinks.many().multicast().onBackpressureBuffer();
    }

    @GetMapping
    public Flux<IngressoDto> obterTodos() {
        return servico.obterTodos();
    }

    @GetMapping("/{id}")
    public Mono<IngressoDto> obterPorId(@PathVariable Long id) {
        return servico.obterPorId(id);
    }

    @PostMapping
    public Mono<IngressoDto> cadastrar(@RequestBody IngressoDto dto) {
        return servico.cadastrar(dto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> excluir(@PathVariable Long id) {
        return servico.excluir(id);

    }

    @PutMapping("/{id}")
    public Mono<IngressoDto> alterar(@PathVariable Long id, @RequestBody IngressoDto dto){
        return servico.alterar(id, dto);
    }
}
