class WorklogApi extends RestApi {
    constructor() {
        super("/worklogs/");
    }

    listDetailed = function () {
        return this.list("detailed");
    }
}
