public class Animal extends Entity {
    private String name;
    private int age;

    public Animal(int id, String name, int age) {
        super(id);
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Animal ID = " + getId() + ", Animal name = " + name + ", Age = " + age;
    }

    public boolean feed(Crop crop) {
        if (crop != null && crop.getQuantity() > 0) {
            System.out.println("Feeding " + name + " with " + crop.getType());
            crop.setQuantity(crop.getQuantity() - 1);
            return true;
        } else {
            System.out.println(name + " cannot be fed with " + crop.getType() +
                    " because the crop quantity is 0.");
            return false;
        }
    }
}