package DesignPattern_.Factory_.AbstractFactory;

public class IntelFactory implements AbstractFactory {
    @Override
    public CPU createCpu() {
        return new IntelCPU(755);
    }

    @Override
    public MainBoard createMainBoard() {
        return new IntelMainBoard(755);
    }
}
