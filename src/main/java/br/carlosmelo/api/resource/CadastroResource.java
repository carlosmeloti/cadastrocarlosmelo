package br.carlosmelo.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.carlosmelo.api.event.RecursoCriadoEvent;
import br.carlosmelo.api.model.Cadastro;
import br.carlosmelo.api.repository.CadastroRepository;

@RestController
@RequestMapping("/cadastro")
public class CadastroResource {
	
	@Autowired
	private CadastroRepository cadastroRepository;
	
	@GetMapping
	public List<Cadastro> listar() {
		return cadastroRepository.findAll();
	}
	
	@GetMapping("/source")
	public String retornoGit() {
		return "Nhain";
	}
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA') and #oauth2.hasScope('write')")
	public ResponseEntity<Cadastro>  criar(@Valid @RequestBody Cadastro cadastro, HttpServletResponse response) {
		Cadastro categoriaSalva = cadastroRepository.save(cadastro);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCpf()));
			
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}
	
	@GetMapping("/{cpf}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
	public Cadastro buscarPeloCodigo(@PathVariable Long cpf) {
			return cadastroRepository.getOne(cpf);
	}
	
	@DeleteMapping("/{cpf}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cpf) {
		cadastroRepository.deleteById(cpf);
	}
	
	@PutMapping("/{cpf}")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_GENERO') and #oauth2.hasScope('write')")
	public ResponseEntity<Cadastro> atualizar (@PathVariable Long cpf, @Valid @RequestBody Cadastro cadastro){
		Cadastro cadastroSalvo = cadastroRepository.getOne(cpf);		
		 BeanUtils.copyProperties(cadastro, cadastroSalvo, "cpf");
		 cadastroRepository.save(cadastroSalvo);
		 return ResponseEntity.ok(cadastroSalvo);
	}
	
	
	
	

}
