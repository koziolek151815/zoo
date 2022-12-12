package piotr.koziol.zoo.entities.animals;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Rabbit extends Animal {
    @Transient
    private final Long RABBIT_FOOD = 4L;

    public Rabbit() {
        setFood(RABBIT_FOOD);
    }
}
