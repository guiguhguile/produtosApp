package com.produtos.produtosApp.services;

import com.produtos.produtosApp.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Produto, Integer> {
}
