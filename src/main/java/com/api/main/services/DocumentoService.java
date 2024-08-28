package com.api.main.services;

import java.util.HashSet;
import java.util.List;
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
		return documentoRepository.listByKeyword(keyword);
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
		DocumentoModel responseDocumento = documentoRepository.findById(id).map((DocumentoModel record) -> {
			record.setNumero(updateDocumento.getNumero());
			record.setNumeroSei(updateDocumento.getNumeroSei());

			// Verifies and saves DocumentoTipoModel (must not be null)
			if (updateDocumento.getTipo() != null && updateDocumento.getTipo().getId() != null) {
				Optional<DocumentoTipoModel> documentoTipo = docTipoRepo.findById(updateDocumento.getTipo().getId());
				documentoTipo.ifPresent(record::setTipo);
			} else {
				throw new IllegalArgumentException("É requerido o Tipo de Documento.");
			}

			// Saves or updates ProcessoModel (can be null or have an existing ID)
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
									// Handle if AnexoModel is not found
									throw new IllegalArgumentException("Anexo não encontrado.");
								}
							} else {
								// Save new AnexoModel if ID is null
								AnexoModel newAnexo = anexoRepository.save(anexo);
								proc.setAnexo(newAnexo);
							}
						}
						record.setProcesso(proc);
					} else {
						// Handle if ProcessoModel is not found
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
					record.setProcesso(newProcesso);
				}
			}

			// Saves or updates EnderecoModel (can be null or have an existing ID)
			if (updateDocumento.getEndereco() != null) {
				if (updateDocumento.getEndereco().getId() != null) {
					Optional<EnderecoModel> enderecoOptional = enderecoRepository
							.findById(updateDocumento.getEndereco().getId());
					enderecoOptional.ifPresent(endereco -> {
						// Editar attributos como Cidade e Cep.
						EnderecoModel existingEndereco = endereco;
						existingEndereco.setLogradouro(updateDocumento.getEndereco().getLogradouro());
						existingEndereco.setCidade(updateDocumento.getEndereco().getCidade());
						existingEndereco.setCep(updateDocumento.getEndereco().getCep());

						EnderecoModel updatedEndereco = enderecoRepository.save(existingEndereco);
						record.setEndereco(updatedEndereco);
					});

					// endereco.ifPresent(record::setEndereco);
				} else {
					// Create a new EnderecoModel if the ID is null
					EnderecoModel newEndereco = enderecoRepository.save(updateDocumento.getEndereco());
					record.setEndereco(newEndereco);
				}
			}

			return documentoRepository.save(record);
		}).orElse(null);

		if (responseDocumento == null) {
			throw new NoSuchElementException("Não foi encontrado documento com o id: " + id);
		}

		return responseDocumento;
	}

	/*@Transactional
	public DocumentoModel save(DocumentoDTO objDTO, DocumentoModel objMod) {
		DocumentoModel newObject = documentoRepository.save(objMod);

		// Verifica se docProcesso está presente no DTO
		if (objMod.getProcesso() != null) {

			ProcessoModel processo = objMod.getProcesso();

			// Verifica se o anexo associado ao processo precisa ser salvo primeiro
			if (processo.getAnexo() != null && processo.getAnexo().getId() == null) {
				AnexoModel anexo = processo.getAnexo();
				anexo = anexoRepository.save(anexo);
				processo.setAnexo(anexo);
			}

			// Salva o ProcessoModel
			processo = processoRepository.save(processo);
			objMod.setProcesso(processo);
		}

		// Saves or updates EnderecoModel (can be null or have an existing ID)
		if (objMod.getEndereco() != null) {
			if (objMod.getEndereco().getId() != null) {
				Optional<EnderecoModel> enderecoOptional = enderecoRepository.findById(objMod.getEndereco().getId());
				enderecoOptional.ifPresent(endereco -> {
					// Editar attributos como Cidade e Cep.
					EnderecoModel existingEndereco = endereco;
					existingEndereco.setLogradouro(objMod.getEndereco().getLogradouro());
					existingEndereco.setCidade(objMod.getEndereco().getCidade());
					existingEndereco.setCep(objMod.getEndereco().getCep());

					EnderecoModel updatedEndereco = enderecoRepository.save(existingEndereco);
					newObject.setEndereco(updatedEndereco);
				});

				// endereco.ifPresent(record::setEndereco);
			} else {
				// Create a new EnderecoModel if the ID is null
				EnderecoModel newEndereco = enderecoRepository.save(objMod.getEndereco());
				newObject.setEndereco(newEndereco);
			}
		}

		Set<UsuarioModel> usuarios = new HashSet<>();

		// Salvando os usuários e garantindo o relacionamento bidirecional
		for (UsuarioModel usuario : objMod.getUsuarios()) {
			if (usuario.getId() == null) {
				usuario = usuarioRepository.save(usuario);
			}
			usuario.getDocumentos().add(newObject);
			usuarios.add(usuario);
		}

		newObject.setUsuarios(usuarios);

		// Salva o DocumentoModel atualizado com todas as relações
		newObject = documentoRepository.save(objMod);

		return newObject;
	}*/
	
	@Transactional
	public DocumentoModel save(DocumentoDTO objDTO, DocumentoModel objMod) {
		
	  // Processa o EnderecoModel antes de salvar o DocumentoModel
	    if (objMod.getEndereco() != null) {
	    	
	    	
	        EnderecoModel savedEndereco = saveEndereco(objMod.getEndereco());
	        objMod.setEndereco(savedEndereco); // Atualiza o DocumentoModel com o endereço salvo
	        
	        // Salva as Interferencias associadas ao EnderecoModel
	        if (savedEndereco.getInterferencias() != null) {
	        	
	            for (InterferenciaModel interferencia : savedEndereco.getInterferencias()) {
	                interferencia.setEndereco(savedEndereco);
	                interferenciaRepository.save(interferencia);
	            }
	        }
	        
	    }
	    
	    //08/08/2024 - Não está  salvando usuários nem processo

	    // Processa o ProcessoModel se presente
	    if (objMod.getProcesso() != null) {
	        ProcessoModel savedProcesso = saveProcesso(objMod.getProcesso());
	        objMod.setProcesso(savedProcesso); // Atualiza o DocumentoModel com o processo salvo
	    }

	    // Agora que o Endereco e Processo foram salvos, salva o DocumentoModel
	    DocumentoModel newObject = documentoRepository.save(objMod);
	    
	    // Processa os Usuários associados
	    Set<UsuarioModel> usuarios = saveUsuarios(objMod.getUsuarios(), newObject);
	    newObject.setUsuarios(usuarios);

	    // Salva o DocumentoModel atualizado com todas as relações
	    return documentoRepository.save(newObject);
		
	}


	private EnderecoModel saveEndereco(EnderecoModel endereco) {
	    if (endereco.getId() == null) {
	        // Se o endereço não tem ID, ele ainda não foi salvo, então salvamos
	        return enderecoRepository.save(endereco);
	    } else {
	        // Se já tem ID, verificamos se o endereço já está salvo e retornamos o existente
	        return enderecoRepository.findById(endereco.getId()).orElseGet(() -> enderecoRepository.save(endereco));
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
	    Set<UsuarioModel> savedUsuarios = new HashSet<>();
	    for (UsuarioModel usuario : usuarios) {
	    	System.out.println(usuario.getNome());
	        if (usuario.getId() == null) {
	            usuario = usuarioRepository.save(usuario);
	        }
	        usuario.getDocumentos().add(newObject);
	        savedUsuarios.add(usuario);
	    }
	    return savedUsuarios;
	}



}