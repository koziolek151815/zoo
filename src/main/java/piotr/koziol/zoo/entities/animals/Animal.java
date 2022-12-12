package piotr.koziol.zoo.entities.animals;

import jakarta.persistence.*;
import lombok.*;
import piotr.koziol.zoo.entities.Zone;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "animals")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "animal_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Animal {
    @Id
    @GeneratedValue
    private Long id;
    private Long food;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id")
    private Zone zone;

}
