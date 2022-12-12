package piotr.koziol.zoo.entities.animals;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Elephant extends Animal {
    @Transient
    private final Long ELEPHANT_FOOD = 20L;

    public Elephant() {
        setFood(ELEPHANT_FOOD);
    }

}
