package br.org.serratec.backend.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.org.serratec.backend.model.Foto;
import br.org.serratec.backend.model.Produto;
import br.org.serratec.backend.repository.FotoRepository;

@Service
public class FotoService {
	
	@Autowired
	private FotoRepository fotoRepository;
	
	public Foto inserir(MultipartFile file) throws IOException {
		Foto foto = new Foto();
		foto.setDados(file.getBytes());
		foto.setTipo(file.getContentType());
		foto.setNome(file.getName());
		return fotoRepository.save(foto);
	}
	
	public Foto buscar(Long id) {
		Optional<Foto> optional = fotoRepository.findById(id);
		
		if(!optional.isPresent()) {
			return null;
		}
		return optional.get();
	}
	
	public Foto deletar(Long id) {
		fotoRepository.deleteById(id);
		return null;
	}
}
