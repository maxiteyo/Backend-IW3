package ar.edu.iua.iw3.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity //Para que sea persistente la entidad (le digo JPA) --> Que la clase product sea una entidad que va a persistir
@Table(name="products") //Le digo que la tabla que va a guardar mis entidades de tipo "Product" se va a llamar "products"
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Autonumerico
    private long id;

    @Column(length = 100, unique = true) //Cada uno de los nombres va a ser unico (por su nombre o su ID), de tamaño 100
    private String product;

    @Column(columnDefinition = "tinyint default 0") //Este boolean va a ser un tinyint (se guarda asi en la BD)
    private boolean stock = false;
    
    private double precio;

    @ManyToOne //El many hace referencia a la clase donde estamos.
    @JoinColumn(name="id_category", nullable = true) //Le digo que la columna que va a hacer de FK en la tabla products se va a llamar id_category
    private Category category;//Una categoria va a poder tener muchos productos (relacion de muchos a uno)

}
