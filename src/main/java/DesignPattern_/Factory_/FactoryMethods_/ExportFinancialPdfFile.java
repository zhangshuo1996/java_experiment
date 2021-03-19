package DesignPattern_.Factory_.FactoryMethods_;

public class ExportFinancialPdfFile implements ExportFile {
    @Override
    public boolean export(String data) {
        System.out.println("导出财务PDF数据");
        return false;
    }
}
