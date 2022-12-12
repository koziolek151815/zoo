package piotr.koziol.zoo.zones;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class ZoneResponseDto {
    private Long id;
    private String name;
    private Long animalsCount;
    private Long foodCount;
}
