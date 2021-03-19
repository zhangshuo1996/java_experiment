package DesignPattern_.Factory_.SimpleFactory;

public class SimpleFactoryTest_ {
    public static void main(String[] args) {

        Login login = LoginManager.factory("password");
        System.out.println(login.getClass().toString());
    }
}
