package DesignPattern_.Factory_.FactoryMethods_;

public class ExportStandardHtmlFile implements ExportFile {
    @Override
    public boolean export(String data) {

        System.out.println("导出标准html数据");
        return false;
    }
}
