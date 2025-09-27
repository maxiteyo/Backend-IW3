package ar.edu.iua.iw3.integration.cli1.model.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.iua.iw3.integration.cli1.model.ProductCli1;
import ar.edu.iua.iw3.model.Product;

@Repository
public interface ProductCli1Repository extends JpaRepository<Product, Long>{
    Optional<ProductCli1> findOneByCodCli1(String codCli1);
}
