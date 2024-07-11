class ExpenseApi extends RestApi {
    constructor() {
        super("/expenses/");
    }

    listWithEmployee = function () {
        return this.list("with-employee");
    }
}
