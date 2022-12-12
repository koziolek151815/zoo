package piotr.koziol.zoo.entities.animals;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Lion extends Animal {
    @Transient
    private final Long LION_FOOD = 11L;

    public Lion() {
        setFood(LION_FOOD);
    }

}
