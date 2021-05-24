package com.example.crawlprice.exceptions;

import com.example.crawlprice.factory.resfact.ResponseStatusEnum;

public class CustomBusinessLogicException  extends ApplicationException{

    private static final long serialVersionUID = -1605187590106478545L;

    public CustomBusinessLogicException(ResponseStatusEnum responseStatusEnum) {
        super(responseStatusEnum);
    }
}
