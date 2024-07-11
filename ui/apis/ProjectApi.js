class ProjectApi extends RestApi {
    constructor() {
        super("/projects/");
    }

    listDetailed = function () {
        return this.list("detailed");
    }
}
