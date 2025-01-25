package br.com.lima.codechella;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("vendas")
public class Venda {
    @Id
    private Long id;
    private Long ingressoId;
    private int total;

    //getters e setters
}