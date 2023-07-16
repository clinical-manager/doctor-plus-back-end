package br.com.doctorplus.gerenciador.model.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResponseUtil {
    private final MessageUtil messageUtil;
    public ResponseSucesso buildResponse(String mensagem, Long id) {
        ResponseSucesso sucesso = new ResponseSucesso(messageUtil.getMessage(mensagem, id), id);
        return sucesso;
    }

    public ResponseSucesso buildResponse(String mensagem) {
        ResponseSucesso sucesso = new ResponseSucesso(messageUtil.getMessage(mensagem), null);
        return sucesso;
    }
}
