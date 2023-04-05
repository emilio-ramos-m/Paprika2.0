package test.example;

public class B {
    private C c;
    B(){
        createC();
    }
    private void createC(){
        this.c = new C();
    }
    public C getC() {
        return c;
    }
}
