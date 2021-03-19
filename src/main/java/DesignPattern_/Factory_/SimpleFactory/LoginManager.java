package DesignPattern_.Factory_.SimpleFactory;

public class LoginManager {
    public static Login factory(String type){
        if(type.equals("password")){
            return new PasswordLogin();
        }else if(type.equals("passcord")){
            return new DomainLogin();
        }else{
            return null;
        }
    }
}
