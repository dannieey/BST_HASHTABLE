package models;

public class MyTestingClass {//creat this class for testing our hash table
    private int id;

    public MyTestingClass(int id) { //constructor
        this.id = id;
    }//here we initialize the obj with a given id

    @Override
    public int hashCode() {
        // A simple custom hash function
        return (id * 31 + 17) ^ (id >>> 3);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyTestingClass)) return false;
        MyTestingClass other = (MyTestingClass) o;
        return this.id == other.id;
        //here we compare two obj based on their id + ensure that tese obj w the same id are considered equal
    }

    @Override
    public String toString() {
        return "ID: " + id;
    }
}
