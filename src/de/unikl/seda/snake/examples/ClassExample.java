package de.unikl.seda.snake.examples;


abstract class Person {
    Person() {
    }

    void sayHelloGoodBye() {
        sayHi();
        System.out.println("Good Bye!");
    }

    abstract void sayHi();
}

// Klasse "User"
class User extends Person {
    // Attribute der Klasse == klassen-spezifische Variablen
    String name;
    String password;
    int age;

    // Constructor
    User(String name, String password, int age) {
        this.name = name;
        this.password = password;
        this.age = age;
    }

    // einfache Methode
    void sayHi() {
        System.out.println("Hello, my name is " + name + " and I am " + age + " years old.");
    }
}

// Klasse "User"
class Admin extends User {
    // Attribute der Klasse == klassen-spezifische Variablen
    int adminId;

    // Constructor
    Admin(String name, String password, int age, int adminId) {
        super(name, password, age);
        this.adminId = adminId;
    }

    // einfache Methode
    @Override
    void sayHi() {
        super.sayHi();
        System.out.println("Hello, I am a very importand admin with ID: " + adminId);
    }
}

public class ClassExample {
    public static void main(String[] args) {
        User bob = new User("Bob", "test", 42);
        User yoda = new User("Yoda", "the force", 800);
        Admin boss = new Admin("Boss", "1234", 60, 1);

        bob.sayHi();
        yoda.sayHi();
        boss.sayHelloGoodBye();
    }
}

