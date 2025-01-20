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

			//System.out.println("list doc by key " + json);
			// Since the structure is a list of objects containing 'endereco', extract them
			List<Map<String, DocumentoModel>> tempList = new Gson().fromJson(json,
					new TypeToken<List<Map<String, DocumentoModel>>>() {
					}.getType());

			// Iterate over the list and extract 'endereco' object from each map
			for (Map<String, DocumentoModel> map : tempList) {
				DocumentoModel endereco = map.get("documento");
				if (endereco != null) {
					response.add(endereco);
				}
			}
		}

		return response;

	}

	@Transactional
	public void delete() {
		documentoRepository.deleteAll();
	}

	@Transactional
	public DocumentoModel deleteById(Long id) {
		DocumentoModel deletedDocument = documentoRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Não foi encontrado documento com o id: " + id));
		documentoRepository.deleteById(id);
		return deletedDocument;
	}

	@Transactional
	public Optional<DocumentoModel> findById(Long id) {
		return documentoRepository.findById(id);
	}

	@Transactional
	public DocumentoModel update(Long id, DocumentoModel updateDocumento) {

		DocumentoModel originalResponse = documentoRepository.findById(id).map((DocumentoModel record) -> {
			record.setNumero(updateDocumento.getNumero());
			record.setNumeroSei(updateDocumento.getNumeroSei());

			// Verifies and saves DocumentoTipoModel (must not be null)
			if (updateDocumento.getTipoDocumento() != null && updateDocumento.getTipoDocumento().getId() != null) {
				Optional<DocumentoTipoModel> documentoTipo = docTipoRepo
						.findById(updateDocumento.getTipoDocumento().getId());
				documentoTipo.ifPresent(record::setTipoDocumento);
			} else {
				throw new IllegalArgumentException("É requerido o Tipo de Documento.");
			}

			return documentoRepository.save(record);

		}).orElse(null);

		updateProcesso(updateDocumento, originalResponse);

		updateEndereco(updateDocumento, originalResponse);

		updateUsuarios(updateDocumento, originalResponse);

		if (originalResponse == null) {
			throw new NoSuchElementException("Não foi encontrado documento com o id: " + id);
		}

		return createSafeResponse(originalResponse);

	}

	public DocumentoModel createSafeResponse(DocumentoModel originalResponse) {

		DocumentoModel safeResponse = new DocumentoModel();

		safeResponse.setId(originalResponse.getId());
		safeResponse.setNumero(originalResponse.getNumero());
		safeResponse.setNumeroSei(originalResponse.getNumeroSei());

		// Não permite referências cíclicas que geram loop na criaçaõ do json
		safeResponse.setEndereco(new EnderecoModel(originalResponse.getEndereco().getId(),
				originalResponse.getEndereco().getLogradouro()));

		// Não permite referências cíclicas que geram loop na criaçaõ do json
		safeResponse.setProcesso(
				// Se o anexo for diferente de nulo preencha com anexo (id, descricao) ou
				// preenche com nulo
				new ProcessoModel(originalResponse.getProcesso().getId(), originalResponse.getProcesso().getNumero(),
						originalResponse.getProcesso().getAnexo() != null
								? new AnexoModel(originalResponse.getProcesso().getAnexo().getId(),
										originalResponse.getProcesso().getAnexo().getNumero())
								: null));

		safeResponse.setTipoDocumento(new DocumentoTipoModel(originalResponse.getTipoDocumento().getId(),
				originalResponse.getTipoDocumento().getDescricao()));

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
	            ProcessoModel existingProcesso = processoRepository.findById(processo.getId())
	                .orElseThrow(() -> new RuntimeException("Processo não encontrado para o ID: " + processo.getId()));
	            
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
	            UsuarioModel existingUsuario = usuarioRepository.findById(usuario.getId())
	                .orElse(null);

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
					existing.setCidade(enderecoToUpdate.getCidade());
					existing.setCep(enderecoToUpdate.getCep());

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

	private Set<UsuarioModel> saveUsuarios(Set<UsuarioModel> usuarios, DocumentoModel newObject) {

	    Set<UsuarioModel> response = new HashSet<>();

	    for (UsuarioModel usuario : usuarios) {
	        if (usuario.getId() == null) {
	            // Usuário novo, salva no banco de dados
	            usuario = usuarioRepository.save(usuario);
	        }

	        // Adiciona o documento ao conjunto de documentos do usuário
	        usuario.getDocumentos().add(newObject);

	        // Atualiza o usuário no banco de dados (para persistir a associação)
	        usuario = usuarioRepository.save(usuario);

	        System.out.println("Usuário salvo - ID: " + usuario.getId());
	        response.add(usuario);
	    }
	    return response;
	}


	private UsuarioModel saveOrUpdateUsuario(UsuarioModel usuario, DocumentoModel documento) {
		Set<DocumentoModel> docsToRelation = new HashSet<>();
		docsToRelation.add(documento);
		usuario.setDocumentos(docsToRelation);
		return usuarioRepository.save(usuario);
	}

	private void updateUsuarios(DocumentoModel updateDocumento, DocumentoModel originalResponse) {
		Set<UsuarioModel> usuarios = updateDocumento.getUsuarios();
		if (usuarios != null && !usuarios.isEmpty()) {
			originalResponse.getUsuarios().clear();
			usuarios.forEach(
					usuario -> originalResponse.getUsuarios().add(saveOrUpdateUsuario(usuario, originalResponse)));
		}
	}

}