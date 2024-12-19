package structures.hashTable;

import java.util.*;

public class HashTable<K, V> implements Iterable<KeyValue<K, V>> {
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.80d;
    private int collisions = 0;
    private LinkedList<KeyValue<K, V>>[] slots;
    private int count; // количество элементов
    private int capacity; // размер таблицы

    public HashTable() {
        this(INITIAL_CAPACITY); // вызов другого конструктора
    }

    public HashTable(int capacity) {
        this.slots = new LinkedList[capacity];
        this.count = 0;
        this.capacity = capacity;
    }

    public void add(K key, V value) {
        growIfNeeded();

        int index = findSlotNumber(key);
        if (slots[index] == null) {
            slots[index] = new LinkedList<>();
        } else {
            collisions++;
        }

        for (KeyValue<K, V> current : slots[index]) {
            if (current.getKey().equals(key)) {
                throw new IllegalArgumentException("Такой ключ уже существует");
            }
        }


        KeyValue<K, V> newKeyValue = new KeyValue<>(key, value);
        slots[index].addLast(newKeyValue);
        count++;

    }

    private int findSlotNumber(K key) {
        return Math.abs(key.hashCode()) % this.slots.length;
    }

    private void growIfNeeded() {
        if ((double) (this.size() + 1) / this.capacity() > LOAD_FACTOR) {
            this.grow();
        }
    }

    private void grow() {
        HashTable<K, V> newHashTable = new HashTable<>(slots.length * 2);

        for (LinkedList<KeyValue<K, V>> elements : slots) {
            if (elements != null) {
                for (KeyValue<K, V> element : elements) {
                    newHashTable.add(element.getKey(), element.getValue());
                }
            }
        }

        slots = newHashTable.slots;
        count = newHashTable.count;
    }

    public int size() {
        return count;
    }

    public int capacity() {
        return slots.length;
    }

    public boolean addOrReplace(K key, V value) {
        growIfNeeded();

        int index = findSlotNumber(key);
        if (slots[index] == null) {
            slots[index] = new LinkedList<>();
        }
        for (KeyValue<K, V> current : slots[index]) {
            if (current.getKey().equals(key)) {
                current.setValue(value);
                return false;
            }
        }

        KeyValue<K, V> newKeyValue = new KeyValue<>(key, value);
        slots[index].addLast(newKeyValue);
        count++;
        return true;
    }

    public V get(K key) {
        KeyValue<K, V> element = find(key);

        if (element == null) {
            throw new IllegalArgumentException("Такого элемента нет!");
        }
        return element.getValue();
    }

    public KeyValue<K, V> find(K key) {
        int index = findSlotNumber(key);
        LinkedList<KeyValue<K, V>> elements = slots[index];

        if (elements != null) {
            for (KeyValue<K, V> slot : elements) {
                if (slot.getKey().equals(key)) {
                    return slot;
                }
            }
        }

        return null;
    }

    public boolean containsKey(K key) {
        KeyValue<K, V> element = find(key);

        return element != null;
    }

    public boolean remove(K key) {
        int index = findSlotNumber(key);
        LinkedList<KeyValue<K, V>> elements = slots[index];

        if (elements != null) {
            for (KeyValue<K, V> element : elements) {
                if (element.getKey().equals(key)) {
                    elements.remove(element);
                    count--;
                    return true;
                }
            }
        }

        return false;
    }

    public void clear() {
        this.slots = new LinkedList[INITIAL_CAPACITY];
        this.count = 0;
    }

    public int getCollisions() {
        return collisions;
    }

    public long collisionCount() {
        return Arrays.stream(slots)
                .filter(list -> list != null && !list.isEmpty())
                .count();
    }

    public Iterable<K> keys() {
        List<K> keys = new ArrayList<>();
        this.forEach(e -> keys.add(e.getKey())); // метод forEach из Iterable!!
        return keys;
    }

    public Iterable<V> values() {
        List<V> values = new ArrayList<>();
        this.forEach(e -> values.add(e.getValue()));
        return values;
    }

    @Override
    public Iterator<KeyValue<K, V>> iterator() {
        return new HashTableIterator();
    }

    private class HashTableIterator implements Iterator<KeyValue<K, V>> {
        Deque<KeyValue<K, V>> elements;

        public HashTableIterator() {
            this.elements = new ArrayDeque<>();
            for (LinkedList<KeyValue<K, V>> slot : slots) {
                if (slot != null) {
                    for (KeyValue<K, V> keyValue : slot) {
                        elements.addFirst(keyValue);
                    }
                }
            }
        }

        @Override
        public boolean hasNext() {
            return !elements.isEmpty();
        }

        @Override
        public KeyValue<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return elements.removeFirst();
        }
    }
}


