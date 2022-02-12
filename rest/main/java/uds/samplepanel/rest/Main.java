package uds.samplepanel.rest;

import uds.samplepanel.base.Props;
import uds.samplepanel.base.Resource;

public class Main {
    public static void main(String[] args) {
        Props props = new Props((args.length > 0)? args[0] : new Resource("default.properties").findPath());
        System.out.println(props.get("port"));
    }
}
