package org.fruitstore.normal;

public class DemoTest {
    public static void main(String[] args) {
        FruitStore fruitStore = new FruitStore("阳光水果商店");
        fruitStore.newArrival("banana");
        System.out.println();
        fruitStore.discount(5);
    }
}
