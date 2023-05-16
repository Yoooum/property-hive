package com.prprv.property.exception;

import com.prprv.property.common.response.E;

/**
 * @author Yoooum
 */
public class AppException extends RuntimeException {
    private final E e;
    public AppException(E e) {
        super(e.getMessage());
        this.e = e;
    }
    public E getE() {
        return e;
    }
}
