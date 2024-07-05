class EmployeeApi extends RestApi {
    constructor() {
        super("/employees/");
    }

    listByUserId = function (userId) {
        return this.list("?userId=" + userId);
    }

    listWithUser = function () {
        return this.list("with-user");
    }
}
