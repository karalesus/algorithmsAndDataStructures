package structures.hashTable;

public class MainHashTable {

    public static void main(String[] args) {
        HashTable<String, Integer> hashTable = new HashTable<>();

        hashTable.add("Chocolate", 5);
        hashTable.add("Cookies", 7);
        hashTable.add("Ice Cream", 3);
        hashTable.add("Candy", 4);
        hashTable.add("Marmalade", 4);
        hashTable.add("Waffles", 9);
        hashTable.add("Zephyr", 2);
//        hashTable.add("Cake", 1);
//        hashTable.add("Macaron", 1);
//        hashTable.add("M&Ms", 4);
//        hashTable.add("Skittles", 2);
//        hashTable.add("Picnic", 2);
//        hashTable.add("Gum", 8); //13 -> 32

        for (KeyValue<String, Integer> i : hashTable) {
            System.out.println(i + " | Индекс в таблице: " + Math.abs(i.getKey().hashCode()) % hashTable.capacity());

        }

        System.out.println("\nРазмер таблицы: " + hashTable.capacity());
        System.out.println("Найти значение по ключу Candy: " + hashTable.get("Candy"));
        System.out.println("В таблице есть ключ Cookies?: " + hashTable.containsKey("Cookies"));
        System.out.println("Удалить Zephyr: " + hashTable.remove("Zephyr"));

//        for (KeyValue<String, Integer> i : hashTable) {
//            System.out.println(i);
//        }

//        System.out.println(hashTable.keys());
//        System.out.println(hashTable.values());

//        System.out.println("Количество коллизий: " + hashTable.getCollisions());
    }
}
