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
import com.api.main.models.DocumentoModel;
import com.api.main.models.EnderecoModel;
import com.api.main.models.ProcessoModel;
import com.api.main.models.UsuarioModel;
import com.api.main.models.DocumentoTipoModel;
import com.api.main.repositories.DocumentoRepository;
import com.api.main.repositories.DocumentoTipoRepository;
import com.api.main.repositories.EnderecoRepository;
import com.api.main.repositories.ProcessoRepository;
import com.api.main.repositories.UsuarioRepository;

@Service
public class DocumentoService {
	
	@Autowired
	private DocumentoRepository docRepo;
	@Autowired
	private ProcessoRepository procRepo;
	@Autowired
	private EnderecoRepository endRepo;
	@Autowired
	private DocumentoTipoRepository docTipoRepo;
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	
	public DocumentoService(DocumentoRepository docRepo, ProcessoRepository procRepo, EnderecoRepository endRepo,
			DocumentoTipoRepository docTipoRepo) {
		super();
		this.docRepo = docRepo;
		this.procRepo = procRepo;
		this.endRepo = endRepo;
		this.docTipoRepo = docTipoRepo;
	}

	@Transactional
	public List<DocumentoModel> list(String keyword) {
		return docRepo.list(keyword);
	}

	@Transactional
	public void delete() {
		docRepo.deleteAll();
	}

	@Transactional
	public DocumentoModel deleteById(Long id) {
		DocumentoModel deletedDocument = docRepo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Não foi encontrado documento com o id: " + id));
		docRepo.deleteById(id);
		return deletedDocument;
	}

	@Transactional
	public Optional<DocumentoModel> findById(Long id) {
		return docRepo.findById(id);
	}

	
	@Transactional
    public DocumentoModel update(Long id, DocumentoModel updateDocumento) {
        DocumentoModel responseDocumento = docRepo.findById(id).map((DocumentoModel record) -> {
            record.setDocNumero(updateDocumento.getDocNumero());
            record.setDocSei(updateDocumento.getDocSei());

            // Verifies and saves DocumentoTipoModel (must not be null)
            if (updateDocumento.getDocTipo() != null && updateDocumento.getDocTipo().getDtId() != null) {
                Optional<DocumentoTipoModel> documentoTipo = docTipoRepo.findById(updateDocumento.getDocTipo().getDtId());
                documentoTipo.ifPresent(record::setDocTipo);
            } else {
                throw new IllegalArgumentException("É requerido o Tipo de Documento.");
            }

            // Saves or updates ProcessoModel (can be null or have an existing ID)
            if (updateDocumento.getDocProcesso() != null) {
                if (updateDocumento.getDocProcesso().getProcId() != null) {
                    Optional<ProcessoModel> processo = procRepo.findById(updateDocumento.getDocProcesso().getProcId());
                    processo.ifPresent(record::setDocProcesso);
                } else {
                    // Create a new ProcessoModel if the ID is null
                    ProcessoModel newProcesso = procRepo.save(updateDocumento.getDocProcesso());
                    record.setDocProcesso(newProcesso);
                }
            }

            // Saves or updates EnderecoModel (can be null or have an existing ID)
            if (updateDocumento.getDocEndereco() != null) {
                if (updateDocumento.getDocEndereco().getEndId() != null) {
                    Optional<EnderecoModel> enderecoOptional = endRepo.findById(updateDocumento.getDocEndereco().getEndId());
                    enderecoOptional.ifPresent(endereco -> {
                        // Editar attributos como Cidade e Cep.
                        EnderecoModel existingEndereco = endereco;
                        existingEndereco.setEndLogradouro(updateDocumento.getDocEndereco().getEndLogradouro());
                        existingEndereco.setEndCidade(updateDocumento.getDocEndereco().getEndCidade());
                        existingEndereco.setEndCep(updateDocumento.getDocEndereco().getEndCep());

                        EnderecoModel updatedEndereco = endRepo.save(existingEndereco);
                        record.setDocEndereco(updatedEndereco);
                    });
                    
                   // endereco.ifPresent(record::setDocEndereco);
                } else {
                    // Create a new EnderecoModel if the ID is null
                    EnderecoModel newEndereco = endRepo.save(updateDocumento.getDocEndereco());
                    record.setDocEndereco(newEndereco);
                }
            }

            return docRepo.save(record);
        }).orElse(null);

        if (responseDocumento == null) {
            throw new NoSuchElementException("Não foi encontrado documento com o id: " + id);
        }

        return responseDocumento;
    }

	@Transactional
	public DocumentoModel save(DocumentoDTO docDTO, DocumentoModel docMod) {
	    DocumentoModel savedDocumento = null;

	    // Check if docProcesso is present in the DTO
	    if (docMod.getDocProcesso() != null) {
	        ProcessoModel processo = new ProcessoModel();
	        processo.setProcId(docMod.getDocProcesso().getProcId());
	        processo.setProcNumero(docMod.getDocProcesso().getProcNumero());

	        // Save the ProcessoModel
	        processo = procRepo.save(processo);

	        // Set the saved ProcessoModel in DocumentoModel
	        docMod.setDocProcesso(processo);
	    }

	    // Check if docEndereco is present in the DTO
	    if (docMod.getDocEndereco() != null) {
	        EnderecoModel endereco = new EnderecoModel();
	        endereco.setEndId(docMod.getDocEndereco().getEndId());
	        endereco.setEndLogradouro(docMod.getDocEndereco().getEndLogradouro());
	        endereco.setEndCidade(docMod.getDocEndereco().getEndCidade());
	        endereco.setEndCep(docMod.getDocEndereco().getEndCep());

	        // Save the EnderecoModel
	        endereco = endRepo.save(endereco);

	        // Set the saved EnderecoModel in DocumentoModel
	        docMod.setDocEndereco(endereco);
	    }
	    
	    
	    
	    Set<UsuarioModel> usuarios = new HashSet<>();
	    
	    //savedDocumento.setUsuarios(docDTO.getUsuarios());
        
        // Salvando os usuários e garantindo o relacionamento bidirecional
        for (UsuarioModel usuario : docMod.getUsuarios()) {
            if (usuario.getUsId() == null) {
                usuario = usuarioRepository.save(usuario);
            }
            usuario.getDocumentos().add(savedDocumento);
            usuarios.add(usuario);
        }
        
        docMod.setUsuarios(usuarios);

	    // Save the DocumentoModel
	    savedDocumento = docRepo.save(docMod);
	    
	    

	    return savedDocumento;
	}





}