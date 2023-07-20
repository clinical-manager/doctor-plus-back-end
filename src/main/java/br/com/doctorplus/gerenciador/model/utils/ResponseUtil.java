package br.com.doctorplus.gerenciador.model.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResponseUtil {
    private final MessageUtil messageUtil;
    public ResponseSucesso buildResponse(String mensagem, Long id) {
        return new ResponseSucesso(messageUtil.getMessage(mensagem, id), id);
    }

    public ResponseSucesso buildResponse(String mensagem) {
        return new ResponseSucesso(messageUtil.getMessage(mensagem), null);
    }
}
