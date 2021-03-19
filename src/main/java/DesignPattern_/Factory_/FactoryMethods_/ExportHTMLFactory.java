package DesignPattern_.Factory_.FactoryMethods_;

public class ExportHTMLFactory implements ExportFactory_{
    @Override
    public ExportFile factory(String type) {
        if("standard".equals(type)){
            return new ExportStandardHtmlFile();
        }else if("finanical".equals(type)){
            return new ExportFinancialHtmlFile();
        }
        return null;
    }
}
