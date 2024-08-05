class ExpenseApi extends RestApi {
    constructor() {
        super("/expenses/");
    }

    listDetailed = function () {
        return this.list("detailed");
    }

    readDetailed = function (id) {
        return this.read(id, "detailed/");
    }
}
