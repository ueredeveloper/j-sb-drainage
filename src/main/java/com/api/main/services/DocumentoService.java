package com.api.main.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.dto.DocumentoDTO;
import com.api.main.models.AnexoModel;
import com.api.main.models.DocumentoModel;
import com.api.main.models.DocumentoTipoModel;
import com.api.main.models.EnderecoModel;
import com.api.main.models.InterferenciaModel;
import com.api.main.models.ProcessoModel;
import com.api.main.models.UsuarioModel;
import com.api.main.repositories.AnexoRepository;
import com.api.main.repositories.DocumentoRepository;
import com.api.main.repositories.DocumentoTipoRepository;
import com.api.main.repositories.EnderecoRepository;
import com.api.main.repositories.InterferenciaRepository;
import com.api.main.repositories.ProcessoRepository;
import com.api.main.repositories.UsuarioRepository;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;


@Service
public class DocumentoService {

	@Autowired
	private DocumentoRepository documentoRepository;
	@Autowired
	private ProcessoRepository processoRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private DocumentoTipoRepository docTipoRepo;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private AnexoRepository anexoRepository;
	@Autowired
	private InterferenciaRepository interferenciaRepository;

	@Transactional
	public List<DocumentoModel> listByKeyword(String keyword) {
		List<DocumentoModel> response = readJsonStringAndConvert(keyword);
		return response;
	}

	public List<DocumentoModel> readJsonStringAndConvert(String keyword) {

		List<Object> result = documentoRepository.listByKeyword(keyword);
		List<DocumentoModel> response = new ArrayList<>();

		if (result == null) {
			System.out.println("No results found for the keyword: " + keyword);
			return response; // Return an empty list if no results
		}

		String json = result != null ? result.toString() : null;

		if (json != null) {

			// Since the structure is a list of objects containing 'endereco', extract them
			List<Map<String, DocumentoModel>> tempList = new Gson().fromJson(json,
					new TypeToken<List<Map<String, DocumentoModel>>>() {
					}.getType());

			// Iterate over the list and extract 'endereco' object from each map
			for (Map<String, DocumentoModel> map : tempList) {
				DocumentoModel documento = map.get("documento");
				if (documento != null) {
					response.add(documento);
				}
			}
		}

		return response;

	}

	public List<DocumentoModel> readJsonStringAndConvert(Long id) {
		// Fetch data from repository
		List<Object> result = documentoRepository.listByUserId(id);
		List<DocumentoModel> response = new ArrayList<>();

		if (result == null || result.isEmpty()) {
			System.out.println("No results found for the id: " + id);
			return response; // Return an empty list if no results
		}

		// Convert result to JSON string
		String json = result.toString();

		try {
			// Deserialize JSON array into a list of DocumentoModel objects
			response = new Gson().fromJson(json, new TypeToken<List<DocumentoModel>>() {
			}.getType());
		} catch (JsonSyntaxException e) {
			System.err.println("Error parsing JSON: " + e.getMessage());
		}

		return response;
	}

	@Transactional
	public Long deleteDocUserRelation(Long documentoId, Long usuarioId) {
		// Attempt to delete the user-document relation
		Long docId = documentoRepository.deleteDocUseRelation(documentoId, usuarioId);

		if (docId != null) {
			return docId;
		} else {
			return null;
		}
	}

	@Transactional
	public DocumentoModel deleteById(Long id) {
		DocumentoModel deletedDocument = documentoRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Não foi encontrado documento com o id: " + id));
		documentoRepository.deleteById(id);
		return createSafeResponse(deletedDocument);
	}

	@Transactional
	public Optional<DocumentoModel> findById(Long id) {
		return documentoRepository.findById(id);
	}

	@Transactional
	public List<DocumentoModel> listByUserId(Long id) {
		List<DocumentoModel> response = readJsonStringAndConvert(id);
		return response;
	}

	@Transactional
	public DocumentoModel update(Long id, DocumentoModel toUpdateObject) {

		DocumentoModel originalResponse = documentoRepository.findById(id).map((DocumentoModel record) -> {
			record.setNumero(toUpdateObject.getNumero());
			record.setNumeroSei(toUpdateObject.getNumeroSei());

			// Verifies and saves DocumentoTipoModel (must not be null)
			if (toUpdateObject.getTipoDocumento() != null && toUpdateObject.getTipoDocumento().getId() != null) {
				Optional<DocumentoTipoModel> documentoTipo = docTipoRepo
						.findById(toUpdateObject.getTipoDocumento().getId());
				documentoTipo.ifPresent(record::setTipoDocumento);
			} else {
				throw new IllegalArgumentException("É requerido o Tipo de Documento.");
			}

			return documentoRepository.save(record);

		}).orElse(null);

		updateProcesso(toUpdateObject, originalResponse);

		updateEndereco(toUpdateObject, originalResponse);

		saveOrUpdateUsuarios(toUpdateObject, originalResponse);

		if (originalResponse == null) {
			throw new NoSuchElementException("Não foi encontrado documento com o id: " + id);
		}

		return createSafeResponse(documentoRepository.save(originalResponse));

	}

	public DocumentoModel createSafeResponse(DocumentoModel originalResponse) {

		DocumentoModel safeResponse = new DocumentoModel();

		safeResponse.setId(originalResponse.getId());
		safeResponse.setNumero(originalResponse.getNumero());
		safeResponse.setNumeroSei(originalResponse.getNumeroSei());

		// Safe handling of endereco (address), checking for null before accessing its
		// fields
		if (originalResponse.getEndereco() != null) {
			safeResponse.setEndereco(new EnderecoModel(
					originalResponse.getEndereco().getId(),
					originalResponse.getEndereco().getLogradouro(),
					originalResponse.getEndereco().getCidade(),
					originalResponse.getEndereco().getBairro(),
					originalResponse.getEndereco().getCep(),
					originalResponse.getEndereco().getEstado()));
		} else {
			safeResponse.setEndereco(null); // You can set this to null or handle as needed
		}

		// Safe handling of processo (process), checking for null before accessing its
		// fields
		if (originalResponse.getProcesso() != null) {
			ProcessoModel processo = originalResponse.getProcesso();

			// Safe handling of anexo (attachment) inside processo, checking for null before
			// accessing its fields
			AnexoModel anexo = null;
			if (processo.getAnexo() != null) {
				anexo = new AnexoModel(processo.getAnexo().getId(), processo.getAnexo().getNumero());
			}

			safeResponse.setProcesso(new ProcessoModel(processo.getId(), processo.getNumero(), anexo // Attach the anexo
																										// if it exists,
																										// otherwise it
																										// will be null
			));
		} else {
			safeResponse.setProcesso(null); // You can set this to null or handle as needed
		}

		// Safe handling of tipoDocumento (document type), checking for null before
		// accessing its fields
		if (originalResponse.getTipoDocumento() != null) {
			safeResponse.setTipoDocumento(new DocumentoTipoModel(originalResponse.getTipoDocumento().getId(),
					originalResponse.getTipoDocumento().getDescricao()));
		} else {
			safeResponse.setTipoDocumento(null); // You can set this to null or handle as needed
		}

		return safeResponse;
	}

	@Transactional
	public DocumentoModel save(DocumentoDTO objDTO, DocumentoModel objMod) {

		// Processa o EnderecoModel antes de salvar o DocumentoModel
		if (objMod.getEndereco() != null) {
			EnderecoModel savedEndereco = saveEndereco(objMod.getEndereco());
			objMod.setEndereco(savedEndereco);

			// Salva as Interferencias associadas ao EnderecoModel
			if (savedEndereco.getInterferencias() != null) {
				for (InterferenciaModel interferencia : savedEndereco.getInterferencias()) {
					interferencia.setEndereco(savedEndereco);
					interferenciaRepository.save(interferencia);
				}
			}
		}

		// Processa o ProcessoModel se presente
		if (objMod.getProcesso() != null) {
			ProcessoModel processo = objMod.getProcesso();

			if (processo.getId() != null) {
				// Verifica se o processo já existe
				ProcessoModel existingProcesso = processoRepository.findById(processo.getId()).orElseThrow(
						() -> new RuntimeException("Processo não encontrado para o ID: " + processo.getId()));

				// Relaciona o processo existente ao DocumentoModel
				objMod.setProcesso(existingProcesso);
			} else {
				// Salva um novo processo
				ProcessoModel savedProcesso = saveProcesso(processo);
				objMod.setProcesso(savedProcesso);
			}
		}

		// Salva o DocumentoModel com o Endereco e Processo atualizados
		DocumentoModel newObject = documentoRepository.save(objMod);

		Set<UsuarioModel> processedUsuarios = new HashSet<>();

		if (objMod.getUsuarios() != null) {

			for (UsuarioModel usuario : objMod.getUsuarios()) {
				// Verifica se o usuário já existe no banco de dados
				UsuarioModel existingUsuario = usuarioRepository.findById(usuario.getId()).orElse(null);

				if (existingUsuario != null) {
					// Usuário já existe, adiciona à relação com o documento
					processedUsuarios.add(existingUsuario);
					existingUsuario.getDocumentos().add(objMod);
				} else {
					// Usuário não existe, salva e relaciona ao documento
					UsuarioModel savedUsuario = usuarioRepository.save(usuario);
					processedUsuarios.add(savedUsuario);
					savedUsuario.getDocumentos().add(objMod);
				}

				// Log para depuração
				System.out.println("Usuário processado: " + usuario.getNome() + ", CPF/CNPJ: " + usuario.getCpfCnpj());
			}

		}

		newObject.setUsuarios(processedUsuarios);

		// Salva novamente o DocumentoModel atualizado
		DocumentoModel originalResponse = documentoRepository.save(newObject);
		return createSafeResponse(originalResponse);
	}

	private EnderecoModel saveEndereco(EnderecoModel endereco) {

		if (endereco.getId() == null) {
			// Se o endereço não tem ID, ele ainda não foi salvo, então salvamos
			return enderecoRepository.save(endereco);
		} else {
			// Se já tem ID, verificamos se o endereço já está salvo e retornamos o
			// existente
			return enderecoRepository.findById(endereco.getId()).orElseGet(() -> enderecoRepository.save(endereco));
		}
	}

	@Transactional
	private void updateEndereco(DocumentoModel updateDocumento, DocumentoModel originalResponse) {
		if (updateDocumento.getEndereco() != null) {
			EnderecoModel enderecoToUpdate = updateDocumento.getEndereco();

			// Check if EnderecoModel has an ID
			if (enderecoToUpdate.getId() != null) {
				Optional<EnderecoModel> existingEndereco = enderecoRepository.findById(enderecoToUpdate.getId());
				if (existingEndereco.isPresent()) {
					EnderecoModel existing = existingEndereco.get();

					// Update attributes of the existing EnderecoModel
					existing.setLogradouro(enderecoToUpdate.getLogradouro());
					existing.setBairro(enderecoToUpdate.getBairro());
					existing.setCidade(enderecoToUpdate.getCidade());
					existing.setCep(enderecoToUpdate.getCep());
					existing.setEstado(enderecoToUpdate.getEstado());

					// Save the updated EnderecoModel and set it to the original response
					EnderecoModel updatedEndereco = enderecoRepository.save(existing);
					originalResponse.setEndereco(updatedEndereco);
				} else {
					throw new IllegalArgumentException("Endereço não encontrado.");
				}
			} else {
				// Save a new EnderecoModel if the ID is null
				EnderecoModel newEndereco = enderecoRepository.save(enderecoToUpdate);
				originalResponse.setEndereco(newEndereco);
			}
		}
	}

	@Transactional
	private void updateProcesso(DocumentoModel updateDocumento, DocumentoModel originalResponse) {
		if (updateDocumento.getProcesso() != null) {
			ProcessoModel processo = updateDocumento.getProcesso();

			// Check if ProcessoModel has an ID
			if (processo.getId() != null) {
				Optional<ProcessoModel> existingProcesso = processoRepository.findById(processo.getId());
				if (existingProcesso.isPresent()) {
					ProcessoModel proc = existingProcesso.get();

					// Check if ProcessoModel has an AnexoModel
					if (processo.getAnexo() != null) {
						AnexoModel anexo = processo.getAnexo();
						if (anexo.getId() != null) {
							Optional<AnexoModel> existingAnexo = anexoRepository.findById(anexo.getId());
							if (existingAnexo.isPresent()) {
								proc.setAnexo(existingAnexo.get());
							} else {
								throw new IllegalArgumentException("Anexo não encontrado.");
							}
						} else {
							// Save new AnexoModel if ID is null
							AnexoModel newAnexo = anexoRepository.save(anexo);
							proc.setAnexo(newAnexo);
						}
					}
					originalResponse.setProcesso(proc);
				} else {
					throw new IllegalArgumentException("Processo não encontrado.");
				}
			} else {
				// Save new ProcessoModel if ID is null
				ProcessoModel newProcesso = processoRepository.save(processo);

				if (processo.getAnexo() != null) {
					AnexoModel anexo = processo.getAnexo();
					if (anexo.getId() != null) {
						Optional<AnexoModel> existingAnexo = anexoRepository.findById(anexo.getId());
						existingAnexo.ifPresent(newProcesso::setAnexo);
					} else {
						AnexoModel newAnexo = anexoRepository.save(anexo);
						newProcesso.setAnexo(newAnexo);
					}
				}
				originalResponse.setProcesso(newProcesso);
			}
		}
	}

	private ProcessoModel saveProcesso(ProcessoModel processo) {
		if (processo.getAnexo() != null && processo.getAnexo().getId() == null) {
			AnexoModel anexo = processo.getAnexo();
			anexo = anexoRepository.save(anexo);
			processo.setAnexo(anexo);
		}
		return processoRepository.save(processo);
	}

	private void saveOrUpdateUsuarios(DocumentoModel toUpdateObject, DocumentoModel originalResponse) {
		Set<UsuarioModel> processedUsuarios = new HashSet<>();

		if (toUpdateObject.getUsuarios() != null) {
			for (UsuarioModel usuario : toUpdateObject.getUsuarios()) {
				// Check if the user exists in the database
				UsuarioModel existingUsuario = usuarioRepository.findById(usuario.getId()).orElse(null);

				if (existingUsuario != null) {
					// Check if the relationship between the user and document already exists
					boolean relationshipExists = usuarioRepository.existsRelationship(existingUsuario.getId(),
							toUpdateObject.getId());

					if (!relationshipExists) {
						existingUsuario.getDocumentos().add(toUpdateObject);
					}
					processedUsuarios.add(existingUsuario);
				} else {
					// Save the user if it doesn't exist and relate it to the document
					UsuarioModel savedUsuario = usuarioRepository.save(usuario);
					savedUsuario.getDocumentos().add(toUpdateObject);
					processedUsuarios.add(savedUsuario);
				}

				// Log for debugging
				System.out.println("Processed user: " + usuario.getNome() + ", CPF/CNPJ: " + usuario.getCpfCnpj());
			}
		}

		originalResponse.setUsuarios(processedUsuarios);
	}

}