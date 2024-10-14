package structures.DoublyLinkedList;

public class Minion implements Comparable<Minion> {

    private String name;
    private int age;
    private int eyesCount;
    public Minion(String name, int age, int eyesCount) {
        this.name = name;
        this.age = age;
        this.eyesCount = eyesCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getEyesCount() {
        return eyesCount;
    }

    public void setEyesCount(int eyesCount) {
        this.eyesCount = eyesCount;
    }

    @Override
    public String toString() {
        return "Minion{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", eyesCount=" + eyesCount +
                '}';
    }

    // имя по возраст, возр по возр, eyesCount по убыв.
    @Override
    public int compareTo(Minion otherMinion) {
        int nameComparing = this.getName().compareTo(otherMinion.getName());
        if (nameComparing != 0) {
            return nameComparing;
        }
        if (this.getAge() < otherMinion.getAge()) return -1;
        else if (this.getAge() > otherMinion.getAge()) return 1;

        if (this.getEyesCount() > otherMinion.getEyesCount()) return 1;
        else if (this.getEyesCount() < otherMinion.getEyesCount()) return -1;
        return nameComparing;
    }

}