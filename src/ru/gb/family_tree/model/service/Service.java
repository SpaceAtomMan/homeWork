package ru.gb.family_tree.model.service;

import ru.gb.family_tree.model.familly_tree.FamilyTree;
import ru.gb.family_tree.model.human.Gender;
import ru.gb.family_tree.model.human.Human;
import ru.gb.family_tree.model.human.builder.HumanBuilder;
import ru.gb.family_tree.model.writer.FileHandler;
import ru.gb.family_tree.model.writer.Writer;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Service { //  в этом классе функционал Приложения, что прописано, то может делать это приложение
// сам ничего не делает (верхне уровневый), но всем раздает. Ты сделай, ты сделай.

    private FamilyTree familyTree;
    private final HumanBuilder humanBuilder;
    private final Writer fileHandler;

    public Service(){
        familyTree = new FamilyTree<Human>();
        humanBuilder = new HumanBuilder();
        fileHandler = new FileHandler();
    }

    public void addHuman(String name, Gender gender, LocalDate birthDate) { // принимаем сырые данные и обращаемся к Билдеру
        Human human; // появился человек human
        human = humanBuilder.build(name, gender, birthDate);
        familyTree.add(human); // добавили его в дерево
    }

    public void sortByName() {
        familyTree.sortByName();
    }
    public void sortByBirthDate() {
        familyTree.sortByBirthDate();
    }


    public String getHumanListInfo() {
        return familyTree.getInfo();
    }

    public void saveTree() throws IOException {
        fileHandler.save(familyTree);
    }

    public void readTree() throws IOException, ClassNotFoundException {
        if (fileHandler.read() != null) {
            familyTree = (FamilyTree) fileHandler.read();
        }
    }

    public String findByName(String name) {
        StringBuilder sb = new StringBuilder();
        List<Human> foundHumans = familyTree.getByName(name);
        for (Human human : foundHumans) {
            sb.append(human).append("\n");
        }
        return sb.toString();
    }

    public List<Long> foundHumansId(String name) {
        List<Human> foundHumans = familyTree.getByName(name);
        List<Long> foundHumansId = new ArrayList<>();
        for (Human human : foundHumans) {
            foundHumansId.add(human.getId());
        }
        return foundHumansId;
    }

    public void removeHuman(int id) {
        familyTree.remove(id);
    }
}
