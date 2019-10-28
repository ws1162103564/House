package cn.kgc.utils;



public class tiaojianfenye  extends pageUtils{

private String name;
private String telephone;

    public tiaojianfenye(String name, String telephone) {
        this.name = name;
        this.telephone = telephone;
    }

    public tiaojianfenye() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "tiaojianfenye{" +
                "name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
