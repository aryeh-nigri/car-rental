package anigri.jct.ac.il.android5778_7866_2.model.backend;

import anigri.jct.ac.il.android5778_7866_2.model.datasource.MySQL_DB_Manager;

/**
 * Created by Bruno on 15/01/2018.
 */

public class DB_Manager_Factory {

    private static DB_Manager manager = null;

    public static DB_Manager getManager(){
        if(manager == null){
            //manager = new List_DB_Manager();
            manager = new MySQL_DB_Manager();
        }

        return manager;
    }

}
