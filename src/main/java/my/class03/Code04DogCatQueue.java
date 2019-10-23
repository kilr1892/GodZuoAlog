package my.class03;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 实现一种狗猫队列的结构，要求如下： 用户可以调用add方法将cat类或dog类的
 * 实例放入队列中； 用户可以调用pollAll方法，将队列中所有的实例按照进队列
 * 的先后顺序依次弹出； 用户可以调用pollDog方法，将队列中dog类的实例按照
 * 进队列的先后顺序依次弹出； 用户可以调用pollCat方法，将队列中cat类的实
 * 例按照进队列的先后顺序依次弹出； 用户可以调用isEmpty方法，检查队列中是
 * 否还有dog或cat的实例； 用户可以调用isDogEmpty方法，检查队列中是否有dog
 * 类的实例； 用户可以调用isCatEmpty方法，检查队列中是否有cat类的实例。
 *
 * @author RichardLee
 * @version v1.0
 */
public class Code04DogCatQueue {
    public static void main(String[] args) {
        Code04DogCatQueue.DogCatQueue test = new Code04DogCatQueue.DogCatQueue();

        Code04DogCatQueue.Pet dog1 = new Code04DogCatQueue.Dog();
        Code04DogCatQueue.Pet cat1 = new Code04DogCatQueue.Cat();
        Code04DogCatQueue.Pet dog2 = new Code04DogCatQueue.Dog();
        Code04DogCatQueue.Pet cat2 = new Code04DogCatQueue.Cat();
        Code04DogCatQueue.Pet dog3 = new Code04DogCatQueue.Dog();
        Code04DogCatQueue.Pet cat3 = new Code04DogCatQueue.Cat();

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);
        while (!test.isDogEmpty()) {
            System.out.println(test.pollDog().getPetType());
        }
        while (!test.isEmpty()) {
            String petType = test.pollAll().getPetType();
            System.out.println(petType);
        }
    }

    public static class Pet {
        private String type;

        public Pet(String type) {
            this.type = type;
        }

        public String getPetType() {
            return this.type;
        }
    }

    public static class Dog extends Code04DogCatQueue.Pet {
        public Dog() {
            super("dog");
        }
    }

    public static class Cat extends Code04DogCatQueue.Pet {
        public Cat() {
            super("cat");
        }
    }

    public static class PetEnterQueue {
        private Pet pet;
        private long count;

        public PetEnterQueue(Pet pet, long count) {
            this.pet = pet;
            this.count = count;
        }

        public Pet getPet() {
            return pet;
        }

        public long getCount() {
            return count;
        }

        public String getEnterPetType() {
            return this.pet.getPetType();
        }
    }

    public static class DogCatQueue {
        private Queue<PetEnterQueue> dogQ;
        private Queue<PetEnterQueue> catQ;
        private long count;

        public DogCatQueue() {
            dogQ = new LinkedList<>();
            catQ = new LinkedList<>();
            count = 0;
        }


        public void add(Pet pet) {
            if ("dog".equals(pet.getPetType())) {
                dogQ.add(new PetEnterQueue(pet, count++));
            } else if ("cat".equals(pet.getPetType())) {
                catQ.add(new PetEnterQueue(pet, count++));
            } else {
                throw new RuntimeException("err, not dog or cat");
            }
        }

        public Pet pollAll() {
            if (!this.dogQ.isEmpty() && !this.catQ.isEmpty()) {
                if (dogQ.peek().count < catQ.peek().count) {
                    return dogQ.poll().getPet();
                } else {
                    return catQ.poll().getPet();
                }
            } else if (!dogQ.isEmpty()) {
                return dogQ.poll().getPet();
            } else if (!catQ.isEmpty()) {
                return catQ.poll().getPet();
            } else {
                throw new RuntimeException("err, queue is empty!");
            }
        }

        public Dog pollDog() {
            if (!isDogEmpty()) {
                return (Dog) dogQ.poll().getPet();
            } else {
                throw new RuntimeException("Dog queue is empty!");
            }
        }

        public Cat pollCat() {
            if (!isCatEmpty()) {
                return (Cat) catQ.poll().getPet();
            } else {
                throw new RuntimeException("Cat queue is empty!");
            }
        }

        public boolean isEmpty() {
            return this.dogQ.isEmpty() && this.catQ.isEmpty();
        }

        public boolean isDogEmpty() {
            return this.dogQ.isEmpty();
        }

        public boolean isCatEmpty() {
            return this.catQ.isEmpty();
        }
    }

}
