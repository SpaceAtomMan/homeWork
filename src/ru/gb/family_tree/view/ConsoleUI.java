package ru.gb.family_tree.view;

import ru.gb.family_tree.model.human.Gender;
import ru.gb.family_tree.presenter.Presenter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI implements View{
    private final MenuHandler menuHandler;
    private final Presenter presenter;
    private final InputHandler inputHandler;
    private boolean work;


    public ConsoleUI() {
        Scanner scanner = new Scanner(System.in);
        inputHandler = new InputHandler(scanner);
        presenter = new Presenter(this);
        menuHandler = new MenuHandler(this);
        work = true;
    }

    @Override
    public void startWork() throws IOException, ClassNotFoundException {
        greetings();
 //       presenter.readTree();
        selectItemFromMenu();
    }

    private void greetings() {
        System.out.println("Добро пожаловать! \nПожалуйста, выберите нужный Вам пункт меню.");
    }

    private void selectItemFromMenu() throws IOException {
        while (work) {
            System.out.println(menuHandler.getMenu());
            String choiceStr = inputHandler.getInput();
            if (inputHandler.isValidMenuChoice(choiceStr, menuHandler.size())) {
                int choice = Integer.parseInt(choiceStr);
                menuHandler.execute(choice);
            } else {
                System.out.println("Введён неверный пункт меню.\nВведите корректный номер из меню: от 1 до " + menuHandler.size());
            }
        }
    }

    public void finishWork() throws IOException {
        work = false;
        System.out.println("До новых встреч!");
        presenter.saveTree();
    }


    public void sortByBirthDate() {
        presenter.sortByBirthDate();
    }

    public void sortByName() {
        presenter.sortByName();
    }

    public void getHumanListInfo() {
        presenter.getHumanListInfo();
    }

    public void addHuman() {
        System.out.println("Укажите имя человека:");
        String name = inputHandler.getInput();

        System.out.println("Укажите пол человека м/ж:");
        Gender gender = inputHandler.getGenderInput();

        System.out.println("Укажите дату рождения человека через пробел в формате ДД ММ ГГГГ:");
        LocalDate birthDate = inputHandler.getBirthDateInput();

        presenter.addHuman(name, gender, birthDate);
    }

    public void findByName() {
        System.out.println("Укажите имя человека, которого хотите найти:");
        String name = inputHandler.getInput();
        printFoundHumans(name);
    }

    private void printFoundHumans(String name) {
        String foundHumans = presenter.findByName(name);
        if (foundHumans.isEmpty()) {
            System.out.println("Не найдено ни одного человека.");
        } else {
            System.out.println("Список найденных людей:");
            System.out.println(foundHumans);
        }
    }

    public void removeHuman() {
        System.out.println("Укажите имя человека, которого хотите удалить:");
        String name = inputHandler.getInput();
        printFoundHumans(name);
        List<Long> foundHumansId = presenter.foundHumansId(name);
        if (!foundHumansId.isEmpty()) {
            removeHumanById(name, foundHumansId);
        }
    }

    private void removeHumanById(String name, List<Long> foundHumansId) {
        System.out.println("Укажите id человека, которого хотите удалить:");
        boolean flag = true;
        while (flag) {
            String idStr = inputHandler.getInput();
            if (inputHandler.isValidIdChoice(idStr, foundHumansId)) {
                int id = Integer.parseInt(idStr);
                presenter.removeHuman(id);
                System.out.println("Человек по имени " + name + ", с id " + id + " был удалён.");
                flag = false;
            } else {
                System.out.println("Введён неверный id.\nВведите корректный id из списка: " + foundHumansId);
            }
        }
    }

    @Override
    public void printAnswer(String answer) {
        System.out.println(answer);
    }

}
