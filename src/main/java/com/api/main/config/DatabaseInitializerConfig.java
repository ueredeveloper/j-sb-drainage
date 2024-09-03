package com.api.main.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import com.api.main.models.AnexoModel;
import com.api.main.models.DocumentoTipoModel;
import com.api.main.models.EnderecoModel;
import com.api.main.models.EstadoModel;
import com.api.main.models.FormaCaptacaoModel;
import com.api.main.models.LocalCaptacaoModel;
import com.api.main.models.SituacaoProcessoModel;
import com.api.main.models.SubtipoOutorgaModel;
import com.api.main.models.TemplateModel;
import com.api.main.models.TipoAtoModel;
import com.api.main.models.TipoInterferenciaModel;
import com.api.main.models.TipoOutorgaModel;
import com.api.main.models.TipoPocoModel;
import com.api.main.repositories.AnexoRepository;
import com.api.main.repositories.DocumentoTipoRepository;
import com.api.main.repositories.EnderecoRepository;
import com.api.main.repositories.EstadoRepository;
import com.api.main.repositories.FormaCaptacaoRepository;
import com.api.main.repositories.LocalCaptacaoRepository;
import com.api.main.repositories.ProcessoRepository;
import com.api.main.repositories.SituacaoProcessoRepository;
import com.api.main.repositories.SubtipoOutorgaRepository;
import com.api.main.repositories.TemplateRepository;
import com.api.main.repositories.TipoAtoRepository;
import com.api.main.repositories.TipoInterferenciaRepository;
import com.api.main.repositories.TipoOutorgaRepository;
import com.api.main.repositories.TipoPocoRepository;
import com.google.gson.Gson;

@Configuration
@RestController
public class DatabaseInitializerConfig {

	@Bean
	public CommandLineRunner insertDocumentoTipo(DocumentoTipoRepository r) {

		return (args) -> {

			r.save(new DocumentoTipoModel("Requerimento"));
			r.save(new DocumentoTipoModel("Ofício"));
			r.save(new DocumentoTipoModel("Despacho"));

		};
	}

	@Bean
	public CommandLineRunner InsertEstado(EstadoRepository r) {

		return (args) -> {

			r.save(new EstadoModel("DF"));
			r.save(new EstadoModel("SP"));
			r.save(new EstadoModel("BA"));

		};
	}

	@Bean
	public CommandLineRunner insertTipoInterferencia(TipoInterferenciaRepository r) {

		return (args) -> {

			r.save(new TipoInterferenciaModel("Superficial"));
			r.save(new TipoInterferenciaModel("Subterrânea"));
			r.save(new TipoInterferenciaModel("Lançamento de Águas Pluviais"));
			r.save(new TipoInterferenciaModel("Canal"));
			r.save(new TipoInterferenciaModel("Caminhão Pipa"));
			r.save(new TipoInterferenciaModel("Lançamento de Efluentes"));
			r.save(new TipoInterferenciaModel("Barragem"));

		};
	}

	@Bean
	public CommandLineRunner insetHTMLTemplate(TemplateRepository r) {

		String jsonString = "{\"descricao\":\"Parecer de Outorga\",\"html\":\"<html dir='ltr'><head><title></title></head><body contenteditable='true'><table style='margin-left: auto; margin-right: auto; width: 800px; border: 1px solid initial;'><tbody><tr><td><div style='text-align: center;'>&nbsp;</div><p style='margin-left: 400px;'>Emite outorga prévia para reservar o direito de uso de água subterrânea a&nbsp;<us_nome_tag></us_nome_tag>, para fins de <finalidades_tag></finalidades_tag> .</p><p>&nbsp;</p><div align='justify'><p>O SUPERINTENDENTE DE RECURSOS HÍDRICOS DA AGÊNCIA REGULADORA DE ÁGUAS, ENERGIA E SANEAMENTO BÁSICO DO DISTRITO FEDERAL – ADASA, no uso de suas atribuições regimentais e com base na competência que lhe foi delegada pela Diretoria Colegiada, nos termos da Resolução nº 02, de 25 de janeiro de 2019, c/c Portaria nº 49, de 02 de maio de 2019 e com base no art. 12 da Lei nº 2.725, de 13 de junho de 2001, e inciso VII do art. 23 da Lei nº 4.285, de 26 de dezembro de 2008, tendo em vista o que consta do Processo SEI N.º <b><doc_proc_principal_tag></doc_proc_principal_tag></b>, resolve:</p><p>Art. 1º Emitir outorga prévia para reservar o direito de uso de água subterrânea a&nbsp;<b><us_nome_tag></us_nome_tag></b>, <b>CPF/CNPJ n.º <us_cpfcnpj_tag></us_cpfcnpj_tag></b>, mediante a perfuração de 01 (um) poço <inter_tipo_poco_tag></inter_tipo_poco_tag>, para fins&nbsp; de <finalidades_tag></finalidades_tag>, localizado na <end_log_tag></end_log_tag> , <end_ra_tag></end_ra_tag> - Distrito Federal, tendo a seguinte característica:</p></div><p>&nbsp;</p><div align='justify'><inter_dados_basicos_tag></inter_dados_basicos_tag></div><p style='text-align: center;'>&nbsp;</p><p>Art. 2º A reserva de disponibilidade hídrica para cada um dos poços tubulares mencionados no art. 1º é a seguinte:</p><p>&nbsp;</p><p>I – Tabela dos limites outorgados.</p><p>&nbsp;</p><tabela_limites_outorgados_tag></tabela_limites_outorgados_tag><p>&nbsp;</p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>Art. 3ºAo término da perfuração do poço e previamente à captação definitiva de água, ooutorgado deverá requerer à Adasa a respectiva outorga de direito de uso deágua subterrânea, em formulário próprio, quando apresentará:<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>a) ensaiode bombeamento (contendo planilhas, gráficos e relatórios);<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>b) perfilconstrutivo litológico do poço; e<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>c)registro fotográfico que comprove o cumprimento do disposto no art. 7º, incisosII, IV, V, VI e VII, desta outorga prévia.<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>&nbsp;<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>Art. 4º Estaoutorga prévia não substitui a outorga de direito de uso de recursos hídricos,necessária para operação do poço e captação de água.<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>&nbsp;<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>Art. 5º Aoutorga prévia terá validade de <b>03 (três) anos</b>, a contar da data depublicação do extrato no Diário Oficial do Distrito Federal, podendo ser renovadamediante solicitação do outorgado, ou prorrogada, observada a legislaçãovigente.<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>§ 1º Opedido de renovação desta outorga prévia poderá ser requerido à Adasa comantecedência mínima de 90 (noventa) dias do término do prazo de vigência fixadono <i>caput</i>.<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>§ 2º Naanálise do pedido para prorrogação da presente outorga serão observadas asnormas, os critérios e as prioridades de usos vigentes à época da renovação.<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>§ 3º Aoutorga prévia será automaticamente prorrogada até deliberação da Adasa sobre oreferido pedido de renovação, se cumpridos os termos previstos no §1º.<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>&nbsp;<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>Art. 6º Aoutorga prévia poderá ser suspensa, parcial ou totalmente, revogada ou revista,por prazo determinado, nos seguintes casos, previstos nos artigos 29 e 30 daResolução nº 350, de 23 de junho de 2006:<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>I –quando o outorgado descumprir quaisquer condições e termos fixados no presenteato de outorga;<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>II –&nbsp;diante da necessidade de:<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>a) águapara atender situações de calamidade, inclusive decorrentes de condiçõesclimáticas adversas;<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>b)prevenir ou reverter grave degradação ambiental; e<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>c)atender usos prioritários, de interesse coletivo, para os quais não se disponhade fontes alternativas.<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>III –racionamento de recursos hídricos, conforme regulamento específico; e<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>IV – indeferimentoou cassação da licença ambiental, se for o caso.<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>§ 1º Asuspensão total ou parcial da outorga prévia não implica em indenização aqualquer título.<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>§ 2º Aoutorga prévia para abastecimento humano será revogada ou modificada quandoocorrer a ligação da rede de abastecimento de água pela concessionária desaneamento básico.<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>&nbsp;<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>Art. 7ºConstituem obrigações do outorgado:<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>I -observar os limites estabelecidos no art. 2º deste ato de outorga;<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>II -proteger a porção do poço perfurado executada sobre material inconsolidado ecom possibilidade de desmoronamento, para prevenção de contaminação dosaquíferos por meio de percolação de águas superficiais indesejáveis;<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>III -construir uma laje de concreto envolvendo o tubo de revestimento, comdeclividade do centro para a borda, com espessura mínima de 10 cm (dezcentímetros) e área não inferior a 1 m² (um metro quadrado);<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>IV -manter a parte externa do poço com 30 cm (trinta centímetros), no mínimo, acimada laje de concreto, a qual deverá ter proteção de alvenaria e coberturaremovível;<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>V -&nbsp;manter área de proteção com raio de, pelo menos, 5 m (cinco metros), a partirdos limites do poço, que deverá ser cercado e mantido limpo;<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>VI -desativar e tamponar as fossas posicionadas no raio de 30 m (trinta metros) dopoço, a fim de evitar a contaminação do aquífero;<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>VII -instalar hidrômetro na saída do poço, num prazo máximo de 90 (noventa) dias apartir da perfuração ou da publicação do extrato de outorga;<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>VIII -após a instalação do dispositivo de medição dos volumes extraídos, o outorgadodeverá enviar à Adasa o resultado de sua leitura, bem como a respectivaplanilha com os volumes mensais extraídos;<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>IX -responsabilizar-se pelo controle e vigilância da qualidade da água e seu padrãode potabilidade, conforme estabelece a Portaria do Ministério da Saúde nº2.914, de 12 de dezembro de 2011 e obter junto à Diretoria de VigilânciaAmbiental da Secretaria de Saúde do Distrito Federal as autorizações cabíveis;e<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>X -construir e manter sistema de adução, reservação e distribuição, completamenteindependente do sistema de abastecimento da concessionária de água, caso o usode água de poço ocorra em área atendida pela rede de abastecimento de água.<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>Parágrafoúnico. Em situações especiais, a Adasa poderá reduzir o tamanho do raio de quetrata o inciso V deste artigo, não podendo ser o raio inferior a 1 m (ummetro).<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>&nbsp;<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>Art. 8ºFica o outorgado sujeito à fiscalização da Adasa, por intermédio de seusagentes ou prepostos indicados, devendo franquear-lhes o acesso aoempreendimento e à documentação respectiva, como projetos, contratos,relatórios, registros e quaisquer outros documentos referentes à presenteoutorga.<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>&nbsp;<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>Art. 9ºFica o outorgado sujeito às penalidades previstas na legislação em vigor emcaso de descumprimento das disposições legais e regulamentares decorrentes dareserva do direito de uso da água subterrânea e pelo não atendimento dassolicitações, recomendações e determinações da fiscalização.<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>&nbsp;<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>Art. 10.A transferência do direito previsto neste ato, bem como qualquer alteração nascaracterísticas do empreendimento sujeito à esta outorga prévia, deverá serprecedida de anuência formal da Adasa.<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>&nbsp;<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>Art. 11.A presente outorga não dispensa ou substitui a obtenção, pelo outorgado, decertidões, alvarás ou licenças de qualquer natureza, exigidos pela legislaçãovigente.<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>Parágrafoúnico. O outorgado deverá respeitar a legislação ambiental e articular-se com oórgão competente, com vistas à obtenção de licenças ambientais, quando couber,cumprir as exigências nelas contidas e responder pelas consequências do descumprimentodas leis, regulamentos e licenças.<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>&nbsp;<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>Art. 12.O outorgado responderá civil, penal e administrativamente, por danos causados àvida, à saúde, ao meio ambiente, bem como a terceiros, pelo uso inadequado quevier a fazer da presente outorga, na forma da Lei.<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>&nbsp;<o:p></o:p></span></p><p class='MsoNormal' style='margin-bottom:0cm;text-align:justify;line-height:normal'><span style='font-size:12.0pt;font-family:&quot;Times New Roman&quot;,serif;mso-fareast-font-family:&quot;Times New Roman&quot;;mso-fareast-language:PT-BR'>Art. 13.Esta outorga prévia entra em vigor na data de sua publicação.<o:p></o:p></span></p><p>&nbsp;</p><p style='text-align: center;'><strong>GUSTAVO ANTONIO CARNEIRO</strong></p><p style='text-align: center;'>Superintendente de Recursos Hídricos</p><div align='justify'>&nbsp;</div></td></tr></tbody></table><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p></body></html>\"}";

		Gson gson = new Gson();
		TemplateModel htmlDocument = gson.fromJson(jsonString, TemplateModel.class);

		return (args) -> {

			r.save(htmlDocument);

		};
	}

	@Bean
	public CommandLineRunner insertAnexo(AnexoRepository procRepo) {

		return (args) -> {

			procRepo.save(new AnexoModel("197.123.456/2013"));
			procRepo.save(new AnexoModel("197.456.789/2015"));

		};
	};

	@Bean
	public CommandLineRunner isertProcesso(ProcessoRepository repo) {

		return (args) -> {

			// repo.save(new ProcessoModel("123/2015", new AnexoModel("197.444.444/2013")));
			// repo.save(new ProcessoModel("890/2088", new AnexoModel("197.444.444/2013")));

		};
	}

	@Bean
	public CommandLineRunner insertEndereco(EnderecoRepository r) {
		return (args) -> {

			r.save(new EnderecoModel("Rua Novaes Terceiro, Casa 12"));
			r.save(new EnderecoModel("Avenida Principal, Bloco A"));
			r.save(new EnderecoModel("Rua das Flores, Apartamento 5"));

		};
	}

	@Bean
	public CommandLineRunner insetTipoOutorga(TipoOutorgaRepository r) {

		/*
		 * Tipo_Outorga (1, N'Outorga') (2, N'Outorga Prévia') (3, N'Registro')
		 */

		return (args) -> {

			r.save(new TipoOutorgaModel("Outorga"));
			r.save(new TipoOutorgaModel("Outorga Prévia"));
			r.save(new TipoOutorgaModel("Registro"));

		};

	}

	@Bean
	public CommandLineRunner insertSubtipoOutorga(SubtipoOutorgaRepository r) {

		return (args) -> {

			r.save(new SubtipoOutorgaModel("Renovação"));
			r.save(new SubtipoOutorgaModel("Modificação"));
			r.save(new SubtipoOutorgaModel("Transferência"));
			r.save(new SubtipoOutorgaModel("Transferência"));
			r.save(new SubtipoOutorgaModel("Suspensão/Revogação"));
			r.save(new SubtipoOutorgaModel(""));

		};

	}

	@Bean
	public CommandLineRunner insertSituacaoProcesso(SituacaoProcessoRepository r) {
		return (args) -> {
			r.save(new SituacaoProcessoModel("Arquivado"));
			r.save(new SituacaoProcessoModel("Em Análise"));
			r.save(new SituacaoProcessoModel("Outorgado"));
			r.save(new SituacaoProcessoModel("Vencido"));
			r.save(new SituacaoProcessoModel("Arquivado (CNRH 16)"));
			r.save(new SituacaoProcessoModel("Pendência"));
			r.save(new SituacaoProcessoModel("Indeferido"));
			r.save(new SituacaoProcessoModel("Revogado"));

		};
	}

	@Bean
	CommandLineRunner insertTipoAto(TipoAtoRepository r) {
		return (args) -> {
			r.save(new TipoAtoModel("Despacho"));
			r.save(new TipoAtoModel("Portaria"));
			r.save(new TipoAtoModel("Despacho"));
			r.save(new TipoAtoModel("Registro"));
			r.save(new TipoAtoModel("Resolução"));
			r.save(new TipoAtoModel("Resolução ANA"));
			r.save(new TipoAtoModel("Portaria DNAEE"));
		};
	}

	@Bean
	CommandLineRunner insertFormaCaptacao(FormaCaptacaoRepository r) {
		return (args) -> {
			r.save(new FormaCaptacaoModel("Bombeamento"));
			r.save(new FormaCaptacaoModel("Gravidade"));
		};
	}

	@Bean
	CommandLineRunner insertLocalCaptacao(LocalCaptacaoRepository r) {
		return (args) -> {
			r.save(new LocalCaptacaoModel("Nascente"));
			r.save(new LocalCaptacaoModel("Rio"));
			r.save(new LocalCaptacaoModel("Reservatório"));
			r.save(new LocalCaptacaoModel("Canal"));
			r.save(new LocalCaptacaoModel(" Lago Natural"));
		};
	}

	@Bean
	CommandLineRunner insertTipoPoco(TipoPocoRepository r) {
		return (args) -> {
			r.save(new TipoPocoModel("Manual"));
			r.save(new TipoPocoModel("Tubular Raso"));
			r.save(new TipoPocoModel("Tubular Profundo"));

		};
	}

}