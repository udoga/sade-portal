class ProjectApi extends RestApi {
    constructor() {
        super("/projects/");
    }

    listWithClient = function () {
        return this.list("with-client");
    }
}
