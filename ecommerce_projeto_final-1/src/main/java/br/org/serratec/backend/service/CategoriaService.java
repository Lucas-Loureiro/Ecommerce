package br.org.serratec.backend.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.org.serratec.backend.model.Categoria;
import br.org.serratec.backend.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	public Categoria inserir(Categoria categoria) {
		Categoria c1 = new Categoria();
		c1.setNome(categoria.getNome());
		c1.setDescricao(categoria.getDescricao());
		return categoriaRepository.save(c1);
		}
	
	
}
