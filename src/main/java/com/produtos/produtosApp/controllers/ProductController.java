package com.produtos.produtosApp.controllers;


import com.produtos.produtosApp.models.Produto;
import com.produtos.produtosApp.models.ProdutoDto;
import com.produtos.produtosApp.services.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
@RequestMapping("/produtos")
public class ProductController {

    @Autowired
    private ProductRepository repo;

    @GetMapping({"", "/"})
    public String listProducts(Model model) {
        List<Produto> products = repo.findAll();
        model.addAttribute("products", products);
        return "produtos/index";
    }

    @GetMapping("/novo-produto")
    public String createPage(Model model) {
        ProdutoDto produtoDto = new ProdutoDto();
        model.addAttribute("produtoDto", produtoDto);
        return "produtos/criarProduto";
    }

    @PostMapping("/novo-produto")
    public String createProduct(@Valid @ModelAttribute ProdutoDto productDto, BindingResult result) {
        if (result.hasErrors()) {
            return "produtos/criarProduto";
        }

        Produto produto = new Produto();
        produto.setNome(productDto.getNome());
        produto.setDescricao(productDto.getDescricao());
        produto.setPreco(productDto.getPreco());
        produto.setCategoria(productDto.getCategoria());

        repo.save(produto);

        return "redirect:/produtos";
    }

    @GetMapping("/editar")
    public String editPage(Model model, @RequestParam int id) {
        try {
            Produto produto = repo.findById(id).get();
            model.addAttribute("produto", produto);

            ProdutoDto produtoDto = new ProdutoDto();
            produtoDto.setNome(produto.getNome());
            produtoDto.setDescricao(produto.getDescricao());
            produtoDto.setPreco(produto.getPreco());
            produtoDto.setCategoria(produto.getCategoria());

            model.addAttribute("produtoDto", produtoDto);

        } catch (Exception ex) {
            System.out.println("Erro ao atualizar produto:" + ex.getMessage());
            return "redirect:/produtos";
        }

        return "produtos/editar-produto";
    }

    @PostMapping("/editar")
    public String editProduct(Model model, @RequestParam int id, @Valid @ModelAttribute ProdutoDto productDto, BindingResult result) {
        try {
            Produto produto = repo.findById(id).get();
            model.addAttribute("produto", produto);

            if (result.hasErrors()) {
                return "produtos/editar-produto";
            }

            produto.setNome(productDto.getNome());
            produto.setDescricao(productDto.getDescricao());
            produto.setPreco(productDto.getPreco());
            produto.setCategoria(productDto.getCategoria());

            repo.save(produto);

        } catch (Exception ex) {
            System.out.println("Erro ao atualizar produto:" + ex.getMessage());
        }

        return "redirect:/produtos";

    }


    @GetMapping("/deletar")
    public String deleteProduct(@RequestParam int id) {
        try{
            Produto produto = repo.findById(id).get();

            repo.delete(produto);
        } catch (Exception ex) {
            System.out.println("Erro ao deletar produto:" + ex.getMessage());
        }

        return "redirect:/produtos";
    }
}