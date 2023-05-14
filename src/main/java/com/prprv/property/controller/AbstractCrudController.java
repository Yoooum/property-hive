package com.prprv.property.controller;

import com.prprv.property.common.response.E;
import com.prprv.property.common.response.R;
import com.prprv.property.entity.AbstractEntity;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Yoooum
 */
public abstract class AbstractCrudController<T extends AbstractEntity, D extends JpaRepository<T, Long>> {
    protected final D repository;

    protected AbstractCrudController(D repository) {
        this.repository = repository;
    }

    @Operation(summary = "根据ID查询实体")
    @GetMapping("/{id}")
    public R<T> getById(@PathVariable Long id) {
        Optional<T> byId = repository.findById(id);
        return byId.map(R::ok).orElseGet(() -> R.error(E.NOT_FOUND));
    }

    @Operation(summary = "创建实体")
    @PostMapping
    public R<T> create(@RequestBody T entity) {
        entity.setId(null);
        try {
            return R.ok(repository.save(entity));
        } catch (Exception e) {
            return R.error(E.CREATE_FAILED);
        }
    }

    @Operation(summary = "根据ID更新实体")
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

    @Operation(summary = "根据ID删除实体")
    @DeleteMapping("/{id}")
    public R<Void> delete(@PathVariable Long id) {
        Optional<T> existEntity = repository.findById(id);
        if (existEntity.isPresent()) {
            repository.delete(existEntity.get());
            return R.ok();
        }
        return R.error(E.NOT_FOUND);
    }

    /**
     * getByPaged
     *
     * @param page      页码
     * @param size      每页大小
     * @param sort      按某字段排序，如 id
     * @param direction 排序方向，asc 或者 desc
     * @return 分页数据
     */
    @Operation(summary = "分页查询")
    @GetMapping("/paged")
    public R<Page<T>> getByPaged(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "id") String sort,
            @RequestParam(required = false, defaultValue = "asc") String direction) {
        Sort.Direction dir = direction.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sortObj = Sort.by(dir, sort);
        Pageable pageable = PageRequest.of(page, size, sortObj);
        Page<T> result = repository.findAll(pageable);
        return R.ok(result);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<R<T>> handleException(Exception e) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(R.error(E.INTERNAL_SERVER_ERROR, e.getMessage()));
//    }
}
