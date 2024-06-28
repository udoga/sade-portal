class EmployeeApi extends RestApi {
    constructor() {
        super("/employees/");
    }

    listByUserId = function (userId) {
        return this.list("?userId=" + userId);
    }
}
