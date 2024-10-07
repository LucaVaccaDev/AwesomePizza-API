package com.portal.awesome_pizza.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.portal.awesome_pizza.Utility.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@Builder
public class ResponsePojo<T> {

    public String messaggio;
    public String status;
    public T data;

    public ResponsePojo() {
        //costruttore vuoto
    }

    public ResponsePojo error(String errorMsg, T data) {
        return ResponsePojo.builder().messaggio(errorMsg).status(Constants.KO).data(data).build();
    }

    public ResponsePojo success(String message, T data) {
        return ResponsePojo.builder().messaggio(message).status(Constants.OK).data(data).build();
    }

}
