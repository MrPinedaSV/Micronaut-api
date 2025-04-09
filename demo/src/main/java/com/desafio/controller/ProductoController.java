package com.desafio.controller;

import com.desafio.model.Producto;
import com.desafio.repository.ProductoRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller("/productos")
public class ProductoController {

    private final ProductoRepository repository;

    public ProductoController(ProductoRepository repository) {
        this.repository = repository;
    }

    @Get
    public List<Producto> getAll() {
        return repository.findAll();
    }

    @Get("/{id}")
    public HttpResponse<Producto> getById(Long id) {
        Optional<Producto> producto = repository.findById(id);
        return producto.map(HttpResponse::ok)
                       .orElse(HttpResponse.notFound());
    }

    @Post
    public HttpResponse<Producto> create(@Body Producto producto) {
        return HttpResponse.created(repository.save(producto));
    }

    @Put("/{id}")
    public HttpResponse<Producto> update(Long id, @Body Producto producto) {
        Optional<Producto> updated = repository.update(id, producto);
        return updated.map(HttpResponse::ok)
                      .orElse(HttpResponse.notFound());
    }

    @Delete("/{id}")
    public HttpResponse<?> delete(Long id) {
        return repository.delete(id) ? HttpResponse.noContent() : HttpResponse.notFound();
    }
}