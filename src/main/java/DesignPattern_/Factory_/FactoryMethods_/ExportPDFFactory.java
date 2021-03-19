package DesignPattern_.Factory_.FactoryMethods_;

public class ExportPDFFactory implements ExportFactory_{
    @Override
    public ExportFile factory(String type) {
        if("standard".equals(type)){
            return new ExportStandardPDFFile();
        }else if("finanical".equals(type)){
            return new ExportFinancialPdfFile();
        }
        return null;
    }
}
