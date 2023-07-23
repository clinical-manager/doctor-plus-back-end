package br.com.doctorplus.gerenciador.model.utils;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

@Component
public class MapperUtil {

    public String trimString(String value) {
        return Strings.isNotBlank(value) ? value.trim() : null;
    }
}
