package tecnologo.nosql.laboratorio.Error;


import lombok.Data;

import java.util.List;

@Data
public class CodigoError extends Exception{

    //id
    public int id;
    public String info;

    public CodigoError(int id, String info) {
        this.id = id;
        this.info = info;
    }
}