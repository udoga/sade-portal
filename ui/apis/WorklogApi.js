class WorklogApi extends RestApi {
    constructor() {
        super("/worklogs/");
    }

    listDetailedBy = function (params) {
        return this.list("detailed", params);
    }
}
