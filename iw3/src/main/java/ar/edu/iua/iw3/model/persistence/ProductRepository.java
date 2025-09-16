package ar.edu.iua.iw3.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.iua.iw3.model.Product;
import java.util.Optional;


@Repository //Le digo a JPA que esto es un repositorio JPA

//Con el repositorio VACIO ya tengo un metodo que me permite buscar mis productos por ID,
// pero no tengi ninguno que me permita buscarlo por nombre (JPA repositori no sabe que name es mi clave alternativa --> Name es unique)
public interface ProductRepository extends JpaRepository<Product, Long>{ //Long es el tipo de dato del ID

    Optional<Product> findByProduct(String product); //Le digo que me busque por el atributo producto
    //y lo empaquete en un Optional

    Optional<Product> findByProductAndIdNot(String product, long id); //Lo uso para el update
    //Es importante el "AndIdNot" porque si no me va a encontrar el mismo producto que estoy queriendo actualizar
    //y me va a tirar error de que ya existe
}
// SELECT * FROM products WHERE product=?
