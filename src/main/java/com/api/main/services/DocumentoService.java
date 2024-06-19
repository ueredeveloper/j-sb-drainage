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
	public List<DocumentoModel> list(String keyword) {
		return documentoRepository.list(keyword);
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
			record.setDocNumero(updateDocumento.getDocNumero());
			record.setDocSei(updateDocumento.getDocSei());

			// Verifies and saves DocumentoTipoModel (must not be null)
			if (updateDocumento.getDocTipo() != null && updateDocumento.getDocTipo().getDtId() != null) {
				Optional<DocumentoTipoModel> documentoTipo = docTipoRepo
						.findById(updateDocumento.getDocTipo().getDtId());
				documentoTipo.ifPresent(record::setDocTipo);
			} else {
				throw new IllegalArgumentException("É requerido o Tipo de Documento.");
			}

			// Saves or updates ProcessoModel (can be null or have an existing ID)
			if (updateDocumento.getDocProcesso() != null) {
				if (updateDocumento.getDocProcesso().getProcId() != null) {
					Optional<ProcessoModel> processo = processoRepository.findById(updateDocumento.getDocProcesso().getProcId());
					processo.ifPresent(record::setDocProcesso);
				} else {
					// Create a new ProcessoModel if the ID is null
					ProcessoModel newProcesso = processoRepository.save(updateDocumento.getDocProcesso());
					record.setDocProcesso(newProcesso);
				}
			}

			// Saves or updates EnderecoModel (can be null or have an existing ID)
			if (updateDocumento.getDocEndereco() != null) {
				if (updateDocumento.getDocEndereco().getEndId() != null) {
					Optional<EnderecoModel> enderecoOptional = enderecoRepository
							.findById(updateDocumento.getDocEndereco().getEndId());
					enderecoOptional.ifPresent(endereco -> {
						// Editar attributos como Cidade e Cep.
						EnderecoModel existingEndereco = endereco;
						existingEndereco.setEndLogradouro(updateDocumento.getDocEndereco().getEndLogradouro());
						existingEndereco.setEndCidade(updateDocumento.getDocEndereco().getEndCidade());
						existingEndereco.setEndCep(updateDocumento.getDocEndereco().getEndCep());

						EnderecoModel updatedEndereco = enderecoRepository.save(existingEndereco);
						record.setDocEndereco(updatedEndereco);
					});

					// endereco.ifPresent(record::setDocEndereco);
				} else {
					// Create a new EnderecoModel if the ID is null
					EnderecoModel newEndereco = enderecoRepository.save(updateDocumento.getDocEndereco());
					record.setDocEndereco(newEndereco);
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
	    public DocumentoModel save(DocumentoDTO docDTO, DocumentoModel docMod) {
	        DocumentoModel newDocumento = documentoRepository.save(docMod);

	        // Verifica se docProcesso está presente no DTO
	        if (docMod.getDocProcesso() != null) {
	            ProcessoModel processo = docMod.getDocProcesso();

	            // Verifica se o anexo associado ao processo precisa ser salvo primeiro
	            if (processo.getAnexo() != null && processo.getAnexo().getId() == null) {
	                AnexoModel anexo = processo.getAnexo();
	                anexo = anexoRepository.save(anexo);
	                processo.setAnexo(anexo);
	            }

	            // Salva o ProcessoModel
	            processo = processoRepository.save(processo);
	            docMod.setDocProcesso(processo);
	        }

	        // Verifica se docEndereco está presente no DTO
	        if (docMod.getDocEndereco() != null) {
	            EnderecoModel endereco = docMod.getDocEndereco();
	            endereco = enderecoRepository.save(endereco);
	            docMod.setDocEndereco(endereco);
	        }

	        Set<UsuarioModel> usuarios = new HashSet<>();

	        // Salvando os usuários e garantindo o relacionamento bidirecional
	        for (UsuarioModel usuario : docMod.getUsuarios()) {
	            if (usuario.getUsId() == null) {
	                usuario = usuarioRepository.save(usuario);
	            }
	            usuario.getDocumentos().add(newDocumento);
	            usuarios.add(usuario);
	        }

	        newDocumento.setUsuarios(usuarios);

	        // Salva o DocumentoModel atualizado com todas as relações
	        newDocumento = documentoRepository.save(docMod);

	        return newDocumento;
	    }*/
	
	@Transactional
	public DocumentoModel save(DocumentoDTO docDTO, DocumentoModel docMod) {
	    DocumentoModel newDocumento = documentoRepository.save(docMod);

	    // Verifica se docProcesso está presente no DTO
	    if (docMod.getDocProcesso() != null) {
	    	
	        ProcessoModel processo = docMod.getDocProcesso();

	        // Verifica se o anexo associado ao processo precisa ser salvo primeiro
	        if (processo.getAnexo() != null && processo.getAnexo().getId() == null) {
	            AnexoModel anexo = processo.getAnexo();
	            anexo = anexoRepository.save(anexo);
	            processo.setAnexo(anexo);
	        }

	        // Salva o ProcessoModel
	        processo = processoRepository.save(processo);
	        docMod.setDocProcesso(processo);
	    }

	    // Verifica se docEndereco está presente no DTO
	    if (docMod.getDocEndereco() != null) {
	        EnderecoModel endereco = docMod.getDocEndereco();
	        System.out.println("endereco model");
	        System.out.println(docMod.getDocEndereco().getEndInterferencias().size());
	        endereco = enderecoRepository.save(endereco);
	        docMod.setDocEndereco(endereco);
	    }
	    
	  

	    Set<UsuarioModel> usuarios = new HashSet<>();

	    // Salvando os usuários e garantindo o relacionamento bidirecional
	    for (UsuarioModel usuario : docMod.getUsuarios()) {
	        if (usuario.getUsId() == null) {
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