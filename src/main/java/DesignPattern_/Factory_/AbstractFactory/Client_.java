package DesignPattern_.Factory_.AbstractFactory;

public class Client_ {
    public static void main(String[] args) {
        ComputerEngineer computerEngineer = new ComputerEngineer();

        AbstractFactory af = new IntelFactory();
        computerEngineer.makeComputer(af);
    }
}
