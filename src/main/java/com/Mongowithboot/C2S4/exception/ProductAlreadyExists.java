package com.Mongowithboot.C2S4.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "Product Details Already Exists.")
public class ProductAlreadyExists extends Exception{
}
