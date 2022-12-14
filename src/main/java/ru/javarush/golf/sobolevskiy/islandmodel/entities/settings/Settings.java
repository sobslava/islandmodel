package ru.javarush.golf.sobolevskiy.islandmodel.entities.settings;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import ru.javarush.golf.sobolevskiy.islandmodel.entities.organisms.OrganismsCommonSpecs;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Настройки
 *
 * @param
 * @return
 * @throws
 */

public class Settings {
    private static final String INIT_FILE = "InitialValues.yml";
    private static volatile Settings SETTINGS;

    private final int mapRows = 10; // Default initial value
    private final int mapCols = 10; // Default initial value
    private final int cycleDuration = 100; // Default initial value
    private final Map<String, Integer> organismsStartNumber = null;
    private final Boolean stopOnTimeout = false;
    private final int gameDuration = 1000; // Default initial value
    private Map<String, Integer> organismsChildrenQuantity = null;
    private Map<String, Map<String, Integer>> chanceToGetEat = null;
    private Map<String, OrganismsCommonSpecs> organismsCommonSpecs = null;
    private List<String> organismsTypes = null;
    private int initialBirthPercent = 5; // Default initial value
    private int deathWeightPercent = 50; // Default initial value
    private int animalGrowUpPercent = 30; // Default initial value
    private int plantGrowUpPercent = 50; // Default initial value
    private byte corePoolSize = 4;

    private Settings() {
        try {
            URL resource = Settings.class.getClassLoader().getResource(INIT_FILE);
            ObjectReader objectReader = new YAMLMapper().readerForUpdating(this);
            if (Objects.nonNull(resource)) {
                objectReader.readValue(resource.openStream());
            }

            organismsTypes = new ArrayList<>(organismsCommonSpecs.keySet());
        } catch (IOException e) {
            System.out.printf("Ошибка при чтении файла настроек InitialValues.yml: %s", e);
        }
    }

    public static Settings get() {
        Settings settings = SETTINGS;

        if (Objects.isNull(settings)) {
            synchronized (Settings.class) {
                if (Objects.isNull(settings = SETTINGS)) {
                    settings = SETTINGS = new Settings();
                }
            }
        }

        return settings;
    }

    public int getMapRows() {
        return mapRows;
    }

    public int getMapCols() {
        return mapCols;
    }

    public Map<String, Integer> getOrganismsStartNumber() {
        return organismsStartNumber;
    }

    public int getCycleDuration() {
        return cycleDuration;
    }

    public Boolean getStopOnTimeout() {
        return stopOnTimeout;
    }

    public byte getCorePoolSize() { return corePoolSize; }

    public int getGameDuration() {
        return gameDuration;
    }

    public Map<String, Integer> getOrganismsChildrenQuantity() {
        return organismsChildrenQuantity;
    }

    public Map<String, Map<String, Integer>> getChanceToGetEat() {
        return chanceToGetEat;
    }

    public Map<String, OrganismsCommonSpecs> getOrganismsCommonSpecs() {
        return organismsCommonSpecs;
    }

    public List<String> getOrganismsTypes() {
        return organismsTypes;
    }

    public OrganismsCommonSpecs getOrganismCommonSpecsByType(String organismType) {
        return organismsCommonSpecs.get(organismType);
    }

    public int getInitialBirthPercent() {
        return initialBirthPercent;
    }

    public int getDeathWeightPercent() {
        return deathWeightPercent;
    }

    public int getAnimalGrowUpPercent() {
        return animalGrowUpPercent;
    }

    public int getPlantGrowUpPercent() {
        return plantGrowUpPercent;
    }
}
