package tecnologo.nosql.laboratorio.Error;


import java.util.List;

public class Error {

    //id
    public int id;
    public String info;

    public Error(int id, String info) {
        this.id = id;
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}