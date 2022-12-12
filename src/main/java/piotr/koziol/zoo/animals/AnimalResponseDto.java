package piotr.koziol.zoo.animals;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnimalResponseDto {
    private Long id;
    private Long food;
    private String name;
}
