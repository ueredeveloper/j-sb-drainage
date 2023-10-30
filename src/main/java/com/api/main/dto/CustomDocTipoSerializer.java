
package com.api.main.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class CustomDocTipoSerializer extends JsonSerializer<Object> {
    @Override
    public void serialize(Object docTipo, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (docTipo instanceof DocumentoTipoDTO) {
            // If doc_tipo is an object, serialize it as an object with properties
            DocumentoTipoDTO docTipoDTO = (DocumentoTipoDTO) docTipo;
            jsonGenerator.writeStartObject();
            jsonGenerator.writeObjectField("dt_id", docTipoDTO.getDt_id());
            jsonGenerator.writeObjectField("dt_descricao", docTipoDTO.getDt_descricao());
            jsonGenerator.writeEndObject();
        } else if (docTipo instanceof Integer) {
            // If doc_tipo is an ID, serialize it as an integer
          jsonGenerator.writeObjectField("dt_id", docTipo);
        }
    }
}
