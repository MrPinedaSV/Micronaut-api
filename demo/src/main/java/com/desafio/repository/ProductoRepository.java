package com.desafio.repository;

import com.desafio.model.Producto;
import jakarta.inject.Singleton;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Singleton
public class ProductoRepository {

    private final Map<Long, Producto> productos = new HashMap<>();
    private final AtomicLong idGen = new AtomicLong(1);

    public List<Producto> findAll() {
        return new ArrayList<>(productos.values());
    }

    public Optional<Producto> findById(Long id) {
        return Optional.ofNullable(productos.get(id));
    }

    public Producto save(Producto producto) {
        Long id = idGen.getAndIncrement();
        producto.setId(id);
        productos.put(id, producto);
        return producto;
    }

    public Optional<Producto> update(Long id, Producto producto) {
        if (productos.containsKey(id)) {
            producto.setId(id);
            productos.put(id, producto);
            return Optional.of(producto);
        }
        return Optional.empty();
    }

    public boolean delete(Long id) {
        return productos.remove(id) != null;
    }
}