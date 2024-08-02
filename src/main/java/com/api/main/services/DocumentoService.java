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
				Optional<DocumentoTipoModel> documentoTipo = docTipoRepo
						.findById(updateDocumento.getTipo().getId());
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

	@Transactional
	public DocumentoModel save(DocumentoDTO docDTO, DocumentoModel docMod) {
		DocumentoModel newDocumento = documentoRepository.save(docMod);

		// Verifica se docProcesso está presente no DTO
		if (docMod.getProcesso() != null) {

			ProcessoModel processo = docMod.getProcesso();

			// Verifica se o anexo associado ao processo precisa ser salvo primeiro
			if (processo.getAnexo() != null && processo.getAnexo().getId() == null) {
				AnexoModel anexo = processo.getAnexo();
				anexo = anexoRepository.save(anexo);
				processo.setAnexo(anexo);
			}

			// Salva o ProcessoModel
			processo = processoRepository.save(processo);
			docMod.setProcesso(processo);
		}

		// Verifica se docEndereco está presente no DTO
		if (docMod.getEndereco() != null) {
			EnderecoModel endereco = docMod.getEndereco();
			System.out.println("endereco model");
			System.out.println(docMod.getEndereco().getInterferencias().size());
			endereco = enderecoRepository.save(endereco);
			docMod.setEndereco(endereco);
		}

		Set<UsuarioModel> usuarios = new HashSet<>();

		// Salvando os usuários e garantindo o relacionamento bidirecional
		for (UsuarioModel usuario : docMod.getUsuarios()) {
			if (usuario.getId() == null) {
				usuario = usuarioRepository.save(usuario);
			}
			usuario.getDocumentos().add(newDocumento);
			usuarios.add(usuario);
		}

		newDocumento.setUsuarios(usuarios);

		// Salva o DocumentoModel atualizado com todas as relações
		newDocumento = documentoRepository.save(docMod);

		return newDocumento;
	}

}