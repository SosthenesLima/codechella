/*
By Sósthenes Oliveira Lima
 */

package br.com.lima.codechella;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTestAotProcessor;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CodechellaApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void cadastraNovoEvento() {
		EventoDto dto = new EventoDto(null, TipoEvento.SHOW, "Kiss", LocalDate.parse("2025-11-02"), "Show da melhor banda que existe");

		webTestClient.post().uri("/eventos").bodyValue(dto)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(EventoDto.class)
				.value(response -> {
					assertNotNull(response.id());
					assertEquals(dto.tipo(), response.tipo());
					assertEquals(dto.nome(), response.nome());
					assertEquals(dto.data(), response.data());
					assertEquals(dto.descricao(), response.descricao());
				});
	}

	@Test
	void buscarEvento() {
		EventoDto dto = new EventoDto(13L, TipoEvento.SHOW, "The Weeknd", LocalDate.parse("2025-11-02"), "Um show eletrizante ao ar livre com muitos efeitos especiais.");

		webTestClient.get().uri("/eventos")
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBodyList(EventoDto.class)
				.value(response -> {
					EventoDto eventoRespose = response.get(13);
					assertEquals(dto.id(), eventoRespose.id());
					assertEquals(dto.tipo(), eventoRespose.tipo());
					assertEquals(dto.nome(), eventoRespose.nome());
					assertEquals(dto.data(), eventoRespose.data());
					assertEquals(dto.descricao(), eventoRespose.descricao());
				});
	}
}
