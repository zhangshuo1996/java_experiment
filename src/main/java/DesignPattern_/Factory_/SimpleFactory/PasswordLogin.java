package DesignPattern_.Factory_.SimpleFactory;

public class PasswordLogin implements Login{
    @Override
    public boolean verify(String name, String password) {
        return false;
    }
}
