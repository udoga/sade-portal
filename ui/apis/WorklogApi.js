class WorklogApi extends RestApi {
    constructor() {
        super("/worklogs/");
    }

    listWithDetails = function () {
        return this.list("with-details");
    }
}
