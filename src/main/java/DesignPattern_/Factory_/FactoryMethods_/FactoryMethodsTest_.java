package DesignPattern_.Factory_.FactoryMethods_;

public class FactoryMethodsTest_ {
    public static void main(String[] args) {
        ExportFactory_ exportFactory_ = new ExportHTMLFactory();
        ExportFile exportFile = exportFactory_.factory("standard");
        System.out.println(exportFile.getClass());
    }
}
