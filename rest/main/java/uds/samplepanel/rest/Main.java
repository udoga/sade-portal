package uds.samplepanel.rest;

import uds.samplepanel.base.Props;
import uds.samplepanel.base.PathUtil;

public class Main {
    public static void main(String[] args) {
        Props props = new Props((args.length > 0)? args[0] : PathUtil.findResourcePath("default.properties"));
        System.out.println(props.get("port"));
    }
}
