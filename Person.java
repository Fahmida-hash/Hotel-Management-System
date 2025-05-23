
package hotelmanagementsystem;


public abstract class Person {
    protected String name;
    protected String contact;

    public Person(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    // ✅ Add these getters if not already present
    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public abstract void displayInfo();
}
