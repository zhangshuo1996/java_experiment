package DesignPattern_.Factory_.AbstractFactory;

public class AmdCPU implements CPU {
    /**
     * CPU的针脚数
     */
    private int pins = 0;
    public  AmdCPU(int pins){
        this.pins = pins;
    }
    @Override
    public void calculate() {
        // TODO Auto-generated method stub
        System.out.println("AMD CPU的针脚数：" + pins);
    }
}
