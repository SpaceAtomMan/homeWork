package ru.gb.family_tree.human;

import java.time.LocalDate;
import java.util.List;
import java.time.Period;
import java.util.SplittableRandom;
// import java.util.ArrayList;

public class Human {
    private long id;
    private String name;
    private LocalDate birthDate;
    private LocalDate deathDate;
    private Gender gender;
    private Human father;
    private Human mother;
    private List<Human> children;
    private Human spouse;

    public Human(String name, LocalDate birthDate, LocalDate deathDate, Human father, Human mother,
                 List<Human> children, Gender gender) { // Общий конструктор для Человека
        id = -1;
        this.name = name;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.father = father;
        this.mother = mother;
        this.children = children;
        this.gender = gender;
    }

    public Human(String name, LocalDate birthDate, Gender gender) {
        this.name = name;
        this.birthDate = birthDate;
        this.deathDate = null;
        this.gender = gender;
        this.father = null;
        this.mother = null;
    }

    public Human(String name, LocalDate birthDate, Gender gender, Human father, Human mother) {
        this.name = name;
        this.birthDate = birthDate;
        this.deathDate = null;
        this.gender = gender;
        this.father = father;
        this.mother = mother;
    }

    public void setId(long id) { this.id = id; }
    public long getId() { return id; }
    public void setSpouse(Human spouse){ this.spouse = spouse; }
    public Human getSpouse(){ return spouse;}


    @Override
    public String toString() {
        return getInfo();
    }

    public String getInfo(){
        StringBuilder sb = new StringBuilder();
        sb.append("id=");
        sb.append(id);
        sb.append(", имя=");
        sb.append(name);
        sb.append(", пол=");
        sb.append(getGender());
//        sb.append(", возраст=");
//        sb.append(getAge());
        sb.append(", ");
        sb.append(getSpouseInfo());
        sb.append(", ");
        sb.append(getMotherInfo());
        sb.append(", ");
        sb.append(getFatherInfo());
//        sb.append(", ");
//        sb.append(getChildrenInfo());
        return sb.toString();
    }

    public String getName (){ return name; }
    public Gender getGender () { return gender; }
    public void setFather (Human father) { this.father = father; }
    public void setMother (Human mother) { this.mother = mother; }


    public String getSpouseInfo(){
        String res = "супруг(а): ";
        if (spouse == null) {
            res += "нет";
        } else {
            res += spouse.getName();
        }
        return res;
    }

    public String getMotherInfo(){
        String res = "мать: ";
        if (mother == null){
            res += "не учтено";
        } else {
            res += mother.getName();
        }
    return res;
    }

    public String getFatherInfo(){
        String res = "отец: ";
        if (father == null){
            res += "не учтено";
        } else {
            res += father.getName();
        }
    return res;
    }

}



