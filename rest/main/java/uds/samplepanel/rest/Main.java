package uds.samplepanel.rest;

import uds.samplepanel.base.MemoryTable;
import uds.samplepanel.base.Props;
import uds.samplepanel.base.PathUtil;
import uds.samplepanel.base.Table;
import uds.samplepanel.core.User;
import uds.samplepanel.core.UserService;

public class Main {
    public static void main(String[] args) {
        Props props = new Props((args.length > 0)? args[0] : PathUtil.findResourcePath("default.properties"));
        Table<User> userTable = new MemoryTable<>("User");
        UserService userService = new UserService(userTable);
        RestServer server = new RestServer(props.getInt("port"), userService);
        server.run();
    }
}
