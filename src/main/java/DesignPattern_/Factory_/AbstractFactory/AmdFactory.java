package DesignPattern_.Factory_.AbstractFactory;

public class AmdFactory implements AbstractFactory {
    @Override
    public CPU createCpu() {
        return new AmdCPU(938);
    }

    @Override
    public MainBoard createMainBoard() {
        return new AmdMainBoard(938);
    }
}
