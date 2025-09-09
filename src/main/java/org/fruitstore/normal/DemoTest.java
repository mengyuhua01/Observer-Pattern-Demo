package org.fruitstore.normal;

public class DemoTest {
    public static void main(String[] args) {
        FruitStore fruitStore = new FruitStore("港珠没有澳");
        fruitStore.newArrival("banana");
        System.out.println();
        fruitStore.discount(5);
    }
}
