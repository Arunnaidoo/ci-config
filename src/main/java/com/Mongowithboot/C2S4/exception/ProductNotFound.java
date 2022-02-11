package com.Mongowithboot.C2S4.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Product with specific ID is not found.")
public class ProductNotFound extends Exception{
}
