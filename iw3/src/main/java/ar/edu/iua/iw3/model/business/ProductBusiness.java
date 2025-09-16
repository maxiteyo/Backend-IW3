package ar.edu.iua.iw3.model.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.iua.iw3.model.Product;
import ar.edu.iua.iw3.model.persistence.ProductRepository;
import lombok.extern.slf4j.Slf4j;

@Service //Le decimos a Spring que esto se va a instanciar cada vez que quiera un IProductBusiness
@Slf4j
public class ProductBusiness implements IProductBusiness{


    //--Esta es la capa de persistencia---
    @Autowired //Le digo a Spring que me cablee automaticamente con algo físico
    private ProductRepository productDAO; //Los DAO son un patrón. Tienen todas las funcionalidades
    // que me permiten lidiar con una Base de datos.

    @Override
    public List<Product> list() throws BusinessException {
        
        try {
            return productDAO.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).message(e.getMessage()).build();
        }
    }

	@Override
	public Product load(long id) throws NotFoundException, BusinessException {
		Optional<Product> r;
		try {
			r = productDAO.findById(id);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}
		if (r.isEmpty()) {
			throw NotFoundException.builder().message("No se encuentra el Producto id=" + id).build();
		}
		return r.get();//Al optional le sacas lo que encapsula adentro con get()
	}

	@Override
	public Product load(String product) throws NotFoundException, BusinessException {
		Optional<Product> r;
		try {
			r = productDAO.findByProduct(product);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}
		if (r.isEmpty()) {
			throw NotFoundException.builder().message("No se encuentra el Producto '"+product+"'").build();
		}
		return r.get();
	}

	@Override
	public Product add(Product product) throws FoundException, BusinessException {

		try { //Busca por ID, si no lo encuentro sigo
			load(product.getId());
			throw FoundException.builder().message("Se encuentró el Producto id=" + product.getId()).build();
		} catch (NotFoundException e) {
		}
		try { //Busca por nombre, si no lo encuentro sigo
			load(product.getProduct());
			throw FoundException.builder().message("Se encuentró el Producto '" + product.getProduct() +"'").build();
		} catch (NotFoundException e) {
		}

		try {
			return productDAO.save(product); //Esto es lo que finalmente agrega el producto. Save() me devuelve el producto COMPLETO
            // El producto tiene el ID generado por la BD (autonumerico) y me lo devuelve con el ID que generó MySQL
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}
	}

	@Override
	public Product update(Product product) throws FoundException, NotFoundException, BusinessException {
		load(product.getId()); 
		Optional<Product> nombreExistente=null;
		try {
			nombreExistente=productDAO.findByProductAndIdNot(product.getProduct(), product.getId());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}
		if(nombreExistente.isPresent()) {
			throw FoundException.builder().message("Se encontró un producto nombre="+product.getProduct()).build();
		}

		try {
			return productDAO.save(product);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}
	}

	@Override
	public void delete(long id) throws NotFoundException, BusinessException {
		load(id);
		try {
			 productDAO.deleteById(id);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}
	}

}
