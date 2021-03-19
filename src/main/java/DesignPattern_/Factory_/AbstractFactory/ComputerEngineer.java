package DesignPattern_.Factory_.AbstractFactory;

public class ComputerEngineer {
    public CPU cpu = null;
    public MainBoard mainBoard = null;

    public void makeComputer(AbstractFactory af){
        prepareHardWare(af);

    }

    public void prepareHardWare(AbstractFactory af){
        this.cpu = af.createCpu();
        this.mainBoard = af.createMainBoard();

        this.cpu.calculate();
        this.mainBoard.installCPU();
    }
}
