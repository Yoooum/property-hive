package com.prprv.property.controller;

import com.prprv.property.common.response.E;
import com.prprv.property.common.response.R;
import com.prprv.property.entity.AbstractEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Yoooum
 */
public abstract class AbstractCrudController<T extends AbstractEntity, D extends JpaRepository<T, Long>> {
    protected final D repository;

    protected AbstractCrudController(D repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public R<T> findById(@PathVariable Long id) {
        Optional<T> byId = repository.findById(id);
        return byId.map(R::ok).orElseGet(() -> R.error(E.NOT_FOUND));
    }

    @PostMapping
    public R<T> create(@RequestBody T entity) {
        return R.ok(repository.save(entity));
    }

    @PutMapping("/{id}")
    public R<T> update(@PathVariable Long id, @RequestBody T entity) {
        Optional<T> existEntity = repository.findById(id);
        if (existEntity.isPresent()) {
            T updateEntity = existEntity.get();
            BeanUtils.copyProperties(entity, updateEntity, "id");
            return R.ok(repository.saveAndFlush(updateEntity));
        }
        return R.error(E.UPDATE_FAILED);
    }

    @DeleteMapping("/{id}")
    public R<Void> delete(@PathVariable Long id) {
        Optional<T> existEntity = repository.findById(id);
        if (existEntity.isPresent()) {
            repository.delete(existEntity.get());
            return R.ok();
        }
        return R.error(E.NOT_FOUND);
    }

    @GetMapping("/all")
    public R<List<T>> findAll() {
        return R.ok(repository.findAll());
    }

    @GetMapping("/page")
    public R<Page<T>> findAll(Integer page, Integer size, String sort, String direction) {
        Pageable pageable = direction.equals("asc") || direction.equals("desc")
                ? PageRequest.of(page, size, Sort.by(direction, sort))
                : PageRequest.of(page, size);
        return R.ok(repository.findAll(pageable));
    }
}
