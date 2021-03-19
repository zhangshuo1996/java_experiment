package DesignPattern_.Factory_.FactoryMethods_;

public class ExportStandardPDFFile implements ExportFile {
    @Override
    public boolean export(String data) {

        System.out.println("导出标准PDF数据");
        return false;
    }
}
