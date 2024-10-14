package structures.DoublyLinkedList;

import java.util.Arrays;
import java.util.Iterator;

public class MainLinkedList {
    public static void main(String[] args) {

        DoublyLinkedList<Minion> doublyLinkedList = new DoublyLinkedList<>();

        Minion minionBob = new Minion("Bob", 10, 2);
        Minion minionAlice = new Minion("Alice", 15, 1);
        Minion minionJohn = new Minion("John", 5, 2);
        Minion minionMax = new Minion("Max", 7, 3);
        Minion minionAnn = new Minion("Ann", 3, 1);
        Minion minionGeorge = new Minion("George", 4, 2);

        System.out.println("== Заполнение двусвязного списка ==");
        doublyLinkedList.addFirst(minionBob);
        doublyLinkedList.addLast(minionAlice);
        doublyLinkedList.addFirst(minionAnn);
        doublyLinkedList.addLast(minionJohn);
        doublyLinkedList.addFirst(minionMax);
        System.out.println(doublyLinkedList + "\n" + "Длина: " + doublyLinkedList.getLength());
        System.out.println("\n== Индексатор ==");
        System.out.println("Получение индекса: " + minionJohn + " Индекс: " + doublyLinkedList.getIndex(minionJohn));
        System.out.println("Поиск по индексу: " + doublyLinkedList.findByIndex(1));
        doublyLinkedList.deleteByIndex(3);
        System.out.println("\n== Обновленный список после удаления по индексу и итератор ==");
        for (Minion i : doublyLinkedList) {
            System.out.println(i);
        }

        System.out.println("\n== Замена первого элемента списка ==");
        doublyLinkedList.replaceFirst(minionGeorge);
        System.out.println(doublyLinkedList);

        System.out.println("== Замена последнего элемента списка ==");
        doublyLinkedList.replaceLast(minionGeorge);
        System.out.println(doublyLinkedList);

        System.out.println("== Замена определенного элемента списка ==");
        doublyLinkedList.replace(minionAlice, minionJohn);
        System.out.println(doublyLinkedList);

        System.out.println("== Удаление первого элемента в списке ==");
        doublyLinkedList.removeFirst();
        System.out.println(doublyLinkedList);

        System.out.println("== Удаление последнего элемента в списке ==");
        doublyLinkedList.removeLast();
        System.out.println(doublyLinkedList);

        System.out.println("== Итератор ==");
        for (Minion i : doublyLinkedList) {
            System.out.println(i);
        }
        System.out.println("\n== Компаратор: имя по возрастанию, возр по возрастанию, eyesCount по убыванию ==");
        comparator();
    }


    private static void comparator() {
            // имя по возраст, возр по возр, eyesCount по убыв.
            Minion[] minions = {
                    new Minion("Олеся", 2, 5),
                    new Minion("Алекс", 1, 7),
                    new Minion("Богдан", 1, 1),
                    new Minion("Вика", 1, 8),
                    new Minion("Вика", 2, 7),
                    new Minion("Максим", 1, 8),
                    new Minion("Вика", 3, 5),
                    new Minion("Настя", 2, 5),
                    new Minion("Яна", 1, 4)
            };

            Arrays.sort(minions);

            for (Minion minion : minions) {
                System.out.println(minion);
            }
        }
    }