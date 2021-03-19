package DesignPattern_.Factory_.FactoryMethods_;

public class ExportFinancialHtmlFile implements ExportFile{
    @Override
    public boolean export(String data) {
        System.out.println("导出财务HTML");
        return false;
    }
}
