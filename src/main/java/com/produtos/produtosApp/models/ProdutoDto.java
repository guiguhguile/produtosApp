package com.produtos.produtosApp.models;


import jakarta.validation.constraints.*;

public class ProdutoDto {

    @NotEmpty(message = "O nome é obrigatório")
    private String nome;


    @Size(min= 10 ,message = "A descrição deve ter ao menos 10 caracteres" )
    @Size(max= 1000,message = "A descrição deve ter no máximo 10 caracteres" )
    private String descricao;


    @NotEmpty(message = "A categoria é obrigatório")
    private String categoria;


    @Min(value = 1, message = "O preço é obrigatório")
    private double preco;

    public @NotEmpty(message = "O nome é obrigatório") String getNome() {
        return nome;
    }

    public void setNome(@NotEmpty(message = "O nome é obrigatório") String nome) {
        this.nome = nome;
    }

    public @Size(min = 10, message = "A descrição deve ter ao menos 10 caracteres") @Size(max = 1000, message = "A descrição deve ter no máximo 10 caracteres") String getDescricao() {
        return descricao;
    }

    public void setDescricao(@Size(min = 10, message = "A descrição deve ter ao menos 10 caracteres") @Size(max = 1000, message = "A descrição deve ter no máximo 10 caracteres") String descricao) {
        this.descricao = descricao;
    }

    public @NotEmpty(message = "A categoria é obrigatório") String getCategoria() {
        return categoria;
    }

    public void setCategoria(@NotEmpty(message = "A categoria é obrigatório") String categoria) {
        this.categoria = categoria;
    }

    @Min(value = 1, message = "O preço é obrigatório")
    public double getPreco() {
        return preco;
    }

    public void setPreco(@Min(value = 1, message = "O preço é obrigatório") double preco) {
        this.preco = preco;
    }
}
