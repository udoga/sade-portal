class ExpenseApi extends RestApi {
    constructor() {
        super("/expenses/");
    }

    listDetailed = function () {
        return this.list("detailed");
    }
}
