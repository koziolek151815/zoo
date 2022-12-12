package piotr.koziol.zoo.animals;

public enum AnimalTypeEnum {
    ELEPHANT("elephant"),
    LION("lion"),
    RABBIT("rabbit");
    private final String animalType;

    AnimalTypeEnum(String animalType) {
        this.animalType = animalType;
    }

    public String getValue() {
        return this.animalType;
    }


}
