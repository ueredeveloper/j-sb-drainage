package com.api.main.models;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;


/**
 * Esta classe implementa a interface `JsonDeserializer` para desserializar 
 * JSONs no modelo adequado, seja `InterferenciaModel` ou sua subclasse `SubterraneaModel`.
 * A desserialização condicional é necessária porque diferentes tipos de interferência
 * (por exemplo, `Subterrânea`) podem ter atributos distintos e, portanto, precisam ser
 * mapeados para classes específicas.
 *
 * O Gson normalmente desserializa objetos com base na estrutura da classe declarada.
 * No entanto, como `SubterraneaModel` é uma especialização de `InterferenciaModel`,
 * esta classe `InterferenciaTypeAdapter` verifica o tipo de interferência
 * antes de decidir qual classe usar para desserialização.
 */
public class InterferenciaTypeAdapter implements JsonDeserializer<InterferenciaModel> {
	
	/**
     * Método que realiza a desserialização de um JSON para um modelo de interferência.
     *
     * @param json O elemento JSON a ser desserializado.
     * @param typeOfT O tipo esperado de retorno, que pode ser `InterferenciaModel` ou uma de suas subclasses.
     * @param context O contexto de desserialização que permite a desserialização recursiva de outros elementos do JSON.
     * @return Um objeto do tipo `InterferenciaModel` ou `SubterraneaModel`, dependendo do tipo de interferência.
     * @throws JsonParseException Caso haja erros durante o processo de desserialização.
     */
	@Override
	public InterferenciaModel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
        // Converter o JSON recebido para um objeto JSON para facilitar a navegação pelos campos
		JsonObject jsonObject = json.getAsJsonObject();

		// Verificar se o tipo de interferência é Subterrânea
		JsonObject tipoInterferencia = jsonObject.getAsJsonObject("tipoInterferencia");
		String descricaoTipoInterferencia = tipoInterferencia.get("descricao").getAsString();

		if ("Subterrânea".equals(descricaoTipoInterferencia)) {
			// Desserializar como SubterraneaModel
			return context.deserialize(jsonObject, SubterraneaModel.class);
		} else {
			// Desserializar como InterferenciaModel
			return context.deserialize(jsonObject, InterferenciaModel.class);
		}
	}

}
