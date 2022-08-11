package ru.javarush.golf.sobolevskiy.islandmodel.factories;

import ru.javarush.golf.sobolevskiy.islandmodel.entities.organisms.Organism;
import ru.javarush.golf.sobolevskiy.islandmodel.entities.organisms.animals.herbivores.Horse;

public class HorseFactory implements ru.javarush.golf.sobolevskiy.islandmodel.factories.OrganismFactory {
    @Override
    public Organism createOrganism() {
        return new Horse();
    }
}